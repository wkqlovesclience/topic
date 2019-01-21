<%@page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 

"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<script src="${ctx}/js/jquery-1.6.js"  type="text/javascript" ></script>
<title>首页楼层管理（分类）</title>
<style type="text/css">
.tmp-class thead td,.tmp-class tbody td {
	padding: 5px 5px;
}

.tmp-class1 {
	text-align: center;
}

.tmp-class1 th {
	width: 63px;
	height: 29px;
	border: 1px solid #C4C8CC;
	background: #E9EBED;
	text-align: center;
}

.tmp-class1 td {
	width: 63px;
	border: 1px solid #C4C8CC;
	height: 29px;
	text-align: center;
}
</style>
<script type="text/javascript">
	//全选 、取消 
	function sel_all(checked){ 
	    var check_obj = $("input[id='cbs']"); 
	   for(var i=0; i<check_obj.length;i++){ 
	       if(checked){ 
	            check_obj.get(i).checked = true; 
	        }else{ 
	            check_obj.get(i).checked = false; 
	        } 
	    } 
	    return; 
	}
	
 
	
	//新增
	function addHomeFloor(){
		var url = '/IndexFloor/createIndexFloor.action';
		window.location = url;
	}
 
	//删除
	function delCategories() {
		var cc = $('input:checked');
		var ids = "";
		for ( var j = 0; j < cc.length; j++) {
			ids = ids + $('input:checked').get(j).value + ";";
		}
		if (ids == "") {
			alert('请至少选择一项！');
			return;
		}
		delCategoryIds(ids);
	}
	
	function delCategoryIds(ids) {
		if(confirm('确认删除？')){
			window.location.href = "${ctx}/IndexFloor/deleteIndexFloorByid.action?ids=" + ids;
		}
	}
	
	function delCategory(id) {
		delCategoryIds(id);
	}
	
	function tunePage(num) { 
		var url = '${ctx}/IndexFloor/floorlist.action';
		url += '?pageNumber=' + num;
		window.location = url;
	}
 
</script>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
</head>
<body>
 
	<div class="mod-1">
		<div class="hd">
			<h3>楼层分类管理</h3>
		</div>
		<div class="bd clearfix"> 
			<div class="container-1"> 
				<table style="width: 100%;">
					<tbody>
						<tr>
							<td style="height: 25px; padding: 10px 0; vertical-align: middle;">
								<input type="button" value="全选" onclick="sel_all('true')" /> 
								<input type="button" value="删除" onclick="delCategories()" /> 
								<input type="button" value="取消" onclick="sel_all()" /> 
								<input type="button" value="新增" onclick="addHomeFloor()" /> 
							</td>
							<td style="padding-right: 10px;" align="right" >
 							</td>
						</tr>
					</tbody>
				</table>
				<table class="tb-zebra tmp-class" style="width: 100%;"> 
					<thead>
						<tr>  <td style="line-height: 1;"></td>
							<td style="line-height: 1;">id</td>  
							<td style="line-height: 1;">三级分类名称</td>
							<td style="line-height: 1;">三级分类id</td>
							<td style="line-height: 1;">优先级</td>
							<td style="line-height: 1;">是否启用</td>
							<td style="line-height: 1;">创建时间</td>
							<td style="line-height: 1;">修改时间</td>
							<td style="line-height: 1;">操作</td>
						</tr>
					</thead> 
					<tbody>
						<s:iterator value="IndexFloorlist" var="tags">
							<tr> 
							<td><label><input type="checkbox" name="cbs" id="cbs" value="<s:property value='id' />" /></label></td>
								<td style="text-align:center">${tags.id}</td>
								<td style="text-align:center">${tags.category_name}</td> 
								<td style="text-align:center">${tags.category_id}</td>
								<td style="text-align:center">${tags.priority}</td>
								<td style="text-align:center"> ${tags.is_valid=='true'?'启用':'停用'}</td> 
								<td style="text-align:center"> <s:date name="#tags.create_time" format="yyyy-MM-dd HH:mm:ss" /></td> 
								<td style="text-align:center"> <s:date name="#tags.update_time" format="yyyy-MM-dd HH:mm:ss" /></td> 
							 
							 <td style="text-align:center;">
							 	<a href="${ctx}/IndexFloor/editIndexFloor.action?indexFloor.id=<s:property value='id'/>">编辑</a>
							 		<a href="javascript:delCategory(<s:property value='id' />)">删除</a>
							 </td> 
							</tr>
						</s:iterator>
					</tbody>
				</table>
			 	<table class="tb-zebra" width="100%">
					<tfoot>
						<tr>
							<td style="border: 0 none;">
								<div class="numpage-box">
									<div class="numpage">
										<coo8:page name="IndexFloorlist" style="js" systemId="1" />
									</div>
								</div></td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>  
</body>
</html>