package com.ldgx.eshop.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ldgx.eshop.dao.IWeixinDao;
import com.ldgx.eshop.model.WeixinUser;
import com.ldgx.eshop.util.AuthUtil;

import net.sf.json.JSONObject;



@Controller
@RequestMapping("/wxlogin")
public class LoginController {

	@Value("${weixin.appid}")
	private String wx_appid;
	
	@Value("${weixin.appsecret}")
	private String wx_appsecret;
	
	@Value("${weixin.backUrl}")
	private String wx_backUrl;//回调地址
	
	@Autowired
	private IWeixinDao weixinDao;
	
	private Logger logger =LoggerFactory.getLogger(this.getClass());
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
	public ModelAndView callback(String code) throws IOException{
		
		ModelAndView mv = new ModelAndView();
		
		StringBuffer sb = new StringBuffer("https://api.weixin.qq.com/sns/oauth2/access_token?appid=");
		sb.append(wx_appid);
		sb.append("&secret=");
		sb.append(wx_appsecret);
		sb.append("&code=");
		sb.append(code);
		sb.append("&grant_type=authorization_code");
		JSONObject jo = AuthUtil.doGetJson(sb.toString());//HttpClientUtil.sendGet(sb.toString(), null);
		
		//4 第四步：拉取用户信息(需scope为 snsapi_userinfo)
		StringBuffer sb4 = new StringBuffer("https://api.weixin.qq.com/sns/userinfo?access_token=");
		sb4.append(jo.get("access_token"));
		sb4.append("&openid=");
		sb4.append(jo.get("openid"));
		sb4.append("&lang=zh_CN");

		//StringBuffer rsb4 = HttpClientUtil.sendGet(sb4.toString(), null);
		JSONObject jo2 = AuthUtil.doGetJson(sb4.toString());
		
		
		
		WeixinUser user = new WeixinUser();
		if(jo2 != null && jo2.containsKey("openid")) {
			String openid = jo2.getString("openid");
			if(openid != null) {
				user.setOpenid(openid);
				user.setNickname(jo2.getString("nickname"));
				user.setSex(jo2.getInt("sex"));
				user.setProvince(jo2.getString("province"));
				user.setCity(jo2.getString("city"));
				user.setCountry(jo2.getString("country"));
				String headimgurl = jo2.getString("headimgurl");//用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
				
				if(headimgurl != null) {
					String str = headimgurl.substring(headimgurl.lastIndexOf("/"));
					if(str.matches("^\\d+$")) {
						user.setHeadimgsize(Integer.parseInt(str));//正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像）
					}
					
					String headimgurl2 = headimgurl.substring(0, headimgurl.lastIndexOf("/"));
					user.setHeadimgurl(headimgurl2);//用户头像图片
				}
				
			}
			if(jo2.containsKey("unionid")) {
				user.setUnionid(jo2.getString("unionid"));				
			}
			
			//1.使用微信用户信息直接登录，无需注册和绑定
			HttpServletRequest req = AuthUtil.getServletRequest();
			//req.getSession().setAttribute("currentUser", user);
			
			//mv.setViewName("index");
			
			//2.讲微信与当前系统的账号进行绑定。
			if(jo2.containsKey("nickname")) {//绑定成功
				String nickName = jo2.getString("nickname");
				
				String dbopenid = weixinDao.getNickNameByOpenid(openid);
				if(dbopenid == null) {//重新登录

					mv.addObject("openid", openid);
					mv.setViewName("login");
					return mv;
				}
				req.setAttribute("nickname", nickName);
				mv.setViewName("index2");
				return mv;
			}else {//绑定失败
				mv.addObject("openid", openid);
				mv.setViewName("login");
				return mv;
			}
		}else {
			logger.error("error:" +jo2.toString());
		}
		
		return mv;
	}
	
	@RequestMapping(value="/wxCallBack")
	public ModelAndView wxCallBack(@RequestParam(value="account")String account,
			@RequestParam(value="openid")String openid) {
		//根据account,password,,更新数据库中openid
		ModelAndView mv = new ModelAndView();
		weixinDao.updateOpenidByAccount(account, openid);
		mv.setViewName("index");
		return mv;
	}
}
