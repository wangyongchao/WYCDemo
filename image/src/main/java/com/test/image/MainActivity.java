package com.test.image;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xueersi.parentsmeeting.speakerrecognition.SpeakerRecognitionerInterface;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SpeakerRecognitionerInterface speakerRecognitionerInterface = new SpeakerRecognitionerInterface();
        int init = speakerRecognitionerInterface.init();
        String stuId = "12345";
        for (int i = 0; i < 50; i++) {
            byte[] bytes = new byte[12000];
            int enrollIvector = speakerRecognitionerInterface.enrollIvector(bytes, bytes.length,
                    i + 1, stuId, true);
            System.out.println("regist enrollIvector=" + enrollIvector);
        }
        byte[] bytes = new byte[12000];
        int enrollIvector = speakerRecognitionerInterface.enrollIvector(bytes, bytes.length,
                -12, stuId, true);
        System.out.println("regist success=" + enrollIvector);
    }
}
