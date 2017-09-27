new Vue({
    el: '#app',
    data: {
        leftnavDatas:{name:"关于我们",url:"#",data:[{name:"公司公告",url:"about.html?gsgg"},{name:"招贤纳士",url:"about.html?zxns"},{name:"管理团队",url:"about.html?gltd"},{name:"联系我们",url:"about.html?lxwm"}]},
        gsggDatas:[{title:"细胞项目一期",time:"2017-05-06",id:"123"},{title:"细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期细胞项目一期",time:"2017-05-06",id:"123"},{title:"细胞项目一期",time:"2017-05-06",id:"123"},{title:"细胞项目一期",time:"2017-05-06",id:"123"},{title:"细胞项目一期",time:"2017-05-06",id:"123"}],
        zxnsDatas:[{title:"细胞项目一期",img:"",content:"产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍品介绍产品介绍产品介绍产品介绍产品介绍产品介品介绍产品介绍产品介绍产品介绍产品介绍产品介品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品",url:"productlist.html"},{title:"细胞项目一期",img:"",content:"产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍",url:"productlist.html"},{title:"细胞项目一期",img:"",content:"产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍",url:"productlist.html"}],
        gltdDatas:[{name:"Carl",detail:"产品介绍产品介绍产品介绍产品绍产品介绍产品品介绍产品介绍产品品介绍产品绍产品介绍产品品介绍产品介绍产品品介绍产品绍产品介绍产品品介绍产品介绍产品品介绍产品绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品介绍",img:"images/2.jpg"},{name:"Carl",detail:"产品介绍产品介绍产品介绍产品介绍产品介绍",img:"images/2.jpg"},{name:"Carl",detail:"产品介绍产品介绍产品介绍产品介绍产品介绍",img:"images/2.jpg"},{name:"Carl",detail:"产品介绍产品介绍产品介绍产品介绍产品介绍",img:"images/2.jpg"},{name:"Carl",detail:"产品介绍产品介绍产品介绍产品介绍产品介绍",img:"images/2.jpg"}],
        pages:[1,2,3,4,5],
        pageNum:5,
        nowModal:"",
    },
    methods: {
        leftnavclick: function (modal,event) {
            location.href = "about.html?"+modal;
        },
        detailClick: function (api, event){
            //ajax 获取数据
        }
    },
    mounted : function () {
        var str=location.href;
        var num=str.indexOf("?");
        str=str.substr(num+1).replace("#","");
        this.nowModal = str;
        //ajax 获取对应右侧数据
        if(str === "gsgg"){

        }else if (str === "zxns"){

        }else if (str === "gltd"){

        }else if (str == "lxwm"){

        }
    }

})