package activitydialogtest.pczhu.com.everytest.widge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.github.lzyzsd.randomcolor.RandomColor;

import java.util.ArrayList;
import java.util.Random;

import activitydialogtest.pczhu.com.everytest.utils.DisplayUtil;
import activitydialogtest.pczhu.com.everytest.utils.LogUtils;

/**
 * 名称：EveryTest
 * 作用：实现动态增长的几个柱状图
 * 描述：练习绘画式动画的开发过程。
 * 作者：pczhu
 * 创建时间： 15/12/8 上午11:42
 * 版本：V1.0
 * 修改历史：
 */
public class CustomAniView extends View{
    private Paint picpaint;
    private Paint txtpaint;
    private int cell_margin;
    private int cell_width;
    private int times = 0;
    private int max_times = 0;
    private ArrayList<ShapeProperty> propertys;

    public CustomAniView(Context context) {
        super(context);
        initview(context);
    }

    public CustomAniView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initview(context);
    }

    public CustomAniView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initview(context);
    }

    /**
     * 各种初始化
     * @param context
     */
    private void initview(Context context) {
        cell_margin = DisplayUtil.dip2px(getContext(),20.0f);
        cell_width = DisplayUtil.dip2px(getContext(),40.0f);
        LogUtils.i("宽度"+cell_margin+":"+cell_width);
        getData();
    }
    private void getData(){
        propertys = new ArrayList<ShapeProperty>();
        RandomColor randomColor = new RandomColor();
        Random rand = new Random();

        for(int i = 0; i < 5; i++){
            propertys.add(new ShapeProperty(i,
                    randomColor.randomColor(),
                    cell_width,
                    DisplayUtil.dip2px(getContext(),rand.nextInt(240) + 80),
                    (cell_width+cell_margin)*i));
            LogUtils.i("目标单位"+propertys.toString());
        }
    }
    /**
     * 初始化画笔
     */
    private Paint getPaint(int color){
        picpaint = new Paint();
        picpaint.setColor(color);
        return picpaint;
    }
    private Paint getTextPaint(int color){
        txtpaint = new Paint();
        txtpaint.setTextAlign(Paint.Align.CENTER);
        txtpaint.setColor(color);

        txtpaint.setTextSize(DisplayUtil.sp2px(getContext(), 16.0f));
        return txtpaint;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w_mode = MeasureSpec.getMode(widthMeasureSpec);
        int w_size = MeasureSpec.getSize(widthMeasureSpec);

        int h_mode = MeasureSpec.getMode(heightMeasureSpec);
        int h_size = MeasureSpec.getSize(heightMeasureSpec);
        int layout_width = 0;
        int layout_height = 0;
        for (ShapeProperty property : propertys){
            layout_width+=property.getWidth()+cell_margin;
            layout_height = Math.max(layout_height,property.getSize());
        }
        max_times = layout_height;
        LogUtils.i(""+((w_mode == MeasureSpec.EXACTLY)?w_size:(layout_width - cell_margin)));
        LogUtils.i((h_mode == MeasureSpec.EXACTLY ? h_size : layout_height)+"");
        setMeasuredDimension((w_mode == MeasureSpec.EXACTLY) ? w_size : (layout_width - cell_margin),
                (h_mode == MeasureSpec.EXACTLY ? h_size : layout_height));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        times++;
        //canvas.restore();
        for (ShapeProperty property:propertys) {
            if(times <= property.getSize()){
                canvas.drawRect(property.getLeft(),
                        getMeasuredHeight() - times,
                        property.getLeft() + property.getWidth(),
                        getMeasuredHeight(),
                        getPaint(property.getColor()));
                canvas.drawText(""+times,
                        property.getLeft()+property.getWidth()/2,
                        getMeasuredHeight() -times - 10,
                        getTextPaint(property.getColor()));

            }else{
                canvas.drawRect(property.getLeft(),
                        getMeasuredHeight() - property.getSize(),
                        property.getLeft() + property.getWidth(),
                        getMeasuredHeight(),
                        getPaint(property.getColor()));
                canvas.drawText(""+property.getSize(),
                        property.getLeft()+property.getWidth()/2,
                        getMeasuredHeight() - property.getSize()-10,
                        getTextPaint(property.getColor()));
            }
        }
        if(times <  max_times){
            invalidate();
        }
        //左上右下


    }
    public class ShapeProperty{
        private int id;
        private int color;
        private int width;
        private int size;
        private int left;

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public ShapeProperty(int id, int color, int width, int size, int left) {
            this.id = id;
            this.color = color;
            this.width = width;
            this.size = size;
            this.left = left;
        }

        @Override
        public String toString() {
            return "ShapeProperty{" +
                    "id=" + id +
                    ", color=" + color +
                    ", width=" + width +
                    ", size=" + size +
                    ", left=" + left +
                    '}';
        }
    }
}
