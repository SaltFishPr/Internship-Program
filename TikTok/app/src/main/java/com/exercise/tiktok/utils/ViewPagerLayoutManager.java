package com.exercise.tiktok.utils;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ViewPagerLayoutManager extends LinearLayoutManager {
    private PagerSnapHelper mPagerSnapHelper;
    private OnViewPagerListener mOnViewPagerListener;

    @Override
    public void onScrollStateChanged(int state) {
        switch (state) {
            case RecyclerView.SCROLL_STATE_IDLE:
                View viewIdle = mPagerSnapHelper.findSnapView(this);
                if (viewIdle == null) {
                    return;
                }
                int positionIdle = getPosition(viewIdle);
                if (mOnViewPagerListener != null && getChildCount() == 1) {
                    mOnViewPagerListener.onPageSelected(positionIdle, positionIdle == getItemCount() - 1);
                }
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:
                View viewDrag = mPagerSnapHelper.findSnapView(this);
                if (viewDrag != null) {
                    int positionDrag = getPosition(viewDrag);
                }
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                View viewSettling = mPagerSnapHelper.findSnapView(this);
                if (viewSettling != null) {
                    int positionSettling = getPosition(viewSettling);
                }
                break;
        }
    }

    public ViewPagerLayoutManager(Context context) {
        super(context);
        mPagerSnapHelper = new PagerSnapHelper();
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        mPagerSnapHelper.attachToRecyclerView(view);
    }

    public void setOnViewPagerListener(OnViewPagerListener listener) {
        this.mOnViewPagerListener = listener;
    }

    /**
     * 设置监听
     *
     * @param listener
     */

    private RecyclerView.OnChildAttachStateChangeListener mChildAttachStateChangeListener = new RecyclerView.OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(View view) {
            if (mOnViewPagerListener != null && getChildCount() == 1) {
                mOnViewPagerListener.onInitComplete();
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {
            if (mOnViewPagerListener != null)
                mOnViewPagerListener.onPageRelease(true, getPosition(view));
            else {
                if (mOnViewPagerListener != null)
                    mOnViewPagerListener.onPageRelease(false, getPosition(view));
            }
            mOnViewPagerListener.onPageRelease(true, getPosition(view));
        }
    };

}
