<%@page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="coo8" uri="/coo8-tag"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>��ӷ�������</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/editor_config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/jquery-1.6.js"></script>      
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/editor_all.js"></script>   
<link rel="stylesheet" type="text/css" href="${ctx}/js/ueditor/themes/default/ueditor.css"/>   
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script type="text/javascript" src="${ctx}/js/singleCalendar/WdatePicker.js"></script>
<script type="text/javascript">
var integerReg = /^[1-9]\d*$/;
$(function(){
	function secondChange(){
		$("#secondId").empty();
		$("#secondId").append("<option value=''>��������</option>");
		$.ajax({
			url:"${ctx}/CategoryNavigation/getSecondCategories.action?catId=" + $('#firstId').val(),
			type:"GET",
			dataType:"text",
			async:false,
			success:function(data){	
				var models = eval("("+data+")");
				for(var i in models){
					$("#secondId").append("<option value='"+models[i].categoryId+"'>"+models[i].categoryName+"</option>");
				}
			}
		});
	}
	$.ajax({
		url:"${ctx}/CategoryNavigation/getFirstCategoryByGroupId.action?groupId=" + $("#groupId").val(),
		type:"POST",
		dataType:"text",
		async:false,
		success:function(data){	
			var models = eval("("+data+")");
			for(var i in models){
				$("#firstId").append("<option value='"+models[i].categoryId+"'>"+models[i].categoryName+"</option>");
			}
		}
	});
	if(null != "${categoryThird.firstId}" && '' != "${categoryThird.firstId}"){
		$("#firstId").val("${categoryThird.firstId}");
		secondChange();
	}
	$('#firstId').change(function(){
		secondChange();
	});
	if(null != "${categoryThird.secondId}" && '' != "${categoryThird.secondId}"){
		$("#secondId").val("${categoryThird.secondId}");
	}
	$("#reset").click(function() {
		setTimeout(function () {
			$("#firstId").val("${categoryThird.firstId}");
			secondChange();
			$("#secondId").val("${categoryThird.secondId}");
		}, 20);
	});
	$("form").submit(function(){
		var firstId = $("#firstId").val();
		var secondId = $("#secondId").val();
		var extendName = $("#extendName").val().trim();
		var extendLink = $("#extendLink").val().trim();
		var sorts = $("#sorts").val().trim();
		if(firstId==""){
			alert("��ѡ��һ�����࣡");
			$('#firstId').focus();
			return false;
		}
		if(secondId==""){
			alert("��ѡ��������࣡");
			$('#secondId').focus();
			return false;
		}
		if(extendName==""){
			alert("����д�������ಹ�䣡");
			$('#extendName').focus();
			return false;
		}else{
			var result = true;
			$.ajax({
				url:"${ctx}/CategoryNavigation/checkThirdCategory.action",
				type:"POST",
				data:{
					extendName:extendName,
					subCatId:secondId,
					catId:firstId,
					groupId:$("#groupId").val(),
					thirdId:$("#id").val()
				},
				dataType:"text",
				async:false,
				success:function(data){	
					if(data == "1"){
						alert("���������ಹ���Ѿ����ڣ�");
						$('#extendName').focus();
						result = false;
					}
				}
			});
			if(!result){
				return false;
			}
		}
		if(extendLink==""){
			alert("����д��Ӧ���ӣ�");
			$('#extendLink').focus();
			return false;
		}else if(extendLink.indexOf("//")!=0){
			alert("���ӵ�ַ������//��ͷ��");
			$('#extendLink').focus();
			return false;
		}
		if(sorts==""){
			alert("����д����");
			$('#sorts').focus();
			return false;
		}else if(!integerReg.test(sorts)){
			alert("����ȷ��д����");
			$('#sorts').focus();
			return false;
		}else if(sorts>32767){
			alert("������ֵ̫��");
			$('#sorts').focus();
			return false;
		}
	});
});
</script>

</head>
<body>
	<div class="mod-1">
		<div class="hd">
			<c:if test="${categoryThird.id == null}">
				<h3>��ӷ�������</h3>
			</c:if>
			<c:if test="${categoryThird.id != null}">
				<h3>�༭��������</h3>
			</c:if>
			<h3><a href="${ctx}/CategoryNavigation/thirdList.action?groupId=${categoryThird.groupId}">������һ��</a></h3>
		</div>

		<div class="bd clearfix">
			<div class="container-1">
				<form action="${ctx}/CategoryNavigation/thirdSave.action" method="post">
					<s:hidden name="command"></s:hidden>
					<input type="hidden" id="id" name="categoryThird.id" value="${categoryThird.id}"/>
					<input type="hidden" id="groupId" name="categoryThird.groupId" value="${categoryThird.groupId}"/>
					<table class="tb-1">
						<tbody>
							<tr>
								<th>һ�����ࣺ</th>
								<td>
									<select class="txt-9" id="firstId" name="categoryThird.firstId">
										<option value="">һ������</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>�������ࣺ</th>
								<td>
									<select class="txt-9" id="secondId" name="categoryThird.secondId">
										<option value="">��������</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>�������ಹ�䣺</th>
								<td>
									<input type="text" class="txt-9" id="extendName" name="categoryThird.extendName" value="${categoryThird.extendName}"/>
								</td>
								<th>��Ӧ���ӣ�</th>
								<td>
									<input type="text" class="txt-9" id="extendLink" name="categoryThird.extendLink" value="${categoryThird.extendLink}"/>
									(���ӵ�ַ��//��ͷ)
								</td>
							</tr>
							<tr>
								<th>����</th>
								<td><input type="text" class="txt-9" id="sorts" name="categoryThird.sorts" value="${categoryThird.sorts}"/>
								</td>
							</tr>
							<tr>
								<th>״̬��</th>
								<td>
									<input name="categoryThird.status" type="radio" value=1 <c:if test="${categoryThird.status == 1}">checked</c:if> />����
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input name="categoryThird.status" type="radio" value=0 <c:if test="${categoryThird.status == 0}">checked</c:if> />����
								</td>
							</tr>
							<tr>
								<th>��죺</th>
								<td>
									<input name="categoryThird.markRed" type="radio" value=0 <c:if test="${categoryThird.markRed == 0}">checked</c:if> />N
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input name="categoryThird.markRed" type="radio" value=1 <c:if test="${categoryThird.markRed == 1}">checked</c:if> />Y
								</td>
							</tr>
							<tr>
								<th></th>
								<td>
									<input type="submit" value="����" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input id="reset" type="reset" value="����" />
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>