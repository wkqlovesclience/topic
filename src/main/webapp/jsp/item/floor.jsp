<%@page import="com.coo8.item.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>¥�����</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script type="text/javascript" charset="GBK" src="${ctx}/js/jquery-1.6.js"></script> 
<script type="text/javascript">
	$(document).ready(function() {
	 	$('form').submit(function() {
	 	    var floorName = $('#floorName').val();
			var priority = $('#priority').val();
			
	 	    if(floorName==''){
		    	alert('����д¥������!');
		    	$('#floorName').focus();
		    	return false;
		    }
	 	    var re = /^[0-9]+$/;
		    if (!re.test(priority)){
		    	alert('����д��ȷ�����ȼ�!');
		    	$('#priority').focus();
				return false;
			}
	    });
	   scroll(0,0);
	});
</script>
</head>
<body>
	<form name="form" action="${ctx}/Item/saveFloor.action" method="post">
		<div class="mod-1">
			<div class="hd">
				<c:if test="${floor.id != null }">
					<h3>�༭¥��</h3>
				</c:if>
				<c:if test="${floor.id == null}">
					<h3>���¥��</h3>
				</c:if>
				<h3><a href="./listFloors.action?pageNumber=${pageNumber}">������һ��</a></h3>
			</div>

			<div class="bd clearfix">
				<div class="container-1">
					<table class="tb-1" style="margin-left: 20px;" width="100%">
						<tbody>
						<tr>
							<td>��ţ�</td>
							<td>
								<div style="float: left;">${floor != null ? floor.id : ''}<input type="hidden" name="floor.id"  value="${floor.id}"/></div>
							</td>
						</tr>
						<tr>
							<td width="100">¥�����ƣ�</td>
							<td><input type="text" class="txt-7" id="floorName"
									name="floor.floorName" value="${floor.floorName}"/></td>
						</tr>
						<tr>
							<td width="100">���ȼ���</td>
							<td><input type="text" class="txt-7" id="priority"
									name="floor.priority" value="${floor.priority}"/></td>
						</tr>
						<tr>
							<td>�Ƿ����ã�</td>
							<td>
							<s:select id="isValid" name="floor.isValid" theme="simple" list="#{'false':'ͣ��','true':'����'}">
							</s:select>
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<input type="submit" value="�ύ" />
								<input type="button" value="����" onclick="location.href='${ctx}/Item/listFloors.action?pageNumber=${pageNumber}'"/>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>