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
			$("#is_show").val("${category.isShow}");
		});
		
		
		function goback(qparent_pageNumer){
			window.location = "./list.action?pageNumber="+qparent_pageNumer;
		}
	</script>
	<title>商品分类编辑</title>
</head>
<body style="background:#afb8bf;overflow-x:hidden;overflow-y:scroll;*overflow-y:hidden">
	<div class="mod-1">
		<div class="hd">
			<h3>编辑分类</h3>
		</div>
		<div class="bd clearfix">
			<div class="container-1">
				<form action="<c:if test="${category == null}">./add.action</c:if><c:if test="${category != null}">./update.action?id=${category.id}</c:if>" 
					method="post" name="categoryForm" id="categoryForm">
					<table class="tb-1">
						<tbody>
							<tr>
								<td width="100">分类ID：</td>
								<td>
								    <input type="hidden" name="parent_id" value="homeStoreRootCategory" />
									<input type="text" value="${category.id}" id="id" class="inbox" name="cid"/>
									<input type="button" value="是否可用" onclick="checkId()"/>
									<font color="red">*</font>
									<span id="info_error"></span>
								</td>
							</tr>
							<tr>
								<td width="100">分类名称：</td>
								<td>
									<input type="text" value="${category.categoryName}" id="category_name" class="inbox" name="category_name"/>
									<input type="button" value="是否可用" onclick="checkName()"/>
									<font color="red">*</font>
									<span id="info2_error"></span>
								</td>
							</tr>
							<tr>
								<td width="100">分类排序：</td>
								<td>
									<input type="text" class="inbox" value="${category.sort}" name="sort" id="sort" />
									<font color="red">*</font>
								</td>
							</tr>
							<tr>
								<td width="100">是否显示：</td>
								<td>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
	                                    <td>
	                                        <select name="is_show" id="is_show">
	                                            <option value="">请选择</option>
												<option value="1">显示</option>
	                                            <option value="0">不显示</option>
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
								    <input type="hidden" name="pageNumber" value="${pageNumber}"/>
									<input type="button" onclick="sub()" value="保存" />
									<input type="reset" value="重置" />
									<input type="button" value="返回上一级"  onclick="goback(${pageNumber});"/>
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
			window.showModalDialog ("./categorys.action", "category","dialogWidth:" + 1500 + "px; dialogHeight:" + 700 + 
				"px; status:no;directories:yes;scrollbars:no;Resizable=false;");
		}
		
		function recovery()
		{
			$("#parent_id").val("homeStoreRootCategory");
		}
		
		function checkName()
		{
			var category_name = $("#category_name").val();
			if(!category_name)
			{
				alert("请填写分类名称");
				return;
			}
			
			if("${category.categoryName}")
			{
				if(category_name != "${category.categoryName}")
				{
					$.post("./check.action", {cname:category_name}, function(result){
						if($.trim(result) == "fail")
						{
							var obj = document.getElementById("info2_error");
							obj.innerHTML = "该分类名称已存在";
							obj.style.color = "red";
						}
						else
						{
							var obj = document.getElementById("info2_error");
							obj.innerHTML = "该分类名称可以使用";
							obj.style.color = "green";
						}
					});
				}
				else
				{
					var obj = document.getElementById("info2_error");
					obj.innerHTML = "该分类名称可以使用";
					obj.style.color = "green";
				}
			}
			else
			{
				$.post("./check.action", {cname:category_name}, function(result){
					if($.trim(result) == "fail")
					{
						var obj = document.getElementById("info2_error");
						obj.innerHTML = "该分类名称已存在";
						obj.style.color = "red";
					}
					else
					{
						var obj = document.getElementById("info2_error");
						obj.innerHTML = "该分类名称可以使用";
						obj.style.color = "green";
					}
				});
			}
		}
		
		function checkId()
		{
			var product_id = $.trim($("#id").val());
			if(!product_id)
			{
				alert("请填写分类ID");
				return;
			}
			var reg = /^[a-zA-Z]*\d+$/;
			if(!product_id.match(reg))
			{
				alert("分类ID不合法");
				return;
			}
			else
			{
				if("${category.id}")
				{
					if(product_id != "${category.id}")
					{
						$.post("./check.action", {id:product_id}, function(result){
							if($.trim(result) == "fail")
							{
								var obj = document.getElementById("info_error");
								obj.innerHTML = "该分类ID已存在";
								obj.style.color = "red";
							}
							else
							{
								var obj = document.getElementById("info_error");
								obj.innerHTML = "该分类ID可以使用";
								obj.style.color = "green";
							}
						});
					}
					else
					{
						var obj = document.getElementById("info_error");
						obj.innerHTML = "该分类ID可以使用";
						obj.style.color = "green";
					}
				}
				else
				{
					$.post("./check.action", {id:product_id}, function(result){
						if($.trim(result) == "fail")
						{
							var obj = document.getElementById("info_error");
							obj.innerHTML = "该分类ID已存在";
							obj.style.color = "red";
						}
						else
						{
							var obj = document.getElementById("info_error");
							obj.innerHTML = "该分类ID可以使用";
							obj.style.color = "green";
						}
					});
				}
			}
		}
		
		function sub()
		{
			var id = $.trim($("#id").val());
			var idFlag = false;
			var nameFlag = false;
			if(!id)
			{
				alert("请填写分类ID");
				return;
			}
			else{
				if(id == "${category.id}")
				{
					idFlag = true;
				}
				else
				{
					$.ajax({
				        type:"POST",
				        url:"./check.action",
				        data:{id:id},
				        cache:false,
				        async:false,
				        success:function (result){
				            if($.trim(result) == "fail")
		                    {
		                        var obj = document.getElementById("info_error");
		                        obj.innerHTML = "该分类ID已存在";
		                        obj.style.color = "red";
		                    }
		                    else{
		                        idFlag = true;
		                    }
				        }
			    	});
				}
			}
			if(!idFlag){
			    return false;
			}
			var category_name = $.trim($("#category_name").val());
			if(!category_name)
			{
				alert("请填写分类名称");
				return;
			}
			else
			{
				if(category_name == "${category.categoryName}")
				{
					nameFlag = true;
				}
				else
				{
					$.ajax({
				        type:"POST",
				        url:"./check.action",
				        data:{cname:category_name},
				        cache:false,
				        async:false,
				        success:function (result){
				            if($.trim(result) == "fail")
		                    {
		                        var obj = document.getElementById("info2_error");
								obj.innerHTML = "该分类名称已存在";
								obj.style.color = "red";
		                    }
		                    else{
		                        nameFlag = true;
		                    }
				        }
			    	});
		    	}
			}
			if(!nameFlag){
			    return false;
			}
			var sort = $.trim($("#sort").val());
			if(sort)
			{
				if(isNaN(sort))
				{
					alert("分类排序为数字类型");
					return;
				}
			}
			else
			{
				alert("请填写分类排序");
				return;
			}
			var is_show = $.trim($("#is_show").val());
			if(!is_show)
			{
				alert("请选择是否显示");
				return;
			}
			
			document.forms["categoryForm"].submit();
		}
	</script>
</body>
</html>