package com.supermarket.view;

import android.view.View;
import android.widget.FrameLayout;

public class ViewWrapper {

    private View mTarget;

    public ViewWrapper(View target){

        mTarget = target;
    }

    public float getTop(){
         return mTarget.getTop();
    }

    public void setTop(float top){
//			mTarget.setTop((int) top);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(0, (int) top, 0, 0);
        mTarget.setLayoutParams(params);
        mTarget.requestLayout();
    }
}