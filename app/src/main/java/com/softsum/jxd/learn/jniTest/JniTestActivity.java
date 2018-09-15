package com.softsum.jxd.learn.jniTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.softsum.jxd.learn.R;

public class JniTestActivity extends AppCompatActivity implements View.OnClickListener{

    private String TAG = "JniTestActivity";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni_test);
        mTextView = (TextView)findViewById(R.id.jni_test_text_view);
        Button jniButton = findViewById(R.id.jni_test_button);
        jniButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jni_test_button:
                mTextView.setText(new JniTest().getString());
                break;
            default:
        }
    }
}
