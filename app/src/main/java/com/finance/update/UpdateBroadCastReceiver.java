package com.finance.update;

import java.io.File;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import com.finance.cache.ConfigurationCache;
import com.supermarket.ui.activity.base.ActivityStack;
import com.supermarket.utils.TTLog;
import com.supermarket.utils.VersionUpdateUtils;

/**
 * 当前应用更新提示广播，应用级别广播
 * 
 * @author lqsir
 * 
 */
class UpdateBroadCastReceiver extends BroadcastReceiver {
	private static final String TAG = "TTFM/UpdateBroadCastReceiver ";

	@Override
	public void onReceive(final Context context, Intent intent) {
		TTLog.i(TAG + intent.getAction());
		if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
			long downId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

			String path = null;
			DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
			Cursor c = manager.query(new DownloadManager.Query().setFilterById(downId));
			if (c != null && c.moveToFirst()) {
				path = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME));
				System.out.println(downId + "--" + path);
				switch (c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS))) {
				case DownloadManager.STATUS_FAILED:
				case DownloadManager.STATUS_PAUSED:
				case DownloadManager.STATUS_PENDING:
					manager.remove(downId);
					break;
				case DownloadManager.STATUS_RUNNING:
					break;
				case DownloadManager.STATUS_SUCCESSFUL:
					downloadCompleteInstall(path);// mingli 成功了才去安装
					break;
				default:
					break;
				}
			}
		} else if (intent.getAction().equals(Intent.ACTION_SHUTDOWN)) {
			final long downId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
			if (downId == ConfigurationCache.getVersionDownloadId()) {
				DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
				manager.remove(downId);
				ConfigurationCache.setVersionInterrupt(true);
			}
		}
	}

	private void downloadCompleteInstall(String path) {
		System.out.println("downloadCompleteInstall path: " + path);
		
		File file = new File(path);

		if (file.exists()) {
			VersionUpdateUtils.Instanll(file, ActivityStack.getInstance().getStackTopActivity());
		}
	}

}