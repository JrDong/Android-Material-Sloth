package xyz.ibat.sloth.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by DongJr on 2017/1/18.
 */

public class ZoomImageView extends ImageView {

    private int mViewwidth;
    private int mViewHeight;

    public ZoomImageView(Context context) {
        super(context);
    }

    public ZoomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZoomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (getDrawable() == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.AT_MOST) {

            WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            Display defaultDisplay = manager.getDefaultDisplay();
            int width = defaultDisplay.getWidth();
            int height = defaultDisplay.getHeight();

            float viewWidth = getDrawable().getIntrinsicWidth();
            float viewHeight = getDrawable().getIntrinsicHeight();
            float scale = viewHeight / viewWidth;

            //先适配宽度
            viewWidth = width;
            viewHeight = viewWidth * scale;

            //如果高度超出屏幕则适配高度
            if (viewHeight > height) {
                viewHeight = height;
                viewWidth = viewHeight / scale;
            }

            setMeasuredDimension((int) viewWidth, (int) viewHeight);
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
