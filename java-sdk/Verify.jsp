<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.touclick.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<body>
<%
			/*从http://www.touclick.com 注册账号，可获取公钥和私钥*/
			String website_key = "公钥（从点触官网申请）";
			String private_key = "私钥（从点触官网申请）";
			String check_address = request.getParameter("checkAddress");
			String check_key = request.getParameter("token");
			String ip = TouClick.getIpAddr(request);
			
			Boolean result = TouClick.check(website_key, private_key, check_key, check_address, null, null, null);
			if(result == true){
%>
<label>That's ok!</label>
<% }else{ %>
<label>Error</label>
<% } %>
	</body>
</html>
