<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/admin/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<title>��̬ҳ�����</title>
		<link href="http://css.gomein.net.cn/topics/css/b2c_backstage.css" rel="stylesheet"
			type="text/css" />
		<link href="http://css.gomein.net.cn/topics/css/OPTBG.css" rel="stylesheet"
			type="text/css" />
		<%@ include file="/jsp/admin/common/css.jsp"%>
		<%@ include file="/jsp/admin/common/js.jsp"%>
		<%@ include file="/jsp/admin/common/common_admin_css.jsp"%>
		<script type="text/javascript" src="/coo8js/jquery.blockUI.js"></script>
		<style type="text/css">
.div_data {
	
}
</style>
	</head>
	<body
		style="background: #afb8bf; overflow-x: hidden; overflow-y: scroll; * overflow-y: hidden">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td background="http://app.gomein.net.cn/topics/images/RigNav.gif" height="31">
					<table cellspacing="0" cellpadding="0">
						<tr>
							<td class="TextRig">
								<img src="http://app.gomein.net.cn/topics/images/nav_3.gif" width="11"
									height="7" />
							</td>
							<td style="width: 97%" class="NavText DText">
								��̬ҳ�����
							</td>
						</tr>
					</table>
				</td>
			<tr>
				<td>
					<div class="RightBg">
						<div class="H10"></div>
						<div class="RightTabTags">
							<table cellspacing="0" cellpadding="0">
								<tr>
									<td style="width: 16px"></td>
									<td style="width: 88px; border-bottom: none;"
										class="TextCen GDDDray Boder fB">
										<a style="color: white" href="${ctx}/admin/html/list.action">���о�̬ҳ</a>
									</td>
									<td style="width: 5px"></td>
									<td style="width: 88px; border-bottom: none;"
										class="TextCen GDray LBoder fB">
										<a style="text-decoration: none;"
											href="${ctx}/admin/html/staticHtmlCreateRequest.action">�½���̬ҳ</a>
									</td>
									<td></td>
								</tr>
							</table>
						</div>
						<div class="RightTab">
							<form action="${ctx}/admin/html/list.action" metdod="post"
								id="htmlFormId" onsubmit="YUTIL.trimFormVal(tdis.id)">
								<input type="hidden"
									value="<s:property value='htmlList.pageIndex' />"
									name="html.pageIndex" id="page-index" />
								<table cellspacing="0" cellpadding="0">
									<tr>
										<td style="width: 90px" class="TextRig">
											��̬ҳ����
										</td>
										<td style="width: 148px" class="TextLeft">
											&nbsp;
											<input type="text" name="html.name" style="height: 16px;"
												value="<s:property value='html.name'/>" />
										</td>
										<td style="width: 90px" class="TextRig">
											��̬ҳ����
										</td>
										<td style="width: 148px" class="TextLeft">
											&nbsp;
											<input type="text" name="html.description"
												style="height: 16px;"
												value="<s:property value='html.description'/>" />
										</td>
										<td style="width: 90px" class="TextRig">
											����
										</td>
										<td colspan="2" class="TextLeft" style="width: 148px">
											&nbsp;
											<select name="html.type">
												<option value="">
													ȫ��
												</option>
												<option
													<s:property value="%{html.isCommonType() ? 'selected' : ''}" />
													value="<s:property value="%{@com.coo8.btoc.model.html.StaticHtml@COMMON_TYPE}" />">
													��ͨ
												</option>
												<option
													<s:property value="%{html.isCatalogType() ? 'selected' : ''}" />
													value="<s:property value="%{@com.coo8.btoc.model.html.StaticHtml@CATALOG_TYPE}" />">
													�������
												</option>
											</select>
										</td>
									</tr>
									<tr>
										<td class="TextRig">
											�޸���
										</td>
										<td class="TextLeft">
											&nbsp;
											<input type="text" name="html.updator"
												value="<s:property value='html.updator'/>"
												style="height: 16px;" />
										</td>
										<td class="TextRig">
											������
										</td>
										<td class="TextLeft">
											&nbsp;
											<input type="text" name="html.creator"
												value="<s:property value='html.creator'/>"
												style="height: 16px;" />
										</td>
										<td class="TextRig">
											��̬ҳ·��
										</td>
										<td class="TextLeft">
											&nbsp;
											<input type="text" name="html.path"
												value="<s:property value='html.path'/>"
												style="height: 16px;" />
											&nbsp;&nbsp;&nbsp;
											<input type="submit" name="Submit222" value="��ѯ"
												style="width: 48px; height: 25px; color: #485058" />
										</td>
									</tr>
								</table>
							</form>
							<div class="H10"></div>
							<table cellpadding="0" cellspacing="0" class="LBoder">
								<tr>
									<td class="TextCen fB GDDray LBodRit DText" style="width: 3%">
										���
									</td>
									<td class="TextCen fB GDDray LBodRit DText" style="width: 12%">
										����
									</td>
									<td class="TextCen fB GDDray LBodRit DText" style="width: 10%">
										·��
									</td>
									<td class="TextCen fB GDDray LBodRit DText" style="width: 5%">
										����
									</td>
									<td class="TextCen fB GDDray LBodRit DText" style="width: 24%">
										����
									</td>
									<td class="TextCen fB GDDray LBodRit DText" style="width: 8%">
										������
									</td>
									<td class="TextCen fB GDDray LBodRit DText" style="width: 8%">
										����ʱ��
									</td>
									<td class="TextCen fB GDDray LBodRit DText" style="width: 8%">
										�޸���
									</td>
									<td class="TextCen fB GDDray LBodRit DText" style="width: 8%">
										�޸�ʱ��
									</td>
									<td class="TextCen fB GDDray DText" style="width: 14%">
										����
									</td>
								</tr>
								<s:iterator value="htmlList" status="t" var="ct">
									<tr
										bgcolor="<s:property value="%{#t.count%2==0?'#e7eaee':'#f9f9f9'}"/>"
										id="tr_<s:property value='id'/>">

										<td class="TextCen DText LBodTop LBodRit">
											<!-- c:if test="${fn:indexOf(sessionScope.urls,'/admin/html/updateRequest.action')>=0}" -->
												<a
													href="${ctx}/admin/html/updateRequest.action?html.id=<s:property value="id"/>"><s:property
														value="id" />
												</a>
												<!--  c:if>-->
										</td>
										<td class="TextCen DText LBodTop LBodRit"
											style="white-space: normal; line-height: 20px;">
											<a
												href="${ctx}/admin/html/updateRequest.action?html.id=<s:property value="id"/>"><s:property
													value="name" />
											</a>
										</td>
										<td class="TextCen DText LBodTop LBodRit"
											style="white-space: normal; line-height: 20px;">
											<s:property value="path" />
										</td>
										<td class="TextCen DText LBodTop LBodRit">
											<s:property value="%{commonType ? '��ͨ' : '�������'}" />
										</td>
										<td class="TextCen DText LBodTop LBodRit"
											style="white-space: normal; line-height: 20px;">
											<s:property value="description" />
										</td>
										<td class="TextCen DText LBodTop LBodRit">
											<s:property value="creator" />
										</td>
										<td class="TextCen DText LBodTop LBodRit"
											style="white-space: normal; line-height: 20px;">
											<s:date name="createdTime" format="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td class="TextCen DText LBodTop LBodRit">
											<s:property value="updator" />
										</td>
										<td class="TextCen DText LBodTop LBodRit"
											style="white-space: normal; line-height: 20px;">
											<s:date name="updatedTime" format="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td class="TextCen DText LBodTop"
											style="white-space: normal; line-height: 20px;">
											<a
												href="/admin/html/updateRequest.action?html.id=<s:property value="id"/>">�༭</a>
											<a
												href="javascript:deleteStaticHtml(<s:property value="id"/>);">ɾ��</a>
											<s:if test="%{templateId == null}">
												<!-- c:if
													test="${fn:indexOf(sessionScope.urls,'/admin/template/list.action')>=0}"> -->
													<a
														href="${ctx}/admin/template/list.action?htmlId=<s:property value='id' />">����</a>
												<!-- c:if>-->
											</s:if>
											<s:else>
												<!-- c:if
													test="${fn:indexOf(sessionScope.urls,'/admin/template/list.action')>=0}"> -->
													<a
														href="${ctx}/admin/template/list.action?htmlId=<s:property value='id' />">���¹���</a>
												<!--cif>-->

												<!-- c:if
													test="${fn:indexOf(sessionScope.urls,'/admin/html/publish.action')>=0}">-->
													<a href="javascript:"
														onclick="publish(<s:property value='id'/>)">����</a>
												<!--cif>-->
												<a id="show_a_id_<s:property value='id'/>"
													href="javascript:showBlock(<s:property value="id"/>, <s:property value="templateId"/>);">
													<font color="red">�鿴��</font> </a>
											</s:else>
										</td>
									</tr>
									<tr id="tr_block_div_<s:property value='id'/>"
										style="display: none;">
										<td style="white-space: normal; line-height: 20px;"
											colspan="10">
											<div id="block_div_<s:property value='id'/>"
												style="display: none;"></div>
										</td>
									</tr>
								</s:iterator>
								<tr>
									<td colspan="10" class="LBodTop TextRig">
										<div>
											<div class="numpage">
												<s:if test="htmlList.firstPage">
													<h6>
														��
														<s:property value='%{htmlList.totalRec}' />
														����¼��
													</h6>
													<span class="numpage1">��һҳ</span>
												</s:if>
												<s:else>
													<a target="_self"
														href="javascript:submitPgaeSplit(<s:property value='%{htmlList.pageIndex-1}' />);">��һҳ</a>
												</s:else>
												<s:iterator var="index" begin="htmlList.begin"
													end="htmlList.end">
													<s:if test="%{htmlList.pageIndex==#index}">
														<span><s:property value="#index" />
														</span>
													</s:if>
													<s:else>
														<a target="_self"
															href="javascript:submitPgaeSplit(<s:property value='#index' />);"><s:property
																value="#index" />
														</a>
													</s:else>
												</s:iterator>
												<s:if test="htmlList.lastPage">
													<span class="numpage1">��һҳ</span>
												</s:if>
												<s:else>
													<a target="_self"
														href="javascript:submitPgaeSplit(<s:property value='%{htmlList.pageIndex+1}' />);">��һҳ</a>
												</s:else>
												<s:if test="htmlList.end > 1">
													<label>
														����
														<input name="" type="text" />
														ҳ
													</label>
												</s:if>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</td>
			</tr>
		</table>

		<div id="data_div" style="display: none;" class="div_data"></div>
		<script type="text/javascript">
	var resourceType = "<s:property value='%{@com.coo8.btoc.model.resource.Resource@PRODUCT_RESOURCE}' />";
	function submitPgaeSplit(num){
		$('#page-index').val(num);
		$('#htmlFormId').submit();				
	}
	
	//����
	function publish(obj){
		if(!confirm("ȷ��Ҫ������?"))
			return;
			
		$.ajax({
			url:"${ctx}/admin/html/publish.action?aaa="+Math.random(),
			data:"id="+obj,
			success:function(){alert("�����ɹ�")}
		});
	}
	
	function deleteStaticHtml( id ) {
		if(!confirm("ȷ��Ҫɾ����?"))
			return;
		
		$.ajax({
			url:"${ctx}/admin/html/delete.action?html.id=" + id,
			success:function(){
				alert("ɾ���ɹ�");
				$("#tr_" + id).hide();
			}
		});
	}
	
	function showBlock(id, templateId){
		var did = "block_div_" + id;
		$.ajax({
			  url: "${ctx}/admin/block/listWithResource.action?block.templateId="+templateId+"&resourceType="+resourceType+"&staticHtmlId="+id+"&a="+Math.random(),
			  success: function(data){
				$("#" + did).html(data);
				$("#show_a_id_" + id).text("���ؿ�").attr("href","javascript:hide("+id+"," +templateId+ ");");
				show(id);
			}
		});
	}
	function show(id){
		$("#tr_block_div_" + id).show();
		$("#block_div_" + id).slideDown(500);
	}
	
	function hide(id, templateId) {
		$("#block_div_" + id).slideUp(500);
		setTimeout(function(){
			$("#block_div_"+id).empty();
			$("#tr_block_div_" + id).hide();
		}, 450);
		$("#show_a_id_" + id).text("�鿴��").attr("href","javascript:showBlock("+id+"," +templateId+ ");");
	}
	
	
	var data_num = 0;
	
	//չʾ���в�Ʒ
	function showData(htmlId, resourceId, dataNum){
		this.data_num = dataNum;
		$.ajax({
			  url: "${ctx}/admin/resource/product/list.action?staticHtmlId="+htmlId+"&resourceId="+resourceId+"&dateNum="+dataNum+"&a="+Math.random(),
			  success: function(data){
				  if($.trim(data) == 'error') {
					  alert("��û�������Ʒ��");
					  return false;
				  }
				  
				  $("#data_div").html(data);
				  $("#data_div").find(".delete_a").click(function(){
					  deleteOneProudcut($(this), htmlId, resourceId);
				  });
				  
				  $("#data_div").find(".open_a").click(function(){
					  openOneProudcut($(this), htmlId, resourceId);
				  });
				  
				  $("#data_div").find(".moveup_a").click(function(){
					  moveUp($(this), htmlId, resourceId);
				  });
				  
				  $("#data_div").find(".movedown_a").click(function(){
					  moveDown($(this), htmlId, resourceId);
				  });
				  
				  $.blockUI({ 
						message: $('#data_div'),
						css: {
							top: '30%',
							left: '35%',
							textAlign: 'left',
							marginLeft: '-320px',
							marginTop: '-145px',
							width: '1000px',
							background: 'none'
							}
				});
				$('.blockOverlay').attr('title','�����ر�').click(function(){
					$.unblockUI();
					$("#data_div").empty();
				}); 
			}
		});
	};
	
	//ɾ����Ʒ
	function deleteOneProudcut(rc, hid, rid){
		$.ajax({
			  url: "${ctx}/admin/resource/product/delete.action?staticHtmlId=" 
					  + hid  + "&resourceId=" + rid + "&position=" + rc.attr("position"),
			  success: function(data){
				  $("#tr_"+rc.attr('tid')).children(".clean_data").each(function(){
				  	$(this).html("");
				  });
			  }
		});		
	};
	
	//�򿪹رղ�Ʒλ
	function openOneProudcut(rc, hid, rid){
	
		$.ajax({
			  url: "${ctx}/admin/resource/updateOpenState.action?staticHtmlId=" 
					  + hid  + "&resourceId=" + rid + "&compId=" + rc.attr("cid") + "&position=" + rc.attr("position") + "&open=" + rc.attr("openState"),
			  success: function(data){
			  	var img_close = '<img style="width: 14px; height: 14px;" alt="" src="http://app.gomein.net.cn/topics/images/u155.gif" border="0" complete="complete"/>';
			  	var img_open = '<img style="width: 14px; height: 14px;" alt="" src="http://app.gomein.net.cn/topics/images/u153.gif" border="0" complete="complete"/>';
			  	rc.html(rc.attr("openState") == 0 ? img_open : img_close);
			  	rc.parent().siblings('#td_status_' + rc.attr('tid')).html(rc.attr("openState") == 0 ? '�ر�' : '����');
			  	rc.attr("openState", (rc.attr("openState") == 0 ? 1 : 0) );
			  }
		});		
	};
	
	//����
	function moveUp(rc, htmlId, resourceId){
		var dataNum = this.data_num;
		
		if(rc.attr("position") <= 1){
			alert('��ǰ��ƷλΪ��1�����޷�����');
			return ;
		}
		
		$.ajax({
			url: "${ctx}/admin/resource/moveUpPosition.action?resourceHtmlId=" +rc.attr("tid")+ "&staticHtmlId=" 
				  + htmlId  + "&resourceId=" + resourceId + "&position=" + rc.attr("position"),
		  	success: function(data){
		  	   showData(htmlId,resourceId,dataNum);
		    }
		});
	
	}
	
	//����
	function moveDown(rc, htmlId, resourceId){
		var dataNum = this.data_num;
		
		if(rc.attr("position") >= dataNum){
			alert('��ǰ��ƷλΪ���һ�����޷�����');
			return ;
		}
		
		$.ajax({
			url: "${ctx}/admin/resource/moveDownPosition.action?resourceHtmlId=" +rc.attr("tid")+ "&staticHtmlId=" 
				  + htmlId  + "&resourceId=" + resourceId + "&position=" + rc.attr("position") + "&dateNum=" + dataNum,
		  	success: function(data){
		  		showData(htmlId,resourceId,dataNum);
		  }
		});
	
	}
	
	//��Ӳ�Ʒ�ص��������̨��ӡ�
	function queryDataCallback(data, resourceId, htmlId, position, resourceHtmlId){
		var dataNum = this.data_num;
		if(data.length == 0)
			return;
			
		$.ajax({
			  url: "${ctx}/admin/resource/addProductIds.action?productId=" 
					  + data[0].id + "&resourceId="+resourceId + "&staticHtmlId=" + htmlId + "&position=" + position + "&resourceHtmlId=" + resourceHtmlId,
			  dataType:'text',
			  success: function(state){
			  	  if(state == 'success'){
				  	showData(htmlId,resourceId,dataNum);
				  }else if(state == 'error'){
				  	  alert('ϵͳ����');
				  }
			  }
		});	
	}
	
	function addData(htmlId,resourceId,position,resourceHtmlId){
		window.parent.commonShowQueryPanel(function(data){
			queryDataCallback(data, resourceId, htmlId, position, resourceHtmlId);
		});
		$.unblockUI();
		//$("#data_div").empty();
	};
</script>

</body>
</html>

