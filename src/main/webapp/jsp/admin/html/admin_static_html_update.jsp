<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/admin/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>静态页面管理</title>
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
             <td style="width:97%" class="NavText DText">静态页面管理</td>
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
							           		<a href="/admin/html/list.action">所有静态页</a>
							          	</td>
							           <td style="width:5px"></td>
							           <td style="width:108px;border-bottom:none;" class="TextCen GDray Boder fB">
							           		<a href="/jsp/admin/html/admin_static_html_create.jsp">新建静态页</a>
							           	</td>
							           	<td style="width:5px"></td>
							           	<td style="width:160px;border-bottom:none;" class="TextCen GDDDray Boder fB">
							           		<a style="color:white" href="#">
							           			修改<s:property value="html.name" />
							           		</a>							           	</td>
							           	<td></td>
						           	</tr>
						    </table>
						  </div>
						  <div class="RightTab">
							<form action="/admin/html/update.action" method="post" id="htmlForm" onsubmit="return verfiySubmitData();" >
								<table cellpadding="0" cellspacing="0"> 
								<tr>
									<td class="TextRig">类型：</td> 
									<td class="TextLeft">
										<s:property value="%{html.isCatalogType() ? '分类相关' : '普通' }"/>
									</td> 
								</tr>
								<tr>
									<td class="TextRig" style="width:10%">名字：</td> 
									<td class="TextLeft">
										<input type="hidden" value="<s:property value='html.id' />" name="html.id"/>
										<input type="hidden" value="<s:property value='html.templateId' />" name="html.templateId"/>
										<input type="text" style="width:524px;" class="inbox" maxlength="100"  value="<s:property value='html.name' />" name="html.name" id="name" />
									</td> 
								</tr>
								<tr>
									<td class="TextRig">路径：</td> 
									<td class="TextLeft">
										<input type="text" style="width:524px;" class="inbox" maxlength="100" value="<s:property value='html.path' />" name="html.path" id="name" />
										<font color="red">访问路径例如  cuxiao   访问就是 http://www.coo8.com/cuxiao/  必填</font>
									</td> 
								</tr>
								
								<tr>
									<td class="TextRig">描述：</td> 
									<td class="TextLeft">
										<textarea onkeydown="if (this.value.length&gt;=500){if(event.keyCode != 8) event.returnValue=false;}" 
												style="width:824px;" rows="6" class="mulinbox1" name="html.description" 
												id="description"><s:property value='html.description' /></textarea>
									</td> 
								</tr>
								
								<tr>
									<td colspan="2" class="TextCen">
										<input type="submit" value="保存"/>&nbsp;&nbsp;<input type="button" value="返回" onclick="window.location.href='/admin/html/list.action'"/>
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
  		function verfiySubmitData(){
  			YUTIL.trimFormVal("htmlForm");
  			
  			if($("#path").val() == ""){
				alert("访问路径是必填的");return false;  				
  			}
 
  		}
  	</script>
</body>
</html>