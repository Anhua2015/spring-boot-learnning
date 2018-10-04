
    var myChart = echarts.init(document.getElementById('charts'));
    var data = [];
    var date = [];
    var d = new Date().toLocaleTimeString();

    function addData(shift,types) {

        $.ajax({
            url:'/charts?type='+types,
            type:'GET',
            success:function(result){
                data.push(result);
                $("#"+types).html(result);
                d = new Date().toLocaleTimeString();
                date.push(d);
            }
        });
        if (date.length>=100) {
            if (shift) {
                date.shift();
                data.shift();
            };
        };
    }
    var int=null;
    function getCharts(types){
        if (null!=int){
            clearInterval(int);
            date=[];
            data=[];
        }
        int = setInterval(function () {
            if (bip != null){
                addData(true,types);
                myChart.setOption({
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'line'
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: true,
                        axisPointer: {
                            type:'line'
                        },
                        data: date
                    },
                    yAxis: {
                        boundaryGap: [0, '50%'],
                        type: 'value'
                    },
                    series: [
                        {
                            name:types,
                            type:'line',
                            smooth:true,
                            data: data
                        }
                    ]
                });
            }
        }, 3000);
    }
