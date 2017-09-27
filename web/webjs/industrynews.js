new Vue({
    el: '#app',
    data: {
        leftnavDatas:{name:"行业资讯",url:"#",data:[{name:"行业新闻",url:"industrynews.html?hyxw"},{name:"行业趋势",url:"industrynews.html?hyqs"}]},
        hyxwDatas:[{title:"细胞项目一期",time:"2017-05-06",id:"123"},{title:"细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期",time:"2017-05-06",id:"123"},{title:"细胞项目一期",time:"2017-05-06",id:"123"},{title:"细胞项目一期",time:"2017-05-06",id:"123"},{title:"细胞项目一期",time:"2017-05-06",id:"123"}],
        hyqsDatas:[{title:"细胞项目一期",time:"2017-05-06",id:"123"},{title:"细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期",time:"2017-05-06",id:"123"},{title:"细胞项目一期",time:"2017-05-06",id:"123"},{title:"细胞项目一期",time:"2017-05-06",id:"123"},{title:"细胞项目一期",time:"2017-05-06",id:"123"}],
        pages:[1,2,3,4,5],
        pageNum:5,
        nowModal:"",
        hyxwdetail:{title:"细胞项目一期",content:"产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍品介绍产品介绍产品介绍产品介绍产品介绍产品介品介绍产品介绍产品介绍产品介绍产品介绍产品介品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品"},
        hyqsdetail:{title:"细胞项目一期",content:"产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍品介绍产品介绍产品介绍产品介绍产品介绍产品介品介绍产品介绍产品介绍产品介绍产品介绍产品介品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品"},
    },
    methods: {
        leftnavclick: function (modal,event) {
            location.href = "industrynews.html?"+modal;
        },
        detailClick: function (modal, event){
            //ajax 获取数据
            if (modal === "hyxw"){

            }else if (modal === "hyqs"){

            }

            //显示详情组件
            $("#"+modal).hide();
            $(".page").hide();
            $("#"+modal+"detail").show();
        }
    },
    mounted : function () {
        var str=location.href;
        var num=str.indexOf("?");
        str=str.substr(num+1).replace("#","");
        this.nowModal = str;
        //ajax 获取对应右侧数据
        if(str === "hyxw"){

        }else if (str === "hxqs"){

        }
    }
})
