<%@ page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品黑名单列表</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css }" />
<style>
html {
	*overflow-y: scroll;
	*overflow-x: hidden;
}
</style>
</head>
<body>
	<form action="./list.action" method="post" id="f1">
		<div class="mod-1">
			<div class="hd">
				<h3>商品一级分类管理</h3>
			</div>
			<div class="bd clearfix">
				<div style="margin-bottom: 5px;" class="container-1">
					产品ID： <input type="text" class="txt-5" id="proId" name="proId"
						value="${param.qcid}" /> <input type="submit" value="搜索" />
				</div>
				<div
					style="clear: both; background: #FFF; border: 1px solid #66727B; padding: 5px;">
					<span> <input type="button" onclick="edit();" value="添加" />&nbsp;&nbsp;&nbsp;&nbsp;
					</span>
				</div>
				<div class="container-1" style="overflow: auto;">
					<table class="tb-zebra tmp-class" style="width: 100%;">
						<colgroup>
							<col style="width: 15%;" />
							<col style="width: 15%;" />
							<col style="width: 15%;" />
							<col style="width: 15%;" />
							<col style="width: 15%;" />
							<col style="width: 15%;" />
							<col style="width: 15%;" />
						</colgroup>
						<thead>
							<tr>
								<td style="line-height: 1;">ID</td>
								<td style="line-height: 1;">产品ID</td>
								<td style="line-height: 1;">创建人</td>
								<td style="line-height: 1;">创建时间</td>
								<td style="line-height: 1;">修改人</td>
								<td style="line-height: 1;">修改时间</td>
								<td style="line-height: 1;">操作</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${productList }" var="product">
								<tr>
									<td>${product.id }</td>
									<td>${product.proId }</td>
									<td>${product.createUser }</td>
									<td><fmt:formatDate value="${product.createDate }"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>${product.updateUser }</td>
									<td><fmt:formatDate value="${product.updateDate }"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td><a href="javascript:void(0)"
										onclick="deleteBlack('${product.proId}', './delete.action')">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div> 
			</div>
		</div>
	</form>

</body>
<script type="text/javascript">
	function edit() {
		window.location = './addBlack.action';
	}
	function deleteBlack(proId, url) {
		if (window.confirm("确认要删除吗？")) {
			$.post(url, {
				proId : proId
			}, function(result) {
				window.location = location;
			});
		}
	}
</script>








</html>