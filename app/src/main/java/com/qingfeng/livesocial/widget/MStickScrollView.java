package com.qingfeng.livesocial.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.amar.library.ui.StickyScrollView;

/**
 * Created by Administrator on 2017/8/25.
 */

public class MStickScrollView extends StickyScrollView {
    public MStickScrollView(Context context) {
        super(context);
    }

    public MStickScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MStickScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private float mDownPosX = 0;
    private float mDownPosY = 0;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final float x = ev.getX();
        final float y = ev.getY();

        final int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mDownPosX = x;
                mDownPosY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaX = Math.abs(x - mDownPosX);
                final float deltaY = Math.abs(y - mDownPosY);
                // 这里是够拦截的判断依据是左右滑动，读者可根据自己的逻辑进行是否拦截
                if (deltaX > deltaY) {//左右滑动
                    return false;
                }else{//上下滑动
                    return true;
                }
        }

        return super.onInterceptTouchEvent(ev);
    }
}
