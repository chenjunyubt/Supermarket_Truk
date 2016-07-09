package com.supermarket.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.supermarket.R;

/**
 * 弹出Toast的一个工具类，这里主要是增加了对系统Toast背景的修改
 *
 * @author Administrator
 */
public class ToastUtils {
    private static Handler handler = new Handler(){};
    private static Toast toast;
    private static LinearLayout layout;
    private static TextView tv;

    /**
     * @param context 上下文对象
     * @param msg     要显示的信息
     */
    public static void showToast(Context context, String msg) {
        int time = Toast.LENGTH_SHORT;
        toast = null;//解决mete8 上面不Toast不显示问题 @liqiang
        if (null == toast) {//防止重复显示
            toast = Toast.makeText(context, null, time);
            layout = (LinearLayout) toast.getView();
            layout.setBackgroundResource(R.drawable.dialog_rectangle);
            layout.setPadding(80, 30, 80, 30);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setGravity(Gravity.CENTER);
            tv = new TextView(context);
            tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(Color.parseColor("#ffffff"));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimensionPixelSize(R.dimen.text_size_48));
            toastSetText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            toastSetText(msg);
        }
//        final ToastDialog.Builder toastDialog = new ToastDialog.Builder(context);
//        toastDialog.setAdapterData(msg);
//        toastDialog.create1().show();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                toastDialog.hideDialog();
//            }
//        }, 2000);
    }

    private static void toastSetText(String msg) {
        tv.setText(msg);
        layout.removeAllViews();
        layout.addView(tv);
    }

    public static void showToast(Context context, int resId) {
        showToast(context, context.getResources().getString(resId));
    }
}
