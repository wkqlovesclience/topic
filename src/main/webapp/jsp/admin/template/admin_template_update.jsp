<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/admin/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>模板管理</title>
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
		     	<td class="TextRig"><img src="http://app.gomein.net.cn/topics/images/nav_3.gif" style="width:11" height="7" /></td>
             	<td style="width:97%" class="NavText DText">模板管理</td>
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
					<td style="width:108px;border-bottom:none;" class="TextCen GDray Boder fB">
						<!--c:if test="${fn:indexOf(sessionScope.urls,'/admin/template/list.action')>=0}"-->	
							<a href="${ctx}/admin/template/list.action">所有模板</a>
						<!--/c:if-->	
					</td>
					<td style="width:5px"></td>
					<td style="width:108px;border-bottom:none;" class="TextCen GDray Boder fB">
						<a href="${ctx}/jsp/admin/template/admin_template_create.jsp">新建模板</a>
					</td>
					<td style="width:5px"></td>
					<td style="width:200px;border-bottom:none;" class="TextCen GDDDray Boder fB">
						<a style="color:white" href="#">
							修改<s:property value="template.name" />
						</a>
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			</div>
			<div class="RightTab">
			<form action="${ctx}/admin/template/update.action" method="post" enctype="multipart/form-data" id="templateForm" onsubmit="YUTIL.trimFormVal(this.id)">
				<table cellpadding="0" cellspacing="0"> 
					<tr>
						<td class="TextRig" style="width:80px">名字：</td> 
						<td class="TextLeft">
							<input type="hidden" name="template.id" value="<s:property value='template.id' />"/>
							<!--存site的值 在修改模板 新增块的时候 之前取site为null 存了site值之后可以去到 -->
							<input type="hidden" name="template.site" value="<s:property value='template.site' />"/>
							<input type="text" style="width:524px;" class="inbox" maxlength="30"  name="template.name" id="name" value="<s:property value='template.name' />"/>
						</td> 
					</tr>
					<tr>
						<td class="TextRig">描述：</td> 
						<td class="TextLeft">
							<textarea onkeydown="if (this.value.length&gt;=500){if(event.keyCode != 8) event.returnValue=false;}" style="width:824px;" rows="6" class="mulinbox1" name="template.description" id="description"><s:property value="template.description"/></textarea>
						</td> 
					</tr>
					<tr>
						<td class="TextRig">状态：</td> 
						<td class="TextLeft" >
							<select name="template.status" id="status" style="float:left;">
								<option <s:property value="%{template.status == 0 ? 'selected' : ''}"/> value="0">启用</option>
	    						<option <s:property value="%{template.status == 1 ? 'selected' : ''}"/> value="1">停用</option>
	    					</select>
    					</td> 
					</tr>
					<tr>
						<td class="TextRig">内容：</td> 
						<td class="TextLeft">
							<dl class="mbboxname">
								<dt style="float:left;"><br /><br />&nbsp;</dt>
								<dt style="float:left;">
									<input id="frm054" name="button" class="sub12" value="块编辑" onclick="blockEdit()" type="button"/>
									<input id="insertblock1" name="button" class="sub12" value="插入手动块" onclick="textUtil.insertBlock(1)" type="button"/>
									<input id="insertblock2" name="button" class="sub12" value="插入自动块" onclick="textUtil.insertBlock(0)" type="button"/>
								</dt>
							</dl><br /><br /><br />
							<div class="mbbox">
								<textarea rows="30" cols="150" id="content" name="template.content"><s:property value="template.content"/></textarea>
							</div>
							<dl class="mbboxname">
								<dt style="float:left;"><br />&nbsp;<input id="frm059" name="button" class="sub12" value="块编辑" onclick="blockEdit()" type="button"/></dt>
								<dt style="float:left;">
									<br/>
									<input id="insertblock1" name="button" class="sub12" value="插入手动块" onclick="textUtil.insertBlock(1)" type="button"/>
									<input id="insertblock2" name="button" class="sub12" value="插入自动块" onclick="textUtil.insertBlock(0)" type="button"/>
								</dt>
							</dl>
						</td> 
					</tr>
					<tr>
						<td class="TextRig">文件名：</td> 
						<td class="TextLeft">
							<input type="text" style="width:150px;" class="inbox" maxlength="30"  name="template.path" id="path" value="<s:property value='template.path' />">
						</td>  
					</tr>
					<tr>
						<td colspan="2" class="TextCen">
							<input type="submit" value="保存模板"/>
							<input type="button" value="预览" onclick="openWindow();"/>
							<input type="button" value="返回" onclick="window.location.href='${ctx}/admin/template/list.action'"/>
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
		a = '<coo8publishblock name="blockname' + c + '" type="1"/>';
	} else if(b == 0){
		a = '<coo8publishblock name="blockname' + c + '" type="0"/>';
	}
	textUtil.insertText(a);	
}
function blockEdit(){
	window.location.href = "${ctx}/admin/block/updateRequest.action?templateId=<s:property value='id'/>";   

}
</script>
</body>
</html>

