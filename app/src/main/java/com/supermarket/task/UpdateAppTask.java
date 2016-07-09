package com.supermarket.task;

import android.content.Context;

import com.finance.common.core.TTUrlManager;
import com.supermarket.request.BaseRequest;
import com.supermarket.response.UpdateVersionResponse;
import com.supermarket.utils.DataProcessUtils;

public class UpdateAppTask extends BaseTask<BaseRequest, Void, UpdateVersionResponse>{
	
    public UpdateAppTask(Context c) {
        super(c);
    }
    
    public UpdateAppTask(Context c, boolean isShowDialog) {
        super(c, isShowDialog);
    }

    @Override
    protected UpdateVersionResponse doLogic(BaseRequest... params) throws Throwable {
        return DataProcessUtils.getJson(TTUrlManager.CHECK_VERSION_UPDATE, params[0], UpdateVersionResponse.class);
    }

}
