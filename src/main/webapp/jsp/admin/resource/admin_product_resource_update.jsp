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
							           	<!--  c:if test="${fn:indexOf(sessionScope.urls,'/admin/resource/list.action')>=0}"-->
							           		<a href="${ctx}/admin/resource/list.action">所有数据源</a>
							           		<!--/c:if-->
							          	</td>
							           <td style="width:5px"></td>
							           <td style="width:108px;border-bottom:none;" class="TextCen GDray Boder fB">
							           		<a href="${ctx}/jsp/admin/resource/admin_resource_create.jsp">新建程序数据源</a>
							           	</td>
							           	<td style="width:5px"></td>
							            <td style="width:108px;border-bottom:none;" class="TextCen GDray Boder fB">
							           		<a href="${ctx}/jsp/admin/resource/admin_product_resource_create.jsp">新建产品数据源</a>
							           	</td>
							           	<td style="width:5px"></td>
							           	<td style="width:108px;border-bottom:none;" class="TextCen GDray Boder fB">
							           		<a href="${ctx}/jsp/admin/resource/admin_outside_resource_create.jsp">新建外部数据源</a>
							           	</td>
							           	<td style="width:5px"></td>
							            <td style="width:160px;border-bottom:none;" class="TextCen GDDDray Boder fB">
							           		<a style="text-decoration:none;color:white" href="#">
							           			修改<s:property value="resource.name" />
							           		</a>
							           	</td>
							           	<td colspan="2"></td>
						           	</tr>
						  	</table>
						  </div>
						  <div class="RightTab">
							<form action="${ctx}/admin/resource/update.action" method="post" id="templateForm">
								<table cellpadding="0" cellspacing="0"> 
								<tr>
									<td class="TextRig" style="width:10%">名字：</td> 
									<td class="TextLeft">
										<input type="hidden" name="resource.id" value="<s:property value='resource.id' />"/>
										<input type="text" style="width:524px;" class="inbox" maxlength="30"  value="<s:property value='resource.name' />" name="resource.name" id="name" >
									</td> 
								</tr>
								<tr>
									<td class="TextRig">描述：</td> 
									<td class="TextLeft">
										<textarea onkeydown="if (this.value.length&gt;=500){if(event.keyCode != 8) event.returnValue=false;}" 
												style="width:824px;" rows="6" class="mulinbox1" name="resource.description" 
												id="description"><s:property value='resource.description' /></textarea>
									</td> 
								</tr>
								<tr>
									<td class="TextRig">数据源文档：</td> 
									<td class="TextLeft">
										<br /><textarea onkeydown="if (this.value.length&gt;=500){if(event.keyCode != 8) event.returnValue=false;}" 
												style="width:824px;" rows="6" class="mulinbox1" name="resource.doc" 
												id="description"><s:property value='resource.doc' /></textarea><br />
									</td> 
								</tr>
								<tr>
									<td class="TextRig">状态：</td> 
									<td class="TextLeft" style="text-align:left;">
										<select name="resource.status" id="status" style="float:left;">
											<option <s:property value='resource.status == 0 ? "selected" : "" ' /> value="0">启用</option>
	    									<option <s:property value='resource.status == 1 ? "selected" : "" ' /> value="1">停用</option>
	    								</select>
	    							</td> 	
								</tr>
								<!-- 
								<tr>
									<td class="TextCen" colspan="2">
										<a href="${ctx}/admin/resource/catalog/addProductRequest.action?resourceId=<s:property value='resourceId' />&type=2" >添加主页或者活动产品</a>
										|
										<a href="${ctx}/admin/resource/catalog/addProductRequest.action?resourceId=<s:property value='resourceId' />&type=1" >添加超市页产品</a>
	    							</td> 
								</tr> -->
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
  		</tbody>
  	</table>
</div>
</div>
<script type="text/javascript">
var resourceId = "<s:property value='resourceId' />";
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
function checkCatalogBeforeSelectProduct() {
	if($("#bigCatalogId").val() == ''){
		alert('请先选择一个分类');
		return;
	}
	window.location.href="${ctx}/admin/items/selcondition.action?t_resourceId=" 
				+ resourceId + "&t_catalogId=" + $("#bigCatalogId").val()
				+"&catbigid="+$("#bigCatalogId").val();
}

$(".product_show_a").one("click", showProduct);

function showProduct(){
	$("#product_id_" + $(this).attr("tid")).show();
	var id = "product_id_" + $(this).attr("tid");
	$(this).one("click", hideProduct);
	$(this).html("隐藏产品");
	$.ajax({
		  url: "/admin/resource/product/list.action?ids=" + $("#product_id_" + $(this).attr("tid")).attr("ids"),
		  success: function(data){
			$("#" + id).find("td").html(data);
			
			$("#" + id).find(".delete_a").bind("click", function(){
				var productId = $(this).attr("tid");
				var catalogId = $("#"+id).attr("cid");
				$.ajax({
					  url: "${ctx}/admin/resource/product/delete.action?productIds=" 
							  + productId + "&resourceId="+resourceId
							  + "&catalogId=" + $("#"+id).attr("cid"),
					  success: function(data){
						  $("#"+id).find("table[tid="+productId+"]").remove();
						  $("#catalog_product_" + catalogId).text($("#catalog_product_" + catalogId).text().replace(productId, ""));
					  }
				});
			});
	}});
}
function hideProduct(){
	$("#product_id_" + $(this).attr("tid")).hide();
	$(this).one("click", showProduct);
	$(this).html("查看产品");
}

function deleteResourceCatalog(cid, rid) {
	if(confirm("确定要删除？")){
        window.location.href="${ctx}/admin/resource/catalog/delete.action?resourceId="+rid+"&catalogId="+cid;
    }
}

</script>
</body>
</html>

