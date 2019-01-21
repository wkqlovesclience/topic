<%@ page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<title>搜索词</title>
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
			
			// 分页方法
			function tunePage(num)
			{
				var url = "./list.action?pageNumber=" + num;
				var creator = $("#qcreator").val();
				if(creator != null && creator != "" )
				{
					url += "&qcreator=" + encodeURIComponent(encodeURIComponent(creator));
				}
			
				var qtitle = $("#qtitle").val();
				if(qtitle != null && qtitle != "" )
				{
					url += "&qtitle=" + encodeURIComponent(encodeURIComponent(qtitle));
				}
				var createTime = $("#qcreateTime").val();
				if(createTime != null && createTime != "" )
				{
					url += "&qcreateTime=" + createTime;
				}
				var qfirstTagId = $("#qfirstTagId").val();
				if(qfirstTagId != null && qfirstTagId != "" )
				{
					url += "&qfirstTagId=" + qfirstTagId;
				}
				var qsecondTagId = $("#qsecondTagId").val();
				if(qsecondTagId != null && qsecondTagId != "" )
				{
					url += "&qsecondTagId=" + qsecondTagId;
				}
				var qchecked = $("#qchecked").val();
                if(qchecked != null && qchecked != "" )
                {
                    url += "&qchecked=" + qchecked;
                }
				window.location = url;
				return;
			}
			
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
			
			//修改热词
			function update()
			{
				var id = $("#editKeywordId").val();
				var title = $("#editTitle").val();
				$.post("./update.action", {id:id, title:title}, function(result){
					window.location = location;
				});
			}
			
			//以id为范围导出excel热词文件。
			function outFile()
			{
				var minId = $.trim($("#minId").val());
				var maxId = $.trim($("#maxId").val());
				if(!minId || !maxId)
				{
					alert("请输入正确的数字！");
					return;
				}
				if(isNaN(minId) || isNaN(maxId))
				{
					alert("对不起您的输入不合法，请输入正确数字！");
					return;
				}
				window.location = "./out.action?minId="+minId+"&maxId="+maxId;
			}
			
			//发布热词
			function publish(id, url)
			{
				$.post(url, {id:id}, function(result){
				    if(result == 'success'){
				        alert("发布成功");
                        window.location = location;
				    }
				    else if(result == 'noTag'){
				        alert("一级标签或者二级标签存在空值，不能发布");
				    }
				    else if(result == 'noHotkeyword'){
				        alert("编号不存在");
				    }
				    else if(result == 'emptyParam'){
				        alert("参数为空");
				    }
				    else {
				        alert("异常错误");
				    }
				});
			}
			//批量发布热词
			function publishBatch(){
			    var cc = $(':checkbox[id=each][checked=true]');
		        var str = "";
		        for ( var j = 0; j < cc.length; j++) {
		            str = str + cc.get(j).value + ";";
		        }
		        if (str == "") {
		            alert('请至少选择一项！');
		            return;
		        }
		        if(confirm('确认发布？'))
		            {
		             $.post('${ctx}/HotKeyword/publishBatch.action?ids='+ str,
		                    function(msg){          
		                        if(msg =='success'){
		                            alert("全部发布成功！");
		                            window.location.reload();
		                        }
		                        else if(msg.indexOf("error:")!=-1){
		                            var needMsg = msg.substring(msg.indexOf(":")+1);
		                            alert("编号："+needMsg+"发布失败！");
                                    window.location.reload();
		                        }
		                    }
		            );
		        } 
			}
			
			
			//批量发布热词
			function publishWapBatch(){
			    var cc = $(':checkbox[id=each][checked=true]');
		        var str = "";
		        for ( var j = 0; j < cc.length; j++) {
		            str = str + cc.get(j).value + ";";
		        }
		        if (str == "") {
		            alert('请至少选择一项！');
		            return;
		        }
		        if(confirm('确认发布？'))
		            {
		             $.post('${ctx}/HotKeyword/publishWapBatch.action?ids='+ str,
		                    function(msg){          
		                        if(msg =='success'){
		                            alert("全部发布成功！");
		                            window.location.reload();
		                        }
		                        else if(msg.indexOf("error:")!=-1){
		                            var needMsg = msg.substring(msg.indexOf(":")+1);
		                            alert("编号："+needMsg+"发布失败！");
                                    window.location.reload();
		                        }
		                    }
		            );
		        } 
			}
			
			
			
			//全部发布热词
			function publishAllBatch(){
		        if(confirm('确定执行全部发布吗？如果是，时间比较长，请耐心等待'))
		            {
		             $.post('${ctx}/HotKeyword/publishAllBatch.action',
		                    function(msg){          
		                        if(msg =='success'){
		                            alert("全部发布成功！");
		                            window.location.reload();
		                        }
		                        else if(msg.indexOf("error:")!=-1){
		                            var needMsg = msg.substring(msg.indexOf(":")+1);
		                            alert("编号："+needMsg+"发布失败！");
                                    window.location.reload();
		                        }
		                    }
		            );
		        } 
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
				<div style="margin-bottom: 5px;" class="container-1">
					搜索词ID： <input type="text" class="txt-5" id="qid" name="qid" value="${param.qid}"/>
					搜索词名称： <input type="text" class="txt-5" id="qtitle" name="qtitle" value="${qtitle}"/> 
					创建者：      <input type="text" class="txt-5" id="qcreator" name="qcreator" value="${qcreator}"/>
					&nbsp;&nbsp;&nbsp;&nbsp;	
					<input type="button" onclick="clearAll()" value="重置"/>
					<input type="submit" value="搜索"/><p>&nbsp;</p>
					创建时间：
					<input readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"
						   name="qcreateTime" id="qcreateTime" class="txt-5" value="${param.qcreateTime}"  />
					<img onclick="yxbegin()" src="http://app.gomein.net.cn/topics/images/images_3.gif" />
					&nbsp;标签：
					<select name="qfirstTagId" id="qfirstTagId" onchange="getSubTags(value, 'qsecondTagId', null)">
						<option value="">请选择</option>
						<c:forEach items="${tags}" var="tag">
							<option value="${tag.id}">${tag.tagName}</option>
						</c:forEach>
					</select>
					<select name="qsecondTagId" id="qsecondTagId">
						<option value="">请选择</option>
					</select>
					&nbsp;&nbsp;
					状态：
					<s:select list="#{'':'请选择','0':'未发布','1':'已发布'}" name="qchecked" id="qchecked" theme="simple"></s:select>	 
				</div>
				<div class="container-1" style="overflow: auto;">
					<table>
						<tbody>
							<tr>
								<td style="height: 5px; padding: 10px 0; vertical-align: middle;">
									<input type="button" value="删除" onclick="batchDeleteData()" />
									<input type="button" value="发布" onclick="publishBatch()"/>
									<input type="button" value="手机端发布" onclick="publishWapBatch()"/>
									<input type="button" value="全部发布" onclick="publishAllBatch()"/>
									<span id="importSpan" style="margin-left: 100ex">
										导出excel文件：ID从<input type="text" size="10" id="minId"/>到 <input type="text" size="10" id="maxId"/>
										<input type="button" value="导出热词文件" onclick="outFile()" />
									</span>
								</td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
							</tr>
						</tbody>
					</table>
					<table class="tb-zebra tmp-class" style="width: 100%;">
						<colgroup>
							<col style="width: 5%;" />
							<col style="width: 5%;" />
							<col style="width: 5%;" />
							<col style="width: 10%;" />
							<col style="width: 15%;" />
							<col style="width: 22%;" />
							<col style="width: 10%;" />
							<col style="width: 8%;" />
							<col style="width: 5%;" />
							<col style="width: 15%;" />
						</colgroup>
						<thead>
							<tr>
								<td style="line-height: 1;"><input type="checkbox" onclick="allChecked(this)" /></td>
								<td style="line-height: 1;">序号</td>
								<td style="line-height: 1;">产品ID</td>
								<td style="line-height: 1;">搜索词标题</td>
								<td style="line-height: 1;">一级标签  / 二级标签</td>
								<td style="line-height: 1;">添加/修改时间</td>
								<td style="line-height: 1;">创建者/修改者</td>
								<td style="line-height: 1;">发布</td>
								<td style="line-height: 1;">站点</td>
								<td style="line-height: 1;">操作</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listHotword}" var="hotWord">
							<tr>
								<td>
									<label> <input type="checkbox" id="each" value="${hotWord.id}" /> </label>
								</td>
								<td>${hotWord.id}</td>
								<td>${hotWord.productId}</td>
								<td>
								    <c:if test="${hotWord.checked == 1}">
								    <a href="http://www.gome.com.cn/keywords/${hotWord.id}/" target="_blank" >${hotWord.title}</a>
								    </c:if>
								    <c:if test="${hotWord.checked == 0}">
								    ${hotWord.title}
								    </c:if>
								</td>
								<td>
									<c:if test="${hotWord.firstTagName != null}">${hotWord.firstTagName}</c:if>
									/
									<c:if test="${hotWord.secondTagName != null}">${hotWord.secondTagName}</c:if>
								</td>
								<td>${hotWord.createTime} / ${hotWord.updateTime}</td>
								<td>${hotWord.creator} / ${hotWord.modifier}</td>
								<td>
									<c:if test="${hotWord.checked == 0}">未发布</c:if>
									<c:if test="${hotWord.checked == 1}">已发布</c:if> 
								</td>
								<td>
									<c:choose>
										<c:when test="${hotWord.site == 'gome'}">国美</c:when>
										<c:otherwise>库巴</c:otherwise>
									</c:choose>
								</td>
								<td>
									<a href="http://www.gome.com.cn/keywords/test/${hotWord.id}/" target="_blank">预览</a>
									<a href="javascript:void(0)" onclick="publish('${hotWord.id}', './publish.action')">发布</a>
									<a href="./edit.action?id=${hotWord.id}&pageNumber=${pageNumber}" >编辑</a>
									<a href="javascript:void(0)" onclick="deleteSingleData('${hotWord.id}', './delete.action')">删除</a>
									<a href="${ctx}/HotLink/create.action?hotLink.moduleId=${hotWord.id}&&hotLink.moduleType=1&pageNumber=${pageNumber}">添加热门链接</a>
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
										<coo8:page name="listHotword" style="js" systemId="1" />
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