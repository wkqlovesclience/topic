<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>����������������ͼ����</title>
		<script type="text/javascript" src="${ctx}/js/echarts.min.js" charset="UTF-8"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.6.js"></script>
		<script language="javascript" type="text/javascript">
            function yearChange(value){
                $.post("${ctx}/ExpenditureOffLine/expendAnalyseInMonth.action?yearNum="+value+"&stairId=<s:property value='firstStairId'/>",function(data){
                    console.log(data);
                    // ʹ�ø�ָ�����������������ʾͼ��
                    myChart.setOption({
                        xAxis: {
                            data: data.intervals
                        },
                        series: [{
                            itemStyle: {
                                normal: {
                                    lineStyle: {
                                        color: '#3399ff'//����������ɫ
                                    }
                                }
                            },
                            markPoint: {
                                data: [
                                    { type: 'max'},
                                    { type: 'min'},
                                ]
                            },
                            markLine: {
                                data: [
                                    { type: 'average'}//��ʾƽ��ֵ
                                ]
                            },
                            // �������ֶ�Ӧ����Ӧ��ϵ��
                            type: 'line',
                            name: '����',
                            data: data.count
                        }]
                    });
                },"json");
                myChart.setOption(option);
			}
		</script>
	</head>
	<body>
	<span style="align-content: center">��ѡ����ݣ�<select id="myYear" onchange="yearChange(this.value)"></select></span>

	<script type="text/javascript">
        window.onload=function(){
            //������ݵ�ѡ��
            var myDate= new Date();
            var startYear=myDate.getFullYear()-10;//��ʼ���
            var endYear=myDate.getFullYear()+10;//�������
            var obj=document.getElementById('myYear')
            for (var i=startYear;i<=endYear;i++)
            {
                obj.options.add(new Option(i,i));
            }
            obj.options[obj.options.length-11].selected=1;
        }
	</script>
	<!-- ΪECharts׼��һ���߱���С����ߣ���Dom -->
	<div id="statistics_main" style="width: 1200px;height: 500px;;margin-top: 30px;margin-left: 30px"></div>

	<script type="text/javascript">
        var myChart = echarts.init(document.getElementById("statistics_main"));
        var option = {
            title: {
                text: '������������',
                subtext: 'һ���ڻ���ͳ��'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {//��껬����������ʽ
                    type: 'line',
                    lineStyle: {
                        color: 'red',//��ɫ
                        width: 1,//���
                        type: 'solid'//ʵ��
                    }
                },
                formatter: function(value) {//��껬��ʱ��ʾ���ݵĸ�ʽ��
                    var template = "";
                    template += '�·ݣ�' + value[0].axisValue + "<br/>";
                    template += '���ѽ�' + value[0].data;
                    return template;
                }
            },
            //���Ͻǹ�����
            toolbox: {
                show: true,
                feature: {
                    mark: { show: true },//Ŀǰ����֪����ɶ��
                    dataView: { show: true, readOnly: true ,title:'data view'},//������ͼ
                    magicType: { show: true, type: ['line', 'bar'],title:['line', 'bar'] },//��������״���л�
                    restore: { show: true ,title:'revert'},//����
                    saveAsImage: { show: true ,title:'save'}//����ΪͼƬ
                }
            },
            calculable: true,
            xAxis: [{//x�������
                type: 'category',
                name:'�·�',
                boundaryGap: false,//��Ϊtrue,��x���ֵ���ڿ̶���.
                data: [],
                axisLabel: {//y������ݸ�ʽ��,�����õ�����
                    formatter: '{value}'
                }
            }],
            yAxis: [{
                type: 'value',
                name: "����",
                axisLabel: {//y������ݸ�ʽ��,�����õ�����
                    formatter: '{value}'
                }
            }],
            legend: {
                data: ['����']//Ҫ��series�е�nameһ��
            },

        };

        $.post("${ctx}/ExpenditureOffLine/expendAnalyseInMonth.action?stairId=<s:property value='firstStairId'/>",function(data){
            console.log(data);
            // ʹ�ø�ָ�����������������ʾͼ��
            myChart.setOption({
                xAxis: {
                    data: data.intervals
                },
                series: [{
                    itemStyle: {
                        normal: {
                            lineStyle: {
                                color: '#3399ff'//����������ɫ
                            }
                        }
                    },
                    markPoint: {
                        data: [
                            { type: 'max'},
                            { type: 'min'},
                        ]
                    },
                    markLine: {
                        data: [
                            { type: 'average'}//��ʾƽ��ֵ
                        ]
                    },
                    // �������ֶ�Ӧ����Ӧ��ϵ��
                    type: 'line',
                    name: '����',
                    data: data.count
                }]
            });
        },"json");
        myChart.setOption(option);

	</script>


	</body>

</html>
