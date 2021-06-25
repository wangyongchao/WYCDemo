package com.weishop.test.dialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;

import com.weishop.test.R;

public class BottomSheetActivity extends AppCompatActivity implements View.OnClickListener {
    private BottomSheetDialog cloudNoDataDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.pop).setOnClickListener(this);


    }


    /**
     * 显示云端无数据对话框
     */
    public void showCloudNoDataDialog() {

        if (cloudNoDataDialog == null) {
            cloudNoDataDialog = new BottomSheetDialog(this,R.style.BottomDialogStyle);
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_delete_sync_remote_data, null);
            view.findViewById(R.id.loading).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProgressDialog progressDialog = ProgressDialog.show(BottomSheetActivity.this, "标题", "message", true, true);
                    progressDialog.getWindow().setDimAmount(0.1f);
                }
            });
            cloudNoDataDialog.setContentView(view);
            cloudNoDataDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {

                }
            });
            cloudNoDataDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                }
            });
            //禁止向下滑动消失
            ViewParent parent = view.getParent();
            if (parent != null && parent instanceof View) {
                BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from((View) parent);
                BottomSheetBehavior.BottomSheetCallback callback = new BottomSheetBehavior.BottomSheetCallback() {

                    @Override
                    public void onStateChanged(@NonNull View view, int i) {
                        if (i == BottomSheetBehavior.STATE_DRAGGING) {
                            //判断为向下拖动行为时，则强制设定状态为展开
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        }
                    }

                    @Override
                    public void onSlide(@NonNull View view, float v) {

                    }
                };
                bottomSheetBehavior.setBottomSheetCallback(callback);
            }

        }
        if (!cloudNoDataDialog.isShowing()) {
            cloudNoDataDialog.show();
        }

    }

    @Override
    public void onClick(View v) {
        showCloudNoDataDialog();
    }
}