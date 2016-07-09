package com.supermarket.utils;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.supermarket.R;

/**
 * Created by chenjunyu on 2016-01-07.
 * 动态增加View
 */
public class DynamicViewUtils {


    /**
     * 动态增加 无数据后的底部view  没有了哦
     * @param mContext
     * @return
     */
    public static  View getNotDatasView(Context mContext){
        TextView tv = new TextView(mContext);
        tv.setText(mContext.getResources().getString(R.string.no_data));
        tv.setPadding(0,(int)mContext.getResources().getDimension(R.dimen.dip_8),
                0,(int)mContext.getResources().getDimension(R.dimen.dip_5));
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(mContext.getResources().getColor(R.color.color_999999));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.text_size_40));
        return tv;
    }
}
