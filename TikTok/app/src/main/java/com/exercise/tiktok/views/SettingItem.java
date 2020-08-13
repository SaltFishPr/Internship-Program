package com.exercise.tiktok.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exercise.tiktok.R;

public class SettingItem extends LinearLayout {
    private ImageView settingIcon;
    private ImageView rightArrow;  // 右侧箭头
    private TextView settingTitle;
    private TextView settingDescribe;
    private TextView bottomView;  // 下划线

    private Boolean isLeftIcon;

    public SettingItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.item_setting, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.item_setting_attrs);//解析布局
        isLeftIcon = ta.getBoolean(R.styleable.item_setting_attrs_setting_drawable_left, true); //底部分割线是否显示

        settingIcon = findViewById(R.id.setting_icon); //左边图标
        rightArrow = findViewById(R.id.setting_rightArrow);//右边箭头
        settingTitle = findViewById(R.id.setting_title); //左边标题文字
        settingDescribe = findViewById(R.id.setting_describe);//右边描述文字
        bottomView = findViewById(R.id.setting_bottom); //底部分割线

        settingIcon.setImageDrawable(ta.getDrawable(R.styleable.item_setting_attrs_setting_icon));//赋值
        settingTitle.setText(ta.getText(R.styleable.item_setting_attrs_setting_title));
        settingDescribe.setText(ta.getText(R.styleable.item_setting_attrs_setting_describe));
        settingIcon.setVisibility(isLeftIcon ? View.VISIBLE : View.INVISIBLE);
        bottomView.setVisibility(View.VISIBLE);
        //回收属性对象
        ta.recycle();
    }

    /**
     * 设置标题内容
     */
    public void setTitle(String titleText) {
        settingTitle.setText(titleText);
    }
    public void setDescribeName (String name){
        settingDescribe.setText(name);
    }
}