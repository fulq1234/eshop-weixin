package com.ldgx.eshop.controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ldgx.eshop.button.Menu;
import com.ldgx.eshop.util.WeixinUtil;

import net.sf.json.JSONObject;

/**
 * 可以执行执行controller,执行方法
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/wxmain")
public class WxTestController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 上传图片
	 * 直接执行该controller，就能实现
	 * @param type image:图片，thumb:语音
	 * @return
	 */
	@RequestMapping("/upimage")
	@ResponseBody
	public String upimage(String type) {
		if(type == null) {
			type = "image";
		}
		try {
			String access_token = WeixinUtil.getAccessToken();
			System.out.println(access_token);
			String path = "E:/2.jpg";
			String media_id = WeixinUtil.upload(path, access_token, type);
			logger.info("media_id:" + media_id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
		
	}
	
	/**
	 * 自定义菜单，执行这个controller，就可以实现
	 * @return
	 */
	@RequestMapping("/zdycd")
	@ResponseBody
	public String zidingyicaidan() {
		try {
			String access_token = WeixinUtil.getAccessToken();
			Menu menu = WeixinUtil.initMenu();
			int result = WeixinUtil.createMenu(access_token, menu);
			if(result == 0) {
				logger.info("菜单创建成功");
			}else {
				logger.error("错误码:" +result);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
		
	}
	
	/**
	 * 菜单查询
	 * @return
	 */
	@RequestMapping("/querymenu")
	@ResponseBody
	public String querymenu() {
		try {
			String access_token = WeixinUtil.getAccessToken();
			JSONObject jsonObject = WeixinUtil.queryMenu(access_token);
			System.out.println(jsonObject);
			logger.info(jsonObject);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
		
	}
	
}
