package com.ldgx.eshop.controller;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ldgx.eshop.util.HttpClientUtil;



@Controller
@RequestMapping("/wxlogin")
public class LoginController {

	@Value("${weixin.appid}")
	private String wx_appid;
	
	@Value("${weixin.appsecret}")
	private String wx_appsecret;
	
	@Value("${weixin.backUrl}")
	private String wx_backUrl;//回调地址
	/**
	 * 微信网络授权，1 第一步：用户同意授权，获取code
	 * @return
	 */
	@RequestMapping("/login")
	public String wxLogin() {
		StringBuffer sb = new StringBuffer("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
		sb.append(wx_appid);
		sb.append("&redirect_uri=");
		sb.append(URLEncoder.encode(wx_backUrl));
		sb.append("&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		
		return "redirect:" + sb.toString();
	}
	
	/**
	 * 微信网络授权 2 第二步：通过code换取网页授权access_token
	 * 正确时返回的JSON数据包如下：
{ "access_token":"ACCESS_TOKEN",    

 "expires_in":7200,    

 "refresh_token":"REFRESH_TOKEN",    

 "openid":"OPENID",    

 "scope":"SCOPE" } 
 
 
 
	 * 4 第四步：拉取用户信息(需scope为 snsapi_userinfo)
	 * 正确时返回的JSON数据包如下：

{    "openid":" OPENID",  

 " nickname": NICKNAME,   

 "sex":"1",   

 "province":"PROVINCE"   

 "city":"CITY",   

 "country":"COUNTRY",    

 "headimgurl":    "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ

4eMsv84eavHiaiceqxibJxCfHe/46",  

"privilege":[ "PRIVILEGE1" "PRIVILEGE2"     ],    

 "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL" 

} 


	 * @param code
	 * @return
	 */
	@RequestMapping("/callback")
	public String callback(String code) throws IOException{
		StringBuffer sb = new StringBuffer("https://api.weixin.qq.com/sns/oauth2/access_token?appid=");
		sb.append(wx_appid);
		sb.append("&secret=");
		sb.append(wx_appsecret);
		sb.append("&code=");
		sb.append(code);
		sb.append("&grant_type=authorization_code");
		StringBuffer rsb = HttpClientUtil.sendGet(sb.toString(), null);
		
		//4 第四步：拉取用户信息(需scope为 snsapi_userinfo)
		StringBuffer sb4 = new StringBuffer("https://api.weixin.qq.com/sns/userinfo?access_token=");
		sb4.append("ACCESS_TOKEN");
		sb4.append("&openid=");
		sb4.append("OPENID");
		sb4.append("&lang=zh_CN");

		StringBuffer rsb4 = HttpClientUtil.sendGet(sb4.toString(), null);
		
		return null;
	}
}
