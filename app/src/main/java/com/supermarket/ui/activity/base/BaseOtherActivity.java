/**
 *
 */
package com.supermarket.ui.activity.base;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.supermarket.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author liqiang
 * @data 2014年9月13日
 */
public abstract class BaseOtherActivity extends AbstractActivity {
    @Bind(R.id.container_top_layout)
    RelativeLayout mTitleLayout;
    @Bind(R.id.title_left1)
    ImageView mLeftView1;
    @Bind(R.id.title_left2)
    TextView mLeftView2;
    @Bind(R.id.title_right1)
    ImageView mRightView1;
    @Bind(R.id.title_right3)
    TextView mRightView3;
    @Bind(R.id.title_content)
    TextView mContentView;
    @Bind(R.id.title_right_layout)
    LinearLayout mTitleRightLayout;

    @Override
    boolean onCreateTTActivity(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = LayoutInflater.from(this);
        View wholelay = inflater.inflate(R.layout.tt_basic_titlebar_lay, null, false);
        View contentView = initContentView();
        if (contentView != null) {
            LinearLayout.LayoutParams contentLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            LinearLayout contentlay = (LinearLayout) wholelay.findViewById(R.id.content_lay);
            contentlay.addView(contentView, contentLayoutParams);
        }
        setContentView(wholelay);
        ButterKnife.bind(this, wholelay);
        initTitleBarView();
        handleCreate();

        return true;
    }

    private void initTitleBarView() {
        mTitleLayout.setBackgroundColor(getResources().getColor(R.color.color_1fa7cb));

        mLeftView1.setOnClickListener(this);
        mLeftView2.setOnClickListener(this);
        mRightView1.setOnClickListener(this);
        mRightView3.setOnClickListener(this);
        mContentView.setOnClickListener(this);
    }

    @Override
    protected void customOnClick(View v) {
        switch (v.getId()) {
            case R.id.title_left1:
                onLeftView1Click();
                break;
            case R.id.title_left2:
                onLeftView2Click();
                break;
            case R.id.title_right1:
                onRightView1Click();
                break;
            case R.id.title_right3:
                onRightView3Click();
                break;
            case R.id.title_content:
                onCenterViewClick();
                break;
            default:
                onCustomClick(v);
                break;
        }
    }

    protected void onLeftView1Click() {
        finish();
        hideSoftInput(mLeftView1);
    }

    protected void onLeftView2Click() {

    }

    protected void onRightView1Click() {
    }

    protected void onRightView3Click() {

    }

    protected void onCenterViewClick() {

    }

    protected void setLeftView1Background(int resId) {
        if (mLeftView1 != null) {
            mLeftView1.setImageResource(resId);
        }
    }

    protected void setLeftView1Visibility(int visibility) {
        if (mLeftView1 != null) {
            mLeftView1.setVisibility(visibility);

            if (mLeftView2 != null && View.VISIBLE == visibility) {
                mLeftView2.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置左边按钮的属性
     *
     * @param strResId 要显示的内容资源id
     */
    protected void setLeftView2Content(int strResId) {
        if (mLeftView2 != null) {
            mLeftView2.setText(strResId);
        }
    }

    protected void setLeftView2Visibility(int visibility) {
        if (mLeftView2 != null) {
            mLeftView2.setVisibility(visibility);

            if (mLeftView1 != null && View.VISIBLE == visibility) {
                mLeftView1.setVisibility(View.GONE);
            }
        }
    }

    protected void setRightView1Background(int resId) {
        if (mRightView1 != null) {
            mRightView1.setImageResource(resId);
        }
    }

    protected void setRightView1Visibility(int visibility) {
        if (mRightView1 != null) {
            mRightView1.setVisibility(visibility);
        }

        if (mRightView3 != null)
            mRightView3.setVisibility(View.GONE);
    }

    /**
     * 设置右边按钮的属性
     *
     * @param strResId 要显示的内容资源id
     */
    protected void setRightView3Content(int strResId) {
        if (mRightView3 != null) {
            mRightView3.setText(strResId);
        }
    }
    /**
     * 设置右边按钮的属性
     *
     * @param size 要显示的内容Size
     */
    protected void setRightView3Size(int size) {
        if (mRightView3 != null) {
            mRightView3.setTextSize(size);
        }
    }
    /**
     * 设置右边按钮的属性
     *
     * @param strResId 要显示的内容
     */
    protected void setRightView3Content(String strResId) {
        if (mRightView3 != null) {
            mRightView3.setText(strResId);
        }
    }

    /**
     * 设置右边按钮的属性
     *
     * @param strResId 点击时的字体颜色
     */
    protected void setRightView3TextColor(int strResId) {
        if (mRightView3 != null) {
            ColorStateList csl = (ColorStateList) getResources().getColorStateList(strResId);
            mRightView3.setTextColor(csl);
        }
    }

    protected void setRightView3Visibility(int visibility) {
        if (mRightView3 != null)
            mRightView3.setVisibility(visibility);

        if (mRightView1 != null)
            mRightView1.setVisibility(View.GONE);
    }

    protected void setRightView3Enable(boolean boo){
        if(mRightView3 !=null){
            mRightView3.setEnabled(boo);
        }
    }

    protected void setCenterViewContent(int strResId) {
        if (mContentView != null) {
            mContentView.setText(strResId);
        }
    }

    protected void setCenterViewContent(String content) {
        if (mContentView != null) {
            mContentView.setText(content);
        }
    }

    protected TextView setCenterContentView() {
        if (mContentView != null) {
          return mContentView;
        }
        return null;
    }

    protected void setCenterViewBackground(int resId) {
        if (mContentView != null) {
            mContentView.setBackgroundResource(resId);
        }
    }

    protected void setAllBackgroundColor(int resId) {
        if (mTitleLayout != null) {
            mTitleLayout.setBackgroundColor(resId);
        }
    }

    protected void setTitleVisiable(int value) {
        if (mTitleLayout != null) {
            mTitleLayout.setVisibility(value);
        }
    }

    protected void setRightLayoutHide() {
        if (mTitleRightLayout != null) {
            mTitleRightLayout.setVisibility(View.INVISIBLE);
        }
    }

    protected abstract void onCustomClick(View v);

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public TextView getCenTextView() {
        return mContentView;
    }

}
