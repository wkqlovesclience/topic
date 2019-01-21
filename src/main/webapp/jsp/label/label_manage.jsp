<%@page import="com.coo8.topic.labelart.modal.*"%>
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
<title>文章相关标签管理</title>
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
	var url = '${ctx}/Label/list.action?'
			+ 'pageNumber=' + num + '&names=${names}';
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
		window.location.href="${ctx}/Label/update.action?label.id="+v+"&"+"label.names="+encodeURI(newnames);
	}
}

function test(id){
	
	window.location.href="${ctx}/Label/test.action";
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
	window.location.href="${ctx}/Label/delete.action?idsString="+str;
	}
}

</script>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
</head>
<body>
	<div class="mod-1">
		<div class="hd">
			<h3>专题标签管理</h3>
		</div>

		<div class="bd clearfix">
			<div style="margin-bottom: 10px;" class="container-1">
				<form action="${ctx}/Label/list.action" method="post">
					专题标签名：
					<input type="text" class="txt-5" id="names" name="names"  value="${names}"/>
					<input type="submit" value="搜索" />
				</form>
			</div>
			<div class="container-1">
				<table>
					<tbody>
						<tr>
							<td
								style="height: 25px; padding: 10px 0; vertical-align: middle;">
								<input type="button" value="全选" onclick="sel_all('true')" /> <input
								type="button" value="取消" onclick="sel_all()" /> 
								
								
								<input
								type="button" value="删除" onclick="changeBlock()" />
						
							</td>
						</tr>
					</tbody>
				</table>
				<table class="tb-zebra tmp-class" style="width: 100%;">
					<colgroup>
						<col style="width: 5%;" />
						<col style="width: 20%;" />
						<col style="width: 20%;" />
						<col style="width: 20%;" />
						<col style="width: 15%;" />
						<col style="width: 20%;" />
					</colgroup>
					<thead>
						<tr>
							<!-- ID 标签名称 添加/修改时间 用户 操作 -->
							<td style="line-height: 1;"></td>
							<td style="line-height: 1;">编号</td>
							<td style="line-height: 1;">标签名称</td>
							<td style="line-height: 1;">添加/修改时间</td>
							<td style="line-height: 1;">创建者/修改者</td>
							<td style="line-height: 1;">操作</td>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="listtags" var="tags">
							<tr>
								<td><label><input type="checkbox" name="checkbox"
										value="${tags.id}" /> </label>
								</td>
								<td>${tags.id}</td>
								<td class="ta-l"><a href="${baseUrl}${tags.id}.html" target="blank">${tags.names}</a></td>
								<td><s:date name="#tags.createTime"
										format="yyyy-MM-dd HH:mm:ss" /> / <s:date
										name="#tags.updateTime" format="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${tags.creator} &nbsp;/&nbsp; ${tags.modifier}</td>
								<td><a href="javascript:void(0)"
									onclick="showDiv('pos${tags.id}')">编辑</a>
									<!-- 测试按钮 -->
									<!-- <a href="javascript:void(0)"
									onclick="test()">测试发布</a> -->
									<!-- 测试按钮 -->
									<div style="width: 225px; display: none;" class="popbx-1"
										id="pos${tags.id}">
										<div class="hd">
											<h3 style="background: none; padding: 0;">修改标签名字</h3>
											<a class="close" href="javascript:void(0)"
												onclick="showDiv('pos${tags.id}')"></a>
										</div>
										<div class="bd">
											<input type="text" id="tag${tags.id}" value="${tags.names }" />
											<input type="button" value="确定"
												onclick="alertnames(${tags.id })" />
										</div>
									</div>
								</td>
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
												
							
								<input
												type="button" value="删除" onclick="changeBlock()" />
											
											</td>
										</tr>
									</tbody>
								</table></td>
							<td style="border: 0 none;">
								<div class="numpage-box">
									<div class="numpage">
										<coo8:page name="listtags" style="js" systemId="1" />
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