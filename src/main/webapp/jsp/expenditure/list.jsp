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

        $(document).ready(function(){
            var ids = document.getElementById("id").value;
            var selectPortIds= document.getElementById("selectPortIds").value;
            var selectStairIds = document.getElementById("selectStairIds").value;
            var selectSecondIds = document.getElementById("selectSecondIds").value;
            var selectThirdIds = document.getElementById("selectThirdIds").value;
            if(ids>0){
                document.getElementById("channels").style.visibility="hidden";
            }else{
                document.getElementById("channels").style.visibility="visible";
                document.getElementById("id").value="";
            }
            if(selectPortIds>0){
                document.getElementById("id").disabled=true;
            }else{
                document.getElementById("id").disabled="";
            }

            <!--�첽port-->
            $.ajax({
                url: "${ctx}/Expenditure/portList_ajax.action",
                type: "post",
                dataType:"json",
                cache:true,
                success:function(data){
                    for (var i = 0; i < data.length; i++) {
                        var $option = $("<option></option>");
                        $option.attr("value", data[i].id);
                        if(selectPortIds== data[i].id){
                            $option.attr("selected","selected");
                        }
                        $option.text(data[i].portName);
                        $("#portId").append($option);
                    }
                }
            })
            if(selectPortIds !=0){
                $.ajax({
                    url: "${ctx}/Expenditure/stairList_ajax.action?selectPortIds="+selectPortIds,//����url
                    type: "post",
                    dataType:"json",
                    async:false,
                    cache:true,
                    success:function(data){
                        $("#stairId option[value!='0']").remove();
                        $("#secondChanId option[value!='0']").remove();
                        $("#thirdChanId option[value!='0']").remove();
                        for (var i = 0; i < data.length; i++) {
                            var $option = $("<option></option>");
                            $option.attr("value", data[i].id);
                            if(selectStairIds== data[i].id){
                                $option.attr("selected","selected");
                            }
                            $option.text(data[i].stairName);
                            $("#stairChanId").append($option);
                        }
                    }
                })
            }
            if(selectStairIds!=0){
                $.ajax({
                    url: "${ctx}/Expenditure/secondList_ajax.action?selectStairIds="+selectStairIds,//����url
                    type: "post",
                    async:false,
                    cache:true,
                    dataType:"json",
                    success:function(data){
                        $("#secondChanId option[value!='0']").remove();
                        $("#thirdChanId option[value!='0']").remove();
                        for (var i = 0; i < data.length; i++) {
                            var $option = $("<option></option>");
                            $option.attr("value", data[i].id);
                            if(selectSecondIds== data[i].id){
                                $option.attr("selected","selected");
                            }
                            $option.text(data[i].secondName);
                            $("#secondChanId").append($option);
                        }
                    }
                })
            }

            if(selectSecondIds!=0){
                $.ajax({
                    url: "${ctx}/Expenditure/thirdList_ajax.action?selectStairIds="+selectStairIds+"&selectSecondIds="+selectSecondIds,
                    type: "post",
                    async:false,
                    cache:true,
                    dataType:"json",
                    success:function(data){
                        $("#thirdChanId option[value!='0']").remove();
                        for (var i = 0; i < data.length; i++) {
                            var $option = $("<option></option>");
                            $option.attr("value", data[i].id);
                            if(selectThirdIds== data[i].id){
                                $option.attr("selected","selected");
                            }
                            $option.text(data[i].thirdName);
                            $("#thirdChanId").append($option);
                        }
                    }
                })
            }

            $("#portId").change(function () {
                var portIds = document.getElementById("portId").value;
                if(portIds==0){
                    document.getElementById("id").disabled="";
                    $("#stairChanId option[value!='0']").remove();
                    $("#secondChanId option[value!='0']").remove();
                    $("#thirdChanId option[value!='0']").remove();
                }else{
                    document.getElementById("id").disabled=true;
                    $.ajax({
                        url: "${ctx}/Expenditure/stairList_ajax.action?selectPortIds="+portIds,
                        type: "post",
                        dataType:"json",
                        success:function(data){
                            $("#stairChanId option[value!='0']").remove();
                            $("#secondChanId option[value!='0']").remove();
                            $("#thirdChanId option[value!='0']").remove();
                            for (var i = 0; i < data.length; i++) {
                                var $option = $("<option></option>");
                                $option.attr("value", data[i].id);
                                $option.text(data[i].stairName);
                                $("#stairChanId").append($option);
                            }
                        }
                    })
                }
            });
            $("#stairChanId").change(function () {
                var stairIds = document.getElementById("stairChanId").value;
                if(stairIds==0){
                    $("#secondChanId option[value!='0']").remove();
                    $("#thirdChanId option[value!='0']").remove();
                }else{
                    $.ajax({
                        url: "${ctx}/Expenditure/secondList_ajax.action?selectStairIds="+stairIds,
                        type: "post",
                        dataType:"json",
                        success:function(data){
                            $("#secondChanId option[value!='0']").remove();
                            $("#thirdChanId option[value!='0']").remove();
                            for (var i = 0; i < data.length; i++) {
                                var $option = $("<option></option>");
                                $option.attr("value", data[i].id);
                                $option.text(data[i].secondName);
                                $("#secondChanId").append($option);
                            }
                        }
                    })
                }
            });
            $("#secondChanId").change(function () {
                var stairIds = document.getElementById("stairChanId").value;
                var secondChanIds = document.getElementById("secondChanId").value;
                if(secondChanIds!=0){
                    $.ajax({
                        url: "${ctx}/Expenditure/thirdList_ajax.action?selectStairIds="+stairIds+"&selectSecondIds="+secondChanIds,
                        type: "post",
                        dataType:"json",
                        success:function(data){
                            $("#thirdChanId option[value!='0']").remove();
                            for (var i = 0; i < data.length; i++) {
                                var $option = $("<option></option>");
                                $option.attr("value", data[i].id);
                                $option.text(data[i].thirdName);
                                $("#thirdChanId").append($option);
                            }
                        }
                    })
                }else{
                    $("#thirdChanId option[value!='0']").remove();
                }
            });

        });

        function tunePage(num) {
            var portId  = document.getElementById("portId").value;
            var stairChanId = document.getElementById("stairChanId").value;
            var secondChanId = document.getElementById("secondChanId").value;
            var thirdChanId = document.getElementById("thirdChanId").value;
            var fromToDate = document.getElementById("from_to_date1").value;
            var id  = document.getElementById("id").value;
            var page_size = document.getElementById("pageSize").value;
            var url = '${ctx}/Expenditure/list.action?pageNum=' + num;
            url += "&pageSize=" + page_size+"&searchKey.portId="+portId+
                "&searchKey.stairChanId="+stairChanId+"&searchKey.secondChanId="+secondChanId+"&searchKey.thirdChanId="+thirdChanId+"&fromToDate="+fromToDate;
            window.location = url;
        }
        function changePageSize(size) {
            var portId  = document.getElementById("portId").value;
            var stairChanId = document.getElementById("stairChanId").value;
            var secondChanId = document.getElementById("secondChanId").value;
            var thirdChanId = document.getElementById("thirdChanId").value;
            var id  = document.getElementById("id").value;
            var url = "${ctx}/Expenditure/list.action?pageNum=1&pageSize="+size+"&searchKey.portId="+portId+
                "&searchKey.stairChanId="+stairChanId+"&searchKey.secondChanId="+secondChanId+"&searchKey.thirdChanId="+thirdChanId+"&searchKey.id="+id;
            window.location = url;
        }
        function keypress(value){
            if(value>0){
                document.getElementById("channels").style.visibility="hidden";
            }else{
                document.getElementById("channels").style.visibility="visible";
            }
        }
        <!--�������ҳ��-->
        function addChannel(){
            window.location.href = "${ctx}/Expenditure/addChannelView.action";
        }
        <!--��ӻ���ҳ��-->
        function goAddList(){
            window.location.href = "${ctx}/Expenditure/addView.action"
        }
        <!--��������У��-->
        var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
        function checkFile(target) {
            var fileSize = 0;
            var filetypes =[".xls",".xlsx"];
            var filepath = target.value;
            var filemaxsize = 1024*2;//2M
            if(filepath){
                var isnext = false;
                var fileend = filepath.substring(filepath.lastIndexOf("."));
                if(filetypes && filetypes.length>0){
                    for(var i =0; i<filetypes.length;i++){
                        if(filetypes[i]==fileend){
                            isnext = true;
                            break;
                        }
                    }
                }
                if(!isnext){
                    alert("�ļ���?����ԣ����ϴ�Excel�ļ���");
                    target.value ="";
                    return false;
                }
            }else{
                return false;
            }
            if (isIE && !target.files) {
                var filePath = target.value;
                var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
                if(!fileSystem.FileExists(filePath)){
                    alert("���������ڣ����������룡");
                    return false;
                }
                var file = fileSystem.GetFile (filePath);
                fileSize = file.Size;
            } else {
                fileSize = target.files[0].size;
            }
            var size = fileSize / 1024;
            if(size>filemaxsize){
                alert("������С���ܴ���"+filemaxsize/1024+"M��");
                target.value ="";
                return false;
            }
            if(size<=0){
                alert("������С����Ϊ0M��");
                target.value ="";
                return false;
            }
        }

        function toExcelTemplate(){
            window.location.href ='./download.action';

        }
        function sureFile(){
            var fileName = $("#fileId").val();
            if(fileName ==''){
                alert("���ϴ��ļ�~");
                return false;
            }
        }
        <!--�����ļ�-->
        function exportExpenditure(){
            var fromToDate =  $("#from_to_date").val();
            if(fromToDate == ''){
                alert("��ѡ�񵼳�����!");
            }else{
                window.location.href = "./export.action?fromToDate="+fromToDate;
            }
        }
	</script>
</head>
<body>
<div class="mod-1">
	<div class="hd">
		<h3>���ϻ��ѹ���</h3>
	</div>
	<div class="bd clearfix">
		<form action="${ctx}/Expenditure/list.action" method="post" id="f1">
			<div style="margin-bottom: 10px;" class="container-1">
				ID��
				<input type="text" class="txt-5" id="id" name="searchKey.id" value="${searchKey.id}"  oninput="keypress(this.value=this.value.replace(/[^0-9-]+/,''))"/>
				<input type="hidden" id="selectPortIds" value="${selectPortIds}"/>
				<input type="hidden" id="selectStairIds" value="${selectStairIds}"/>
				<input type="hidden" id="selectSecondIds" value="${selectSecondIds}"/>
				<input type="hidden" id="selectThirdIds" value="${selectThirdIds}"/>
				<span id="channels">
					�˿ڣ�
				 <select  id="portId" name="searchKey.portId" >
				 <option value="0">��</option>
				  </select>
				 һ������:
				  <select id="stairChanId" name="searchKey.stairChanId" >
				   <option value="0">��</option>
				  </select>

				  ��������:
				   <select id="secondChanId" name="searchKey.secondChanId" >
				   <option value="0">��</option>
				   </select>
				   ��������:
				    <select id="thirdChanId" name="searchKey.thirdChanId" >
				    <option value="0">��</option>
				    </select>
				    </span>
				<input type="text" readonly="readonly" id="from_to_date1" name="fromToDate" value="${fromToDate}" placeholder="��ѡ���ѯ���ڷ�Χ"/>
				<input type="submit" value="��ѯ" style="width:45px;background:#ffc800" />
				&nbsp;&nbsp;&nbsp;
				<erm:authorize code="topic_expenditure_channel_add">
					<span>��ӣ�
					<em style="color:#0d14ff;">
						<input type="button" value="�������" onclick="addChannel()" style="width:75px;background:#ec971f"/>
					</em>
				</span>
				</erm:authorize>
			</div>


			<div class="container-1">

					<table style="width: 100%;">
						<tbody>
						<tr>
							<erm:authorize code="topic_expenditure_channel_add">
								<div style="float: left;margin: 10px;padding-right: 8px">
									<span style="font-size: large;font-weight: bolder">��ӻ��ѣ�</span>
									<img onclick="goAddList()" src="${ctx}/images/add2.png" style="width: 25px;height: 25px"/>
								</div>
							</erm:authorize>
							<%--<erm:authorize code="topic_expenditure_download">
								<div style="float: right;margin: 10px;padding-right: 8px">
									<input type="text" readonly="readonly" id="from_to_date" style="height: 25px;width: 152px;margin-right: 20px" placeholder="��ѡ�񵼳�����"/>
									<input type="button" value="����" onclick="exportExpenditure()" style="margin-right:20px;width:45px;"/>
								</div>
							</erm:authorize>--%>
						</tr>
						</tbody>
					</table>


				<table class="tb-zebra tmp-class" style="width: 100%;">
					<colgroup>
						<col style="width: 5%;" />
						<col style="width: 10%;" />
						<col style="width: 5%;" />
						<col style="width: 10%;" />
						<col style="width: 10%;" />
						<col style="width: 10%;" />
						<col style="width: 8%;" />
						<col style="width: 8%;" />
						<col style="width: 8%;" />
						<col style="width: 8%;" />
						<col style="width: 5%;" />
						<col style="width: 8%;" />
						<col style="width: 1%;" />
					</colgroup>
					<thead>
					<tr>
						<td style="line-height: 1;">ID</td>
						<td style="line-height: 1;">����</td>
						<td style="line-height: 1;">�˿�</td>
						<td style="line-height: 1;">һ������</td>
						<td style="line-height: 1;">��������</td>
						<td style="line-height: 1;">��������</td>
						<td style="line-height: 1;">UV</td>
						<td style="line-height: 1;">������</td>
						<td style="line-height: 1;">���۶�</td>
						<td style="line-height: 1;">����</td>
						<td style="line-height: 1;">����</td>
						<td style="line-height: 1;">������</td>

					</tr>
					</thead>
					<tbody>
					<s:if test="expenditureList!= null">
						<s:iterator value="expenditureList"  status="st">
							<tr>
								<td><s:property value='id' /></td>
								<td ><s:property value='timestamp' /></td>
								<td ><s:property value='port' /></td>
								<td ><s:property value='chanStair.stairName' /></td>
								<td ><s:property value='chanSecond.secondName' /></td>
								<td ><s:property value='chanThird.thirdName' /></td>
								<td ><s:property value='UV' /></td>
								<td><s:property value='orderNum' /></td>
								<td><s:property value='sale' /></td>
								<td><s:property value='expenditure' /></td>
								<td><erm:authorize code="topic_expenditure_modify">
									<a href="${ctx}/Expenditure/modifyView.action?expenditureId=<s:property value='id'/>"/>�޸�</a>
								</erm:authorize></td>
								<td><s:property value='mender' /></td>
								<%--<s:if test="#st.index==0">
									<erm:authorize code="topic_expenditure_add"><td ><img onclick="goAddList()" src="${ctx}/images/add2.png"/></td></erm:authorize>
								</s:if>--%>
							</tr>
						</s:iterator>
					</s:if>
					<s:if test="expenditureList == null">
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
				<table width="100%">
					<tfoot>
					<tr>
						<td style="border: 0 none;">
							<div class="numpage-box">
								<div class="numpage">
									<coo8:page name="expenditureList" style="js" systemId="1" />
									<coo8:page name="expenditureList" style="js" systemId="3" />
								</div>
							</div></td>
					</tr>
					</tfoot>
				</table>
			</div>
		</form>
		<erm:authorize code="topic_expenditure_download">
			<form action="${ctx}/Expenditure/import.action" method="post"  enctype="multipart/form-data" onsubmit="return sureFile()">
				<input type="file" size="50" id="fileId" name="expFile"  onchange="checkFile(this);" />
				<input type="submit"  value="���뻨������" />
			</form>
		</erm:authorize>
		<input type="button" value="����excelģ��" onclick="toExcelTemplate()" />��ʾ:���������,���ֵ��������0���!
	</div>
</div>
</body>
<script type="text/javascript" charset="GBK">
    <!--���ڿؼ�-->
    /*laydate.render({
        elem: '#from_to_date',
        lang: 'en',
        range: '~' //�� range: '~' ���Զ���ָ��ַ�
    });*/
    laydate.render({
        elem: '#from_to_date1',
        lang: 'en',
        range: '~' //�� range: '~' ���Զ���ָ��ַ�
    });
</script>
</html>