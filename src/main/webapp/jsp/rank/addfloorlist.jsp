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
			var categoryName = $('#categoryName').val();
			var categoryId = $('#categoryId').val();
			var priority = $('#priority').val();
			if (categoryName == '') {
				alert('请填写正确的三级分类名称!');
				$('#categoryId').focus();
				return false;
			}
			if (categoryId == '') {
				alert('请填写正确的三级分类id!');
				$('#categoryId').focus();
				return false;
			}
			if (priority == '') {
				alert('优先级不能为空');
				$('#priority').focus();
				return false;
			}
			
		});
		scroll(0, 0);
	});
</script>
</head>
<body>

	<form name="form" action="${ctx}/IndexFloor/saveIndexFloor.action"
		method="post">
		<input type="hidden" id="indexFloor" name="indexFloor.id"
			value="${indexFloor.id}" />
		<div class="mod-1">
			<div class="hd">
				<c:if test="${indexFloor==null && indexFloor.id ==null}">
					<h3>添加首页楼层分类</h3>
				</c:if>
				<c:if test="${indexFloor.id > 0 }">
					<h3>编辑首页楼层分类</h3>
				</c:if>
				<h3>
					<a href="/IndexFloor/list.action">返回上一级</a>
				</h3>
			</div>
			<div class="bd clearfix">
				<div class="container-1">
					<c:if test="${indexFloor==null && indexFloor.id ==null}">
						<h3>添加楼层分类</h3>
					</c:if>
					<c:if test="${indexFloor.id > 0 }">
						<h3>编辑楼层分类</h3>
					</c:if>
					<div class="line-1"></div>
					<table class="tb-1">
						<tbody>
							<tr>
								<th>三级分类名称：</th>
								<td><input type="text" class="txt-7" id="categoryName"
									name="indexFloor.category_name"
									value="${indexFloor.category_name}" /><span
									style="color: red;">*(必填)</span></td>
							</tr>
							<tr>
								<th>三级分类ID：</th>
								<td><input type="text" class="txt-7" id="categoryId"
									name="indexFloor.category_id"
									value="${indexFloor.category_id }" /><span style="color: red;">*(必填)</span></td>
							</tr>
							<tr>
								<th>优先级：</th>
								<td><input type="text" class="txt-7" id="priority"
									name="indexFloor.priority" value="${indexFloor.priority}" /><span
									style="color: red;">*(必填)优先级，值越大优先级越高</span></td>
							</tr>
							<tr>
								<th>是否启用：</th>
								<td><s:select id="isValid" name="indexFloor.is_valid"
										value="indexFloor.is_valid" theme="simple"
										list="#{'false':'停用','true':'启用' }">
									</s:select></td>
							</tr>
						</tbody>
					</table>
					<div class="But">
						<input type="submit" value="保存" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="返回"
							onclick="location.href='${ctx}/IndexFloor/list.action'" />
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>