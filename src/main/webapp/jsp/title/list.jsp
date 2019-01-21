<%@page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>商品专题管理</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.6.js"></script>
<script src="${ctx}/js/json.js"  type="text/javascript" ></script>
<script src="${ctx}/js/singleCalendar/WdatePicker.js"  type="text/javascript" ></script>
<script src="${ctx}/js/laydate/laydate.js"  type="text/javascript" ></script>
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
	//全部发布
	function publicAllTitle() {
		var cc = $('input:checked');
		var str = "";
		for ( var j = 0; j < cc.length; j++) {
			str = str + $('input:checked').get(j).value + ";";
		}
		if (str == "") {
			alert('请至少选择一项！');
			return;
		}
		if(confirm('确认发布？')) {
            window.location.href = "${ctx}/Titles/publicTitle.action?tags="+str;
		} 
		
	}
	function publicOneTitle(v){
	   if(confirm('确认发布？')){
           window.location.href = "${ctx}/Titles/publicTitle.action?titles.id="+v;
		}
		
	}
	
	//所有的商品专题一键发布(包含所有的商品专题数据)
	function publicWholeTitle() {
		if(confirm("确定执行全部发布吗？如果是，时间比较长，请耐心等待"))
			{
	         $.post('${ctx}/Titles/publicWholeTitle.action',
					function(msg){	
						if(msg =='0'){
							alert("全部发布成功！"
							);
						}else{
						alert("异常错误，退出重新登录或者联系开发人员");
						}
					}
			);
		} 
		
	} 
	
	function   deletePublish(){
	 if(confirm('确认清空？')){
	   window.location.href = "${ctx}/Titles/deletePublish.action";
	  }
	}
	
	/* function changePath(){
	  window.location.href="${ctx}/Titles/changePath.action";
	} */
	
	//删除
	function delTitles() {
		var cc = $('input:checked');
		var str = "";
		for ( var j = 0; j < cc.length; j++) {
			str = str + $('input:checked').get(j).value + ";";
		}
		if (str == "") {
			alert('请至少选择一项！');
			return;
		}
		if(confirm('确认删除？')){
		   window.location.href = "${ctx}/Titles/delete.action?tags=" + str;
		 }
	}
	
	function tunePage(num) {
		var conditkey  = document.getElementById("conditkey").value;
		var conditvalue = document.getElementById("conditvalue").value;
		var creator = document.getElementById("creator").value;
		var tempStat = document.getElementById("tempStat").value;
		var createTime = document.getElementById("titles.createTime").value;
		
		var url = '${ctx}/Titles/list.action?' + 'page_index=' + num;
		
		if(null != conditkey && '' != conditkey){
			url += '&conditkey=' + conditkey;
		}
		if(null != conditvalue && '' != conditvalue){
			url += '&conditvalue=' + encodeURIComponent(encodeURIComponent(conditvalue));
		}
		if(null != creator && '' != creator){
			url += '&creator=' + encodeURIComponent(encodeURIComponent(creator));
		}
		if(null != tempStat && '' != tempStat){
			url += '&tempStat=' + tempStat;
		}
		if(null != createTime && '' != createTime){
			url += '&titles.createTime=' + createTime;
		}
		window.location = url;
	}
	
	
	// 下拉条件改变触发
	function condit_change(){
		document.getElementById("conditvalue").value = "";
	}
	
	//按回车实现搜索
	document.onkeydown=function(event){
		e = event ? event :(window.event ? window.event : null); 
	        if(e.keyCode==13){
	        	//document.form[0].submit();
	        	document.getElementById("f1").submit();
	        }
	}
	
	//日期控件
	function yxbegin() {
		if (window.ActiveXObject) {
			document.getElementById("titles.createTime").click();
		} else {
			var evt = document.createEvent("MouseEvents");
			evt.initEvent("click", true, true);
			document.getElementById("titles.createTime").dispatchEvent(evt);
		}
	}
	
	//部分发布
	function partPublish(){
		var idBegin = document.getElementById("idBegin").value;
		var idEnd = document.getElementById("idEnd").value;
		
		if(numValidate(idBegin) && numValidate(idEnd) && compareNum(idBegin,idEnd)){
			if(confirm('确认发布？')){
                window.location.href = "${ctx}/Titles/toPartPublish.action?idBegin="+idBegin+"&idEnd="+idEnd;
			} 

		}
	}
	
	//导出Excel
	function exportExcel(){
		
		var idBegin = document.getElementById("idBegin").value;
		var idEnd = document.getElementById("idEnd").value;
		
		if(numValidate(idBegin) && numValidate(idEnd) && compareNum(idBegin,idEnd)){
			var f1 = document.getElementById("f1");		
			f1.action="${ctx}/Titles/toExcel.action?idBegin="+idBegin + "&idEnd="+idEnd;
			f1.submit();
			f1.action="${ctx}/Titles/list.action";
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
	
	function viewHotLink( moduleId, moduleType){
	  var url = "${ctx}/HotLink/list.action?moduleId="+moduleId+"&&hotLinkModuleType"+moduleType;
	   var dialogLeft = $(window).width()/2;
       var dialogTop = $(window).height()/2;
       if($.browser.msie){
           centerDialog = "";
       }
       else{
	       var centerDialog = "dialogLeft:"+dialogLeft+"px;"+"dialogTop:"+dialogTop+"px;";
       }
	   window.showModalDialog(url,window,"dialogWidth:600px;dialogHeight:300px;center:yes;resizable:yes;status:no;help:no;scroll:yes;"+centerDialog);
	}
</script>
</head>
<body>
	<form action="${ctx}/Titles/list.action" method="post" id="f1">
		<div class="mod-1">
			<div class="hd">
				<h3>商品专题管理</h3>
			</div>
			<div class="bd clearfix">
				<div style="margin-bottom: 10px;" class="container-1">
				  <s:select id="conditkey" name="conditkey" list="#{'title':'专题名称','id':'专题ID'}" onchange="javascript:condit_change();"/>
				  <input type="text" class="txt-5"  id="conditvalue" name="conditvalue"  value="${conditvalue }"/>
				  &nbsp;&nbsp;创建者：
				  <input type="text" class="txt-5" id="creator" name="creator" value="${creator}"/>  	 
				  <%--&nbsp;&nbsp;创建时间：
				  <input onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"  value="${tempTime}" id="titles.createTime" name="titles.createTime" class="txt-5">
				  	<img onclick="yxbegin()" src="http://app.gomein.net.cn/topics/images/images_3.gif" /> 
				  </input>--%>
					&nbsp;&nbsp; &nbsp;
					创建时间：
					<input class="laydate-icon" id="titles.createTime" value="${param.qcreateTime}" onclick="laydate()" name="titles.createTime" readonly="readonly">
				  &nbsp;&nbsp;专题修改状态：
				  <s:select id="tempStat" name="tempStat" list="#{0:'默认',1:'未发布专题',2:'最近修改专题'}" />
				  &nbsp;&nbsp;
				  <input type="submit" value="搜索" /><br />
				  </div>
				<div class="container-1">

					<table style="width: 100%;">
						<tbody>
						
							<tr>
								<td style="height: 25px; padding: 10px 0; vertical-align: middle;">
								 <input type="button" value="全选" onclick="sel_all('true')" /> 
									
									 <input
									type="button" value="发布" onclick="publicAllTitle()" /> 
							
									<input type="button" value="取消"
									onclick="sel_all()" /> 
									<%-- <c:if test="${sessionScope.userName== '林承君'}">
										 <c:if test="${fn:indexOf(sessionScope.urls,'/Titles/delete.action')>=0}">
										  <input type="button" value="删除" onclick="delTitles()"/>
										 </c:if> 
										 <input type = "hidden" value="${sessionScope.total}" id="total"/>
									  	<input type="button" value="全部发布" onclick="publicWholeTitle()"/> 
										<input type="button" value="清空发布表" onclick="deletePublish()"/> 
									    <!--  <input type="button" value="更改海外购模块中block中的site" onclick="changePath()"/> -->
									</c:if> --%>
									</td>
									
										<td style="padding-right: 10px;" align="right" >专题ID:
											<input type="text" class="txt-5" id="idBegin" />  到 <input type="text" class="txt-5" id="idEnd" /> 
											<input type="button" value="部分发布" onclick="javascript:partPublish();"/>
	 										<input type="button" value="导出excel" onclick="javascript:exportExcel();"/>
			 							</td>
			 					
							</tr>
						</tbody>
					</table>
					<table class="tb-zebra tmp-class" style="width: 100%;">
						<colgroup>
							<col style="width: 5%;" />
							<col style="width: 10%;" />
							<col style="width: 13%;" />
							<col style="width: 15%;" />
							<col style="width: 10%;" />
							<col style="width: 10%;" />
							<col style="width: 5%;" />
							<col style="width: 5%;" />
							<col style="width: 5%;" />
							<col style="width: 27%;" />
						</colgroup>
						<thead>
							<tr>
								<td style="line-height: 1;"></td>
								<td style="line-height: 1;">编号</td>
								<td style="line-height: 1;">专题名称</td>
								<td style="line-height: 1;">标签</td>
								<td style="line-height: 1;">添加/修改时间</td>
								<td style="line-height: 1;">创建者/修改者</td>
								<td style="line-height: 1;">发布/审核</td>
								<td style="line-height: 1;">文章数量</td>
								<td style="line-height: 1;">站点</td>
								<td style="line-height: 1;">操作</td>
							</tr>
						</thead>
						<tbody>
							<s:if test="titleList!= null">
								<s:iterator value="titleList"  status="st">
									<tr>
										<td><label> <input type="checkbox" name="cbs"
												id="cbs" value="<s:property value='id' />" /> </label></td>
										<td><s:property value='id' /></td>
										<td class="ta-l"><c:if test="${publicStat=='Y'}"><a href="${titleBaseUrl}${paths}/" target="_blank" >
										</c:if><s:property value='title' /><c:if test="${publicStat=='Y'}"></a></c:if></td>
										<td class="ta-l"><s:property value='tags' /></td>
										<td><s:date name="createTime"
												format="yyyy.MM.dd" />/<s:date name="updateTime"
												format="yyyy.MM.dd" /></td>
										<td><s:property value='creator' />&nbsp;/&nbsp;<s:property value="modifier"/></td>
										<td>${publicStat=='Y'?'是':'否' }</td>
										<td><s:property value='newscount' /></td>
										<td>
											<c:choose>
												<c:when test="${site eq 'gome'}">国美</c:when>
												<c:otherwise>库巴</c:otherwise>
											</c:choose>
										</td>
										<td> 
										  	
										<%--   <s:if test="paths != null&& paths!=''">
										     <a href="${titleTestBaseUrl}<s:property value='paths'  />/" target="blank">预览</a>
										 </s:if>
										 <s:else>
										     <a href="${titleTestBaseUrl}<s:property value='id' />/" target="blank" >预览</a>
										   
										   </s:else>
										&nbsp;&nbsp;&nbsp;&nbsp;  --%>
										
										<a
											href="javascript:void(0)" onclick="publicOneTitle(<s:property value='id' />)">发布</a>
											
									
										&nbsp;
										<a
											href="${ctx}/News/create.action?news.titleId=<s:property value='id'/>&pageNumber=${page_index}">添加文章</a>&nbsp;
											<a href="${ctx}/News/list.action?titleId=<s:property value='id'/>&pageNumber=${page_index}">文章管理</a>&nbsp;
											<a href="${ctx}/HotLink/create.action?hotLink.moduleId=<s:property value='id' />&hotLink.moduleType=2&pageNumber=${page_index}">添加热门链接</a>&nbsp;
											<%-- <a href="javascript:void(0);" onclick="viewHotLink(<s:property value='id'/>,2)">查看热门链接</a>&nbsp; --%>
											 <a href="${ctx}/Titles/edit.action?titles.id=<s:property value='id'/>&forwards=0&page_index=${page_index}">编辑</a>
										</td>
									</tr>
								</s:iterator>
							</s:if>
						</tbody>
					</table>

					<table width="100%">
						<tfoot>
							<tr>
								<td style="padding-left: 0; text-align: left; border: none;">
									<table>
										<tbody>
											<tr>
												<td
									style="height: 25px; padding: 10px 0; vertical-align: middle;">
									<input type="button" value="全选" onclick="sel_all('true')" /> 
									
										
										<input
									type="button" value="发布" onclick="publicAllTitle()"  /> 
							
									<input type="button" value="取消"
									onclick="sel_all(false)" />  
								
									<input type="button" value="删除"
									onclick="delTitles()"/>
									</td>
											</tr>
										</tbody>
									</table></td>
								<td style="border: 0 none;">
									<div class="numpage-box">
										<div class="numpage">
											<coo8:page name="titleList" style="js" systemId="1" />
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