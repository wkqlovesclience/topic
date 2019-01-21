<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/commons/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=GBK" />
    <link rel="stylesheet" href="${ctx}/styles/cui.css" />
    <script src="${ctx}/js/jquery-1.6.js"  type="text/javascript" ></script>
    <script type="text/javascript">
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
	function deleteBatch(){
	    var cc=$('input:checked');
	    var str="";
	    for(var j=0;j<cc.length;j++){
	        str=str+$('input:checked').get(j).value+";";
	    }   
	    if(str == ""){
	         alert('������ѡ��һ�'); 
	         return;
	    } 
	    if(confirm('ȷ��ɾ����')){
	    window.location.href="${ctx}/HotSearchIndex/deleteBatch.action?paramIds="+str;
	    }
	}
	function tunePage(num) {
	    var url = '${ctx}/HotSearchIndex/list.action?page_index='+num;
	    var hotSearchIndex = $('#HotSearchIndex').val();
	    var hotSearchId = $('#hotkeyId').val();
	    if(HotSearchIndex != null && HotSearchIndex != ''){
	        url += '&hotSearchIndexEntity.hotSearchIndex='+hotSearchIndex;
	    }
	    if(hotkeyId != null && hotkeyId != ''){
	        url += '&hotSearchIndexEntity.hotSearchId='+hotSearchId; 
	    }
	    window.location = url;
	}
	//������ҳ����
    function publishHotSearchHomePage(){
        if(confirm('ȷ�Ϸ�����')){
            $.post('${ctx}/HotSearchIndex/publishHotSearchHomePage.action',
                    function(msg){  
                        if(msg == '0'){
                            alert("������ҳ�����ɹ�,����ʣ�http://www.gome.com.cn/hotwords/");
                        }
                    }
            );
        }
    }
    
    function publishMobileHotSearchHomePage(){
     if(confirm('ȷ�Ϸ�����')){
            $.post('${ctx}/HotSearchIndex/publishMobileHotSearchHomePage.action',
                    function(msg){  
                        if(msg == '0'){
                            alert("�ֻ���������ҳ�����ɹ�");
                        }
                    }
            );
        }
    }
    </script>
    <title>������������</title>
  </head>
  
  <body>
  <form action="${ctx}/HotSearchIndex/list.action" method="post" id="HotSearchIndexForm">
    <div class="mod-1">
        <div class="hd">
            <h3>������������</h3>
        </div>
        <div class="bd clearfix">
            <div style="margin-bottom: 10px;font-size: 15px;" class="container-1">
                ����������<s:select id="HotSearchIndex" name="hotSearchIndexEntity.hotSearchIndex" value="hotSearchIndexEntity.hotSearchIndex" cssStyle="width:80px;" list="#{'':'','A':'A','B':'B','C':'C','D':'D','E':'E','F':'F','G':'G','H':'H','I':'I','J':'J','K':'K','L':'L','M':'M','N':'N',
                'O':'O','P':'P','Q':'Q','R':'R','S':'S','T':'T','U':'U','V':'V','W':'W','X':'X','Y':'Y','Z':'Z','_':'0-9'}">
                </s:select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                ���ѱ�ţ�<input type="text" id="hotkeyId" name="hotSearchIndexEntity.hotSearchId" value="${hotSearchIndexEntity.hotSearchId }" class="txt-5"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="submit" value="����"/>
            </div>
            <div style="font-size: 20px;" class="container-1">
                <input type="button" value="ȫѡ" onclick="sel_all('true')" />
                <input type="button" value="ȡ��" onclick="sel_all()" />
                <input type="button" value="ɾ��" onclick="deleteBatch()" />
                <input type="button" value="����" onclick="location.href='/jsp/hotSearchIndex/add.jsp'"  />
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" value="������ҳ����" onclick="publishHotSearchHomePage()"  />
               <!--  <input type="button" value="�ֻ���������ҳ����" onclick="publishMobileHotSearchHomePage()"  /> -->
            </div>
            <div class="container-1">
                <table style="width: 100%;" class="tb-zebra tmp-class" border="0">
                    <colgroup>
                        <col style="width: 5%;" />
                        <col style="width: 8%;" />
                        <col style="width: 8%;" />
                        <col style="width: 8%;" />
                        <col style="width: 21%;"/>
                        <col style="width: 15%;"/>
                        <col style="width: 5%;" />
                        <col style="width: 15%;"/>
                        <col style="width: 5%;" />
                        <col style="width: 10%;"/>
                    </colgroup>
                    <thead>
                    <tr>
                        <td style="line-height: 1;">&nbsp;</td>
                        <td style="line-height: 1;">��������</td>
                        <td style="line-height: 1;">���ȼ�</td>
                        <td style="line-height: 1;">���ѱ��</td>
                        <td style="line-height: 1;">���ѱ���</td>
                        <td style="line-height: 1;">������</td>
                        <td style="line-height: 1;">״̬</td>
                        <td style="line-height: 1;">����ʱ��</td>
                        <td style="line-height: 1;">վ��</td>
                        <td style="line-height: 1;">����</td>
                    </tr>
                    </thead>
                    <tbody>
                    <s:if test="HotSearchIndexList != null">
                        <s:iterator value="HotSearchIndexList"  status="til">
                        <tr style="height:15px;">
                            <td><label><input type="checkbox" name="checkbox" value="${id}" /></label></td>
                            <td><s:property value="HotSearchIndex"/></td>
                            <td>
                                <c:choose>
                                    <c:when test="${priority == 0 }">��</c:when>
                                    <c:when test="${priority == 1 }">��</c:when>
                                    <c:otherwise>��</c:otherwise>
                                </c:choose>
                            </td>
                            <td><s:property value="hotSearchId" /></td>
                            <td><s:property value="hotSearchTitle" /></td>
                            <td><s:property value="operator" /></td>
                            <td>
                                <c:choose>
                                    <c:when test="${status eq '1'}">��Ч</c:when>
                                    <c:otherwise>ʧЧ</c:otherwise>
                                </c:choose>
                            </td>
                            <td><s:date name="updatetime" format="yyyy.MM.dd HH:mm:ss" /></td>
                            <td>
                                <c:choose>
                                    <c:when test="${site eq 'gome'}">����</c:when>
                                    <c:otherwise>${site}</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="javascript:void(0)" onclick="location.href='${ctx}/HotSearchIndex/edit.action?id=${id}'">�޸�</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="javascript:void(0)" onclick="location.href='${ctx}/HotSearchIndex/delete.action?id=${id}'">ɾ��</a>
                            </td>
                        </tr>
                        </s:iterator>
                        <tr>
                            <td colspan="10" style="border: 0 none;">
                                <div class="numpage"><coo8:page name="HotKeyIndexList" style="js" systemId="1" /></div>
                            </td>
                        </tr>
                    </s:if>
                    </tbody>
                </table>
            </div>
        </div>
      </div>
    </form>
  </body>
</html>
