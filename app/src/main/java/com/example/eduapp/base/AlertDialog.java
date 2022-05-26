package com.example.eduapp.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.eduapp.R;

public class AlertDialog extends Dialog {

  public AlertDialog(Context context) {
    super(context, R.style.ios_dialog_style);
  }

  public static class Builder {
    private Context mContext;
    private AlertDialog mAlertDialog;
    private CharSequence mTitle;
    private CharSequence mMessage;
    private CharSequence mPositiveButtonText;
    private CharSequence mNegativeButtonText;
    private View mContentView;
    private OnClickListener mPositiveButtonClickListener;
    private OnClickListener mNegativeButtonClickListener;
    private boolean mCancelable = true;
    private OnDismissListener onDismissListener;

    public Builder(Context context) {
      mContext = context;
    }

    public Builder setTitle(int titleId) {
      this.mTitle = mContext.getText(titleId);
      return this;
    }

    public Builder setOnDismissListener(OnDismissListener onDismissListener) {
      this.onDismissListener = onDismissListener;
      return this;
    }

    public Builder setTitle(CharSequence title) {
      this.mTitle = title;
      return this;
    }

    public Builder setMessage(int messageId) {
      this.mMessage = mContext.getText(messageId);
      return this;
    }

    public Builder setMessage(CharSequence message) {
      this.mMessage = message;
      return this;
    }

    public Builder setPositiveButton(int textId, OnClickListener listener) {
      this.mPositiveButtonText = mContext.getText(textId);
      this.mPositiveButtonClickListener = listener;
      return this;
    }

    public Builder setPositiveButton(CharSequence text, OnClickListener listener) {
      this.mPositiveButtonText = text;
      this.mPositiveButtonClickListener = listener;
      return this;
    }

    public Builder setNegativeButton(int textId, OnClickListener listener) {
      this.mNegativeButtonText = mContext.getText(textId);
      this.mNegativeButtonClickListener = listener;
      return this;
    }

    public Builder setNegativeButton(CharSequence text, OnClickListener listener) {
      this.mNegativeButtonText = text;
      this.mNegativeButtonClickListener = listener;
      return this;
    }

    public Builder setCancelable(boolean cancelable) {
      this.mCancelable = cancelable;
      return this;
    }

    public Builder setContentView(View contentView) {
      this.mContentView = contentView;
      return this;
    }

    public AlertDialog create() {
      LayoutInflater inflater = LayoutInflater.from(mContext);
      View dialogView = inflater.inflate(R.layout.alert_dialog, null);
      mAlertDialog = new AlertDialog(mContext);
      mAlertDialog.setCancelable(mCancelable);
      if (onDismissListener != null) {
        mAlertDialog.setOnDismissListener(onDismissListener);
      }
      TextView tvTitle = (TextView) dialogView.findViewById(R.id.title);
      TextView tvMessage = (TextView) dialogView.findViewById(R.id.message);
      Button btnCancel = (Button) dialogView.findViewById(R.id.cancel_btn);
      Button btnConfirm = (Button) dialogView.findViewById(R.id.confirm_btn);
      View horizontal_line = dialogView.findViewById(R.id.horizontal_line);
      View vertical_line = dialogView.findViewById(R.id.vertical_line);
      View btns_panel = dialogView.findViewById(R.id.btns_panel);

      // set title
      // fix #1,if title is null,set title visibility GONE
      if (TextUtils.isEmpty(mTitle)) {
        tvTitle.setVisibility(View.GONE);
      } else {
        tvTitle.setText(mTitle);
      }
      // set content view
      if (mContentView != null) {
        // if no message set add the contentView to the dialog body
        LinearLayout rl = (LinearLayout) dialogView.findViewById(R.id.message_layout);
        rl.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rl.addView(mContentView, params);
      } else {
        tvMessage.setText(mMessage);
      }
      // set buttons
      if (mPositiveButtonText == null && mNegativeButtonText == null) {
        setPositiveButton("OK", null);
        btnConfirm.setBackgroundResource(R.drawable.iosdialog_single_btn_selector);
        btnCancel.setVisibility(View.GONE);
        vertical_line.setVisibility(View.GONE);
      } else if (mPositiveButtonText != null && mNegativeButtonText == null) {
        btnConfirm.setBackgroundResource(R.drawable.iosdialog_single_btn_selector);
        btnCancel.setVisibility(View.GONE);
        vertical_line.setVisibility(View.GONE);
      } else if (mPositiveButtonText == null && mNegativeButtonText != null) {
        btnConfirm.setVisibility(View.GONE);
        btnCancel.setBackgroundResource(R.drawable.iosdialog_single_btn_selector);
        vertical_line.setVisibility(View.GONE);
      }
      if (mPositiveButtonText != null) {
        btnConfirm.setText(mPositiveButtonText);
        btnConfirm.setOnClickListener(v -> {
          if (mPositiveButtonClickListener != null) {
            mPositiveButtonClickListener.onClick(mAlertDialog, DialogInterface.BUTTON_POSITIVE);
          }
          mAlertDialog.dismiss();
        });
      }
      if (mNegativeButtonText != null) {
        btnCancel.setText(mNegativeButtonText);
        btnCancel.setTextColor(Color.parseColor("#FFFD4A2E"));

        btnCancel.setOnClickListener(v -> {
          if (mNegativeButtonClickListener != null) {
            mNegativeButtonClickListener.onClick(mAlertDialog, DialogInterface.BUTTON_NEGATIVE);
          }
          mAlertDialog.dismiss();
        });
      }

      dialogView.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      int dialogHeight = dialogView.getMeasuredHeight();
      WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
      DisplayMetrics metrics = new DisplayMetrics();
      wm.getDefaultDisplay().getMetrics(metrics);
      int maxHeight = (int) (metrics.heightPixels * 0.8);
      ViewGroup.LayoutParams dialogParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      if (dialogHeight >= maxHeight) {
        dialogParams.height = maxHeight;
      }
      mAlertDialog.setContentView(dialogView, dialogParams);

      return mAlertDialog;
    }

    public AlertDialog show() {
      mAlertDialog = create();
      mAlertDialog.show();
      return mAlertDialog;
    }
  }
}
