<%@page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>阿拉丁管理</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.6.js"></script>
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
		   window.location.href = "${ctx}/Aladdin/delete.action?tags=" + str;
		 }
	}
	
	function tunePage(num) {
		var names = document.getElementById("names").value;
		var related = document.getElementById("related").value;
		var url = '${ctx}/Aladdin/list.action?' + 'page_index=' + num;
		
		if(null != names && '' != names){
			url += '&names=' + names;
		}
		if(null != related && '' != related){
			url += '&related=' + related;
		}
		window.location = url;
	}
	
	//按回车实现搜索
	document.onkeydown=function(event){
		e = event ? event :(window.event ? window.event : null); 
	        if(e.keyCode==13){
	        	//document.form[0].submit();
	        	document.getElementById("f1").submit();
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
	
	function createAladdinXml(){
		
		$.post("${ctx}/Aladdin/sendMessage.action",
				function(msg) {
					if (msg == "yes") { 
						alert("文件生成成功,请访问 http://www.gome.com.cn/aladdin/xmlForBaiduAladdin.xml 查看。");					 
					} else {
						alert("文件生成失败。");
					}
				});
		return ;
	}
	
	//添加泛需求词
	function addItem(){
		var url = '${ctx}/Aladdin/edit.action';
		window.location = url;
		return;
	}
	
	//删除
	function delAkeywords() {
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
		   window.location.href = "${ctx}/Aladdin/delete.action?tags=" + str;
		}
	}
</script>
</head>
<body>
	<form action="${ctx}/Aladdin/list.action" method="post" id="f1">
		<div class="mod-1">
			<div class="hd">
				<h3>阿拉丁管理</h3>
			</div>
			<div class="bd clearfix">
				<div style="margin-bottom: 10px;" class="container-1">
				  	泛需求词：
				  	<input type="text" class="txt-5" id="names" name="names" value="${names}"/>  	
				  	品牌关联状态：
				  	<s:select id="related" name="related" list="#{'':'全部','Y':'已关联','N':'未关联'}"/> 
				  <input type="submit" value="搜索" /><br/>
				</div>
				<div class="container-1">
					<table style="width: 100%;">
						<tbody>
							<tr>
								<td style="height: 25px; padding: 10px 0; vertical-align: middle;">
									<input type="button" value="全选" onclick="sel_all('true')" /> 
									<input type="button" value="取消" onclick="sel_all()"/>
									<input type="button" value="删除" onclick="javascript:delAkeywords()"/>
									<input type="button" value="添加" onclick="javascript:addItem()"/>
									<input type="button" value="生成阿拉丁模板文件" onclick="javascript:createAladdinXml()"/>
								</td>
							</tr>
						</tbody>
					</table>
					<table class="tb-zebra tmp-class" style="width: 100%;">
						<colgroup>
							<col style="width: 20%;" />
							<col style="width: 20%;" />
							<col style="width: 20%;" />
							<col style="width: 20%;" />
							<col style="width: 20%;" />
						</colgroup>
						<thead>
							<tr>
								<td style="line-height: 1;"></td>
								<td style="line-height: 1;">编号</td>
								<td style="line-height: 1;">泛需求词</td>
								<td style="line-height: 1;">关联品牌</td>
								<td style="line-height: 1;">操作</td>
							</tr>
						</thead>
						<tbody>
							<s:if test="keywordsList != null">
								<s:iterator value="keywordsList"  status="st" var="bean">
									<tr>
										<td><label> <input type="checkbox" name="cbs"
												id="cbs" value="<s:property value='id' />" /> </label></td>
										<td><s:property value='id' /></td>
										<td><s:property value='names' /></td>
										<td>
											<c:if test="${catalogName != null }"><s:property value='catalogName' /></c:if>
											<c:if test="${catalogName == null }">未关联品牌</c:if>
										</td>
										<td>
											<a
											href="${ctx}/Aladdin/edit.action?id=<s:property value='id' />&forwards=0">编辑</a>
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
											<coo8:page name="keywordsList" style="js" systemId="1" />
										</div>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>