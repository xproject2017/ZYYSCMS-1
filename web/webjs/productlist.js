new Vue({
    el: '#app',
    data: {
        leftnavDatas:[{name:"医疗",url:"#",data:[{name:"细胞项目",url:"productlist.html"},{name:"细胞项目",url:"productlist.html"}]},{name:"医疗",url:"#",data:[{name:"细胞项目",url:"productlist.html"},{name:"细胞项目",url:"productlist.html"}]},{name:"医疗",url:"#",data:[{name:"细胞项目",url:"productlist.html"},{name:"细胞项目",url:"productlist.html"}]}],
        cpjsDatas:[{title:"细胞项目一期",img:"",content:"产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍品介绍产品介绍产品介绍产品介绍产品介绍产品介品介绍产品介绍产品介绍产品介绍产品介绍产品介品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品",url:"productlist.html"},{title:"细胞项目一期",img:"",content:"产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍",url:"productlist.html"},{title:"细胞项目一期",img:"",content:"产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍",url:"productlist.html"}],
        cgfaDatas:[{title:"细胞项目一期",img:"",content:"产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍品介绍产品介绍产品介绍产品介绍产品介绍产品介品介绍产品介绍产品介绍产品介绍产品介绍产品介品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品",url:"productlist.html"},{title:"细胞项目一期",img:"",content:"产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍",url:"productlist.html"},{title:"细胞项目一期",img:"",content:"产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍",url:"productlist.html"}],
        pages:[1,2,3,4,5],
        pageNum:5,
        nowModal:"",
        cpjsdetail:{title:"细胞项目一期",content:"产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍品介绍产品介绍产品介绍产品介绍产品介绍产品介品介绍产品介绍产品介绍产品介绍产品介绍产品介品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品"},
        cgfadetail:{title:"细胞项目一期",content:"产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍产品介绍品介绍产品介绍产品介绍产品介绍产品介绍产品介品介绍产品介绍产品介绍产品介绍产品介绍产品介品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品品介绍产品介绍产品"}
    },
    methods: {
        click2: function (modal,event) {
            $(".containleft h2").removeClass("current");
            $(".leftnav1 li").removeClass("current");
            $(".leftnav1 li").children("img").attr("src","images/icon-arrow-right.png");
            $(".leftnav2 li").removeClass("current");
            var a = event.currentTarget;
            $(a).addClass("current");
            $(a).parent(".leftnav2").parent("li").addClass("current");
            $(a).parent(".leftnav2").parent("li").children("img").attr("src","images/icon-arrow-down.png");
            $(a).parent(".leftnav2").parent("li").parent(".leftnav1").prev("h2").addClass("current");
            //ajax 获取数据
            if (modal === "cpjs"){

            }else if (modal === "cgfa"){

            }

            //显示列表组件
            $("#"+modal+"detail").hide();
            $("#"+modal).show();
            $(".page").show();
        },
        detailClick: function (modal, event){
            //ajax 获取数据
            if (modal === "cpjs"){

            }else if (modal === "cgfa"){

            }

            //显示详情组件
            $("#"+modal).hide();
            $(".page").hide();
            $("#"+modal+"detail").show();
        },
        click1: function (modal,event) {
            location.href = "productlist.html?"+modal;
        }
    },
    mounted : function () {
        //初始化左侧菜单
        var str=location.href;
        var num=str.indexOf("?")
        str=str.substr(num+1).replace("#","");
        this.nowModal = str;
        if (str === "cpjs") {
            var current = $("h2:first");
            var current1 = current.next(".leftnav1");
            var current2 = current1.find(".leftnav2:first");
            current1.slideDown();
            current2.slideDown();
            current2.children("li:first").click();
        }else if (str === "cgfa") {
            var current = $("h2:eq(1)");
            var current1 = current.next(".leftnav1");
            var current2 = current1.find(".leftnav2:first");
            current1.slideDown();
            current2.slideDown();
            current2.children("li:first").click();
        };

        //左侧导航栏
        $('.containleft h2').click(function(){
            var current = $(this);
            var current1 = current.next(".leftnav1");
            current1.slideToggle();
        })
        $('.leftnav1').children("li").children("a").click(function(){
            if(!$(this).parent("li").hasClass("current")){
                $(this).next(".leftnav2").children("li:first").click()
                $(".leftnav2").slideUp();
                $(this).next(".leftnav2").slideDown();
            }
        })
    }

})