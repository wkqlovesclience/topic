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
		     	<td class="TextRig"><img src="		" style="width:11" height="7" /></td>
             	<td style="width:97%" class="NavText DText">�����</td>	
			</tr>
		</table>
		</td>
    </tr>   
	<tr>
    	<td>
      	<div class="RightBg">
			<div class="RightTabTags">
			<table cellspacing="0" cellpadding="0">
				<tr>
					<td style="width:16px"></td>
					<td style="width:78px;border-bottom:none;" class="TextCen GDDDray Boder fB">
						<a style="color:white" href="#">��༭</a></td>
					<td>&nbsp;</td>
				</tr>
			</table>
			</div>
			<div class="RightTab">
			<table cellspacing="0" cellpadding="0">
				<tr>
					<td class="TextLeft fB GDray LBoder DText">&nbsp;&nbsp;�Զ���</td>
				</tr>
				<tr>
					<td class="TextLeft LBoder DText" style="border-top:none;">&nbsp;&nbsp;
					<s:iterator value="blockList" var="b">
						<s:if test="#b.isAutoBlock()">
						<!-- c:if test="${fn:indexOf(sessionScope.urls,'/admin/block/updateRequest.action')>=0}" -->
						<a style="line-height:26px" href="${ctx}/admin/block/updateRequest.action?templateId=<s:property value='templateId' />&blockId=<s:property value='id' />">
							<s:if test="#b.isEnabled()">
								<s:property value="name"/>
							</s:if>
							<s:else>
								<span style="width:100px;color:red;"><s:property value="name"/>��ͣ�ã�</span>
							</s:else>
							<s:if test="%{displayName != null}">
								<span style="width:100px;overflow:hidden;">(<s:property value="displayName"/>)</span>
							</s:if>
						</a> | 
						<!--/c:if -->
						</s:if>
					</s:iterator>
					</td>
				</tr>
				<tr class="H10"></tr>
				<tr>
					<td class="TextLeft fB GDray LBoder DText">&nbsp;&nbsp;�ֶ���</td>
				</tr>  
				<tr>
					<td class="TextLeft LBoder DText" style="border-top:none;">
					<s:iterator value="blockList" var="b">
					<s:if test="%{! #b.isAutoBlock()}">
						<a style="line-height:26px" href="${ctx}/admin/block/updateRequest.action?templateId=<s:property value='templateId' />&blockId=<s:property value='id' />">
							<s:if test="#b.isEnabled()">
								<s:property value="name"/>
							</s:if>
							<s:else>
								<span style="width:100px;color:red;"><s:property value="name"/>��ͣ�ã�</span>
							</s:else>
							<s:if test="%{displayName != null}"><span style="width:100px;overflow:hidden;">(<s:property value="displayName"/>)</span></s:if>
						</a> | 
					</s:if>
					</s:iterator>
					</td>
				</tr>
			</table>
			</div>
			<div class="H10"></div>
	    	<div class="RightTabTags">
				<table cellspacing="0" cellpadding="0">
					<tr>
						<td style="width:16px"></td>
						<td style="width:78px;border-bottom:none;" class="TextCen GDDDray Boder fB">
							<a style="color:white" href="#">�༭����</a></td>
						<td>&nbsp;</td>
					</tr>
				</table>
	 		</div>
			<div class="RightTab">
			<form id="act" value="" method="post" style="height:100%">
				<dt class="blockleft">
				<table cellpadding="0" cellspacing="0" class="LBoder">
  					<tr>
    					<td class="TextRig GDray DText LBodRit LBodBot" style="width:100px">�����ͣ�</td>
						<td class="TextLeft DText LBodBot">
							<input type="hidden" name="block.type" value="<s:property value='editedBlock.type' />"/>
   							<s:if test="%{editedBlock.isAutoBlock()}">
     							&nbsp;�Զ���
     						</s:if><s:if test="%{! editedBlock.isAutoBlock()}">
     							&nbsp;�ֶ���
    						</s:if>
						</td>
  					</tr>
  					<tr>
    					<td class="TextRig GDray DText LBodRit LBodBot">״̬��</td>
						<td class="TextLeft DText LBodBot">&nbsp;
							<select name="block.status" id="status">
								<option value="0" <s:property value="%{editedBlock.status == 0 ? 'selected' : ''}" /> >����</option>
								<option value="1" <s:property value="%{editedBlock.status == 1 ? 'selected' : ''}" /> >ͣ��</option>
							</select>
						</td>
  					</tr>
  					<s:if test="editedBlock.isAutoBlock()">
					<tr>
    					<td class="TextRig GDray DText LBodRit">��Դ���ͣ�</td>
    					<td class="TextLeft DText">&nbsp;
    						<select name="block.resourceId">
    							<option value="">��ѡ������Դ</option>
    							<s:iterator value="resourceList">
    								<option value="<s:property value='id' />" <s:property value="%{id == editedBlock.resourceId ? 'selected' : ''}" />><s:property value='name' /></option>
    							</s:iterator>
    						</select>
    					</td>
  					</tr>
  					</s:if>
				</table>
				<div class="H10"></div>
				<table cellpadding="0" cellspacing="0" class="LBoder">
   					<tr>
  						&nbsp;<input type="hidden" value="<s:property value='editedBlock.templateId' />" name="block.templateId" id="templateId"/>
  						&nbsp;<input type="hidden" value="<s:property value='editedBlock.id' />" name="block.id" id="id"/>
    					<td class="TextRig GDray DText LBodRit LBodBot" style="width:100px">�����֣�</td>
    					<td class="TextLeft DText LBodBot">
    						&nbsp;<input name="oldName" value="<s:property value='editedBlock.name'/>" type="hidden"/>
   							<s:if test='%{editedBlock.name.startsWith("blockname")}'>
    							&nbsp;<input type="text" maxlength="512" style="width: 150px;" value="<s:property value="editedBlock.name"/>" class="inbox" name="block.name" id="name"/>
   								<font color="red">Ӣ���ַ��������»��߷����ļ��ģ���һ�������޸�</font>
    						</s:if>
    						<s:else>
    							<s:property value="editedBlock.name"/>
   								<input type="hidden" readonly="readonly" value="<s:property value="editedBlock.name"/>" style="width: 390px;" class="inbox" name="block.name" id="code"/>
   							</s:else>
    					</td>
  					</tr>
  					<tr>
    					<td class="TextRig GDray DText LBodRit LBodBot">��ʾ���ƣ�</td>
    					<td class="TextLeft DText LBodBot">&nbsp;<input type="text" maxlength="512" style="width: 300px;" value="<s:property value="editedBlock.displayName"/>" class="inbox" name="block.displayName" id="displayName"/></td>
  					</tr>
					<tr>
						<td class="TextRig GDray DText LBodRit LBodBot">��ע��</td>
						<td class="TextLeft DText LBodBot">&nbsp;<input type="text" maxlength="25" style="width: 300px;" class="inbox" name="block.description" value="<s:property value="editedBlock.description"/>" id="description"/></td>
					</tr>
					<s:if test="editedBlock.isAutoBlock()">
					<tr >
						<td class="TextRig GDray DText LBodRit LBodBot">������������</td>
						<td class="TextLeft DText LBodBot">&nbsp;<input type="text" value="<s:property value="editedBlock.dataNum"/>" msg="������������Ϊ����" datatype="Number" require="true" maxlength="3" name="block.dataNum" id="dataNum"/></td>
					</tr>
					<tr >
						<td class="TextRig GDray DText LBodRit">�ڼ�ҳ��</td>
						<td class="TextLeft DText">&nbsp;<input type="text" value="<s:property value="editedBlock.page"/>" msg="������������Ϊ����" datatype="Number" require="true" maxlength="3" name="block.page" id="page"/></td>
					</tr>
					</s:if>
				</table>
				</dt>
				<dd class="blockright">
				</dd>
				<div class="blockall">
				<table cellpadding="0" cellspacing="0">
					<tr>
						<s:if test="editedBlock.isAutoBlock()">
						<td style="width:90px; vertical-align:top" class="TextRig">VM��ʽ��&nbsp;</td>
						<td class="TextLeft">
							<textarea rows="20" msg="��������VM��ʽ" datatype="Require" style="width:545px;" class="mulinbox1" name="block.content" id="vmContent"><s:property value="editedBlock.content"/></textarea>
						</td>
						</s:if><s:else>
						<td style="width:90px; vertical-align:top" class="TextRig">��̬html��&nbsp;</td>
						<td class="TextLeft">
							<table cellspacing="0" cellpadding="0" class="LBoder">
								<tr>
									<td class="TextLeft">
										<textarea rows="20" msg="��������html" datatype="Require" style="width:545px;" class="mulinbox1" name="block.content" id="vmContent">
											<s:property value="editedBlock.content"/>
										</textarea>
									</td>
								</tr>
							</table>
						</td>
						</s:else>
					</tr>
					<tr>
						<td></td>
						<td class="TextLeft">
							<input type="button" onclick="document.getElementById('act').action='${ctx}/admin/block/saveAndContinue.action';document.getElementById('act').submit();" value="���沢�����༭" class="sub12" name="reset" id="frm019"/>&nbsp;&nbsp;
							<input type="button" onclick="document.getElementById('act').action='${ctx}/admin/block/update.action';document.getElementById('act').submit();" value="���沢����" class="sub12" name="reset" id="frm020"/>&nbsp;&nbsp;
							<input type="button" value="����" class="sub12" onclick="window.location='${ctx}/admin/template/list.action'" name="" id="frm021"/>&nbsp;&nbsp;
							<s:if test="%{templateId == 2}">
								<input type="button" value="����" class="sub12" onclick="publishBlock()" name="" id="frm021"/>
							</s:if>
						</td>
					</tr>
				</table>
				</div>
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
function publishBlock(){
	var statustext = $("#status").val();
	var templateId = $("#templateId").val();
	if(statustext == 1){
		alert("�ÿ��״̬Ϊͣ�ã��޷�������");
		return;
	}
	if(templateId != 2){
		alert("�ÿ鲻������ϸҳģ�壬�޷�����");
		return ;
	}
	$.ajax({
		url:"${ctx}/admin/block/publishItemBlock.action?blockid=<s:property value='editedBlock.id' />&templateid=<s:property value='editedBlock.templateId' />",							
		type:"POST",
		dataType:"text",
		success:function(data){	
			alert(data);
		}
	});
//	window.location.href = "/admin/block/publishItemBlock.action?blockid=<s:property value='editedBlock.id' />&templateid=<s:property value='editedBlock.templateId' />";
}
</script>
</body>
</html>

