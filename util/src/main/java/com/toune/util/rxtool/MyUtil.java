package com.toune.util.rxtool;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;


import com.toune.util.rxtool.view.RxToast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Administrator on 2018/5/2.
 */

public class MyUtil {

    public static File getSaveFile(Context context) {
        File file = new File(context.getFilesDir(), "pic.jpg");
        return file;
    }

    /**
     * 跳转页面
     *
     * @param context
     * @param classz
     */
    public static void startActivity(Context context, Class classz) {
        RxActivityTool.skipActivity(context, classz);
    }

    /**
     * 跳转页面并关闭当前页面
     *
     * @param context
     * @param classz
     */
    public static void startActivityAndFinish(Context context, Class classz) {
        RxActivityTool.skipActivityAndFinish(context, classz);
    }

    /**
     * 跳转页面带参数
     *
     * @param context
     * @param classz
     * @param bundle
     */
    public static void startActivity(Context context, Class classz, Bundle bundle) {
        RxActivityTool.skipActivity(context, classz, bundle);
    }

    /**
     * 跳转页面带参数并关闭当前页面
     *
     * @param context
     * @param classz
     * @param bundle
     */
    public static void startActivityAndFinish(Context context, Class classz, Bundle bundle) {
        RxActivityTool.skipActivityAndFinish(context,classz,bundle);
    }

    /**
     * 多颜色文本显示
     */
    public static Spanned getMoreStyleText(String[] strings, String[] colors) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            stringBuilder.append("<font color=\"" + colors[i] + "\">" + strings[i] + "</font>");
        }
        return Html.fromHtml(stringBuilder.toString());
    }

    /**
     * 从asset路径下读取对应json文件转String输出
     *
     * @param mContext
     * @return
     */
    public static String getJsonFileFromAssets(Context mContext, String fileName) {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();
        AssetManager am = mContext.getAssets();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    am.open(fileName)));
            String next = "";
            while (null != (next = br.readLine())) {
                sb.append(next);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            sb.delete(0, sb.length());
        }
        return sb.toString().trim();
    }

    /**
     * 获取ASSETS文件夹下的路径，只是基本路径
     *
     * @param fileName 这是文件的绝对路径 如html/index.html
     * @return 返回一个可以直接loadUrl的路径
     */
    public static String getHtmlFilePathFromAssets(String fileName) {
        String path = "";
        path = "file:///android_asset/" + fileName;
        return path;
    }

    /**
     * 对double类型的数值保留指定位数的小数。<br>
     * 该方法舍入模式：向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。<br>
     * <b>注意：</b>如果精度要求比较精确请使用 keepPrecision(String number, int precision)方法
     *
     * @param number    要保留小数的数字
     * @param precision 小数位数
     * @return double 如果数值较大，则使用科学计数法表示
     */
    public static double keepPrecision(double number, int precision) {
        BigDecimal bg = new BigDecimal(number);
        return bg.setScale(precision, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 对float类型的数值保留指定位数的小数。<br>
     * 该方法舍入模式：向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。<br>
     * <b>注意：</b>如果精度要求比较精确请使用 keepPrecision(String number, int precision)方法
     *
     * @param number    要保留小数的数字
     * @param precision 小数位数
     * @return float 如果数值较大，则使用科学计数法表示
     */
    public static float keepPrecision(float number, int precision) {
        BigDecimal bg = new BigDecimal(number);
        return bg.setScale(precision, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 调用系统浏览器
     *
     * @param context
     * @param url
     */
    public static void intentToLocalChrom(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }

    /**
     * 截图照片存放路径
     *
     * @return
     */
    public static String getCapturePicPath() {
        return getBasePath() + RxTimeTool.getCurTimeMills() + ".png";
    }

    /**
     * 截图照片存放路径
     *
     * @return
     */
    public static String getBasePath() {
        return RxFileTool.getSDCardPath() + "zydl/kszs/";
    }

    /**
     * 录像存放路径
     *
     * @return
     */
    public static String getRecordVideoPath() {
        return getBasePath() + RxTimeTool.getCurTimeMills() + ".mp4";
    }

    /**
     * 调用拨号界面
     * 不拨打电话
     *
     * @param phone 电话号码
     */
    public static void callPhone(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone.trim()));
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 跳过拨号界面
     * 直接拨打电话
     *
     * @param phone 电话号码
     */
    @SuppressLint("MissingPermission")
    public static void callAndPhone(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        context.startActivity(intent);
    }


    /**
     * 获取视频文件截图
     *
     * @param path 视频文件的路径
     * @return Bitmap 返回获取的Bitmap
     */

    public static Bitmap getVideoThumb(String path) {

        MediaMetadataRetriever media = new MediaMetadataRetriever();

        media.setDataSource(path);

        return media.getFrameAtTime();

    }

    /**
     * 倒计时
     *
     * @param textView
     * @param waitTime
     * @param interval
     * @param hint
     */
    public static void countDown(final TextView textView, long waitTime, long interval, final String hint) {
        textView.setEnabled(false);
        CountDownTimer timer = new CountDownTimer(waitTime, interval) {
            @SuppressLint({"DefaultLocale"})
            public void onTick(long millisUntilFinished) {
                textView.setText(String.format("%d S", millisUntilFinished / 1000L));
            }

            public void onFinish() {
                textView.setEnabled(true);
                textView.setText(hint);
            }
        };
        timer.start();
    }

    /**
     * 获取本手机的DPI
     *
     * @param activity
     * @return
     */
    public static int getDensity(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return (int) dm.xdpi;
    }

    public static String getRealPathFromURI(Context context, Uri contentURI) {
        String result;
        Cursor cursor = context.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }


    /**
     * 调用第三方导航，一键导航
     */
    /**
     * 我的位置到终点
     */
    public static void setUpMapByMine(Context context, String name, String lat, String lon) {
        //先进行高德地图的判断
        /**
         * 我的位置BY高德
         */
        try {
            Intent intent = Intent.getIntent("androidamap://route?sourceApplication=softname&sname=我的位置&dlat=" + lat + "&dlon=" + lon + "&dname=" + name + "&dev=0&m=0&t=0");
            if (RxAppTool.isInstallApp(context, "com.autonavi.minimap")) {
                context.startActivity(intent);
                return;
            } else {
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        //如果没有高德地图，再进行百度地图判断
        try {
            Intent intent = Intent.getIntent("intent://map/direction?origin=我的位置&destination=" + name + "&mode=driving&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            if (RxAppTool.isInstallApp(context, "com.baidu.BaiduMap")) {
                context.startActivity(intent);
                return;
            } else {
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        RxToast.info("您手机上没有地图软件");
    }

}
