package com.weishop.test.webview;

/**
 * 战队pk  音效信息
 */
public class SoundInfo {

    private int mSoundId;
    private int mStreamId;
    private float mSoundVolume;//当前音量

    public float getSoundVolume() {
        return mSoundVolume;
    }

    public void setSoundVolume(float soundVolume) {
        this.mSoundVolume = soundVolume;
    }

    public SoundInfo(int soundId, int streamId) {
        this.mSoundId = soundId;
        this.mStreamId = streamId;
    }

    public int getStreamId() {
        return mStreamId;
    }

    public void setStreamId(int streamId) {
        this.mStreamId = streamId;
    }

    public int getSoundId() {
        return mSoundId;
    }

    public void setSoundId(int soundId) {
        this.mSoundId = soundId;
    }
}
