package com.exercise.tiktok.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.exercise.tiktok.R;

public class MessageItem extends ConstraintLayout {
    private ImageView userImage;
    private TextView userName;
    private TextView userStatus;

    public MessageItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.item_message, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.item_message_attrs);//解析布局

        userImage = findViewById(R.id.user_img);
        userName = findViewById(R.id.user_name);
        userStatus = findViewById(R.id.user_status);

        userImage.setImageDrawable(ta.getDrawable(R.styleable.item_message_attrs_user_image));//赋值
        userName.setText(ta.getText(R.styleable.item_message_attrs_user_name));
        userStatus.setText(ta.getText(R.styleable.item_message_attrs_user_status));
        //回收属性对象
        ta.recycle();
    }
}