<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.coo8.topic.model.*"%>
<%@page import="com.coo8.topic.controller.action.TitlesAction"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
<title>ר�������޸�</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script type="text/javascript" charset="GBK" src="${ctx}/js/jquery-1.6.js"></script> 
<script type="text/javascript">
function validateIndexUpdate(){
	var cindex = $('#cindex').val();
	var priority = $('#priority').val();
	if(cindex == ''){
		alert('ר������Ϊ�գ���������д');
		$('#cindex').focus();
		return false;
	}
	if(priority == ''){
		alert('ר�����ȼ�Ϊ�գ���������д');
		$('#priority').focus();
		return false;
	}
}
</script>
</head>
  <body>
    <div class="mod-1">
		<div class="hd">
			<h3>ר�������޸�</h3>
		</div>
		<div class="bd clearfix">
		<div class="container-1">
		<h3>������Ϣ</h3>
		<div class="line-1"></div>
		 <form name="titleIndexUpdateform" action="${ctx}/Titles/titleIndexUpdate.action" method="post" onsubmit="return validateIndexUpdate();">
			<table class="tb-1" style="margin-left: 30px;" width="400">
				<tbody>
				<tr>
					<td width="100">ר���ţ�</td>
				    <td>${titleIndex.titleId }<input type="hidden" name="titleIndex.id" value="${titleIndex.id }" /></td>
				</tr>
				<tr>
				    <td>ר�����ƣ�</td>
				    <td>${titleIndex.title }</td>
				</tr>
				<tr>
				    <td>ר��������</td>
				    <td><s:select id="cindex" name="titleIndex.cindex" theme="simple" value="titleIndex.cindex" list="#{'':'','A':'A','B':'B','C':'C','D':'D','E':'E','F':'F','G':'G','H':'H','I':'I','J':'J','K':'K','L':'L','M':'M','N':'N',
							'O':'O','P':'P','Q':'Q','R':'R','S':'S','T':'T','U':'U','V':'V','W':'W','X':'X','Y':'Y','Z':'Z','0':'0','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9'}">
						</s:select>
					</td>
				</tr>
				<tr>
				  	<td>&nbsp;���ȼ�&nbsp;��</td>
					<td><s:select id="priority" name="titleIndex.priority" theme="simple" value="titleIndex.priority" list="#{'':'','0':'��','1':'��','2':'��'}"></s:select></td>
				</tr>
				<tr>
				  	<td>&nbsp;������&nbsp;��</td>
					<td>${titleIndex.operator }<input type="hidden" name="titleIndex.operator" value="${titleIndex.operator }" /></td>
				</tr> 
				<tr>
				  	<td>ר��״̬��</td>
					<td>
						<s:select id="status" name="titleIndex.status" value="titleIndex.status" theme="simple" list="#{'0':'ʧЧ','1':'��Ч' }">
						</s:select>
					</td>
				</tr> 
				<tr>
				  	<td>ר��վ�㣺</td>
					<td>
						<c:choose>
							<c:when test="${titleIndex.site eq 'gome'}">����</c:when>
							<c:otherwise>���</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<input type="button" value="����" onclick="location.href='${ctx}/Titles/listTitleIndex.action'"/> 
						<input type="submit" value="�޸�" />
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
