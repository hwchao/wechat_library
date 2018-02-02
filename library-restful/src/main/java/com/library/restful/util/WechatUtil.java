package com.library.restful.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Map;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.library.common.utils.JsonUtils;

public class WechatUtil {
	private static String AppID = "wxf37a24657ce3b336"; 
	private static String AppSecret="251715f7d3362eb4972fe83548f8b6b7";
	private static String KEY_ID_URL="https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
	
	/**
	 * 
	 * 功能：code 换取 session_key,openId
	 * 作者：hwchao
	 * 修改时间：2017年10月8日下午11:06:06
	 */
	public static Map<String,String> getSessionKeyAndOpenId(String code){
		String url = KEY_ID_URL.replace("APPID", AppID).replace("SECRET", AppSecret).replace("JSCODE", code);
		String jsonData = HttpClientUtil.doGet(url);
		@SuppressWarnings("unchecked")
		Map<String, String> map = JsonUtils.jsonToPojo(jsonData, Map.class);
		return map;
	}
	
	/**
	 *  --目前本功能还未实现--
	 * 功能：通过session_key换取用户的加密信息 openId和unionId
	 * 作者：hwchao
	 * 修改时间：2017年10月10日下午2:45:46
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void getUserInfo(String session_key,String encryptedData,String iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
		//Base64 decode
		byte[] sessionKey = Base64.decodeBase64(session_key);
		byte[] encrypteddata = Base64.decodeBase64(encryptedData.getBytes("utf-8"));
		byte[] IV = Base64.decodeBase64(iv);
		//AES decode
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
		IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);
		SecretKey key = new SecretKeySpec(sessionKey, "AES");
		cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
		byte[] data = cipher.doFinal(encrypteddata);
		String result = Base64.encodeBase64String(data);
		System.out.println("result:"+result);
	}
	
	/**
	 * 
	 * 功能：将byte转为十六进制
	 * 作者：hwchao
	 * 修改时间：2017年10月15日下午2:52:39
	 */
    public static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash){
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

	/**
	 * 
	 * 功能：返回一个随机字符串
	 * 作者：hwchao
	 * 修改时间：2017年10月15日下午2:51:03
	 */
	public static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }
	
	/**
	 * 
	 * 功能：返回一个时间戳
	 * 作者：hwchao
	 * 修改时间：2017年10月15日下午2:51:29
	 */
	public static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
