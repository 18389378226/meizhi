package com.chensen.meizhi.mvp.contact;

import com.chensen.meizhi.bean.GirlBean;
import com.chensen.meizhi.common.base.BasePresent;
import com.chensen.meizhi.common.base.BaseView;

import java.util.List;

/**
 * author：chensen on 2016/11/25 16:22
 * desc：
 */

public class GirlContact {

    public interface View extends BaseView {

        void showContent(List<GirlBean> data);

        void showMoreGirl(List<GirlBean> data);

    }

    public interface Present extends BasePresent {
        void getGirlList();

        void getMoreGirl();

    }


}
