package activitydialogtest.pczhu.com.everytest.animotion;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Matrix;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import activitydialogtest.pczhu.com.everytest.utils.LogUtils;

/**
 * 名称：MyTransXAnimatorListener
 * 作用：
 * 描述：一个平移的动画
 * 作者：pczhu
 * 创建时间： 15/12/10 上午10:46
 * 版本：V1.0
 * 修改历史：
 */
public class MyTransXAnimatorListener implements ValueAnimator.AnimatorUpdateListener {
    private Matrix mPrimaryMatrix;
    private ImageView imageView;
    public MyTransXAnimatorListener(Matrix matrix,ImageView imageView) {
        mPrimaryMatrix = new Matrix(matrix);
        this.imageView = imageView;
    }
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
       // imageView.postInvalidate();
        //imageView.invalidate();
       // int dx = (Integer) animation.getAnimatedValue();
        float dx = (float) animation.getAnimatedValue("translationX");
        float dy = (float) animation.getAnimatedValue("translationY");
//        float dz = (float) animation.getAnimatedValue("translationZ");
//        float desgree = (float) animation.getAnimatedValue("rotation");
//        float sx = (float) animation.getAnimatedValue("scaleX");
//        float sy = (float) animation.getAnimatedValue("scaleY");
        //平移
        imageView.setTranslationX(dx);
        imageView.setTranslationY(dy);
        LogUtils.i(""+dy);

        //翻转
  //      imageView.setRotation(desgree);
        //imageView.setRotationX(imageView.getWidth() / 2);
        //imageView.setRotationY(imageView.getHeight()/2);
        //放大缩小
//        imageView.setScaleX(sx);
//        imageView.setScaleY(sy);
       // imageView.postInvalidate();
//        Matrix matrix = new Matrix(mPrimaryMatrix);
//        matrix.postTranslate(dx, dy);
        //matrix.postRotate(rd,imageView.getWidth()/2,imageView.getMeasuredHeight()/2);
        //imageView.setImageMatrix(matrix);
    }

}

