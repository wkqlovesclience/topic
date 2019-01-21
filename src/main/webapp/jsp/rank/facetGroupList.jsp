<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<title>���Թ���</title>
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
			
			// ��ҳ����
			function tunePage(num)
			{
				var url = "./list.action?pageNumber=" + num;
				var qgroupid = $("#qgroupid").val();
				if(qgroupid != null && qgroupid != "" )
				{
					url += "&qgroupid=" + qgroupid;
				}
				var qdisplayname = $("#qdisplayname").val();
				if(qdisplayname != null && qdisplayname != "" )
				{
					url += "&qdisplayname=" + qdisplayname;
				}
				var qcategoryid = $("#qcategoryid").val();
				if(qcategoryid != null && qcategoryid != "" )
				{
					url += "&qcategoryid=" + qcategoryid;
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
				if("${param.level}")
				{
					url += "&level=" + level;
				}
				window.location = url;
				return;
			}
			
			function edit()
			{
				window.location = "./edit.action?qcategoryid=${param.qcategoryid}";
			}
			
			function deleteObj(id, url)
			{
				if(window.confirm("ȷ��Ҫɾ����"))
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
				<h3>���Թ���</h3>
			</div>
			<div class="bd clearfix">
				<div style="margin-bottom: 5px;" class="container-1">
					����ID�� <input type="text" class="txt-5" id="qgroupid" name="qgroupid" value="${param.qgroupid}"/>
					�������ƣ� <input type="text" class="txt-5" style="width: 150px;" id="qdisplayname" name="qdisplayname" value="${param.qdisplayname}"/> 
					����ID�� <input type="text" class="txt-5" id="qcategoryid" name="qcategoryid" value="${param.qcategoryid}"/>
					����ʱ�䣺
					<input readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"
						   name="qcreate_time" id="qcreate_time" class="txt-5" value="${param.qcreate_time}"  />
					&nbsp;&nbsp;
					״̬��
					<select name="qis_show" id="qis_show">
						<option value="">��ѡ��</option>
						<option value="1">��ʾ</option>
						<option value="0">����ʾ</option>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="hidden" value="${pageNumber}" name="pageNumber" />
					<input type="button" onclick="clearAll()" value="����"/>
					<input type="submit" value="����"/>
				</div>
				<div style="clear: both;background: #FFF;border: 1px solid #66727B;padding: 5px;">
				    <span>
                        <input type="button" onclick="deleteMul()" value="����ɾ��"/>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="button" onclick="batchShow('1')" value="������ʾ"/>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="button" onclick="edit();" value="���"/>
                    </span>
				</div>
				<div class="container-1" style="overflow: auto;">
					<table class="tb-zebra tmp-class" style="width: 100%;">
						<colgroup>
							<col style="width: 5%;" />
							<col style="width: 7%;" />
							<col style="width: 10%;" />
							<col style="width: 10%;" />
							<col style="width: 6%;" />
							<col style="width: 5%;" />
							<col style="width: 7%;" />
							<col style="width: 10%;" />
							<col style="width: 20%;" />
							<col style="width: 5%;" />
							<col style="width: 10%;" />
						</colgroup>
						<thead>
							<tr>
								<td style="line-height: 1;"><input type="checkbox" onclick="allChecked(this)" /></td>
								<td style="line-height: 1;">����ID</td>
								<td style="line-height: 1;">����ID</td>
								<td style="line-height: 1;">��������</td>
								<td style="line-height: 1;">����ֵ</td>
								<td style="line-height: 1;">����</td>
								<td style="line-height: 1;">�Ƿ���ʾ</td>
								<td style="line-height: 1;">������/�޸���</td>
								<td style="line-height: 1;">���/�޸�ʱ��</td>
								<td style="line-height: 1;">�鿴</td>
								<td style="line-height: 1;">����</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${facetGroupList}" var="facetGroup">
							<tr>
								<td>
									<label> <input type="checkbox" id="each" value="${facetGroup.id}" /> </label>
								</td>
								<td>${facetGroup.groupId}</td>
								<td>${facetGroup.categoryId}</td>
								<td><a href="${ctx}/FacetGroupItem/list.action?qparent_id=${facetGroup.groupId}&qcatid=${facetGroup.categoryId}">${facetGroup.displayName}</a></td>
								<td>${facetGroup.weight}</td>
								<td>${facetGroup.type}</td>
								<td>
									<c:if test="${facetGroup.isshow == 0}">����ʾ</c:if>
									<c:if test="${facetGroup.isshow == 1}">��ʾ</c:if> 
								</td>
								<td>${facetGroup.creator} / ${facetGroup.updator}</td>
								<td>${facetGroup.insertDate} / ${facetGroup.updateDate}</td>
								<td><a href="${ctx}/FacetGroupItem/list.action?qparent_id=${facetGroup.groupId}&qcatid=${facetGroup.categoryId}" >�鿴</a></td>
								<td>
									<a href="./edit.action?pageNumber=${pageNumber}&id=${facetGroup.id}&qcategoryid=${param.qcategoryid}" >�༭</a>
									<a href="javascript:void(0)" onclick="deleteObj('${facetGroup.id}', './delete.action')">ɾ��</a>
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
										<coo8:page name="facetGroupList" style="js" systemId="1" />
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