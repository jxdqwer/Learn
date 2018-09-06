package com.softsum.jxd.learn.dynamic.impl;

import com.softsum.jxd.learn.dynamic.DynamicTestIn;

/**
 * @author jxd
 * @date 2018/9/5-16:39
 * @blog www.softsum.cn
 */
public class DynamicDexTest implements DynamicTestIn {
    @Override
    public String DynamicDexTestCode(){
        return new StringBuilder(getClass().getName()).append("Hello Dynamic").toString();
    }
}
