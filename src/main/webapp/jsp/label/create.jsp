<%@page import="com.coo8.topic.model.*"%>
<%@page import="com.coo8.topic.controller.action.TitlesAction"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
<title>添加商品专题</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />

<script type="text/javascript" charset="GBK" src="${ctx}/js/ueditor/editor_config.js"></script>
<script type="text/javascript" charset="GBK" src="${ctx}/js/jquery-1.6.js"></script>      
<script type="text/javascript" charset="GBK" src="${ctx}/js/ueditor/editor_all.js"></script>   
<link rel="stylesheet" type="text/css" href="${ctx}/js/ueditor/themes/default/ueditor.css"/>   
<script type="text/javascript">
	function addKeytr() {	
		$("#table1")
				.append(
						"<tr>"
								+ "<th>关键词：</th>"
								+ "<td><input type='text' class='txt-7' id='keyss'" + 
							"name='keyss' /></td><th>链接地址：</th><td><input onblur='checkUrl(this.value)' type='text' class='txt-9' id='keyssUrl' name='keyssUrl'  /> </td></tr>");

	}
	function checkUrl(v){
		if(v!=""){
			var str= new Array();  
			str=v.split("//");
			if(str[0]!="http:"){
				alert('请输入完整链接!(包括http://)');
			}
		}
	}
	function checkPaths() {
		var paths = $('#paths').val();
		if (paths == '') {
			alert('请填写路径!');
			return;
		}
		$.post("${ctx}/Titles/checkPaths.action?titles.paths=" + paths,
				function(msg) {
					if (msg == "yes") { 
						document.getElementById("result").innerHTML="路径<font color=red >"+$('#paths').val()+"</font> 可以使用";
					 
					} else {
						alert('路径已经存在!');
						$('#paths').attr("value", "");
					}
				});
	}
	function deleteKey(id) {
	   if(confirm('确认删除？')){
		$.post("${ctx}/Titles/deleteKey.action?idsString=" + id,
				function(msg) {
					if (msg == "yes") { 
					   alert('删除成功!');
					   window.location.reload();
					}  
				});
	   }
	}
	function checkGoodsId(v) {
		if(v=='2'){
			var gids = $('#gids').val();
			if (gids == '') {
				return;
			}
			$.post("${ctx}/Titles/checkGoodsId.action?gids="
					+ gids, function(msg) {
				if (msg != "exist") {
					alert('商品ID没有对应的 商品!');
				}
			});
		}else if(v=='1'){
			var goodsId = $('#goodsId').val();
			if (goodsId == '') {
				alert('请填写商品ID!');
				return;
			}
			var re = /^\w{3}[0-9]+.?[0-9]*$/;   //判断正整数 /^[1-9]+[0-9]*]*$/   
		    if (!re.test(goodsId))
		    {
		        alert("商品ID输入有误");
		        $('#goodsId').focus();
		        return false;
		    }
			$.post("${ctx}/Titles/checkGoodsExists.action?titles.goodsId="+goodsId+'&titles.id=${titles.id}', function(msg) {
				 
				if (msg == "noGoods") {
				    document.getElementById("goodsResult").innerHTML="<font color=red >没有该商品Id!</font>";
					$('#goodsId').attr("value", "");  
				}else if(msg == "exist"){
				    document.getElementById("goodsResult").innerHTML="<font color=red >专题中此商品已经存在!</font>";
					  
					$('#goodsId').attr("value", "");
				}else{
				   var obj = JSON.parse(msg); 
				   //alert(obj.productname);
				   //alert(obj.productdesc);
				   document.getElementById("goodsResult").innerHTML="<font color=blue >"+$('#goodsId').val()+"</font>可以使用";
				   document.getElementById("tempText").innerHTML="<textarea id='"+$('#goodsId').val()+"' style='display:none'>"+obj.productdesc+"</textarea>";
				   document.getElementById("goodsName").value= obj.productname;
				   editor.render($('#goodsId').val());
				  
				   
				}
			});
		}
		
	}

	function checkKeywords(i,v){	
		if (v == '') {
			return;
		}		
		var url="${ctx}/Titles/checkKeywords.action?";
		if(i=='1'){
			url=url+"keys="+v;
		}else if(i=='2'){
			url=url+"tags="+v;
		}else if(i==v){		
			url=url+"keyss="+i;
		}
		
		$.post(url, function(msg) {
			if (msg == "yes") {
				//alert('可以使用');
			} else {				
				if(i=='1'){
					alert('关键词已经存在!');
				}
				if(i=='2'){
					alert('标签已经存在!');
				}
				if(i==v){
					alert('关键词url已经存在!');
				}
				return;
			}
		});		
	}
	function checkTitleName(v){ 
		if (v == '') {
			return;
		}	
		var id=document.getElementById("id").value;
		v=encodeURIComponent(encodeURIComponent(v)); 
	  	$.get("${ctx}/Titles/checkTitleName.action?titlesNames="+ v+'&titleId='+id+'&t='+Math.random(), function(msg) {
		    if (msg != "yes") {
		        document.getElementById("title").value=""
				$('#title').attr("value", "");
		  		alert('此专题已存在，请修改专题名称!');
			} 
		});
	}
	 
	$(document).ready(function() {
	 	$('form').submit(function() {
	 	      var title = $('#title').val();
			  var goodsId = $('#goodsId').val();
			  var skuId = $('skuId').val();
			  var goodsName = $('#goodsName').val();
			  var reslut='';
			 
	 	     if(title==''){
		     	alert('请输入专题名称!');
		     	
		     	$('#title').focus();
		        return false;
		     }
		     if(goodsId==''){
		     	alert('请选择商品!');
		     	   
		     	$('#goodsId').focus();
		        return false;
		     }else{ 
		           var re = /^\w{3}[0-9]+.?[0-9]*$/;   //判断正整数 /^[1-9]+[0-9]*]*$/   
				   if (!re.test(goodsId))
				    {
				        alert("商品ID输入有误");
				        $('#goodsId').focus();
				        return false;
				   }
			 }
		     if(goodsName==''){
		     	alert('请输入商品标题!');
		     	$('#goodsName').focus();
		        return false;
		     }
		     
	    });
	   scroll(0,0);
	});

</script>
</head>
<body>
	<form name="form" action="${ctx}/Titles/save.action" method="post">
		<s:hidden name="command"></s:hidden>
		<s:hidden name="forwards"></s:hidden>
		<s:hidden name="titles.creator"></s:hidden>
		<div class="mod-1">
			<div class="hd">
				<c:if test="${titles.id != null }">
					<h3>编辑商品专题</h3>
				</c:if>
				<c:if test="${titles.id == null}">
					<h3>添加商品专题</h3>
				</c:if>
				<h3><a href="./list.action?page_index=${page_index}">返回上一级</a></h3>
			</div>

			<div class="bd clearfix">
				<div class="container-1">
					<h3>基本信息</h3>
					<div class="line-1"></div>
					<table class="tb-1">
						<tbody>
							<tr>
								<th style="color:red;">自定义路径：</th>
								<td><input type="hidden" name="titles.id"  id="id"
									value="${titles.id}" />
									<input
									type="hidden" name="titles.publicStat"
									value="${titles.publicStat }" /> <input type="hidden"
									name="titles.checkStat" value="${titles.checkStat }" />
									<c:if test="${titles.id != null }">
					                     <input
									type="text"   class="txt-7"  id="paths" 
									value="${titles.paths}"  disabled="disabled"/><span
									class="gray">(默认自动生成,不需要填写,特殊情况自定义填写)</span>
									<input type="hidden" name="titles.paths"  value="${titles.paths}"/>
				                    </c:if>
				                    <c:if test="${titles.id == null}">
					                     <input
									type="text"   class="txt-7"  id="paths" name="titles.paths"
									value="${titles.paths}" /><span
									class="gray">(默认自动生成,不需要填写,特殊情况自定义填写)</span>
				                    </c:if>
								
									<!-- <input type="button" value="确认" onclick="checkPaths()" /><div id="result" class="gray" ></div> -->
								</td>
							</tr>

							<tr>
								<th>标题：</th>
								<td> 
								    <input type="text" class="txt-7" id="title"
									name="titles.title" value="${titles.title}" onblur="checkTitleName(this.value)"/>
								</td>
							</tr>

							<tr>
								<th>关键词：</th>
								<td>  <input type="text" class="txt-7" id="keys" name="keys"
									value="${keys }" /></td>
							</tr>

							<tr>
								<th>标签：</th>
								<td><input type="text" class="txt-7" id="tags" name="tags"
									value="${tags }"  /><span
									class="gray">（多个标签用“,”隔开）</span>
								</td>
							</tr>
						</tbody>
					</table>
					<h3>
						专题信息<span class="gray" class="txt-9">（选填）</span>
					</h3>
					<div class="line-1"></div>
					<table class="tb-1">
						<tbody>
							<tr>
								<th>推荐商品ID：</th>
								<td><input type="text" class="txt-2" id="goodsId" style="width: 100px"
									name="titles.goodsId" value="${titles.goodsId }" />
									<input 	type="button" value="确认" onclick="checkGoodsId('1')" /><div id="goodsResult" class="gray" >									
								</td>
							</tr>
							
							<tr>
								<th>商品SKU：</th>
								<td><input type="text" class="txt-2" id="goodsId" style="width: 100px"
									name="titles.skuId" value="${titles.skuId }" />
								</td>
							</tr>

							<tr>
								<th>推荐商品名称：</th>
								<td><input type="text" class="txt-9" id="goodsName"
									name="titles.goodsName" value="${titles.goodsName}"  />
								</td>
							</tr>
						</tbody>
					</table>
					<h3>
						调用商品<span class="gray" si>（选填）</span>
					</h3>
					<div class="line-1"></div>
					<table class="tb-1">
						<tbody>
							<tr>
								<th>输入相关商品ID：</th>
								<td><input type="text" class="txt-9" id="gids" name="gids"
									value="${gids}"  /><span class="gray">（多个标签用“,”隔开）</span>
									
									</td>
							</tr>
						</tbody>
					</table>
					<h3>专题介绍</h3>
					  <div id="tempText"></div> 
					 <div class="line-1"></div>
					 <div style="margin: 20px;">
					   
						<textarea id="editor">${titles.sources} </textarea>
					</div>
					<script type="text/javascript">
						var editor = new baidu.editor.ui.Editor();
						editor.render("editor");
					 
					</script>
					<h3>
						热门参数推荐<span class="gray">（选填）</span>
					</h3>
					<div class="line-1"></div>
					<table id="table1" class="tb-1"> 
						<s:iterator value="urlMapKey"  status="st">				
						<tr>
							<th>关键词：</th>
							<td><input type="text" class="txt-7" id="keyss" name="keyss"
								value="<s:property value='names' />"/>
							</td>
							<th>链接地址：</th>
							<td><input type="text" class="txt-9" id="keyssUrl"
								name="keyssUrl" value="<s:property value='url' />" />
						   <td>
								<input type="button" value="删除"  onclick="deleteKey(<s:property value='id' />)" /></td>
							</td>
						</tr></s:iterator>
						 <s:else>
						<tr>
							<th>关键词：</th>
							<td><input type="text" class="txt-7" id="keyss" name="keyss"  />
							</td>
							<th>链接地址：</th>
							<td><input type="text" class="txt-9" id="keyssUrl"
								name="keyssUrl"  /></td>
						</tr>
						</s:else>
						
						<tr>
							<th>关键词：</th>
							<td><input type="text" class="txt-7" id="keyss" name="keyss"
								/></td>
							<th>链接地址：</th>
							<td><input type="text" class="txt-9" id="keyssUrl" onblur="checkUrl(this.value)"
								name="keyssUrl" /></td>
							<td><input type="button" onclick="addKeytr()"  value="增加新链接" />
							</td>
						</tr>
					</table>
					<div class="But">
						<input type="submit" value="保存"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
							type="reset" value="重置" />
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>