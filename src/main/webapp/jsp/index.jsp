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
		<a href="${ctx}/News/create.action">�������</a><br><br>
		<a href="${ctx}/News/list.action">�����б�</a><br><br>
		<a href="${ctx}/Titles/create.action">���ר��</a><br><br>
		<a href="${ctx}/Titles/list.action">ר���б�</a><br><br>
		<a href="${ctx}/admin/resource/list.action">����Դ����</a><br><br>
		<a href="${ctx}/admin/template/list.action">ģ�����</a><br><br>
		<a href="${ctx}/Keywords/list.action">��ǩ����</a><br>
	</center>
    <a href="${ctx}/admin/block/list.action">�����</a>
    <a href="${ctx}/admin/template/list.action">ģ���б�</a>
	<a href="${ctx}/admin/resource/list.action">����Դ����</a>
	<a href="${ctx}/admin/html/list.action">��̬ҳ����</a>
	<a href="${ctx}/admin/procedure/list.action">�洢���̹���</a>
	
</body>
</html>