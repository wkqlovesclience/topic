<%@ page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>��Ʒһ������</title>
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
				alert("�������ѯ����");
				basicInfoSpanDom.html("");
				return false;
			} else {
				var searchType = 0;
				if (/^\d+$/.test(searchIdValue)) {
					searchType = 1;
				} else if (/^cat\d+$/.test(searchIdValue)) {
					searchType = 2;
				} else {
					alert("���������ʽ����");
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

	// ��ҳ����
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
					alert("�÷�������ط��࣬������ɾ��");
				} else {
					if (window.confirm("ȷ��Ҫɾ����")) {
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
			alert("��ѡ��Ҫɾ���ķ���ID");
			return;
		}
		if (window.confirm("ȷ��Ҫɾ����")) {
			$.post('./delete.action', {
				ids : (ids.join(","))
			}, function(result) {
				if ($.trim(result) == "success") {
					window.location = location;
				} else if ($.trim(result) == "nodate") {
					alert("�Բ���û��Ҫɾ��������");
				} else {
					alert("ɾ��ʧ�ܣ�" + ($.trim(result)) + "������ɾ��");
				}
			});
		}
	}
	function publish(paramId, url) {
		$.post(url, {
			"qid" : paramId
		}, function(data) {
			if (data == 'Y') {
				alert("�����ɹ�");
				window.location.reload();
			} else {
				alert("����ʧ��");
			}
		});
	}

	//ȫ������
	function publishAllBatch(publishType) {
		if (window.confirm("ȷ��ִ��ȫ������������ǣ�ʱ��Ƚϳ��������ĵȴ�")) {
			$.post('./publishAllBatch.action', {
				publishType : publishType
			}, function(result) {
				if ($.trim(result) == "success") {
					alert("ȫ�������ɹ�");
					//window.location = location;
					$("#f1").submit();
				} else {
					alert("�쳣�����˳����µ�¼������ϵ������Ա");
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
			alert("��ѡ��Ҫ��ʾ�ķ���ID");
			return;
		}
		if (window.confirm("ȷ��Ҫ��ʾ��")) {
			$.post('./changeFirstCatShowState.action', {
				ids : (ids.join(",")),
				type : paramType
			}, function(result) {
				if ($.trim(result) == "success") {
					window.location = location;
				} else if ($.trim(result) == "nodata") {
					alert("�Բ���û��Ҫ��ʾ������");
				} else {
					alert("�쳣���������µ�¼");
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
				<h3>��Ʒһ���������</h3>
			</div>
			<div class="bd clearfix">
				<div style="margin-bottom: 5px;" class="container-1">
					ID�� <input type="text" class="txt-5" id="qcid" name="qcid"
						value="${param.qcid}" /> ����ID�� <input type="text" class="txt-5"
						id="qid" name="qid" value="${param.qid}" /> ���ƣ� <input type="text"
						class="txt-5" style="width: 150px;" id="qname" name="qname"
						value="${param.qname}" /> �����ߣ� <input type="text" class="txt-5"
						id="qcreator" name="qcreator" value="${param.qcreator}" />
					<!-- <p>&nbsp;</p>  -->
					����ʱ�䣺 <input readonly="readonly"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"
						name="qcreate_time" id="qcreate_time" class="txt-5"
						value="${param.qcreate_time}" /> &nbsp;&nbsp; ״̬�� <select
						name="qis_show" id="qis_show">
						<option value="">��ѡ��</option>
						<option value="1">��ʾ</option>
						<option value="0">����ʾ</option>
					</select> &nbsp;&nbsp;&nbsp;&nbsp; <input type="button" onclick="clearAll()"
						value="����" /> <input type="submit" value="����" />
				</div>
				<div
					style="clear: both; background: #FFF; border: 1px solid #66727B; padding: 5px;">
					<span> <input type="button" onclick="deleteMul()"
						value="����ɾ��" />&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
						onclick="batchShow('1');" value="������ʾ" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" onclick="edit();" value="���" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" onclick="publishAllBatch(0);" value="ȫ������" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" onclick="publishAllBatch(1);" value="ȫ���ֻ��淢��" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="text" id="searchId" value="" />&nbsp;&nbsp;<input
						type="button" alt="����ID�����߷���ID����ȡ����Ļ�����Ϣ" value="��ȡ������Ϣ"					
						id="searchForBasicInfo" />&nbsp;&nbsp;&nbsp;&nbsp;	
						<input type="button" onclick="updateBrand();" value="�������з�����Ʒ����Ϣ" />
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
								<td style="line-height: 1;">����ID</td>
								<td style="line-height: 1;">һ����������</td>
								<td style="line-height: 1;">����λ��</td>
								<td style="line-height: 1;">�Ƿ���ʾ</td>
								<td style="line-height: 1;">������/�޸���</td>
								<td style="line-height: 1;">���/�޸�ʱ��</td>
								<td style="line-height: 1;">����</td>
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
									<c:if test="${category.isShow == 0}">����ʾ</c:if>
									<c:if test="${category.isShow == 1}">��ʾ</c:if> 
								</td>
								<td>${category.creator} / ${category.updator}</td>
								<td>${category.createTime} / ${category.updateTime}</td>
								<td>
									<a href="./children.action?qparent_id=${category.id}">�鿴</a>
									<a href="./edit.action?id=${category.id}&pageNumber=${pageNumber}" >�༭</a>
									<a href="javascript:void(0)" onclick="deleteCategory('${category.id}', './delete.action')">ɾ��</a>
								    <a href="${ctx}/HotLink/create.action?hotLink.moduleId=${category.cid}&hotLink.moduleType=3&pageNumber=${pageNumber}">�����������</a>
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