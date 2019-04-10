package com.toune.myapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.rxbus.RxBus;
import com.bumptech.glide.Glide;
import com.toune.myapp.R;
import com.toune.myapp.base.BaseActivity;
import com.toune.myapp.ui.model.TestVo;
import com.toune.myapp.ui.presenter.MainActivityPresenter;
import com.toune.myapp.ui.view.MainView;
import com.toune.util.rxtool.view.RxToast;
import com.toune.util.rxview.dialog.RxDialogSureCancel;
import com.zhy.changeskin.SkinManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainView, MainActivityPresenter> implements MainView {

    @BindView(R.id.img_iv)
    ImageView imgIv;
    @BindView(R.id.text_tv)
    TextView textTv;

    @Override
    public MainActivityPresenter initPresenter() {
        return new MainActivityPresenter();
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void refreData() {
        mPresenter.getVideoList();
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mPresenter.getVideoList();
        RxBus.getDefault().subscribe(this, new RxBus.Callback<String>() {
            @Override
            public void onEvent(String s) {
                textTv.setText(s);
                SkinManager.getInstance().changeSkin("night");
            }
        });
        imgIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(context);
                rxDialogSureCancel.setCancel("哈哈哈");
                rxDialogSureCancel.setContent("点击呵呵呵试试");
                rxDialogSureCancel.setSure("呵呵呵");
                rxDialogSureCancel.setSureListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rxDialogSureCancel.dismiss();
                        SkinManager.getInstance().removeAnySkin();
                    }
                });
                rxDialogSureCancel.setCancelListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rxDialogSureCancel.dismiss();
                        RxBus.getDefault().post("xxxxxx");
                    }
                });
                rxDialogSureCancel.show();
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected String getTitleStr() {
        return "Main";
    }

    @Override
    protected void initEventAndData() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void getVideoList(TestVo testVo) {
//        Glide.with(context).load(testVo.getData().get(0).getVideos().getCover()).into(imgIv);
    }
}
