<%@ page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<title>��Ʒ��������</title>
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
			
			// ��ҳ����
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
							alert("�÷�������ط��࣬������ɾ��");
						}
						else
						{
							if(window.confirm("ȷ��Ҫɾ����"))
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
					alert("��ѡ��Ҫɾ���ķ���ID");
					return;
				}
				if(window.confirm("ȷ��Ҫɾ����"))
				{
					$.post('./delete.action', {ids:(ids.join(","))}, function(result){
						if($.trim(result) == "success")
						{
							//window.location = location;
							$("#f1").submit();
						}
						else if($.trim(result) == "nodate")
						{
							alert("�Բ���û��Ҫɾ��������");
						}
						else
						{
							alert("ɾ��ʧ�ܣ�" + ($.trim(result)) + "������ɾ��");
						}
					});
				}
			}
			function publish(paramId,url,publishType){
			    $.post(url,{"qid":paramId,"publishType":publishType},function (data){
			    	
			        if(data == 'Y'){
			            alert("�����ɹ�");
			            //window.location.reload();
			            $("#f1").submit();
			        }
			        else{
			            alert("����ʧ��");
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
					alert("��ѡ��Ҫ�����ķ���ID");
					return;
				}
				if(window.confirm("ȷ��Ҫ������"))
				{
					$.post('./publishBatch.action', {ids:(ids.join(","))}, function(result){
						if($.trim(result) == "success")
						{
							alert("���������ɹ�");
							//window.location = location;
							$("#f1").submit();
						}
						else
						{
							alert("����ʧ��");
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
                    alert("��ѡ��Ҫ����Ԥ���ķ���ID");
                    return;
                }
                if(window.confirm("ȷ��Ҫ����Ԥ����"))
                {
                    $.post('./publishPreviewBatch.action', {ids:(ids.join(","))}, function(result){
                        if($.trim(result) == "success")
                        {
                            alert("��������Ԥ���ɹ�");
                            //window.location = location;
                            $("#f1").submit();
                        }
                        else
                        {
                            alert("����Ԥ��ʧ��");
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
                    alert("��ѡ��Ҫ��ʾ�ķ���ID");
                    return;
                }
                if(window.confirm("ȷ��Ҫ��ʾ��"))
                {
                    $.post('./changeFirstCatShowState.action', {ids:(ids.join(",")),type:paramType}, function(result){
                        if($.trim(result) == "success")
                        {
                             $("#f1").submit();
                        }
                        else if($.trim(result) == "nodata")
                        {
                            alert("�Բ���û��Ҫ��ʾ������");
                        }
                        else
                        {
                            alert("�쳣���������µ�¼");
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
				<h3>��Ʒ�����������</h3>
			</div>
			<div class="bd clearfix">
				<div style="margin-bottom: 5px;" class="container-1">
					ID�� <input type="text" class="input-txt-5" id="qcid" name="qcid" value="${param.qcid}"/>
					����ID�� <input type="text" class="input-txt-5" id="qid" name="qid" value="${param.qid}"/>
					���ƣ� <input type="text" class="txt-5" id="qname" name="qname" value="${param.qname}"/> 
					�����ߣ� <input type="text" class="input-txt-5" id="qcreator" name="qcreator" value="${param.qcreator}"/>
					<!-- <p>&nbsp;</p>  -->
					����ʱ�䣺
					<input readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"
						   name="qcreate_time" id="qcreate_time" class="input-txt-5" value="${param.qcreate_time}"  />
					&nbsp;&nbsp;
					״̬��
					<select name="qis_show" id="qis_show">
						<option value="">��ѡ��</option>
						<option value="1">��ʾ</option>
						<option value="0">����ʾ</option>
					</select>&nbsp;&nbsp;
					�Ƿ񷢲���
					<select name="qispublish" id="qispublish">
						<option value="">��ѡ��</option>
						<option value="1">����</option>
						<option value="0">δ����</option>
					</select>	
					<input type="hidden" value="${param.qparent_id}" name="qparent_id"/>
					<input type="hidden" value="${pageNumber}" name="pageNumber"/>
					&nbsp;&nbsp;&nbsp;&nbsp;	
					<input type="button" onclick="clearAll()" value="����"/>
					<input type="submit" value="����"/>
				</div>
				<div style="clear: both;background: #FFF;border: 1px solid #66727B;padding: 5px;">
				    <span>
                        <input type="button" onclick="publishPreviewBatch()" value="��������Ԥ��"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="button" onclick="publishBatch()" value="��������"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="button" onclick="deleteMul()" value="����ɾ��"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="button" onclick="batchShow('1')" value="������ʾ"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="button" onclick="edit();" value="���"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="button" onclick="goback(${qparent_pageNumber});" value="�����ϼ�����"/>
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
								<td style="line-height: 1;">����ID</td>
								<td style="line-height: 1;">������������</td>
								<td style="line-height: 1;">��������ID</td>
								<td style="line-height: 1;">����λ��</td>
								<td style="line-height: 1;">�Ƿ���ʾ</td>
								<td style="line-height: 1;">�Ƿ񷢲�</td>
								<td style="line-height: 1;">������/�޸���</td>
								<td style="line-height: 1;">���/�޸�ʱ��</td>
								<td style="line-height: 1;">�鿴</td>
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
								<td>
								    <c:if test="${category.ispublish == 0}">${category.id}</c:if>
                                    <c:if test="${category.ispublish == 1}"><a href="http://www.gome.com.cn/top10/${category.cid}.html" target="_blank">${category.id}</a></c:if>
								</td>
								<td><a href="${ctx}/FacetGroup/list.action?qcategoryid=${category.id}">${category.categoryName}</a></td>
								<td>${category.parentId}</td>
								<td>${category.sort}</td>
								<td>
									<c:if test="${category.isShow == 0}">����ʾ</c:if>
									<c:if test="${category.isShow == 1}">��ʾ</c:if> 
								</td>
								<td>
									<c:if test="${category.ispublish == 0}"><font color="gray">δ����</></c:if>
									<c:if test="${category.ispublish == 1}"><font color="blue">�ѷ���</font></c:if>
								</td>
								<td>${category.creator} / ${category.updator}</td>
								<td>${category.createTime} / ${category.updateTime}</td>
								<td><a href="${ctx}/FacetGroup/list.action?qcategoryid=${category.id}">�鿴</a></td>
								<td>
								    <a href="http://www.gome.com.cn/top10/test/${category.cid}.html" target="_blank">Ԥ��</a>
									<a href="javascript:void(0)" onclick="publish('${category.id}', './publish.action',0)">����</a>
									<a href="javascript:void(0)" onclick="publish('${category.id}', './publish.action',1)">�ֻ��淢��</a>
									<a href="./edit.action?id=${category.id}&qparent_id=${param.qparent_id}&pageNumber=${pageNumber}" >�༭</a>
									<a href="javascript:void(0)" onclick="deleteCategory('${category.id}', './delete.action')">ɾ��</a>
							        <a href="${ctx}/HotLink/create.action?hotLink.moduleId=${category.cid}&hotLink.moduleType=3&pageNumber=${pageNumber}">�����������</a>
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