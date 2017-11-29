package com.ldgx.eshop.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import net.sf.json.JSONObject;

/**
 * 微信授权
 * @author Administrator
 *
 */
public class AuthUtil {
	
	/**
	 * 根据访问地址返回json格式数据
	 * @param url:访问地址
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static JSONObject doGetJson(String url) throws ClientProtocolException,IOException {
		JSONObject jsonObject = null;
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = client.execute(httpGet);
		
		HttpEntity entity = response.getEntity();
		
		if(entity != null) {
			String result = EntityUtils.toString(entity,"UTF-8");
			jsonObject = JSONObject.fromObject(result);
		}
		
		httpGet.releaseConnection();
		return jsonObject;
	}
	
	/**
	 * 获取request
	 * @return
	 */
	public static HttpServletRequest getServletRequest() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        if(sra == null) {
        	return null;
        }
       //request
        HttpServletRequest request = sra.getRequest();
        return request;
	}
	
}
