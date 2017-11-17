package com.ldgx.eshop.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * ��֤����
 * @author fulq
 *
 */
public class SignUtil {
	private static final String token = "immoc";

	public static boolean checkSignature(String signature, String timestamp, String nonce)
			throws NoSuchAlgorithmException {
		String[] arr = new String[] { token, timestamp, nonce };

		// ����/У���������£�
		// 1. ��token��timestamp��nonce�������������ֵ�������
		Arrays.sort(arr);
		// 2. �����������ַ���ƴ�ӳ�һ���ַ�������sha1����
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
		}
		// sha1����
		String temp = getSha1(sb.toString());

		// 3. �����߻�ü��ܺ���ַ�������signature�Աȣ���ʶ��������Դ��΢��
		return temp.equals(signature);
	}
	// �����ĸ�import��������ǰ�� ��������
	// import java.io.UnsupportedEncodingException;
	// import java.security.MessageDigest;
	// import java.security.NoSuchAlgorithmException;
	// import java.util.Arrays;

	public static String getSha1(String str) {
		if (null == str || 0 == str.length()) {
			return null;
		}
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char[] buf = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

}
