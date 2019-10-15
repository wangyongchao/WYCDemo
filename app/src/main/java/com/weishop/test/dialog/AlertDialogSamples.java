/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.weishop.test.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.weishop.test.R;


/**
 * Example of how to use an {@link AlertDialog}.
 * <h3>AlertDialogSamples</h3>
 *
 * <p>This demonstrates the different ways the AlertDialog can be used.</p>
 *
 * <h4>Demo</h4>
 * App/Dialog/Alert Dialog
 *
 * <h4>Source files</h4>
 * <table class="LinkTable">
 * <tr>
 * <td >src/com.example.android.apis/app/AlertDialogSamples.java</td>
 * <td >The Alert Dialog Samples implementation</td>
 * </tr>
 * <tr>
 * <td >/res/any/layout/alert_dialog.xml</td>
 * <td >Defines contents of the screen</td>
 * </tr>
 * </table>
 */
public class AlertDialogSamples extends Activity {
    private static final String TAG = "AlertDialogSamples";
    private static final int DIALOG_YES_NO_MESSAGE = 1;

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_YES_NO_MESSAGE:
                AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialogSamples.this);
                builder.setTitle(R.string.block_canary_delete_all_dialog_content)
                        .setIcon(R.drawable.block_canary_icon)
                        .setPositiveButton(R.string.alert_dialog_ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {

                                        /* User clicked OK so do some stuff */
                                    }
                                })
                        .setNegativeButton(R.string.alert_dialog_cancel,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {

                                        /* User clicked Cancel so do some stuff */
                                    }
                                });

                builder.show();

        }
        return null;
    }

    /**
     * Initialization of the Activity after it is first created.  Must at least
     * call {@link Activity#setContentView(int)} to
     * describe what is to be displayed in the screen.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.alert_dialog);

        /* Display a text message with yes/no buttons and handle each message as well as the
        cancel action */
        Button twoButtonsTitle = (Button) findViewById(R.id.two_buttons);
        twoButtonsTitle.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_YES_NO_MESSAGE);
            }
        });
    }

    @Override
    public void onEnterAnimationComplete() {
        Log.i(TAG, "onEnterAnimationComplete");
    }
}
