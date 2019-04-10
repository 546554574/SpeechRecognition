//package com.toune.myapp.base;
//
//import com.lzy.okgo.OkGo;
//import com.lzy.okgo.model.HttpHeaders;
//import com.lzy.okgo.model.HttpParams;
//import com.toune.util.rxtool.RxDataTool;
//
//import java.util.Map;
//
//
///**
// * Created by Administrator on 2018/4/3 0003.
// */
//
//public class RequstUtils {
//
//    /**
//     * 默认调用，默认添加baseUrl,添加token
//     *
//     * @param url
//     * @param noHttpCallBack
//     */
//    public static <T> void GET(String url, NoHttpCallBack noHttpCallBack) {
//        GET(url, true, null, noHttpCallBack);
//    }
//
//    /**
//     * 默认调用，默认添加baseUrl,添加token
//     *
//     * @param url
//     * @param noHttpCallBack
//     */
//    public static <T> void GET(String url, HttpParams params, NoHttpCallBack noHttpCallBack) {
//        GET(url, true, params, noHttpCallBack);
//    }
//
//    /**
//     * @param url
//     * @param isAdd
//     * @param noHttpCallBack
//     */
//    public static void GET(String url, boolean isAdd, HttpParams params,, NoHttpCallBack noHttpCallBack) {
//        GET(url, true, isAdd, params, noHttpCallBack);
//    }
//
//    /**
//     * @param url            请求链接
//     * @param isAddToken     是否提交token header
//     * @param isAdd          是否加baseURL
//     * @param noHttpCallBack 返回
//     */
//    public static void GET(String url, boolean isAddToken, boolean isAdd, HttpParams params,, NoHttpCallBack noHttpCallBack) {
//        if (isAdd) {
//            url = Api.BaseUrl + url;
//        }
//        HttpHeaders httpHeaders = new HttpHeaders();
//        if (!RxDataTool.isEmpty(Api.Token) && isAddToken) {
//            httpHeaders.put("token", Api.Token);
//        }
//        OkGo.get(url).headers(httpHeaders).params(params).call();
//        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.GET);
//        RequestQueue queue = MyApp.getmRequestQueue();
//
//        request.add(NoHttpCallBack.params);
//        queue.add(0, request, noHttpCallBack);
//    }
//
//
//    public static void POST(String url, NoHttpCallBack noHttpCallBack) {
//        POST(url, true, noHttpCallBack);
//    }
//
//    public static void POST(String url, boolean isAdd, NoHttpCallBack noHttpCallBack) {
//        POST(url, true, isAdd, noHttpCallBack);
//    }
//
//    /**
//     * @param url            请求链接
//     * @param isAddToken     是否提交token header
//     * @param isAdd          是否加baseURL
//     * @param noHttpCallBack 返回
//     */
//    public static void POST(String url, boolean isAddToken, boolean isAdd, NoHttpCallBack noHttpCallBack) {
//        if (isAdd) {
//            url = Api.BaseUrl + url;
//        }
//        RequestQueue requestQueue = MyApp.getmRequestQueue();
//        final Request<String> request = NoHttp.createStringRequest(url,
//                RequestMethod.POST);
//        if (!RxDataTool.isEmpty(Api.Token) && isAddToken) {
//            request.addHeader("token", Api.Token);
//        }
////        request.setContentType("multipart/form-data");
//        request.add(NoHttpCallBack.params);
//        /**
//         *  what:请求的标识
//         *  request：请求
//         *  response:请求的回调监听
//         */
//        requestQueue.add(0, request, noHttpCallBack);
//    }
//}
