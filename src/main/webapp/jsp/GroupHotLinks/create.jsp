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
			var linkName = $('#linkName').val();
			var LINK_URL = $('#LINK_URL').val();
			var PRIORITY = $('#PRIORITY').val();
			if (linkName == '') {
				alert('请填写热门名称!');
				$('#LINK_NAME').focus();
				return false;
			}
			if (LINK_URL == '') {
				alert('请填写热门链接');
				$('#LINK_URL').focus();
				return false;
			}
			if (PRIORITY == '') {
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

	<form name="form" action="${ctx}/GroupHotLinks/saveGroupHotLinks.action"method="post">
		<input type="hidden" id="groupHotLinks" name="groupHotLinks.ID"
			value="${GroupHotLinks.ID}" />
		<div class="mod-1">
			<div class="hd">
				<c:if test="${groupHotLinks==null && groupHotLinks.ID ==null}">
					<h3>添加热门链接</h3>
				</c:if>
				<c:if test="${groupHotLinks.ID > 0 }">
					<h3>编辑热门链接</h3>
				</c:if>
				<h3>
					<a href="/GroupHotLinks/list.action">返回上一级</a>
				</h3>
			</div>
			<div class="bd clearfix">
				<div class="container-1">
					<c:if test="${groupHotLinks==null && groupHotLinks.ID ==null}">
						<h3>添加热门链接</h3>
					</c:if>
					<c:if test="${groupHotLinks.ID > 0 }">
						<h3>编辑热门链接</h3>
					</c:if>
					<div class="line-1"></div>
					<table class="tb-1">
						<tbody>
							<tr>
								<th>分组id：</th>
								<td><input type="text" class="txt-7" id="GROUP_ID"
									name="groupHotLinks.GROUP_ID"
									value="${groupHotLinks.GROUP_ID}" /> </td>
							</tr>
							<tr>
								<th>分组简介：</th>
								<td><input type="text" class="txt-7" id="GROUP_NAME"
									name="groupHotLinks.GROUP_NAME"
									value="${groupHotLinks.GROUP_NAME}" /></td>
							</tr>
							<tr>
								<th>链接名称：</th>
								<td><input type="text" class="txt-7" id="linkName"
									name="groupHotLinks.LINK_NAME"
									value="${groupHotLinks.LINK_NAME }" /><span style="color: red;">*(必填)</span></td>
							</tr>
							<tr>
								<th>链接地址：</th>
								<td><input type="text" class="txt-7" id="LINK_URL"
									name="groupHotLinks.LINK_URL"
									value="${groupHotLinks.LINK_URL }" /><span style="color: red;">*(必填)</span></td>
							</tr>
							<tr>
								<th>优先级：</th>
								<td><input type="text" class="txt-7" id="PRIORITY"
									name="groupHotLinks.PRIORITY" value="${groupHotLinks.PRIORITY}" /><span
									style="color: red;">*(必填)优先级，值越大优先级越高</span></td>
							</tr>
							<tr>
								<th>是否启用：</th>
								<td><s:select id="isValid" name="groupHotLinks.IS_VALID"
										value="groupHotLinks.IS_VALID" theme="simple"
										list="#{'false':'停用','true':'启用' }">
									</s:select></td>
							</tr>
						</tbody>
					</table>
					<div class="But">
						<input type="submit" value="保存" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="返回"
							onclick="location.href='${ctx}/GroupHotLinks/list.action'" />
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>