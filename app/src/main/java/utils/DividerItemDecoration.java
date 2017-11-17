package utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.custom.cainiaowo.R;

/**
 * Created by xuchichi on 2017/11/14.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private int mydevider;
    private Paint dividerPaint;

    public DividerItemDecoration(Context context) {
        dividerPaint = new Paint();
        //设置分割线颜色
        dividerPaint.setColor(context.getResources().getColor(R.color.colorAccent));
        //设置分割线宽度
        mydevider = context.getResources().getDimensionPixelSize(R.dimen.divider_bottom);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = mydevider;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + mydevider;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }
    }
}
