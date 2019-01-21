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
<title>首页专题管理</title>
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
function tunePage(num) {
    
	var url = '${ctx}/Toptitle/list.action?'
			+ 'pageNumber=' + num;
 
	window.location = url;
}

function showDiv(v){
	$("#"+v).toggle();
}

function alertnames(v){
	var newnames = $('#tag'+v).val();
	if(newnames==""){
		alert("输个名字吧！");
	}else{
		window.location.href="${ctx}/Toptitle/update.action?keywords.id="+v+"&"+"keywords.names="+newnames;
	}
}

function sel_all(checked){ 
    var check_obj = $("input[name='checkbox']"); 
   for(var i=0; i<check_obj.length;i++){ 
       if(checked){ 
            check_obj.get(i).checked = true; 
        }else{ 
         check_obj.get(i).checked = false; 
        } 
    } 
    return; 
} 

function changeBlock(){
	var cc=$('input:checked');
	var str="";
	for(var j=0;j<cc.length;j++){
		str=str+$('input:checked').get(j).value+";";
	}	
	if(str == ""){
		 alert('请至少选择一项！'); 
		 return;
	} 
	if(confirm('确认删除？')){
	window.location.href="${ctx}/Toptitle/delete.action?idsString="+str;
	}
}
function changePublic(){
	var cc=$('input:checked');
	var str="";
	for(var j=0;j<cc.length;j++){
		str=str+$('input:checked').get(j).value+";";
	}	
	if(str == ""){
		 alert('请至少选择一项！'); 
		 return;
	} 
	if(confirm('确认发布？')){
	  window.location.href="${ctx}/Toptitle/publics.action?idsString="+str;
	}
}
function changeStop(){
	var cc=$('input:checked');
	var str="";
	for(var j=0;j<cc.length;j++){
		str=str+$('input:checked').get(j).value+";";
	}	
	if(str == ""){
		 alert('请至少选择一项！'); 
		 return;
	} 
	if(confirm('确认停用？')){
	  window.location.href="${ctx}/Toptitle/stops.action?idsString="+str;
	}
}
//按回车实现搜索
document.onkeydown=function(event){
	e = event ? event :(window.event ? window.event : null); 
        if(e.keyCode==13){
        	//document.form[0].submit();
        	$("f1").submit();
        }
}
</script>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
</head>
<body>
	<div class="mod-1">
		<div class="hd">
			<h3>首页专题管理</h3>
		</div>

		<div class="bd clearfix">
			<div style="margin-bottom: 10px;" class="container-1">
				<form action="${ctx}/Toptitle/list.action" method="post" id="f1">
					专题名：<input type="text" class="txt-5" id="names" name="names" value="${names}"/>
					<input type="submit" value="搜索" />
				</form>
			</div>
			<div class="container-1">
				<table>
					<tbody>
					     <tr>
							<td
								style="height: 25px; padding: 10px 0; vertical-align: middle;">
								<form action="${ctx}/Toptitle/save.action" method="post">
					专题名：<input type="text" class="txt-5" id="names" name="toptitle.names" />   地址：<input type="text" class="txt-9" id="urls" name="toptitle.urls" />
					<input type="submit" value="新增" />
				</form>
							</td>
						</tr>
						<tr>
							<td
								style="height: 25px; padding: 10px 0; vertical-align: middle;">
								<input type="button" value="全选" onclick="sel_all('true')" /> <input
								type="button" value="取消" onclick="sel_all()" />
								 <input type="button" value="发布" onclick="changePublic()" />
								  <input type="button" value="停用" onclick="changeStop()" />
								 <input type="button" value="删除" onclick="changeBlock()" />
							</td>
						</tr>
					</tbody>
				</table>
				<table class="tb-zebra tmp-class" style="width: 100%;">
					<colgroup>
						<col style="width: 5%;" />
						<col style="width: 15%;" />
						<col style="width: 20%;" />
						<col style="width: 30%;" />
						<col style="width: 15%;" />
						<col style="width: 5%;" />
						<col style="width: 15%;" />  
					</colgroup>
					<thead>
						<tr>
							<!-- ID 标签名称 添加/修改时间 用户 操作 -->
							<td style="line-height: 1;"></td> 
							<td style="line-height: 1;">编号</td>
							<td style="line-height: 1;">专题名称</td>
							<td style="line-height: 1;">地址</td>
							<td style="line-height: 1;">发布</td>
							<td style="line-height: 1;">站点</td>
							<td style="line-height: 1;">用户</td> 
						</tr>
					</thead>
					<tbody>
						<s:iterator value="listtop" var="tags">
							<tr>
								<td><label><input type="checkbox" name="checkbox"
										value="${tags.id}" /> </label>
								</td> 
								<td class="ta-l">${tags.id}</td>
								<td class="ta-l">${tags.names}</td>
								<td class="ta-l">${tags.urls}</td>
								<td class="ta-l">${tags.status=='1'?'是':'否' }</td>
								<td>
									<c:choose>
										<c:when test="${tags.site eq 'gome'}">国美</c:when>
										<c:otherwise>库巴</c:otherwise>
									</c:choose>
								</td>
								<td class="ta-l">${tags.creator}</td>
								 
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<table class="tb-zebra" width="100%">
					<tfoot>
						<tr>
							<td style="padding-left: 0; text-align: left; border: none;">
								<table>
									<tbody>
										<tr>
											<td
												style="height: 25px; padding: 10px 0; vertical-align: middle; border: none;">
												<input type="button" value="全选" onclick="sel_all('true')" />
												<input onclick="sel_all()" type="button" value="取消" />
												 <input type="button" value="发布" onclick="changePublic()" />
												  <input type="button" value="停用" onclick="changeStop()" />
												  <input type="button" value="删除" onclick="changeBlock()" />
											</td>
										</tr>
									</tbody>
								</table></td>
							<td style="border: 0 none;">
								<div class="numpage-box">
									<div class="numpage">
										<coo8:page name="listtop" style="js" systemId="1" />
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