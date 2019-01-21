<%@ page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<title>热词任务管理</title>
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
		</style>
		<script type="text/javascript" src="${ctx}/js/ueditor/editor_config.js"></script>
		<script type="text/javascript"  src="${ctx}/js/jquery-1.6.js"></script>
		<script type="text/javascript"  src="${ctx}/js/ueditor/editor_all.js"></script>
		<script type="text/javascript" src="${ctx}/js/singleCalendar/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/tag/tag.js"></script>
		<script type="text/javascript">
			$().ready(function(){
				$("#importSpan").css("margin-left", ($(window).width())/1.8);
				$("#floatBox").hide();
				$("#qchecked").val('${param.qchecked}');
				$("#qfirstTagId").val('${param.qfirstTagId}');
				getSubTags('${param.qfirstTagId}', 'qsecondTagId', '${param.qsecondTagId}');
				$(window).resize(function() {
				    $("#importSpan").css("margin-left", ($(window).width())/1.8);
				});
			});
			
			//编辑热词
			function edit(id, title, productId, firstTagId, secondTagId)
			{
			    //计算出弹出框的位移
			    var docHeight = $(document).height();
			    var docWidth = $(document).width();
			    $("#floatBox").css("margin-top",(docHeight-100)/2);
			    $("#floatBox").css("margin-left",(docWidth-300)/2);
				$("#editKeywordId").val(id);
				$("#editTitle").val(title);
				$("#productId").val(productId);
				$("#firstTagId").val(firstTagId);
				getSubTags(firstTagId, "secondTagId", secondTagId);
				$("#floatBox").show();
			}
			
			function batchDeleteData(){
			    var cc = $(':checkbox[id=each][checked=true]');
                var str = "";
                for ( var j = 0; j < cc.length; j++) {
                    str = str + cc.get(j).value + ";";
                }
			    if(str == ""){
			         alert('请至少选择一项！');
			         return;
			    }
			    if(confirm('确认删除？')){
			        window.location = "./delete.action?ids="+str;
			    }
			}
			function deleteSingleData(paramId){
			    if(confirm('确认删除？')){
                    window.location = "./delete.action?ids="+paramId;
                }
			}
		</script>
	</head>
	<body>
		<form action="./list.action" method="post" id="f1">
		<div class="mod-1">
			<div class="hd">
				<h3>搜索词管理</h3>
			</div>
			<div class="bd clearfix">
				<div class="container-1" style="overflow: auto;">
					<table class="tb-zebra tmp-class" style="width: 100%;">
						<colgroup>
							<col style="width: 10%;" />
							<col style="width: 20%;" />
							<col style="width: 35%;" />
							<col style="width: 8%;" />
							<col style="width: 7%;" />
							<col style="width: 20%;" />
						</colgroup>
						<thead>
							<tr>
								<td style="line-height: 1;">序号</td>
								<td style="line-height: 1;">热词任务接口</td>
								<td style="line-height: 1;">任务说明</td>
								<td style="line-height: 1;">截止ID</td>
								<td style="line-height: 1;">更新数量</td>
								<td style="line-height: 1;">操作</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>触发器 : trigger_SeoPublishJobForUncreate</td>
								<td>1.只跑已发布数据。2.按照热词id 每天跑5000（默认）3.全量数据更新时间：每天2:00</td>
								<td>${numSat}</td>
								<td>${numEnd}</td>
								<td>
									<a href="./editTask.action?numSat=${numSat}&numEnd=${numEnd}" >编辑</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</form>
	</body>
</html>