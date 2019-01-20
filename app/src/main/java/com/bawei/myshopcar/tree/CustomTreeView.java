package com.bawei.myshopcar.tree;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class CustomTreeView extends LinearLayout {
    private Paint mPaint;

    public CustomTreeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        setWillNotDraw(false);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(3);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        int bottom = 0;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            bottom += view.getMeasuredHeight();
        }

        setMeasuredDimension(sizeWidth, bottom);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int left = 0, top = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);

            if (i % 2 == 0) {
                left = 0;
            } else {
                left = getMeasuredWidth() / 2;
            }

            view.layout(left, top, left + getMeasuredWidth() / 2, top + view.getMeasuredHeight());
            top += view.getMeasuredHeight();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(getMeasuredWidth() / 2, 0, getMeasuredWidth() / 2, getMeasuredHeight(), mPaint);
    }
}

