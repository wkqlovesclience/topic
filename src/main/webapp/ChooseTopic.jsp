<%@ page language="java" import="com.coo8.btoc.config.ReloadableConfig"
	pageEncoding="GBK"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<base target="_self" />
<title>后台登录</title>
<style>
.OutterDiv{
	width:380px;
	height:200px;
	margin-left:auto;
	margin-right:auto;
	margin-top:15%;
	border:4px solid #383838;
	background-color: white;
}
.InnerDiv{
	border-bottom:7px solid #808080;
	border-top:7px solid #808080;
	border-left:4px solid #808080;
	border-right:4px solid #808080;
	background-color: #B8B8B8;
	height: 186px;
}
.format{
	margin-left: 40px;
}
.selectLogin{
	margin-top: 20px;
	font-weight: bold;
	color:#606060;
	font-size: 20px;
}
.TopicContainer{
	margin-top: 20px;
	height: 50px;
}
.Topic{
	float:left;
	width: 110px;
	height:40px;
	line-height:40px;
	font-size:20px;
	text-align:center;
	background-color: #505050;
	color:#DCDCDC;
}
.description{
	color:#606060;
	font-size: 12px;
	text-align: left;
	margin-top:20px; 
	width: 290px;
	display: block;
}
.own_a{
	text-decoration: none;
	color:white;
}
</style>
<script type="text/javascript">
	function openTopic(topicType){
		location.href="/Titles/chooseTopics.action?select="+topicType;
	}
</script>
</head>

<body style="background:#545e68; margin:0 auto;">
<form action="${ctx}/Aladdin/list.action" method="post" id="f1">
<div class="OutterDiv"> 
	<div class="InnerDiv">
		<div class="format">
				<div class="selectLogin">选择登录：</div> 
				<div class="TopicContainer">
					<div class="Topic"><a href="javascript:void(0)" onclick="openTopic('gome')" class="own_a">国美专题</a></div>
					<div style="width: 70px;float: left;">&nbsp;&nbsp;</div>
					<div class="Topic"><a href="javascript:void(0)" onclick="openTopic('coo8')" class="own_a">库巴专题</a></div>
					<div style="width: 24px;float: left;"></div>
				</div>
			<div class="description">后台选择：点击进入对应的商品专题后台</div>
		</div>
	</div>
</div>
</form>
</body>
</html>
