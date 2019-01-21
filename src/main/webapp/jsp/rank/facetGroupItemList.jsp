<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<title>属性值管理</title>
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
			.txt-5{
                width: 80px;
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
			});
			
			// 分页方法
			function tunePage(num)
			{
				var url = "./list.action?pageNumber=" + num;
				var qid = $("#qid").val();
                if(qid != null && qid != "" )
                {
                    url += "&qid=" + qid;
                }
				var qcatid = $("#qcatid").val();
				if(qcatid != null && qcatid != "" )
				{
					url += "&qcatid=" + qcatid;
				}
				var qvalue = $("#qvalue").val();
				if(qvalue != null && qvalue != "" )
				{
					url += "&qvalue=" + qvalue;
				}
				var qparent_id = $("#qparent_id").val();
				if(qparent_id != null && qparent_id != "" )
                {
                    url += "&qparent_id=" + qparent_id;
                }
				var qis_show = $("#qis_show").val();
				if(qis_show != null)
				{
					url += "&qis_show=" + qis_show;
				}
				/*
				var qcreate_time = $("#qcreate_time").val();
				if(qcreate_time != null && qcreate_time != "" )
				{
					url += "&qcreate_time=" + qcreate_time;
				}
				*/
				if("${param.level}")
				{
					url += "&level=" + level;
				}
				window.location = url;
				return;
			}
			
			function edit()
			{
				window.location = "./edit.action?qcatid=${param.qcatid}&qparent_id=${param.qparent_id}";
			}
			
			function deleteObj(id, url)
			{
				if(window.confirm("确认要删除吗？"))
				{
					$.post(url, {ids:id}, function(result){
						//window.location = location;
						$("#f1").submit();
					});
				}
			}
			
			function deleteMul()
			{
				var ids = new Array();
				var boxs = $(":checkbox[id=each]:checked");
				boxs.each(function() {
					ids.push(this.value);
				});
				$.post('./delete.action', {ids:(ids.join(","))}, function(result){
					//window.location = location;
					$("#f1").submit();
				});
			}
			
			function batchShow(paramType)
            {
                var ids = new Array();
                var boxs = $(":checkbox[id=each]:checked");
                boxs.each(function() {
                    ids.push(this.value);
                });
                $.post('./changeShowState.action', {ids:(ids.join(",")),type:paramType}, function(result){
                    //window.location = location;
                    $("#f1").submit();
                });
            }
		</script>
	</head>
	<body>
		<form action="./list.action" method="post" id="f1">
		<div class="mod-1">
			<div class="hd">
				<h3>属性值管理</h3>
			</div>
			<div class="bd clearfix">
				<div style="margin-bottom: 5px;" class="container-1">
				    编号： <input type="text" class="txt-5" id="qid" name="qid" value="${param.qid}"/>
					分类ID： <input type="text" class="txt-5" id="qcatid" name="qcatid" value="${param.qcatid}"/>
					属性值名称： <input type="text" class="txt-5" style="width: 150px;" id="qvalue" name="qvalue" value="${param.qvalue}"/>
					父级ID： <input type="text" class="txt-5" id="qparent_id" name="qparent_id" value="${param.qparent_id}"/>
					<!-- 
					创建时间：
					<input readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" 
					name="qcreate_time" id="qcreate_time" class="txt-5" value="${param.qcreate_time}"  />
					 -->
					&nbsp;&nbsp;
					状态：
					<select name="qis_show" id="qis_show">
						<option value="">请选择</option>
						<option value="1">显示</option>
						<option value="0">不显示</option>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="hidden" value="${pageNumber}" name="pageNumber" />
					<input type="button" onclick="clearAll()" value="重置"/>
					<input type="submit" value="搜索"/>
				</div>
				<div style="clear: both;background: #FFF;border: 1px solid #66727B;padding: 5px;">
				    <span>
                        <input type="button" onclick="deleteMul()" value="批量删除"/>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="button" onclick="batchShow('1')" value="批量显示"/>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="button" onclick="edit();" value="添加"/>
                    </span>
				</div>
				<div class="container-1" style="overflow: auto;">
					<table class="tb-zebra tmp-class" style="width: 100%;">
						<colgroup>
							<col style="width: 5%;" />
							<col style="width: 5%;" />
							<col style="width: 10%;" />
							<col style="width: 10%;" />
							<col style="width: 7%;" />
							<col style="width: 8%;" />
							<col style="width: 7%;" />
							<col style="width: 8%;" />
							<col style="width: 10%;" />
							<col style="width: 22%;" />
							<col style="width: 8%;" />
						</colgroup>
						<thead>
							<tr>
								<td style="line-height: 1;"><input type="checkbox" onclick="allChecked(this)" /></td>
								<td style="line-height: 1;">编号</td>
								<td style="line-height: 1;">分类ID</td>
								<td style="line-height: 1;">属性值名称</td>
								<td style="line-height: 1;">排序值</td>
								<td style="line-height: 1;">父级Id</td>
								<td style="line-height: 1;">facetId</td>
								<td style="line-height: 1;">是否显示</td>
								<td style="line-height: 1;">创建者/修改者</td>
								<td style="line-height: 1;">添加/修改时间</td>
								<td style="line-height: 1;">操作</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${facetGroupItemList}" var="facetGroupItem">
							<tr>
								<td>
									<label> <input type="checkbox" id="each" value="${facetGroupItem.id}" /> </label>
								</td>
								<td>${facetGroupItem.id}</td>
								<td>${facetGroupItem.catId}</td>
								<td>${facetGroupItem.value}</td>
								<td>${facetGroupItem.index}</td>
								<td>${facetGroupItem.parentId}</td>
								<td>${facetGroupItem.facetId}</td>
								<td>
									<c:if test="${facetGroupItem.isshow == 0}">不显示</c:if>
									<c:if test="${facetGroupItem.isshow == 1}">显示</c:if> 
								</td>
								<td>${facetGroupItem.creator} / ${facetGroupItem.updator}</td>
								<td>${facetGroupItem.insertDate} / ${facetGroupItem.updateDate}</td>
								<td>
									<a href="./edit.action?pageNumber=${pageNumber}&id=${facetGroupItem.id}&qcatid=${param.qcatid}&qparent_id=${param.qparent_id}" >编辑</a>
									<a href="javascript:void(0)" onclick="deleteObj('${facetGroupItem.id}', './delete.action')">删除</a>
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
										<coo8:page name="facetGroupItemList" style="js" systemId="1" />
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