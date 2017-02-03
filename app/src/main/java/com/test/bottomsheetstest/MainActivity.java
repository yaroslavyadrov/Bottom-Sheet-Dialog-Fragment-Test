package com.test.bottomsheetstest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.showDialogButton) Button showDialogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }
    private void showDialog() {
        new BottomDialog.Builder(this, getSupportFragmentManager())
                .cancellable(true)
                .title("sdfsdfsdfs")
                .positiveText("YES")
                .negativeText("NO")
                .positiveClick(new BottomDialog.ButtonClickListener() {
                    @Override
                    public void click(BottomDialog dialog) {
                        Toast.makeText(MainActivity.this, "positive", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .negativeClick(new BottomDialog.ButtonClickListener() {
                    @Override
                    public void click(BottomDialog dialog) {
                        Toast.makeText(MainActivity.this, "negative", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
