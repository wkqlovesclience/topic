<%@ page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<title>商品三级分类</title>
		<link rel="stylesheet" href="${ctx}/styles/cui.css" />
		<style>
			html{*overflow-y:scroll;*overflow-x:hidden;}
			.fixed{
			    position:absolute;
			    width:400px;
			    height:130px;
			    background:#A8A8A8;
			    border:1px solid black;
			    text-align:center;
			    z-index: 9999;
			}
			a {
			    color: #5075a3;
			    text-decoration: none;
			}
			a:hover{
			    color: #5075a3;
			}
			.input-txt-5{
                width: 80px;
                height: 22px;
				margin-right: 8px;
				padding: 0 2px;
				border: 1px solid #7F9DB9;
            }
		</style>
		<script type="text/javascript" charset="GBK" src="${ctx}/js/jquery-1.6.js"></script>
		<script type="text/javascript" src="${ctx}/js/singleCalendar/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/tag/tag.js"></script>
		<script type="text/javascript">
			$().ready(function(){
				$(window).resize(function() {
				    $("#importSpan").css("margin-left", ($(window).width())/1.8);
				});
				$("#qis_show").val("${qis_show}");
				$("#qispublish").val("${param.qispublish}");
			});
			
			// 分页方法
			function tunePage(num)
			{
				var url = "./children.action?pageNumber=" + num;
				var qcid = $("#qcid").val();
				if(qcid != null && qcid != "" )
				{
					url += "&qcid=" + qcid;
				}
				var qid = $("#qid").val();
				if(qid != null && qid != "" )
				{
					url += "&qid=" + qid;
				}
				var qname = $("#qname").val();
				if(qname != null && qname != "" )
				{
					url += "&qname=" + qname;
				}
				var qis_show = $("#qis_show").val();
				if(qis_show != null)
				{
					url += "&qis_show=" + qis_show;
				}
				var qcreate_time = $("#qcreate_time").val();
				if(qcreate_time != null && qcreate_time != "" )
				{
					url += "&qcreate_time=" + qcreate_time;
				}
				var qispublish = $("#qispublish").val();
				if(qispublish != null && qispublish != "" )
				{
					url += "&qispublish=" + qispublish;
				}
				if("${param.level}")
				{
					url += "&level=" + level;
				}
				if("${param.qparent_id}")
				{
					url += "&qparent_id=${param.qparent_id}";
				}
				window.location = url;
				return;
			}
			
			function edit()
			{
				window.location = "./edit.action?qparent_id=${param.qparent_id}";
			}
			
			function deleteCategory(id, url)
			{
				if(id)
				{
					$.post("./check.action", {parent_id:id}, function(result){
						if($.trim(result) == "fail")
						{
							alert("该分类有相关分类，不可以删除");
						}
						else
						{
							if(window.confirm("确认要删除吗？"))
							{
								$.post(url, {ids:id}, function(result){
									//window.location = location;
									$("#f1").submit();
								});
							}
						}
					});
				}
			}
			
			function deleteMul()
			{
				var ids = new Array();
				var nonids = new Array();
				var boxs = $(":checkbox[id=each]:checked");
				boxs.each(function() {
					ids.push(this.value);
				});
				if(ids.length <= 0)
				{
					alert("请选择要删除的分类ID");
					return;
				}
				if(window.confirm("确认要删除吗？"))
				{
					$.post('./delete.action', {ids:(ids.join(","))}, function(result){
						if($.trim(result) == "success")
						{
							//window.location = location;
							$("#f1").submit();
						}
						else if($.trim(result) == "nodate")
						{
							alert("对不起，没有要删除的数据");
						}
						else
						{
							alert("删除失败，" + ($.trim(result)) + "不可以删除");
						}
					});
				}
			}
			function publish(paramId,url,publishType){
			    $.post(url,{"qid":paramId,"publishType":publishType},function (data){
			    	
			        if(data == 'Y'){
			            alert("发布成功");
			            //window.location.reload();
			            $("#f1").submit();
			        }
			        else{
			            alert("发布失败");
			        }
			    });
			}
			
			
			
			function publishBatch()
			{
				var ids = new Array();
				var boxs = $(":checkbox[id=each]:checked");
				boxs.each(function() {
					ids.push(this.value);
				});
				if(ids.length <= 0)
				{
					alert("请选择要发布的分类ID");
					return;
				}
				if(window.confirm("确认要发布吗？"))
				{
					$.post('./publishBatch.action', {ids:(ids.join(","))}, function(result){
						if($.trim(result) == "success")
						{
							alert("批量发布成功");
							//window.location = location;
							$("#f1").submit();
						}
						else
						{
							alert("发布失败");
						}
					});
				}
			}
			function publishPreviewBatch()
            {
                var ids = new Array();
                var boxs = $(":checkbox[id=each]:checked");
                boxs.each(function() {
                    ids.push(this.value);
                });
                if(ids.length <= 0)
                {
                    alert("请选择要发布预览的分类ID");
                    return;
                }
                if(window.confirm("确认要发布预览吗？"))
                {
                    $.post('./publishPreviewBatch.action', {ids:(ids.join(","))}, function(result){
                        if($.trim(result) == "success")
                        {
                            alert("批量发布预览成功");
                            //window.location = location;
                            $("#f1").submit();
                        }
                        else
                        {
                            alert("发布预览失败");
                        }
                    });
                }
            }
			
			function goback(qparent_pageNumer)
			{
				window.location = "./list.action?pageNumber="+qparent_pageNumer;
			}
			
			function batchShow(paramType){
                var ids = new Array();
                var nonids = new Array();
                var boxs = $(":checkbox[id=each]:checked");
                boxs.each(function() {
                    ids.push(this.value);
                });
                if(ids.length <= 0)
                {
                    alert("请选择要显示的分类ID");
                    return;
                }
                if(window.confirm("确认要显示吗？"))
                {
                    $.post('./changeFirstCatShowState.action', {ids:(ids.join(",")),type:paramType}, function(result){
                        if($.trim(result) == "success")
                        {
                             $("#f1").submit();
                        }
                        else if($.trim(result) == "nodata")
                        {
                            alert("对不起，没有要显示的数据");
                        }
                        else
                        {
                            alert("异常错误，请重新登录");
                        }
                    });
                }
            }
		</script>
	</head>
	<body>
		<form action="./children.action" method="post" id="f1">
		<div class="mod-1">
			<div class="hd">
				<h3>商品三级分类管理</h3>
			</div>
			<div class="bd clearfix">
				<div style="margin-bottom: 5px;" class="container-1">
					ID： <input type="text" class="input-txt-5" id="qcid" name="qcid" value="${param.qcid}"/>
					分类ID： <input type="text" class="input-txt-5" id="qid" name="qid" value="${param.qid}"/>
					名称： <input type="text" class="txt-5" id="qname" name="qname" value="${param.qname}"/> 
					创建者： <input type="text" class="input-txt-5" id="qcreator" name="qcreator" value="${param.qcreator}"/>
					<!-- <p>&nbsp;</p>  -->
					创建时间：
					<input readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"
						   name="qcreate_time" id="qcreate_time" class="input-txt-5" value="${param.qcreate_time}"  />
					&nbsp;&nbsp;
					状态：
					<select name="qis_show" id="qis_show">
						<option value="">请选择</option>
						<option value="1">显示</option>
						<option value="0">不显示</option>
					</select>&nbsp;&nbsp;
					是否发布：
					<select name="qispublish" id="qispublish">
						<option value="">请选择</option>
						<option value="1">发布</option>
						<option value="0">未发布</option>
					</select>	
					<input type="hidden" value="${param.qparent_id}" name="qparent_id"/>
					<input type="hidden" value="${pageNumber}" name="pageNumber"/>
					&nbsp;&nbsp;&nbsp;&nbsp;	
					<input type="button" onclick="clearAll()" value="重置"/>
					<input type="submit" value="搜索"/>
				</div>
				<div style="clear: both;background: #FFF;border: 1px solid #66727B;padding: 5px;">
				    <span>
                        <input type="button" onclick="publishPreviewBatch()" value="批量发布预览"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="button" onclick="publishBatch()" value="批量发布"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="button" onclick="deleteMul()" value="批量删除"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="button" onclick="batchShow('1')" value="批量显示"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="button" onclick="edit();" value="添加"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="button" onclick="goback(${qparent_pageNumber});" value="返回上级类型"/>
                    </span>
				</div>
				<div class="container-1" style="overflow: auto;">
					<table class="tb-zebra tmp-class" style="width: 100%;">
						<colgroup>
							<col style="width: 5%;" />
							<col style="width: 5%;" />
							<col style="width: 5%;" />
							<col style="width: 10%;" />
							<col style="width: 7%;" />
							<col style="width: 6%;" />
							<col style="width: 6%;" />
							<col style="width: 6%;" />
							<col style="width: 10%;" />
							<col style="width: 23%;" />
							<col style="width: 5%;" />
							<col style="width: 10%;" />
						</colgroup>
						<thead>
							<tr>
								<td style="line-height: 1;"><input type="checkbox" onclick="allChecked(this)" /></td>
								<td style="line-height: 1;">ID</td>
								<td style="line-height: 1;">分类ID</td>
								<td style="line-height: 1;">三级分类名称</td>
								<td style="line-height: 1;">父级分类ID</td>
								<td style="line-height: 1;">排序位置</td>
								<td style="line-height: 1;">是否显示</td>
								<td style="line-height: 1;">是否发布</td>
								<td style="line-height: 1;">创建者/修改者</td>
								<td style="line-height: 1;">添加/修改时间</td>
								<td style="line-height: 1;">查看</td>
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
								<td>
								    <c:if test="${category.ispublish == 0}">${category.id}</c:if>
                                    <c:if test="${category.ispublish == 1}"><a href="http://www.gome.com.cn/top10/${category.cid}.html" target="_blank">${category.id}</a></c:if>
								</td>
								<td><a href="${ctx}/FacetGroup/list.action?qcategoryid=${category.id}">${category.categoryName}</a></td>
								<td>${category.parentId}</td>
								<td>${category.sort}</td>
								<td>
									<c:if test="${category.isShow == 0}">不显示</c:if>
									<c:if test="${category.isShow == 1}">显示</c:if> 
								</td>
								<td>
									<c:if test="${category.ispublish == 0}"><font color="gray">未发布</></c:if>
									<c:if test="${category.ispublish == 1}"><font color="blue">已发布</font></c:if>
								</td>
								<td>${category.creator} / ${category.updator}</td>
								<td>${category.createTime} / ${category.updateTime}</td>
								<td><a href="${ctx}/FacetGroup/list.action?qcategoryid=${category.id}">查看</a></td>
								<td>
								    <a href="http://www.gome.com.cn/top10/test/${category.cid}.html" target="_blank">预览</a>
									<a href="javascript:void(0)" onclick="publish('${category.id}', './publish.action',0)">发布</a>
									<a href="javascript:void(0)" onclick="publish('${category.id}', './publish.action',1)">手机版发布</a>
									<a href="./edit.action?id=${category.id}&qparent_id=${param.qparent_id}&pageNumber=${pageNumber}" >编辑</a>
									<a href="javascript:void(0)" onclick="deleteCategory('${category.id}', './delete.action')">删除</a>
							        <a href="${ctx}/HotLink/create.action?hotLink.moduleId=${category.cid}&hotLink.moduleType=3&pageNumber=${pageNumber}">添加热门链接</a>
								</td>
							</tr>
							</c:forEach>
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