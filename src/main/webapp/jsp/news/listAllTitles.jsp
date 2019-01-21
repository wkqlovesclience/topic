<%@page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>商品下架提示</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.6.js"></script>
<script src="${ctx}/js/json.js"  type="text/javascript" ></script>
<script src="${ctx}/js/singleCalendar/WdatePicker.js"  type="text/javascript" ></script>
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
		if(confirm('确认发布？'))
			{
	         $.post('${ctx}/Titles/publicTitle.action?tags='+ str,
					function(msg){			
						if(msg =='0'){
							alert("全部发布成功！");
						}
					}
			);
		} 
		
	}
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
		
		var url = '${ctx}/Titles/listConditTitles.action?' + 'page_index=' + num;
		
		if(null != conditkey && '' != conditkey){
			url += '&conditkey=' + conditkey;
		}
		if(null != conditvalue && '' != conditvalue){
			url += '&conditvalue=' + conditvalue;
		}
		if(null != creator && '' != creator){
			url += '&creator=' + creator;
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
	//导出Excel
	function exportExcel(){
		
		var idBegin = document.getElementById(elementId);
		
		
	}
	//生成列表
	function openList(flag){
		var f1 = document.getElementById("f1");
		f1.action = "${ctx}/Titles/createTitlesList.action";
		f1.submit();
		f1.action = "${ctx}/Titles/listConditTitles.action";
	}
</script>
</head>
<body>
	<form action="${ctx}/Titles/listConditTitles.action" method="post" id="f1">
		<div class="mod-1">
			<div class="hd">
				<h3>商品下架提示</h3>
			</div>
			<div class="bd clearfix">
				<div style="margin-bottom: 10px;" class="container-1">
				  <s:select id="conditkey" name="conditkey" list="#{'title':'专题名称','id':'专题ID'}" onchange="javascript:condit_change();"/>
				  <input type="text" class="txt-5"  id="conditvalue" name="conditvalue"  value="${conditvalue }"/>
				  &nbsp;&nbsp;创建者：
				  <input type="text" class="txt-5" id="creator" name="creator" value="${creator }"/>  	 
				  &nbsp;&nbsp;创建时间：
				  <input onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"  value="${tempTime}" id="titles.createTime" name="titles.createTime" class="txt-5">
				  	<img onclick="yxbegin()" src="http://app.gomein.net.cn/topics/images/images_3.gif" /> 
				  </input>
				  &nbsp;&nbsp;专题修改状态：
				  <s:select id="tempStat" name="tempStat" list="#{0:'默认',1:'未发布专题',2:'最近修改专题'}"/>
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
									
									<input type="button" value="删除"
									onclick="delTitles()" />
							
									</td>
									
										<td align="right" style="padding-right: 5px;">
			 								<input type="button"  value="生成列表"  onclick="javascript:openList();"/>
			 							</td>
							
									
							</tr>
						</tbody>
					</table>
					<table class="tb-zebra tmp-class" style="width: 100%;">
						<colgroup>
							<col style="width: 5%;" />
							<col style="width: 10%;" />
							<col style="width: 15%;" />
							<col style="width: 16%;" />
							<col style="width: 10%;" />
							<col style="width: 10%;" />
							<col style="width: 7%;" />
							<col style="width: 7%;" />
							<col style="width: 20%;" />
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
										  <s:if test="paths != null&& paths!=''">
										     <a href="${titleTestBaseUrl}<s:property value='paths'  />/" target="blank">预览</a>
										 </s:if>
										 <s:else>
										     <a href="${titleTestBaseUrl}<s:property value='id' />/" target="blank" >预览</a>
										   
										   </s:else>
										&nbsp;&nbsp;&nbsp;&nbsp; 
									
										<a
											href="javascript:void(0)" onclick="publicOneTitle(<s:property value='id' />)">发布</a>
											
									
										&nbsp;&nbsp;&nbsp;&nbsp; 
										<a
											href="${ctx}/Titles/edit.action?titles.id=<s:property value='id' />&forwards=1">编辑</a>
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