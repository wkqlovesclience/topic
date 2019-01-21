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
	<title>�����ȴ�</title>
</head>
<body style="background:#afb8bf;overflow-x:hidden;overflow-y:scroll;*overflow-y:hidden">
	<div class="mod-1">
		<div class="hd">
			<h3>����ȴ�</h3>
			<h3><a href="${ctx}/HotKeyword/list.action?pageNumber=${pageNumber}"  >������һ��</a></h3>
		</div>
		<div class="bd clearfix">
			<div class="container-1">
				<form action="<c:if test="${hotKeyword == null}">./add.action</c:if><c:if test="${hotKeyword != null}">./update.action?id=${hotKeyword.id}</c:if>" 
					method="post" name="hotwordForm" id="hotwordForm" onreset="clearInfo()">
					<table class="tb-1">
						<tbody>
							<tr>
								<td width="100">��ǩ��</td>
								<td>
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
                                    <td>
                                        <select name="firstTagId" id="firstTagId" onchange="getSubTags(value, 'secondTagId', null)">
                                            <option value="">��ѡ��</option>
                                            <c:forEach items="${tags}" var="tag">
                                                <option value="${tag.id}" <c:if test="${hotKeyword.firstTagId==tag.id}">selected="selected"</c:if>>${tag.tagName}</option>
                                            </c:forEach>
                                        </select>
                                        <select name="secondTagId" id="secondTagId">
                                            <option value="">��ѡ��</option>
                                        </select>
                                        <font color="red">*</font>
                                    </td>
                                </tr>
								</table>
								</td>
							</tr>
							<tr>
								<td width="100">�����ʱ��⣺</td>
								<td>
									<input type="text" value="${hotKeyword.title}" id="title" class="inbox" name="title"/>
									<font color="red">*</font>
								</td>
							</tr>
							<tr>
								<td width="100">�Ƽ���ƷID��</td>
								<td>
									<input type="text" class="inbox" value="${hotKeyword.productId}" name="goodsId" id="goodsId" />
									<font color="red">*</font>
									<input 	type="button" value="ȷ��" onclick="checkGoodsId('1')" /><div id="goodsResult" class="gray" >
									<br />
									<span style="color: red;" id="error_info"></span>
								</td>
							</tr>
							<tr>
								<td width="100">&nbsp;</td>
								<td>
									<input type="button" onclick="sub()" value="����" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" value="����" />
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		// �����ȴ�
		var isRightProductFlag = 1;
		function sub()
		{
			var firstTagId = $("#firstTagId").val();
			if(!firstTagId)
			{
				alert("��ѡ��һ����ǩ");
				return;
			}
			var secondTagId = $("#secondTagId").val();
            if(!secondTagId)
            {
                alert("��ѡ�������ǩ");
                return;
            }
			var title = $("#title").val();
			if(!title)
			{
				alert("����������ʱ���");
				return;
			}
			var goodsId = $("#goodsId").val();
			if(isRightProductFlag==1 && goodsId=="")
			{
				alert("��������ƷID");
				return;
			}
			else if(isRightProductFlag==2){
			    alert("��������ȷ��ʽ����ƷID");
                return;
			}
			else if(isRightProductFlag==3){
                alert("��������õ���ƷID");
                return;
            }
            if(isRightProductFlag!=4 && goodsId!=""){
                alert("����ȷ����ť��������õ���ƷID");
                return;
            }
			$("#hotwordForm").submit();
		}
		
/* 		//����Ʒ�Ƿ����
		function check()
		{
			var product_id = $.trim($("#product_id").val());
			if(!product_id)
			{
			    isRightProductFlag = 1;
				alert("�������ƷID");
				return;
			}
			var reg = /^[a-zA-Z]*\d+$/;
			if(!product_id.match(reg)){
			    isRightProductFlag = 2;
			    $("#error_info").css('color','red');
			    $("#error_info").html("��ƷID��ʽ���ԣ�����������");
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
					$("#error_info").html("�Բ��𣬸���ƷID������");
				}else{
				    isRightProductFlag = 4;
				    $("#product_id").css('border','');
				    $("#error_info").css('color','blue');
					$("#error_info").html("����ƷID����"+",��Ʒ���ƣ�"+productObj.productname);
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
						alert('��ƷIDû�ж�Ӧ�� ��Ʒ!');
					}
				});
			}else if(v=='1'){
				var goodsId = $('#goodsId').val();
				if (goodsId == '') {
					alert('����д��ƷID!');
					return;
				}
				var reg = /^[a-zA-Z]*\d+$/;
				if(!goodsId.match(reg))
			    {
			        alert("��ƷID��������");
			        $('#goodsId').focus();
			        return false;
			    }
				$.post("${ctx}/Titles/checkGoodsExists.action?titles.goodsId="+goodsId, function(msg) {
					 
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
					    isRightProductFlag = 4;
					   document.getElementById("goodsResult").innerHTML="<font color=blue >"+$('#goodsId').val()+"</font>����ʹ��";
					   document.getElementById("tempText").innerHTML="<textarea id='"+$('#goodsId').val()+"' style='display:none'>"+obj.productdesc+"</textarea>";
					   document.getElementById("goodsName").value= obj.productname;
					   editor.render($('#goodsId').val());
					  
					   
					}
				});
			}
			
		}
		
		// ��ȡ�ӱ�ǩ����
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
