package com.yanglin.demo2.utils;

import android.Manifest;
import android.util.Log;

import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;


/**
 * Created by jess on 17/10/2016 10:09
 * 由于出来Android6.0之后版本的动态权限问题
 *
 * 示例:
 *
 PermissionUtil.externalStorage(new PermissionUtil.RequestPermission() {
public void onRequestPermissionSuccess() {
//request permission success, do something.
}

public void onRequestPermissionFailure() {
mRootView.showMessage("Request permissons failure");
}
}, mRootView.getRxPermissions());

 */

public class PermissionUtil {
    public static final String TAG = "Permission";


    private PermissionUtil() {
    }

    public interface RequestPermission {
        void onRequestPermissionSuccess();

        void onRequestPermissionFailure();
    }


    public static void requestPermission(final RequestPermission requestPermission, RxPermissions rxPermissions,  String... permissions) {
        if (permissions == null || permissions.length == 0) return;

        List<String> needRequest = new ArrayList<>();
        for (String permission : permissions) { //过滤调已经申请过的权限
            if (!rxPermissions.isGranted(permission)) {
                needRequest.add(permission);
            }
        }

        if (needRequest.size() == 0) {//全部权限都已经申请过，直接执行操作
            requestPermission.onRequestPermissionSuccess();
        } else {//没有申请过,则开始申请
            rxPermissions
                    .request(needRequest.toArray(new String[needRequest.size()]))
                    .subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean granted) {
                            if (granted) {
                                Log.d(TAG,"Request permissons success");
                                requestPermission.onRequestPermissionSuccess();
                            } else {
                                Log.d(TAG,"Request permissons failure");
                                requestPermission.onRequestPermissionFailure();
                            }
                        }
                    });
        }

    }


    /**
     * 请求摄像头权限
     */
    public static void launchCamera(RequestPermission requestPermission, RxPermissions rxPermissions) {
        requestPermission(requestPermission, rxPermissions, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA);
    }


    /**
     * 请求外部存储的权限
     */
    public static void externalStorage(RequestPermission requestPermission, RxPermissions rxPermissions) {
        requestPermission(requestPermission, rxPermissions, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }


    /**
     * 请求发送短信权限
     */
    public static void sendSms(RequestPermission requestPermission, RxPermissions rxPermissions) {
        requestPermission(requestPermission, rxPermissions, Manifest.permission.SEND_SMS);
    }


    /**
     * 请求打电话权限
     */
    public static void callPhone(RequestPermission requestPermission, RxPermissions rxPermissions) {
        requestPermission(requestPermission, rxPermissions,Manifest.permission.CALL_PHONE);
    }


    /**
     * 请求获取手机状态的权限
     */
    public static void readPhonestate(RequestPermission requestPermission, RxPermissions rxPermissions) {
        requestPermission(requestPermission, rxPermissions, Manifest.permission.READ_PHONE_STATE);
    }

}

