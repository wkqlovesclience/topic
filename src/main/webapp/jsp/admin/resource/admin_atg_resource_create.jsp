<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/admin/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>数据源管理</title>
<%@ include file="/jsp/admin/common/css.jsp"%>
<%@ include file="/jsp/admin/common/js.jsp"%>
<%@ include file="/jsp/admin/common/common_admin_css.jsp" %>
<style>html{*overflow-y:scroll;*overflow-x:hidden}</style>
<script type="text/javascript">
function validate(){
	var name = $('#name').val();
	var resource = $('#resource').val();
	var status = $('#status').val();
	if(name == null || name == ''){
		alert('数据源名字为空，请重新填写');
		$('#name').focus();
		return false;
	}
	if(resource == null || resource == ''){
		alert('数据源代码为空，请重新填写');
		$('#resource').focus();
		return false;
	}
	if(status == null || status == ''){
		alert('数据源状态为空，请重新填写');
		$('#status').focus();
		return false;
	}
}
</script>
</head>

<!-- 区分本界面为新建还是编辑 -->
<c:if test="${resource.id == null }">
	<s:set name="tabTitleName" value="'新建'"></s:set>
	<s:set name="actionName" value="'createAtgResource.action'"></s:set>
</c:if>
<c:if test="${resource.id != null }">
	<s:set name="tabTitleName" value="'编辑'"></s:set>
	<s:set name="actionName" value="'update.action'"></s:set>
</c:if>

<body style="background:#afb8bf;overflow-x:hidden;overflow-y:scroll;*overflow-y:hidden">
<table cellpadding="0" cellspacing="0">
    <tr>
      <td background="http://app.gomein.net.cn/topics/images/RigNav.gif" height="31">
	    <table cellspacing="0" cellpadding="0">
          <tr>
		     <td class="TextRig"><img src="http://app.gomein.net.cn/topics/images/nav_3.gif" style="width:11" height="7" /></td>
             <td style="width:97%" class="NavText DText">数据源管理</td>
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
						       <tbody>
						         	<tr>
							           <td style="width:16px"></td>
							           <td style="width:108px;border-bottom:none;" class="TextCen GDray Boder fB">
							           	<a href="${ctx}/admin/resource/list.action">所有数据源</a>
						          	  </td>
							          <td style="width:5px"></td>
							          <td style="width:122px;border-bottom:none;" class="TextCen GDDDray Boder fB">
							           	<a style="color:white" href="${ctx}/jsp/admin/resource/admin_atg_resource_create.jsp"><s:property value="#tabTitleName"/>ATG数据源</a>
							          </td>
							          <td style="width:5px;"></td>
                                       <td style="width:108px;border-bottom:none;" class="TextCen GDray Boder fB">
                                            <a href="${ctx}/jsp/admin/resource/admin_resource_create.jsp">新建程序数据源</a>
                                      </td>
							          <td>&nbsp;</td>
						           	</tr>
						  	</table>
						  </div>
						  <div class="RightTab">
							<form action="${ctx}/admin/resource/${actionName}" method="post" id="resourceFormId" onsubmit="return validate()">
								<table cellpadding="0" cellspacing="0"> 
								<tr>
									<td class="TextRig">数据源名字：</td> 
									<td class="TextLeft">
										<input type="hidden" name="resource.id" value="${resource.id }"/>
										<input type="text" style="width:524px;" class="inbox" maxlength="255"  name="resource.name" id="name" value="${resource.name }" />
										<font color="red">*(必填项)</font>
									</td> 
								</tr>
								<tr>
									<td class="TextRig" style="width:10%">数据源代码：</td> 
									<td class="TextLeft">
										<input type="text" style="width:524px;" class="inbox" maxlength="255"  name="resource.resource" id="resource" value="${resource.resource }"/>
										<font color="red">*(必填项)</font>
									</td> 
								</tr>
								<tr>
									<td class="TextRig" style="width:10%">测试代码：</td> 
									<td class="TextLeft">
										<input type="text" style="width:524px;" class="inbox" maxlength="255"  name="resource.testCode" id="testCode" value="${resource.testCode}"/>
									</td> 
								</tr>
								<tr>
									<td class="TextRig">数据源状态：</td> 
									<td class="TextLeft">
										<s:select list="#{'':'','0':'启用','1':'停用' }" id="status" theme="simple" name="resource.status" value="resource.status" style="float:left;">
										</s:select>
	    								<font color="red">*(必填项)</font>
	    							</td> 
								</tr>
								<tr>
									<td class="TextRig">数据源描述：</td> 
									<td class="TextLeft">
										<textarea onkeydown="if (this.value.length&gt;=500){if(event.keyCode != 8) event.returnValue=false;}" 
												style="width:824px;" rows="6" class="mulinbox1" name="resource.description" 
												id="description">${resource.description }</textarea>
									</td> 
								</tr>
								<tr>
									<td class="TextRig">数据源文档：</td> 
									<td class="TextLeft">
										<br />
										<textarea onkeydown="if (this.value.length&gt;=500){if(event.keyCode != 8) event.returnValue=false;}" 
												style="width:824px;" rows="6" class="mulinbox1" name="resource.doc" 
												id="doc">${resource.doc }</textarea>
										<br />
									</td> 
								</tr>
								<tr>
									<td colspan="2" class="TextCen">
									<!--  c:if test="${fn:indexOf(sessionScope.urls,'/admin/resource/createOutsideResource.action')>=0}"-->
										<input type="submit" value="保存" /> &nbsp;&nbsp; 
										<input type="button" value="返回" onclick="window.location.href='${ctx}/admin/resource/list.action'"/>
									<!--/c:if-->
									</td>
								</tr>
								</table>
							</form>
						 </div>
     				</div>
 				</td>
    		</tr>
  	</table>
</body>
</html>

