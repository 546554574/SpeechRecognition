package com.toune.myapp.ui.view;

import com.toune.myapp.base.BaseView;
import com.toune.myapp.ui.model.TestVo;

public interface MainView extends BaseView {
    void getVideoList(TestVo testVo);
}
