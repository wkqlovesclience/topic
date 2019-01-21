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
</head>
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
						         	<tr>
							           <td style="width:16px"></td>
							           <td style="width:108px;border-bottom:none;" class="TextCen GDray Boder fB">
							           <!--c:if test="${fn:indexOf(sessionScope.urls,'/admin/resource/list.action')>=0}"-->
							           		<a href="${ctx}/admin/resource/list.action">所有数据源</a>
							           	<!--/c:if-->
							          	</td>
							           <td style="width:5px"></td>
							           <td style="width:108px;border-bottom:none;" class="TextCen GDray Boder fB">
							           		<a href="${ctx}/jsp/admin/resource/admin_resource_create.jsp">新建程序数据源</a>
							           	</td>
							           	<td style="width:5px"></td>
							           <td style="width:122px;border-bottom:none;" class="TextCen GDDDray Boder fB">
							           		<a style="color:white " href="${ctx}/jsp/admin/resource/admin_product_resource_create.jsp">新建产品数据源</a>
							           	</td>
							           	<td style="width:5px"></td>
							          	<td style="width:108px;border-bottom:none;" class="TextCen GDray Boder fB">
							           		<a href="${ctx}/jsp/admin/resource/admin_outside_resource_create.jsp">新建外部数据源</a>
							           	</td>
							           	<td>&nbsp;</td>
						           	</tr>
						  	</table>
						  </div>
						  <div class="RightTab">
							<form action="${ctx}/admin/resource/createProductResource.action" method="post" id="templateForm">
								<table cellpadding="0" cellspacing="0"> 
								<tr>
									<td class="TextRig" style="width:10%">名字：</td> 
									<td class="TextLeft">
										<input type="text" style="width:524px;" class="inbox" maxlength="255"  name="resource.name" id="name" >
									</td> 
								</tr>
								<tr>
									<td class="TextRig">描述：</td> 
									<td class="TextLeft">
										<textarea onkeydown="if (this.value.length&gt;=500){if(event.keyCode != 8) event.returnValue=false;}" 
												style="width:824px;" rows="6" class="mulinbox1" name="resource.description" 
												id="description"></textarea>
									</td> 
								</tr>
								<tr>
									<td class="TextRig">数据源文档：</td> 
									<td class="TextLeft">
										<br /><textarea onkeydown="if (this.value.length&gt;=500){if(event.keyCode != 8) event.returnValue=false;}" 
												style="width:824px;" rows="6" class="mulinbox1" name="resource.doc" 
												id="description"></textarea><br />
									</td> 
								</tr>
								<tr>
									<td class="TextRig">状态：</td> 
									<td class="TextLeft">
										<select name="resource.status" id="status" style="float:left;">
											<option  value="0">启用</option>
	    									<option selected="selected" value="1">停用</option>
	    								</select>
	    							</td> 
								</tr>
								<tr>
									<td colspan="2" class="TextCen">
										
											<input type="submit" value="保存"/>&nbsp;&nbsp;<input type="button" value="返回" onclick="window.location.href='${ctx}/admin/resource/list.action'"/>
									
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