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
							           		<a href="${ctx}/admin/html/list.action">所有静态页</a>
							          	</td>
							           <td style="width:5px"></td>
							           <td style="width:108px;border-bottom:none;" class="TextCen GDDDray Boder fB">
							           		<a style="color:white" href="${ctx}/jsp/admin/html/admin_static_html_create.jsp">新建静态页</a>
							           	</td>
							           	<td></td>
						           	</tr>
						    </table>
						  </div>
						  <div class="RightTab">
								<form action="${ctx}/admin/html/create.action" method="post" id="htmlForm" onsubmit="return verfiySubmitData();">
									<table cellpadding="0" cellspacing="0"> 
										<tr>
											<td class="TextRig">类型：</td> 
											<td class="TextLeft">
												<select name="html.type" onchange="onTypeChange(this)" id="type">
													<option selected="selected" 
														value="<s:property value="%{@com.coo8.btoc.model.html.StaticHtml@COMMON_TYPE}" />">普通</option>
													<option  value="<s:property value="%{@com.coo8.btoc.model.html.StaticHtml@CATALOG_TYPE}" />">分类相关</option>
												</select>
												<select style="display:none;" id="bigCatalogId" onchange="onCatalogTypeChange(this);">
													<option value="0">请选择</option>
													<s:iterator value="catalogList">
														<option  value="<s:property value="id" />"><s:property value="name" escape="false"/></option>
													</s:iterator>
												</select>
												<input type="hidden" name="html.refId" id="refId"/>
											</td> 
										</tr>
										<tr>
											<td class="TextRig" style="width:10%">名字：</td> 
											<td class="TextLeft">
												<input type="text" style="width:524px;" class="inbox" maxlength="100"  name="html.name" id="name" >
											</td> 
										</tr>
										<tr>
											<td class="TextRig">路径：</td> 
											<td class="TextLeft">
												<input type="text" style="width:524px;" class="inbox" maxlength="100"  name="html.path" id="path" >
												
												<font color="red">*&nbsp;访问路径例如  cuxiao   访问就是 http://www.coo8.com/cuxiao/  必填</font>
											</td> 
										</tr>
										<tr>
											<td class="TextRig">描述：</td> 
											<td class="TextLeft">
												<textarea onkeydown="if (this.value.length&gt;=500){if(event.keyCode != 8) event.returnValue=false;}" 
														style="width:824px;" rows="6" class="mulinbox1" name="html.description" 
														id="description"></textarea>
											</td> 
										</tr>
										
										<tr>
											<td colspan="2" class="TextCen">
												<!--<c:if test="${fn:indexOf(sessionScope.urls,'/admin/html/create.action')>=0}">-->
												<input type="submit" value="保存"/>&nbsp;&nbsp;<input type="button" value="返回" onclick="window.location.href='/admin/html/list.action'"/>
												<!--</c:if>-->
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
  		var catalogType = '<s:property value="%{@com.coo8.btoc.model.html.StaticHtml@CATALOG_TYPE}" />';
  		var catalogList = {};
  		<s:iterator value="catalogList">
  			catalogList["<s:property value='id' />"] = {
  					id:"<s:property value='id' />",
  					name:"<s:property value='name' escape='false'/>",
  					ename:"<s:property value='enname' />"
  			};
		</s:iterator>
  		function onTypeChange(t){
  			if($(t).val() != catalogType){
  				$("#refId").val("");
  				$("#bigCatalogId").hide();
  				return;
  			}
  				
  			$("#bigCatalogId").show();
  		}
  		
  		function onCatalogTypeChange(t){
  			var c = catalogList[$(t).val()];
  			if(!c)return;
  			$("#name").val(c.name);
  			$("#path").val("/"+c.ename+"/");
  			$("#refId").val(c.id);
  		}
  		
  		function verfiySubmitData(){
  			YUTIL.trimFormVal("htmlForm");
  			
  			if($("#type").val() == catalogType && $("#bigCatalogId").val()  == 0){
  				alert("请选择分类");return false;
  			}
  			if($("#path").val() == ""){
				alert("访问路径是必填的");return false;  				
  			}
 
  		}
  	</script>
</body>
</html>

