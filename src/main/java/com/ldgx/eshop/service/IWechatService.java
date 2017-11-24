package com.ldgx.eshop.service;

import com.ldgx.eshop.model.Scan;

public interface IWechatService {
	
	/**
	 * 处理消息
	 * @param scan
	 * @return
	 */
	public Scan handlerMessage(Scan scan);
}
