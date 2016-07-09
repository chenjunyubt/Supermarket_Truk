package com.finance.update;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import com.supermarket.R;
import com.finance.cache.ConfigurationCache;
import com.finance.common.utils.AppUtils;
import com.finance.core.TTApplication;
import com.supermarket.request.BaseRequest;
import com.supermarket.response.ErrorCodeResp;
import com.supermarket.response.UpdateVersionResponse;
import com.supermarket.response.UpdateVersionResponse.UpdateInfo;
import com.supermarket.task.UpdateAppTask;
import com.supermarket.ui.activity.base.ActivityStack;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 检查更新的线程
 *
 * @author lqsir
 */
class UpdateRunnable implements Runnable {
    /**
     * 是否显示消息
     */
    private boolean isShow = false;
    private Handler handler;
    private Context context;

    public UpdateRunnable(boolean isShow, Handler handler) {
        this.context = ActivityStack.getInstance().getStackTopClassName();
        this.isShow = isShow;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            Looper.prepare();
            new UpdateAppTask(context, isShow) {
                protected void onSuccess(UpdateVersionResponse result) {
                    if (result != null && result.getData() != null
                            && result.getData().getNewVersion() != null
                            && !TextUtils.isEmpty(result.getData().getNewVersion().getUrl())) {
                        TTApplication application = (TTApplication) TTApplication.getAppContext();
                        if (application.isUpdating()) {
                            showToast(R.string.updateing, isShow);
                        } else {
                            dispathUpdateData(result);
                        }
                    } else {
                        deleteUpdateFile(); // 删掉之前下载文件
                        if (isShow) {
                            showToast(R.string.newestversion, isShow);
                        }
                    }
                }

                protected void onDataException(ErrorCodeResp resp) {

                }

                protected void onServerException() {

                }

                protected void onNoNetWorkException() {
                }
            }.execute(new BaseRequest());

            Looper.loop();
        } catch (Exception e) {

        }
    }


    /**
     * 处理更新数据
     *
     * @param updateData
     */
    private void dispathUpdateData(UpdateVersionResponse updateData) {
        // 判断data数据是否存在，存在说明有更新数据，反之，无更新数据
        UpdateInfo updateInfo = updateData.getData().getNewVersion();

        if (null != updateInfo && !TextUtils.isEmpty(updateInfo.getUrl())) {
            if (AppUtils.getVersionName().equals(updateInfo.getVersion())) {
                showToast(R.string.newestversion, isShow);
                return;
            }

            if (updateInfo.getForce() == 1) {
                sendMessage(UpdateConstant.DOWNLOAD_UPDATE_MSGID, buildBundle(updateInfo));
            } else {
                // 非强制安装，一天弹一次
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                String updatetipdate = dateformat.format(new Date());
                if (!isShow) {// 非当天
                    if (!ConfigurationCache.getVersionDownloadDate().equals(updatetipdate)) {
                        ConfigurationCache.setVersionDownloadDate(updatetipdate);
                        sendMessage(UpdateConstant.DOWNLOAD_UPDATE_MSGID, buildBundle(updateInfo));
                    }
                } else {
                    sendMessage(UpdateConstant.DOWNLOAD_UPDATE_MSGID, buildBundle(updateInfo));
                }
            }
        }
    }

    private Bundle buildBundle(UpdateInfo updateData) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("object", updateData);
        return bundle;
    }

    /**
     * 如果不需要更新把更新文件目录清空
     *
     * @DATE 2014年1月9日 15:46:11
     */
    private void deleteUpdateFile() {
        File file = openDownLoadFile(AppUtils.getAppName() + "_" + AppUtils.getVersionName() + ".apk");
        if (file.exists()) {
            file.delete();
        }

        file = openDownLoadFile(AppUtils.getAppName() + "_" + AppUtils.getVersionName() + ".temp");
        if (file.exists()) {
            file.delete();
        }
    }

    private final File openDownLoadFile(String filename) {
        File file = new File(Environment.getExternalStoragePublicDirectory(UpdateConstant.DOWNLOAD_FILE_PATH), filename);
        return file;
    }

    public void sendMessage(int what, String data) {
        Bundle bundle = new Bundle();
        bundle.putString("value", data);
        sendMessage(what, 0, 0, bundle);
    }

    public void sendMessage(int what, Bundle bundle) {
        sendMessage(what, 0, 0, bundle);
    }

    public void sendMessage(int what, int arg1, int arg2, Bundle data) {
        if (handler != null) {
            Message message = handler.obtainMessage(what, arg1, arg2);
            message.setData(data);
            handler.sendMessage(message);
        }
    }

    private final void showToast(int resId, boolean need) {
        if (need) {
            sendMessage(UpdateConstant.DOWNLOAD_SHOW_TOAST_MSGID, context.getString(resId));
        }
    }
}
