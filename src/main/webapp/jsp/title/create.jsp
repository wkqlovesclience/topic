<%@page import="com.coo8.topic.model.*"%>
<%@page import="com.coo8.topic.controller.action.TitlesAction"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
<title>�����Ʒר��</title>
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
								+ "<th>�ؼ��ʣ�</th>"
								+ "<td><input type='text' class='txt-7' id='keyss'" + 
							"name='keyss' /></td><th>���ӵ�ַ��</th><td><input onblur='checkUrl(this.value)' type='text' class='txt-9' id='keyssUrl' name='keyssUrl'  /> </td></tr>");

	}
	function checkUrl(v){
		if(v!=""){
			var str= new Array();  
			str=v.split("//");
			if(str[0]!="http:"){
				alert('��������������!(����http://)');
			}
		}
	}
	function checkPaths() {
		var paths = $('#paths').val();
		if (paths == '') {
			alert('����д·��!');
			return;
		}
		$.post("${ctx}/Titles/checkPaths.action?titles.paths=" + paths,
				function(msg) {
					if (msg == "yes") { 
						document.getElementById("result").innerHTML="·��<font color=red >"+$('#paths').val()+"</font> ����ʹ��";
					 
					} else {
						alert('·���Ѿ�����!');
						$('#paths').attr("value", "");
					}
				});
	}
	function deleteKey(id) {
	   if(confirm('ȷ��ɾ����')){
		$.post("${ctx}/Titles/deleteKey.action?idsString=" + id,
				function(msg) {
					if (msg == "yes") { 
					   alert('ɾ���ɹ�!');
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
					alert('��ƷIDû�ж�Ӧ�� ��Ʒ!');
				}
			});
		}else if(v=='1'){
			var goodsId = $('#goodsId').val();
			if (goodsId == '') {
				alert('����д��ƷID!');
				return;
			}
			var re = /^\w{3}[0-9]+.?[0-9]*$/;   //�ж������� /^[1-9]+[0-9]*]*$/   
		    if (!re.test(goodsId))
		    {
		        alert("��ƷID��������");
		        $('#goodsId').focus();
		        return false;
		    }
			$.post("${ctx}/Titles/checkGoodsExists.action?titles.goodsId="+goodsId+'&titles.id=${titles.id}', function(msg) {
				 
				if (msg == "noGoods") {
				    document.getElementById("goodsResult").innerHTML="<font color=red >û�и���ƷId!</font>";
					$('#goodsId').attr("value", "");  
				}else if(msg == "exist"){
				    document.getElementById("goodsResult").innerHTML="<font color=red >ר���д���Ʒ�Ѿ�����!</font>";
					  
					$('#goodsId').attr("value", "");
				}else{
				   var obj = JSON.parse(msg); 
				   //alert(obj.productname);
				   //alert(obj.productdesc);
				   document.getElementById("goodsResult").innerHTML="<font color=blue >"+$('#goodsId').val()+"</font>����ʹ��";
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
				//alert('����ʹ��');
			} else {				
				if(i=='1'){
					alert('�ؼ����Ѿ�����!');
				}
				if(i=='2'){
					alert('��ǩ�Ѿ�����!');
				}
				if(i==v){
					alert('�ؼ���url�Ѿ�����!');
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
		  		alert('��ר���Ѵ��ڣ����޸�ר������!');
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
		     	alert('������ר������!');
		     	
		     	$('#title').focus();
		        return false;
		     }
		     if(goodsId==''){
		     	alert('��ѡ����Ʒ!');
		     	   
		     	$('#goodsId').focus();
		        return false;
		     }else{ 
		           var re = /^\w{3}[0-9]+.?[0-9]*$/;   //�ж������� /^[1-9]+[0-9]*]*$/   
				   if (!re.test(goodsId))
				    {
				        alert("��ƷID��������");
				        $('#goodsId').focus();
				        return false;
				   }
			 }
		     if(goodsName==''){
		     	alert('��������Ʒ����!');
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
					<h3>�༭��Ʒר��</h3>
				</c:if>
				<c:if test="${titles.id == null}">
					<h3>�����Ʒר��</h3>
				</c:if>
				<h3><a href="./list.action?page_index=${page_index}">������һ��</a></h3>
			</div>

			<div class="bd clearfix">
				<div class="container-1">
					<h3>������Ϣ</h3>
					<div class="line-1"></div>
					<table class="tb-1">
						<tbody>
							<tr>
								<th style="color:red;">�Զ���·����</th>
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
									class="gray">(Ĭ���Զ�����,����Ҫ��д,��������Զ�����д)</span>
									<input type="hidden" name="titles.paths"  value="${titles.paths}"/>
				                    </c:if>
				                    <c:if test="${titles.id == null}">
					                     <input
									type="text"   class="txt-7"  id="paths" name="titles.paths"
									value="${titles.paths}" /><span
									class="gray">(Ĭ���Զ�����,����Ҫ��д,��������Զ�����д)</span>
				                    </c:if>
								
									<!-- <input type="button" value="ȷ��" onclick="checkPaths()" /><div id="result" class="gray" ></div> -->
								</td>
							</tr>

							<tr>
								<th>���⣺</th>
								<td> 
								    <input type="text" class="txt-7" id="title"
									name="titles.title" value="${titles.title}" onblur="checkTitleName(this.value)"/>
								</td>
							</tr>

							<tr>
								<th>�ؼ��ʣ�</th>
								<td>  <input type="text" class="txt-7" id="keys" name="keys"
									value="${keys }" /></td>
							</tr>

							<tr>
								<th>��ǩ��</th>
								<td><input type="text" class="txt-7" id="tags" name="tags"
									value="${tags }"  /><span
									class="gray">�������ǩ�á�,��������</span>
								</td>
							</tr>
						</tbody>
					</table>
					<h3>
						ר����Ϣ<span class="gray" class="txt-9">��ѡ�</span>
					</h3>
					<div class="line-1"></div>
					<table class="tb-1">
						<tbody>
							<tr>
								<th>�Ƽ���ƷID��</th>
								<td><input type="text" class="txt-2" id="goodsId" style="width: 100px"
									name="titles.goodsId" value="${titles.goodsId }" />
									<input 	type="button" value="ȷ��" onclick="checkGoodsId('1')" /><div id="goodsResult" class="gray" >									
								</td>
							</tr>
							
							<tr>
								<th>��ƷSKU��</th>
								<td><input type="text" class="txt-2" id="goodsId" style="width: 100px"
									name="titles.skuId" value="${titles.skuId }" />
								</td>
							</tr>

							<tr>
								<th>�Ƽ���Ʒ���ƣ�</th>
								<td><input type="text" class="txt-9" id="goodsName"
									name="titles.goodsName" value="${titles.goodsName}"  />
								</td>
							</tr>
						</tbody>
					</table>
					<h3>
						������Ʒ<span class="gray" si>��ѡ�</span>
					</h3>
					<div class="line-1"></div>
					<table class="tb-1">
						<tbody>
							<tr>
								<th>���������ƷID��</th>
								<td><input type="text" class="txt-9" id="gids" name="gids"
									value="${gids}"  /><span class="gray">�������ǩ�á�,��������</span>
									
									</td>
							</tr>
						</tbody>
					</table>
					<h3>ר�����</h3>
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
						���Ų����Ƽ�<span class="gray">��ѡ�</span>
					</h3>
					<div class="line-1"></div>
					<table id="table1" class="tb-1"> 
						<s:iterator value="urlMapKey"  status="st">				
						<tr>
							<th>�ؼ��ʣ�</th>
							<td><input type="text" class="txt-7" id="keyss" name="keyss"
								value="<s:property value='names' />"/>
							</td>
							<th>���ӵ�ַ��</th>
							<td><input type="text" class="txt-9" id="keyssUrl"
								name="keyssUrl" value="<s:property value='url' />" />
						   <td>
								<input type="button" value="ɾ��"  onclick="deleteKey(<s:property value='id' />)" /></td>
							</td>
						</tr></s:iterator>
						 <s:else>
						<tr>
							<th>�ؼ��ʣ�</th>
							<td><input type="text" class="txt-7" id="keyss" name="keyss"  />
							</td>
							<th>���ӵ�ַ��</th>
							<td><input type="text" class="txt-9" id="keyssUrl"
								name="keyssUrl"  /></td>
						</tr>
						</s:else>
						
						<tr>
							<th>�ؼ��ʣ�</th>
							<td><input type="text" class="txt-7" id="keyss" name="keyss"
								/></td>
							<th>���ӵ�ַ��</th>
							<td><input type="text" class="txt-9" id="keyssUrl" onblur="checkUrl(this.value)"
								name="keyssUrl" /></td>
							<td><input type="button" onclick="addKeytr()"  value="����������" />
							</td>
						</tr>
					</table>
					<div class="But">
						<input type="submit" value="����"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
							type="reset" value="����" />
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>