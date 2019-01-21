<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.coo8.topic.model.*"%>
<%@page import="com.coo8.topic.controller.action.TitlesAction"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
<title>专题索引修改</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script type="text/javascript" charset="GBK" src="${ctx}/js/jquery-1.6.js"></script> 
<script type="text/javascript">
function validateIndexUpdate(){
	var cindex = $('#cindex').val();
	var priority = $('#priority').val();
	if(cindex == ''){
		alert('专题索引为空，请重新填写');
		$('#cindex').focus();
		return false;
	}
	if(priority == ''){
		alert('专题优先级为空，请重新填写');
		$('#priority').focus();
		return false;
	}
}
</script>
</head>
  <body>
    <div class="mod-1">
		<div class="hd">
			<h3>专题索引修改</h3>
		</div>
		<div class="bd clearfix">
		<div class="container-1">
		<h3>基本信息</h3>
		<div class="line-1"></div>
		 <form name="titleIndexUpdateform" action="${ctx}/Titles/titleIndexUpdate.action" method="post" onsubmit="return validateIndexUpdate();">
			<table class="tb-1" style="margin-left: 30px;" width="400">
				<tbody>
				<tr>
					<td width="100">专题编号：</td>
				    <td>${titleIndex.titleId }<input type="hidden" name="titleIndex.id" value="${titleIndex.id }" /></td>
				</tr>
				<tr>
				    <td>专题名称：</td>
				    <td>${titleIndex.title }</td>
				</tr>
				<tr>
				    <td>专题索引：</td>
				    <td><s:select id="cindex" name="titleIndex.cindex" theme="simple" value="titleIndex.cindex" list="#{'':'','A':'A','B':'B','C':'C','D':'D','E':'E','F':'F','G':'G','H':'H','I':'I','J':'J','K':'K','L':'L','M':'M','N':'N',
							'O':'O','P':'P','Q':'Q','R':'R','S':'S','T':'T','U':'U','V':'V','W':'W','X':'X','Y':'Y','Z':'Z','0':'0','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9'}">
						</s:select>
					</td>
				</tr>
				<tr>
				  	<td>&nbsp;优先级&nbsp;：</td>
					<td><s:select id="priority" name="titleIndex.priority" theme="simple" value="titleIndex.priority" list="#{'':'','0':'低','1':'中','2':'高'}"></s:select></td>
				</tr>
				<tr>
				  	<td>&nbsp;操作者&nbsp;：</td>
					<td>${titleIndex.operator }<input type="hidden" name="titleIndex.operator" value="${titleIndex.operator }" /></td>
				</tr> 
				<tr>
				  	<td>专题状态：</td>
					<td>
						<s:select id="status" name="titleIndex.status" value="titleIndex.status" theme="simple" list="#{'0':'失效','1':'有效' }">
						</s:select>
					</td>
				</tr> 
				<tr>
				  	<td>专题站点：</td>
					<td>
						<c:choose>
							<c:when test="${titleIndex.site eq 'gome'}">国美</c:when>
							<c:otherwise>库巴</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<input type="button" value="返回" onclick="location.href='${ctx}/Titles/listTitleIndex.action'"/> 
						<input type="submit" value="修改" />
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
