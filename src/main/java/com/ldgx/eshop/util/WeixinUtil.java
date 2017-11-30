package com.ldgx.eshop.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;

import com.ldgx.eshop.button.Button;
import com.ldgx.eshop.button.ClickButton;
import com.ldgx.eshop.button.Menu;
import com.ldgx.eshop.button.ViewButton;

import net.sf.json.JSONObject;


/**
 * 微信操作
 * @author Administrator
 *
 */
public class WeixinUtil {
	
	private static String wx_appid = "wx1bb6a6f489c676f6";
	
	private static String wx_appsecret = "8013da894f90199356795ebe2a19f50b";
	
	@Value("${weixin.backUrl}")
	private String wx_backUrl;//回调地址
	
	//获取access_token
	//access_token是公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token。开发者需要进行妥善保存。access_token的存储至少要保留512个字符空间。access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的access_token失效。
	private static String token_url= "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" +wx_appid + "&secret=" + wx_appsecret;
	
	//新增素材
	private static String media_upload_url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESSTOKEN&type=";;//新增临时素.图片：image；语音:voice
	
	//自定义菜单
	private static String create_menu_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	/**
	 * 获取access token
	 * @return
	 */
	public static String getAccessToken() {
		HttpServletRequest request = AuthUtil.getServletRequest();
		if(request != null) {
			String access_token = (String) request.getSession().getAttribute("access_token");
			if(access_token != null) {
				return access_token;
			}			
		}
		
		try {
			JSONObject json = AuthUtil.doGetJson(token_url);
			if(json.containsKey("access_token")) {
				String access_token2 = json.getString("access_token");
				request.getSession().setAttribute("access_token", access_token2);
				return access_token2;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 新增临时素材
	 * @param filePath
	 * @param accessToken
	 * @param type
	 * @return
	 * @throws IOException
	 */
	public static String upload(String filePath,String accessToken,String type ) throws IOException{
		File file = new File(filePath);
		if(!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}
		
		String media_upload_url_str = media_upload_url.replace("ACCESSTOKEN", getAccessToken() ) + type;
		URL urlObject =new URL(media_upload_url_str);
		//连接
		HttpURLConnection con = (HttpURLConnection) urlObject.openConnection();
		con.setRequestMethod("POST");
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false);
		
		//设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");
		
		//设置边界
		String BOUNDARY = "------------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + BOUNDARY);
		
		StringBuilder sb = new StringBuilder();
		sb.append("---");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition:form-data;name=\"file\";filename=\"" +file.getName() +"\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		
		byte[] head = sb.toString().getBytes("utf-8");
		
		//获取输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		//输出表头
		out.write(head);
		
		//文件正文部分
		//把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut,0,bytes);
		}
		in.close();
		
		//结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//定义最后数据分割线
		out.write(foot);
		
		
		out.flush();
		out.close();
		
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		String result  = null;
		reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String line = null;
		while((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		if(result == null) {
			result = buffer.toString();
		}
		
		
		//
		JSONObject jsonObj = JSONObject.fromObject(result);
		System.out.println(jsonObj);
		
		String mediaId = "";
		if(jsonObj.has("media_id")) {//type=image
			mediaId = jsonObj.getString("media_id");
			
		}else if(jsonObj.has("thumb_media_id")) {//type=thumb
			mediaId = jsonObj.getString("thumb_media_id");
			
		}
		return mediaId;
	}
	
	
	/**
	 * 组装菜单
	 * @return
	 */
	public static Menu initMenu() {
		Menu menu = new Menu();
		
		ClickButton button11 = new ClickButton();
		button11.setName("click菜单");
		button11.setType("click");
		button11.setKey("11");
		
		ViewButton button21 = new ViewButton();
		button21.setName("view菜单");
		button21.setType("view");
		button21.setUrl("http://www.baidu.com");
		
		ClickButton button31 = new ClickButton();
		button31.setName("扫码事件");//扫码推送事件
		button31.setType("scancode_push");
		button31.setKey("31");

		ClickButton button32 = new ClickButton();
		button32.setName("地理位置");//扫码推送事件
		button32.setType("location_select");
		button32.setKey("32");
		
		Button button = new Button();
		button.setName("菜单");
		button.setSub_button(new Button[] {button31,button32});
		
		menu.setButton(new Button[] {button11,button21,button});
		
		
		return menu;
	}
	
	/**
	 * POST请求
	 * @param url
	 * @param outStr:实体转化成的字符串,比如JSONObject.fromObject(menu).toString()
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doPostStr(String url,String outStr) throws ParseException, IOException{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);
		JSONObject jsonObject = null;
		httpost.setEntity(new StringEntity(outStr,"UTF-8"));
		HttpResponse response = client.execute(httpost);
		String result = EntityUtils.toString(response.getEntity(),"UTF-8");
		jsonObject = JSONObject.fromObject(result);
		return jsonObject;
	}
	
	/**
	 * 自定义菜单
	 * @param token
	 * @param menu
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException

接口调用请求说明

http请求方式：POST（请使用https协议） https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN

click和view的请求示例

 {
     "button":[
     {	
          "type":"click",
          "name":"今日歌曲",
          "key":"V1001_TODAY_MUSIC"
      },
      {
           "name":"菜单",
           "sub_button":[
           {	
               "type":"view",
               "name":"搜索",
               "url":"http://www.soso.com/"
            },
            {
                 "type":"miniprogram",
                 "name":"wxa",
                 "url":"http://mp.weixin.qq.com",
                 "appid":"wx286b93c14bbf93aa",
                 "pagepath":"pages/lunar/index"
             },
            {
               "type":"click",
               "name":"赞一下我们",
               "key":"V1001_GOOD"
            }]
       }]
 }
	 */
	public static int createMenu(String token,Menu menu) throws ClientProtocolException, IOException {
		int result = 0;
		String url = create_menu_url.replace("ACCESS_TOKEN", token);
		
		String menu_str = JSONObject.fromObject(menu).toString();
		JSONObject jsonObject = doPostStr(url,menu_str);//发送请求
		if(jsonObject != null) {
			result = jsonObject.getInt("errcode");
		}
		return result;
	}
	
}
