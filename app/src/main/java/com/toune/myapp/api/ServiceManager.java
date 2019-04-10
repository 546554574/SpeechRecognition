package com.toune.myapp.api;

import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;
import com.toune.myapp.helper.ResponseData;
import com.toune.myapp.ui.model.TestVo;
import com.toune.myapp.helper.JsonConvert;
import com.toune.myapp.helper.LzyResponse;

import io.reactivex.Observable;

public class ServiceManager {
    public static final String Token = "";
    public static String BaseUrl = "http://pay-forum.zydl-tec.cn";
    public static final String TestUrl = BaseUrl + "/v1/app/videos/list";

    public static Observable<ResponseData<TestVo>> getVideo() {
        return OkGo.<ResponseData<TestVo>>get(TestUrl)
                .converter(new JsonConvert<ResponseData<TestVo>>() {
                })
                .adapt(new ObservableBody<ResponseData<TestVo>>());
    }
}
