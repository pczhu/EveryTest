package activitydialogtest.pczhu.com.everytest;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.lzyzsd.randomcolor.RandomColor;

import java.util.ArrayList;

import activitydialogtest.pczhu.com.everytest.utils.DisplayUtil;
import activitydialogtest.pczhu.com.everytest.utils.Utils;

/**
 * 名称：EveryTest
 * 作用：标签布局
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/2 上午11:58
 * 版本：V1.0
 * 修改历史：
 */
public class FlowLayout extends ViewGroup {
    private static final float DEFAULT_TEXT_SIZE = 16.0f;
    private static final String DEFAULT_TEXT_COLOR = "#666666";
    private static final float DEFAULT_PADDING = 0.0f;
    private static final float DEFAULT_CELLPADDING = 5.0f;
    private static final float DEFAULT_CELLMARGIN = 5.0f;
    /**
     * margin 上下左右
     */
    private ArrayList<TextView> textViews;
    private ArrayList<String> textlist;
    int[] screenWH = null;

    /**
     * 各种参数
     * @param
     */
    int dimension_textsize;
    int dimension_cellpadding;
    int dimension_cellmargin;
    int dimension_textpadding;
    int dimension_textpaddingtop;
    int dimension_textpaddingbottom;
    int dimension_textpaddingleft;
    int dimension_textpaddingright;
    int color_textcolor;

    public FlowLayout(Context context) {
        super(context);
        init(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    private void init(Context context,AttributeSet attrs){
        screenWH = Utils.getScreenMaxWidth(context);
        if(attrs != null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout_Style);

            dimension_textsize = typedArray.getDimensionPixelOffset(R.styleable.FlowLayout_Style_fl_textsize, DisplayUtil.dip2px(getContext(), DEFAULT_TEXT_SIZE));
            dimension_cellpadding = typedArray.getDimensionPixelOffset(R.styleable.FlowLayout_Style_fl_cellpadding, DisplayUtil.dip2px(getContext(), DEFAULT_CELLPADDING));
            dimension_cellmargin = typedArray.getDimensionPixelOffset(R.styleable.FlowLayout_Style_fl_cellmargin, DisplayUtil.dip2px(getContext(), DEFAULT_CELLMARGIN));
            dimension_textpadding = typedArray.getDimensionPixelOffset(R.styleable.FlowLayout_Style_fl_text_padding, DisplayUtil.dip2px(getContext(), DEFAULT_PADDING));
            dimension_textpaddingtop = typedArray.getDimensionPixelOffset(R.styleable.FlowLayout_Style_fl_text_paddingtop, DisplayUtil.dip2px(getContext(), DEFAULT_PADDING));
            dimension_textpaddingbottom =typedArray.getDimensionPixelOffset(R.styleable.FlowLayout_Style_fl_text_paddingbottom, DisplayUtil.dip2px(getContext(), DEFAULT_PADDING));
            dimension_textpaddingleft = typedArray.getDimensionPixelOffset(R.styleable.FlowLayout_Style_fl_text_paddingleft, DisplayUtil.dip2px(getContext(), DEFAULT_PADDING));
            dimension_textpaddingright = typedArray.getDimensionPixelOffset(R.styleable.FlowLayout_Style_fl_text_paddingright, DisplayUtil.dip2px(getContext(), DEFAULT_PADDING));
            color_textcolor = typedArray.getColor(R.styleable.FlowLayout_Style_fl_textcolor, getResources().getColor(R.color.white));
            checkDimension();
        }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w_mode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int h_mode = MeasureSpec.getMode(heightMeasureSpec);
        int heigh = MeasureSpec.getSize(heightMeasureSpec);

        int layout_height = 0;
        int layout_width = 0;
        int max_layout_width = 0;
        for (int i = 0;i < getChildCount(); i++){
           // Log.e("子空间个数",getChildCount()+"");
            View child = getChildAt(i);
            if(child.getVisibility() != View.GONE){
                measureChild(child,widthMeasureSpec,heightMeasureSpec);
                int childwidth = child.getMeasuredWidth();
                int childheigh = child.getMeasuredHeight();
                //int[] margin = getLayoutParams(child);
                int targetwidth = layout_width+childwidth+dimension_textpaddingleft+dimension_textpaddingright;
                if(w_mode == MeasureSpec.EXACTLY){//如果是最大数值或者是精确值
                    //Log.e("测量目标",""+targetwidth+":"+width);
                    if(targetwidth < width){//小于这个

                        layout_width+=childwidth+dimension_textpaddingleft+dimension_textpaddingright;//继续对宽度加成
                       // Log.e("测量小于",""+layout_width);
                    }else{
                        layout_width = childwidth+dimension_textpaddingleft+dimension_textpaddingright;//重置宽度
                        layout_height+=childheigh+dimension_textpaddingtop+dimension_textpaddingbottom;//高度加成
                       // Log.e("测量大于",""+layout_height);
                    }
                }else{
                    if(targetwidth < screenWH[0]){//如果是包裹内容 小于屏幕宽度
                        layout_width+=childwidth+dimension_textpaddingleft+dimension_textpaddingright;//继续对宽度加成
                    }else{
                        layout_width = childwidth+dimension_textpaddingleft+dimension_textpaddingright;
                        layout_height+=childheigh+dimension_textpaddingtop+dimension_textpaddingbottom;//高度加成
                    }
                }
                if(i == 0){
                   // Log.e("各个",""+i);
                    layout_height+=childheigh+dimension_textpaddingtop+dimension_textpaddingbottom;//只有一行
                }
            }
            max_layout_width = Math.max(layout_width,max_layout_width);

        }
        setMeasuredDimension((w_mode == MeasureSpec.EXACTLY) ? width : max_layout_width,
                (h_mode == MeasureSpec.EXACTLY ? heigh : layout_height));
       // Log.e("122——宽度", ((w_mode == MeasureSpec.EXACTLY) ? width : max_layout_width) + "");
       // Log.e("122——高度", (h_mode == MeasureSpec.EXACTLY ? heigh : layout_height) + "");

    }

    private int[] getLayoutParams(View child) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) child.getLayoutParams();
        int[] margin = new int[]{marginLayoutParams.topMargin,
        marginLayoutParams.bottomMargin,
        marginLayoutParams.leftMargin,
        marginLayoutParams.rightMargin};
        return margin;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int layout_width = l;
        int layout_height = 0;
        for (TextView textview: textViews) {
            if(textview!=null && textview.getVisibility() != View.GONE){
                // MarginLayoutParams layoutParams = (MarginLayoutParams) getLayoutParams();
                //int childwidth = textview.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
                // int childheight =textview. getMeasuredHeight()+layoutParams.topMargin+layoutParams.bottomMargin;
                int target = layout_width+dimension_textpaddingleft+dimension_textpaddingright+textview.getMeasuredWidth();
//                Log.e("没有进入目标左上右下", (layout_width + dimension_textpaddingleft) +":"+
//                        + (layout_height + dimension_textpaddingtop) +":"+
//                        + (layout_width + textview.getMeasuredWidth() + dimension_textpaddingleft + dimension_textpaddingright) +":"+
//                        + (layout_height + textview.getMeasuredHeight() + dimension_textpaddingtop + dimension_textpaddingbottom));

                if(target < getMeasuredWidth()) {
                    textview.layout(layout_width + dimension_textpaddingleft,
                            layout_height + dimension_textpaddingtop,
                            layout_width + textview.getMeasuredWidth() + dimension_textpaddingleft + dimension_textpaddingright,
                            layout_height + textview.getMeasuredHeight() + dimension_textpaddingtop + dimension_textpaddingbottom);
//                    Log.e("目标左上右下", (layout_width + dimension_textpaddingleft) +":"+
//                            + (layout_height + dimension_textpaddingtop) +":"+
//                            + (layout_width + textview.getMeasuredWidth() + dimension_textpaddingleft + dimension_textpaddingright) +":"+
//                            + (layout_height + textview.getMeasuredHeight() + dimension_textpaddingtop + dimension_textpaddingbottom));
                    layout_width = target;
                }else{
                    layout_width = 0;
                    layout_height+=textview.getMeasuredHeight()+dimension_textpaddingtop+dimension_textpaddingbottom;
                    target = layout_width+dimension_textpaddingleft+dimension_textpaddingright+textview.getMeasuredWidth();
                    if(target < getMeasuredWidth()) {
                        textview.layout(layout_width + dimension_textpaddingleft,
                                layout_height + dimension_textpaddingtop,
                                layout_width + textview.getMeasuredWidth() + dimension_textpaddingleft + dimension_textpaddingright,
                                layout_height + textview.getMeasuredHeight() + dimension_textpaddingtop + dimension_textpaddingbottom);
//                        Log.e("换行的目标左上右下", (layout_width + dimension_textpaddingleft) + ":" +
//                                +(layout_height + dimension_textpaddingtop) + ":" +
//                                +(layout_width + textview.getMeasuredWidth() + dimension_textpaddingleft + dimension_textpaddingright) + ":" +
//                                +(layout_height + textview.getMeasuredHeight() + dimension_textpaddingtop + dimension_textpaddingbottom));
                        layout_width = target;
                    }
                }
            }
        }
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return  new MarginLayoutParams(getContext(),attrs);
    }
    public ArrayList<TextView> getTextViews() {
        return textViews;
    }

    public void setTextViews(ArrayList<TextView> textViews) {
        this.textViews = textViews;
        if(textViews!=null && textViews.size()!=0) {
            RandomColor randomColor = new RandomColor();
            LayoutParams layout = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);

            for (int i = 0;i < textViews.size();i++){

                int color = randomColor.randomColor();
                TextView textview = textViews.get(i);
                textview.setTextColor(color_textcolor);
                textview.setPadding(dimension_textpaddingleft, dimension_textpaddingtop, dimension_textpaddingright, dimension_textpaddingbottom);
                textview.setTextSize(dimension_textsize);
                textview.setBackgroundColor(color);
                this.addView(textview,layout);
            }

        }
        invalidate();
    }
    public ArrayList<String> getTextlist() {
        return textlist;
    }

    public void setTextlist(ArrayList<String> textlist) {
        this.textlist = textlist;
        ArrayList<TextView> textviews = new ArrayList<TextView>();
        for (String text: textlist) {
            TextView textview = new TextView(getContext());
            textview.setText(text+"");
            textviews.add(textview);
        }
        setTextViews(textviews);
    }
    public void checkDimension(){
        if(dimension_textpadding != 0){
            dimension_textpaddingbottom = dimension_textpadding;
            dimension_textpaddingleft = dimension_textpadding;
            dimension_textpaddingright= dimension_textpadding;
            dimension_textpaddingtop = dimension_textpadding;
        }
    }
}
