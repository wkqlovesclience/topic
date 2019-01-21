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
<title>添加分类扩充</title>
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
		$("#secondId").append("<option value=''>二级分类</option>");
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
			alert("请选择一级分类！");
			$('#firstId').focus();
			return false;
		}
		if(secondId==""){
			alert("请选择二级分类！");
			$('#secondId').focus();
			return false;
		}
		if(extendName==""){
			alert("请填写三级分类补充！");
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
						alert("该三级分类补充已经存在！");
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
			alert("请填写对应链接！");
			$('#extendLink').focus();
			return false;
		}else if(extendLink.indexOf("//")!=0){
			alert("链接地址必须以//开头！");
			$('#extendLink').focus();
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
			<c:if test="${categoryThird.id == null}">
				<h3>添加分类扩充</h3>
			</c:if>
			<c:if test="${categoryThird.id != null}">
				<h3>编辑分类扩充</h3>
			</c:if>
			<h3><a href="${ctx}/CategoryNavigation/thirdList.action?groupId=${categoryThird.groupId}">返回上一级</a></h3>
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
								<th>一级分类：</th>
								<td>
									<select class="txt-9" id="firstId" name="categoryThird.firstId">
										<option value="">一级分类</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>二级分类：</th>
								<td>
									<select class="txt-9" id="secondId" name="categoryThird.secondId">
										<option value="">二级分类</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>三级分类补充：</th>
								<td>
									<input type="text" class="txt-9" id="extendName" name="categoryThird.extendName" value="${categoryThird.extendName}"/>
								</td>
								<th>对应链接：</th>
								<td>
									<input type="text" class="txt-9" id="extendLink" name="categoryThird.extendLink" value="${categoryThird.extendLink}"/>
									(链接地址以//开头)
								</td>
							</tr>
							<tr>
								<th>排序：</th>
								<td><input type="text" class="txt-9" id="sorts" name="categoryThird.sorts" value="${categoryThird.sorts}"/>
								</td>
							</tr>
							<tr>
								<th>状态：</th>
								<td>
									<input name="categoryThird.status" type="radio" value=1 <c:if test="${categoryThird.status == 1}">checked</c:if> />启用
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input name="categoryThird.status" type="radio" value=0 <c:if test="${categoryThird.status == 0}">checked</c:if> />禁用
								</td>
							</tr>
							<tr>
								<th>标红：</th>
								<td>
									<input name="categoryThird.markRed" type="radio" value=0 <c:if test="${categoryThird.markRed == 0}">checked</c:if> />N
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input name="categoryThird.markRed" type="radio" value=1 <c:if test="${categoryThird.markRed == 1}">checked</c:if> />Y
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