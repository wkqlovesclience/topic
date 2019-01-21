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
<title>热门链接管理</title>
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
		var url = '/GroupHotLinks/create.action';
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
			window.location.href = "${ctx}/GroupHotLinks/deleteIndexFloorByid.action?ids=" + ids;
		}
	}
	
	function delCategory(id) {
		delCategoryIds(id);
	}
	
	function tunePage(num) { 
		var url = '${ctx}/GroupHotLinks/list.action';
		url += '?pageNumber=' + num;
		var groupid = $("#groupid").val();
		if(groupid != null && groupid != "" )
		{
			url += "&groupid=" + qgroupid;
		}
		var groupname = $("#groupname").val();
		if(groupname != null && groupname != "" )
		{
			url += "&groupname=" + qdisplayname;
		}
		window.location = url;
	}
 
</script>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
</head>
<body>
 <form action="./list.action" method="post" id="f1">
	<div class="mod-1">
		<div class="hd">
			<h3>热门链接管理</h3>
		</div>
		<div class="bd clearfix"> 
			<div class="container-1"> 
				分组id： <input type="text" class="txt-5" id="groupid" name="groupid" value="${param.groupid}"/>
					分组名称： <input type="text" class="txt-5" style="width: 150px;"
					 id="groupname" name="groupname" value="${param.groupname}"/> 
					 &nbsp;&nbsp;&nbsp;&nbsp;
					<input type="hidden" value="${pageNumber}" name="pageNumber" />
					<input type="submit" value="搜索"/>
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
							<td style="line-height: 1;">分组id</td>
							<td style="line-height: 1;">分组名称</td>
							<td style="line-height: 1;">热门链接</td>
							<td style="line-height: 1;">热门URL</td>
							<td style="line-height: 1;">优先级</td>
							<td style="line-height: 1;">是否启用</td>
							<td style="line-height: 1;">创建时间</td>
							<td style="line-height: 1;">修改时间</td>
							<td style="line-height: 1;">操作</td>
						</tr>
					</thead> 
					<tbody>
						<s:iterator value="groupHotLinkslist" var="tags">
							<tr> 
							<td><label><input type="checkbox" name="cbs" id="cbs" value="<s:property value='ID' />" /></label></td>
								<td style="text-align:center">${tags.ID}</td>
								<td style="text-align:center">${tags.GROUP_ID}</td> 
								<td style="text-align:center">${tags.GROUP_NAME}</td> 
								<td style="text-align:center">${tags.LINK_NAME}</td> 
								<td style="text-align:center">${tags.LINK_URL}</td> 
								<td style="text-align:center">${tags.PRIORITY}</td> 
								<td style="text-align:center">${tags.IS_VALID=='true'?'启用':'停用'}</td> 
								<td style="text-align:center"> <s:date name="#tags.CREATE_TIME" format="yyyy-MM-dd HH:mm:ss" /></td> 
								<td style="text-align:center"> <s:date name="#tags.UPDATE_TIME" format="yyyy-MM-dd HH:mm:ss" /></td> 
							 <td style="text-align:center;">
							 	<a href="${ctx}/GroupHotLinks/editGroupHotLinks.action?groupHotLinks.ID=<s:property value='ID'/>">编辑</a>
							 		<a href="javascript:delCategory(<s:property value='ID' />)">删除</a>
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
										<coo8:page name="groupHotLinkslist" style="js" systemId="1" />
									</div>
								</div></td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>  
	</form>
</body>
</html>