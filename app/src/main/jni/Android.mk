LOCAL_PATH := $(call my-dir)

APP_CPPFLAGS += -std=c++11

include $(CLEAR_VARS)

LOCAL_MODULE := JniLib
LOCAL_SRC_FILES =: JniLib.cpp NumTest.cpp
include $(BUILD_SHARED_LIBRARY)

