<%@ page import="com.coo8.topic.model.*"%>
<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<title>������</title>
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
				$("#isSearched").val('${param.isSearched}');
				
				$(window).resize(function() {
				    $("#importSpan").css("margin-left", ($(window).width())/1.8);
				});
			});
			
			//У����д�������Ƿ�Ϸ� 
			function checkCreateTime(){
				var type = 0;
				var createTime = $("#qcreateTime").val();
				var createEndTime = $("#qcreateEndTime").val();
				var createTimeNew = null;
				var createEndTimeNew = null;
				
				if(createTime !="" && createEndTime ==""){
					type=3;
				}
				if(createTime =="" && createEndTime !=""){
					type=4;
				}
				
				if(createTime != null && createTime != "" ){
					
					createTimeNew = new Date(createTime); 
				}
				if(createEndTime != null && createEndTime != "" ){
					
					createEndTimeNew = new Date(createEndTime);
				}
				
				if(createTimeNew !=null && createEndTimeNew !=null){
					
					if(Date.parse(createEndTimeNew) - Date.parse(createTimeNew)<0){
						type = 2;
					}
				}
				
				
				
				
				return type;
				
			}
			// ��ҳ����
			function tunePage(num)
			{
				//��д�������Ƿ�Ϸ� 
				
				var type = checkCreateTime();
				if(type != 0){
					if(type == 1){
						alert("��ʼʱ�䲻�ܵ��ڽ���ʱ�䣬��˶Ժ��ٲ�ѯ !");
					}else if(type == 2){
						alert("��ʼʱ�䲻�ܴ��ڽ���ʱ�䣬��˶Ժ��ٲ�ѯ !");
					}else if(type == 3){
						alert("����ʱ�䲻��Ϊ�գ���˶Ժ��ٲ�ѯ !");
					}else if (type == 4){
						alert("��ʼʱ�䲻��Ϊ�գ���˶Ժ��ٲ�ѯ !");
					}
					
					return;
					
				}
				
				var url = "./listSearchwords.action?pageNumber=" + num;
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
				var createEndTime = $("#qcreateEndTime").val();
				if(createEndTime != null && createEndTime != "" )
				{
					url += "&qcreateEndTime=" + createEndTime;
				}
				var qchecked = $("#qchecked").val();
                if(qchecked != null && qchecked != "" )
                {
                    url += "&qchecked=" + qchecked;
                }
                var qid = $("#qid").val();
                if(qid != null && qid != "" )
                {
                    url += "&qid=" + qid;
                }
                
				var isSearched = $("#isSearched").val();
                if(isSearched != null && isSearched != "" )
                {
                    url += "&isSearched=" + isSearched;
                }
				window.location = url;
				return;
			}
			//��idΪ��Χ����excel�ȴ��ļ���
			function outFile()
			{
				var minId = $.trim($("#minId").val());
				var maxId = $.trim($("#maxId").val());
				if(!minId || !maxId)
				{
					alert("��������ȷ�����֣�");
					return;
				}
				if(isNaN(minId) || isNaN(maxId))
				{
					alert("�Բ����������벻�Ϸ�����������ȷ���֣�");
					return;
				}
				window.location = "./outSearchwords.action?minId="+minId+"&maxId="+maxId;
			}
			//��idΪ��Χ�����ȴ�
			function scopePublishWords()
			{
				var minId = $.trim($("#minId").val());
				var maxId = $.trim($("#maxId").val());
				if(!minId || !maxId)
				{
					alert("��������ȷ�����֣�");
					return;
				}
				if(isNaN(minId) || isNaN(maxId))
				{
					alert("�Բ����������벻�Ϸ�����������ȷ���֣�");
					return;
				}
				if(minId > maxId)
				{
					alert("��ʼid���ܴ��ڽ���id�� ");
					return;
				}
				
				if(confirm('ȷ�Ϸ�����'))
	            {
	             $.post('${ctx}/SearchKeyword/scopePublishWords.action?minId='+minId+'&maxId='+maxId,
	                    function(msg){          
	                        if(msg =='success'){
	                            alert("�����ɹ���");
	                            window.location.reload();
	                        }
	                    }
	            	);
	        	} 
				
				//window.location = "./scopePublishWords.action?minId="+minId+"&maxId="+maxId;
			}
			
			//�����ȴ�
			function publish(id, url)
			{
				$.post(url, {id:id}, function(result){
				    if(result == 'success'){
				        alert("�����ɹ�");
                        window.location = location;
				    }
				    else if(result == 'noHotkeyword'){
				        alert("��Ų�����");
				    }
				    else if(result == 'emptyParam'){
				        alert("����Ϊ��");
				    }
				    else {
				        alert("�쳣����");
				    }
				});
			}
			//���������ȴ�
			function publishBatch(){
			    var cc = $(':checkbox[id=each][checked=true]');
		        var str = "";
		        for ( var j = 0; j < cc.length; j++) {
		            str = str + cc.get(j).value + ";";
		        }
		        if (str == "") {
		            alert('������ѡ��һ�');
		            return;
		        }
		        if(confirm('ȷ�Ϸ�����'))
		            {
		             $.post('${ctx}/SearchKeyword/publishBatchwords.action?ids='+ str,
		                    function(msg){          
		                        if(msg =='success'){
		                            alert("ȫ�������ɹ���");
		                            window.location.reload();
		                        }
		                        else if(msg.indexOf("error:")!=-1){
		                            var needMsg = msg.substring(msg.indexOf(":")+1);
		                            alert("��ţ�"+needMsg+"����ʧ�ܣ�");
                                    window.location.reload();
		                        }
		                    }
		            );
		        } 
			}
			
			
			//���������ȴ�
			function publishWapBatch(){
			    var cc = $(':checkbox[id=each][checked=true]');
		        var str = "";
		        for ( var j = 0; j < cc.length; j++) {
		            str = str + cc.get(j).value + ";";
		        }
		        if (str == "") {
		            alert('������ѡ��һ�');
		            return;
		        }
		        if(confirm('ȷ�Ϸ�����'))
		            {
		             $.post('${ctx}/SearchKeyword/publishWapBatchwords.action?ids='+ str,
		                    function(msg){          
		                        if(msg =='success'){
		                            alert("ȫ�������ɹ���");
		                            window.location.reload();
		                        }
		                        else if(msg.indexOf("error:")!=-1){
		                            var needMsg = msg.substring(msg.indexOf(":")+1);
		                            alert("��ţ�"+needMsg+"����ʧ�ܣ�");
                                    window.location.reload();
		                        }
		                    }
		            );
		        } 
			}
			
			
			
			//ȫ�������ȴ�
			function publishAllBatch(){
		        if(confirm('ȷ��ִ��ȫ������������ǣ�ʱ��Ƚϳ��������ĵȴ�'))
		            {
		             $.post('${ctx}/SearchKeyword/publishAllBatchwords.action',
		                    function(msg){          
		                        if(msg =='success'){
		                            alert("��ʼ���ɹ���");
		                            window.location.reload();
		                        }
		                        else if(msg.indexOf("error:")!=-1){
		                            var needMsg = msg.substring(msg.indexOf(":")+1);
		                            alert("��ţ�"+needMsg+"����ʧ�ܣ�");
                                    window.location.reload();
		                        }
		                    }
		            );
		        } 
			}
			
			function initReatedHotWords(){
				if(confirm('ȷ����ʼ������ȴ�������ǣ�ʱ��Ƚϳ��������ĵȴ�'))
	            {
	             $.post('${ctx}/SearchKeyword/initReatedHotWords.action',
	                    function(msg){          
	                        if(msg =='success'){
	                        	alert("��ʼ���ɹ���");
	                            
	                            window.location.reload();
	                        }else{
	                        	alert("��ʼ��ʧ�ܣ�");
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
			         alert('������ѡ��һ�');
			         return;
			    }
			    if(confirm('ȷ��ɾ����')){
			        window.location = "./deleteSearchwords.action?ids="+str;
			    }
			}
			function deleteSingleData(paramId){
			    if(confirm('ȷ��ɾ����')){
                    window.location = "./deleteSearchwords.action?ids="+paramId;
                }
			}
		</script>
	</head>
	<body>
		<form action="./listSearchwords.action" method="post" id="f1">
		<div class="mod-1">
			<div class="hd">
				<h3>�����ʹ���</h3>
			</div>
			<div class="bd clearfix">
				<div style="margin-bottom: 5px;" class="container-1">
					������ID�� <input type="text" class="txt-5" id="qid" name="qid" value="${qid}"/>
					���������ƣ� <input type="text" class="txt-5" id="qtitle" name="qtitle" value="${qtitle}"/> 
					�����ߣ�      <input type="text" class="txt-5" id="qcreator" name="qcreator" value="${qcreator}"/>
					&nbsp;&nbsp;&nbsp;&nbsp;	
					<input type="button" onclick="clearAll()" value="����"/>
					<input type="button" onclick="tunePage(1)" value="����"/><p>&nbsp;</p>
					������ʼʱ�䣺
					<input readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"
						   name="qcreateTime" id="qcreateTime" class="txt-5" value="${param.qcreateTime}"  />
					<img onclick="yxbegin() " src="http://app.gomein.net.cn/topics/images/images_3.gif" />
					&nbsp;&nbsp;
					��������ʱ�䣺
					<input readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"
						   name="qcreateEndTime" id="qcreateEndTime" class="txt-5" value="${param.qcreateEndTime}"  />
					<img onclick="yxend()" src="http://app.gomein.net.cn/topics/images/images_3.gif" />
					
					&nbsp;&nbsp;
					״̬��
					<s:select list="#{'':'��ѡ��','0':'δ����','1':'�ѷ���'}" name="qchecked" id="qchecked" theme="simple"></s:select>	 
					&nbsp;&nbsp;
					���������
					<s:select list="#{'':'��ѡ��','0':'��','1':'��'}" name="isSearched" id="isSearched" theme="simple"></s:select>	 
				</div>
				<div class="container-1" style="overflow: auto;">
					<table>
						<tbody>
							<tr>
								<td style="height: 5px; padding: 10px 0; vertical-align: middle;">
									<input type="button" value="����" onclick="publishBatch()"/>
									<!-- <input type="button" value="�ֻ��˷���" onclick="publishWapBatch()"/> -->
									<!-- <input type="button" value="ȫ������" onclick="publishAllBatch()"/> -->
									<input type="button" value="��ʼ��������" onclick="initReatedHotWords()"/>
									<input type="button" value="ɾ��" onclick="batchDeleteData()" />
									<span id="importSpan" style="margin-left: 100ex">
										����excel�ļ���ID��<input type="text" size="10" id="minId"/>�� <input type="text" size="10" id="maxId"/>
										<input type="button" value="���ַ���" onclick="scopePublishWords()" />
										<input type="button" value="�����ȴ��ļ�" onclick="outFile()" />
									</span>
								</td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
							</tr>
						</tbody>
					</table>
					<table class="tb-zebra tmp-class" style="width: 100%;">
						<colgroup>
							<col style="width: 5%;" />
							<col style="width: 8%;" />
							<col style="width: 10%;" />
							<col style="width: 8%;" />
							<col style="width: 29%;" />
							<col style="width: 12%;" />
							<col style="width: 8%;" />
							<col style="width: 5%;" />
							<col style="width: 15%;" />
						</colgroup>
						<thead>
							<tr>
								<td style="line-height: 1;"><input type="checkbox" onclick="allChecked(this)" /></td>
								<td style="line-height: 1;">���</td>
								<td style="line-height: 1;">�����ʱ���</td>
								<td style="line-height: 1;">�������</td>
								<td style="line-height: 1;">���/�޸�ʱ��</td>
								<td style="line-height: 1;">������/�޸���</td>
								<td style="line-height: 1;">����</td>
								<td style="line-height: 1;">վ��</td>
								<td style="line-height: 1;">����</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listHotword}" var="hotWord">
							<tr>
								<td>
									<label> <input type="checkbox" id="each" value="${hotWord.id}" /> </label>
								</td>
								<td>${hotWord.id}</td>
								<td>
								    <c:if test="${hotWord.isSearched == 1 && hotWord.checked == 1}">
								    <a href="http://www.gome.com.cn/hotwords/${hotWord.createTimeYMD}/${hotWord.id}/" target="_blank" >${hotWord.title}</a>
								    </c:if>
								    <c:if test="${hotWord.isSearched == 0 || hotWord.checked == 0}">
								    	${hotWord.title}
								    </c:if>
								    
								    
								</td>
								<td>
									<c:if test="${hotWord.isSearched == 0}">��</c:if>
									<c:if test="${hotWord.isSearched == 1}">��</c:if> 
								</td>
								<td>${hotWord.createTime} / ${hotWord.updateTime}</td>
								<td>${hotWord.creator} / ${hotWord.modifier}</td>
								<td>
									<c:if test="${hotWord.checked == 0}">δ����</c:if>
									<c:if test="${hotWord.checked == 1}">�ѷ���</c:if> 
								</td>
								<td>
									<c:choose>
										<c:when test="${hotWord.site == 'gome'}">����</c:when>
										<c:otherwise>���</c:otherwise>
									</c:choose>
								</td>
								<td>
									<a href="http://www.gome.com.cn/hotwords/${hotWord.createTimeYMD}/${hotWord.id}/" target="_blank">Ԥ��</a>
									<a href="javascript:void(0)" onclick="publish('${hotWord.id}', './publishSearchwords.action')">����</a>
									<a href="./editSearchword.action?id=${hotWord.id}&pageNumber=${pageNumber}" >�༭</a>
									<a href="javascript:void(0)" onclick="deleteSingleData('${hotWord.id}', './deleteSearchwords.action')">ɾ��</a>
									<%-- <a href="${ctx}/HotLink/create.action?hotLink.moduleId=${hotWord.id}&&hotLink.moduleType=1">�����������</a> --%>
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