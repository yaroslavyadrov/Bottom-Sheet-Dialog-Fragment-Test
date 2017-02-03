package com.test.bottomsheetstest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.showDialogButton) Button showDialogButton;
    @BindView(R.id.titleEditText) EditText titleEditText;
    @BindView(R.id.positiveEditText) EditText positiveEditText;
    @BindView(R.id.negativeEditText) EditText negativeEditText;
    @BindView(R.id.cancellableCheckBox) AppCompatCheckBox cancellableCheckBox;
    @BindView(R.id.imageCheckBox) AppCompatCheckBox imageCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(positiveEditText.getText()) &&
                        !TextUtils.isEmpty(titleEditText.getText())) {
                    showDialog();
                } else {
                    Toast.makeText(MainActivity.this, "error: enter text", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showDialog() {
        new BottomDialog.Builder(this, getSupportFragmentManager())
                .cancellable(cancellableCheckBox.isChecked())
                .title(titleEditText.getText().toString())
                .positiveText(positiveEditText.getText().toString())
                .negativeText(negativeEditText.getText().toString())
                .positiveClick(new BottomDialog.ButtonClickListener() {
                    @Override
                    public void click(BottomDialog dialog) {
                        Toast.makeText(MainActivity.this, "positive button clicked", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .negativeClick(new BottomDialog.ButtonClickListener() {
                    @Override
                    public void click(BottomDialog dialog) {
                        Toast.makeText(MainActivity.this, "negative button clicked", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .image(imageCheckBox.isChecked() ? R.drawable.image : 0)
                .show();
    }
}
