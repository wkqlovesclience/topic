<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/commons/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=GBK" />
    <link rel="stylesheet" href="${ctx}/styles/cui.css" />
    <script src="${ctx}/js/jquery-1.6.js"  type="text/javascript" ></script>
    <script type="text/javascript">
    function publishTestSingle(){
        var itemId = $("#publishTestId").val();
        var publishTestInfoDom = $("#publishTestInfo");
        itemId = $.trim(itemId);
        if(itemId!=""){
            if(/^\d+$/.test(itemId)){
                $.post("${ctx}/gomeStores/publish.action",{type:1,isAll:false,id:itemId},function (data){
                    if(data.indexOf("success")>=0){
                        var accessURL = data.substr(8);
                        var needHTML = "<a href='"+accessURL+"' target='_blank'>"+accessURL+"</a>";
                        publishTestInfoDom.html(needHTML);
                        publishTestInfoDom.show();
                        alert("发布预览成功");
                    }
                });
            }
            else{
                alert("请输入正确格式的整形Id");
                publishTestInfoDom.html("");
                publishTestInfoDom.hide();
            }
        }
        else{
            alert("请输入门店Id");
        }
    }
    
    function publishTestWapSingle(){
        var itemId = $("#publishTestId").val();
        var publishTestInfoDom = $("#publishTestInfo");
        itemId = $.trim(itemId);
        if(itemId!=""){
            if(/^\d+$/.test(itemId)){
                $.post("${ctx}/gomeStores/wapPublish.action",{type:1,isAll:false,id:itemId},function (data){
                    if(data.indexOf("success")>=0){
                        var accessURL = data.substr(8);
                        var needHTML = "<a href='"+accessURL+"' target='_blank'>"+accessURL+"</a>";
                        publishTestInfoDom.html(needHTML);
                        publishTestInfoDom.show();
                        alert("发布预览成功");
                    }
                });
            }
            else{
                alert("请输入正确格式的整形Id");
                publishTestInfoDom.html("");
                publishTestInfoDom.hide();
            }
        }
        else{
            alert("请输入门店Id");
        }
    }
    
    function publishSingle(){
        var itemId = $("#publishId").val();
        var publishInfoDom = $("#publishInfo");
        itemId = $.trim(itemId);
        if(itemId!=""){
            if(/^\d+$/.test(itemId)){
                $.post("${ctx}/gomeStores/publish.action",{type:0,isAll:false,id:itemId},function (data){
                    if(data.indexOf("success")>=0){
                        var accessURL = data.substr(8);
                        var needHTML = "<a href='"+accessURL+"' target='_blank'>"+accessURL+"</a>";
                        publishInfoDom.html(needHTML);
                        publishInfoDom.show();
                        alert("发布成功");
                    }
                });
            }
            else{
                alert("请输入正确格式的整形Id");
                publishTestInfoDom.html("");
                publishTestInfoDom.hide();
            }
        }
        else{
            alert("请输入门店Id");
        }
    }
    
    function publishWapSingle(){
        var itemId = $("#publishId").val();
        var publishInfoDom = $("#publishInfo");
        itemId = $.trim(itemId);
        if(itemId!=""){
            if(/^\d+$/.test(itemId)){
                $.post("${ctx}/gomeStores/wapPublish.action",{type:0,isAll:false,id:itemId},function (data){
                    if(data.indexOf("success")>=0){
                        var accessURL = data.substr(8);
                        var needHTML = "<a href='"+accessURL+"' target='_blank'>"+accessURL+"</a>";
                        publishInfoDom.html(needHTML);
                        publishInfoDom.show();
                        alert("发布成功");
                    }
                });
            }
            else{
                alert("请输入正确格式的整形Id");
                publishTestInfoDom.html("");
                publishTestInfoDom.hide();
            }
        }
        else{
            alert("请输入门店Id");
        }
    }
    
    function publishTestInterval(){
        var publishTestStartId = $("#publishTestStartId").val();
        publishTestStartId = $.trim(publishTestStartId);
        var publishTestEndId = $("#publishTestEndId").val();
        publishTestEndId = $.trim(publishTestEndId);
        
        if(publishTestStartId!=""){
            if(!/^\d+$/.test(publishTestStartId)){
                alert("请输入整形格式的开始Id");
                return false;
            }
        }
        
        if(publishTestEndId!=""){
            if(!/^\d+$/.test(publishTestEndId)){
                alert("请输入整形格式的结束Id");
                return false;
            }
        }
        
        if(publishTestStartId!="" || publishTestEndId!=""){
            if(window.confirm("确定执行批量区间发布预览吗？如果是，时间比较长，请耐心等待")){
                 $.post("${ctx}/gomeStores/publish.action",{type:1,isAll:false,startId:publishTestStartId,endId:publishTestEndId},function (data){
	                if(data == "success"){
	                    alert("区间发布预览成功");
	                }
	            });
            }
        }
        else{
            alert("请至少输入一个门店Id");
        }
    }
    
    
    function publishTestWapInterval(){
        var publishTestStartId = $("#publishTestStartId").val();
        publishTestStartId = $.trim(publishTestStartId);
        var publishTestEndId = $("#publishTestEndId").val();
        publishTestEndId = $.trim(publishTestEndId);
        
        if(publishTestStartId!=""){
            if(!/^\d+$/.test(publishTestStartId)){
                alert("请输入整形格式的开始Id");
                return false;
            }
        }
        
        if(publishTestEndId!=""){
            if(!/^\d+$/.test(publishTestEndId)){
                alert("请输入整形格式的结束Id");
                return false;
            }
        }
        
        if(publishTestStartId!="" || publishTestEndId!=""){
            if(window.confirm("确定执行批量区间发布预览吗？如果是，时间比较长，请耐心等待")){
                 $.post("${ctx}/gomeStores/wapPublish.action",{type:1,isAll:false,startId:publishTestStartId,endId:publishTestEndId},function (data){
	                if(data == "success"){
	                    alert("区间发布预览成功");
	                }
	            });
            }
        }
        else{
            alert("请至少输入一个门店Id");
        }
    }
    
    function publishInterval(){
        var publishStartId = $("#publishStartId").val();
        publishStartId = $.trim(publishStartId);
        var publishEndId = $("#publishEndId").val();
        publishEndId = $.trim(publishEndId);
        
        if(publishStartId!=""){
            if(!/^\d+$/.test(publishStartId)){
                alert("请输入整形格式的开始Id");
                return false;
            }
        }
        
        if(publishEndId!=""){
            if(!/^\d+$/.test(publishEndId)){
                alert("请输入整形格式的结束Id");
                return false;
            }
        }
        
        if(publishEndId!="" || publishEndId!=""){
            if(window.confirm("确定执行批量区间发布吗？如果是，时间比较长，请耐心等待")){
                $.post("${ctx}/gomeStores/publish.action",{type:0,isAll:false,startId:publishStartId,endId:publishEndId},function (data){
	                if(data == "success"){
	                    alert("区间发布成功");
	                }
	            });
            }
        }
        else{
            alert("请至少输入一个门店Id");
        }
    }
    
    
     function publishWapInterval(){
        var publishStartId = $("#publishStartId").val();
        publishStartId = $.trim(publishStartId);
        var publishEndId = $("#publishEndId").val();
        publishEndId = $.trim(publishEndId);
        
        if(publishStartId!=""){
            if(!/^\d+$/.test(publishStartId)){
                alert("请输入整形格式的开始Id");
                return false;
            }
        }
        
        if(publishEndId!=""){
            if(!/^\d+$/.test(publishEndId)){
                alert("请输入整形格式的结束Id");
                return false;
            }
        }
        
        if(publishEndId!="" || publishEndId!=""){
            if(window.confirm("确定执行批量区间发布吗？如果是，时间比较长，请耐心等待")){
                $.post("${ctx}/gomeStores/wapPublish.action",{type:0,isAll:false,startId:publishStartId,endId:publishEndId},function (data){
	                if(data == "success"){
	                    alert("区间发布成功");
	                }
	            });
            }
        }
        else{
            alert("请至少输入一个门店Id");
        }
    }
    
    
    function publishTestAll(){
        if(window.confirm("确定执行全部发布预览吗？如果是，时间比较长，请耐心等待")){
            $.post("${ctx}/gomeStores/publish.action",{type:1,isAll:true},function (data){
	            if(data == "success"){
	                alert("全部发布预览成功");
	            }
	            else{
	                alert("异常错误，退出重新登录或者联系开发人员");
	            }
	        });
        }
    }
    function publishAll(){
        if(window.confirm("确定执行全部发布吗？如果是，时间比较长，请耐心等待")){
            $.post("${ctx}/gomeStores/publish.action",{type:0,isAll:true},function (data){
	            if(data == "success"){
	                alert("全部发布成功");
	            }
	            else{
	                alert("异常错误，退出重新登录或者联系开发人员");
	            }
	        });
        }
    }
    
     function publishTestWapAll(){
        if(window.confirm("确定执行全部发布预览吗？如果是，时间比较长，请耐心等待")){
            $.post("${ctx}/gomeStores/wapPublish.action",{type:1,isAll:true},function (data){
	            if(data == "success"){
	                alert("全部发布预览成功");
	            }
	            else{
	                alert("异常错误，退出重新登录或者联系开发人员");
	            }
	        });
        }
    }
    
    function publishWapAll(){
        if(window.confirm("确定执行全部发布吗？如果是，时间比较长，请耐心等待")){
            $.post("${ctx}/gomeStores/wapPublish.action",{type:0,isAll:true},function (data){
	            if(data == "success"){
	                alert("全部发布成功");
	            }
	            else{
	                alert("异常错误，退出重新登录或者联系开发人员");
	            }
	        });
        }
    }
    
    function exportBasicInfo(){
        if(window.confirm("确定导出全部信息吗？如果是，时间比较长，请耐心等待")){            
            window.location = "${ctx}/gomeStores/exportBasicInfo.action";
        }
    }
    function clearTestSingle(){
        $("#publishTestId").val("");
        $("#publishTestInfo").html("");
        $("#publishTestInfo").hide();
    }
    function clearTestInterval(){
        $("#publishTestStartId").val("");
        $("#publishTestEndId").val("");
    }
    function clearSingle(){
        $("#publishId").val("");
        $("#publishtInfo").html("");
        $("#publishInfo").hide();
    }
    function clearInterval(){
        $("#publishStartId").val("");
        $("#publishEndId").val("");
    }
    </script>
    <title>SEO国美门店管理</title>
  </head>
  
  <body>
    <div class="mod-1">
        <div class="hd">
            <h3>SEO国美门店管理</h3>
        </div>
        <div class="bd clearfix">
            <div class="container-1">
                <div style="font-size: 14px;color: blue;margin-bottom: 5px;">预览操作</div>
                <div style="margin-bottom: 5px;">
                    <input id="publishTestId" type="text" value=""/>
                    <input id="publishTestSingle" type="button" value="单个门店PC端发布预览" onclick="publishTestSingle()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" value="单个门店WAP端发布预览" onclick="publishTestWapSingle()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input id="clearTestSingle" type="button" value="清空" onclick="clearTestSingle()" />
                    <span id="publishTestInfo" style="margin-left: 10px;font-size: 12px;color: blue;display: none;"></span>
                </div>
                <div style="margin-bottom: 5px;">
                    <input id="publishTestStartId" type="text" value=""/>&nbsp;~&nbsp;<input id="publishTestEndId" type="text" value=""/>
                    <input id="publishTestInterval" type="button" value="PC端区间发布预览" onclick="publishTestInterval()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" value="WAP端区间发布预览" onclick="publishTestWapInterval()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input id="clearTestInterval" type="button" value="清空" onclick="clearTestInterval()" />
                </div>
                <div>
                    <input id="publishTestAll" type="button" value="PC端全部发布预览" onclick="publishTestAll()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" value="WAP端全部发布预览" onclick="publishTestWapAll()" />
                </div>
            </div>
            <div style="height: 10px;width: 100%;">&nbsp;</div>
            <div class="container-1">
                <div style="font-size: 14px;color: blue;margin-bottom: 5px;">发布操作</div>
                <div style="margin-bottom: 5px;">
                    <input id="publishId" type="text" value=""/>
                    <input id="publishSingle" type="button" value="PC端单个门店发布" onclick="publishSingle()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" value="WAP端单个门店发布" onclick="publishWapSingle()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input id="clearSingle" type="button" value="清空" onclick="clearSingle()" />
                    <span id="publishInfo" style="margin-left: 10px;font-size: 12px;color: blue;display: none;"></span>
                </div>
                <div style="margin-bottom: 5px;">
                    <input id="publishStartId" type="text" value=""/>&nbsp;~&nbsp;<input id="publishEndId" type="text" value=""/>
                    <input id="publishInterval" type="button" value="PC端区间发布" onclick="publishInterval()" />
                    <input type="button" value="WAP端区间发布" onclick="publishWapInterval()" />
                    <input id="clearInterval" type="button" value="清空" onclick="clearInterval()" />
                </div>
                <div>
                    <input id="publishAll" type="button" value="PC端全部发布" onclick="publishAll()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" value="WAP端全部发布" onclick="publishWapAll()" />
                </div>
            </div>
            <div style="height: 10px;width: 100%;">&nbsp;</div>
            <div class="container-1">
                <input id="exportBasicInfo" type="button" value="SEO国美门店基本信息导出" onclick="exportBasicInfo()" />
            </div>
        </div>
      </div>
  </body>
</html>
