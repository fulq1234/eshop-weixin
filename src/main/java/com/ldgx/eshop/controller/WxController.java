package com.ldgx.eshop.controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ldgx.eshop.util.WeixinUtil;

@Controller
@RequestMapping("/wxmain")
public class WxController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 上传图片
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
	
}
