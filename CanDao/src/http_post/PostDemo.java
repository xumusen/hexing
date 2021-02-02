package http_post;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class PostDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpClient httpClient = null;
		HttpPost httppost = null;
		try {
			httpClient = new DefaultHttpClient();
			httppost = new HttpPost("http://123.57.229.179:9090/sms/batch/v1");
			Map<String, String> map = new HashMap<String, String>();
			String appkey = "DX9544";
			String appsecret = "BW117H";
			String appcode = "1000";
			map.put("appkey", appkey);
			map.put("appcode", appcode);
			String timestamp = System.currentTimeMillis() + "";
			map.put("timestamp", timestamp);
			map.put("phone", "18601070550");
			map.put("msg", "晚上好啊");
			String sign = md5(appkey + appsecret + timestamp);
			map.put("sign", sign);
			map.put("extend", "");
			System.out.println(map.toString());
			String json = JSONObject.toJSONString(map);
			StringEntity formEntity = new StringEntity(json, "utf-8");
			httppost.setEntity(formEntity);
			HttpResponse httpresponse = null;
			httpresponse = httpClient.execute(httppost);
			HttpEntity httpEntity = httpresponse.getEntity();
			String response = EntityUtils.toString(httpEntity, "utf-8");
			System.out.println(response);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httppost != null) {
				httppost.abort();
			}
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
			}
		}
	}

	private static String md5(String param) throws NoSuchAlgorithmException,

			UnsupportedEncodingException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] md5Byte = md5.digest(param.getBytes("utf8"));
		String result = byteToHex(md5Byte);
		return result;
	}

	private static String byteToHex(byte[] md5Byte) {
		String result = "";

		StringBuilder sb = new StringBuilder();
		for (byte each : md5Byte) {
			int value = each & 0xff;
			String hex = Integer.toHexString(value);
			if (value < 16) {
				sb.append("0");
			}
			sb.append(hex);
		}
		result = sb.toString();
		return result;
	}

	public static int byte4ToInteger(byte[] b, int offset) {
		return (0xff & b[offset]) << 24 | (0xff & b[offset + 1]) << 16 | (0xff & b[offset + 2]) << 8
				| (0xff & b[offset + 3]);
	}

}
