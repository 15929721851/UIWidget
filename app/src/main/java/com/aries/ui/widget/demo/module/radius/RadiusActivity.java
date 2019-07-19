package com.aries.ui.widget.demo.module.radius;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aries.ui.view.radius.RadiusEditText;
import com.aries.ui.view.radius.RadiusRadioButton;
import com.aries.ui.view.radius.RadiusTextView;
import com.aries.ui.view.radius.delegate.RadiusViewDelegate;
import com.aries.ui.widget.demo.R;
import com.aries.ui.widget.demo.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: AriesHoo on 2019/4/11 15:32
 * @E-Mail: AriesHoo@126.com
 * @Function: RadiusView示例
 * @Description:
 */
public class RadiusActivity extends BaseActivity {

    @BindView(R.id.rtv_testRadius) RadiusTextView rtvTest;
    @BindView(R.id.rtv_disable) RadiusTextView rtvDisable;
    @BindView(R.id.tv_shapeRadius) TextView tvShape;
    @BindView(R.id.rtv_javaBg) RadiusTextView rtvJavaBg;
    @BindView(R.id.rtv_drawableRadius) RadiusTextView rtvDrawable;
    @BindView(R.id.rtv_selectRadius) RadiusTextView rtvSelect;
    @BindView(R.id.radioTest_radius) RadiusRadioButton mRadioButtonTest;

    @BindView(R.id.ret_circle) RadiusEditText mRetCircle;

    private GradientDrawable mBgDrawable = new GradientDrawable();

    @Override
    protected void setTitleBar() {
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_radius;
    }

    @Override
    protected void initView(Bundle var1) {
        mBgDrawable.setCornerRadius(getResources().getDimension(R.dimen.dp_radius));
        mBgDrawable.setStroke(getResources().getDimensionPixelSize(R.dimen.dp_stroke_width),
                Color.MAGENTA,
                getResources().getDimension(R.dimen.dp_dash_width),
                getResources().getDimension(R.dimen.dp_dash_gap));
        mBgDrawable.setShape(GradientDrawable.RECTANGLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            rtvJavaBg.setBackground(mBgDrawable);
        } else {
            rtvJavaBg.setBackgroundDrawable(mBgDrawable);
        }
        //以上是java代码设置GradientDrawable设置背景属性
        //以下是通过RadiusViewDelegate代理类设置达到相同效果大家可参照下依然是通过设置GradientDrawable属性
        rtvJavaBg.getDelegate()
                .setTextCheckedColor(Color.BLUE)
                .setBackgroundCheckedColor(Color.WHITE)
                .setRadius(getResources().getDimension(R.dimen.dp_radius))
                .setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.dp_stroke_width))
                .setStrokeColor(getResources().getColor(android.R.color.holo_purple))
                .setStrokeDashWidth(getResources().getDimension(R.dimen.dp_dash_width))
                .setStrokeDashGap(getResources().getDimension(R.dimen.dp_dash_gap))
                .init();
        rtvSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rtvSelect.setSelected(!rtvSelect.isSelected());
            }
        });
        rtvDrawable.getDelegate()
                .setLeftDrawable(null)
                .setOnSelectedChangeListener(new RadiusViewDelegate.OnSelectedChangeListener() {
                    @Override
                    public void onSelectedChanged(View view, boolean isSelected) {
                        Toast.makeText(mContext, "isSelected:" + isSelected, Toast.LENGTH_SHORT).show();
                    }
                }).init();

        RadiusRadioButton radiusSwitch = new RadiusRadioButton(mContext);
        radiusSwitch.getDelegate()
                .setBackgroundSelectedColor(Color.BLUE)
                .setBackgroundCheckedColor(Color.YELLOW)
                .setRadius(4f)
                .init();
        mRadioButtonTest.getDelegate()
                .setButtonPressedDrawable(R.drawable.ic_cb_normal)
                .init();

        mRetCircle.requestFocus();
        mRetCircle.setText("我就试一试光标位置");
    }


    @OnClick({R.id.rtv_testRadius, R.id.rtv_drawableRadius})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rtv_testRadius:
                boolean enable = rtvDisable.isEnabled();
                rtvDisable.setEnabled(!enable);
                break;
            case R.id.rtv_drawableRadius:
                rtvDrawable.setSelected(!rtvDrawable.isSelected());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if(mBgDrawable!=null){
           rtvJavaBg.setBackgroundColor(Color.TRANSPARENT);
           mBgDrawable =null;
        }
        super.onDestroy();
    }
}
