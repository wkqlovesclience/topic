<%@page import="com.coo8.topic.model.*"%>
<%@page import="com.coo8.topic.labelart.action.LabelAction"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
<title>������±�ǩ����</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script type="text/javascript" charset="GBK" src="${ctx}/js/jquery-1.6.js"></script>  
<script type="text/javascript">
var isTitleIndexExistFlag = false;
function getTitleName(){
	var titleId = $('#title_id').val();
	if(titleId == ''){
		return;
	}
	$.post("${ctx}/Label/getLabelName.action?titleId="+titleId,
		function (msg){
			if(msg.indexOf('ERROR') == -1){
				document.getElementById("titleNameDiv").innerHTML = msg;
				document.getElementById("title_name").value = msg;
				isTitleIndexExistFlag = true;
			}
			else{
				document.getElementById("titleNameDiv").innerHTML = "<font color='red'>"+msg.substr(6)+"</font>";
			}
		}
	);
}
function validateIndexAdd(){
	var titleId = $('#title_id').val();
	var cindex = $('#cindex').val();
	var priority = $('#priority').val();
	
	if(titleId == ''){
		alert('���Ϊ�գ���������д');
		$('#title_id').focus();
		return false;
	} 
	if(isTitleIndexExistFlag == false){
		var info = document.getElementById("titleNameDiv").innerText;
		alert(info);
		return false;
	}
	if(cindex == ''){
		alert('����Ϊ�գ���������д');
		$('#cindex').focus();
		return false;
	}
	if(priority == ''){
		alert('���ȼ�Ϊ�գ���������д');
		$('#priority').focus();
		return false;
	}
}
function onReset(){
	document.getElementById("titleNameDiv").innerHTML = "";
}
</script>
</head>
  
  <body>
  <div class="mod-1">
		<div class="hd">
			<h3>�������</h3>
		</div>
	    <div class="bd clearfix">
			<div class="container-1">
				<form name="titleIndexAddform" action="${ctx}/Label/labelIndexAdd.action" method="post" onsubmit="return validateIndexAdd();" onreset="onReset()">
				<table class="tb-1" style="margin-left: 20px;" width="100%">
					<tbody>
					<tr>
						<td>��ǩ��ţ�</td>
						<td>
						<div style="float: left;"><input type="text" id="title_id" name="labelIndex.labelId" value="" onblur="getTitleName()" /></div>
						<div id="titleNameDiv" style="float: left;margin-left: 10px;"></div>
						<input type="hidden" id="title_name" name="labelIndex.title" value=""/>
						</td>
					</tr>
					<tr>
						<td width="100">������</td>
						<td><s:select id="cindex" name="labelIndex.cindex" theme="simple" list="#{'':'','A':'A','B':'B','C':'C','D':'D','E':'E','F':'F','G':'G','H':'H','I':'I','J':'J','K':'K','L':'L','M':'M','N':'N',
					'O':'O','P':'P','Q':'Q','R':'R','S':'S','T':'T','U':'U','V':'V','W':'W','X':'X','Y':'Y','Z':'Z','0':'0','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9'}"></s:select>
						</td>
					</tr>
					<tr>
						<td>���ȼ���</td>
						<td>
						<s:select id="priority" name="labelIndex.priority" theme="simple" list="#{'':'','0':'��','1':'��','2':'��'}">
						</s:select>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="reset" value="����" /> <input type="submit" value="�ύ" /> </td>
					</tr>
					</tbody>
				</table>
    			</form>
			</div>
		</div>
  </div> 
  </body>
  
</html>
