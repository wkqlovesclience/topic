<%@page import="com.coo8.topic.model.*"%>
<%@page import="com.coo8.topic.controller.action.TitlesAction"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
<title>楼层分类</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />

<script type="text/javascript" charset="GBK"
	src="${ctx}/js/ueditor/editor_config.js"></script>
<script type="text/javascript" charset="GBK"
	src="${ctx}/js/jquery-1.6.js"></script>
<script type="text/javascript" charset="GBK"
	src="${ctx}/js/ueditor/editor_all.js"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx}/js/ueditor/themes/default/ueditor.css" />
<script type="text/javascript">
	$(document).ready(function() {
	 	$('form').submit(function() {
	 	    var displayName = $('#displayName').val();
			var priority = $('#priority').val();
			
	 	    if(displayName==''){
		    	alert('请填写显示名称!');
		    	$('#displayName').focus();
		    	return false;
		    }
	 	    var re = /^[0-9]+$/;
		    if (!re.test(priority)){
		    	alert('请填写正确的优先级!');
		    	$('#priority').focus();
				return false;
			}
	    });
	   scroll(0,0);
	});
</script>
</head>
<body>

	<form name="form" action="${ctx}/Item/saveCategory.action"
		method="post">
		<input type="hidden" id="floor.id" name="floor.id" value="${floor.id}" />
		<div class="mod-1">
			<div class="hd">
				<c:if test="${category.id == 0  && category.floorId>0}">
						<h3>添加楼层分类</h3>
					</c:if>
					<c:if test="${category.id > 0 }">
						<h3>编辑楼层分类</h3>
					</c:if>
				<h3><a href="./listCategories.action?floor.id=${category.floorId}">返回上一级</a></h3>
			</div>
			<div class="bd clearfix">
				<div class="container-1">
					<c:if test="${category.id == 0  && category.floorId>0}">
						<h3>添加楼层分类</h3>
					</c:if>
					<c:if test="${category.id > 0 }">
						<h3>编辑楼层分类</h3>
					</c:if>
					<div class="line-1"></div>
					<table class="tb-1">
						<tbody>
							<c:if test="${category.id > 0 }">
							<tr>
								<th>分类id：</th>
								<td>${category.id}<input type="hidden" id="id" name="category.id" value="${category.id}" /></td>
							</tr>
							<tr>
								<th>楼层id：</th>
								<td>${category.floorId}<input type="hidden"	id="floorId" name="category.floorId" value="${category.floorId}" /></td>
							</tr>
							</c:if>
							<tr>
								<th>三级分类ID：</th>
								<td><input type="text" class="txt-7" id="categoryId"
									name="category.categoryId" value="${category.categoryId }" /></td>
							</tr>
							<tr>
								<th>显示名称：</th>
								<td><input type="text" class="txt-7" id="displayName"
									name="category.displayName" value="${category.displayName }" /></td>
							</tr>
							<tr>
								<th>优先级：</th>
								<td><input type="text" class="txt-7" id="priority"
									name="category.priority" value="${category.priority }" /></td>
							</tr>
							<tr>
								<th>是否启用：</th> 
								<td>  
	    					<s:select id="isValid" name="category.isValid" value="category.isValid" theme="simple" list="#{'false':'停用','true':'启用' }">
						</s:select>
								 </td>
							</tr>
						</tbody>
					</table>
					<div class="But">
						<input type="submit" value="保存" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="返回" onclick="location.href='${ctx}/Item/listCategories.action?floor.id=${floor.id}'"/>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>