package com.supermarket.task;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.widget.Toast;

import com.finance.cache.TTConstantManager;
import com.finance.common.dialog.TTAlertDialog;
import com.finance.common.dialog.TTProgressDialog;
import com.finance.core.TTApplication;
import com.supermarket.R;
import com.supermarket.response.ErrorCodeResp;
import com.supermarket.utils.DataProcessUtils;
import com.supermarket.utils.TTLog;
import com.supermarket.utils.ToastUtils;

public abstract class BaseTask<Params, Progress, Result> extends
		ExpandAsyncTask<Params, Progress, Result> {

    private TTProgressDialog mWaitingDlg = null;
	private TTAlertDialog mNetWorkDlg = null;
	private Context mContext = null;
	private boolean isShowDialog = true;
	private int mMessageId;
	
	public BaseTask(Context c) {
		mContext = c;
	}
	
	public BaseTask(Context c, boolean isShowDialog) {
		this(c);
		this.isShowDialog = isShowDialog;
	}
	
	public BaseTask(Context c, int messageId) {
		this(c);
		this.mMessageId = messageId; 
	}
	
	public BaseTask(Context c, boolean isShowDialog, int messageId) {
		this(c);
		this.isShowDialog = isShowDialog;
		this.mMessageId = messageId; 
	}

	@Override
	final protected void onPreExecute() {

		if (isCancelled() || mContext == null) {
			return;
		}
		onPreLogic();


		if (isShowDialog && mContext instanceof Activity) {
			Activity activity = (Activity) mContext;
			if (activity.isFinishing()) {
				return;
			}
			
			mWaitingDlg = TTProgressDialog.show(mContext, null, 
					mMessageId == 0 ? getStringForId(R.string.loading_message) : getStringForId(mMessageId), 
							false, true);
//			mWaitingDlg.setOnKeyListener(new OnKeyListener() {
//				@Override
//				public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//					return true;
//				}
//			});
			
			mWaitingDlg.show();
		}
	}

	/**
	 * 由资源ID得到当前对应的字符串
	 * @param resId 资源ID
	 * @return
	 */
	private String getStringForId(int resId) {
		return mContext.getResources().getString(resId);
	}

	public void onPreLogic() {
	    if (!com.supermarket.utils.NetUtils.isNetConnected()) {
	    	onNoNetWorkException();
	        cancel(true);
	        return;
	    }
	}

	@Override
	protected void onFinish(Result result) {
		if (mContext instanceof Activity) {
			Activity activity = (Activity) mContext;
			if (activity.isFinishing()) {
				return;
			}
		
			if (mWaitingDlg != null) {
				mWaitingDlg.dismiss();
				mWaitingDlg = null;
			}
		}
	}

	@Override
	protected Result doing(Params... params) throws Throwable {
		Result r = null;
		try {
			r = doLogic(params);
		} catch (DataProcessUtils.ServerException e) {
			TTLog.d(e.getMessage());
			e.printStackTrace();
		}

		return r;
		
	}

	abstract protected Result doLogic(Params... params) throws Throwable;
	
	protected void onShowTipMessage(String message) {

	}
	
	/**
	 * 无网络情况下，回调方法
	 */
	protected void onNoNetWorkException() {
		TTLog.d("BaseTask ---- onNoNetWorkException");
        if (!TTConstantManager.getInstance().isShowErrorDialog()) { 
        	TTConstantManager.getInstance().setShowErrorDialog(true);
            mNetWorkDlg = new TTAlertDialog.Builder(mContext)
	            .setMessage(R.string.net_check_connect)
	            .setNeutralButton(R.string.ok, null)
	            .create();
            
            mNetWorkDlg.show();
            mNetWorkDlg.setOnDismissListener(new OnDismissListener() {
                
                @Override
                public void onDismiss(DialogInterface dialog) {
                	TTConstantManager.getInstance().setShowErrorDialog(false);
                }
            });
        }
	}
	
	/**
	 * 连接超时、服务器异常
	 */
	protected void onServerException() {
		Toast.makeText(TTApplication.getAppContext(),
				R.string.net_check_connect, Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * 服务器处理数据异常（参数错误...)
	 */
	protected void onDataException(ErrorCodeResp resp) {
		ToastUtils.showToast(TTApplication.getAppContext(), resp.error);
	}
	
	@Override
	protected void onException(Throwable e) {
		if (e instanceof DataProcessUtils.DataException) {
            DataProcessUtils.DataException pe = (DataProcessUtils.DataException) e;
            onDataException(pe.mError);
            return;
        } else {
        	e.printStackTrace();
        	onServerException();
			return;
        }
	}
}
