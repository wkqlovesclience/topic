<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/admin/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>�����</title>
<%@ include file="/jsp/admin/common/css.jsp"%>
<%@ include file="/jsp/admin/common/js.jsp"%>
<%@ include file="/jsp/admin/common/common_admin_css.jsp" %>
</head>
<body style="background:#afb8bf;overflow-x:hidden;overflow-y:scroll;*overflow-y:hidden">
<table cellpadding="0" cellspacing="0">
    <tr>
	<td background="http://app.gomein.net.cn/topics/images/RigNav.gif" height="31">
	    <table cellspacing="0" cellpadding="0">
          <tr>						
		  <td class="TextRig"><img src="http://app.gomein.net.cn/topics/images/nav_3.gif" width="11" height="7" /></td>						
		  <td style="width:97%" class="NavText DText">�����</td>	
		</tr>
	</table>
	</td>
   </tr>   
	<tr>
     <td>
     <div class="RightBg">
	    <div class="H10"></div>
		<div class="RightTabTags">
						    <table cellspacing="0" cellpadding="0">
						         	<tr>
							           <td style="width:16px"></td>
							           <td style="width:110px; height:30px;border-bottom:none;" class="TextCen GDray LBoder fB">
							           		<a href="${ctx}/admin/block/list.action">���п�</a>
							          	</td>
							           <td style="width:5px"></td>
							           <td style="width:80px;border-bottom:none;" class="TextCen GDray LBoder fB">
							           		<a href="${ctx}/jsp/admin/block/admin_common_block_create.jsp">�½�ͨ�ÿ�</a>
						           	  </td>
							           	<td style="width:5px"></td>
							           <td style="width:80px;border-bottom:none;" class="TextCen GDray LBoder fB">
							           		<a href="${ctx}/admin/block/autoCreateRequest.action">�½��Զ���</a>
						           	  </td>
							           	<td style="width:5px"></td>
							           	<td style="width:110px;border-bottom:none;" class="TextCen GDDDray Boder fB">
							           		<a style="color:white" href="#">
							           			�޸�<s:property value="block.name"/>
							           		</a>
							           	</td>
							           	<td>&nbsp;</td>
						           	</tr>
						    </table>
						  </div>
						  <div class="RightTab">
							<form action="${ctx}/admin/block/autoBlockUpdate.action" method="post" enctype="multipart/form-data" id="blockFormId" onsubmit="YUTIL.trimFormVal(this.id)">
								<table cellpadding="0" cellspacing="0"> 
								<tr>
									<td class="TextRig DText" style="width:10%; height:30px">���֣�</td> 
									<td class="TextLeft DText">
										&nbsp;<input name="block.id" value="<s:property value='block.id' />" type="hidden" />
										<input name="block.type" value="<s:property value='block.type' />" type="hidden" />
										<s:property value="block.name" />
									</td> 
								</tr>
								<tr>
									<td class="TextRig DText" style="height:30px">��ʾ���֣�</td> 
									<td class="TextLeft DText">
										&nbsp;<input type="text" style="width:524px;text-align: left;" class="inbox" maxlength="30"  name="block.displayName" id="name"  value="<s:property value='block.displayName' />"/>
									</td> 
								</tr>
								<tr>
									<td class="TextRig DText" style="height:30px">����Դ��</td> 
									<td class="TextLeft DText">
										&nbsp;<select name="block.resourceId">
											<option value="">ѡ������Դ</option>
											<s:iterator value="resourceList">
												<option value="<s:property value='id' />"  <s:property value="%{id == block.resourceId ? 'selected' : '' }"/> ><s:property value="name"/></option>
											</s:iterator>
										</select>
									</td> 
								</tr>
								<tr>
									<td class="TextRig DText" style=" height:30px">����������</td> 
									<td class="TextLeft DText">
										&nbsp;<input name="block.dataNum" size="20" type="text" value="<s:property value='block.dataNum' />" />
									</td> 
								</tr>
								<tr>
									<td class="TextRig DText" style="height:30px">�ڼ�ҳ��</td> 
									<td class="TextLeft DText">
										&nbsp;<input name="block.page" size="20" type="text" value="<s:property value='block.page' />" />
									</td> 
								</tr>
								<tr>
									<td class="TextRig DText" style="height:80px">������</td> 
									<td class="TextLeft DText">
										&nbsp;<textarea onkeydown="if (this.value.length&gt;=500){if(event.keyCode != 8) event.returnValue=false;}" 
												style="width:824px;" rows="6" class="mulinbox1" name="block.description" 
												id="description"><s:property value="block.description"/></textarea>
									</td> 
								</tr>
								<tr>
									<td class="TextRig DText">״̬��</td> 
									<td class="TextLeft DText">
										&nbsp;<select name="block.status" id="status" style="float:left;">
											<option value="0">����</option>
	    									<option value="1" <s:property value='%{block.isDisabled() ? "selected" : ""}' /> >ͣ��</option>
	    								</select>
	    							</td> 
								</tr>
								<tr>
									<td class="TextRig DText" style="width:10%; height:80px">VM��</td> 
									<td class="TextLeft DText">
										&nbsp;<textarea rows="15" cols="100" id="content" name="block.content"><s:property value="block.content"/>  </textarea>
									</td> 
								</tr>
								<tr>
									<td colspan="2" class="TextCen DText">
										&nbsp;<input type="submit" value="����"/>&nbsp;&nbsp;
										<input type="button" value="Ԥ��" onclick="openWindow();"/>&nbsp;&nbsp;
										<input type="button" value="�����б�" onclick="window.location='${ctx}/admin/block/list.action'"/>
									</td>
								</tr>
								</table>
							</form>
						 </div>
	               </div>
 				</td>
    		</tr>
  	</table>
<script type="text/javascript">
function openWindow() {
    var newWindow = window.open('','newWindow','toolbar,resizable,scrollbars,dependent,width=620,height=420,left=150,top=80');
    if (newWindow != null){
    var windowHTML= document.getElementById('content').value;
     newWindow.document.write(windowHTML);
     newWindow.focus();
    }
}

var textUtil = {};
textUtil.insertText = function(c){
	var a = document.getElementById("content");
	var b = a.value.length;
	a.focus();
	if (typeof document.selection != "undefined") {
		document.selection.createRange().text = c;
	} else {
		a.value = a.value.substr(0, a.selectionStart) + c
				+ a.value.substring(a.selectionStart, b);
	}
}
textUtil.insertBlock = function(b) {
	var a = "";
	var c = parseInt(1000 * Math.random());
	if (b == 1) {
		a = '<block name="blockname' + c + '" type="0"/>';
	} else if(b == 2){
		a = '<block name="blockname' + c + '" type="1"/>';
	}
	textUtil.insertText(a);	
}
</script>
</body>
</html>

