package com.touclick;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/// <summary>
/// </summary>
public class TouClick {

    /// <summary>
    /// 返回 true 则表示验证成功
    ///      false 表示验证失败或是没有验证或是其他错误发生
    /// </summary>
    /// <param name="website_key">公钥(需向点触申请)</param>
    /// <param name="private_key">私钥(需向点触申请)</param>
    /// <param name="check_key">二次验证口令(来自客户端post)</param>
    /// <param name="check_address">二次验证地址(来自客户端post)</param>
    /// <param name="client_ip">进行此次二次验证的用户IP(非必须参数 建议传输,网站会更加安全)</param>
    /// <param name="user_name">进行此次二次验证的用户名(非必须参数 统计数据使用)</param>
    /// <param name="user_id">进行此次二次验证的用户ID(非必须参数 统计数据使用)</param>
    /// <returns></returns>
	private static String[] URLPRAS = new String[]{"http://","",".touclick.com/","",".touclick"};
	private static String[] URLVARS = new String[]{"","?b=","","&z=","","&i=","","&p=","","&un=","","&ud=",""};
	public static Boolean check(String website_key, String private_key, String check_key, String check_address, String client_ip, String user_name, String user_id)
	{
		if(null == website_key||null == private_key||null == check_key||null == check_address||!GUIDCheck(website_key)||!GUIDCheck(private_key))
		{
			return false;
		}
		if(null==client_ip)
		{
			client_ip="";
		}
		if(null==user_name)
		{
			user_name = "0";
		}
		if(null==user_id)
		{
			user_id = "0";	
		}
		
		URLVARS[0] = filter_host_path(check_address);
		if (null == URLVARS[0])
        {
            return false;
        }
		URLVARS[2] = website_key;
		URLVARS[4] = private_key;
		URLVARS[6] = check_key;
		URLVARS[8] = client_ip;
		URLVARS[10] = TouClick.encodeStr(user_name);
		URLVARS[12] = user_id;
		StringBuilder sbUrl = new StringBuilder();
		for(int i=0;i<URLVARS.length;i++)
		{
			sbUrl.append(URLVARS[i]);
		}
		String resString = TouClick.sendGet(sbUrl.toString());
		if(resString.contains("<<[yes]>>"))
		{
			return true;
		}else 
		{
			return false;
		}
	}
	private static Boolean GUIDCheck(String key)
	{
		return 36==key.length()?true:false;
	}
	private static String filter_host_path(String check_address)
	{
		StringBuilder  url = new StringBuilder ();
		String[] check_address_arr = check_address.split(",");
		if (check_address_arr == null || check_address_arr.length != 2)
        {
            return null;
        }

		String[] check_host_arr = check_address_arr[0].split("\\.");
        if (check_host_arr == null || check_host_arr.length != 3)
        {
        	return null;
        }

        String[] check_path_arr = check_address_arr[1].split("\\.");
        if (check_path_arr == null || check_path_arr.length != 2)
        {
        	return null;
        }

        if (filterStr(check_host_arr[0]) && filterStr(check_path_arr[0]))
        {
        	URLPRAS[1] = check_host_arr[0];
        	URLPRAS[3] = check_path_arr[0];
        	for(int i=0;i<URLPRAS.length;i++)
        	{
        		url.append(URLPRAS[i]);
        	}
            return url.toString();
        }
        else
        {
        	return null;	
        }
        
	}
	private static boolean filterStr(String str){
		Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
		Matcher matcher = pattern.matcher(str);
		if (matcher.find())
		{
			return true;
		}else{
			return false;
		}
	}
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	


	private static String encodeStr(String iniStr){
		
		//统一为UTF-8
		try {
			byte[] bytes = iniStr.getBytes("UTF-8");
			iniStr = new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			iniStr="exc";
			e.printStackTrace();
		}
		//URL编码
		try {
			iniStr = URLEncoder.encode(iniStr, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			iniStr = "enc";
			e.printStackTrace();
		}
		return iniStr;
	}
	
	private static String sendGet(String url) {
		StringBuilder result = new StringBuilder("");
		BufferedReader in = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 建立实际的连接
			conn.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = conn.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append("\n");
				result.append(line);
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return result.toString();
	}
}
