package com.xueersi.parentsmeeting.speakerrecognition;

import android.os.Environment;

public class SpeakerRecognitionerInterface {
    static {
        System.loadLibrary("SpeakerRecognitioner");
    }

    private static SpeakerRecognitionerInterface instance;
    private static final String SOURCE_URL = "https://wxapp.xesimg.com/Android/voicerecog/voice_recog.zip";
    private static final String SO_NAME = "libSpeakerRecognitioner.so";
    private static final String SOURCE_MD5 = "8ed601e818100d6f8ee84d80a7f645ef";
    private static final String GMM_FILE_NAME = "final.ubm";
    private static final String IVECTOR_EXTRACTOR_NAME = "final.ie";
    private static final String PLDA_FILE_NAME = "plda";
    private static final String SOURCE_FILE_NAME = "voice_recog.zip";

    String localpath = Environment.getExternalStorageDirectory() + "/parentsmeeting/voice_recog/";
    final String gmmPath = localpath + "final.ubm";
    final String ivectorPath = localpath + "final.ie";
    final String pldaPath = localpath + "plda";


    private String path = localpath;
//    private String path = Environment.getExternalStorageDirectory() + "/parentsmeeting/voicerecognize/model/";

    private boolean isInitializing;

    public SpeakerRecognitionerInterface() {
        ptr_ = SpeakerRecognitionerGen();
    }

    private long ptr_;

    private native long SpeakerRecognitionerGen();

    private native void SpeakerRecognitionerFree(long ptr);

    private native int SpeakerRecognitionerInit(long ptr, String fgmm_file, String ivector_extractor_file,
                                                String plda_file);

    private native int SpeakerRecognitionerEnrollIvector(long ptr, byte[] pcm_data, int pcm_data_size, int pcm_data_id,
                                                         String correct_spk, boolean is_first, String saved_path);

    private native String SpeakerRecognitionerPredict(long ptr, byte[] pcm_data, int pcm_data_size, int pcm_data_id,
                                                      String correct_spk, boolean is_final);


    public void speakerRecognitionerFree() {
        SpeakerRecognitionerFree(ptr_);
    }


    public int enrollIvector(byte[] pcm_data, int pcm_data_size, int pcm_data_id, String correct_spk, boolean
            is_first) {
        return SpeakerRecognitionerEnrollIvector(ptr_, pcm_data, pcm_data_size, pcm_data_id, correct_spk, is_first,
                path);
    }

    public String predict(byte[] pcm_data, int pcm_data_size, int pcm_data_id, String correct_spk, boolean is_final) {
        return SpeakerRecognitionerPredict(ptr_, pcm_data, pcm_data_size, pcm_data_id, correct_spk, is_final);
    }

    public boolean isInit() {
        return isInitializing;
    }

    public int init() {
        int init = -12;
        if (!isInitializing) {
            init = SpeakerRecognitionerInit(ptr_, gmmPath, ivectorPath, pldaPath);
            if (init == 0) {
                isInitializing = true;
            }

        }
        return init;
    }

}