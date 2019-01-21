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
<title>添加分组</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/editor_config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/jquery.2.1.1.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/jquery.form.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/bootstrap.3.3.7.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/editor_all.js"></script> 
<script type="text/javascript" charset="utf-8" src="${ctx}/js/singleCalendar/WdatePicker.js"></script>
<link rel="stylesheet" href="${ctx}/styles/bootstrap.3.3.7.min.css"/>  
<link rel="stylesheet" href="${ctx}/js/ueditor/themes/default/ueditor.css"/>   
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script type="text/javascript">
var integerReg = /^[1-9]\d*$/;
var imgIndex = 1;
$(function(){
	$("#category_form").submit(
		function(){
			var names = $("#names").val().trim();
			var sorts = $("#sorts").val().trim();
			var defaultImg = $("#defaultImgCopy").val().trim();
			var hoverImg = $("#hoverImgCopy").val().trim();
			var id = $("#id").val();
			if(names==""){
				alert("请填写分组名称！");
				$('#names').focus();
				return false;
			}else if(names.replace(/[^\x00-\xff]/g, "aa").length > 12){
				alert("分组名称限制12个字符，6个汉字！");
				$('#names').focus();
				return false;
			}else{
				var result = true;
				$.ajax({
					url:"${ctx}/CategoryNavigation/checkNames.action?names=" + names + "&id=" + id,
					type:"GET",
					dataType:"text",
					async:false,
					success:function(data){	
						if(data == "1"){
							alert("该分组名称已经存在！");
							$('#names').focus();
							result = false;
						}
					}
				});
				if(!result){
					return false;
				}
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
			if(defaultImg==""){ 
				alert("请上传图片！");
				return false;
			}
			if(hoverImg==""){ 
				alert("请上传hover图片！");
				return false;
			}
	});
	$("#add_default_img").click(function() {
		imgIndex = 1;
		$("#file").val("");
		$("#myModal").modal('show');
	});
	$("#add_hover_img").click(function() {
		imgIndex = 2;
		$("#file").val("");
		$("#myModal").modal('show');
	});
	$("#reset").click(function() {
		$("#defaultImg").val("${category.defaultImg}");
		$("#hoverImg").val("${category.hoverImg}");
	});
});

function uploadImageFile(){
	var options = {
		url:"${ctx}/CategoryNavigation/upload.action",  
        type:"post",  
        enctype:'multipart/form-data',
        async:false,  
        success:function(data) {
        	var dataObj = eval("(" + data.substring(1, data.length-1) + ")");  // 转换为json对象
        	if(dataObj.status=="success"){
        		alert("上传成功！");
        		$("#myModal").modal('hide');
        		if(imgIndex==1){
        			$("#defaultImg").val(dataObj.result);
        			$("#defaultImgCopy").val(dataObj.result);
        		}else if(imgIndex==2){
        			$("#hoverImg").val(dataObj.result);
        			$("#hoverImgCopy").val(dataObj.result);
        		}
        	}else{
        		alert("上传失败！");
        		return false;
        	}
        },
        error:function(data) {  
            alert("上传出错！");
        }
	};
	var file = $("#file").val();
	if(file==""){
		alert("请选择图片！");
		return false;
	}else{
		if(file.indexOf(".")<=0){
			alert("图片格式错误！");
			return false;
		}
		var extName = file.substring(file.lastIndexOf(".")+1);
		if(extName!="jpg" && extName!="jpeg" && extName!="png"){
			alert("图片格式错误！");
			return false;
		}
	}
	$("#add_image_form").ajaxSubmit(options);
}
</script>
<style type="text/css">
	.mod-1 .hd h3 {
	    padding-top: 10px;
	}
</style>
</head>
<body>
	<div class="mod-1">
		<div class="hd">
			<c:if test="${category.id == null}">
				<h3>添加分组</h3>
			</c:if>
			<c:if test="${category.id != null}">
				<h3>编辑分组</h3>
			</c:if>
			<h3><a href="${ctx}/CategoryNavigation/list.action">返回上一级</a></h3>
		</div>

		<div class="bd clearfix">
			<div class="container-1">
				<form id="category_form" action="${ctx}/CategoryNavigation/save.action" method="post">
					<s:hidden name="command"></s:hidden>
					<input id="id" name="category.id" value="${category.id}" type="hidden" />
					<table class="tb-1">
						<tbody>
							<tr>
								<th>分组名称：</th>
								<td><input id="names" name="category.names" value="${category.names}" type="text" class="txt-9" placeholder="限制12个字符，6个汉字"/>
								</td>
							</tr>
							<tr>
								<th>排序：</th>
								<td><input type="text" class="txt-9" id="sorts" name="category.sorts" value="${category.sorts}"/>
								</td>
							</tr>
							<tr>
								<th>状态：</th>
								<td>
									<input name="category.status" type="radio" value=1 <c:if test="${category.status == 1}">checked</c:if> />启用
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input name="category.status" type="radio" value=0 <c:if test="${category.status == 0}">checked</c:if> />禁用
								</td>
							</tr>
							<tr>
								<th>图片：</th>
								<td><input type="text" class="txt-9" id="defaultImgCopy" value="${category.defaultImg}" disabled="disabled"/>
									<input type="hidden" id="defaultImg" name="category.defaultImg" value="${category.defaultImg}"/>
									<input type="button" value="上传图片" id="add_default_img"/>
								</td>
							</tr>
							<tr>
								<th>图片hover状态：</th>
								<td><input type="text" class="txt-9" id="hoverImgCopy" value="${category.hoverImg}" disabled="disabled"/>
									<input type="hidden" id="hoverImg" name="category.hoverImg" value="${category.hoverImg}"/>
									<input type="button" value="上传图片" id="add_hover_img"/>
								</td>
							</tr>
							<tr>
								<th></th>
								<td>
									<input type="submit" value="保存" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" value="重置" id="reset"/>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form id="add_image_form" name="add_image_form" method="post" enctype="multipart/form-data">
						<div class="modal-header">
							<h4 class="modal-title" id="myModalLabel">
								上传图片
							</h4>
						</div>
						<div class="modal-body">
							<table style="width:100%" id="imgTable" class="gome_pop_table">
								<tr>
									<td width="80" class="text-right">图片：</td>
									<td class="text-left">
										<input id="file" name="file" type="file"/>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td>
										(一次上传一张图片，可用扩展名:jpg,jpeg,png)
									</td>
								</tr>
							</table>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭
							</button>
							<button type="button" class="btn btn-primary" onClick="uploadImageFile();">
								上传
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>