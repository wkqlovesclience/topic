<%@ page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<title>导入商品专题日志</title>
		<link rel="stylesheet" href="${ctx}/styles/cui.css" />
		<style>
			html{*overflow-y:scroll;*overflow-x:hidden}
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
		<script>
			function checkFile() {

                var fileName = $.trim($("#words").val());
                fileName = fileName.substring(fileName.lastIndexOf("."));
                if(fileName){
                    if(fileName == ".xls" || fileName == ".xlsx"){
                        alert("数据较多，处理需要一段时间，稍后会有弹窗提示您是否成功! 小提示：如您离开本页面，弹窗将不再提示，您稍后刷新即可！");
                        var a = new FormData();
                        a.append("words", $("#words")[0].files[0]);
                        $.ajax({
                            url:"${ctx}/Titles/import.action",
                            xhrFields:{
                                withCredentials:true
                            },
                            type: "POST",
                            cache: false,
                            data: a,
                            processData: false,
                            contentType:false,
                            async: true,
                            success: function (result) {
                                var str = JSON.parse(result);
                                alert(str);
                                location.reload(true);
                            }
                        })
                    }else{
                        alert("对不起，您上传的文件不合法，文件类型为excel格式 .xls 或者 .xlsx");
                        return false;
                    }
                }else{
                    alert("请您添加要上传的文件，文件的格式为excel文件。");
                    return false;
                }


				/*var fileName = $.trim($("#words").val());
				fileName = fileName.substring(fileName.lastIndexOf("."));
				if(fileName)
				{
					if(fileName == ".xls" || fileName == ".xlsx")
					{
						document.forms["importFile"].submit();
					}
					else
					{
						alert("对不起，您上传的文件不合法，文件类型为excel格式 .xls 或者 .xlsx");
						return false;
					}
				}
				else
				{
					alert("请您添加要上传的文件，文件的格式为excel文件。");
					return false;
				}*/
			}
			function downErrorList(fileUrl,logId,uploader){
			    var personName = encodeURIComponent(encodeURIComponent(uploader));
			    window.location.href = fileUrl +"&logid=" + logId +"&uploader="+personName;
			}
			function tunePage(num){
			    var url = "./log.action?pageNumber=" + num;
			    window.location = url;
                return;
			}
		</script>
	</head>
	<body>
		<form action="./import.action" name="importFile" method="post" id="f1" enctype="multipart/form-data">
		<div class="mod-1">
			<div class="hd">
				<h3>批量上传商品专题</h3>
			</div>
			<div class="bd clearfix">
				<div style="margin-bottom: 10px;" class="container-1">
					批量添加商品专题： <input type="file" size="50" id="words" name="words" />
                    <input type="hidden" id="fileType" name="fileType" />
					<input type="button" onclick="checkFile()" value="导入商品专题"/>
				</div>
				<div class="container-1">
					<table class="tb-zebra tmp-class" style="width: 100%;">
						<colgroup>
							<col style="width: 5%;" />
							<col style="width: 10%;" />
							<col style="width: 35%;" />
							<col style="width: 10%;" />
							<col style="width: 10%;" />
							<col style="width: 20%;" />
							<col style="width: 10%;" />
						</colgroup>
						<thead>
							<tr>
								<td style="line-height: 1;">序号</td>
								<td style="line-height: 1;">上传人</td>
								<td style="line-height: 1;">上传时间</td>
								<td style="line-height: 1;">总词数</td>
								<td style="line-height: 1;">失败词数</td>
								<td style="line-height: 1;">下载出错列表</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listImportLogs}" var="log">
							<tr>
								<td>${log.id}</td>
								<td>${log.creator}</td>
								<td>${log.createTime}</td>
								<td>${log.totalCount}</td>
								<td>${log.failCount}</td>
								<td>
									<c:choose>
										<c:when test="${log.failCount != 0}">
											<a href="javascript:void(0)" onclick="downErrorList('${log.fileUrl}','${log.id}','${log.creator}')" title="下载导入失败的热词列表文件">
												下载<c:if test="${log.downStatus == 0}">[未下载]</c:if>
												<c:if test="${log.downStatus == 1}">[已下载]</c:if>
											</a>
										</c:when>
										<c:otherwise>
											下载
										</c:otherwise>
									</c:choose>
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
										<coo8:page name="listImportLogs" style="js" systemId="1" />
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