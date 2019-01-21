<%@page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="coo8" uri="/coo8-tag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>全部文章管理</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script src="${ctx}/js/jquery-1.6.js"  type="text/javascript" ></script>
<script src="${ctx}/js/json.js"  type="text/javascript" ></script>
<script src="${ctx}/js/singleCalendar/WdatePicker.js"  type="text/javascript" ></script>
<script type="text/javascript">
	function tunePage(num) {
		
		var url = '${ctx}/News/listAllNews.action?'
				+ 'pageNumber=' + num ;
		var topic = $('#topic').val();
		if(topic != null && topic != "" ){
			url += '&news.topic=' + encodeURIComponent(encodeURIComponent(topic))
		}
		var creator = $('#creator').val();
		if(creator != null && creator != "" ){
			url += '&creator=' + encodeURIComponent(encodeURIComponent(creator))
		}
		var createTime = $('#createTime').val();
		if(createTime != null && createTime != "" ){
			url += '&createTime=' + createTime
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
		   window.location.href="${ctx}/News/delete.action?idsString="+str+"&command=1";
		}
	}
	
	//按回车实现搜索
	document.onkeydown=function(event){
		e = event ? event :(window.event ? window.event : null); 
	        if(e.keyCode==13){
	        	document.getElementById("f1").submit();
	        }
	}
	function yxbegin() {
		if (window.ActiveXObject) {
			document.getElementById("createTime").click();
		} else {
			var evt = document.createEvent("MouseEvents");
			evt.initEvent("click", true, true);
			document.getElementById("createTime").dispatchEvent(evt);
		}
	}
	
	//导出Excel
	function exportExcel(){
		
		var idBegin = document.getElementById("idBegin").value;
		var idEnd = document.getElementById("idEnd").value;
		
		if(numValidate(idBegin) && numValidate(idEnd) && compareNum(idBegin,idEnd)){
			var f1 = document.getElementById("f1");		
			f1.action="${ctx}/News/toExcel.action?idBegin="+idBegin + "&idEnd="+idEnd;
			f1.submit();
			f1.action="${ctx}/News/listAllNews.action";
		}
	}
	
	//校验输入是否为数字
	function numValidate(num){
		var reg = /^\d+$/;
		if(null != num && "" != num){
			if(!reg.test(num)){
				alert(num + "不是数字，请输入数字！")
				return false;
			}
		}
		return true;
	}
	
	
	//比较两个值大小
	function compareNum(num1,num2){
		if(null != num1 && num2 != null && "" != num1 && "" != num2 && (num1 > num2)){
			alert(num2 + " 值大于 " + num1 + "  请重新输入！");
			return false;
		}
		return true;
	}
	</script>
</head>
<body>
	<form action="<c:url value="/News/listAllNews.action"/>" method="post" id="f1">
		<div class="mod-1">
			<div class="hd">
				<h3>全部文章管理</h3>
			</div>
			<div class="bd clearfix">
					文章标题： <input type="text" class="txt-5" id="topic" name="topic" value="${topic}"/> 
					创建者：      <input type="text" class="txt-5" id="creator" name="creator" value="${creator}"/>		 
					创建时间：
					<input readonly onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"
									name="createTime" id="createTime" class="txt-5"
									value="<s:date name="createTime"
												format="yyyy-MM-dd" />"  /><img onclick="yxbegin()"
									src="http://app.gomein.net.cn/topics/images/images_3.gif" /> 
					<input type="submit" value="搜索"/>
				</div>
				<div class="container-1">
					<table style="width: 100%;">
						<tbody>
							<tr>
								<td style="height: 25px; padding: 10px 0; vertical-align: middle;">
									<input type="button" value="全选" onclick="sel_all('true')" /> <input
								type="button" value="取消" onclick="sel_all()" />
								
								<input
								type="button" value="删除" onclick="changeBlock()" />
									&nbsp;&nbsp;&nbsp;&nbsp; 
								</td>
								<td style="padding-right: 10px;" align="right">文章ID:
											<input type="text" class="txt-5" id="idBegin" />  到 <input type="text" class="txt-5" id="idEnd" /> 
	 										<input type="button" value="导出excel" onclick="javascript:exportExcel();"/>
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
								<s:iterator value="listNews" var="news1" status="st">
									<tr>
										<td><label> <input type="checkbox"
												name="checkbox" value="${news1.id}" /> </label></td>
										<td>${news1.id}</td>
										<td class="ta-l">${news1.topic}</td>
										<td><s:date name="#news1.createTime"
												format="yyyy-MM-dd HH:mm:ss" /> / <s:date name="#news1.updateTime"
												format="yyyy-MM-dd HH:mm:ss" /></td>
										<td>${news1.titleId}</td>
										<td>${news1.creator}&nbsp;/&nbsp;${news1.modifier}</td>
										<td>${news1.states=='Y'?'是':'否' }</td>
										<td>
											<c:choose>
												<c:when test="${news1.site eq 'gome'}">国美</c:when>
												<c:otherwise>库巴</c:otherwise>
											</c:choose>
										</td>
										<td>
										<a href="${news1.newsTestUrl}${news1.id}.html" target="blank">预览</a>&nbsp;&nbsp;&nbsp;&nbsp; 
											&nbsp;&nbsp;&nbsp;&nbsp; 
										<a href="${ctx}/News/edit.action?newsid=${news1.id}&command=1">编辑</a></td>
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
								
								
								<input
								type="button" value="删除" onclick="changeBlock()" />
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