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
<title>添加一级分类</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/editor_config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/jquery-1.6.js"></script>      
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/editor_all.js"></script>   
<link rel="stylesheet" type="text/css" href="${ctx}/js/ueditor/themes/default/ueditor.css"/>   
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script type="text/javascript" src="${ctx}/js/singleCalendar/WdatePicker.js"></script>
<script type="text/javascript">
var integerReg = /^[1-9]\d*$/;
$(function(){
	$.ajax({
		url:"${ctx}/CategoryNavigation/getFirstCategories.action",							
		type:"POST",
		dataType:"text",
		async:false,
		success:function(data){	
			var models = eval("("+data+")");
			for(var i in models){
				$("#catId").append("<option value='"+models[i].categoryId+"'>"+models[i].categoryName+"</option>");
			}
		}
	});
	if(null != "${categoryFirst.catId}" && '' != "${categoryFirst.catId}"){
		$("#catId").val("${categoryFirst.catId}");
	}
	$("#reset").click(function() {
		setTimeout(function () {
			$("#catId").val("${categoryFirst.catId}");
		}, 20);
	});
	$("form").submit(function(){
		var sorts = $("#sorts").val().trim();
		var catId = $("#catId").val();
		var result = true;
		$.ajax({
			url:"${ctx}/CategoryNavigation/checkFirstCatId.action",
			type:"POST",
			data:{
				catId:catId,
				groupId:$("#groupId").val(),
				firstId:$("#id").val()
			},
			dataType:"text",
			async:false,
			success:function(data){	
				if(data == "1"){
					alert("该一级分类已经存在！");
					$('#catId').focus();
					result = false;
				}
			}
		});
		if(!result){
			return false;
		}
		if(sorts==""){
			alert("请填写排序！");
			$('#sorts').focus();
			return false;
		}else if(!integerReg.test(sorts)){
			alert("请正确填写排序！");
			$('#sorts').focus();
			return false;
		}else if(sorts>32767){
			alert("排序数值太大！");
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
			<c:if test="${categoryFirst.id == null}">
				<h3>添加一级分类</h3>
			</c:if>
			<c:if test="${categoryFirst.id != null}">
				<h3>编辑一级分类</h3>
			</c:if>
			<h3><a href="${ctx}/CategoryNavigation/firstList.action?groupId=${categoryFirst.groupId}">返回上一级</a></h3>
		</div>

		<div class="bd clearfix">
			<div class="container-1">
				<form action="${ctx}/CategoryNavigation/firstSave.action" method="post">
					<s:hidden name="command"></s:hidden>
					<input type="hidden" id="id" name="categoryFirst.id" value="${categoryFirst.id}"/>
					<input type="hidden" id="groupId" name="categoryFirst.groupId" value="${categoryFirst.groupId}"/>
					<table class="tb-1">
						<tbody>
							<tr>
								<th>一级分类：</th>
								<td>
									<select class="txt-9" id="catId" name="categoryFirst.catId">
									</select>
								</td>
							</tr>
							<tr>
								<th>排序：</th>
								<td><input type="text" class="txt-9" id="sorts" name="categoryFirst.sorts" value="${categoryFirst.sorts}"/>
								</td>
							</tr>
							<tr>
								<th>状态：</th>
								<td>
									<input name="categoryFirst.status" type="radio" value=1 <c:if test="${categoryFirst.status == 1}">checked</c:if> />启用
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input name="categoryFirst.status" type="radio" value=0 <c:if test="${categoryFirst.status == 0}">checked</c:if> />禁用
								</td>
							</tr>
							<tr>
								<th></th>
								<td>
									<input type="submit" value="保存" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input id="reset" type="reset" value="重置" />
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