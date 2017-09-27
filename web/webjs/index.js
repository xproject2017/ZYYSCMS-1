new Vue({
    el: '#app',
    data: {
        newsDatas:[{title:"新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题",url:"/image/2.jpg",time:"2017-05-06"},{title:"新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题",url:"/image/2.jpg",time:"2017-05-06"},{title:"新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题",url:"/image/2.jpg",time:"2017-05-06"},{title:"新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题",url:"/image/2.jpg",time:"2017-05-06"},{title:"新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题",url:"/image/2.jpg",time:"2017-05-06"}],
        infoDatas:[{title:"新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题",url:"/image/2.jpg",time:"2017-05-06"},{title:"新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题",url:"/image/2.jpg",time:"2017-05-06"},{title:"新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题",url:"/image/2.jpg",time:"2017-05-06"},{title:"新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题",url:"/image/2.jpg",time:"2017-05-06"},{title:"新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题",url:"/image/2.jpg",time:"2017-05-06"}],
        anoDatas:[{title:"新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题",url:"/image/2.jpg",time:"2017-05-06"},{title:"新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题",url:"/image/2.jpg",time:"2017-05-06"},{title:"新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题",url:"/image/2.jpg",time:"2017-05-06"},{title:"新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题",url:"/image/2.jpg",time:"2017-05-06"},{title:"新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题",url:"/image/2.jpg",time:"2017-05-06"}],
        examDatas:[{etitle:"sdsdcvscdas"}]
    },
    mounted : function () {
        var h = $(window).height();
        var w = $(window).width();
        var bh = $("body").height();
        var bw = $("body").width();
        if (h < bh){
            h = bh;
        }
        if (w < bw){
            w = bw;
        }
        $(".alert-shadow").css("height",h).css("width",w);
        $(".alert-exam").css("height",$(window).height()-130);

        var str=location.href;
        var num=str.indexOf("?");
        str=str.substr(num+1).replace("#","");
        this.nowModal = str;
        if (str === "regsuccess"){
            $(".alert-shadow").show();
            $(".alert-exam").show();
        }
    }
})