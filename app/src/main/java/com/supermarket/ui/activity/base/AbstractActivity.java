package com.supermarket.ui.activity.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.finance.common.dialog.TTAlertDialog;
import com.finance.common.dialog.TTProgressDialog;
import com.supermarket.R;
import com.supermarket.utils.SystemBarTintManager;
import com.supermarket.utils.ToastUtils;

import butterknife.ButterKnife;

public abstract class AbstractActivity extends FragmentActivity implements
        View.OnClickListener{
    public static final String ACTION_SHOW_PLAYER = "com.audio.tingting.ui.ShowPlayer";
    public final static int EXIT_OK = 0x801;
    protected static TTAlertDialog mAlertDialog = null;

    protected int DEFAULTCOLOR = R.color.color_1fa7cb;
    protected int TRANSPARENTCOLOR = R.color.transparent;
    protected int WHITECOLOR = R.color.white;
    protected int SELECTCOLOR = DEFAULTCOLOR;

    protected TTAlertDialog mErrorDialog = null;
    protected TTProgressDialog mProgressDialog = null;
    protected Handler basicHandler = new BasicHandle();
    protected boolean isTransparent = false;//状态栏颜色是否透明
    protected SystemBarTintManager tintManager;
    private boolean eventControl = true;

    /**
     * @param mact          Activity
     * @param MessageTest   提示信息
     * @param basicHandler2
     */
    public static void showExitDialog(Context mact, int MessageTest, final Handler basicHandler2) {
        mAlertDialog = new TTAlertDialog.Builder(mact)
                .setTitle(MessageTest)
//                .setMessage(MessageTest)
                .setPositiveButton(mact.getResources().getString(R.string.cancel), new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton(mact.getResources().getString(R.string.ok), new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        basicHandler2.sendEmptyMessage(EXIT_OK);
                    }
                }).create();
        mAlertDialog.show();
    }

    /**
     * 初始化一些View操作
     *
     * @return
     */
    public abstract View initContentView();

    /**
     * 逻辑操作，如：请求数据，加载界面...
     */
    public abstract void handleCreate();

    protected abstract void customOnClick(View v);

    /**
     * Handler消息处理
     *
     * @param msg
     */
    protected void processMessage(Message msg) {

    }

    boolean onCreateTTActivity(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!onCreateTTActivity(savedInstanceState)) {
            View view = initContentView();
            if (view != null) {
                setContentView(view);
            }
            ButterKnife.bind(this);
            handleCreate();
        }

        ActivityStack.getInstance().pushActivity(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(!isTransparent ? SELECTCOLOR : TRANSPARENTCOLOR);
        }
    }

    /**
     * 设置头部背景颜色
     *
     * @param color 选择的颜色
     */
    public void setSelectColor(int color) {
        this.SELECTCOLOR = color;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStack.getInstance().popActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityStack.getInstance().addFrontPage();
        ActivityStack.getInstance().pushToStack(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ActivityStack.getInstance().decrementFrontPage();
        ActivityStack.getInstance().popForStack(this);
    }

    @Override
    public void onClick(View v) {
        customOnClick(v);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public void sendMessage(int what) {
        sendMessage(what, 0, 0, null);
    }

    public void sendMessage(int what, Object data) {
        sendMessage(what, 0, 0, data);
    }

    /**
     * {@link Handler#obtainMessage(int, int, int)}
     *
     * @param what
     * @param arg1
     * @param arg2
     * @param data
     */
    public void sendMessage(int what, int arg1, int arg2, Object data) {
        if (basicHandler != null) {
            Message message = basicHandler.obtainMessage(what, arg1, arg2);
            message.obj = data;
            basicHandler.sendMessage(message);
        }
    }

    public void showProgressDlg() {
        showProgressDlg(R.string.progressdlgtitle, R.string.nowisloading);
    }

    public final void showProgressDlg(int msg) {
        showProgressDlg(R.string.progressdlgtitle, msg);
    }

    public final void showProgressDlg(final String msg) {
        showProgressDlg(getString(R.string.progressdlgtitle), msg);
    }

    public final void showProgressDlg(int title, int msg) {
        showProgressDlg(getString(title), getString(msg));
    }

    public final void showProgressDlg(final String title, final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (AbstractActivity.this.isFinishing()
                        || (mProgressDialog != null && mProgressDialog.isShowing())) {
                    return;
                }
                if (mProgressDialog != null) {
//					mProgress.setTitle(title);
                    mProgressDialog.setMessage(msg);
                    mProgressDialog.show();
                } else {
                    mProgressDialog = TTProgressDialog.show(AbstractActivity.this,
                            null, msg, false, true);
                }
            }
        });
    }

    public void dismissDlg() {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (AbstractActivity.this.isFinishing()) {
                    return;
                }

                if (mProgressDialog != null && mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }
        });

    }

    public final void showToast(int res) {
        showToast(getString(res));
    }

    public final void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showToast(AbstractActivity.this, msg);
            }
        });
    }

    public final void showErrorDialog(int errorId) {
        showErrorDialog(getString(errorId));
    }

    /**
     * 提示框
     */
    public final void showErrorDialog(final String errorInfo) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (AbstractActivity.this.isFinishing()
                        || (mErrorDialog != null && mErrorDialog.isShowing())) {
                    return;
                }
                if (mErrorDialog != null) {
                    mErrorDialog
                            .setMessage(errorInfo == null
                                    || "".equals(errorInfo) ? getString(R.string.parse_json_err)
                                    : errorInfo);
                    mErrorDialog.show();
                } else {
                    mErrorDialog = new TTAlertDialog.Builder(
                            AbstractActivity.this)
                            .setTitle(R.string.prompt)
                            .setMessage(
                                    errorInfo == null || "".equals(errorInfo) ? getString(R.string.parse_json_err)
                                            : errorInfo)
                            .setNeutralButton(R.string.ok,
                                    new OnClickListener() {
                                        @Override
                                        public void onClick(
                                                DialogInterface dialog,
                                                int which) {
                                        }
                                    }).show();
                }
            }
        });
    }

    public final void dissErrorDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!AbstractActivity.this.isFinishing()
                        && (mErrorDialog != null && mErrorDialog.isShowing())) {
                    mErrorDialog.dismiss();
                }
            }
        });
    }

    /**
     * 提示框
     */
    public final void showErrorDialog(final String positiveButtonMsg, final String errorInfo) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (AbstractActivity.this.isFinishing()
                        || (mErrorDialog != null && mErrorDialog.isShowing())) {
                    return;
                }
                if (mErrorDialog != null) {
                    mErrorDialog
                            .setMessage(errorInfo == null
                                    || "".equals(errorInfo) ? getString(R.string.parse_json_err)
                                    : errorInfo);
                    mErrorDialog.show();
                } else {
                    mErrorDialog = new TTAlertDialog.Builder(
                            AbstractActivity.this)
                            .setTitle(R.string.prompt)
                            .setMessage(
                                    errorInfo == null || "".equals(errorInfo) ? getString(R.string.parse_json_err)
                                            : errorInfo)
                            .setNeutralButton(positiveButtonMsg,
                                    new OnClickListener() {
                                        @Override
                                        public void onClick(
                                                DialogInterface dialog,
                                                int which) {
                                        }
                                    }).show();
                }
            }
        });
    }

    public void setEventControl(boolean bool) {
        eventControl = bool;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN && eventControl) {
            View v = getCurrentFocus();

            if (isShouldHideInput(v, ev)) {
                hideSoftInput(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void hideSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }


    protected void releaseImageView(ImageView imageView) {
        if (imageView == null)
            return;

        Drawable d = imageView.getDrawable();
        if (d != null)
            d.setCallback(null);
        imageView.setImageDrawable(null);
        imageView.setBackgroundDrawable(null);
    }



    class BasicHandle extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            boolean isDispatch = false;
            switch (msg.what) {
                default:
                    break;
            }

            if (!isDispatch) {
                processMessage(msg);
            }
        }
    }


    protected View getContentView(int layoutId) {
        return LayoutInflater.from(this).inflate(layoutId, null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //需重写此方法，让Fragment 中的onActivityResult也能执行，同时对应Activity不可设置为 android:launchMode="singleTask"
        super.onActivityResult(requestCode, resultCode, data);
    }
}
