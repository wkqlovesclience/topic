<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/commons/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=GBK" />
    <link rel="stylesheet" href="${ctx}/styles/cui.css" />
    <style>
        .fixed{
            position:absolute;
            width:300px;
            height:130px;
            background:#A8A8A8;
            border:1px solid black;
            text-align:center;
            z-index: 9999;
        }
        .batchModifyDiv{
            position:absolute;
            width:500px;
            background:#A8A8A8;
            border:1px solid black;
            text-align:center;
            z-index: 9999;
        }
        .batchModifyItem{
            margin-top: 5px;
            margin-bottom: 5px;
        }
    </style>
    <script src="${ctx}/js/jquery-1.6.js"  type="text/javascript" ></script>
    <script type="text/javascript">
    $().ready(function(){
        $("#floatBox").hide();
        $("#batchModifyDiv").hide();
    });
    
    function clickCheck(){
        var flag = $("#chkSelect").is(":checked");
        if(flag){
            sel_all(true);
        }
        else{
            sel_all(false);
        }
    }
    function sel_all(checked){ 
        var check_obj = $("input[name='checkbox']"); 
        for(var i=0; i<check_obj.length;i++){ 
            if(checked){ 
                check_obj.get(i).checked = true; 
            }
            else{ 
             check_obj.get(i).checked = false; 
            } 
        } 
        return; 
    }
    function popDiv(){
        //������������λ��
       var docHeight = $(document).height();
        var docWidth = $(document).width();
        $("#floatBox").css("margin-top",(docHeight-200)/2);
        $("#floatBox").css("margin-left",(docWidth-300)/2);
        
        $("#rankingName").val("");
        $("#rankingURL").val("");
        $("#rankingSortNum").val("");
        $("#operateType").val("1");
        $("#operateInput").val("���");
        $("#rankingId").val("");
        
        $("#floatBox").show();
    }
    function add(){
        var name = $("#rankingName").val();
        var url = $("#rankingURL").val();
        var sortNum = $("#rankingSortNum").val();
        var operateType = $("#operateType").val();
        var parentId = $("#parentId").val();
        var rankingId = $("#rankingId").val();
        if($.trim(name)==''){
            alert("�������������");
            $("#rankingName").focus();
            return false;
        }
        if($.trim(url)==''){
            alert("������·����ַ");
            $("#rankingURL").focus();
            return false;
        }
        if(!/^https?:\/\//.test(url)){
            alert("��������ȷ��ʽ��URL��ַ");
            $("#rankingURL").focus();
            return false;
        }
        if($.trim(sortNum)==''){
            alert("����������ֵ");
            $("#rankingSortNum").focus();
            return false;
        }
        if(!/^\d+$/.test(sortNum)){
            alert("��������������ʽ������ֵ");
            $("#rankingSortNum").focus();
            return false;
        }
        $.ajax({
            type:"POST",
            url:"${ctx}/SingleTopRanking/add.action",
            data:{"ranking.id":rankingId,"ranking.name":name,"ranking.url":url,"ranking.parent":parentId,"ranking.sort":sortNum,"operateType":operateType,"source":"subList"},
            cache:false,
            async:false,
            success:function(data){
                if(data == -1){
                    alert("����ʧ�ܣ����ݿ��쳣����");
                }
                else if(/^\d+$/.test(data)){
                    alert("�����ɹ�");
                    window.location.reload();
                }
                else{
                    alert("�㻹δ��½���߳�ʱ��δ����");
                }
            }
        });
    }
    //�޸�
    function modifyItem(paramId){
        var docHeight = $(document).height();
        var docWidth = $(document).width();
        $("#floatBox").css("margin-top",(docHeight-200)/2);
        $("#floatBox").css("margin-left",(docWidth-300)/2);
        $("#floatBox").show();
        
        $.ajax({
            type:"POST",
            url:"${ctx}/SingleTopRanking/getRankingInfoById.action",
            data:{id:paramId},
            cache:false,
            async:false,
            success:function(data){
                if(data == 'NoParam' || data=='NoValue'){
                    alert("��ȡ��Ϣʧ��");
                    return false;
                }
                eval("var ranking = "+data);
                $("#rankingName").val(ranking.name);
                $("#rankingURL").val(ranking.url);
                $("#rankingSortNum").val(ranking.sort);
                $("#operateType").val("0");
                $("#operateInput").val("�޸�");
                $("#rankingId").val(ranking.id);
                $("#floatBox").show();
            }
        });
    }
    function deleteItem(paramId){
        $.ajax({
            type:"POST",
            url:"${ctx}/SingleTopRanking/deleteItem.action",
            data:{id:paramId},
            cache:false,
            async:false,
            success:function(data){
                if(data == "N"){
                    alert("����ʧ�ܣ����ݿ��쳣����");
                }
                else if(data == "Y"){
                    alert("ɾ���ɹ�");
                    window.location.reload();
                }
                else{
                    alert("�㻹δ��½���߳�ʱ��δ����");
                }
            }
        });
    }
    function batchDelete(){
        var ids = new Array();
        var boxs = $(":checkbox[name=checkbox]:checked");
        boxs.each(function() {
            ids.push(this.value);
        });
        if(ids.length <= 0)
        {
            alert("��ѡ��Ҫɾ��������");
            return;
        }
        if(window.confirm("ȷ��Ҫɾ����")){
            $.ajax({
                type:"POST",
                url:"${ctx}/SingleTopRanking/batchDelete.action",
                data:{"ids":ids.join(",")},
                cache:false,
                async:false,
                success:function(data){
                    if(data == "N"){
                        alert("����ʧ�ܣ����ݿ��쳣����");
                    }
                    else if(data == "Y"){
                        alert("ɾ���ɹ�");
                        window.location.reload();
                    }
                    else{
                        alert("�㻹δ��½���߳�ʱ��δ����");
                    }
                }
            });
        }
    }
    function checkBatchModifyName(thisDom){
        var curName = thisDom.value;
        if($.trim(curName)==''){
            alert("����Ϊ��,����������");
            thisDom.focus();
            return false;
        }
    }
    function checkBatchModifyURL(thisDom){
        var url = thisDom.value;
        if(!/^https?:\/\//.test(url)){
            alert("URL��ʽ����,����������");
            thisDom.focus();
            return false;
        }
    }
    function checkBatchModifySort(thisDom){
        var sortNum = thisDom.value;
        if(!/^\d+$/.test(sortNum)){
            alert("����ֵ��ʽ����,����������");
            thisDom.focus();
            return false;
        }
    }
    function batchModify(){
        var ids = new Array();
        var boxs = $(":checkbox[name=checkbox]:checked");
        boxs.each(function() {
            ids.push(this.value);
        });
        if(ids.length <= 0)
        {
            alert("��ѡ��Ҫ�޸ĵ�����");
            return;
        }
        var docHeight = $(document).height();
        var docWidth = $(document).width();
        $("#batchModifyDiv").css("margin-top",(docHeight-300)/2);
        $("#batchModifyDiv").css("margin-left",(docWidth-500)/2);
        $("#batchModifyDiv").show();
        
        var totalCount = ids.length;
        var batchModifyDom = $("#batchModifyDivContent");
        batchModifyDom.empty();
        for(i=0;i<totalCount;i++){
            var appendDivInfo = "";
            var curId = ids[i];
            var curName = $("#td_2_"+curId).text().trim();
            var curURL = $("#td_3_"+curId).text().trim();
            var curSort = $("#td_6_"+curId).text().trim();
            appendDivInfo += "<div class='batchModifyItem' id='batchModifyItem_"+curId+"'>";
            appendDivInfo += "���⣺<input type='text' onblur='checkBatchModifyName(this)' id='editName_"+curId+"' value='"+curName+"'/>";
            appendDivInfo += "URL��<input type='text' onblur='checkBatchModifyURL(this)' id='editURL_"+curId+"' value='"+curURL+"'/>";
            appendDivInfo += "����ֵ��<input type='text' onblur='checkBatchModifySort(this)' size='5' id='editSort_"+curId+"' value='"+curSort+"'/>";
            appendDivInfo +="</div>";
            batchModifyDom.append(appendDivInfo);
        }
    }
    function batchUpdate(){
        var $batchModifyItem = $(".batchModifyItem");
        var count = $batchModifyItem.length;
        if(count <= 0){
            return false;
        }
        var sendInfo = "";
        $batchModifyItem.each(function (){
            var divId = $(this).attr("id");
            var realId = divId.split("_")[1];
            sendInfo += realId +","+$("#editName_"+realId).val()+"," + $("#editURL_"+realId).val()+"," + $("#editSort_"+realId).val()+";"
        });
        $.ajax({
            type:"POST",
            url:"${ctx}/SingleTopRanking/batchUpdate.action",
            data:{"sendInfo":sendInfo},
            cache:false,
            async:false,
            success:function(data){
                if(data == "N"){
                    alert("����ʧ�ܣ����ݿ��쳣����");
                }
                else if(data == "Y"){
                    alert("���³ɹ�");
                    window.location.reload();
                }
                else{
                    alert("�㻹δ��½���߳�ʱ��δ����");
                }
            }
        });
    }
    </script>
    <title>${parentName }����</title>
  </head>
  
  <body>
  <div class="fixed" id="floatBox">
      <table width="100%" border="0" style="border-spacing: 5px;border-collapse: separate;">
          <tr>
              <td colspan="2">�������а�</td>
          </tr>
          <tr>
              <td width="80">�������ƣ�</td>
              <td><input type="text" id="rankingName" style="width: 210px;"/></td>
          </tr>
          <tr>
              <td>·����ַ��</td>
              <td><input type="text" id="rankingURL" style="width: 210px;"/></td>
          </tr>
          <tr>
              <td>�����С��</td>
              <td><input type="text" id="rankingSortNum" style="width: 210px;"/></td>
          </tr>
          <tr>
              <td>&nbsp;</td>
              <td align="right">
                  <input type="hidden" value="1" id="operateType"/>
                  <input type="hidden" value="" id="rankingId"/>
                  <input type="button" value="���" onclick="add()" id="operateInput"/>
                  <input type="button" value="ȡ��" onclick="$('#floatBox').hide()"/>
              </td>
          </tr>
      </table>
  </div>
  <div class="batchModifyDiv" id="batchModifyDiv">
    <div style="margin-top: 10px" id="batchModifyDivContent">
    </div>
    <div style="margin-top: 5px;margin-bottom: 5px;">
        <input type="button" value="�޸�" onclick="batchUpdate();"/>
        <input type="button" value="ȡ��" onclick="$('#batchModifyDiv').hide();"/>
    </div>
  </div>
  <form action="${ctx}/SingleTopRanking/list.action" method="post" id="SingleTopRankingForm">
    <div class="mod-1">
        <div class="hd">
            <h3>${parentName }����</h3>
        </div>
        <div class="bd clearfix">
            <div style="font-size: 20px;" align="right" class="container-1">
                <input type="button" onclick="location.href='${ctx }/SingleTopRanking/list.action'" value="�����������а����"/>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" value="����" onclick="popDiv()"/>
                <input type="button" value="�޸�" onclick="batchModify()" />
                <input type="button" value="ɾ��" onclick="batchDelete()" />
                <input type="hidden" value="${parentId }" id="parentId"/>
            </div>
            <div class="container-1">
                <table style="width: 100%;" class="tb-zebra tmp-class" border="0">
                    <colgroup>
                        <col style="width: 5%;" />
                        <col style="width: 7%;" />
                        <col style="width: 15%;" />
                        <col style="width: 25%;" />
                        <col style="width: 12%;"/>
                        <col style="width: 18%;"/>
                        <col style="width: 8%;" />
                        <col style="width: 10%;"/>
                    </colgroup>
                    <thead>
                    <tr>
                        <td style="line-height: 1;"><input type="checkbox" id="chkSelect" vlaue="" onclick="clickCheck()" /></td>
                        <td style="line-height: 1;">���</td>
                        <td style="line-height: 1;">����</td>
                        <td style="line-height: 1;">URL</td>
                        <td style="line-height: 1;">������/�޸���</td>
                        <td style="line-height: 1;">����ʱ��/�޸�ʱ��</td>
                        <td style="line-height: 1;">����ֵ</td>
                        <td style="line-height: 1;">����</td>
                    </tr>
                    </thead>
                    <tbody>
                    <s:if test="rankingList != null">
                        <s:iterator value="rankingList" status="st">
                        <tr style="height:15px;">
                            <td><label><input type="checkbox" name="checkbox" value="${id}" /></label></td>
                            <td>${st.count }</td>
                            <td id="td_2_${id }" >${name }</td>
                            <td id="td_3_${id }" >${url }</td>
                            <td>${creator }/${updator }</td>
                            <td><s:date name="createTime" format="yyyy.MM.dd" /> / <s:date name="updateTime" format="yyyy.MM.dd" /></td>
                            <td id="td_6_${id }" >${sort }</td>
                            <td>
                                <a href="javascript:modifyItem(${id })">�޸�</a>
                                <a href="javascript:deleteItem(${id })">ɾ��</a>
                            </td>
                        </tr>
                        </s:iterator>
                    </s:if>
                    </tbody>
                </table>
            </div>
        </div>
      </div>
    </form>
  </body>
</html>