package cn.softsum.base;

import android.support.v4.app.Fragment  ;

import javax.inject.Inject;

/**
 * @author jxd
 * @date 2018/8/30-21:21
 * @blog www.softsum.cn
 */
public abstract class BaseMvpFragment <P extends BasePresenter> extends Fragment {

    @Inject
    protected P mPresenter;

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.onDestroy();
        }
        this.mPresenter = null;
    }
}
