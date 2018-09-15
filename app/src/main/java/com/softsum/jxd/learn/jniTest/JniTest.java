package com.softsum.jxd.learn.jniTest;

/**
 * @author jxd
 * @date 2018/9/14-11:48
 * @blog www.softsum.cn
 */
public class JniTest {
    static {
        System.loadLibrary("JniLib");
    }

    public native String getString();
}
