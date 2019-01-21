<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
	<title>���»���ϵͳ</title>
	<link rel="stylesheet" href="${ctx}/styles/cui.css" />
	<script type="text/javascript" src="${ctx}/js/jquery-1.6.js"></script>
	<script src="${ctx}/js/json.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/laydate/laydate.js"  type="text/javascript" ></script>
	<script type="text/javascript">
        $(document).ready(function(){
            // lay('.date-control').each(function(){
            //     laydate.render({
            //         elem: this
            //         ,trigger: 'click'
            //     });
            // });
            laydate.render({
                elem: '#createTime',
				lang:'en',
				trigger: 'click'
            });
            <!--�첽port-->
            $.ajax({
                url: "${ctx}/ExpenditureOffLine/portList_ajax.action",
                type: "get",
                dataType:"json",
                async:true,
                cache:true,
                success:function(data){
                    $(".portIds option[value!='0']").remove();
                    for (var i = 0; i < data.length; i++) {
                        var $option = $("<option></option>");
                        $option.attr("value", data[i].id);
                        $option.text(data[i].portName);
                        $(".portIds").append($option);
                    }
                }
            })
            var show_count = 20;   //Ҫ��ʾ������
            var count = 1;    //�����Ŀ�ʼֵ�����������ID
            $("#btn_addtr").click(function () {
                var length = $("#dynamicTable tbody tr").length;
                /* alert(length); */
                //���ʱ�������ǰ������С�ڵ�������������
                if (length < show_count) {
                    $("#dynamicTable_c tbody tr").clone().appendTo("#dynamicTable tbody");   //�ڱ��������һ��
                    changeIndex();//�����к�
                }

                // laydate.render({
                // 	elem: ('.laydate-icon')
                // 	,trigger: 'click'
                // });
                $('#dynamicTable .date-control').each(function(){
                    laydate.render({
                        elem: this,
                        lang:'en',
						trigger: 'click'
                    });
                })

            });

            var ids = document.getElementById("id").value;
            var selectPortIds= document.getElementById("selectPortIds").value;
            var selectStairIds = document.getElementById("selectStairIds").value;
            var selectSecondIds = document.getElementById("selectSecondIds").value;
            var selectThirdIds = document.getElementById("selectThirdIds").value;

            $("#stairChanId").change(function () {
                var stairIds = document.getElementById("stairChanId").value;
                if(stairIds==0){
                    $("#secondChanId option[value!='0']").remove();
                    $("#thirdChanId option[value!='0']").remove();
                }else{
                    $.ajax({
                        url: "${ctx}/ExpenditureOffLine/secondList_ajax.action?selectStairIds="+stairIds,
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
                        url: "${ctx}/ExpenditureOffLine/thirdList_ajax.action?selectStairIds="+stairIds+"&selectSecondIds="+secondChanIds,
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
        function changeIndex() {
            var i = 1;
            $("#dynamicTable tbody tr").each(function () { //ѭ��tab tbody�µ�tr
                $(this).find("select[name='portIds']").prop("id","portIds"+i);
                $(this).find("select[name='stairChanIds']").prop("id","stairChanIds"+i);
                $(this).find("select[name='secondChanIds']").prop("id","secondChanIds"+i);
                $(this).find("select[name='thirdChanIds']").prop("id","thirdChanIds"+i);
                $(this).find("input[name='createTime']").prop("id","createTime"+i);
                $(this).find("input[name='uvs']").prop("id","uvIds"+i);
                $(this).find("input[name='sales']").prop("id","saleIds"+i);
                $(this).find("input[name='orderNums']").prop("id","orderNumIds"+i);
                $(this).find("input[name='expenditures']").prop("id","expenditureIds"+i);
                $(this).find("input[name='noi']").val(i++);//�����к�

            });
        }

        function deltr(opp) {
            var length = $("#dynamicTable tbody tr").length;
            console.log(length);
            if (length <= 1) {
                alert("���ٱ���һ��");
            } else {
                $(opp).parent().parent().remove();//�Ƴ���ǰ��
                changeIndex();
            }
        }
        <!--�˿�����1��-->
        function selectPort(port) {
            var portIds = port.value;
            var strId =$(port).attr("id");
            var rowNum =strId.charAt(strId.length-1);
            var stairChanIds = "#stairChanIds"+rowNum;
            var secondChanIds ="#secondChanIds"+rowNum+" option[value!='0']";
            var thirdChanIds ="#thirdChanIds"+rowNum+" option[value!='0']";
            console.log(secondChanIds);
            $(stairChanIds+" option[value!='0']").remove();
            $(secondChanIds).remove();
            $(thirdChanIds).remove();
            if(portIds!=0){
                $.ajax({
                    url: "${ctx}/ExpenditureOffLine/stairList_ajax.action?selectPortIds="+portIds,
                    type: "get",
                    dataType:"json",
                    success:function(data){
                        for (var i = 0; i < data.length; i++) {
                            var $option = $("<option></option>");
                            $option.attr("value", data[i].id);
                            $option.text(data[i].stairName);
                            $(stairChanIds).append($option);
                        }
                    }
                })
            }

        };
        <!--һ����������-->
        function selectStair(stair) {
            var stairIds = stair.value;
            var strId =$(stair).attr("id");
            var rowNum =strId.charAt(strId.length-1);
            var secondChanIds ="#secondChanIds"+rowNum;
            var secondRemove ="#secondChanIds"+rowNum+" option[value!='0']";
            var thirdChanIds ="#thirdChanIds"+rowNum+" option[value!='0']";
            $(secondRemove).remove();
            $(thirdChanIds).remove();
            if(stairIds!=0){
                $.ajax({
                    url: "${ctx}/ExpenditureOffLine/secondList_ajax.action?selectStairIds="+stairIds,
                    type: "get",
                    dataType:"json",
                    success:function(data){
                        for (var i = 0; i < data.length; i++) {
                            var $option = $("<option></option>");
                            $option.attr("value", data[i].id);
                            $option.text(data[i].secondName);
                            $(secondChanIds).append($option);
                        }
                    }
                })
            }
        };
        <!--������������-->
        function selectSecond(second) {
            var secondIds = second.value;
            //��ȡ��̬id
            var strId =$(second).attr("id");
            var rowNum =strId.charAt(strId.length-1);
            var thirdChanIds = "#thirdChanIds"+rowNum;
            var thirdRemove ="#thirdChanIds"+rowNum+" option[value!='0']";
            $(thirdRemove).remove();
            if(secondIds !=0){
                $.ajax({
                    url: "${ctx}/ExpenditureOffLine/thirdList_ajax.action?selectSecondIds="+secondIds,
                    type: "get",
                    dataType:"json",
                    success:function(data){
                        for (var i = 0; i < data.length; i++) {
                            var $option = $("<option></option>");
                            $option.attr("value", data[i].id);
                            $option.text(data[i].thirdName);
                            $(thirdChanIds).append($option);
                        }
                    }
                })
            }
        };

        function getBack() {
            window.history.back();
        }

	</script>
</head>
<body>

<form action="${ctx}/ExpenditureOffLine/add_Expenditure.action" method="post" id="f1" onsubmit="return verify()">
	<div class="mod-1">
		<div class="hd">
			<h3>������»���</h3>
			<h3><a href="#" onclick="getBack()">������һ��</a></h3>
		</div>
		<div class="bd clearfix">
			<div style="margin-bottom: 10px;" class="container-1">
				<input type="button" id="btn_addtr" value="����" />

			</div>
			<div class="container-1">
				<table class="tb-zebra tmp-class" id="dynamicTable" style="width: 100%;">
					<colgroup>
						<col style="width: 5%;" />
						<col style="width: 8%;" />
						<col style="width: 5%;" />
						<col style="width: 8%;" />
						<col style="width: 8%;" />
						<col style="width: 10%;" />
						<col style="width: 8%;" />
						<col style="width: 8%;" />
						<col style="width: 8%;" />
						<col style="width: 8%;" />
						<col style="width: 6%;" />
					</colgroup>
					<thead>
					<tr>
						<td style="line-height: 1;">���</td>
						<td style="line-height: 1;">����ʱ��</td>
						<td style="line-height: 1;">�˿�</td>
						<td style="line-height: 1;">һ������</td>
						<td style="line-height: 1;">��������</td>
						<td style="line-height: 1;">��������</td>
						<td style="line-height: 1;">UV</td>
						<td style="line-height: 1;">������</td>
						<td style="line-height: 1;">���۶�</td>
						<td style="line-height: 1;">����</td>
						<td style="line-height: 1;">����</td>
					</tr>
					</thead>
					<tbody>
					<tr>
						<td><input type="text" name="noi" size="2" value="1" /></td>
						<td ><input class="date-control" name="createTime" id="createTime" value="${param.qcreateTime}" readonly="readonly" style="width: 80px" /></td>
						<td > <select id="portIds1"  class="portIds" name="portIds" onchange="selectPort(this)" > <option value="0">��</option></select></td>
						<td > <select id="stairChanIds1"  class="stairChanIds" name="stairChanIds" onchange="selectStair(this)" ><option value="0">��</option></select></td>
						<td ><select id="secondChanIds1"  class="secondChanIds" name="secondChanIds" onchange="selectSecond(this)" ><option value="0">��</option></select></td>
						<td ><select id="thirdChanIds1" class="thirdChanIds" name="thirdChanIds"  > <option value="0">��</option> </select></td>
						<td ><input id="uvIds1" type="text" name="uvs" value="0" onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');" style="width: 50px"/></td>
						<td><input id="orderNumIds1" type="text" name="orderNums" value="0" onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');" style="width: 50px"/></td>
						<td><input id="saleIds1" type="text" name="sales" value="0"  onkeyup="this.value=this.value.replace(/[^0-9.]/g,'');" style="width: 50px"/></td>
						<td><input id="expenditureIds1" type="text" name="expenditures"  onkeyup="this.value=this.value.replace(/[^0-9.]/g,'');" style="width: 80px"/></td>
						<td><input type="button" onclick="deltr(this)" value="ɾ��"/></td>
					</tr>
					</tbody>
				</table>
				<table width="100%">
					<tfoot>
					<tr>
						<td style="border: 0 none;">
						</td>
					</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
	<input type="submit" value="��ӻ���" />

</form>

<table id="dynamicTable_c" style="display: none">
	<tbody>
	<tr>
		<td><input type="text" name="noi" size="2" value="1" /></td>
		<td ><input class="date-control" name="createTime" value="${param.createTime}" readonly="readonly" style="width: 80px"/></td>
		<td > <select  class="portIds" name="portIds" onchange="selectPort(this)"> <option value="0">��</option></select></td>
		<td > <select class="stairChanIds" name="stairChanIds" onchange="selectStair(this)" ><option value="0">��</option></select></td>
		<td ><select class="secondChanIds" name="secondChanIds" onchange="selectSecond(this)"><option value="0">��</option></select></td>
		<td ><select class="thirdChanIds" name="thirdChanIds"  > <option value="0">��</option> </select></td>
		<td ><input type="text" name="uvs" value="0" onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');" style="width: 50px"/></td>
		<td><input type="text" name="orderNums" value="0" onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');" style="width: 50px"/></td>
		<td><input type="text" name="sales" value="0" onkeyup="this.value=this.value.replace(/[^0-9.]/g,'');" style="width: 50px"/></td>
		<td><input type="text" name="expenditures" onkeyup="this.value=this.value.replace(/[^0-9.]/g,'');" style="width: 80px"/></td>
		<td><input type="button" onclick="deltr(this)" value="ɾ��"/></td>
	</tr>
	</tbody>
</table>
</body>
<script type="text/javascript">
    function verify() {
        var flag = false;
        var i =1;
        $("#dynamicTable tbody tr").each(function () {
            var ids ="#portIds"+i+" option:selected";
            var stairIds ="#stairChanIds"+i+" option:selected";
            /*var uvIds ="#uvIds"+i;
            var orderNumIds ="#orderNumIds"+i;
            var saleIds ="#saleIds"+i;*/
            var expenditureIds ="#expenditureIds"+i;
            var portVal =$(ids) .val();
            var stairVal =$(stairIds) .val();
            /*		var uvVal = $(uvIds) .val();
                    var orderNumVal = $(orderNumIds) .val();
                    var saleVal = $(saleIds) .val();*/
            var expenditureVal = $(expenditureIds) .val();
            if(portVal==0){
                flag = false;
                alert("��ѡ��˿�on line"+i+"!");
                return false;
            }
            if(stairVal==0){
                flag =false;
                alert("��ѡ������on line"+i+"!");
                return false;
            }
            /*if(uvVal ==''){
                flag =false;
                alert("����д UV on line"+i+"!");
                 return false;
            }
            if(orderNumVal ==''){
                flag =false;
                alert("����д ������ on line"+i+"!");
                 return false;
            }
            if(saleVal ==''){
                flag =false;
            alert("����д ���۶� on line"+i+"!");
                 return false;
            }*/
            if(expenditureVal ==''){
                flag =false;
                alert("����д ���� on line"+i+"!");
                return false;
            }
            i++;
            flag =true;
        });
        if(!flag){
            return false;
        }
    }
</script>

</html>