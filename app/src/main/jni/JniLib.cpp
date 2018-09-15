
#include "com_softsum_jxd_learn_jniTest_JniTest.h"
#include "NumTest.h"
#include <iostream>
#include <sstream>

using namespace std;

JNIEXPORT jstring JNICALL Java_com_softsum_jxd_learn_jniTest_JniTest_getString
    (JNIEnv * env, jobject obj){
    NumTest * test = new NumTest;
    float b = test->getNumTest(78);
    delete test;
    stringstream str;
    str << b;
    string s = "计算结果：" + str.str();
    return (*env).NewStringUTF(s.c_str());
}

