package com.weishop.test.webview;


import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * soundPool 包装类
 *
 * @author chekun
 * created  at 2018/5/14 11:09
 */
public class SoundPoolHelper {

    private String TAG = "SoundPoolHelper";

    private List<SoundPlayTask> playTasks;
    /**
     * 当前是否正在加载音效资源
     */
    private boolean isResOnLoading = false;
    private int mMaxStreamNum;
    private int mStreamTYpe;
    private SoundPool mSoundPool;
    private HashMap<Object, SoundInfo> mSoundInfoMap;
    private Context mContext;

    private static class SoundPlayTask {

        /**
         * raw中的音效资源
         */
        public static final int RESTYPE_RAW = 1;
        /**
         * 非 raw 中的音效资源
         */
        public static final int RESTTYPE_NORMAL = 2;
        /**
         * 音效资源类型
         */
        int resType;
        /**
         * raw 中音效资源id
         */
        int resId;
        /**
         * 音效 存储路径
         */
        String path;
        /**
         * 音量
         */
        float volume;
        /**
         * 是否循环播放
         */
        boolean loop;

        public SoundPlayTask(int resType, int resId, String path, float volume, boolean loop) {
            this.resType = resType;
            this.resId = resId;
            this.path = path;
            this.volume = volume;
            this.loop = loop;
        }

        public Object getCacheKey() {
            return resType == RESTYPE_RAW ? resId : path;
        }


    }

    /**
     * @param context
     * @param maxStreamNum 最多 几路音频
     * @param streamType   音频类型
     */
    public SoundPoolHelper(Context context, int maxStreamNum, int streamType) {
        mMaxStreamNum = maxStreamNum;
        mStreamTYpe = streamType;
        mContext = context;
    }


    /**
     * 播放 raw 中的音效资源
     *
     * @param resId
     * @param volume
     * @param loop
     */
    public void playMusic(int resId, float volume, boolean loop) {
        if (playTasks == null) {
            playTasks = new ArrayList<SoundPlayTask>();
        }
        SoundPlayTask playTask = new SoundPlayTask(SoundPlayTask.RESTYPE_RAW, resId, null, volume
                , loop);
        playTasks.add(playTask);
        if (!isResOnLoading) {
            playMusic(playTasks.remove(0));
        }
    }


    /**
     * @param path   音效路径
     * @param volume 音量
     * @param loop   是否循环播放
     */
    public void playMusic(String path, float volume, boolean loop) {
        if (playTasks == null) {
            playTasks = new ArrayList<SoundPlayTask>();
        }
        SoundPlayTask playTask = new SoundPlayTask(SoundPlayTask.RESTTYPE_NORMAL, -1, path, volume,
                loop);
        playTasks.add(playTask);
        if (!isResOnLoading) {
            playMusic(playTasks.remove(0));
        }
    }

    /**
     * 停止播放音乐
     *
     * @param resId 音效资源id
     */
    public void stopMusic(int resId) {
        doStopMusic(resId);
    }

    /**
     * 停止播放音乐
     *
     * @param path 音效路径
     */
    public void stopMusic(String path) {
        doStopMusic(path);
    }


    /**
     * 暂停播放
     *
     * @param resId 资源id
     */
    public void pauseMusic(int resId) {
        doPauseMusic(resId);
    }

    /**
     * 暂停播放   音效路径
     *
     * @param path
     */
    public void pauseMusic(String path) {
        doPauseMusic(path);
    }


    /**
     * 设置音量大小
     *
     * @param resId  音效资源id
     * @param volume 音量大小  0 - 1 ;
     */
    public void setVolume(int resId, float volume) {
        doSetVolume(resId, volume);
    }


    /**
     * @param path   音效路径
     * @param volume 音量大小
     */
    public void setVolume(String path, float volume) {
        doSetVolume(path, volume);
    }

    private void doSetVolume(Object key, float volume) {
        if (mSoundInfoMap != null && mSoundPool != null) {
            SoundInfo soundInfo = mSoundInfoMap.get(key);
            Log.e(TAG, "=====>setVolume:" + key);
            if (soundInfo != null) {
                mSoundPool.setVolume(soundInfo.getStreamId(), volume, volume);
                Log.e(TAG, "======>setVolume:" + soundInfo.getStreamId());
            }
        }
    }


    private void doPauseMusic(Object key) {
        if (mSoundInfoMap != null && mSoundPool != null) {
            SoundInfo soundInfo = mSoundInfoMap.get(key);
            Log.e(TAG, "=====>pauseMusic:" + key);
            if (soundInfo != null) {
                mSoundPool.pause(soundInfo.getStreamId());
                Log.e(TAG, "======>pauseMusic:" + soundInfo.getStreamId());
            }
        }
    }


    private void doStopMusic(Object key) {
        if (mSoundInfoMap != null && mSoundPool != null) {
            SoundInfo soundInfo = mSoundInfoMap.get(key);
            Log.e(TAG, "=====>stopMusic:" + key);
            if (soundInfo != null) {
                mSoundPool.stop(soundInfo.getStreamId());
                Log.e(TAG, "======>stopMusic:" + soundInfo.getStreamId());
            }
        }
    }


    private void playMusic(final SoundPlayTask task) {
        try {
            initSoundPoolInNeed();
            if (mSoundInfoMap == null) {
                mSoundInfoMap = new HashMap<Object, SoundInfo>();
            }
            isResOnLoading = true;
            SoundInfo soundInfo = mSoundInfoMap.get(task.getCacheKey());
            if (soundInfo != null) {
                int streamId = mSoundPool.play(soundInfo.getSoundId(), task.volume, task.volume,
                        0, task.loop ? -1 : 0, 1);
                soundInfo.setStreamId(streamId);
                mSoundInfoMap.put(task.getCacheKey(), soundInfo);
                isResOnLoading = false;
                if (playTasks.size() > 0) {
                    playMusic(playTasks.remove(0));
                }
            } else {
                if (task.resType == SoundPlayTask.RESTYPE_RAW) {
                    if (mContext != null) {
                        mSoundPool.load(mContext, task.resId, 1);
                    }
                } else {
                    mSoundPool.load(task.path, 1);
                }
                mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                        int streamId = soundPool.play(sampleId, task.volume, task.volume, 0,
                                task.loop ? -1 : 0, 1);
                        SoundInfo soundInfo = new SoundInfo(sampleId, streamId);
                        mSoundInfoMap.put(task.getCacheKey(), soundInfo);
                        isResOnLoading = false;
                        if (playTasks.size() > 0) {
                            playMusic(playTasks.remove(0));
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initSoundPoolInNeed() {
        if (mSoundPool == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                SoundPool.Builder builder = new SoundPool.Builder();
                builder.setMaxStreams(mMaxStreamNum);
                AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
                attrBuilder.setLegacyStreamType(mStreamTYpe);
                builder.setAudioAttributes(attrBuilder.build());
                mSoundPool = builder.build();
            } else {
                mSoundPool = new SoundPool(mMaxStreamNum, mStreamTYpe, 0);
            }
        }
    }


    /**
     * 释放资源
     */
    public void release() {
        if (mSoundInfoMap != null) {
            for (Object o : mSoundInfoMap.keySet()) {
                mSoundPool.stop(mSoundInfoMap.get(o).getStreamId());
            }
            mSoundInfoMap.clear();
        }
        if (mSoundPool != null) {
            mSoundPool.release();
        }
        if (mSoundInfoMap != null) {
            mSoundInfoMap.clear();
        }
        mContext = null;

    }
}
