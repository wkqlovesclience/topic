<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>商品专题	后台管理系统</title>
<link href="http://css.gomein.net.cn/topics/css/OPTBG.css" rel="stylesheet" type="text/css" />
<%@ include file="/jsp/admin/common/taglibs.jsp"%>
<%@ include file="/jsp/admin/common/js.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	var url="${ctx}/jsp/admin/common/navigation.jsp?aaa="+Math.random();
	$("#leftframe").attr("src",url);
    
	function wResize(){
        $("#rightid").height($(window).height()-115);
        $("#leftid").height($(window).height()-120);
    }
    
    $(window).resize(wResize());
	wResize();
});
</script>

</head>

<body style="overflow: auto;">
<div id="topid" class="Top">
    <iframe frameborder="no"border="0" src="${ctx}/jsp/admin/common/header.jsp" style="width:100%; height:100%;"></iframe>
</div>
<div id="leftid" class="LeftNavCon">
    <div class="LeftNavbot">
        <img src="http://app.gomein.net.cn/topics/images/left_2.gif"/>
    </div>
    <iframe id="leftframe" name="leftframe" allowtransparency="true"  frameborder="no" border="0" style="width:190px;_width:188px;background:#e7eaee; height:100%; margin-left:9px;border:1px solid #636F78;">
    </iframe>
</div>
<div  class="Right">
    <div id="rightid" class="RightCon">
    <iframe id="rightframe" name="childframe" frameborder="no" border="0" style="width:100%; height:100%;"></iframe>
</div>
</div>
</body>
</html>


