package com.softsum.jxd.learn.dagger2Test;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.softsum.jxd.learn.R;
import com.softsum.jxd.learn.dagger2Test.daggerDemo.Eat;
import com.softsum.jxd.learn.dagger2Test.daggerDemo.Human;
import com.softsum.jxd.learn.dagger2Test.di.CheckVersionModule;
import com.softsum.jxd.learn.dagger2Test.di.DaggerCheckVersionComponent;
import com.yanzhenjie.nohttp.NoHttp;

import javax.inject.Inject;

import cn.softsum.base.BaseMvpActivity;

public class DaggerTestActivity extends BaseMvpActivity<CheckVersionPresenter> implements CheckVersionContract.ICheckVersionView , View.OnClickListener{

    private EditText editText;
    private TextView textView;
    private Button button;
    private ProgressDialog progressDialog;

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degger_test);

        Human human = new Human();
        human.action();

        NoHttp.initialize(this);
        mContext = this;
        Log.d("DaggerTestActivity", "onCreate: thread");
        textView = findViewById(R.id.version_text);
        editText = findViewById(R.id.version_url_edit_text);
        editText.setText("请输入url");
        button = findViewById(R.id.check_version_button);
        button.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("加载中");

        DaggerCheckVersionComponent.builder().
                checkVersionModule(new CheckVersionModule(this)).
                build().
                inject(this);



    }


    @Override
    public void showVersion(String version) {
        textView.setText(version);
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public String getSerUrl() {
        return editText.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_version_button:
                    mPresenter.getVersion();
                break;
            default:
        }
    }
}
