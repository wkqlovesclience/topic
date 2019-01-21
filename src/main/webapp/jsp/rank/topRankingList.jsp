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
    
    //发布排行榜首页
    function publishRankingHomePage(){
        if(confirm('确认发布？')){
            $.post('${ctx}/SingleTopRanking/publishRankingHomePage.action',
                    function(msg){  
                        if(msg == 'Y'){
                            alert("发布排行榜首页成功,请访问：http://www.gome.com.cn/top10/");
                        }
                    }
            );
        }
    }
    //手机版发布排行榜首页
    function wapPublishRankingHomePage(){
        if(confirm('确认发布？')){
            $.post('${ctx}/SingleTopRanking/wapPublishRankingHomePage.action',
                    function(msg){  
                        if(msg == 'Y'){
                        	alert("手机版发布排行榜首页成功,请访问：http://m.gome.com.cn/top10/");
                        }
                    }
            );
        }
    }
    //修改
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
                    alert("获取信息失败");
                    return false;
                }
                eval("var ranking = "+data);
                $("#rankingName").val(ranking.name);
                $("#rankingSortNum").val(ranking.sort);
                $("#operateType").val("0");
                $("#operateInput").val("修改");
                $("#rankingId").val(ranking.id);
                $("#floatBox").show();
            }
        });
    }
    //删除
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
                    alert("存在子元素，不可删除");
                    flag = 0;
                }
                else if(data == "N"){
                    if(confirm("确认删除吗")){
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
                    alert("操作失败，数据库异常错误");
                }
                else if(data == "Y"){
                    alert("删除成功");
                    window.location.reload();
                }
                else{
                    alert("你还未登陆或者长时间未操作");
                }
            }
        });
    }
    //修改状态
    function changeState(paramId,stateValue){
        $.ajax({
            type:"POST",
            url:"${ctx}/SingleTopRanking/changeState.action",
            data:{id:paramId,isShow:stateValue},
            cache:false,
            async:false,
            success:function(data){
                if(data == 'N'){
                    alert("操作失败，数据库异常错误");
                }
                else if(data == 'Y'){
                    alert("操作成功");
                    window.location.reload();
                }
                else{
                    alert("你还未登陆或者长时间未操作");
                }
            }
        });
    }
    function popDiv(){
        //计算出弹出框的位移
       var docHeight = $(document).height();
        var docWidth = $(document).width();
        $("#floatBox").css("margin-top",(docHeight-200)/2);
        $("#floatBox").css("margin-left",(docWidth-300)/2);
        
        $("#rankingName").val("");
        $("#rankingSortNum").val("");
        $("#operateType").val("1");
        $("#operateInput").val("添加");
        $("#rankingId").val("");
        
        $("#floatBox").show();
    }
    function add(){
        var name = $("#rankingName").val();
        var sortNum = $("#rankingSortNum").val();
        var operateType = $("#operateType").val();
        var rankingId = $("#rankingId").val();
        if($.trim(name)==''){
            alert("请输入标题名称");
            $("#rankingName").focus();
            return false;
        }
        if($.trim(sortNum)==''){
            alert("请输入排序值");
            $("#rankingSortNum").focus();
            return false;
        }
        if(!/^\d+$/.test(sortNum)){
            alert("请输入正整数格式的排序值");
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
                    alert("操作失败，数据库异常错误");
                }
                else if(/^\d+$/.test(data)){
                    alert("操作成功");
                    window.location.reload();
                }
                else{
                    alert("你还未登陆或者长时间未操作");
                }
            }
        });
    }
    function cureErrorQueueData(){
        $.post("${ctx}/Category/updatequeue.action",function (){
            alert("成功修正发布队列未执行成功的数据");
        });
    }
    </script>
    <title>热门排行榜管理</title>
  </head>
  
  <body>
  <div class="fixed" id="floatBox">
      <table width="100%" border="0" style="border-spacing: 5px;border-collapse: separate;">
          <tr>
              <td colspan="2">热门排行榜</td>
          </tr>
          <tr>
              <td width="80">标题名称：</td>
              <td><input type="text" id="rankingName" style="width: 210px;"/></td>
          </tr>
          <tr>
              <td>排序大小：</td>
              <td><input type="text" id="rankingSortNum" style="width: 210px;"/></td>
          </tr>
          <tr>
              <td>&nbsp;</td>
              <td align="right">
                  <input type="hidden" value="1" id="operateType"/>
                  <input type="hidden" value="" id="rankingId"/>
                  <input type="button" value="添加" onclick="add()" id="operateInput"/>
                  <input type="button" value="取消" onclick="$('#floatBox').hide()"/>
              </td>
          </tr>
      </table>
  </div>
  <form action="${ctx}/SingleTopRanking/list.action" method="post" id="SingleTopRankingForm">
    <div class="mod-1">
        <div class="hd">
            <h3>热门排行榜管理</h3>
        </div>
        <div class="bd clearfix">
            <div style="font-size: 20px;" class="container-1">
                <table width="100%" border="0">
                    <tr>
                        <td width="40%" align="left">
                            <input type="button" value="发布排行榜首页" onclick="publishRankingHomePage()"  />
                            <c:if test="${unHandleQueueFlag == true }">
                                &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="cureErrorQueueData()" value="修正发布队列数据"/>
                            </c:if>
                        </td>
                        <td width="40%" align="left"><input type="button" value="手机端发布排行榜首页" onclick="wapPublishRankingHomePage()"/></td>
                        <td width="20%" align="right"><input type="button" value="增 加" onclick="popDiv()"/></td>
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
                        <td style="line-height: 1;">序号</td>
                        <td style="line-height: 1;">热门排行榜标题名称</td>
                        <td style="line-height: 1;">操作者/更新者</td>
                        <td style="line-height: 1;">创建时间/修改时间</td>
                        <td style="line-height: 1;">排序值</td>
                        <td style="line-height: 1;">操作</td>
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
                                <a href="javascript:void(0)" onclick="location.href='${ctx}/SingleTopRanking/subList.action?parent=${id}'">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="javascript:modify(${id})" >修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="javascript:deleteRanking(${id})">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                <c:choose>
                                    <c:when test="${isShow == 1 }">
                                        <a href="javascript:changeState(${id},0)">隐藏</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="javascript:changeState(${id},1)">显示</a>
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