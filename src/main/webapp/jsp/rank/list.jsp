<%@ page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>商品一级分类</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<style>
html {
	*overflow-y: scroll;
	*overflow-x: hidden;
}

.fixed {
	position: absolute;
	width: 400px;
	height: 130px;
	background: #A8A8A8;
	border: 1px solid black;
	text-align: center;
	z-index: 9999;
}

a {
	color: #5075a3;
	text-decoration: none;
}

a:hover {
	color: #5075a3;
}

.txt-5 {
	width: 80px;
}
</style>
<script type="text/javascript" charset="GBK"
	src="${ctx}/js/jquery-1.6.js"></script>
<script type="text/javascript"
	src="${ctx}/js/singleCalendar/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/tag/tag.js"></script>
<script type="text/javascript">
	$().ready(function() {

		$(window).resize(function() {
			$("#importSpan").css("margin-left", ($(window).width()) / 1.8);
		});
		$("#qis_show").val("${qis_show}");
		$("#searchForBasicInfo").click(function() {
			var searchIdValue = $("#searchId").val();
			searchIdValue = $.trim(searchIdValue);
			var basicInfoSpanDom = $("#basicInfoSpan");
			if ($.trim(searchIdValue) == "") {
				alert("请输入查询内容");
				basicInfoSpanDom.html("");
				return false;
			} else {
				var searchType = 0;
				if (/^\d+$/.test(searchIdValue)) {
					searchType = 1;
				} else if (/^cat\d+$/.test(searchIdValue)) {
					searchType = 2;
				} else {
					alert("输入参数格式有误");
					return false;
				}
				$.post("./basicCategoryInfo.action", {
					searchId : searchIdValue,
					searchType : searchType
				}, function(data) {
					if (data != null && data.indexOf("success") >= 0) {
						var detailData = data.substr(data.indexOf(":") + 1);
						basicInfoSpanDom.show();
						basicInfoSpanDom.html(detailData);
					}
				});
			}
		});
	});

	// 分页方法
	function tunePage(num) {
		var url = "./list.action?pageNumber=" + num;
		var qcid = $("#qcid").val();
		if (qcid != null && qcid != "") {
			url += "&qcid=" + qcid;
		}
		var qid = $("#qid").val();
		if (qid != null && qid != "") {
			url += "&qid=" + qid;
		}
		var qname = $("#qname").val();
		if (qname != null && qname != "") {
			url += "&qname=" + qname;
		}
		var qis_show = $("#qis_show").val();
		if (qis_show != null) {
			url += "&qis_show=" + qis_show;
		}
		var qcreate_time = $("#qcreate_time").val();
		if (qcreate_time != null && qcreate_time != "") {
			url += "&qcreate_time=" + qcreate_time;
		}
		if ("${param.level}") {
			url += "&level=" + level;
		}
		window.location = url;
		return;
	}

	function edit() {
		window.location = "./edit.action";
	}

	function updateBrand() {
		window.location = "./updateBrand.action";
	}

	function deleteCategory(id, url) {
		if (id) {
			$.post("./check.action", {
				parent_id : id
			}, function(result) {
				if ($.trim(result) == "fail") {
					alert("该分类有相关分类，不可以删除");
				} else {
					if (window.confirm("确认要删除吗？")) {
						$.post(url, {
							ids : id
						}, function(result) {
							window.location = location;
						});
					}
				}
			});
		}
	}

	function deleteMul() {
		var ids = new Array();
		var nonids = new Array();
		var boxs = $(":checkbox[id=each]:checked");
		boxs.each(function() {
			ids.push(this.value);
		});
		if (ids.length <= 0) {
			alert("请选择要删除的分类ID");
			return;
		}
		if (window.confirm("确认要删除吗？")) {
			$.post('./delete.action', {
				ids : (ids.join(","))
			}, function(result) {
				if ($.trim(result) == "success") {
					window.location = location;
				} else if ($.trim(result) == "nodate") {
					alert("对不起，没有要删除的数据");
				} else {
					alert("删除失败，" + ($.trim(result)) + "不可以删除");
				}
			});
		}
	}
	function publish(paramId, url) {
		$.post(url, {
			"qid" : paramId
		}, function(data) {
			if (data == 'Y') {
				alert("发布成功");
				window.location.reload();
			} else {
				alert("发布失败");
			}
		});
	}

	//全部发布
	function publishAllBatch(publishType) {
		if (window.confirm("确定执行全部发布吗？如果是，时间比较长，请耐心等待")) {
			$.post('./publishAllBatch.action', {
				publishType : publishType
			}, function(result) {
				if ($.trim(result) == "success") {
					alert("全部发布成功");
					//window.location = location;
					$("#f1").submit();
				} else {
					alert("异常错误，退出重新登录或者联系开发人员");
				}
			});
		}

	}

	function batchShow(paramType) {
		var ids = new Array();
		var nonids = new Array();
		var boxs = $(":checkbox[id=each]:checked");
		boxs.each(function() {
			ids.push(this.value);
		});
		if (ids.length <= 0) {
			alert("请选择要显示的分类ID");
			return;
		}
		if (window.confirm("确认要显示吗？")) {
			$.post('./changeFirstCatShowState.action', {
				ids : (ids.join(",")),
				type : paramType
			}, function(result) {
				if ($.trim(result) == "success") {
					window.location = location;
				} else if ($.trim(result) == "nodata") {
					alert("对不起，没有要显示的数据");
				} else {
					alert("异常错误，请重新登录");
				}
			});
		}
	}
</script>

</head>
<body>
	<form action="./list.action" method="post" id="f1">
		<div class="mod-1">
			<div class="hd">
				<h3>商品一级分类管理</h3>
			</div>
			<div class="bd clearfix">
				<div style="margin-bottom: 5px;" class="container-1">
					ID： <input type="text" class="txt-5" id="qcid" name="qcid"
						value="${param.qcid}" /> 分类ID： <input type="text" class="txt-5"
						id="qid" name="qid" value="${param.qid}" /> 名称： <input type="text"
						class="txt-5" style="width: 150px;" id="qname" name="qname"
						value="${param.qname}" /> 创建者： <input type="text" class="txt-5"
						id="qcreator" name="qcreator" value="${param.qcreator}" />
					<!-- <p>&nbsp;</p>  -->
					创建时间： <input readonly="readonly"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"
						name="qcreate_time" id="qcreate_time" class="txt-5"
						value="${param.qcreate_time}" /> &nbsp;&nbsp; 状态： <select
						name="qis_show" id="qis_show">
						<option value="">请选择</option>
						<option value="1">显示</option>
						<option value="0">不显示</option>
					</select> &nbsp;&nbsp;&nbsp;&nbsp; <input type="button" onclick="clearAll()"
						value="重置" /> <input type="submit" value="搜索" />
				</div>
				<div
					style="clear: both; background: #FFF; border: 1px solid #66727B; padding: 5px;">
					<span> <input type="button" onclick="deleteMul()"
						value="批量删除" />&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
						onclick="batchShow('1');" value="批量显示" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" onclick="edit();" value="添加" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" onclick="publishAllBatch(0);" value="全部发布" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" onclick="publishAllBatch(1);" value="全部手机版发布" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="text" id="searchId" value="" />&nbsp;&nbsp;<input
						type="button" alt="输入ID，或者分类ID，获取分类的基本信息" value="获取基本信息"					
						id="searchForBasicInfo" />&nbsp;&nbsp;&nbsp;&nbsp;	
						<input type="button" onclick="updateBrand();" value="更新所有分类下品牌信息" />
						 <span id="basicInfoSpan"
						style="display: none;"></span>
					</span>
				</div>
				<div class="container-1" style="overflow: auto;">
					<table class="tb-zebra tmp-class" style="width: 100%;">
						<colgroup>
							<col style="width: 5%;" />
							<col style="width: 5%;" />
							<col style="width: 5%;" />
							<col style="width: 15%;" />
							<col style="width: 7%;" />
							<col style="width: 7%;" />
							<col style="width: 10%;" />
							<col style="width: 25%;" />
							<col style="width: 10%;" />
						</colgroup>
						<thead>
							<tr>
								<td style="line-height: 1;"><input type="checkbox"
									onclick="allChecked(this)" /></td>
								<td style="line-height: 1;">ID</td>
								<td style="line-height: 1;">分类ID</td>
								<td style="line-height: 1;">一级分类名称</td>
								<td style="line-height: 1;">排序位置</td>
								<td style="line-height: 1;">是否显示</td>
								<td style="line-height: 1;">创建者/修改者</td>
								<td style="line-height: 1;">添加/修改时间</td>
								<td style="line-height: 1;">操作</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${categoryList}" var="category">
							<tr>
								<td>
									<label> <input type="checkbox" title="${category.parentId}" id="each" value="${category.id}" /> </label>
								</td>
								<td>${category.cid}</td>
								<td>${category.id}</td>
								<td><a href="./children.action?qparent_id=${category.id}&qparent_pageNumber=${pageNumber}">${category.categoryName}</a></td>
								<td>${category.sort}</td>
								<td>
									<c:if test="${category.isShow == 0}">不显示</c:if>
									<c:if test="${category.isShow == 1}">显示</c:if> 
								</td>
								<td>${category.creator} / ${category.updator}</td>
								<td>${category.createTime} / ${category.updateTime}</td>
								<td>
									<a href="./children.action?qparent_id=${category.id}">查看</a>
									<a href="./edit.action?id=${category.id}&pageNumber=${pageNumber}" >编辑</a>
									<a href="javascript:void(0)" onclick="deleteCategory('${category.id}', './delete.action')">删除</a>
								    <a href="${ctx}/HotLink/create.action?hotLink.moduleId=${category.cid}&hotLink.moduleType=3&pageNumber=${pageNumber}">添加热门链接</a>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					<table width="100%">
						<tfoot>
							<tr>
								<td style="border: 0 none; padding-top: 10px">
									<div class="numpage">
										<coo8:page name="categoryList" style="js" systemId="1" />
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