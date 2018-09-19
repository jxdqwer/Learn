package cn.softsum.base;

import android.content.Context;

/**
 * @author jxd
 * @date 2018/8/30-16:28
 * @blog www.softsum.cn
 */
public abstract class BasePresenter<M extends BaseModel, V extends BaseView> implements IPresenter {


    protected M mModel;

    protected V mView;

    public BasePresenter(M model, V view) {
        mModel = model;
        mView = view;
    }

    public BasePresenter(V view) {
        mView = view;
    }

    @Override
    public void onDestroy() {
        if (mModel != null) {
            mModel.onDestroy();
        }
        this.mModel = null;
        this.mView = null;
    }

    public Context getViewContext() {
        return null;
    }
}
