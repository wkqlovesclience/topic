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
	<script type="text/javascript" charset="GBK" src="${ctx}/js/jquery-1.6.js"></script>
	<script type="text/javascript" src="${ctx}/js/singleCalendar/WdatePicker.js"></script>
	<script>
		$(function(){
			$("#is_show").val("${facetGroupItem.isshow}");
		});
		
		function goback(qparent_pageNumer){
			window.location = "./list.action?pageNumber="+qparent_pageNumer+"&qparent_id=${facetGroupItem.parentId}&qcatid=${facetGroupItem.catId}";
		}
		
	</script>
	<title>����ֵ�༭</title>
</head>
<body style="background:#afb8bf;overflow-x:hidden;overflow-y:scroll;*overflow-y:hidden">
	<div class="mod-1">
		<div class="hd">
			<h3>�༭����ֵ</h3>
		</div>
		<div class="bd clearfix">
			<div class="container-1">
				<form action="<c:if test="${facetGroupItem.id == null}">./add.action</c:if><c:if test="${facetGroupItem.id != null}">./update.action?id=${facetGroupItem.id}</c:if>" 
					method="post" name="facetGroupItemForm" id="facetGroupItemForm">
					<table class="tb-1">
						<tbody>
							<tr>
								<td width="100">����ID��</td>
								<td>
									<input value="${facetGroupItem.catId}" id="parent_id" class="inbox" name="cat_id"/>
									<input type="hidden" onclick="chooseParentId()" value="ѡ���ϼ�����"/>
									<input type="hidden" onclick="recovery()" value="һ������"/>
									<font color="red">*</font>
								</td>
							</tr>
							<tr>
								<td width="100">����ֵ���ƣ�</td>
								<td>
									<input type="text" value="${facetGroupItem.value}" id="value" class="inbox" name="value"/>
									<font color="red">*</font>
								</td>
							</tr>
							<tr>
                                <td width="100">����ֵ��</td>
                                <td>
                                    <input type="text" class="inbox" value="${facetGroupItem.index}" name="index" id="index" />
                                    <font color="red">*</font>
                                </td>
                            </tr>
							<tr>
								<td width="100">����ID��</td>
								<td>
									<input type="text" class="inbox" value="${facetGroupItem.parentId}" name="group_id" id="group_id" />
									<font color="red">*</font>
								</td>
							</tr>
							<tr>
								<td width="100">facetId��</td>
								<td>
									<input type="text" class="inbox" value="${facetGroupItem.facetId}" name="facet_id" id="facet_id"  />
									<font color="red">*</font>
								</td>
							</tr>
							<tr>
								<td width="100">�Ƿ���ʾ��</td>
								<td>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
	                                    <td>
	                                        <select name="is_show" id="is_show">
	                                            <option value="">��ѡ��</option>
												<option value="1">��ʾ</option>
	                                            <option value="0">����ʾ</option>
	                                        </select>
	                                        <font color="red">*</font>
	                                    </td>
	                                </tr>
									</table>
								</td>
							</tr>
							<tr>
								<td width="100"></td>
								<td>
								    <input type="hidden" value="${pageNumber}" name="pageNumber"/>
								    <input type="hidden" value="${qcatid}" name="qcatid"/>
								    <input type="hidden" value="${qparent_id}" name="qparent_id"/>
									<input type="button" onclick="sub()" value="����" />
									<input type="reset" value="����" />
								    <input type="button" value="������һ��"  onclick="goback(${pageNumber});"/>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		function chooseParentId()
		{
			window.showModalDialog ("../Category/categorys.action", "category","dialogWidth:" + 1300 + "px; dialogHeight:" + 700 + 
				"px; status:no;directories:yes;scrollbars:no;Resizable=false;");
		}
		
		function recovery()
		{
			$("#parent_id").val("homeStoreRootCategory");
		}
		
		function checkId()
		{
			var group_id = $.trim($("#group_id").val());
			if(!group_id)
			{
				alert("����д����ID");
				return;
			}
			var reg = /^[a-zA-Z]*\d+$/;
			if(!group_id.match(reg))
			{
				alert("����ID���Ϸ�");
				return;
			}
			else
			{
				if("${facetGroup.groupId}")
				{
					if(group_id != "${facetGroup.groupId}")
					{
						$.post("./check.action", {id:group_id}, function(result){
							if($.trim(result) == "fail")
							{
								var obj = document.getElementById("info_error");
								obj.innerHTML = "�÷���ID�Ѵ���";
								obj.style.color = "red";
							}
							else
							{
								var obj = document.getElementById("info_error");
								obj.innerHTML = "�÷���ID����ʹ��";
								obj.style.color = "green";
							}
						});
					}
					else
					{
						var obj = document.getElementById("info_error");
						obj.innerHTML = "�÷���ID����ʹ��";
						obj.style.color = "green";
					}
				}
				else
				{
					$.post("./check.action", {id:group_id}, function(result){
						if($.trim(result) == "fail")
						{
							var obj = document.getElementById("info_error");
							obj.innerHTML = "�÷���ID�Ѵ���";
							obj.style.color = "red";
						}
						else
						{
							var obj = document.getElementById("info_error");
							obj.innerHTML = "�÷���ID����ʹ��";
							obj.style.color = "green";
						}
					});
				}
			}
		}
		
		function sub()
		{
			var parent_id = $.trim($("#parent_id").val());
			if(!parent_id)
			{
				alert("��ѡ�����ID");
				return;
			}
			if(!/^[a-z]+\d+$/.test(parent_id)){
                alert("��ѡ����ȷ��ʽ�ķ���ID");
                return;
            }
			var value = $.trim($("#value").val());
			if(!value)
			{
				alert("����д����ֵ����");
				return;
			}
			var index = $.trim($("#index").val());
			if(index)
			{
				if(isNaN(index))
				{
					alert("����ֵΪ��������");
					return;
				}
			}
			else
			{
				alert("����д����ֵ");
				return;
			}
			var group_id = $.trim($("#group_id").val());
			if(!group_id)
			{
				alert("����д����ID");
				return;
			}
			var facet_id = $.trim($("#facet_id").val());
			if(!facet_id)
			{
				alert("����дfacetId");
				return;
			}
			var is_show = $.trim($("#is_show").val());
			if(!is_show)
			{
				alert("��ѡ���Ƿ���ʾ");
				return;
			}
			document.forms["facetGroupItemForm"].submit();
		}
	</script>
</body>
</html>