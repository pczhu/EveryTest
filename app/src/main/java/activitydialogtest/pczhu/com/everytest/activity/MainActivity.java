package activitydialogtest.pczhu.com.everytest.activity;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;

import activitydialogtest.pczhu.com.everytest.R;

/**
 * 名称：MainActivity
 * 作用：斐波那契数列的优化
 * 描述：采用缓存优化常用算法会大大增加性能
 * 作者：pczhu
 * 创建时间： 15/12/9 下午1:52
 * 版本：V1.0
 * 修改历史：
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private TextView tv;
    private TextView tv2;
    private TextView tv3;
    private EditText ed_text;
    private EditText ed_text2;

    TextView tv_right;
    TextView tv2_right;
    TextView tv3_right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.button1);
        Button btn2 = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);

        tv = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);

        tv_right = (TextView) findViewById(R.id.tv1_right);
        tv2_right = (TextView) findViewById(R.id.tv2_right);
        tv3_right = (TextView) findViewById(R.id.tv3_right);
        ed_text = (EditText) findViewById(R.id.editText);
        ed_text2 = (EditText) findViewById(R.id.editText2);

    }

    public static long fib(int n){

        if(n > 1) {
            long result = 1;
            do {
                result += fib(n-2);
                n--;
            } while (n > 1);
              return result;

        }
        return n;
    }

    /**
     * 优化的菲波那切数列
     */
    public static BigInteger computeReCache(int n){
        SparseArray<BigInteger> cache = new SparseArray<BigInteger>();
        return computeReCache(n, cache);
    }
    public static BigInteger computeReCache(int n,SparseArray<BigInteger> cache){
        if(n > 92){
            BigInteger fn = cache.get(n);
            if(fn == null){
                int m = (n / 2) + (n & 1);
                BigInteger fm = computeReCache(m,cache);
                BigInteger fm_1 = computeReCache(m-1,cache);
                if((n & 1) == 1){
                    fn = fm.pow(2).add(fm_1.pow(2));
                }else{
                    fn = fm_1.shiftLeft(1).add(fm).multiply(fm);
                }

                cache.put(n,fn);

            }
            return fn;
        }
        return BigInteger.valueOf(iterativeFaster(n));
    }
    public static long iterativeFaster(int n){
        if(n > 1){
            long a,b = 1;
            n--;
            a = n & 1;
            n /= 2;
            while (n-- > 0){
                a += b;
                b += a;
            }
            return b;
        }
        return n;
    }

///////////////////////////////////

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.button1:
                int n = 0;
                try {
                    n = Integer.parseInt(ed_text.getText().toString());
                }catch (Exception e){
                    return;
                }
                btnaction(n);
                break;
            case R.id.button2:
                int m = 0;
                try {
                    m = Integer.parseInt(ed_text2.getText().toString());
                }catch (Exception e){
                    return;
                }
                btnaction2(m);
                break;
        }
    }

    private void btnaction2(int n) {
        long currentThreadTimeMillisstart = SystemClock.currentThreadTimeMillis();
        tv_right.setText(currentThreadTimeMillisstart+"");
        BigInteger result = computeReCache(n);
        long currentThreadTimeMillisend = SystemClock.currentThreadTimeMillis();
        tv2_right.setText(currentThreadTimeMillisend + "");
        tv3_right.setText(currentThreadTimeMillisend - currentThreadTimeMillisstart + ":" + result);
    }

    private void btnaction(int n) {
        long currentThreadTimeMillisstart = SystemClock.currentThreadTimeMillis();
        tv.setText(currentThreadTimeMillisstart+"");
        long result = fib(n);
        long currentThreadTimeMillisend = SystemClock.currentThreadTimeMillis();
        tv2.setText(currentThreadTimeMillisend + "");
        tv3.setText(currentThreadTimeMillisend - currentThreadTimeMillisstart + ":" + result);
    }

}
