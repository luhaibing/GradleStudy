#include <jni.h>
#include <string>

extern "C"
jstring
/*  eg:全类名:com.luhaibing.gradle_practice_as_test.MainActivity(stringFromJNI)
         Java_com_luhaibing_gradle_1practice_1as_1test_MainActivity_stringFromJNI */
/* com.luhaibing.gradle_practice */
Java_com_luhaibing_gradle_1study_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
