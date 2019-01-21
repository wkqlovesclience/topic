<%@ page contentType="text/html; charset=GBK" import="com.coo8.btoc.config.ReloadableConfig" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/jsp/admin/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
request.setAttribute("path", path);
request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>新页面测试</title>
<link href="http://css.gomein.net.cn/topics/css/OPTBG.css" rel="stylesheet" type="text/css" />
</head>

<body style="border:none;">
<div class="Top">
<div class="Nav">
<%
	String logoImgurl = "http://app.gomein.net.cn/topics/images/LOGO.gif";
	String site = "" + session.getAttribute("login_gome_coo8_site_flag");
	if(null != site && site.equals("gome")){
		logoImgurl = "http://app.gomein.net.cn/images/gome-logoico.png";
	}
 %>
<div class="Logo" style="background:url('<%=logoImgurl%>') no-repeat scroll left top transparent"></div><div class="LeftNav"><img src="http://app.gomein.net.cn/topics/images/left_1.gif"/></div>
</div>                                                 
<div class="Exit">

<span class="LText ExitLeft"><font color="red"> </font>尊敬的<s:property value="#session.userName" />，您好！</span>
<span class="DText ExitRit"><a href="javascript:" onclick="checkout()">退出登录</a></span></div>
</div>
<script type="text/javascript">
function checkout(){
	window.parent.document.location.href="${basePath}/admin/role/logout.action";
}
</script>
</body>
</html>