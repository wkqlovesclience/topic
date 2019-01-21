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
            height:100px;
            background:#A8A8A8;
            border:1px solid black;
            text-align:center;
            z-index: 9999;
        }
    </style>
    <script src="${ctx}/js/jquery-1.6.js"  type="text/javascript" ></script>
    <script type="text/javascript">
    $().ready(function(){
        $("#floatBox").hide();
    });
    
    //�������а���ҳ
    function publishRankingHomePage(){
        if(confirm('ȷ�Ϸ�����')){
            $.post('${ctx}/SingleTopRanking/publishRankingHomePage.action',
                    function(msg){  
                        if(msg == 'Y'){
                            alert("�������а���ҳ�ɹ�,����ʣ�http://www.gome.com.cn/top10/");
                        }
                    }
            );
        }
    }
    //�ֻ��淢�����а���ҳ
    function wapPublishRankingHomePage(){
        if(confirm('ȷ�Ϸ�����')){
            $.post('${ctx}/SingleTopRanking/wapPublishRankingHomePage.action',
                    function(msg){  
                        if(msg == 'Y'){
                        	alert("�ֻ��淢�����а���ҳ�ɹ�,����ʣ�http://m.gome.com.cn/top10/");
                        }
                    }
            );
        }
    }
    //�޸�
    function modify(paramId){
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
                $("#rankingSortNum").val(ranking.sort);
                $("#operateType").val("0");
                $("#operateInput").val("�޸�");
                $("#rankingId").val(ranking.id);
                $("#floatBox").show();
            }
        });
    }
    //ɾ��
    function deleteRanking(paramId){
        var flag = -1;
        $.ajax({
            type:"POST",
            url:"${ctx}/SingleTopRanking/isHasChildren.action",
            data:{id:paramId},
            cache:false,
            async:false,
            success:function(data){
                if(data == "Y"){
                    alert("������Ԫ�أ�����ɾ��");
                    flag = 0;
                }
                else if(data == "N"){
                    if(confirm("ȷ��ɾ����")){
                        flag = 1;
                    }
                }
            }
        });
        if(flag != 1){
            return ;
        }
        $.ajax({
            type:"POST",
            url:"${ctx}/SingleTopRanking/delete.action",
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
    //�޸�״̬
    function changeState(paramId,stateValue){
        $.ajax({
            type:"POST",
            url:"${ctx}/SingleTopRanking/changeState.action",
            data:{id:paramId,isShow:stateValue},
            cache:false,
            async:false,
            success:function(data){
                if(data == 'N'){
                    alert("����ʧ�ܣ����ݿ��쳣����");
                }
                else if(data == 'Y'){
                    alert("�����ɹ�");
                    window.location.reload();
                }
                else{
                    alert("�㻹δ��½���߳�ʱ��δ����");
                }
            }
        });
    }
    function popDiv(){
        //������������λ��
       var docHeight = $(document).height();
        var docWidth = $(document).width();
        $("#floatBox").css("margin-top",(docHeight-200)/2);
        $("#floatBox").css("margin-left",(docWidth-300)/2);
        
        $("#rankingName").val("");
        $("#rankingSortNum").val("");
        $("#operateType").val("1");
        $("#operateInput").val("���");
        $("#rankingId").val("");
        
        $("#floatBox").show();
    }
    function add(){
        var name = $("#rankingName").val();
        var sortNum = $("#rankingSortNum").val();
        var operateType = $("#operateType").val();
        var rankingId = $("#rankingId").val();
        if($.trim(name)==''){
            alert("�������������");
            $("#rankingName").focus();
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
            data:{"ranking.id":rankingId,"ranking.name":name,"ranking.sort":sortNum,"operateType":operateType},
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
    function cureErrorQueueData(){
        $.post("${ctx}/Category/updatequeue.action",function (){
            alert("�ɹ�������������δִ�гɹ�������");
        });
    }
    </script>
    <title>�������а����</title>
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
  <form action="${ctx}/SingleTopRanking/list.action" method="post" id="SingleTopRankingForm">
    <div class="mod-1">
        <div class="hd">
            <h3>�������а����</h3>
        </div>
        <div class="bd clearfix">
            <div style="font-size: 20px;" class="container-1">
                <table width="100%" border="0">
                    <tr>
                        <td width="40%" align="left">
                            <input type="button" value="�������а���ҳ" onclick="publishRankingHomePage()"  />
                            <c:if test="${unHandleQueueFlag == true }">
                                &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="cureErrorQueueData()" value="����������������"/>
                            </c:if>
                        </td>
                        <td width="40%" align="left"><input type="button" value="�ֻ��˷������а���ҳ" onclick="wapPublishRankingHomePage()"/></td>
                        <td width="20%" align="right"><input type="button" value="�� ��" onclick="popDiv()"/></td>
                    </tr>
                </table>
            </div>
            <div class="container-1">
                <table style="width: 100%;" class="tb-zebra tmp-class" border="0">
                    <colgroup>
                        <col style="width: 5%;" />
                        <col style="width: 20%;" />
                        <col style="width: 15%;" />
                        <col style="width: 20%;" />
                        <col style="width: 5%;" />
                        <col style="width: 15%;"/>
                    </colgroup>
                    <thead>
                    <tr>
                        <td style="line-height: 1;">���</td>
                        <td style="line-height: 1;">�������а��������</td>
                        <td style="line-height: 1;">������/������</td>
                        <td style="line-height: 1;">����ʱ��/�޸�ʱ��</td>
                        <td style="line-height: 1;">����ֵ</td>
                        <td style="line-height: 1;">����</td>
                    </tr>
                    </thead>
                    <tbody>
                    <s:if test="rankingList != null">
                        <s:iterator value="rankingList" status="st">
                        <tr style="height:15px;">
                            <td>${st.count}</td>
                            <td>${name }</td>
                            <td>${creator }/${updator }</td>
                            <td><s:date name="createTime" format="yyyy.MM.dd HH:mm:ss" />/<s:date name="updateTime" format="yyyy.MM.dd HH:mm:ss" /></td>
                            <td>${sort }</td>
                            <td>
                                <a href="javascript:void(0)" onclick="location.href='${ctx}/SingleTopRanking/subList.action?parent=${id}'">�鿴</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="javascript:modify(${id})" >�޸�</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="javascript:deleteRanking(${id})">ɾ��</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                <c:choose>
                                    <c:when test="${isShow == 1 }">
                                        <a href="javascript:changeState(${id},0)">����</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="javascript:changeState(${id},1)">��ʾ</a>
                                    </c:otherwise>
                                </c:choose>
                                
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