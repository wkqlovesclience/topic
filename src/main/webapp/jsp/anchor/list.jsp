<%@page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>关键词管理</title>
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
	
	
	//部分刪除
	function delAnKeywords() {
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
		   window.location.href = "${ctx}/AnchorKeywords/delete.action?tags=" + str;
		 }
	}
	
	//全部刪除
	function delAllAnKeywords() {
		if(confirm('确认删除？')){
		   window.location.href = "${ctx}/AnchorKeywords/deleteAll.action";
		 }
	}
	
	//更新URL状态 
	function test_Url() {
		if(confirm('更新URL状态所需时间较长，确认更新？')){
		   window.location.href = "${ctx}/AnchorKeywords/testUrl.action";
		 }
	}
	
	//导出Excel
	function exportExcel(){
		
		var idBegin = document.getElementById("idBegin").value;
		var idEnd = document.getElementById("idEnd").value;
		
		if(numValidate(idBegin) && numValidate(idEnd) && compareNum(idBegin,idEnd)){
			var f1 = document.getElementById("f1");		
			f1.action="${ctx}/AnchorKeywords/toExcel.action?idBegin="+idBegin + "&idEnd="+idEnd;
			f1.submit();
			f1.action="${ctx}/AnchorKeywords/list.action";
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
	
	function tunePage(num)
			{
		        var testUrl = document.getElementById("testUrl").value;
				var url = "./list.action?page_index=" + num;
				if(null != testUrl && '' != testUrl){
					url += '&testUrl=' + encodeURIComponent(encodeURIComponent(testUrl));
				}
				window.location = url;
			}
			
	
</script>
</head>
<body>
	<form action="${ctx}/AnchorKeywords/list.action" method="post" id="f1">
		<div class="mod-1">
			<div class="hd">
				<h3>关键词管理</h3>
			</div>
			<div class="bd clearfix">
			   <div style="margin-bottom: 5px;" class="container-1">
					关键词ID： <input type="text" class="txt-5" id="anchorId" name="anchorId" value="${param.anchorId}"/>
					  &nbsp;&nbsp; 关键词名称： <input type="text" class="txt-5" id="anchorName" name="anchorName" value="${param.anchorName}"/> 
					  &nbsp;&nbsp;URL状态：
				    <s:select id="testUrl" name="testUrl"  list="#{'':'','p_w':'全部有效','p':'wap无效','w':'pc无效','no':'全部无效'}" />
					 &nbsp;&nbsp;&nbsp;&nbsp;
					 <input type="reset" value="重置"/>&nbsp;&nbsp;
					<input type="submit" value="搜索"/><p>&nbsp;</p>
				</div>
				<div class="container-1">

					<table style="width: 100%;">
						<tbody>
						
							<tr>
								<td style="height: 25px; padding: 10px 0; vertical-align: middle;">
								 <input type="button" value="全选" onclick="sel_all('true')" /> 
								 <input type="button" value="取消"
									onclick="sel_all()" /> 
								<input type="button" value="删除" onclick="delAnKeywords()"/>
	                            <input type="button" value="全部删除" onclick="delAllAnKeywords()"/>
	                            <input type="button" value="更新URL状态" onclick="test_Url()"/>
								</td>
								<td style="padding-right: 10px;" align="right" >关键词ID:
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
							<col style="width: 16%;" />
							<col style="width: 10%;" />
							<col style="width: 10%;" />
							<col style="width: 20%;" />
						</colgroup>
						<thead>
							<tr>
								<td style="line-height: 1;"></td>
								<td style="line-height: 1;">编号</td>
								<td style="line-height: 1;">关键词名称</td>
								<td style="line-height: 1;">WEB链接</td>
								<td style="line-height: 1;">WAP链接</td>
								<td style="line-height: 1;">URL状态</td>
								<td style="line-height: 1;">频率</td>
								<td style="line-height: 1;">优先级</td>
								<td style="line-height: 1;">操作</td>
							</tr>
						</thead>
						<tbody>
							<s:if test="anchorKeywordsList!= null">
								<s:iterator value="anchorKeywordsList"  status="st">
									<tr>
										<td><label> <input type="checkbox" name="cbs"
												id="cbs" value="<s:property value='id' />" /> </label></td>
										<td><s:property value='id' /></td>
										<td><s:property value='keyName' /></td>
										<td><s:property value='pcUrl' /></td>
										<td><s:property value='wapUrl' /></td>
										<td>
										<c:if test="${testUrl=='p_w'}"> 全部有效 </c:if>
										<c:if test="${testUrl=='p'}"><spean style="color:red;"> wap无效 </spean> </c:if>
										<c:if test="${testUrl=='w'}"><spean style="color:red;"> pc无效 </spean> </c:if>
										<c:if test="${testUrl=='no'}"><spean style="color:red;"> 全部无效 </spean> </c:if>
										</td>
										<td><s:property value='rate'/></td>
										<td><s:property value='youxianji'/></td>
										<td> 
										<a href="${ctx}/AnchorKeywords/edit.action?akeywords.id=<s:property value='id' />">编辑</a>
										</td>
									</tr>
								</s:iterator>
							</s:if>
						</tbody>
					</table>
					<table width="100%">
						<tfoot>
							<tr>
								<td style="border: 0 none;">
									<div class="numpage-box">
										<div class="numpage">
											<coo8:page name="anchorKeywordsList" style="js" systemId="1" />
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