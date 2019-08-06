package com.luhaibing.gradle_study;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static android.content.pm.PackageManager.GET_META_DATA;
import static com.luhaibing.gradle_study.R.id.meta;
import static com.luhaibing.gradle_study.R.id.txt;

public class MainActivity extends AppCompatActivity {

    private TextView mTxt;
    private TextView mMeta;
    private TextView mJni_txt;
    private String space = "\n";
    private static final String KEY = "value";

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mTxt = (TextView) findViewById(txt);
        this.mMeta = (TextView) findViewById(meta);
        this.mJni_txt = (TextView) findViewById(R.id.jni_txt);
        String info = null;
        info = "BUILD_TYPE : " + BuildConfig.BUILD_TYPE + space +
                "APPLICATION_ID : " + BuildConfig.APPLICATION_ID + space +
                "DEBUG : " + BuildConfig.DEBUG + space +
                "FLAVOR : " + BuildConfig.FLAVOR + space +
                "jave .result -->> : " + TypeClass.add(100, 9) + space +
                "Resources : " + getResources().getString(R.string.date);
        mTxt.setText(info);
        mJni_txt.setText(stringFromJNI());

//        ActivityInfo info=this.getPackageManager()
//                .getActivityInfo(getComponentName(),
//                        PackageManager.GET_META_DATA);
//        String msg =info.metaData.getString("data_Name");
//        Log.d(TAG, " msg == " + msg );
        String mMetaInfo = null;
        try {
            PackageManager packageManager = this.getPackageManager();

            ApplicationInfo applicationInfo = packageManager
                    .getApplicationInfo(getPackageName(), GET_META_DATA);
            Object applicationMeta = applicationInfo.metaData.get(KEY);
            mMetaInfo = "ApplicationInfo : " + String.valueOf(applicationMeta) + space;

            ActivityInfo activityInfo = packageManager
                    .getActivityInfo(getComponentName(), GET_META_DATA);
            Bundle metaData = activityInfo.metaData;
            Object mActivityMeta = metaData.get(KEY);
            mMetaInfo += "ActivityInfo : " + String.valueOf(mActivityMeta) + space;

            ComponentName otherActivityName = new ComponentName(this, OtherActivity.class);
            ActivityInfo otherActivityInfo = packageManager
                    .getActivityInfo(otherActivityName, GET_META_DATA);
            Bundle otherMetaData = otherActivityInfo.metaData;
            Object mOtherActivityMeta = otherMetaData.get(KEY);
            mMetaInfo += "OtherActivity : " + String.valueOf(mOtherActivityMeta) + space;

            ComponentName serviceName = new ComponentName(this, MyIntentService.class);
            ServiceInfo serviceInfo = packageManager.getServiceInfo(serviceName, GET_META_DATA);
            Object ServiceMeta = serviceInfo.metaData.get(KEY);
            mMetaInfo += "ServiceInfo : " + String.valueOf(ServiceMeta) + space;

            ComponentName receiverName = new ComponentName(this, MyReceiver.class);
            ActivityInfo receiverInfo = packageManager.getReceiverInfo(receiverName, GET_META_DATA);
            Object receiverMeta = receiverInfo.metaData.get(KEY);
            mMetaInfo += "ReceiverInfo : " + String.valueOf(receiverMeta) + space;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        mMeta.setText(mMetaInfo);

        String packageName = getPackageName();
        System.out.println("packageName -->>> " + packageName);
        mDefault = getComponentName();
        mOne = new ComponentName(
                getBaseContext(),
                "com.luhaibing.gradle_study.One");
//                packageName+".One");
        mTwo = new ComponentName(
                getBaseContext(),
                "com.luhaibing.gradle_study.Two");
//                packageName+".Two");
        mPm = getApplicationContext().getPackageManager();

    }

    private ComponentName mDefault;
    private ComponentName mOne;
    private ComponentName mTwo;
    private PackageManager mPm;


    public void click(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public void onDefault(View view) {
        disableComponent(mOne);
        disableComponent(mTwo);
        enableComponent(mDefault);
    }

    public void onOne(View view) {
        disableComponent(mDefault);
        disableComponent(mTwo);
        enableComponent(mOne);
    }

    public void onTwo(View view) {
        disableComponent(mDefault);
        disableComponent(mOne);
        enableComponent(mTwo);
    }

    private void enableComponent(ComponentName componentName) {
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    private void disableComponent(ComponentName componentName) {
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

}