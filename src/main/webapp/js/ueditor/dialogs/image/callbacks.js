/**
 * Created by JetBrains WebStorm.
 * User: taoqili
 * Date: 11-9-29
 * Time: ����3:50
 * To change this template use File | Settings | File Templates.
 */
/**
 * ѡ���ļ���Ļص�����
 * @param	Array
 */
function selectFileCallback(selectFiles){
    // �����ﵥ��Ԫ��ΪObject��{index:�ڶ�ͼ�ϴ���������, name:�ļ���, size:�ļ���С}
    // ����size��λΪByte
    console.log("ѡ���������ļ���");
    var obj;
    for(var i = 0, iLen = selectFiles.length; i < iLen; i++){
        obj = selectFiles[i];
        console.log(obj.index, obj.name, obj.size);
    }
    console.log("===================================");
}
/**
 * �ļ���С����ʱ�Ļص�����
 * @param	Object
 */
function exceedFileCallback(file){
    // ����ΪObject��{index:�ڶ�ͼ�ϴ���������, name:�ļ���, size:�ļ���С}
    // ����size��λΪByte
    console.log("�ļ�������С���ƣ�");
    console.log(file.index, file.name, file.size);
    console.log("===================================");
}
/**
 * ɾ���ļ���Ļص�����
 * @param	Array
 */
function deleteFileCallback(delFiles){
    // �����ﵥ��Ԫ��ΪObject��{index:�ڶ�ͼ�ϴ���������, name:�ļ���, size:�ļ���С}
    // ����size��λΪByte
    console.log("ɾ���������ļ���");
    var obj;
    for(var i = 0, iLen = delFiles.length; i < iLen; i++){
        obj = delFiles[i];
        console.log(obj.index, obj.name, obj.size);
    }
    console.log("===================================");
}
/**
 * ��ʼ�ϴ������ļ��Ļص�����
 * @param	Object
 */
function startUploadCallback(file){
    console.log("��ʼ�ϴ������ļ���");
    console.log(file.name, file.size);
    console.log("===================================");
}
/**
 * �����ļ��ϴ���ɵĻص�����
 * @param	Object/String	����˷���ɶ����������ɶ
 */
	function uploadCompleteCallback(data){
		console.log("�ϴ��ɹ�", data);
        console.log("===================================");
	}
 /**
  * �����ļ��ϴ�ʧ�ܵĻص�����
  * @param	Object/String	����˷���ɶ����������ɶ
  */
	function uploadErrorCallback(data){
		console.log("�ϴ�ʧ��", data);
        console.log("===================================");
	}
 /**
  * ȫ���ϴ���ɵĻص�����
  */
	function allCompleteCallback(){
		console.log("ȫ���ϴ��ɹ�");
        console.log("===================================");
	}

