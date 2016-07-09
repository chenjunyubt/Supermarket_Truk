package com.finance.common.http.entityhandler;

import java.io.IOException;

import org.apache.http.HttpEntity;

public interface EntityHandler {
	public Object handleEntity(HttpEntity entity)throws IOException;
}
