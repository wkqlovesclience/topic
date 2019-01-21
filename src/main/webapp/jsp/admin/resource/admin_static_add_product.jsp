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
							           <td  style="width:88px;border-bottom:none;" class="TextCen GDDDray Boder fB">
							           <!--c:if test="${fn:indexOf(sessionScope.urls,'/admin/resource/list.action')>=0}"-->
							           		<a style="text-decoration:none;color:#FFFFFF " href="${ctx}/admin/resource/list.action">所有数据源</a>
							           	<!--/c:if-->
							          	</td>
							           <td style="width:5px"></td>
							           <td style="width:108px;border-bottom:none;" class="TextCen GDray LBoder fB">
							           		<a style="text-decoration:none;" href="${ctx}/jsp/admin/resource/admin_resource_create.jsp">新建程序数据源</a>
							           	</td>
							           	<td style="width:5px"></td>
							           <td style="width:108px;border-bottom:none;" class="TextCen GDray LBoder fB">
							           		<a style="text-decoration:none;" href="${ctx}/jsp/admin/resource/admin_product_resource_create.jsp">新建产品数据源</a>
							           	</td>
							           	<td></td>
						           	</tr>
						  	</table>
						  </div>
						  <div class="RightTab">
						  		<table cellpadding="0" cellspacing="0" class="czrz_box">
										<s:iterator var="rc" value="resourceCatalogList">
											<tr>
											<td class="ddlb_box_ydh" >
												<s:iterator value="htmlList">
													<s:if test="%{#rc.catalogId == id}">
														<s:property value="name"/>
													</s:if>
												</s:iterator>
											</td>
											<td class="ddlb_box_ydh"  id="catalog_product_<s:property value='#rc.catalogId'/>">
												<s:property value="productIds"/>
											</td>
											<td class="ddlb_box_ydh" >
												<a href="${ctx}/admin/items/selcondition.action?t_resourceId=<s:property value='resourceId' />&t_catalogId=<s:property value='#rc.catalogId' />&t_type=2&showstatus=1" >继续添加</a>
												|
												<a href="javascript:;" class="product_show_a" tid="<s:property value='#rc.id' />" >查看产品</a>
												|
												<a href="javascript:deleteResourceCatalog('<s:property value='#rc.catalogId' />', '<s:property value='#rc.resourceId' />', <s:property value='#rc.type' />);">删除</a>
											</td>
											</tr>
											<tr style="display:none;" id="product_id_<s:property value='#rc.id' />" ids="<s:property value='productIds'/>" cid="<s:property value='#rc.catalogId' />">
												<td colspan="3" class="ddlb_box_ydh"></td>
											</tr>
										</s:iterator>
										<tr>
											<td class="ddlb_box_ydh" >
												<select id="bigCatalogId">
													<option value="">添加产品之前请先选择页面</option>
													<s:iterator value="htmlList">
														<option value="<s:property value='id' />"><s:property value="name"/></option>
													</s:iterator>
												</select>
											</td>
											<td class="ddlb_box_ydh" >
												<input value="添加产品.." type="button" onclick="checkCatalogBeforeSelectProduct();"/>
											</td>
										</tr>
								</table>
						 </div>
     				</div>
 				</td>
    		</tr>
  	</table>
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
		alert('请先选择一个静态页');
		return;
	}
	window.location.href="${ctx}/admin/items/selcondition.action?t_resourceId=" 
				+ resourceId + "&t_catalogId=" + $("#bigCatalogId").val()
				+"&t_type=2&showstatus=1";
}

$(".product_show_a").one("click", showProduct);

function showProduct(){
	$("#product_id_" + $(this).attr("tid")).show();
	var id = "product_id_" + $(this).attr("tid");
	$(this).one("click", hideProduct);
	$(this).html("隐藏产品");
	$.ajax({
		  url: "/admin/resource/product/list.action?ids=" + $("#product_id_" + $(this).attr("tid")).attr("ids"),
		  data:"aaa="+Math.random(),
		  success: function(data){
			$("#" + id).find("td").html(data);
			
			$("#" + id).find(".delete_a").bind("click", function(){
				var productId = $(this).attr("tid");
				var catalogId = $("#"+id).attr("cid");
				$.ajax({
					  url: "/admin/resource/product/delete.action?productIds=" 
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

function deleteResourceCatalog(cid, rid, type) {
	if(confirm("确定要删除？")){
        window.location.href="${ctx}/admin/resource/catalog/delete.action?resourceId="+rid+"&catalogId="+cid+"&type="+type;
    }
}
</script>
<script type="text/javascript">
//单元格上移下移
function moveup(obj){
		var row=obj.parentNode.parentNode;	
		var column=obj.parentNode;		
		var downid=row.id;
		var downprio=column.id;		
		if($(row).parent().parent().prev().html()!=null){				
			var upid=$(row).parent().parent().prev().find("tr").attr("id");
			var upprio=$(row).parent().parent().prev().find("tr").children("td:eq(6)").attr("id");
			downprio=parseInt(upprio)+1;
			$.post(
				"/admin/items/modifyprio.action?aaa="+Math.random(),
				{downid:downid,downprio:downprio}			
			);
			obj.parentNode.id=downprio;			
			swapNode(row.parentNode.parentNode,$(row).parent().parent().prev());
		}
	}
	function movedown(obj){
		var row=obj.parentNode.parentNode;	
		var column=obj.parentNode;		
		var downid=row.id;
		var downprio=column.id;		
		if($(row).parent().parent().next().html()!=null){
			var upid=$(row).parent().parent().next().find("tr").attr("id");
			var upprio=$(row).parent().parent().next().find("tr").children("td:eq(6)").attr("id");
			downprio=parseInt(upprio)-1;
			if(downprio<0)
				alert("下面商品已是最低级别，不能下移！");
			else{	
				$.post(
						"/admin/items/modifyprio.action?aaa="+Math.random(),
						{downid:downid,downprio:downprio}			
					);
				obj.parentNode.id=downprio;
				swapNode(row.parentNode.parentNode,$(row).parent().parent().next());
			}
		}
	}
	function swapNode(node1,node222){
		var node2=node222[0];
		var parent=node1.parentNode;
		var t1=node1.nextSibling;
		var t2=node2.nextSibling;
		if(t1) parent.insertBefore(node2,t1);
		else parent.appendChild(node2);
		if(t2) parent.insertBefore(node1,t2);
		else parent.appendChild(node1);
	}
  </script>
</body>
</html>

