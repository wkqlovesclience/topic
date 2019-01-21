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
                        alert("����Ԥ���ɹ�");
                    }
                });
            }
            else{
                alert("��������ȷ��ʽ������Id");
                publishTestInfoDom.html("");
                publishTestInfoDom.hide();
            }
        }
        else{
            alert("�������ŵ�Id");
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
                        alert("����Ԥ���ɹ�");
                    }
                });
            }
            else{
                alert("��������ȷ��ʽ������Id");
                publishTestInfoDom.html("");
                publishTestInfoDom.hide();
            }
        }
        else{
            alert("�������ŵ�Id");
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
                        alert("�����ɹ�");
                    }
                });
            }
            else{
                alert("��������ȷ��ʽ������Id");
                publishTestInfoDom.html("");
                publishTestInfoDom.hide();
            }
        }
        else{
            alert("�������ŵ�Id");
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
                        alert("�����ɹ�");
                    }
                });
            }
            else{
                alert("��������ȷ��ʽ������Id");
                publishTestInfoDom.html("");
                publishTestInfoDom.hide();
            }
        }
        else{
            alert("�������ŵ�Id");
        }
    }
    
    function publishTestInterval(){
        var publishTestStartId = $("#publishTestStartId").val();
        publishTestStartId = $.trim(publishTestStartId);
        var publishTestEndId = $("#publishTestEndId").val();
        publishTestEndId = $.trim(publishTestEndId);
        
        if(publishTestStartId!=""){
            if(!/^\d+$/.test(publishTestStartId)){
                alert("���������θ�ʽ�Ŀ�ʼId");
                return false;
            }
        }
        
        if(publishTestEndId!=""){
            if(!/^\d+$/.test(publishTestEndId)){
                alert("���������θ�ʽ�Ľ���Id");
                return false;
            }
        }
        
        if(publishTestStartId!="" || publishTestEndId!=""){
            if(window.confirm("ȷ��ִ���������䷢��Ԥ��������ǣ�ʱ��Ƚϳ��������ĵȴ�")){
                 $.post("${ctx}/gomeStores/publish.action",{type:1,isAll:false,startId:publishTestStartId,endId:publishTestEndId},function (data){
	                if(data == "success"){
	                    alert("���䷢��Ԥ���ɹ�");
	                }
	            });
            }
        }
        else{
            alert("����������һ���ŵ�Id");
        }
    }
    
    
    function publishTestWapInterval(){
        var publishTestStartId = $("#publishTestStartId").val();
        publishTestStartId = $.trim(publishTestStartId);
        var publishTestEndId = $("#publishTestEndId").val();
        publishTestEndId = $.trim(publishTestEndId);
        
        if(publishTestStartId!=""){
            if(!/^\d+$/.test(publishTestStartId)){
                alert("���������θ�ʽ�Ŀ�ʼId");
                return false;
            }
        }
        
        if(publishTestEndId!=""){
            if(!/^\d+$/.test(publishTestEndId)){
                alert("���������θ�ʽ�Ľ���Id");
                return false;
            }
        }
        
        if(publishTestStartId!="" || publishTestEndId!=""){
            if(window.confirm("ȷ��ִ���������䷢��Ԥ��������ǣ�ʱ��Ƚϳ��������ĵȴ�")){
                 $.post("${ctx}/gomeStores/wapPublish.action",{type:1,isAll:false,startId:publishTestStartId,endId:publishTestEndId},function (data){
	                if(data == "success"){
	                    alert("���䷢��Ԥ���ɹ�");
	                }
	            });
            }
        }
        else{
            alert("����������һ���ŵ�Id");
        }
    }
    
    function publishInterval(){
        var publishStartId = $("#publishStartId").val();
        publishStartId = $.trim(publishStartId);
        var publishEndId = $("#publishEndId").val();
        publishEndId = $.trim(publishEndId);
        
        if(publishStartId!=""){
            if(!/^\d+$/.test(publishStartId)){
                alert("���������θ�ʽ�Ŀ�ʼId");
                return false;
            }
        }
        
        if(publishEndId!=""){
            if(!/^\d+$/.test(publishEndId)){
                alert("���������θ�ʽ�Ľ���Id");
                return false;
            }
        }
        
        if(publishEndId!="" || publishEndId!=""){
            if(window.confirm("ȷ��ִ���������䷢��������ǣ�ʱ��Ƚϳ��������ĵȴ�")){
                $.post("${ctx}/gomeStores/publish.action",{type:0,isAll:false,startId:publishStartId,endId:publishEndId},function (data){
	                if(data == "success"){
	                    alert("���䷢���ɹ�");
	                }
	            });
            }
        }
        else{
            alert("����������һ���ŵ�Id");
        }
    }
    
    
     function publishWapInterval(){
        var publishStartId = $("#publishStartId").val();
        publishStartId = $.trim(publishStartId);
        var publishEndId = $("#publishEndId").val();
        publishEndId = $.trim(publishEndId);
        
        if(publishStartId!=""){
            if(!/^\d+$/.test(publishStartId)){
                alert("���������θ�ʽ�Ŀ�ʼId");
                return false;
            }
        }
        
        if(publishEndId!=""){
            if(!/^\d+$/.test(publishEndId)){
                alert("���������θ�ʽ�Ľ���Id");
                return false;
            }
        }
        
        if(publishEndId!="" || publishEndId!=""){
            if(window.confirm("ȷ��ִ���������䷢��������ǣ�ʱ��Ƚϳ��������ĵȴ�")){
                $.post("${ctx}/gomeStores/wapPublish.action",{type:0,isAll:false,startId:publishStartId,endId:publishEndId},function (data){
	                if(data == "success"){
	                    alert("���䷢���ɹ�");
	                }
	            });
            }
        }
        else{
            alert("����������һ���ŵ�Id");
        }
    }
    
    
    function publishTestAll(){
        if(window.confirm("ȷ��ִ��ȫ������Ԥ��������ǣ�ʱ��Ƚϳ��������ĵȴ�")){
            $.post("${ctx}/gomeStores/publish.action",{type:1,isAll:true},function (data){
	            if(data == "success"){
	                alert("ȫ������Ԥ���ɹ�");
	            }
	            else{
	                alert("�쳣�����˳����µ�¼������ϵ������Ա");
	            }
	        });
        }
    }
    function publishAll(){
        if(window.confirm("ȷ��ִ��ȫ������������ǣ�ʱ��Ƚϳ��������ĵȴ�")){
            $.post("${ctx}/gomeStores/publish.action",{type:0,isAll:true},function (data){
	            if(data == "success"){
	                alert("ȫ�������ɹ�");
	            }
	            else{
	                alert("�쳣�����˳����µ�¼������ϵ������Ա");
	            }
	        });
        }
    }
    
     function publishTestWapAll(){
        if(window.confirm("ȷ��ִ��ȫ������Ԥ��������ǣ�ʱ��Ƚϳ��������ĵȴ�")){
            $.post("${ctx}/gomeStores/wapPublish.action",{type:1,isAll:true},function (data){
	            if(data == "success"){
	                alert("ȫ������Ԥ���ɹ�");
	            }
	            else{
	                alert("�쳣�����˳����µ�¼������ϵ������Ա");
	            }
	        });
        }
    }
    
    function publishWapAll(){
        if(window.confirm("ȷ��ִ��ȫ������������ǣ�ʱ��Ƚϳ��������ĵȴ�")){
            $.post("${ctx}/gomeStores/wapPublish.action",{type:0,isAll:true},function (data){
	            if(data == "success"){
	                alert("ȫ�������ɹ�");
	            }
	            else{
	                alert("�쳣�����˳����µ�¼������ϵ������Ա");
	            }
	        });
        }
    }
    
    function exportBasicInfo(){
        if(window.confirm("ȷ������ȫ����Ϣ������ǣ�ʱ��Ƚϳ��������ĵȴ�")){            
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
    <title>SEO�����ŵ����</title>
  </head>
  
  <body>
    <div class="mod-1">
        <div class="hd">
            <h3>SEO�����ŵ����</h3>
        </div>
        <div class="bd clearfix">
            <div class="container-1">
                <div style="font-size: 14px;color: blue;margin-bottom: 5px;">Ԥ������</div>
                <div style="margin-bottom: 5px;">
                    <input id="publishTestId" type="text" value=""/>
                    <input id="publishTestSingle" type="button" value="�����ŵ�PC�˷���Ԥ��" onclick="publishTestSingle()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" value="�����ŵ�WAP�˷���Ԥ��" onclick="publishTestWapSingle()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input id="clearTestSingle" type="button" value="���" onclick="clearTestSingle()" />
                    <span id="publishTestInfo" style="margin-left: 10px;font-size: 12px;color: blue;display: none;"></span>
                </div>
                <div style="margin-bottom: 5px;">
                    <input id="publishTestStartId" type="text" value=""/>&nbsp;~&nbsp;<input id="publishTestEndId" type="text" value=""/>
                    <input id="publishTestInterval" type="button" value="PC�����䷢��Ԥ��" onclick="publishTestInterval()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" value="WAP�����䷢��Ԥ��" onclick="publishTestWapInterval()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input id="clearTestInterval" type="button" value="���" onclick="clearTestInterval()" />
                </div>
                <div>
                    <input id="publishTestAll" type="button" value="PC��ȫ������Ԥ��" onclick="publishTestAll()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" value="WAP��ȫ������Ԥ��" onclick="publishTestWapAll()" />
                </div>
            </div>
            <div style="height: 10px;width: 100%;">&nbsp;</div>
            <div class="container-1">
                <div style="font-size: 14px;color: blue;margin-bottom: 5px;">��������</div>
                <div style="margin-bottom: 5px;">
                    <input id="publishId" type="text" value=""/>
                    <input id="publishSingle" type="button" value="PC�˵����ŵ귢��" onclick="publishSingle()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" value="WAP�˵����ŵ귢��" onclick="publishWapSingle()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input id="clearSingle" type="button" value="���" onclick="clearSingle()" />
                    <span id="publishInfo" style="margin-left: 10px;font-size: 12px;color: blue;display: none;"></span>
                </div>
                <div style="margin-bottom: 5px;">
                    <input id="publishStartId" type="text" value=""/>&nbsp;~&nbsp;<input id="publishEndId" type="text" value=""/>
                    <input id="publishInterval" type="button" value="PC�����䷢��" onclick="publishInterval()" />
                    <input type="button" value="WAP�����䷢��" onclick="publishWapInterval()" />
                    <input id="clearInterval" type="button" value="���" onclick="clearInterval()" />
                </div>
                <div>
                    <input id="publishAll" type="button" value="PC��ȫ������" onclick="publishAll()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" value="WAP��ȫ������" onclick="publishWapAll()" />
                </div>
            </div>
            <div style="height: 10px;width: 100%;">&nbsp;</div>
            <div class="container-1">
                <input id="exportBasicInfo" type="button" value="SEO�����ŵ������Ϣ����" onclick="exportBasicInfo()" />
            </div>
        </div>
      </div>
  </body>
</html>
