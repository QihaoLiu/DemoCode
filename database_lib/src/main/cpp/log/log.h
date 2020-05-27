//
// @author LeoLiu
//

#ifndef DEMO_CODE_LOG_H
#define DEMO_CODE_LOG_H

#include <android/log.h>
#include <string.h>

#define DEBUG

#define TAG "sqlite_log"

#ifdef DEBUG
#define LOGV(format, ...) __android_log_print(ANDROID_LOG_VERBOSE, TAG,"[%d]: " format,  __LINE__, ##__VA_ARGS__);
#define LOGD(format, ...) __android_log_print(ANDROID_LOG_DEBUG, TAG,"[%d]: " format,  __LINE__, ##__VA_ARGS__);
#define LOGI(format, ...) __android_log_print(ANDROID_LOG_INFO, TAG,"[%d]: " format,  __LINE__, ##__VA_ARGS__);
#define LOGW(format, ...) __android_log_print(ANDROID_LOG_WARN, TAG,"[%d]: " format,  __LINE__, ##__VA_ARGS__);
#define LOGE(format, ...) __android_log_print(ANDROID_LOG_ERROR, TAG,"[%d]: " format,  __LINE__, ##__VA_ARGS__);
#else
#define LOGV(format, ...);
#define LOGD(format, ...);
#define LOGI(format, ...) __android_log_print(ANDROID_LOG_INFO, TAG,"[%d]: " format,  __LINE__, ##__VA_ARGS__);
#define LOGW(format, ...) __android_log_print(ANDROID_LOG_WARN, TAG,"[%d]: " format,  __LINE__, ##__VA_ARGS__);
#define LOGE(format, ...) __android_log_print(ANDROID_LOG_ERROR, TAG,"[%d]: " format,  __LINE__, ##__VA_ARGS__);
#endif

#endif //DEMO_CODE_LOG_H
