package com.test.bottomsheetstest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomDialog extends BottomSheetDialogFragment {
    public static final String TAG = "BottomDialog";

    private String title;
    private int image;
    private String positiveText;
    private String negativeText;
    private ButtonClickListener positiveClickListener;
    private ButtonClickListener negativeClickListener;

    @BindView(R.id.titleTextView) TextView titleTextView;
    @BindView(R.id.imageView) ImageView imageView;
    @BindView(R.id.positiveButton) TextView positiveButton;
    @BindView(R.id.negativeButton) TextView negativeButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_dialog, container, false);
        ButterKnife.bind(this, view);
        titleTextView.setText(title);
        if (image != 0) {
            imageView.setImageDrawable(getResources().getDrawable(image));
        }
        positiveButton.setText(positiveText);
        if (!TextUtils.isEmpty(negativeText)) {
            negativeButton.setText(negativeText);
        } else {
            negativeButton.setVisibility(View.GONE);
        }
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positiveClickListener != null) {
                    positiveClickListener.click(BottomDialog.this);
                }
            }
        });
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (negativeClickListener != null) {
                    negativeClickListener.click(BottomDialog.this);
                }
            }
        });
        return view;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setPositiveText(String positiveText) {
        this.positiveText = positiveText;
    }

    public void setNegativeText(String negativeText) {
        this.negativeText = negativeText;
    }

    public void setPositiveClickListener(ButtonClickListener positiveClickListener) {
        this.positiveClickListener = positiveClickListener;
    }

    public void setNegativeClickListener(ButtonClickListener negativeClickListener) {
        this.negativeClickListener = negativeClickListener;
    }

    public static class Builder {
        private final BottomDialog bottomDialog;
        private final Context context;
        private final FragmentManager fragmentManager;

        public Builder(Context context, FragmentManager fm) {
            bottomDialog = new BottomDialog();
            this.fragmentManager = fm;
            this.context = context;
        }

        public Builder cancellable(boolean cancellable) {
            bottomDialog.setCancelable(cancellable);
            return this;
        }

        public Builder title(String title) {
            bottomDialog.setTitle(title);
            return this;
        }

        public Builder title(@StringRes int title) {
            bottomDialog.setTitle(context.getResources().getString(title));
            return this;
        }

        public Builder positiveText(String positiveText) {
            bottomDialog.setPositiveText(positiveText);
            return this;
        }

        public Builder positiveText(@StringRes int positiveText) {
            bottomDialog.setPositiveText(context.getResources().getString(positiveText));
            return this;
        }

        public Builder negativeText(String negativeText) {
            bottomDialog.setNegativeText(negativeText);
            return this;
        }

        public Builder negativeText(@StringRes int negativeText) {
            bottomDialog.setNegativeText(context.getResources().getString(negativeText));
            return this;
        }

        public Builder image(@DrawableRes int image) {
            bottomDialog.setImage(image);
            return this;
        }

        public Builder positiveClick(ButtonClickListener clickListener) {
            bottomDialog.setPositiveClickListener(clickListener);
            return this;
        }

        public Builder negativeClick(ButtonClickListener clickListener) {
            bottomDialog.setNegativeClickListener(clickListener);
            return this;
        }

        public BottomDialog build() {
            return bottomDialog;
        }

        public BottomDialog show() {
            bottomDialog.show(fragmentManager, TAG);
            return bottomDialog;
        }

    }

    public interface ButtonClickListener {
        void click(BottomDialog bottomDialog);
    }
}
