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
<title>��ӷ���</title>
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
				alert("����д�������ƣ�");
				$('#names').focus();
				return false;
			}else if(names.replace(/[^\x00-\xff]/g, "aa").length > 12){
				alert("������������12���ַ���6�����֣�");
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
							alert("�÷��������Ѿ����ڣ�");
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
			if(defaultImg==""){ 
				alert("���ϴ�ͼƬ��");
				return false;
			}
			if(hoverImg==""){ 
				alert("���ϴ�hoverͼƬ��");
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
        	var dataObj = eval("(" + data.substring(1, data.length-1) + ")");  // ת��Ϊjson����
        	if(dataObj.status=="success"){
        		alert("�ϴ��ɹ���");
        		$("#myModal").modal('hide');
        		if(imgIndex==1){
        			$("#defaultImg").val(dataObj.result);
        			$("#defaultImgCopy").val(dataObj.result);
        		}else if(imgIndex==2){
        			$("#hoverImg").val(dataObj.result);
        			$("#hoverImgCopy").val(dataObj.result);
        		}
        	}else{
        		alert("�ϴ�ʧ�ܣ�");
        		return false;
        	}
        },
        error:function(data) {  
            alert("�ϴ�����");
        }
	};
	var file = $("#file").val();
	if(file==""){
		alert("��ѡ��ͼƬ��");
		return false;
	}else{
		if(file.indexOf(".")<=0){
			alert("ͼƬ��ʽ����");
			return false;
		}
		var extName = file.substring(file.lastIndexOf(".")+1);
		if(extName!="jpg" && extName!="jpeg" && extName!="png"){
			alert("ͼƬ��ʽ����");
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
				<h3>��ӷ���</h3>
			</c:if>
			<c:if test="${category.id != null}">
				<h3>�༭����</h3>
			</c:if>
			<h3><a href="${ctx}/CategoryNavigation/list.action">������һ��</a></h3>
		</div>

		<div class="bd clearfix">
			<div class="container-1">
				<form id="category_form" action="${ctx}/CategoryNavigation/save.action" method="post">
					<s:hidden name="command"></s:hidden>
					<input id="id" name="category.id" value="${category.id}" type="hidden" />
					<table class="tb-1">
						<tbody>
							<tr>
								<th>�������ƣ�</th>
								<td><input id="names" name="category.names" value="${category.names}" type="text" class="txt-9" placeholder="����12���ַ���6������"/>
								</td>
							</tr>
							<tr>
								<th>����</th>
								<td><input type="text" class="txt-9" id="sorts" name="category.sorts" value="${category.sorts}"/>
								</td>
							</tr>
							<tr>
								<th>״̬��</th>
								<td>
									<input name="category.status" type="radio" value=1 <c:if test="${category.status == 1}">checked</c:if> />����
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input name="category.status" type="radio" value=0 <c:if test="${category.status == 0}">checked</c:if> />����
								</td>
							</tr>
							<tr>
								<th>ͼƬ��</th>
								<td><input type="text" class="txt-9" id="defaultImgCopy" value="${category.defaultImg}" disabled="disabled"/>
									<input type="hidden" id="defaultImg" name="category.defaultImg" value="${category.defaultImg}"/>
									<input type="button" value="�ϴ�ͼƬ" id="add_default_img"/>
								</td>
							</tr>
							<tr>
								<th>ͼƬhover״̬��</th>
								<td><input type="text" class="txt-9" id="hoverImgCopy" value="${category.hoverImg}" disabled="disabled"/>
									<input type="hidden" id="hoverImg" name="category.hoverImg" value="${category.hoverImg}"/>
									<input type="button" value="�ϴ�ͼƬ" id="add_hover_img"/>
								</td>
							</tr>
							<tr>
								<th></th>
								<td>
									<input type="submit" value="����" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" value="����" id="reset"/>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
		<!-- ģ̬��Modal�� -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form id="add_image_form" name="add_image_form" method="post" enctype="multipart/form-data">
						<div class="modal-header">
							<h4 class="modal-title" id="myModalLabel">
								�ϴ�ͼƬ
							</h4>
						</div>
						<div class="modal-body">
							<table style="width:100%" id="imgTable" class="gome_pop_table">
								<tr>
									<td width="80" class="text-right">ͼƬ��</td>
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
										(һ���ϴ�һ��ͼƬ��������չ��:jpg,jpeg,png)
									</td>
								</tr>
							</table>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">�ر�
							</button>
							<button type="button" class="btn btn-primary" onClick="uploadImageFile();">
								�ϴ�
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>