package com.softsum.jxd.learn.FileSystemTese;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.softsum.jxd.learn.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileSystemActivity extends AppCompatActivity implements View.OnClickListener{

    private String TAG = "FileSystemActivity";
    private TextView myTextView;
    private EditText myEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_system);
        Button buttonNewFile = findViewById(R.id.file_button_1);
        Button buttonReadFile = findViewById(R.id.file_button_2);
        Button buttonDeleteFIce = findViewById(R.id.file_button_3);
        Button buttonFileList = findViewById(R.id.file_button_4);

        buttonNewFile.setOnClickListener(this);
        buttonReadFile.setOnClickListener(this);
        buttonDeleteFIce.setOnClickListener(this);
        buttonFileList.setOnClickListener(this);

        myTextView = (TextView)findViewById(R.id.file_text_view);
        myEditText = (EditText)findViewById(R.id.file_edit);
        myTextView.setText("我是个TextView");
    }

    @Override
    public void onClick(View v) {
        OutputStream out;
        InputStream in;
        File cacheDir = getCacheDir(this);
        String filePath = cacheDir.getAbsolutePath() + File.separator + myEditText.getText();
        switch (v.getId()){
            case R.id.file_button_1:
                //新建文件
                File newFile = new File(filePath);
                try {
                    if(!newFile.exists()){
                        newFile.createNewFile();
                        out = new FileOutputStream(newFile.getAbsolutePath());
                        String s = "我只是一个文件" + filePath;
                        byte[] a = s.getBytes();
                        out.write(a);
                        out.close();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "file path: " + filePath);
                break;
            case R.id.file_button_2:
                //读取文件
                File readFile = new File(filePath);
                try {
                    if(readFile.exists()){
                        in = new FileInputStream(readFile.getAbsolutePath());
                        byte a[] = new byte[0];
                        in.read(a);
                        String b = new String(a);
                        myTextView.setText(filePath + ":" + b);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "file path: " + filePath);

                break;
            case R.id.file_button_3:
                String cachePath = cacheDir.getAbsolutePath();
                File pathFile = new File(cachePath);
                File[] file = pathFile.listFiles();
                String fileList = new String();
                for (File tmpFile:file){
                    String tmp = tmpFile.getAbsolutePath();
                    fileList += tmp + "\n";
                }
                myTextView.setText(fileList);

                break;
            case R.id.file_button_4:
                //删除文件
                File deleteFile = new File(filePath);
                if(deleteFile.exists()){
                    deleteFile.delete();
                    myTextView.setText("删除文件" +filePath);
                }
                Log.d(TAG, "file path: " + filePath);

                break;
            default:
        }
    }


    /**
     * 获取缓存路径
     *
     * @param context
     * @return 返回缓存文件路径
     */
    public static File getCacheDir(Context context) {
        File cache;
        if (hasExternalStorage()) {
            cache = context.getExternalCacheDir();
        } else {
            cache = context.getCacheDir();
        }
        if (!cache.exists())
            cache.mkdirs();
        return cache;
    }

    public static boolean hasExternalStorage() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
