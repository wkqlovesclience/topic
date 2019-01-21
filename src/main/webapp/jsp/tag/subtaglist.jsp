<%@ page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<title>二级标签</title>
		<link rel="stylesheet" href="${ctx}/styles/cui.css" />
		<style>
			html{*overflow-y:scroll;*overflow-x:hidden}
			.fixed{
                position:absolute;
                width:300px;
                height:100px;
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
		</style>
		<script type="text/javascript" charset="GBK" src="${ctx}/js/ueditor/editor_config.js"></script>
		<script type="text/javascript" charset="GBK" src="${ctx}/js/jquery-1.6.js"></script>
		<script type="text/javascript" charset="GBK" src="${ctx}/js/ueditor/editor_all.js"></script>
		<script type="text/javascript" src="${ctx}/js/singleCalendar/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/tag/tag.js"></script>
		<script type="text/javascript">
			$().ready(function(){
				$("#floatBox").hide();
				$("#parent_id").val('${param.parent_id}');
			});
			
			function tunePage(num)
			{
				var url = "./list.action?act=subtag&pageNumber=" + num;
				var creator = $("#qcreator").val();
				if(creator != null && creator != "" )
				{
					url += "&qcreator=" + creator;
				}
				var tagname = $("#qtagname").val();
				if(tagname != null && tagname != "" )
				{
					url += "&qtagname=" + tagname;
				}
				var createTime = $("#qcreateTime").val();
				if(createTime != null && createTime != "" )
				{
					url += "&qcreateTime=" + createTime;
				}
				window.location = url;
				return;
			}
			
			function addTag()
			{
				var parent_id = $("#parent_id").val();
				if(!parent_id)
				{
					alert("请选择一级标签");
					return;
				}
				var tagname = $.trim($("#tagname").val());
				if(!tagname)
				{
					alert("请填写二级标签名称");
					return;
				}
				$.post("./add.action", {tagname:tagname, parent_id:parent_id, act:'subtag'}, function(result){
					window.location = location;
				});
			}
			
			//编辑标签
			function edit(id, tagName)
			{
			    //计算出弹出框的位移
              var docHeight = $(document).height();
                var docWidth = $(document).width();
                $("#floatBox").css("margin-top",(docHeight-200)/2);
                $("#floatBox").css("margin-left",(docWidth-300)/2);
                
				$("#editTagId").val(id);
				$("#editTagName").val(tagName);
				$("#floatBox").show();
			}
			
			//修改标签
			function update()
			{
				var id = $("#editTagId").val();
				var tagname = $("#editTagName").val();
				$.post("./update.action", {id:id, tagname:tagname}, function(result){
					window.location = location;
				});
			}
		</script>
	</head>
	<body>
		<!-- 悬浮框 -->
		<div class="fixed" id="floatBox">
			<div style="margin-top: 20px">
				搜索词标题：<input type="text" id="editTagName"/><br />
				<input type="hidden" id="editTagId"/>
			</div>
			<div style="margin-top: 20px">
				<input type="button" value="修改" onclick="update();"/>
				<input type="button" value="取消" onclick="$('#floatBox').hide();"/>
			</div>
		</div>
		<form action="./list.action?act=subtag" method="post" id="f1">
		<div class="mod-1">
			<div class="hd">
				<h3>标签管理</h3>
			</div>
			<div class="bd clearfix">
				<div style="margin-bottom: 10px;" class="container-1">
					二级标签名称： <input type="text" class="txt-5" id="qtagname" name="qtagname" value="${param.qtagname}"/> 
					创建者：      <input type="text" class="txt-5" id="qcreator" name="qcreator" value="${param.qcreator}"/>		 
					创建时间：
					<input readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"
						   name="qcreateTime" id="qcreateTime" class="txt-5" value="${param.qcreateTime}"  />
					<img onclick="yxbegin()" src="http://app.gomein.net.cn/topics/images/images_3.gif" /> 
					<input type="button" onclick="clearAll()" value="重置"/>
					<input type="submit" value="搜索"/>
				</div>
				<div style="margin-bottom: 10px;" class="container-1">
					二级标签添加：
					<select id="parent_id">
						<option value="">请选择</option>
						<c:forEach items="${firstTags}" var="ftag">
						<option value="${ftag.id}">${ftag.tagName}</option>
						</c:forEach>
					</select> 
					<input type="text" class="txt-5" id="tagname" name="tagname"/> 
					<input type="button" value="添加" onclick="addTag()" />
				</div>
				<div class="container-1">
					<table>
						<tbody>
							<tr>
								<td style="height: 25px; padding: 10px 0; vertical-align: middle;">
									<input type="button" value="删除" onclick="changeBlock('subtag')" />
									&nbsp;&nbsp;&nbsp;&nbsp; 
								</td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
							</tr>
						</tbody>
					</table>
					<table class="tb-zebra tmp-class" style="width: 100%;">
						<colgroup>
							<col style="width: 5%;" />
							<col style="width: 8%;" />
							<col style="width: 15%;" />
							<col style="width: 15%;" />
							<col style="width: 12%;" />
							<col style="width: 30%;" />
							<col style="width: 5%;" />
							<col style="width: 10%;" />
						</colgroup>
						<thead>
							<tr>
								<td style="line-height: 1;"><input type="checkbox" onclick="allChecked(this)" /></td>
								<td style="line-height: 1;">序号</td>
								<td style="line-height: 1;">二级标签</td>
								<td style="line-height: 1;">所属一级标签</td>
								<td style="line-height: 1;">创建者/修改者</td>
								<td style="line-height: 1;">创建时间/修改时间</td>
								<td style="line-height: 1;">站点</td>
								<td style="line-height: 1;">操作</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${subTags}" var="tag">
							<tr>
								<td>
									<label> <input type="checkbox" id="each" value="${tag.id}" /> </label>
								</td>
								<td>${tag.id}</td>
								<td><a href="../HotKeyword/list.action?qfirstTagId=${tag.parentId}&qsecondTagId=${tag.id}">${tag.tagName}</a></td>
								<td>${tag.parentName }</td>
								<td>${tag.creator} / ${tag.modifier}</td>
								<td>${tag.createTime} / ${tag.updateTime}</td>
								<td>
									<c:choose>
										<c:when test="${tag.site == 'gome'}">国美</c:when>
										<c:otherwise>库巴</c:otherwise>
									</c:choose>
								</td>
								<td>
									<a href="javascript:void(0)" onclick="edit('${tag.id}', '${tag.tagName}')">编辑</a>
										&nbsp;&nbsp;&nbsp;&nbsp; 
									<a href="javascript:void(0)" onclick="deleteData('${tag.id}', './delete.action?act=subtag')">删除</a>
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
										<coo8:page name="subTags" style="js" systemId="1" />
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