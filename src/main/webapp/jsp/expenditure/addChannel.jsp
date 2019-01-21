<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
<title>���ϻ���ϵͳ</title>
<link rel="stylesheet" href="${ctx}/styles/cui.css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.6.js"></script>
<script src="${ctx}/js/json.js" type="text/javascript"></script>
	<script src="${ctx}/js/laydate/laydate.js"  type="text/javascript" ></script>
<%-- <script type="text/javascript" src="${ctx}/js/zDialog.js"></script> --%>
<style type="text/css">
input {
	background-color: expression(( this.readOnly && this.readOnly == true)?
		 "#efefef":"");
}

input[readonly] {
	background-color: #efefef;
}
</style>
<script type="text/javascript">
	$(document)	.ready(function() {
		 $.ajax({
	            url: "${ctx}/Expenditure/portList_ajax.action",
	            type: "post",	
	            dataType:"json", 
	       		async:true,  
	      	 	 cache:true,
	            success:function(data){
	             $("#portId2 option[value!='0']").remove(); 
	                for (var i = 0; i < data.length; i++) { 
	                    var $option = $("<option></option>"); 
	                    $option.attr("value", data[i].id); 
	                    $option.text(data[i].portName); 
	                    $("#portId2").append($option); 
	                } 
	            }
	       }) 

});

	//����������ƶ˿�����һ�������¼�
	function selectStairChange() { 
						var portIds = document.getElementById("portId2").value;
						$("#newStair").hide();
						$("#newSecond").hide();
						$("#newThird").hide();
						$("#stairChanId2 option[value!='']").remove();
						$("#stairChanId2").show();
						if (portIds > 0) {
							$.ajax({
										url : "${ctx}/Expenditure/stairList_ajax.action?selectPortIds="+ portIds,
										type : "post",
										dataType : "json",
										success : function(data) {
											$("#secondChanId2 option[value!='']").remove();
											for (var i = 0; i < data.length; i++) {
												var $option = $("<option></option>");
												$option.attr("value",data[i].id);
												$option.text(data[i].stairName);
												$("#stairChanId2").append($option);
											}
											$("#stairChanId2").append("<option value='0' >���</option>");
										}
									})
							$("#stairChanId2").show();
							$("#secondChanId2").show();
							$("#thirdChanId2").show();
						}
					};
	//�����������һ�������������������¼�
	function selectSecondChange(e) {
		e.stopPropagation(); 
		 e.preventDefault();
		var stairIds = document.getElementById("stairChanId2").value;
		$("#newSecond").hide();
		$("#secondChanId2").show();
		$("#newStair").val("");
		$("#newSecond").val("");
		$("#newThird").val("");
	
		if (stairIds == 0) {
			$("#secondChanId2 option[value!='']").remove();
			addStairOptionClick()
			$("#secondChanId2").append("<option value='0' >���</option>");
		} else {
			$("#newStair").hide();
			$.ajax({
						url : "${ctx}/Expenditure/secondList_ajax.action?selectStairIds="+ stairIds,
						type : "post",
						dataType : "json",
						success : function(data) {
							$("#secondChanId2 option[value!='']").remove();
							for (var i = 0; i < data.length; i++) {
								var $option = $("<option></option>");
								$option.attr("value", data[i].id);
								$option.text(data[i].secondName);
								$("#secondChanId2").append($option);
							}
							$("#secondChanId2").append("<option value='0' onclick='addSecondOptionClick(this.value)'>���</option>");
						}
					})
		}
	};

	function selectThirdChange() {
		var secondIds = document.getElementById("secondChanId2").value;
		$("#newSecond").val("");
		$("#newThird").val("");
		secondIds == 0 ? addSecondOptionClick() : ""
	
		
	}
	function addStairOptionClick() {
		
		var port =$("#portId2").val();
		$("#stairChanId2").hide();
		$("#newStair").show();
	}
	function addSecondOptionClick(value) {
		$("#secondChanId2").show();
		$("#newSecond").hide();
		var stairIdC =$("#stairChanId2").val();
		var stair =$("#newStair").val();
		if(stairIdC=='' ){
			alert("��ѡ��һ������!");
			return;
		}else if(stairIdC ==0 && stair ==''){
			alert("����дһ������!");
			return;
		}else{
			$("#secondChanId2").hide();
			$("#newSecond").show();
		}
	}
	function addThirdOptionClick(value) {
		var stairIdC =$("#stairChanId2").val();
		var stair =$("#newStair").val();
		var secondIdC =$("#secondChanId2").val();
		var second =$("#newSecond").val();
		if(stairIdC=='' ){
			alert("��ѡ��һ������!");
			return;
		}else if(stairIdC ==0 && stair ==''){
			alert("����дһ������!");
			return;
		}
		if(secondIdC=='' ){
			alert("��ѡ���������!");
			return;
		}else if(secondIdC ==0 && second ==''){
			alert("����д��������!");
			return;
		}
		$("#newThird").show();
		$("#thirdChanId2").hide();
	}
	function unOptionClick(value) {
		$("#newSecond").hide();
	}
	function keypressNewStair(value) {
		if (value != null && value.trim() != "") {
			$("#selectSecondSpan").show();
		} else {
			$("#selectSecondSpan").hide();
		}
	}
	function keypressNewSecond(value) {
		if (value != null && value.trim() != "") {
			$("#selectThirdSpan").show();
		} else {
			$("#selectThirdSpan").hide();
		}
	};
    function getBack() {
        window.history.back();
    }
</script>
</head>
<body>
	<form action="${ctx}/Expenditure/addChannel.action" onsubmit="return verify()" method="post">
		<div class="mod-1">
			<div class="hd">
				<h3>�����������</h3>
				<h3><a href="#" onclick="getBack()">������һ��</a></h3>
			</div>
			<div class="bd clearfix">
				<div style="margin-bottom: 10px;" class="container-1">
				</div>
					<div class="container-1">
						<table style="width: 100%;">
							<tbody>
								<tr>
								</tr>
							</tbody>
						</table>
						<table class="tb-zebra tmp-class" style="width: 100%;">
							<colgroup>
								<col style="width: 15%;" />
								<col style="width: 15%;" />
								<col style="width: 15%;" />
								<col style="width: 15%;" />
								<col style="width: 15%;" />
								<col style="width: 15%;" />
							</colgroup>
							<thead>
								<tr>
									<td style="line-height: 1;">�˿�:</td>
									<td style="line-height: 1;">һ������</td>
									<td style="line-height: 1;">��������</td>
									<td style="line-height: 1;">��������</td>
									<td style="line-height: 1;">����</td>
								</tr>
							</thead>
							<tbody>
							<tr>
									<td ><select  id="portId2" name="newPortId" onchange="selectStairChange()"><option value="0">��ѡ��</option></select></td>
									<td><select id="stairChanId2" name="newStairId" onchange="selectSecondChange(event)"><option value="">��ѡ��</option></select>
											<input type="text"  style="display:none;" id="newStair" name="newStair"  onkeyup="keypressNewStair(this.value=this.value.replace(/[\-\_\,\.\!\|\~\`\(\)\#\@\%\-\+\=\/\'\$\%\^\&\*\{\}\:\;\L\>\?]/g,''))"/></td>
									<td><select id="secondChanId2" name="newSecondId" onchange="selectThirdChange()"><option value="" onclick='unOptionClick(this.value)'>��ѡ��</option></select>
											<input type="text"  id="newSecond" name="newSecond" style="display: none;" onkeyup="keypressNewSecond(this.value=this.value.replace(/[\-\_\,\.\!\|\~\`\(\)\#\@\%\-\+\=\/\'\$\%\^\&\*\{\}\:\;\L\>\?]/g,''))"/>
									</td>
									<td><input type="button" id="thirdChanId2" value="���" onclick='addThirdOptionClick(this.value);'/>
											<input style="display:none;" type="text" id="newThird" name="newThird" onkeyup="this.value=this.value.replace(/[\-\_\,\.\!\|\~\`\(\)\#\@\%\-\+\=\'\$\%\^\&\*\{\}\:\;\L\>\?]/g,'')" /></td>
									<td><input type="submit" value="�ύ" /> <input type="reset" value="����" /></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
	</form>
</body>
<script type="text/javascript">
	function verify() {
		var stairIdC =$("#stairChanId2").val();
		var port =$("#portId2").val();
		var stair =$("#newStair").val();
		var secondIdC =$("#secondChanId2").val();
		var second =$("#newSecond").val();
		var third =$("#newThird").val();
		if(port ==0){
			alert("��ѡ��˿� !");
			return false;
		}
		if (stair==''&& stairIdC ==0) {
			alert("��ѡ��|���һ������ !");
			return false;
		}
		
		if(stairIdC >0 &&secondIdC==0 && second ==''){
			alert("��ѡ��|��Ӷ������� !");
			return false;
		}
		if(secondIdC >0 && third ==''){
			alert("������������� !");
			return false;
		}
	}
</script>
</html>