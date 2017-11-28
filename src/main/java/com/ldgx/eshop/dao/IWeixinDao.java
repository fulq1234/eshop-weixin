package com.ldgx.eshop.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IWeixinDao {
	
	/**
	 * 更新
	 * @param account
	 * @param openid
	 * @return
	 */
	public void updateOpenidByAccount(@Param(value="account")String account,
			@Param(value="openid")String openid);
	
	/**
	 * 
	 * @param openid
	 * @return
	 */
	public String getNickNameByOpenid(String openid);
}
