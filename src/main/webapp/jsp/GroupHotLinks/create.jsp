<%@page import="com.coo8.topic.model.*"%>
<%@page import="com.coo8.topic.controller.action.TitlesAction"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
<title>¥�����</title>
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
				alert('����д��������!');
				$('#LINK_NAME').focus();
				return false;
			}
			if (LINK_URL == '') {
				alert('����д��������');
				$('#LINK_URL').focus();
				return false;
			}
			if (PRIORITY == '') {
				alert('���ȼ�����Ϊ��');
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
					<h3>�����������</h3>
				</c:if>
				<c:if test="${groupHotLinks.ID > 0 }">
					<h3>�༭��������</h3>
				</c:if>
				<h3>
					<a href="/GroupHotLinks/list.action">������һ��</a>
				</h3>
			</div>
			<div class="bd clearfix">
				<div class="container-1">
					<c:if test="${groupHotLinks==null && groupHotLinks.ID ==null}">
						<h3>�����������</h3>
					</c:if>
					<c:if test="${groupHotLinks.ID > 0 }">
						<h3>�༭��������</h3>
					</c:if>
					<div class="line-1"></div>
					<table class="tb-1">
						<tbody>
							<tr>
								<th>����id��</th>
								<td><input type="text" class="txt-7" id="GROUP_ID"
									name="groupHotLinks.GROUP_ID"
									value="${groupHotLinks.GROUP_ID}" /> </td>
							</tr>
							<tr>
								<th>�����飺</th>
								<td><input type="text" class="txt-7" id="GROUP_NAME"
									name="groupHotLinks.GROUP_NAME"
									value="${groupHotLinks.GROUP_NAME}" /></td>
							</tr>
							<tr>
								<th>�������ƣ�</th>
								<td><input type="text" class="txt-7" id="linkName"
									name="groupHotLinks.LINK_NAME"
									value="${groupHotLinks.LINK_NAME }" /><span style="color: red;">*(����)</span></td>
							</tr>
							<tr>
								<th>���ӵ�ַ��</th>
								<td><input type="text" class="txt-7" id="LINK_URL"
									name="groupHotLinks.LINK_URL"
									value="${groupHotLinks.LINK_URL }" /><span style="color: red;">*(����)</span></td>
							</tr>
							<tr>
								<th>���ȼ���</th>
								<td><input type="text" class="txt-7" id="PRIORITY"
									name="groupHotLinks.PRIORITY" value="${groupHotLinks.PRIORITY}" /><span
									style="color: red;">*(����)���ȼ���ֵԽ�����ȼ�Խ��</span></td>
							</tr>
							<tr>
								<th>�Ƿ����ã�</th>
								<td><s:select id="isValid" name="groupHotLinks.IS_VALID"
										value="groupHotLinks.IS_VALID" theme="simple"
										list="#{'false':'ͣ��','true':'����' }">
									</s:select></td>
							</tr>
						</tbody>
					</table>
					<div class="But">
						<input type="submit" value="����" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="����"
							onclick="location.href='${ctx}/GroupHotLinks/list.action'" />
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>