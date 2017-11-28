package com.ldgx.eshop.model;

/**
 * 每个用户针对每个公众号会产生一个安全的OpenID
 * 对所有这些同一开放平台账号下的公众号和应用，只有一个UnionID
 * @author Administrator
 *
 */
public class WeixinUser {
	
	private String openid;//用户的唯一标识
	
	private String nickname;//用户昵称
	
	private int sex;//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	
	private String province;//用户个人资料填写的省份
	
	private String city;//普通用户个人资料填写的城市
	
	private String country;//国家，如中国为CN
	
	//http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46
	private String headimgurl;//用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	
	private int headimgsize;//最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像）,用户没有头像时该项为空。
	
	private String privilege;//用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
	
	private String unionid;//只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public int getHeadimgsize() {
		return headimgsize;
	}

	public void setHeadimgsize(int headimgsize) {
		this.headimgsize = headimgsize;
	}

	public WeixinUser() {
		super();
	}

	public WeixinUser(String openid, String nickname, int sex, String province, String city, String country,
			String headimgurl, int headimgsize, String privilege, String unionid) {
		super();
		this.openid = openid;
		this.nickname = nickname;
		this.sex = sex;
		this.province = province;
		this.city = city;
		this.country = country;
		this.headimgurl = headimgurl;
		this.headimgsize = headimgsize;
		this.privilege = privilege;
		this.unionid = unionid;
	}

	@Override
	public String toString() {
		return "WeixinUser [openid=" + openid + ", nickname=" + nickname + ", sex=" + sex + ", province=" + province
				+ ", city=" + city + ", country=" + country + ", headimgurl=" + headimgurl + ", headimgsize="
				+ headimgsize + ", privilege=" + privilege + ", unionid=" + unionid + "]";
	}
	
	
}
