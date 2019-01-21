<%@page import="com.coo8.topic.model.*" %>
<%@ page language="java"  pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp" %>  
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>Index</title>
</head>
<body>
	<s:form action="helloWorld">
		<s:textfield label="What is your name?" name="name" />
		<s:textfield label="What is the date?" name="dateNow" />
		<s:submit />
	</s:form>
	<center>
		<a href="${ctx}/News/create.action">添加文章</a><br><br>
		<a href="${ctx}/News/list.action">文章列表</a><br><br>
		<a href="${ctx}/Titles/create.action">添加专题</a><br><br>
		<a href="${ctx}/Titles/list.action">专题列表</a><br><br>
		<a href="${ctx}/admin/resource/list.action">数据源管理</a><br><br>
		<a href="${ctx}/admin/template/list.action">模板管理</a><br><br>
		<a href="${ctx}/Keywords/list.action">标签管理</a><br>
	</center>
    <a href="${ctx}/admin/block/list.action">块管理</a>
    <a href="${ctx}/admin/template/list.action">模板列表</a>
	<a href="${ctx}/admin/resource/list.action">数据源管理</a>
	<a href="${ctx}/admin/html/list.action">静态页管理</a>
	<a href="${ctx}/admin/procedure/list.action">存储过程管理</a>
	
</body>
</html>