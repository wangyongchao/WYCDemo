
package com.weishop.test.designmode.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.weishop.test.R;

public class DialogActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        findViewById(R.id.btn).setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this).
                        setTitle("标题").setMessage("内容")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
