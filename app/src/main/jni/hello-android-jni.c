#include <jni.h>

JNIEXPORT jstring JNICALL
Java_hu_rozsa_daniel_learningapplication_ninth_ExampleNativeFragment_getMsgFromJni(JNIEnv *env,
                                                                                jobject instance) {


    return (*env)->NewStringUTF(env, "Random string from Native script");
}

JNIEXPORT jstring JNICALL
Java_hu_rozsa_daniel_learningapplication_ninth_ExampleNativeFragment_getSecondNativeString(
        JNIEnv *env, jobject instance) {

    return (*env)->NewStringUTF(env, "Second native String");
}

JNIEXPORT jstring JNICALL
Java_hu_rozsa_daniel_learningapplication_ninth_ExampleNativeFragment_getRandomString(JNIEnv *env,
                                                                                     jobject instance) {


    return (*env)->NewStringUTF(env, returnValue);
}