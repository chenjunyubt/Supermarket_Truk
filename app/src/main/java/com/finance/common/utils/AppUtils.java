package com.finance.common.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcelable;

import com.finance.core.TTApplication;
import com.supermarket.R;

//跟App相关的辅助类
public class AppUtils {

    private AppUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName() {
        Context context = TTApplication.getAppContext();
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @return 当前应用的版本名称
     */
    public static String getVersionName() {
        Context context = TTApplication.getAppContext();
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * [获取应用程序版本号信息]
     *
     * @return 当前应用的版本号
     */
    public static int getVersionCode() {
        Context context = TTApplication.getAppContext();
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

//    public static String getAppName() {
//        String applicationName = "";
//        try {
//            PackageManager pm = TTApplication.getAppContext().getPackageManager();
//            PackageInfo pi = pm.getPackageInfo(TTApplication.getAppContext().getPackageName(), 0);
//            applicationName = pi.applicationInfo.loadLabel(pm).toString();
//        } catch (NameNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        return applicationName;
//    }

    /**
     * 判断桌面是否已添加快捷方式
     *
     * @return
     */
    public static boolean hasShortcut() {
        boolean result = false;
        // 获取当前应用名称
        String title = null;
        try {
            final PackageManager pm = TTApplication.getAppContext().getPackageManager();
            title = pm.getApplicationLabel(pm.getApplicationInfo(TTApplication.getAppContext().getPackageName(), PackageManager.GET_META_DATA)).toString();
        } catch (Exception e) {
        }

        final String uriStr;
        if (android.os.Build.VERSION.SDK_INT < 8) {
            uriStr = "content://com.android.launcher.settings/favorites?notify=true";
        } else {
            uriStr = "content://com.android.launcher2.settings/favorites?notify=true";
        }
        final Uri CONTENT_URI = Uri.parse(uriStr);
        final Cursor c = TTApplication.getAppContext().getContentResolver().query(CONTENT_URI, null, "title=?", new String[]{title}, null);
        if (c != null && c.getCount() > 0) {
            result = true;
        }
        return result;
    }

    /**
     * 为当前应用添加桌面快捷方式
     *
     */
    public static void addShortcut() {
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");

        Intent shortcutIntent = TTApplication.getAppContext().getPackageManager().getLaunchIntentForPackage(TTApplication.getAppContext().getPackageName());
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        // 获取当前应用名称
        String title = null;
        try {
            final PackageManager pm = TTApplication.getAppContext().getPackageManager();
            title = pm.getApplicationLabel(pm.getApplicationInfo(TTApplication.getAppContext().getPackageName(), PackageManager.GET_META_DATA)).toString();
        } catch (Exception e) {
        }
        // 快捷方式名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
        // 不允许重复创建（不一定有效）
        shortcut.putExtra("duplicate", false);
        // 快捷方式的图标
      /*  Parcelable iconResource = Intent.ShortcutIconResource.fromContext(TTApplication.getAppContext(), R.drawable.icon);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource);
*/
        TTApplication.getAppContext().sendBroadcast(shortcut);
    }

    /**
     * 删除当前应用的桌面快捷方式
     *
     */
    public static void delShortcut() {
        Intent shortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");

        // 获取当前应用名称
        String title = null;
        try {
            final PackageManager pm = TTApplication.getAppContext().getPackageManager();
            title = pm.getApplicationLabel(pm.getApplicationInfo(TTApplication.getAppContext().getPackageName(), PackageManager.GET_META_DATA)).toString();
        } catch (Exception e) {
        }
        // 快捷方式名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
        Intent shortcutIntent = TTApplication.getAppContext().getPackageManager().getLaunchIntentForPackage(TTApplication.getAppContext().getPackageName());
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        TTApplication.getAppContext().sendBroadcast(shortcut);
    }
}
