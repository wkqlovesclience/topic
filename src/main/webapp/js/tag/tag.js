/**
 * @author tianyu-ds
 * @date 2013-10-08 16:37
 * @desc ��ǩ������Ҫ��js����
 */

/**
 * @param act
 * @desc ɾ������ǰ�Ĳ���
 */
function deleteOperation(act) {
	var boxs = $(":checkbox[id]:checked");
	var ids = new Array();

	boxs.each(function() {
		ids.push(this.value);
	});

	if (ids.length > 0) {
		deleteData(ids.join(","), "./delete.action?act=" + act);
	}
}

/**
 * @param args
 * @param url
 * @desc ɾ������
 */
function deleteData(args, url) {
	if(!window.confirm("ȷ��Ҫɾ����")){return;}
	$.post(url, {
		ids : args
	}, function(data) {
		eval("var info="+data);
		var result = info.result;
		if(result=='error'){
			alert("�쳣����");
		}
		else if(result=='emptyParam'){
			alert("�������ڿ�ֵ");
		}
		else if(result=='success'){
			alert("ɾ���ɹ�");
			window.location = location;
		}
		else if(result.indexOf("subtag:")!=-1){
			alert("ɾ��ʧ�ܣ�\n������ǩ��ţ�"+result.substring(result.indexOf(":")+1)+"����������Ʒ\n����ɾ��������ǩ�µ����Ѵ�");
		}
		else if(result.indexOf("firsttag:")!=-1){
			alert("ɾ��ʧ�ܣ�\nһ����ǩ��ţ�"+result.substring(result.indexOf(":")+1)+"���ڶ�����ǩ\n����ɾ��һ����ǩ�µĶ�����ǩ");
		}
	});
}

/**
 * @param boxObj
 * @desc checkboxȫѡ����
 */
function allChecked(boxObj) {
	var boxs = $(":checkbox[id=each]");
	if (boxObj.checked) {
		boxs.each(function() {
			this.checked = true;
		});
	} else {
		boxs.each(function() {
			this.checked = false;
		});
	}
}

/**
 * @desc ѡ��ʱ����
 */
function yxbegin() {
	if (window.ActiveXObject) {
		document.getElementById("qcreateTime").click();
	} else {
		var evt = document.createEvent("MouseEvents");
		evt.initEvent("click", true, true);
		document.getElementById("qcreateTime").dispatchEvent(evt);
	}
}
function yxend() {
	if (window.ActiveXObject) {
		document.getElementById("qcreateEndTime").click();
	} else {
		var evt = document.createEvent("MouseEvents");
		evt.initEvent("click", true, true);
		document.getElementById("qcreateEndTime").dispatchEvent(evt);
	}
}

/**
 * @desc ѡ��ɾ���������
 */
function changeBlock(act){
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
		$.post("./delete.action",{ids:str,act:act},function (data){
			eval("var info="+data);
			var result = info.result;
			if(result=='error'){
				alert("�쳣����");
			}
			else if(result=='emptyParam'){
				alert("�������ڿ�ֵ");
			}
			else if(result=='success'){
				alert("ɾ���ɹ�");
				window.location = info.url;
			}
			else if(result.indexOf("subtag:")!=-1){
				alert("ɾ��ʧ�ܣ�\n������ǩ��ţ�"+result.substring(result.indexOf(":")+1)+"����������Ʒ\n����ɾ��������ǩ�µ����Ѵ�");
			}
			else if(result.indexOf("firsttag:")!=-1){
				alert("ɾ��ʧ�ܣ�\nһ����ǩ��ţ�"+result.substring(result.indexOf(":")+1)+"���ڶ�����ǩ\n����ɾ��һ����ǩ�µĶ�����ǩ");
			}
		});
	}
}

/**
 * @desc ��ȡ�ӱ�ǩ����
 */
function getSubTags(parent_id, targetId, targetValue)
{
	$.post("./soptions.action", {parent_id: parent_id}, function(result){
		$("#" + targetId).html("");
		$("#" + targetId).html(result);
		$("#" + targetId).val(targetValue);
	});
}

/**
 * @desc �������
 */
function clearAll()
{
	$("input:text,select").not(":button,:submit").val("");
}