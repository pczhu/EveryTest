package activitydialogtest.pczhu.com.everytest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 名称：EveryTest
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/1 下午1:57
 * 版本：V1.0
 * 修改历史：
 */
public class CustomLayout extends ViewGroup {
    public CustomLayout(Context context) {
        super(context);
    }

    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w_mode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        int h_mode = MeasureSpec.getMode(heightMeasureSpec);
        int heigh = MeasureSpec.getSize(heightMeasureSpec);
        int layoutHeight = 0;
        int layoutWidth = 0;
        for (int i = 0; i < getChildCount(); i++){
            View view  = getChildAt(i);
            if(view.getVisibility()!= View.GONE){
                measureChild(view,widthMeasureSpec,heightMeasureSpec);
                MarginLayoutParams layoutParams = (MarginLayoutParams) view.getLayoutParams();
                int childwidth = view.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
                int childheight = view.getMeasuredHeight()+layoutParams.topMargin+layoutParams.bottomMargin;
                layoutHeight += childheight;
                Log.d("CustomLayout", "onLayout "+view.getMeasuredHeight()+":"+view.getMeasuredWidth());
                layoutWidth = Math.max(layoutWidth,childwidth);
            }

        }
        Log.d("宽度计算",""+((w_mode == MeasureSpec.AT_MOST)?layoutWidth:width));
        setMeasuredDimension(
                (w_mode == MeasureSpec.AT_MOST) ? layoutWidth : width,
                (h_mode == MeasureSpec.AT_MOST) ? layoutHeight : heigh);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d("CustomLayout","onLayout "+changed+":"+l+":"+t+":"+r+":"+b);
        int top = 0;
        for (int i = 0;i < getChildCount();i++){
            View view = getChildAt(i);

            if(view.getVisibility() != View.GONE){
                MarginLayoutParams layoutParams = (MarginLayoutParams) view.getLayoutParams();
                int childwidth = view.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
                int childheight = view.getMeasuredHeight()+layoutParams.topMargin+layoutParams.bottomMargin;
                //左边 上边 右边 下边
                view.layout(l+layoutParams.leftMargin,
                        top+layoutParams.topMargin,
                        l + view.getMeasuredWidth()+layoutParams.leftMargin,
                        top + view.getMeasuredHeight()+layoutParams.topMargin);
               // Log.d("CustomLayout", "onLayout " + l+":"+top+":"+(top+view.getMeasuredHeight())+":"+(l+view.getMeasuredWidth()));
                top += childheight;
            }

            Log.d("CustomLayout","onLayout "+top);
        }

    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return  new MarginLayoutParams(getContext(),attrs);
    }
}
