<%@ page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link rel="stylesheet" href="${ctx}/styles/cui.css" />
	<link href="http://css.gomein.net.cn/topics/css/b2c_backstage.css" rel="stylesheet" type="text/css" />
	<link href="http://css.gomein.net.cn/topics/css/OPTBG.css" rel="stylesheet" type="text/css" />
	<style>
		html{*overflow-y:scroll;*overflow-x:hidden}
	</style>
	<script type="text/javascript" charset="GBK" src="${ctx}/js/ueditor/editor_config.js"></script>
	<script type="text/javascript" charset="GBK" src="${ctx}/js/jquery-1.6.js"></script>
	<script type="text/javascript" charset="GBK" src="${ctx}/js/ueditor/editor_all.js"></script>
	<script type="text/javascript" src="${ctx}/js/singleCalendar/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/js/tag/tag.js"></script>
	<script>
		$(function(){
			getSubTags('${hotKeyword.firstTagId}', 'secondTagId', '${hotKeyword.secondTagId}');
		});
	</script>
	<title>导入热词</title>
</head>
<body style="background:#afb8bf;overflow-x:hidden;overflow-y:scroll;*overflow-y:hidden">
	<div class="mod-1">
		<div class="hd">
			<h3>添加热词</h3>
			<h3><a href="${ctx}/HotKeyword/list.action?pageNumber=${pageNumber}"  >返回上一级</a></h3>
		</div>
		<div class="bd clearfix">
			<div class="container-1">
				<form action="<c:if test="${hotKeyword == null}">./add.action</c:if><c:if test="${hotKeyword != null}">./update.action?id=${hotKeyword.id}</c:if>" 
					method="post" name="hotwordForm" id="hotwordForm" onreset="clearInfo()">
					<table class="tb-1">
						<tbody>
							<tr>
								<td width="100">标签：</td>
								<td>
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
                                    <td>
                                        <select name="firstTagId" id="firstTagId" onchange="getSubTags(value, 'secondTagId', null)">
                                            <option value="">请选择</option>
                                            <c:forEach items="${tags}" var="tag">
                                                <option value="${tag.id}" <c:if test="${hotKeyword.firstTagId==tag.id}">selected="selected"</c:if>>${tag.tagName}</option>
                                            </c:forEach>
                                        </select>
                                        <select name="secondTagId" id="secondTagId">
                                            <option value="">请选择</option>
                                        </select>
                                        <font color="red">*</font>
                                    </td>
                                </tr>
								</table>
								</td>
							</tr>
							<tr>
								<td width="100">搜索词标题：</td>
								<td>
									<input type="text" value="${hotKeyword.title}" id="title" class="inbox" name="title"/>
									<font color="red">*</font>
								</td>
							</tr>
							<tr>
								<td width="100">推荐商品ID：</td>
								<td>
									<input type="text" class="inbox" value="${hotKeyword.productId}" name="goodsId" id="goodsId" />
									<font color="red">*</font>
									<input 	type="button" value="确认" onclick="checkGoodsId('1')" /><div id="goodsResult" class="gray" >
									<br />
									<span style="color: red;" id="error_info"></span>
								</td>
							</tr>
							<tr>
								<td width="100">&nbsp;</td>
								<td>
									<input type="button" onclick="sub()" value="保存" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" value="重置" />
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		// 导入热词
		var isRightProductFlag = 1;
		function sub()
		{
			var firstTagId = $("#firstTagId").val();
			if(!firstTagId)
			{
				alert("请选择一级标签");
				return;
			}
			var secondTagId = $("#secondTagId").val();
            if(!secondTagId)
            {
                alert("请选择二级标签");
                return;
            }
			var title = $("#title").val();
			if(!title)
			{
				alert("请添加搜索词标题");
				return;
			}
			var goodsId = $("#goodsId").val();
			if(isRightProductFlag==1 && goodsId=="")
			{
				alert("请输入商品ID");
				return;
			}
			else if(isRightProductFlag==2){
			    alert("请输入正确格式的商品ID");
                return;
			}
			else if(isRightProductFlag==3){
                alert("请输入可用的商品ID");
                return;
            }
            if(isRightProductFlag!=4 && goodsId!=""){
                alert("请点击确定按钮，输入可用的商品ID");
                return;
            }
			$("#hotwordForm").submit();
		}
		
/* 		//检测产品是否存在
		function check()
		{
			var product_id = $.trim($("#product_id").val());
			if(!product_id)
			{
			    isRightProductFlag = 1;
				alert("请输入产品ID");
				return;
			}
			var reg = /^[a-zA-Z]*\d+$/;
			if(!product_id.match(reg)){
			    isRightProductFlag = 2;
			    $("#error_info").css('color','red');
			    $("#error_info").html("商品ID格式不对，请重新输入");
			    $("#product_id").css('border','1px solid red');
			    $("#product_id").foucs();
			    return;
			}
			$.post("./check.action", {product_id:product_id}, function(result){
				eval("var productObj = " + result);
				if($.trim(productObj.productname) == '' || $.trim(productObj.productId) == '')
				{   
				    isRightProductFlag = 3;
				    $("#error_info").css('color','red');
					$("#error_info").html("对不起，该商品ID不可用");
				}else{
				    isRightProductFlag = 4;
				    $("#product_id").css('border','');
				    $("#error_info").css('color','blue');
					$("#error_info").html("该商品ID可用"+",商品名称："+productObj.productname);
				}
			});
		} */
		
		
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
				var reg = /^[a-zA-Z]*\d+$/;
				if(!goodsId.match(reg))
			    {
			        alert("商品ID输入有误");
			        $('#goodsId').focus();
			        return false;
			    }
				$.post("${ctx}/Titles/checkGoodsExists.action?titles.goodsId="+goodsId, function(msg) {
					 
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
					    isRightProductFlag = 4;
					   document.getElementById("goodsResult").innerHTML="<font color=blue >"+$('#goodsId').val()+"</font>可以使用";
					   document.getElementById("tempText").innerHTML="<textarea id='"+$('#goodsId').val()+"' style='display:none'>"+obj.productdesc+"</textarea>";
					   document.getElementById("goodsName").value= obj.productname;
					   editor.render($('#goodsId').val());
					  
					   
					}
				});
			}
			
		}
		
		// 获取子标签集合
		/**
		function getSubTags(parent_id)
		{
			$.post("./soptions.action", {parent_id: parent_id}, function(result){
				$("#secondTagId").html("");
				$("#secondTagId").html(result);
			});
		}
		*/
		
		function clearInfo (){
		    $("#goodsId").css('border','');
		    $("#error_info").html("");
		};
	</script>
</body>
