package com.xueersi.parentsmeeting.modules.groupclass.business.rtc;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.ImageAssetDelegate;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieImageAsset;
import com.czt.mp3recorder.util.LameUtil;
import com.tal.speech.config.SpeechConfig;
import com.tal.speech.speechrecognizer.Constants;
import com.tal.speech.speechrecognizer.EvaluatorListener;
import com.tal.speech.speechrecognizer.PCMFormat;
import com.tal.speech.speechrecognizer.ResultCode;
import com.tal.speech.speechrecognizer.ResultEntity;
import com.tal.speech.speechrecognizer.SpeechParamEntity;
import com.tal.speech.utils.SpeechUtils;
import com.xes.ps.rtcstream.RTCEngine;
import com.xueersi.lib.framework.utils.SizeUtils;
import com.xueersi.lib.framework.utils.file.FileUtils;
import com.xueersi.lib.imageloader.ImageLoader;
import com.xueersi.lib.log.LoggerFactory;
import com.xueersi.lib.log.logger.Logger;
import com.xueersi.parentsmeeting.module.videoplayer.media.VP;
import com.xueersi.parentsmeeting.modules.groupclass.R;
import com.xueersi.parentsmeeting.modules.groupclass.business.rtc.entity.VoiceTestEntity;
import com.xueersi.parentsmeeting.modules.groupclass.widget.VoiceEffectView;
import com.xueersi.parentsmeeting.modules.livevideo.achievement.business.RTCVideoAction;
import com.xueersi.parentsmeeting.modules.livevideo.entity.LiveVideoPoint;
import com.xueersi.parentsmeeting.modules.livevideo.entity.LottieEffectInfo;
import com.xueersi.parentsmeeting.modules.livevideo.page.LiveBasePager;
import com.xueersi.parentsmeeting.modules.livevideo.util.LiveCacheFile;
import com.xueersi.parentsmeeting.modules.livevideo.util.LiveSoundPool;
import com.xueersi.parentsmeeting.modules.livevideo.util.ProxUtil;
import com.xueersi.parentsmeeting.modules.livevideo.util.StandLiveMethod;
import com.xueersi.parentsmeeting.modules.livevideo.widget.BasePlayerFragment;
import com.xueersi.ui.widget.WaveView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date on 2019/10/15 19:07
 * @Author zhangyuansun
 * @Description
 */
public class RTCVideoPager extends LiveBasePager implements View.OnClickListener {
    protected Logger logger = LoggerFactory.getLogger("wangyongchao");
    private OnVoiceTestEndListener mVoiceEndListener;
    private boolean mIsForceStop;//强制收题
    private VoiceTestEntity mVoiceTestEntity;
    private LiveSoundPool mLiveSoundPool;
    private FileOutputStream mFileOutputStream;
    private int bufferSize = 640;
    private byte[] mMp3Buffer;
    private byte[] mMp3FlushBuffer;
    private byte[] mMp3Total;
    /** 采样率 */
    private static final int DEFAULT_SAMPLING_RATE = 16000;
    /** 设置为单声道 */
    private static final int DEFAULT_CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_DEFAULT;
    /** 音频格式 */
    private static final PCMFormat DEFAULT_AUDIO_FORMAT = PCMFormat.PCM_16BIT;
    private boolean isInit;


    public interface OnMicrophoneStatusChanedListener {
        void onMicrophoneStatusChaned(boolean status);
    }

    public interface OnVoiceTestEndListener {
        void onVoiceTestEndListener(int score, int isForce, double answerTimeDuration);
    }

    private Handler volumeUpdateHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int volume = msg.arg1;
            float fVolume = (float) volume * 3 / 90;
            fVolume = fVolume < 0.5f ? 0.5f : fVolume;
            userWaveView.setWaveAmplitude(fVolume);
        }
    };


    OnMicrophoneStatusChanedListener onMicrophoneStatusChanedListener;

    public void setOnMicrophoneStatusChanedListener(OnMicrophoneStatusChanedListener onMicrophoneStatusChanedListener) {
        this.onMicrophoneStatusChanedListener = onMicrophoneStatusChanedListener;
    }

    public void setOnVoiceTestEndListener(OnVoiceTestEndListener listener) {
        this.mVoiceEndListener = listener;
    }

    static final String TAG = "VoiceControlView";

    private class ToastRunnable implements Runnable {
        @Override
        public void run() {
            setViewVisibility(toastLabel, View.GONE);
            showToasting = false;
            checkAppSetting();
        }
    }

    private class RtcCallback extends RtcDefaultListener {
        @Override
        public void localUserJoindWithUid(final int uid) {
            Log.i(TAG, "localUserJoindWithUid uid = " + uid);
        }

        @Override
        public void remoteUserJoinWitnUid(final int uid) {
            final String text = String.valueOf(uid);
            if (text.equals(teamId))
                Log.i(TAG, "remoteUserJoinWitnUid uid = " + uid);

            Runnable action = new Runnable() {
                @Override
                public void run() {
                    if (haveTeamUsr && text.equals(teamId)) {
                        installRemoteId(uid);
                        tcpAudioStatChange();
                    }
                }
            };
            post(action);
        }

        @Override
        public void remotefirstVideoRecvWithUid(final int uid) {
            final String text = String.valueOf(uid);
            if (text.equals(teamId))
                Log.i(TAG, "remotefirstVideoRecvWithUid uid = " + uid);

            Runnable action = new Runnable() {
                @Override
                public void run() {
                    if (haveTeamUsr && text.equals(teamId)) {
                        teamVideoReady = true;
                        buildRemoteVideo(uid);
                    }
                }
            };
            post(action);
        }

        @Override
        public void remotefirstAudioRecvWithUid(final int uid) {
            final String text = String.valueOf(uid);
            if (text.equals(teamId))
                Log.i(TAG, "remotefirstAudioRecvWithUid uid = " + uid);
            Runnable action = new Runnable() {
                @Override
                public void run() {
                    if (haveTeamUsr && text.equals(teamId)) {
                        teamAudioReady = true;
                        updateElementState();
                    }
                }
            };
            post(action);
        }

        @Override
        public void didOfflineOfUid(final int uid) {
            Log.i(TAG, "didOfflineOfUid uid = " + uid);
            final String text = String.valueOf(uid);
            Runnable action = new Runnable() {
                @Override
                public void run() {
                    if (haveTeamUsr && text.equals(teamId)) {
                        clearRemoteVideo();
                    }
                }
            };
            post(action);
        }

        @Override
        public void didAudioMuted(int uid, boolean muted) {
            Log.i(TAG, "didAudioMuted uid=" + uid + " muted=" + muted);
        }

        @Override
        public void didVideoMuted(int uid, boolean muted) {
            Log.i(TAG, "didVideoMuted uid=" + uid + " muted=" + muted);
        }

        @Override
        public void didOccurError(final RTCEngine.RTCEngineErrorCode code) {
            Log.i(TAG, "didOccurError code=" + code);
            Runnable action = new Runnable() {
                @Override
                public void run() {
                    onLocalErrorCode(code);
                }
            };
            post(action);
        }

        @Override
        public void onRemoteVideoStateChanged(final int uid, final int state) {
            Log.i(TAG, "onRemoteVideoStateChanged uid=" + uid + " state=" + state);
        }
    }

    private String userId;
    private String teamId;
    private String rtcToken;
    private String userName;
    private String userLogo;
    private String teamName;
    private String teamLogo;

    private int previewWidth;
    private int previewHeight;

    private ImageView ivBackground;

    private FrameLayout toastGroup;
    private TextView toastLabel;

    private FrameLayout frUserVideo;
    private FrameLayout frTeamVideo;

    private ImageView userLargeBg;
    private ImageView teamLargeBg;

    private ImageView ivUserCover;
    private ImageView ivTeamCover;

    private ImageView userSmallBg;
    private ImageView teamSmallBg;

    private TextView tvNoneTeam;

    private ImageView icUserImage;
    private ImageView icTeamImage;

    private TextView tvUserNick;
    private TextView tvTeamNick;

    private ImageView icUserVoice;
    private ImageView icTeamVoice;

    private TextView tvUserGold;
    private TextView tvTeamGold;

    private SurfaceView userSurface;
    private SurfaceView teamSurface;

    private VoiceEffectView userEffView;
    private VoiceEffectView teamEffView;

    private WaveView userWaveView;
    private WaveView teamWaveView;

    private boolean showToasting = false;
    private ToastRunnable toastRunnable = null;

    private String remoteUser;
    private RTCEngine mRTCEngine;

    private boolean isBackground = false;
    private boolean isPlackBack = false;
    private boolean isVoiceStat = false;
    private boolean spreadState = true;
    private boolean haveTeamUsr = true;

    private boolean userVideoReady = false;
    private boolean userAudioReady = false;

    private boolean teamVideoReady = false;
    private boolean teamAudioReady = false;

    private boolean teamAllowdAudio = true;
    private boolean enableSelfAudio = true;
    private boolean enableTeamAudio = true;

    private boolean selfVideoMuted = false;
    private boolean selfAudioMuted = false;
    private boolean teamAudioMuted = false;

    private LinearLayout settingGroup;
    private TextView settingLabel;
    private ImageView settingImage;

    private Button switchButton;

    private LottieAnimationView readyGoView;
    private SpeechUtils mSpeechUtils;
    private File savedir;
    TextView voicetext;

    public RTCVideoPager(Context context) {
        this(context, false);
    }

    public RTCVideoPager(Context context, boolean isPlackBack) {
        super(context);
        this.isPlackBack = isPlackBack;
        if (isPlackBack) {
            this.spreadState = false;
        }
        initData();
        initListener();
        checkPermissons();
    }

    public void closePager() {
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }

        releaseRes();
    }

    @Override
    public View initView() {
        mView = View.inflate(mContext, R.layout.pager_groupclass_rtc_video, null);
        previewWidth = getDimension(R.dimen.groupclass_videoview_width);
        previewHeight = getDimension(R.dimen.groupclass_videoview_height);

        ivBackground = mView.findViewById(R.id.ivBackground);
        toastGroup = mView.findViewById(R.id.toastGroup);
        toastLabel = mView.findViewById(R.id.toastLabel);
        frUserVideo = mView.findViewById(R.id.frUserVideo);
        frTeamVideo = mView.findViewById(R.id.frTeamVideo);

        userLargeBg = mView.findViewById(R.id.userLargeBg);
        teamLargeBg = mView.findViewById(R.id.teamLargeBg);

        ivUserCover = mView.findViewById(R.id.ivUserCover);
        ivTeamCover = mView.findViewById(R.id.ivTeamCover);

        userSmallBg = mView.findViewById(R.id.userSmallBg);
        teamSmallBg = mView.findViewById(R.id.teamSmallBg);

        icUserVoice = mView.findViewById(R.id.icUserVoice);
        icTeamVoice = mView.findViewById(R.id.icTeamVoice);

        icUserImage = mView.findViewById(R.id.icUserImage);
        icTeamImage = mView.findViewById(R.id.icTeamImage);

        tvUserNick = mView.findViewById(R.id.tvUserNick);
        tvTeamNick = mView.findViewById(R.id.tvTeamNick);

        tvUserGold = mView.findViewById(R.id.tvUserGold);
        tvTeamGold = mView.findViewById(R.id.tvTeamGold);

        tvNoneTeam = mView.findViewById(R.id.tvNoneTeam);

        settingGroup = mView.findViewById(R.id.settingGroup);
        settingLabel = mView.findViewById(R.id.settingLabel);
        settingImage = mView.findViewById(R.id.settingImage);

        userEffView = mView.findViewById(R.id.userEffView);
        teamEffView = mView.findViewById(R.id.teamEffView);

        userWaveView = mView.findViewById(R.id.userWaveView);
        teamWaveView = mView.findViewById(R.id.teamWaveView);

        switchButton = mView.findViewById(R.id.switchButton);

        readyGoView = mView.findViewById(R.id.lav_readygo_view);

        Runnable action = new Runnable() {
            @Override
            public void run() {
                userWaveView.initialize();
                userWaveView.start();

                teamWaveView.initialize();
                teamWaveView.start();
            }
        };

        postDelayed(action, 400);

        mView.findViewById(R.id.testVoice).setOnClickListener(this);
        mView.findViewById(R.id.stopVoice).setOnClickListener(this);
        voicetext = mView.findViewById(R.id.voice_text);

        if (isPlackBack) {
            this.spreadState = false;
            updateElementState();
        }

        icUserVoice.setOnClickListener(this);

        icTeamVoice.setOnClickListener(this);

        settingImage.setOnClickListener(this);

        switchButton.setOnClickListener(this);

        return mView;
    }

    /** mp3编码 */
    private static final int DEFAULT_LAME_MP3_BIT_RATE = 128;
    /** mp3编码压缩倍数 */
    private static final int DEFAULT_LAME_MP3_QUALITY = 7;

    @Override
    public void initData() {
        super.initData();
        savedir = LiveCacheFile.geCacheFile(mContext, "livevoice");
        mSpeechUtils = SpeechUtils.getInstance(mContext.getApplicationContext());
        mSpeechUtils.prepar();
        if(!isInit){
            isInit=true;
            LameUtil.init(DEFAULT_SAMPLING_RATE, DEFAULT_CHANNEL_CONFIG, DEFAULT_SAMPLING_RATE,
                    DEFAULT_LAME_MP3_BIT_RATE, DEFAULT_LAME_MP3_QUALITY);
        }

        initReadyGoAnimation();
    }

    @Override
    public void onResume() {
        isBackground = false;
        checkPermissons();
        checkRtcPreview();
        updateElementState();
    }

    @Override
    public void onPause() {
        isBackground = true;
        setVideoAudioState();
    }

    public void releaseRes() {
        userWaveView.stop();
        teamWaveView.stop();

        if (mRTCEngine != null) {
            mRTCEngine.destory();
            mRTCEngine = null;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.icUserVoice) {
            if (isPlackBack) {
                return;
            }

            if (spreadState) {
                if (enableSelfAudio) {
                    Toast toast = Toast.makeText(mContext, "小伙伴听不到你的声音啦", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }

                enableSelfAudio = !enableSelfAudio;
                updateElementState();
                tcpAudioStatChange();
            }
        } else if (id == R.id.icTeamVoice) {
            if (spreadState) {
                enableTeamAudio = !enableTeamAudio;
                updateElementState();
            }
        } else if (id == R.id.settingImage) {
            checkAppSetting();
        } else if (id == R.id.switchButton) {
            if (isPlackBack) {
                Toast toast = Toast.makeText(mContext, "回放模式，不能展开", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            } else {
                spreadState = !spreadState;
                updateElementState();
            }
        } else if (id == R.id.testVoice) {

            buildMp3File();
            VoiceTestEntity voiceTestEntity = buildTestData();
            openVoiceTest(voiceTestEntity);
        } else if (id == R.id.stopVoice) {
            closeVoiceTest();
            if (mFileOutputStream != null) {
                try {
                    mFileOutputStream.flush();
                    mFileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void buildMp3File() {
        bufferSize = AudioRecord.getMinBufferSize(DEFAULT_SAMPLING_RATE,
                DEFAULT_CHANNEL_CONFIG, DEFAULT_AUDIO_FORMAT.getAudioFormat());
        mMp3Buffer = new byte[(int) (7200 + (bufferSize * 2 * 1.25))];
        mMp3FlushBuffer = new byte[(int) (7200 + (bufferSize * 2 * 1.25))];
        mMp3Total = new byte[(int) (14400 + (bufferSize * 2 * 1.25))];
        if (savedir != null) {
            File saveVideoFile = new File(FileUtils.createOrExistsDirForFile(savedir.getPath()),
                    "test" + System.currentTimeMillis() + ".mp3");
            logger.d("saveVideoFile=" + saveVideoFile);
            if (!saveVideoFile.exists()) {
                try {
                    saveVideoFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (!TextUtils.isEmpty(saveVideoFile.getPath())) {
                try {
                    this.mFileOutputStream =
                            new FileOutputStream(new File(saveVideoFile.getPath()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void setupParams(String token, String userId, String userName, String userLogo,
                            String teamId, String teamName,
                            String teamLogo, boolean haveTeamUsr) {

        this.userId = userId;
        this.teamId = teamId;
        this.rtcToken = token;
        this.haveTeamUsr = haveTeamUsr;
        Log.i(TAG, "userId=" + userId + " teamId=" + teamId);

        initVoiceToken();

        if (!TextUtils.isEmpty(userName) && !userName.equals(this.userName)) {
            tvUserNick.setText(userName);
            this.userName = userName;
        }

        if (!TextUtils.isEmpty(userLogo) && !userLogo.equals(this.userLogo)) {
            this.userLogo = userLogo;
            ImageLoader.with(mContext).load(userLogo).asCircle().into(icUserImage);
            ImageLoader.with(mContext).load(userLogo).into(ivUserCover);
        }

        if (!TextUtils.isEmpty(teamName) && !teamName.equals(this.teamName)) {
            tvTeamNick.setText(teamName);
            this.teamName = teamName;
        }

        if (!TextUtils.isEmpty(teamLogo) && !teamLogo.equals(this.teamLogo)) {
            this.teamLogo = teamLogo;
            ImageLoader.with(mContext).load(teamLogo).asCircle().into(icTeamImage);
            ImageLoader.with(mContext).load(teamLogo).into(ivTeamCover);
        }

        updateElementState();
    }

    public void updateSpread(boolean isSpread) {
        if (isPlackBack) {
            return;
        }
        if (spreadState != isSpread) {
            spreadState = isSpread;
            updateElementState();
        }
    }

    public void updateUserGold(int gold) {
        tvUserGold.setText(String.valueOf(gold));
    }

    public void updateTeamGold(int gold) {
        tvTeamGold.setText(String.valueOf(gold));
    }

    //收到队友主动关闭麦克风消息
    public void updateTeamMacrophone(boolean enable) {
        if (teamAllowdAudio != enable) {
            teamAllowdAudio = enable;
            updateElementState();
        }
    }

    public void addUserGold(int gold) {
        int previoesGold = Integer.valueOf(tvUserGold.getText().toString());
        tvUserGold.setText(String.valueOf(previoesGold + gold));
    }

    /*
     * 金币飞入本场成就区动画
     */
    public void updateUserGold(final int gold, final float getX, final float getY, final int type) {
        if (gold <= 0) {
            return;
        }
        startGoldAnimition(gold, getX, getY, type, 3);
    }

    private void startGoldAnimition(final int gold, final float getX, final float getY,
                                    final int type, final int repeatTimes) {
        if (repeatTimes < 1) {
            return;
        } else {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    startGoldAnimition(gold, getX, getY, type, repeatTimes - 1);
                }
            }, 16);
        }
        final ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(R.drawable.groupclass_small_gold_icon);
        imageView.setX(getX);
        imageView.setY(getY);
        if (type == RTCVideoAction.GOLD_TYPE_REDPACKAGE) {
            imageView.setX(LiveVideoPoint.getInstance().screenWidth / 2 - SizeUtils.Dp2Px(mContext, 18));
            imageView.setY(LiveVideoPoint.getInstance().screenHeight / 2 - SizeUtils.Dp2Px(mContext, 18));
        }
        ((ViewGroup) mView).addView(imageView);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = SizeUtils.Dp2Px(mContext, 37);
        layoutParams.height = SizeUtils.Dp2Px(mContext, 37);
        imageView.setLayoutParams(layoutParams);

        int[] location = new int[2];
        imageView.getLocationOnScreen(location);

        final float x = location[0];
        final float y = location[1];
        int[] lo = new int[2];
        tvUserGold.getLocationInWindow(lo);
        final float targetX = lo[0];
        final float targetY = lo[1];

        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(500);
        valueAnimator.setObjectValues(new PointF(x, y));
        valueAnimator.setInterpolator(new LinearInterpolator());

        //首先判断 目标点在上还是在下
        final boolean flagX = (x - targetX) > 0;
        final boolean flagY = (y - targetY) > 0;

        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {

            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                PointF point = new PointF();
                float fractionNeed = 1 - fraction;

                if (flagX) {
                    float vX = x - targetX;
                    point.x = vX * fractionNeed + targetX;
                } else {
                    float vX = targetX - x;
                    point.x = x + vX * fraction;
                }

                if (flagY) {
                    float vY = y - targetY;
                    point.y = vY * fractionNeed + targetY;
                } else {
                    float vY = targetY - y;
                    point.y = y + vY * fraction;
                }
                return point;

            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                float vX = point.x;
                float vY = point.y;
                //说明vx 最大值就是view原坐标
                if (flagX) {
                    if (vX <= x && vX >= targetX) {
                        imageView.setX(vX);
                    }
                } else {//说明vx 最小值就是view原坐标
                    if (vX >= x && vX <= targetX) {
                        imageView.setX(vX);
                    }
                }

                //说明vY 最大值就是view原坐标
                if (flagY) {
                    if (vY <= y && vY >= targetY) {
                        imageView.setY(vY);
                    }
                } else {//说明vx 最小值就是view原坐标
                    if (vY >= y && vY <= targetY) {
                        imageView.setY(vY);
                    }
                }

                ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                layoutParams.width = SizeUtils.Dp2Px(mContext,
                        37 - 17 * animation.getAnimatedFraction());
                layoutParams.height = SizeUtils.Dp2Px(mContext,
                        37 - 17 * animation.getAnimatedFraction());
                imageView.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ((ViewGroup) mView).removeView(imageView);
                if (repeatTimes == 1) {
                    addUserGold(gold);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.start();
    }

    public int getUserGoldNum() {
        return Integer.valueOf(tvUserGold.getText().toString());
    }

    private int getDimension(int resid) {
        return mContext.getResources().getDimensionPixelSize(resid);
    }

    private void checkPermissons() {
        boolean allowRecord = ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
        boolean allowCamera = ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;

        if (showToasting) {
            settingGroup.setVisibility(View.VISIBLE);
            return;
        } else if (isPlackBack) {
            if (allowRecord) {
                settingGroup.setVisibility(View.GONE);
                settingLabel.setText(null);
            } else {
                settingGroup.setVisibility(View.VISIBLE);
                settingLabel.setText("麦克风不可用");
            }
        } else if (allowCamera) {
            if (allowRecord) {
                settingGroup.setVisibility(View.GONE);
                settingLabel.setText(null);
            } else {
                settingGroup.setVisibility(View.VISIBLE);
                settingLabel.setText("麦克风不可用");
            }
        } else {
            if (allowRecord) {
                settingGroup.setVisibility(View.VISIBLE);
                settingLabel.setText("摄像头不可用");
            } else {
                settingGroup.setVisibility(View.VISIBLE);
                settingLabel.setText("麦克风和摄像头不可用");
            }
        }
    }

    private void checkRtcPreview() {
        if (mRTCEngine == null) {
            return;
        }
        boolean allowRecord = ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
        boolean allowCamera = ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;

        if ((allowCamera && !userVideoReady) || (allowRecord && !userAudioReady)) {
            if (teamSurface != null) {
                frTeamVideo.removeView(teamSurface);
                teamSurface = null;
            }

            if (userSurface != null) {
                frUserVideo.removeView(userSurface);
                userSurface = null;
            }

            if (remoteUser != null) {
                remoteUser = null;
            }

            mRTCEngine.destory();
            mRTCEngine = null;

            teamVideoReady = false;
            teamAudioReady = false;

            initVoiceToken();
        }
    }

    private void initVoiceToken() {
        if (isPlackBack || TextUtils.isEmpty(rtcToken) || mRTCEngine != null) {
            return;
        }

        Log.i(TAG, "initVoiceToken token=" + rtcToken);

        mRTCEngine = new RTCEngine(mContext, new RtcCallback());
        mRTCEngine.initWithToken(rtcToken);
        if (userSurface == null) {
            FrameLayout.LayoutParams params =
                    new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                            FrameLayout.LayoutParams.MATCH_PARENT);
            int margins = SizeUtils.Dp2Px(mContext, 6);
            params.leftMargin = margins;
            params.topMargin = margins;
            params.rightMargin = margins;
            params.bottomMargin = margins;
            userSurface = mRTCEngine.createRendererView();
            userSurface.setZOrderMediaOverlay(true);
            frUserVideo.addView(userSurface, 1, params);
        }
        mRTCEngine.setVideoEncoderConfiguration(previewWidth, previewHeight,
                RTCEngine.RTCEngineVideoBitrate.VIDEO_BITRATE_350,
                RTCEngine.RTC_ORIENTATION_MODE.RTC_ORIENTATION_MODE_FIXED_LANDSCAPE);
        mRTCEngine.setRecordingAudioParameters(16000, AudioFormat.CHANNEL_IN_DEFAULT);
        mRTCEngine.startPreview();
        mRTCEngine.enableVideo();
        mRTCEngine.setupLocalVideo(userSurface);
        if (!haveTeamUsr) {
            mRTCEngine.muteAllRemoteAudio(true);
            mRTCEngine.muteAllRemoteVideo(true);
        }
        mRTCEngine.joinRoom();


        userVideoReady = true;
        userAudioReady = true;
    }

    private void tcpAudioStatChange() {
        if (onMicrophoneStatusChanedListener != null) {
            onMicrophoneStatusChanedListener.onMicrophoneStatusChaned(enableSelfAudio);
        }
    }

    private void updateElementState() {

        if (isPlackBack) {
            teamLargeBg.setImageResource(R.drawable.groupclass_voicecontrol_backspace);
            teamSmallBg.setImageResource(R.drawable.groupclass_voicecontrol_noteam_bg);
        } else if (!haveTeamUsr) {
            teamLargeBg.setImageResource(R.drawable.groupclass_voicecontrol_livenoteam);
            teamSmallBg.setImageResource(R.drawable.groupclass_voicecontrol_user_bg);
        } else if (remoteUser == null) {
            teamLargeBg.setImageResource(R.drawable.groupclass_voicecontrol_offline_bg);
            teamSmallBg.setImageResource(R.drawable.groupclass_voicecontrol_user_bg);
        } else {
            teamLargeBg.setImageResource(R.drawable.groupclass_voicecontrol_logo_bg);
            teamSmallBg.setImageResource(R.drawable.groupclass_voicecontrol_user_bg);
        }

        if (spreadState) {
            ivBackground.setBackgroundResource(R.drawable.groupclass_voicecontrol_large_bg);

            if (enableSelfAudio) {
                icUserVoice.setImageResource(R.drawable.groupclass_microphone_enable_ic);
            } else {
                icUserVoice.setImageResource(R.drawable.groupclass_microphone_disable_ic);
            }

            if (!teamAllowdAudio) {
                icTeamVoice.setImageResource(R.drawable.groupclass_microphone_forbidden);
            } else if (enableTeamAudio) {
                icTeamVoice.setImageResource(R.drawable.groupclass_microphone_enable_ic);
            } else {
                icTeamVoice.setImageResource(R.drawable.groupclass_microphone_disable_ic);
            }


            int height = getDimension(R.dimen.voiceview_large_size);
            updateViewHeight(ivBackground, height);

            setViewVisibility(frUserVideo, View.VISIBLE);
            setViewVisibility(frTeamVideo, View.VISIBLE);

            setViewVisibility(userLargeBg, View.VISIBLE);
            setViewVisibility(teamLargeBg, View.VISIBLE);

            setViewVisibility(userSmallBg, View.GONE);
            setViewVisibility(teamSmallBg, View.GONE);

            if (isVoiceStat) {
                setViewVisibility(icUserVoice, View.GONE);
            } else {
                setViewVisibility(icUserVoice, View.VISIBLE);
            }

            if (haveTeamUsr && remoteUser != null) {
                setViewVisibility(icTeamVoice, View.VISIBLE);
            } else {
                setViewVisibility(icTeamVoice, View.GONE);
            }

            setViewVisibility(icUserImage, View.GONE);
            setViewVisibility(icTeamImage, View.GONE);

            if (isVoiceStat) {
                setViewVisibility(tvUserNick, View.GONE);
            } else {
                setViewVisibility(tvUserNick, View.VISIBLE);
            }

            setViewVisibility(tvUserGold, View.VISIBLE);
            if (haveTeamUsr) {
                setViewVisibility(tvTeamGold, View.VISIBLE);
            } else {
                setViewVisibility(tvTeamGold, View.GONE);
            }


            if (userVideoReady) {
                setViewVisibility(ivUserCover, View.GONE);
            } else {
                setViewVisibility(ivUserCover, View.VISIBLE);
            }

            if (remoteUser == null || teamVideoReady) {
                setViewVisibility(ivTeamCover, View.GONE);
            } else {
                setViewVisibility(ivTeamCover, View.VISIBLE);
            }


            if (userVideoReady) {
                setViewVisibility(userSurface, View.VISIBLE);
            } else {
                setViewVisibility(userSurface, View.GONE);
            }


            if (teamVideoReady) {
                setViewVisibility(teamSurface, View.VISIBLE);
            } else {
                setViewVisibility(teamSurface, View.GONE);
            }

            setViewVisibility(tvNoneTeam, View.GONE);

            updateMarginLeft(tvUserNick, 6);
            updateMarginBottom(tvUserNick, 83);
            updateMarginRight(tvTeamNick, 65);
            updateMarginBottom(tvTeamNick, 83);
            updateMarginBottom(tvUserGold, 75);
            updateMarginBottom(tvTeamGold, 75);
            updateMarginBottom(toastGroup, 106);

            tvUserNick.setTextColor(0XFFFFFFFF);
            tvTeamNick.setTextColor(0XFFFFFFFF);
        } else {
            ivBackground.setBackgroundResource(R.drawable.groupclass_voicecontrol_small_bg);
            icUserVoice.setImageResource(R.drawable.groupclass_microphone_disable_ic);
            icTeamVoice.setImageResource(R.drawable.groupclass_microphone_disable_ic);

            int height = getDimension(R.dimen.voiceview_small_size);
            updateViewHeight(ivBackground, height);

            setViewVisibility(frUserVideo, View.GONE);
            setViewVisibility(frTeamVideo, View.GONE);

            setViewVisibility(userLargeBg, View.GONE);
            setViewVisibility(teamLargeBg, View.GONE);

            setViewVisibility(userSmallBg, View.VISIBLE);
            setViewVisibility(teamSmallBg, View.VISIBLE);

            if (isVoiceStat) {
                setViewVisibility(icUserVoice, View.GONE);
            } else {
                setViewVisibility(icUserVoice, View.VISIBLE);
            }

            if (isPlackBack || !haveTeamUsr) {
                setViewVisibility(icTeamVoice, View.GONE);
            } else {
                setViewVisibility(icTeamVoice, View.VISIBLE);
            }


            setViewVisibility(icUserImage, View.VISIBLE);
            if (isPlackBack || !haveTeamUsr) {
                setViewVisibility(icTeamImage, View.GONE);
            } else {
                setViewVisibility(icTeamImage, View.VISIBLE);
            }

            if (isVoiceStat) {
                setViewVisibility(tvUserNick, View.GONE);
            } else {
                setViewVisibility(tvUserNick, View.VISIBLE);
            }

            setViewVisibility(tvUserGold, View.VISIBLE);

            if (isPlackBack || !haveTeamUsr) {
                setViewVisibility(tvTeamGold, View.GONE);
            } else {
                setViewVisibility(tvTeamGold, View.VISIBLE);
            }


            setViewVisibility(ivUserCover, View.GONE);
            setViewVisibility(ivTeamCover, View.GONE);

            setViewVisibility(userSurface, View.GONE);
            setViewVisibility(teamSurface, View.GONE);

            if (isPlackBack || !haveTeamUsr) {
                setViewVisibility(tvNoneTeam, View.VISIBLE);
            } else {
                setViewVisibility(tvNoneTeam, View.VISIBLE);
            }

            if (isPlackBack) {
                tvNoneTeam.setText("仅在直播时才有队友哦");
            } else if (haveTeamUsr) {
                tvNoneTeam.setText("");
            } else {
                tvNoneTeam.setText("没有匹配到队友哦");
            }

            updateMarginLeft(tvUserNick, 32);
            updateMarginBottom(tvUserNick, 6);
            updateMarginRight(tvTeamNick, 35);
            updateMarginBottom(tvTeamNick, 6);
            updateMarginBottom(tvUserGold, 6);
            updateMarginBottom(tvTeamGold, 6);
            updateMarginBottom(toastGroup, 36);

            tvUserNick.setTextColor(0xFF1593FF);
            tvTeamNick.setTextColor(0xFF1593FF);
        }

        if (isVoiceStat) {
            setViewVisibility(userWaveView, View.VISIBLE);
        } else {
            setViewVisibility(userWaveView, View.INVISIBLE);
        }

        if (mRTCEngine != null) {
            if (spreadState) {

                if (isVoiceStat) {
                    userEffView.stopEffect();
                } else if (enableSelfAudio && userAudioReady) {
                    userEffView.startEffect();
                } else {
                    userEffView.stopEffect();
                }

                if (enableTeamAudio && teamAudioReady) {
                    teamEffView.startEffect();
                } else {
                    teamEffView.stopEffect();
                }
            } else {
                userEffView.stopEffect();
                teamEffView.stopEffect();
            }
        } else {
            userEffView.stopEffect();
            teamEffView.stopEffect();
        }

        setVideoAudioState();

        ivBackground.requestLayout();

        Log.i(TAG, "userVideoReady=" + userVideoReady
                + " userAudioReady=" + userAudioReady
                + " teamVideoReady=" + teamVideoReady
                + " teamAudioReady=" + teamAudioReady
                + " teamAllowdAudio=" + teamAllowdAudio
                + " remoteUser=" + remoteUser
                + " remoteUser=" + remoteUser
                + " userId=" + userId
                + "  teamId=" + teamId);
    }

    private void setVideoAudioState() {

        if (mRTCEngine != null) {
            if (isBackground) {
                if (!selfVideoMuted) {
                    mRTCEngine.muteLocalVideo(true);
                    selfVideoMuted = true;
                }
            } else if (spreadState) {
                if (selfVideoMuted) {
                    mRTCEngine.muteLocalVideo(false);
                    selfVideoMuted = false;
                }
            } else {
                if (!selfVideoMuted) {
                    mRTCEngine.muteLocalVideo(true);
                    selfVideoMuted = true;
                }
            }

            if (isBackground) {
                if (selfAudioMuted) {
                    mRTCEngine.muteLocalAudio(false);
                    selfAudioMuted = false;
                }
            } else if (enableSelfAudio) {
                if (selfAudioMuted) {
                    mRTCEngine.muteLocalAudio(false);
                    selfAudioMuted = false;
                }
            } else {
                if (!selfAudioMuted) {
                    mRTCEngine.muteLocalAudio(true);
                    selfAudioMuted = true;
                }
            }
        }

        if (remoteUser != null) {
            int uid = Integer.parseInt(remoteUser);
            if (isBackground) {
                if (!teamAudioMuted) {
                    mRTCEngine.muteRemoteAudio(uid, true);
                    teamAudioMuted = true;
                }
            } else if (spreadState && teamAllowdAudio && enableTeamAudio) {
                if (teamAudioMuted) {
                    mRTCEngine.muteRemoteAudio(uid, false);
                    teamAudioMuted = false;
                }
            } else {
                if (!teamAudioMuted) {
                    mRTCEngine.muteRemoteAudio(uid, true);
                    teamAudioMuted = true;
                }
            }
        }
    }

    private void updateViewHeight(View v, int height) {
        if (v == null) {
            return;
        }

        ViewGroup.LayoutParams lp = v.getLayoutParams();
        lp.height = height;
        v.setLayoutParams(lp);
    }

    private void setViewVisibility(View v, int visibility) {

        if (v != null && v.getVisibility() != visibility) {
            v.setVisibility(visibility);
        }
    }

    private void updateMarginLeft(View v, float dp) {
        if (v == null) {
            return;
        }
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        lp.leftMargin = SizeUtils.Dp2Px(mContext, dp);
    }

    private void updateMarginRight(View v, float dp) {
        if (v == null) {
            return;
        }
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        lp.rightMargin = SizeUtils.Dp2Px(mContext, dp);
    }

    private void updateMarginBottom(View v, float dp) {
        if (v == null) {
            return;
        }
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        lp.bottomMargin = SizeUtils.Dp2Px(mContext, dp);
    }

    private void onLocalErrorCode(RTCEngine.RTCEngineErrorCode code) {
        if (code == RTCEngine.RTCEngineErrorCode.RTCEngineErrorCodesAudioError.RTCEngineErrorCodeInvalidToken) {
            userAudioReady = false;
            userVideoReady = false;
        } else if (code == RTCEngine.RTCEngineErrorCode.RTCEngineErrorCodesAudioError.RTCEngineErrorCodeTokenExpired) {
            userAudioReady = false;
            userVideoReady = false;
        } else if (code == RTCEngine.RTCEngineErrorCode.RTCEngineErrorCodesAudioError.RTCEngineErrorCodePublishFailed) {
            userAudioReady = false;
            userVideoReady = false;
        } else if (code == RTCEngine.RTCEngineErrorCode.RTCEngineErrorCodeStartVideoRender) {
            userAudioReady = false;
            userVideoReady = false;
        } else if (code == RTCEngine.RTCEngineErrorCode.RTCEngineErrorCodesAudioError) {
            userAudioReady = false;
        } else if (code == RTCEngine.RTCEngineErrorCode.RTCEngineErrorCodeStartCamera) {
            userVideoReady = false;
        }

        updateElementState();
    }

    private void installRemoteId(int uid) {
        if (isPlackBack || remoteUser != null) {
            return;
        }

        remoteUser = String.valueOf(uid);
        updateElementState();
    }

    private void buildRemoteVideo(int uid) {
        if (remoteUser == null) {
            remoteUser = String.valueOf(uid);
        }

        if (mRTCEngine == null) {
            return;
        }

        if (teamSurface == null) {
            FrameLayout.LayoutParams params =
                    new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                            FrameLayout.LayoutParams.MATCH_PARENT);
            int margins = SizeUtils.Dp2Px(mContext, 6);
            params.leftMargin = margins;
            params.topMargin = margins;
            params.rightMargin = margins;
            params.bottomMargin = margins;
            teamSurface = mRTCEngine.createRendererView();
            teamSurface.setZOrderMediaOverlay(true);
            frTeamVideo.addView(teamSurface, 1, params);
            mRTCEngine.setupRemoteVideo(teamSurface, uid);
        }

        updateElementState();
    }

    private void clearRemoteVideo() {
        if (isPlackBack || remoteUser == null) {
            return;
        }

        frTeamVideo.removeView(teamSurface);
        teamVideoReady = false;
        teamAudioReady = false;
        teamSurface = null;
        updateElementState();
    }

    private void checkAppSetting() {
        try {
            String packageName = mContext.getPackageName();
            Uri packageURI = Uri.parse("package:" + packageName);
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setToastMessage(String msg) {
        this.toastLabel.setText(msg);
    }

    private void showSpecialToast(long duration) {
        showToasting = true;
        setViewVisibility(settingGroup, View.GONE);
        setViewVisibility(toastLabel, View.VISIBLE);

        if (toastRunnable == null) {
            toastRunnable = new ToastRunnable();
        } else {
            removeCallbacks(toastRunnable);
        }

        postDelayed(toastRunnable, duration);
    }

    /**
     * readygo动画
     */
    private void initReadyGoAnimation() {
        String bubbleResPath = "shangtai/readygo/images";
        String bubbleJsonPath = "shangtai/readygo/data.json";
        final LottieEffectInfo bubbleEffectInfo = new LottieEffectInfo(bubbleResPath,
                bubbleJsonPath);
        readyGoView.setVisibility(View.VISIBLE);
        readyGoView.setAnimation(bubbleEffectInfo.getJsonReader(mContext), null);
        readyGoView.useHardwareAcceleration(true);
        ImageAssetDelegate imageAssetDelegate = new ImageAssetDelegate() {
            @Override
            public Bitmap fetchBitmap(LottieImageAsset lottieImageAsset) {
                return bubbleEffectInfo.fetchBitmapFromAssets(readyGoView,
                        lottieImageAsset.getFileName(),
                        lottieImageAsset.getId(), lottieImageAsset.getWidth(),
                        lottieImageAsset.getHeight(), mContext);
            }
        };
        readyGoView.setImageAssetDelegate(imageAssetDelegate);
        readyGoView.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                showWaveEffect();
                startVoiceTest();
            }


        });

    }

    private void startReadyGoVoice() {
        if (mLiveSoundPool == null) {
            mLiveSoundPool = LiveSoundPool.createSoundPool();
        }
        StandLiveMethod.readyGo(mLiveSoundPool);
    }

    /**
     * 展开收音条
     */
    private void showWaveEffect() {
        isVoiceStat = true;
        spreadState = false;
        updateElementState();

    }

    private void stopWaveEffect() {
        isVoiceStat = false;
        spreadState = true;
        updateElementState();
    }

    private boolean isResult = false;


    private void reStartVoiceReco() {
        SpeechParamEntity param = new SpeechParamEntity();
        param.setRecogType(SpeechConfig.SPEECH_DEFAULT_EVALUATOR_OFFLINE);
        if (savedir != null) {
            File saveVideoFile = new File(FileUtils.createOrExistsDirForFile(savedir.getPath()),
                    "english1v2voice" + System.currentTimeMillis() + ".mp3");
            logger.d("saveVideoFile=" + saveVideoFile);
            if (!saveVideoFile.exists()) {
                try {
                    saveVideoFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            param.setLocalSavePath(saveVideoFile.getPath());
        }
        param.setLang(Constants.ASSESS_PARAM_LANGUAGE_EN);
        param.setVad_max_sec("90");
        param.setIsNeedRecord(1);
        param.setMultRef(false);
        param.setLearning_stage("-1");
        if (mVoiceTestEntity != null) {
            List<VoiceTestEntity.AnswerItem> testAnswerItem = mVoiceTestEntity.getTestAnswerItem();
            if (testAnswerItem != null) {
                VoiceTestEntity.AnswerItem answerItem = testAnswerItem.get(0);
                param.setStrEvaluator(answerItem.getAnswer());
            }
            mSpeechUtils.startRecog(param, new EvaluatorListener() {
                @Override
                public void onBeginOfSpeech() {

                }

                @Override
                public void onResult(ResultEntity resultEntity) {
                    int totalScore = resultEntity.getScore();
                    if (resultEntity.getStatus() == ResultEntity.SUCCESS) {
                        logger.d("onResult success totalScore=" + totalScore);
                        double speechDuration = resultEntity.getSpeechDuration();
                        //高于80分停止测评
                        if (totalScore > 60) {
                            stopVoiceTest(totalScore, 0, speechDuration);
                        } else {
                            //强制收题
                            if (mIsForceStop) {
                                stopVoiceTest(totalScore, 1, speechDuration);
                                return;
                            }
                            //低于80分重新开始测评
                            mainHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    reStartVoiceReco();
                                }
                            }, 1000);

                        }

                    } else if (resultEntity.getStatus() == ResultEntity.ERROR) {
                        logger.d("onResult ERROR");
                        if (resultEntity.getErrorNo() == ResultCode.MUTE_AUDIO || resultEntity.getErrorNo() == ResultCode.MUTE) {
                            logger.d("no voice,retry");
                            setToastMessage("没听清，请大声点哦");
                            showSpecialToast(2000);
                        }
                        mainHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reStartVoiceReco();
                            }
                        }, 1000);
                    }
                }

                @Override
                public void onVolumeUpdate(int volume) {

                }
            });
        }

    }

    private void stopVoiceTest(final int totalScore, final int isForce, final double duration) {
        BasePlayerFragment videoFragment = ProxUtil.getProxUtil().get(mContext,
                BasePlayerFragment.class);
        if (videoFragment != null) {
            videoFragment.setVolume(VP.DEFAULT_STEREO_VOLUME, VP.DEFAULT_STEREO_VOLUME);
        }
        if (mRTCEngine != null) {
            logger.d("stopVoiceTest");
            mRTCEngine.setMediaAudioProcessListener(null);
        }
        stopWaveEffect();
        if (mSpeechUtils != null) {
            mSpeechUtils.stop();
        }
        //强制手提
        if (isForce == 1) {

            mainHandler.post(new Runnable() {
                int count = 3;

                @Override
                public void run() {
                    if (count == 0) {
                        mainHandler.removeCallbacks(this);
                        if (mVoiceEndListener != null) {
                            mVoiceEndListener.onVoiceTestEndListener(totalScore, isForce, duration);
                        }
                        return;
                    }
                    setToastMessage(count + "s后自动提交");
                    showSpecialToast(1000);
                    mainHandler.postDelayed(this, 1000);
                    --count;
                }
            });

        } else {
            if (mVoiceEndListener != null) {
                mVoiceEndListener.onVoiceTestEndListener(totalScore, isForce, duration);
            }
        }


    }

    /**
     * 时间到了，强制关闭语音测评
     */
    public void closeVoiceTest() {
        mIsForceStop = true;
        if (mSpeechUtils != null) {
            mSpeechUtils.stop();
        }
        logger.d("closeVoiceTest");
    }

    /**
     * 开启语音评测
     *
     * @param entity
     */
    public void openVoiceTest(VoiceTestEntity entity) {
        mIsForceStop = false;
        this.mVoiceTestEntity = entity;
        if (entity.getTestAnswerItem() != null && !entity.getTestAnswerItem().isEmpty()) {
            voicetext.setText(entity.getTestAnswerItem().get(0).getAnswer());
        }
        //关掉主讲声音
        BasePlayerFragment videoFragment = ProxUtil.getProxUtil().get(mContext,
                BasePlayerFragment.class);
        if (videoFragment != null) {
            videoFragment.setVolume(0, 0);
        }
        updateSpread(false);
        readyGoView.playAnimation();
        startReadyGoVoice();
    }


    /**
     * 开始语音评测
     */
    private void startVoiceTest() {
        if (mRTCEngine == null) {
            return;
        }
        reStartVoiceReco();
        logger.d("setMediaAudioProcessListener");
        mRTCEngine.setMediaAudioProcessListener(new RTCEngine.IRTCMediaAudioProcess() {
            @Override
            public void didCapturedAuidoData(RTCEngine.RTCAudioData data) {
                if (data != null && data.bufferLength > 0) {
                    try {
                        mSpeechUtils.transferData(data.buffer, data.bufferLength);
                        short[] shorts = bytesToShort(data.buffer);
                        int shortLenght = data.bufferLength / 2;
                        int volume = calculateRealVolume(shorts, shortLenght);
                        Message message = Message.obtain();
                        message.arg1 = volume;
                        volumeUpdateHandler.sendMessage(message);
                        transferMp3(shorts, shortLenght);

                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
    }

    private void transferMp3(short[] buffer, int readSize) {
        if (mFileOutputStream != null) {
            int encodeSize = 0;
            encodeSize = LameUtil.encode(buffer, buffer, readSize, mMp3Buffer);
            if (encodeSize > 0) {
                System.arraycopy(mMp3Buffer, 0, mMp3Total, 0, encodeSize);
                try {
                    mFileOutputStream.write(mMp3Total, 0, encodeSize);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static short[] bytesToShort(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        short[] shorts = new short[bytes.length / 2];
        ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(shorts);
        return shorts;
    }

    /**
     * 计算录音音量
     *
     * @param buffer   buffer
     * @param readSize readSize
     */
    private int calculateRealVolume(short[] buffer, int readSize) {
        double sum = 0;
        for (int i = 0; i < readSize; i++) {
            // 这里没有做运算的优化，为了更加清晰的展示代码
            sum += buffer[i] * buffer[i];
        }
        if (readSize > 0) {
            double amplitude = sum / readSize;
            int volume = (int) Math.sqrt(amplitude);
            volume = (volume * 30 / 10000);
            volume = (volume > 30 ? 30 : volume);
            return volume;
        }

        return 0;
    }

    private VoiceTestEntity buildTestData() {
        String json = "{\"category\":\"2000\",\"begintime\":\"63\",\"id\":\"7554941980542178\"," +
                "\"type\":\"0\",\"url\":\"\",\"date\":\"1111-11-11 11-11-11\"," +
                "\"packageId\":\"126652\",\"courseWareId\":\"1006145\",\"packageAttr\":\"7\"," +
                "\"releaseInfos\":[{\"answer\":\"\\\"Don't worry, young man,\\\" answers the old " +
                "man\",\"estimatedTime\":\"10\",\"pageId\":\"148570\"}],\"interactType\":\"-1\"," +
                "\"endtime\":\"138\"}";
        JSONObject events = null;
        try {
            events = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RTCVideoAction rtcVideoAction = ProxUtil.getProxUtil().get(mContext, RTCVideoAction.class);
        VoiceTestEntity voiceTestEntity=null;
        if(isPlackBack){
            voiceTestEntity=((RTCVideoBackBll)rtcVideoAction).getEntity();
        }else {
            voiceTestEntity=((RTCVideoBll)rtcVideoAction).getEntity();
        }
        try {
            String packageAttr = events.optString("packageAttr");
            //如果不是语音评测返回
            if (!"7".equals(packageAttr)) {
                return null;
            }
            voiceTestEntity.setInteractionId(events.optString("id"));
            voiceTestEntity.setPackageId(events.optInt("packageId"));
            voiceTestEntity.setCourseWareId(events.optInt("courseWareId"));
            String pageIds = "";
            JSONArray releaseInfos = events.optJSONArray("releaseInfos");
            if (releaseInfos != null && releaseInfos.length() > 0) {
                List<VoiceTestEntity.AnswerItem> answerItems = new ArrayList<>();
                VoiceTestEntity.AnswerItem answerItem =
                        new VoiceTestEntity.AnswerItem();
                answerItems.add(answerItem);
                JSONObject jsonObject = releaseInfos.getJSONObject(0);
                answerItem.setAnswer(jsonObject.optString("answer"));
                answerItem.setPageId(jsonObject.optInt("pageId", 1));
                pageIds += answerItem.getPageId();
                voiceTestEntity.setTestAnswerItem(answerItems);
            }
            voiceTestEntity.setPageIds(pageIds);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return voiceTestEntity;

    }

}
