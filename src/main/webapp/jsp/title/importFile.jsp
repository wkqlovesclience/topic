<%@ page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<title>������Ʒר����־</title>
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
                        alert("���ݽ϶࣬������Ҫһ��ʱ�䣬�Ժ���е�����ʾ���Ƿ�ɹ�! С��ʾ�������뿪��ҳ�棬������������ʾ�����Ժ�ˢ�¼��ɣ�");
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
                        alert("�Բ������ϴ����ļ����Ϸ����ļ�����Ϊexcel��ʽ .xls ���� .xlsx");
                        return false;
                    }
                }else{
                    alert("�������Ҫ�ϴ����ļ����ļ��ĸ�ʽΪexcel�ļ���");
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
						alert("�Բ������ϴ����ļ����Ϸ����ļ�����Ϊexcel��ʽ .xls ���� .xlsx");
						return false;
					}
				}
				else
				{
					alert("�������Ҫ�ϴ����ļ����ļ��ĸ�ʽΪexcel�ļ���");
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
				<h3>�����ϴ���Ʒר��</h3>
			</div>
			<div class="bd clearfix">
				<div style="margin-bottom: 10px;" class="container-1">
					���������Ʒר�⣺ <input type="file" size="50" id="words" name="words" />
                    <input type="hidden" id="fileType" name="fileType" />
					<input type="button" onclick="checkFile()" value="������Ʒר��"/>
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
								<td style="line-height: 1;">���</td>
								<td style="line-height: 1;">�ϴ���</td>
								<td style="line-height: 1;">�ϴ�ʱ��</td>
								<td style="line-height: 1;">�ܴ���</td>
								<td style="line-height: 1;">ʧ�ܴ���</td>
								<td style="line-height: 1;">���س����б�</td>
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
											<a href="javascript:void(0)" onclick="downErrorList('${log.fileUrl}','${log.id}','${log.creator}')" title="���ص���ʧ�ܵ��ȴ��б��ļ�">
												����<c:if test="${log.downStatus == 0}">[δ����]</c:if>
												<c:if test="${log.downStatus == 1}">[������]</c:if>
											</a>
										</c:when>
										<c:otherwise>
											����
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