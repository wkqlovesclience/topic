<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
	<title>��Ʒ�ʹ���</title>
	<link rel="stylesheet" href="${ctx}/styles/cui.css" />
	<script type="text/javascript" src="${ctx}/js/jquery-1.6.js"></script>
	<script src="${ctx}/js/json.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/laydate/laydate.js"  type="text/javascript" ></script>

	<style type="text/css">
		.tmp-class thead td,.tmp-class tbody td {
			padding: 5px 5px;
		}

		.tmp-class1 {
			text-align: center;
		}

		.tmp-class1 th {
			width: 63px;
			height: 29px;
			border: 1px solid #C4C8CC;
			background: #E9EBED;
			text-align: center;
		}

		.tmp-class1 td {
			width: 63px;
			border: 1px solid #C4C8CC;
			height: 29px;
			text-align: center;
		}
	</style>
	<script type="text/javascript">
        //��Чȫѡ ����Чȫѡ
        function sel_status_all(checked){
        	var check_objz = null;
        	if(checked){
        		check_objz = $("input[name='cbsz'][value='1']");
        	}else{
        		check_objz = $("input[name='cbsz'][value='0']");
        	}
        	for(var i=0; i<check_objz.length;i++){
        		check_objz.get(i).previousElementSibling.checked = true;
            }
            return;
        }
        
      //ȫѡ ��ȡ��
        function sel_all(checked){
            var check_obj = $("input[id='cbs']");
            for(var i=0; i<check_obj.length;i++){
                if(checked){
                    check_obj.get(i).checked = true;
                }else{
                    check_obj.get(i).checked = false;
                }
            }
            return;
        }

        //ɾ��
        function delProductWords() {
            var cc = $('input:checked');
            var str = "";
            for ( var j = 0; j < cc.length; j++) {
                str = str + $('input:checked').get(j).value + ";";
            }
            if (str == "") {
                alert('������ѡ��һ�');
                return;
            }
            if(confirm('ȷ��ɾ����')){
                window.location.href = "${ctx}/ProductWordsCard/delete.action?tags=" + str;
            }
        }
		//ɾ������
        function delSingleProductWords(id) {
            if(id==""){
                alert('��������');
                return;
			}
            if(confirm('ȷ��ɾ����')){
                window.location.href = "${ctx}/ProductWordsCard/delete.action?tags=" + id;
            }
        }


        //����
        function putProductWords() {
            var cc = $('input:checked');
            var str = "";
            for ( var j = 0; j < cc.length; j++) {
                str = str + $('input:checked').get(j).value + ";";
                var v = $('input:checked').get(j).nextElementSibling.value;
                if(v == 0){
                	alert("��Ч��Ʒ���ɷ���");
                	return;
                }
            }
            if (str == "") {
                alert('������ѡ��һ�');
                return;
            }
            if(confirm('ȷ�Ϸ�����')){
                window.location.href = "${ctx}/ProductWordsCard/putForward.action?tags=" + str;
            }
        }


        function tunePage(num) {
            var productWordsName  = document.getElementById("productWordsName").value;
            var isInvalid = document.getElementById("isInvalid").value;
            var qcreateTime = document.getElementById("qcreateTime").value;
            var status = document.getElementById("status").value;
            var page_size = document.getElementById("page_size").value;

            var url = '${ctx}/ProductWordsCard/list.action?' + 'page_index=' + num;

            if(null != productWordsName && '' != productWordsName){
                url += '&productWordsName=' + encodeURI(encodeURI(productWordsName));
            }
            if(null != isInvalid && '' != isInvalid){
                url += '&isInvalid=' + encodeURIComponent(encodeURIComponent(isInvalid));
            }
            if(qcreateTime != null && qcreateTime != "" )
            {
                url += "&qcreateTime=" + qcreateTime;
            }
            if(status != null && status != "" )
            {
                url += "&status=" + status;
            }
            if(page_size != null && page_size != "" )
            {
                url += "&page_size=" + page_size;
            }
            window.location = url;
        }

        function pageChange() {
            var num=1;
            var productWordsName  = document.getElementById("productWordsName").value;
            var isInvalid = document.getElementById("isInvalid").value;
            var qcreateTime = document.getElementById("qcreateTime").value;
            var status = document.getElementById("status").value;
            var page_size = document.getElementById("page_size").value;

            var url = "${ctx}/ProductWordsCard/list.action?pageNumber=1&page_size="+page_size;

            if(null != productWordsName && '' != productWordsName){
                url += '&productWordsName=' + encodeURI(encodeURI(productWordsName));
            }
            if(null != isInvalid && '' != isInvalid){
                url += '&isInvalid=' + encodeURIComponent(encodeURIComponent(isInvalid));
            }
            if(qcreateTime != null && qcreateTime != "" )
            {
                url += "&qcreateTime=" + qcreateTime;
            }
            if(status != null && status != "" )
            {
                url += "&status=" + status;
            }
            window.location = url;
        }






        //���س�ʵ������
        document.onkeydown=function(event){
            e = event ? event :(window.event ? window.event : null);
            if(e.keyCode==13){
                document.getElementById("f1").submit();
            }
        }

        //У�������Ƿ�Ϊ����
        function numValidate(num){
            var reg = /^\d+$/;
            if(null != num && "" != num){
                if(!reg.test(num)){
                    alert(num + "�������֣����������֣�")
                    return false;
                }
            }
            return true;
        }
        //�Ƚ�����ֵ��С
        function compareNum(num1,num2){
            if(null != num1 && num2 != null && "" != num1 && "" != num2 && (num1 > num2)){
                alert(num2 + " ֵ���� " + num1 + "  ���������룡");
                return false;
            }
            return true;
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
            window.location = "./out.action?minId="+minId+"&maxId="+maxId;
        }
        
        function putForward(id) {
            alert(id);
            var url = '${ctx}/ProductWordsCard/putForward.action?tags=' + id;
            alert(url);
            window.location = url;
        }
        

        window.onload = function(){
        	var d = $("input[name='cbszp'][value='0']");
        	for(var i=0; i<d.length;i++){
        		d.get(i).previousElementSibling.disabled = "disabled";
            }
        }
	</script>
</head>
<body>
<form action="${ctx}/ProductWordsCard/list.action" method="post" id="f1">
	<div class="mod-1">
		<div class="hd">
			<h3>��Ʒ�ʹ���</h3>
		</div>
		<div class="bd clearfix">
			<div style="margin-bottom: 10px;" class="container-1">
				&nbsp;&nbsp;��Ʒ�����ƣ�
				<input type="text" class="txt-5" id="productWordsName" name="productWordsName" value="${productWordsName}"/>
				&nbsp;&nbsp;�Ƿ���Ч��
				<s:select id="status" name="status" list="#{3:'ȫ��',1:'��Ч',0:'��Ч'}" />
				&nbsp;&nbsp; &nbsp;
				����ʱ�䣺
				<input class="laydate-icon" name="qcreateTime" id="qcreateTime" value="${param.qcreateTime}" onclick="laydate()" readonly="readonly">
				&nbsp; &nbsp;&nbsp;
				״̬��
				<s:select id="isInvalid" name="isInvalid" list="#{3:'ȫ��',0:'δ����',1:'�ѷ���'}" />
				<input type="submit" value="����" />
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				<span>��Ʒ����Ч������
					<em style="color:#0d14ff;">${isEnableCount}</em>
				</span>
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				<span>��Ʒ����Ч������
					<em style="color:#ff000a;">${notEnableCount}</em>
				</span>

			</div>
			<div class="container-1">

				<table style="width: 100%;">
					<tbody>
					<tr>
						<td style="height: 25px; padding: 10px 0; vertical-align: middle;">
							<input type="button" value="ȫѡ" onclick="sel_all('true')" />
							
							<input type="button" value="��Чȫѡ" onclick="sel_status_all(true)" />
							
							<input type="button" value="��Чȫѡ" onclick="sel_status_all(false)" />

							<input type="button" value="ɾ��" onclick="delProductWords()"/>

							<input type="button" value="����" onclick="putProductWords()"/>

							<input type="button" value="ȡ��" onclick="sel_all(false)" />
							<span id="importSpan" style="margin-left: 150ex">
										����excel�ļ���ID��<input type="text" size="10" id="minId" onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');"/>�� <input type="text" size="10" id="maxId" onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');"/>
										<input type="button" value="����" onclick="outFile()" />
									</span>
						</td>
					</tr>
					</tbody>
				</table>
				<table class="tb-zebra tmp-class" style="width: 100%;">
					<colgroup>
						<col style="width: 6%;" />
						<col style="width: 6%;" />
						<col style="width: 14%;" />
						<col style="width: 12%;" />
						<col style="width: 6%;" />
						<col style="width: 8%;" />
						<col style="width: 8%;" />
						<col style="width: 5%;" />
						<col style="width: 8%;" />
						<col style="width: 6%;" />
						<col style="width: 6%;" />
						<col style="width: 15%;" />
					</colgroup>
					<thead>
					<tr>
						<td style="line-height: 1;"></td>
						<td style="line-height: 1;">���</td>
						<td style="line-height: 1;">��Ʒ��</td>
						<td style="line-height: 1;">�ʸ�</td>
						<td style="line-height: 1;">������</td>
						<td style="line-height: 1;">���/�޸�ʱ��</td>
						<td style="line-height: 1;">�Ƿ���Ч</td>
						<td style="line-height: 1;">״̬</td>
						<td style="line-height: 1;">�Ƿ񲹴�</td>
						<td style="line-height: 1;">�����������</td>
						<td style="line-height: 1;">վ��</td>
						<td style="line-height: 1;">����</td>
					</tr>
					</thead>
					<tbody>
					<s:if test="productWordsCardList!= null">
						<s:iterator value="productWordsCardList"  status="st">
							<tr>
								<td><label> <input type="checkbox" name="cbs"
												   id="cbs" value="<s:property value='id' />" />
								            <input type="hidden" name="cbsz"
												    value="<s:property value='isEnable' />" />
												    </label></td>
								<td><s:property value='id' /></td>
								<td ><s:property value='productWordsName' /></td>
								<td ><s:property value='productWordsBase' /></td>
								<td ><s:property value='creator' /></td>
								<td><s:date name="createDate" format="yyyy.MM.dd" />/<s:date name="updateDate" format="yyyy.MM.dd" /></td>
								<td>${isEnable == 1 ?'��Ч':'��Ч' }</td>
								<td>${isInvalid == 1 ?'�ѷ���':'δ����' }</td>
								<td>${search_status == 1 ?'ԭ��':'����' }</td>
								<td>${search_amount}</td>
								<td >����</td>
								<td>
									<a href="${ctx}/ProductWordsCard/edit.action?id=<s:property value='id'/>">�༭</a>
									&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;
									<%--<a href="${ctx}/ProductWordsCard/delete.action?tags=<s:property value='id'/>">ɾ��</a>--%>
									<a href="#" onclick="delSingleProductWords(<s:property value='id'/>)">ɾ��</a>
									&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;
									
									<button onclick="putForward(<s:property value='id'/>)">����</button>
									<input type="hidden" name="cbszp"
												    value="<s:property value='isEnable' />" />
												    </label></td>
								</td>
							</tr>
						</s:iterator>
					</s:if>
					</tbody>
				</table>
				<table width="100%">
					<tfoot>
					<tr>

						<td style="border: 0 none;">
							<div class="numpage-box">
								<div class="numpage">
									<coo8:page name="productWordsCardList" style="js" systemId="1" />

								</div>
							</div></td>
						<td><s:select style="margin-top:-30px;" id="page_size" name="page_size" list="#{10:'10/ҳ',100:'100/ҳ',500:'500/ҳ',1000:'1000/ҳ'}" onchange="pageChange()"/></td>
					</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</form>

</body>
</html>