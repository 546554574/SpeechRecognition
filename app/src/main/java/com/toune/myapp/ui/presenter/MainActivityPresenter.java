package com.toune.myapp.ui.presenter;

import com.toune.myapp.api.ServiceManager;
import com.toune.myapp.base.BasePresenterImpl;
import com.toune.myapp.helper.rxjavahelper.RxObserver;
import com.toune.myapp.helper.rxjavahelper.RxResultHelper;
import com.toune.myapp.helper.rxjavahelper.RxSchedulersHelper;
import com.toune.myapp.ui.model.TestVo;
import com.toune.myapp.ui.view.MainView;

import io.reactivex.disposables.Disposable;

public class MainActivityPresenter extends BasePresenterImpl<MainView> {

    public void getVideoList() {
        ServiceManager.getVideo()
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<TestVo>() {
                    @Override
                    public void _onSubscribe(Disposable d) {
                        getView().showLoading();
                    }

                    @Override
                    public void _onNext(TestVo testVo) {
                        getView().getVideoList(testVo);
                    }

                    @Override
                    public void _onError(String errorMessage) {

                    }

                    @Override
                    public void _onComplete() {
                        getView().hideLoading();
                    }
                });
    }
}
