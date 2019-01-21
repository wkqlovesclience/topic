<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>线下渠道数据折线图分析</title>
		<script type="text/javascript" src="${ctx}/js/echarts.min.js" charset="UTF-8"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.6.js"></script>
		<script language="javascript" type="text/javascript">
            function yearChange(value){
                $.post("${ctx}/ExpenditureOffLine/expendAnalyseInMonth.action?yearNum="+value+"&stairId=<s:property value='firstStairId'/>",function(data){
                    console.log(data);
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption({
                        xAxis: {
                            data: data.intervals
                        },
                        series: [{
                            itemStyle: {
                                normal: {
                                    lineStyle: {
                                        color: '#3399ff'//控制折线颜色
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
                                    { type: 'average'}//显示平均值
                                ]
                            },
                            // 根据名字对应到相应的系列
                            type: 'line',
                            name: '花费',
                            data: data.count
                        }]
                    });
                },"json");
                myChart.setOption(option);
			}
		</script>
	</head>
	<body>
	<span style="align-content: center">请选择年份：<select id="myYear" onchange="yearChange(this.value)"></select></span>

	<script type="text/javascript">
        window.onload=function(){
            //设置年份的选择
            var myDate= new Date();
            var startYear=myDate.getFullYear()-10;//起始年份
            var endYear=myDate.getFullYear()+10;//结束年份
            var obj=document.getElementById('myYear')
            for (var i=startYear;i<=endYear;i++)
            {
                obj.options.add(new Option(i,i));
            }
            obj.options[obj.options.length-11].selected=1;
        }
	</script>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="statistics_main" style="width: 1200px;height: 500px;;margin-top: 30px;margin-left: 30px"></div>

	<script type="text/javascript">
        var myChart = echarts.init(document.getElementById("statistics_main"));
        var option = {
            title: {
                text: '线下渠道花费',
                subtext: '一年内花费统计'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {//鼠标滑过的线条样式
                    type: 'line',
                    lineStyle: {
                        color: 'red',//颜色
                        width: 1,//宽度
                        type: 'solid'//实线
                    }
                },
                formatter: function(value) {//鼠标滑过时显示内容的格式化
                    var template = "";
                    template += '月份：' + value[0].axisValue + "<br/>";
                    template += '花费金额：' + value[0].data;
                    return template;
                }
            },
            //右上角工具条
            toolbox: {
                show: true,
                feature: {
                    mark: { show: true },//目前还不知道有啥用
                    dataView: { show: true, readOnly: true ,title:'data view'},//数据视图
                    magicType: { show: true, type: ['line', 'bar'],title:['line', 'bar'] },//折线与柱状的切换
                    restore: { show: true ,title:'revert'},//重置
                    saveAsImage: { show: true ,title:'save'}//保存为图片
                }
            },
            calculable: true,
            xAxis: [{//x轴的数据
                type: 'category',
                name:'月份',
                boundaryGap: false,//若为true,则x轴的值不在刻度上.
                data: [],
                axisLabel: {//y轴的内容格式化,很有用的属性
                    formatter: '{value}'
                }
            }],
            yAxis: [{
                type: 'value',
                name: "花费",
                axisLabel: {//y轴的内容格式化,很有用的属性
                    formatter: '{value}'
                }
            }],
            legend: {
                data: ['花费']//要与series中的name一致
            },

        };

        $.post("${ctx}/ExpenditureOffLine/expendAnalyseInMonth.action?stairId=<s:property value='firstStairId'/>",function(data){
            console.log(data);
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption({
                xAxis: {
                    data: data.intervals
                },
                series: [{
                    itemStyle: {
                        normal: {
                            lineStyle: {
                                color: '#3399ff'//控制折线颜色
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
                            { type: 'average'}//显示平均值
                        ]
                    },
                    // 根据名字对应到相应的系列
                    type: 'line',
                    name: '花费',
                    data: data.count
                }]
            });
        },"json");
        myChart.setOption(option);

	</script>


	</body>

</html>
