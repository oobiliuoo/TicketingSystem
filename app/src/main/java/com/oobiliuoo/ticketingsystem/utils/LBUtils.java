package com.oobiliuoo.ticketingsystem.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.oobiliuoo.ticketingsystem.data.OrderInfo;
import com.oobiliuoo.ticketingsystem.data.UserInfo;

import org.litepal.LitePal;

import java.util.Hashtable;

/**
 * @author biliu
 * 工具类
 */
public class LBUtils {

    /**服务器IP地址*/
    public static final String IP_ADDRESS = "192.168.43.239";
    /**服务器开放的端口号*/
    public static final int IP_PORT = 8777;

    /** DIVISION: 分割符，根据这个拆分字符串 */
    public static final String DIVISION = "::";

    /** REQUEST_LOGIN: 请求登录*/
    public static final String REQUEST_LOGIN = "LOGIN::";
    /** REQUEST_REGISTER: 请求注册*/
    public static final String REQUEST_REGISTER = "REGISTER::";
    /** REQUEST_ADD_ORDER: 请求下单 */
    public static final String REQUEST_ADD_ORDER = "ADD_ORDER::";


    /** RESPOND_ACCESS_LOGIN： 准许登陆 */
    public static final String RESPOND_ACCESS_LOGIN = "ACCESS_LOGIN";
    /** RESPOND_REGISTER_OK： 注册成功*/
    public static final String RESPOND_REGISTER_OK = "REGISTER_OK";
    /** RESPOND_ADD_ORDER_OK: 下单成功*/
    public static final String RESPOND_ADD_ORDER_OK = "ADD_ORDER_OK";



    /**
     *  相当于 log.i("mLog1",text)
     *  text: 想要显示的文字
     * */
    public static void mLog1(String text){
        Log.i("mLog", text);
    }
    /**
     *  相当于 log.i(TAG,text)
     *  TAG: TAG
     *  text: 想要显示的文字
     * */
    public static void mLog1(String TAG ,String text){
        Log.i(TAG, text);
    }

    /**
     *  显示Toast
     * */
    public static void showToast(Context context, String text){

        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }

    /**
     *  通过Handler传递消息
     * */
    public static void sendMessage(Handler handler, int what, Object obj){
        Message message = Message.obtain();
        message.what = what;
        message.obj = obj;
        handler.sendMessage(message);
    }

    /**
     *  判断是否为纯数字
     *  str:想判断的字符串
     *  return: boolean
     * */
    public static boolean isNumeric(String str) {
        for(int i =0;i<str.length();i++){
            if(!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     *  从SharePreferences中读取当前登录帐号
     *  context: 当前活动上下文
     *  return: 当前帐号
     * */
    public static String readCurrentUser(Context context){
        SharedPreferences pref = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        String tel = pref.getString("tel","");
        return tel;
    }

    public static void resetUserInfo(){
        LitePal.deleteAll(UserInfo.class);
    }
    public static void resetOrderInfo(){
        LitePal.deleteAll(OrderInfo.class);
    }

    /**
     * 生成简单二维码
     *
     * @param content                字符串内容
     * @param width                  二维码宽度
     * @param height                 二维码高度
     * @param character_set          编码方式（一般使用UTF-8）
     * @param error_correction_level 容错率 L：7% M：15% Q：25% H：35%
     * @param margin                 空白边距（二维码与边框的空白区域）
     * @param color_black            黑色色块
     * @param color_white            白色色块
     * @return BitMap
     */
    public static Bitmap createQRCodeBitmap(String content, int width, int height,
                                            String character_set, String error_correction_level,
                                            String margin, int color_black, int color_white) {
        // 字符串内容判空
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        // 宽和高>=0
        if (width < 0 || height < 0) {
            return null;
        }
        try {
            /** 1.设置二维码相关配置 */
            Hashtable<EncodeHintType, String> hints = new Hashtable<>();
            // 字符转码格式设置
            if (!TextUtils.isEmpty(character_set)) {
                hints.put(EncodeHintType.CHARACTER_SET, character_set);
            }
            // 容错率设置
            if (!TextUtils.isEmpty(error_correction_level)) {
                hints.put(EncodeHintType.ERROR_CORRECTION, error_correction_level);
            }
            // 空白边距设置
            if (!TextUtils.isEmpty(margin)) {
                hints.put(EncodeHintType.MARGIN, margin);
            }
            /** 2.将配置参数传入到QRCodeWriter的encode方法生成BitMatrix(位矩阵)对象 */
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            /** 3.创建像素数组,并根据BitMatrix(位矩阵)对象为数组元素赋颜色值 */
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    //bitMatrix.get(x,y)方法返回true是黑色色块，false是白色色块
                    if (bitMatrix.get(x, y)) {
                        pixels[y * width + x] = color_black;//黑色色块像素设置
                    } else {
                        pixels[y * width + x] = color_white;// 白色色块像素设置
                    }
                }
            }
            /** 4.创建Bitmap对象,根据像素数组设置Bitmap每个像素点的颜色值,并返回Bitmap对象 */
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

}
