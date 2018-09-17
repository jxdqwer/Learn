package cn.softsum.base;

import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

/**
 * @author jxd
 * @date 2018/8/30-16:28
 * @blog www.softsum.cn
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends AppCompatActivity {

    @Inject
    protected P mPresenter;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.onDestroy();
        }
        this.mPresenter = null;
    }
}
