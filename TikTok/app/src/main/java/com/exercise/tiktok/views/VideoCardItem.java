package com.exercise.tiktok.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.exercise.tiktok.R;

public class VideoCardItem extends ConstraintLayout {
    private ImageView videoImage;
    private Button videoBtnIcon;
    private TextView videoDescribe;

    public VideoCardItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.item_video_card, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.item_video_card_attrs);//解析布局

        videoImage = findViewById(R.id.video_image);
        videoBtnIcon = findViewById(R.id.video_small_icon);
        videoDescribe = findViewById(R.id.video_describe);

        videoImage.setImageDrawable(ta.getDrawable(R.styleable.item_video_card_attrs_video_image));//赋值
        videoBtnIcon.setText(ta.getText(R.styleable.item_video_card_attrs_video_small_icon));
        videoDescribe.setText(ta.getText(R.styleable.item_video_card_attrs_video_describe));
        //回收属性对象
        ta.recycle();
    }
}