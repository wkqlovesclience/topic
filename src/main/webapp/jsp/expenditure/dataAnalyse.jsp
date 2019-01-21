<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
	<title>���ϻ���ϵͳ</title>
	<link rel="stylesheet" href="${ctx}/styles/cui.css" />
	<script type="text/javascript" src="${ctx}/js/jquery-1.6.js"></script>
	<script src="${ctx}/js/json.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/laydate/laydate.js"  type="text/javascript" ></script>
	<script type="text/javascript" charset="GBK" src="${ctx}/js/ueditor/editor_config.js"></script>
	<script type="text/javascript" charset="GBK" src="${ctx}/js/ueditor/editor_all.js"></script>
	<script type="text/javascript" src="${ctx}/js/singleCalendar/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/js/tag/tag.js"></script>
	<style type="text/css">
		#channelAdd input {
			margin:10px;
			width:50px;
			height:20px;
		}
	</style>

	<script type="text/javascript"  >
        function keypress(value){
            if(value>0){
                document.getElementById("channels").style.visibility="hidden";
            }else{
                document.getElementById("channels").style.visibility="visible";
            }
        }
        <!--�����ļ�-->
        function exportExpenditure(){
            var fromToDate =  $("#from_to_date").val();
            if(fromToDate == ''){
                alert("��ѡ������!");
            }else{
                window.location.href = "./expendAnalyse.action?fromToDate="+fromToDate;
            }
        }

        <!--�����ļ�-->
        function downExpenditure(){
            var fromToDate =  $("#from_to_date").val();
            if(fromToDate == ''){
                alert("��ѡ�񵼳�����!");
            }else{
                window.location.href = "./downloadExpendAnalyse.action?fromToDate="+fromToDate;
            }
        }


	</script>
</head>
	<body>
		<div class="mod-1">
			<div class="hd">
				<h3>���ϻ���ͳ��</h3>
			</div>
			<div class="bd clearfix">
				<form action="${ctx}/Expenditure/list.action" method="post" id="f1">
					<div class="container-1">
						<table style="width: 100%;">
							<tbody>
							<tr>
								<div style="float: right;margin: 10px;padding-right: 8px">
									<input type="text" readonly="readonly" id="from_to_date" value="${fromToDate}" style="height: 25px;width: 152px;margin-right: 20px" placeholder="��ѡ����Ҫͳ�Ƶ����ڷ�Χ"/>
									<input type="button" value="��ѯ"  onclick="exportExpenditure()" style="margin-right:20px;width:65px;background:#ffc800" />
									<input type="button" value="����"  onclick="downExpenditure()" style="margin-right:20px;width:65px;background:#2293C4" />
								</div>
							</tr>
							</tbody>
						</table>
						<table class="tb-zebra tmp-class" style="width: 100%;">
							<colgroup>
								<col style="width: 10%;" />
								<col style="width: 20%;" />
								<col style="width: 30%;" />
								<col style="width: 20%;" />
								<col style="width: 20%;" />
							</colgroup>
							<thead>
							<tr>
								<td style="line-height: 1;">�˿�ID</td>
								<td style="line-height: 1;">�˿�</td>
								<td style="line-height: 1;">һ������</td>
								<td style="line-height: 1;">�����ܶ�</td>
								<td style="line-height: 1;">����</td>
							</tr>
							</thead>
							<tbody>
							<s:if test="expendAnalyseBaseList!= null">
								<tr>
									<td colspan="4">
										<span style="color: red;font-weight: bolder;font-size: x-large; text-align: center" >
											<s:property value="startDate"/>��<s:property value="endDate"/>�׶λ����ܶ�:
											<fmt:formatNumber value="${wholeStageExpendAmount}" pattern=",###.#"/>
										</span>
									</td>
									<td>
										<erm:authorize code="topic_expenditure_modify">
											<a href="${ctx}/Expenditure/ajaxWholeJump.action" target="_blank"/>ȫվ��������ͼ�鿴</a>
										</erm:authorize>
									</td>
								</tr>
								<s:iterator value="expendAnalyseBaseList"  status="ExpendAnalyseBase">
									<tr>
										<td rowspan="<s:property value='stairChannelAndExpends.size()+2' />"><s:property value='portId' /></td>
										<td rowspan="<s:property value='stairChannelAndExpends.size()+2' />"><s:property value='portName' /></td>
									</tr>
									<s:iterator value="stairChannelAndExpends" status="listmap">
										<tr>
											<s:iterator value="listmap" status="stairChannelAndExpends">
												<td> <s:property value="stairName" /></td>
												<td>
													<fmt:formatNumber value="${expendAmount}" pattern=",###.#"/>
												</td>
												<td>
													<erm:authorize code="topic_expenditure_modify">
														<a href="${ctx}/Expenditure/ajaxJump.action?firstStairId=<s:property value='stairId'/>" target="_blank"/>��������ͼ�鿴</a>
													</erm:authorize>
												</td>
											</s:iterator>
										</tr>

									</s:iterator>
									<tr>
										<td colspan="2">
											<span style="float: right;color: #761c19;font-size: larger;font-weight: bold"; >ͳ�ƣ�
												<fmt:formatNumber value="${portCostAmount}" pattern=",###.#"/>
											</span>
										</td>
										<td>
											<erm:authorize code="topic_expenditure_modify">
												<a href="${ctx}/Expenditure/ajaxPortJump.action?realPortId=<s:property value='portId'/>" target="_blank"/>�˿�����ͼ�鿴</a>
											</erm:authorize>
										</td>
									</tr>

								</s:iterator>
							</s:if>
							<s:if test="expendAnalyseBaseList == null">
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<erm:authorize code="topic_expenditure_add"><td ><img onclick="goAddList()" src="${ctx}/images/add.png"/></td></erm:authorize>
								</tr>
							</s:if>
							</tbody>
						</table>
					</div>
				</form>
			</div>
		</div>
	</body>

<script type="text/javascript" charset="GBK">
    <!--���ڿؼ�-->
    laydate.render({
        elem: '#from_to_date',
        lang: 'en',
        range: '~' //�� range: '~' ���Զ���ָ��ַ�
    });
</script>
</html>