<%@page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>热门链接管理</title>
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
		   window.location.href = "${ctx}/HotLink/delete.action?tags=" + str;
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
				var url = "./list.action?page_index=" + num;
				window.location = url;
				return;
			}
	//判断select下拉框的条件选中
	$(document).ready(function(){
		var type=$("#type").val();
		if(type!=null && type!=""){
			$("#hotLinkModuleType").val(type);
		}
	})		
</script>
</head>
<body>
	<form action="${ctx}/HotLink/list.action" method="post" id="f1">
		<div class="mod-1">
			<div class="hd">
				<h3>热门链接管理</h3>
			</div>
			<div class="bd clearfix">
			   <div style="margin-bottom: 5px;" class="container-1">
					热门链接ID： <input type="text" class="txt-5"  name="hotLinkId" value="${param.hotLinkId}"/>
					热门链接名称： <input type="text" class="txt-5"  name="hotLinkName" value="${param.hotLinkName}"/> 
				          模块ID： <input type="text" class="txt-5"  name="moduleId" value="${param.moduleId}"/> 
					模块类型： <input type="hidden" class="txt-5" id="type" value="${param.hotLinkModuleType}"/>
					<s:select list="#{'':'请选择','1':'热门搜索','2':'商品专题','3':'排行榜' }" theme="simple" name="hotLinkModuleType" id="hotLinkModuleType" cssStyle="width:80px;"></s:select>
					 <input type="reset" value="重置"/>
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
	                        <!--     <input type="button" value="全部删除" onclick="delAllAnKeywords()"/> -->
								</td>
								<!-- <td style="padding-right: 10px;" align="right" >热门链接ID:
											<input type="text" class="txt-5" id="idBegin" />  到 <input type="text" class="txt-5" id="idEnd" /> 
	 										<input type="button" value="导出excel" onclick="javascript:exportExcel();"/>
			 					</td> -->
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
							<col style="width: 10%;" />
							<col style="width: 10%;" />
							<col style="width: 20%;" />
						</colgroup>
						<thead>
							<tr>
								<td style="line-height: 1;"></td>
								<td style="line-height: 1;">编号</td>
								<td style="line-height: 1;">热门链接名称</td>
								<td style="line-height: 1;">WEB链接</td>
								<td style="line-height: 1;">WAP链接</td>
								<td style="line-height: 1;">模块类型</td>
								<td style="line-height: 1;">模块ID</td>
								<td style="line-height: 1;">排序</td>
								<td style="line-height: 1;">操作</td>
							</tr>
						</thead>
						<tbody>
							<s:if test="hotLinkList!= null">
								<s:iterator value="hotLinkList"  status="st">
									<tr>
										<td><label> <input type="checkbox" name="cbs"
												id="cbs" value="<s:property value='id' />" /> </label></td>
										<td><s:property value='id' /></td>
										<td><s:property value='hotName' /></td>
										<td><s:property value='pcUrl' /></td>
										<td><s:property value='wapUrl' /></td>
										<td>
										 <c:if test="${moduleType == 1}">热门搜索</c:if>
										 <c:if test="${moduleType == 2}">商品专题</c:if>
										 <c:if test="${moduleType == 3}">排行榜</c:if>
										</td>
										<td><s:property value='moduleId' /></td>
										<td><s:property value='sort'/></td>
										<td> 
										<a href="${ctx}/HotLink/edit.action?hotLink.id=<s:property value='id' />">编辑</a>
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
											<coo8:page name="hotLinkList" style="js" systemId="1" />
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