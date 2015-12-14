package activitydialogtest.pczhu.com.everytest.widge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 名称：EveryTest
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/10 上午10:39
 * 版本：V1.0
 * 修改历史：
 */
public class CustomImgaeMatrixView extends ImageView {
    public CustomImgaeMatrixView(Context context) {
        super(context);
    }

    public CustomImgaeMatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImgaeMatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public void setImageMatrix(Matrix matrix) {
        super.setImageMatrix(matrix);
    }
}
