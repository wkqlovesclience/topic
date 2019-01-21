<%@page import="java.net.URLEncoder"%>
<%@page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="coo8" uri="/coo8-tag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>文章管理</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script src="${ctx}/js/jquery-1.6.js"  type="text/javascript" ></script>
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
		var url = '${ctx}/News/list.action?'
				+ 'pageNumber=' + num;
		var titleId = ${titleId};
		if(titleId != ""){
			url += '&titleId=' + titleId;
		}
		window.location = url;
		return;
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
		   window.location.href="${ctx}/News/delete.action?idsString="+str+"&titleId=${titleId}";
		}
	}
	function publicBlock(){
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
		   window.location.href="${ctx}/News/publics.action?idsString="+str+"&titleId=${titleId}";
		}
	}
	
	function publicOneNews(v){
	  if(confirm('确认发布？')){
			$.post('${ctx}/News/publicNews.action?news.id='+v,function(msg){
				if(msg == '0'){
					alert("发布成功！");
				}
			}
			);
		}
	
	}
	
	//点击发布 专题
	function publicOneTitle(v){
	   if(confirm('确认发布？')){
			$.post('${ctx}/Titles/publicTitle.action?titles.id='+v,
					function(msg){	
						if(msg == '0'){
							alert("发布成功！");
						}
					}
			);
		}
	}
	//单击搜索按钮时触发事件
	function click_event(){
		var topic = document.getElementById('topic').value;
		//alert(encodeURI(encodeURI(topic)));
		window.open ("${ctx}/News/listAllNews.action?param="+encodeURI(encodeURI(topic)),"文章搜索列表"
				,"height=600,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=yes,location=no, status=no");
		return;
	}
	
	
	//按回车实现搜索
	document.onkeydown=function(event){
		e = event ? event :(window.event ? window.event : null); 
	        if(e.keyCode==13){
	        	click_event();
	        }
	}
	
	  function goback(qparent_pageNumer){
		  window.location = "/Titles/list.action?pageNumber="+qparent_pageNumer;
	  }
			
	
	</script>
</head>
<body>
	<form action="<c:url value="/News/list.action"/>" method="post" id="f1">
		<div class="mod-1">
			<div class="hd">
				<h3>文章管理</h3>
				<%-- <input type="button" onclick="goback(${qparent_pageNumber});" value="返回上级类型"/> --%>
				<h3><a href="${ctx}/Titles/list.action?page_index=${pageNumber}"  >返回上一级</a></h3>
			</div>
			<div class="bd clearfix">
				<div style="margin-bottom: 10px;" class="container-1">
					文章标题： <input type="text" class="txt-5" id="topic" name="topic" value="${topic}"/> <input
						type="button" value="搜索" onclick="javascript:click_event();"/>
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
								<input type="button" value="发布" onclick="publicOneTitle(${titleId})"/>
								
									&nbsp;&nbsp;&nbsp;&nbsp; 
										<a href="${ctx}/News/create.action?news.titleId=${titleId}">添加文章</a>
						
								</td>
								<td>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<c:if test="${fn:length(requestScope.titlename) > 0}">
										专题名称：${requestScope.titlename}
									</c:if>
								</td>
							</tr>
						</tbody>
					</table>
					<table class="tb-zebra tmp-class" style="width: 100%;">
						<colgroup>
							<col style="width: 5%;" />
							<col style="width: 10%;" />
							<col style="width: 15%;" />
							<col style="width: 20%;" />
							<col style="width: 10%;" />
							<col style="width: 10%;" />
							<col style="width: 10%;" />
							<col style="width: 5%;" />
							<col style="width: 15%;" />
						</colgroup>
						<thead>
							<tr>
								<td style="line-height: 1;"></td>
								<td style="line-height: 1;">编号</td>
								<td style="line-height: 1;">文章标题</td>
								<td style="line-height: 1;">添加/修改时间</td>
								<td style="line-height: 1;">所属专题</td>
								<td style="line-height: 1;">创建者/修改者</td>
								<td style="line-height: 1;">发布/审核</td>
								<td style="line-height: 1;">站点</td>
								<td style="line-height: 1;">操作</td>
							</tr>
						</thead>
						<tbody>
							<s:if test="listNews!= null">
								<s:iterator value="listNews" var="news" status="st">
									<tr>
										<td><label> <input type="checkbox"
												name="checkbox" value="${news.id}" /> </label></td>
										<td>${news.id}</td>
										<td class="ta-l">${news.topic}</td>
										<td><s:date name="#news.createTime"
												format="yyyy-MM-dd HH:mm:ss" /> / <s:date name="#news.updateTime"
												format="yyyy-MM-dd HH:mm:ss" /></td>
										<td>${news.titleId}</td>
										<td>${news.creator}&nbsp;/&nbsp;${news.modifier}</td>
										<td>${news.states=='Y'?'是':'否' }</td>
										<td>
											<c:choose>
												<c:when test="${news.site eq 'gome'}">国美</c:when>
												<c:otherwise>库巴</c:otherwise>
											</c:choose>
										</td>
										<td>
										<a href="${newsTestUrl2}${news.id}.html" target="blank">预览</a>&nbsp;&nbsp;&nbsp;&nbsp; 
										<c:if test="${fn:indexOf(sessionScope.urls,'/News/publicNews.action')>=0}">
									       <!--  a href="javascript:void(0)" onclick="publicOneNews(<s:property value='id' />)">发布</a> -->
									    </c:if>
											&nbsp;&nbsp;&nbsp;&nbsp; 
										<a href="${ctx}/News/edit.action?newsid=${news.id}&command=0">编辑</a></td>
									</tr>
								</s:iterator>
							</s:if>

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
													<input type="button" value="全选" onclick="sel_all('true')" /> <input
								type="button" value="取消" onclick="sel_all()" />
								<c:if test="${fn:indexOf(sessionScope.urls,'/News/publicNews.action')>=0}">
								 <!--input
								type="button" value="发布" onclick="publicBlock()"   -->
								</c:if>
								<c:if test="${fn:indexOf(sessionScope.urls,'/News/delete.action')>=0}">
								<input
								type="button" value="删除" onclick="changeBlock()" /></c:if>
								</td>
											</tr>
										</tbody>
									</table></td>
								<td style="border: 0 none;">
									<div class="numpage">
										<coo8:page name="listNews" style="js" systemId="1" />
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