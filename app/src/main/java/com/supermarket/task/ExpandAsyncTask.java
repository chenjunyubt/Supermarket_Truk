package com.supermarket.task;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

public abstract class ExpandAsyncTask<Params, Progress, Result> extends
		AsyncTask<Params, Progress, Result> {

	Throwable mThrowable = null;

	static Handler mHandler = new Handler();

	@Override
	final protected Result doInBackground(Params... params) {
		mThrowable = null;

		try {
			return doing(params);
		} catch (Throwable e) {
			mThrowable = e;
			e.printStackTrace();
			return null;
		}
	}

	abstract protected Result doing(Params... params) throws Throwable;

	@Override
	final protected void onCancelled() {
		onFinish(null);
	}

	@Override
	final protected void onCancelled(Result result) {
		onFinish(result);
	}

	@Override
	final protected void onPostExecute(Result result) {
		if (mThrowable != null) {
			onFinish(null);
			onException(mThrowable);
		} else {
			onSuccess(result);
			onFinish(result);
		}
	}

	public void executeSync(Params... params) throws Throwable {
		onPreExecute();
		
		Result r = doing(params);

		if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
			runPostOnUI(r);
		} else {
			onPostExecute(r);
		}
	}

	public static abstract class runOnUISync implements Runnable {
		private boolean mIsOvered = false;
		
		@Override
		final public void run() {
			runOnUI();
			
			synchronized (this) {
				notifyAll();
				mIsOvered = true;
			}
		}

		abstract public void runOnUI();

		public runOnUISync(){
			mHandler.post(this);

			try {
				synchronized (this) {
					if (!mIsOvered) {
						wait();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void runPostOnUI(final Result r) {
		new runOnUISync() {
			@Override
			public void runOnUI() {
				onPostExecute(r);
			}
		};
	}

	protected void onSuccess(Result result) {
	}

	protected void onException(Throwable e) {
	}

	protected void onFinish(Result result) {
	}

}
