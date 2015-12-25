package activitydialogtest.pczhu.com.everytest.refresh2;

import java.sql.Date;
import java.text.SimpleDateFormat;





import android.annotation.SuppressLint;
import android.util.Log;

import activitydialogtest.pczhu.com.everytest.utils.StringUtils;

/**
 * Logͳһ������
 * 
 * @author wen
 * @version 2015��1��16��
 * @see L
 * @since
 */
@SuppressLint("SimpleDateFormat")
public class L {

	public final static int VERBOSE = 5; // ������������е�����Ϣ ����
											// VERBOSE��DEBUG��INFO��WARN��ERROR

	public final static int DEBUG = 4; // debug�����������DEBUG��INFO��WARN��ERROR������Ϣ

	public final static int INFO = 3; // info�����������INFO��WARN��ERROR������Ϣ

	public final static int WARN = 2; // waring�����������WARN��ERROR������Ϣ

	public final static int ERROR = 1; // error��������ֻ���ERROR������Ϣ

	public static int level = 0;        //0��6

	private static final String TAG = "wen";
	// private static final String TAG = _CLASS_FUNC();
	public static final String SEPARATOR = ",";

	// ������Ĭ��tag�ĺ���
	public static void v(String msg) {
		if (VERBOSE >= level && !StringUtils.isEmpty(msg))
		{
			Log.v(TAG, combineNoTagMessage(msg));
		}
			
	}

	public static void d(String msg) {
		if (DEBUG >= level && !StringUtils.isEmpty(msg)){
			Log.d(TAG, combineNoTagMessage(msg));	
		}
			
	}

	public static void i(String msg) {
		if (INFO >= level && !StringUtils.isEmpty(msg)){
			Log.i(TAG, combineNoTagMessage(msg));
		}
			
	}

	public static void w(String msg) {
		if (WARN >= level && !StringUtils.isEmpty(msg)){
			Log.w(TAG, combineNoTagMessage(msg));
		}
			
	}

	public static void e(String msg) {
		if (ERROR >= level && !StringUtils.isEmpty(msg)){
			Log.e(TAG, combineNoTagMessage(msg));
		}
			
	}

	// �����Ǵ����Զ���tag�ĺ���,tagΪ����
	public static void v(String tag, String msg) {
		if (VERBOSE >= level && !StringUtils.isEmpty(msg)){
			Log.v(tag, combineMessage(tag,msg));
		}
			
	}

	public static void d(String tag, String msg) {
		if (DEBUG >= level && !StringUtils.isEmpty(msg)){
			Log.d(tag, combineMessage(tag,msg));
		}
			
	}

	public static void i(String tag, String msg) {
		if (INFO >= level && !StringUtils.isEmpty(msg)){
			Log.i(tag, combineMessage(tag,msg));
		}
			
	}

	public static void w(String tag, String msg) {
		if (WARN >= level && !StringUtils.isEmpty(msg)){
			Log.w(tag, combineMessage(tag,msg));	
		}
			
	}

	public static void e(String tag, String msg) {
		if (ERROR >= level && !StringUtils.isEmpty(msg)){
			Log.e(tag, combineMessage(tag,msg));
		}
			
	}

	private static String combineNoTagMessage( String msg) {
		return "[ " + TAG + "] "+ " Line: " + getLineNumber() + " : " + msg;
	
	}
	
	private static String combineMessage(String tag, String msg) {
		return "[ " + tag + "] "+ " Line: " + getLineNumber() + " : " + msg;
	
	}
	
	private static int getLineNumber() {
		return Thread.currentThread().getStackTrace()[5].getLineNumber();
	}
}
