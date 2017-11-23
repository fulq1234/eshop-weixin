package com.ldgx.eshop.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ldgx.eshop.model.Scan;
import com.ldgx.eshop.model.ScanResponse;
import com.ldgx.eshop.util.CheckUtil;

@Controller
@RequestMapping("/weixin")
public class WechatController {

	private static final Logger logger = LoggerFactory.getLogger(WechatController.class);
	
	
	/**
	 * 微信连接接口
	 * @param signature:微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	 * @param timestamp:时间戳
	 * @param nonce：随机数
	 * @param echostr：随机字符串
	 * @return
	 */	
	
	@ResponseBody
	@GetMapping("/verifyWX")
	public String connectionWeixin(String signature,String timestamp,String nonce,String echostr){
		if(signature == null || timestamp == null || nonce == null || echostr == null) {
			logger.error("微信接入错误","微信参数有空值");
		}
		try {
			if(CheckUtil.checkSignature(signature, timestamp, nonce)){
				return echostr;
			}
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        logger.error("微信接入错误");
        return "";
		
	}
	
	@PostMapping(value = "/verifyWX")	
	//@RequestMapping(value="/xml")
	public @ResponseBody Scan weixinCreate(@RequestBody Scan scan) {
		logger.info(scan.toString());
		/*if("text".equals(scan.getMsgType())){			
			scan.setContent("回复了");
			return scan;
		}*/

		String toUserName = scan.getToUserName();
		String fromUserName = scan.getFromUserName();
		/*scan.setFromUserName(toUserName);
		scan.setToUserName(fromUserName);
		
		scan.setMsgType("text");
		scan.setContent("回复了");
		scan.setMsgId(null);
		scan.setCreateTime(new Date().getTime());*/

		Scan rScan = new Scan();
		rScan.setFromUserName(toUserName);
		rScan.setToUserName(fromUserName);
		
		rScan.setMsgType("text");
		rScan.setContent("回复了");
		rScan.setCreateTime(new Date().getTime());
		
		
		return rScan;
	}
	
	/*@PostMapping(value = "/verifyWX")	
	//@RequestMapping(value="/xml")
	public @ResponseBody ScanResponse weixinCreate(@RequestBody Scan scan) {
		logger.info(scan.toString());
		if("text".equals(scan.getMsgType())){			
			scan.setContent("回复了");
			return scan;
		}

		String toUserName = scan.getToUserName();
		String fromUserName = scan.getFromUserName();
		scan.setFromUserName(toUserName);
		scan.setToUserName(fromUserName);
		
		scan.setMsgType("text");
		scan.setContent("回复了");
		scan.setMsgId(null);
		scan.setCreateTime(new Date().getTime());

		ScanResponse rScan = new ScanResponse();
		rScan.setFromUserName(toUserName);
		rScan.setToUserName(fromUserName);
		
		rScan.setMsgType("text");
		rScan.setContent("回复了");
		rScan.setCreateTime(new Date().getTime());
		
		
		return rScan;
	}*/
	
	@RequestMapping(value="/xml3")
	public @ResponseBody Scan weixinCreate3() {
		Scan scan = new Scan();
		scan.setFromUserName("toUserName");
		scan.setToUserName("fromUserName");
		
		scan.setMsgType("text");
		scan.setContent("回复了");
		scan.setCreateTime(new Date().getTime());
		
		
		return scan;
	}
	
	

}
