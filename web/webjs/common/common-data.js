new Vue({
    el: '#header',
    data: {
		navDatas:[{name:"产品信息",url:"#",data:[{name:"产品介绍",url:"productlist.html?cpjs"},{name:"成功方案",url:"productlist.html?cgfa"}]},{name:"行业资讯",url:"#",data:[{name:"行业新闻",url:"industrynews.html?hyxw"},{name:"行业趋势",url:"industrynews.html?hyqs"}]},{name:"公司新闻",url:"#"},{name:"关于我们",url:"#",data:[{name:"公司公告",url:"about.html?gsgg"},{name:"招贤纳士",url:"about.html?zxns"},{name:"管理团队",url:"about.html?gltd"},{name:"联系我们",url:"about.html?lxwm"}]}]
	}
})
new Vue({
    el: '#banner',
    data: {
		bannerDatas:[{id:1,url:"/image/1.jpg"},{id:2,url:"/image/2.jpg"},{id:3,url:"/image/3.jpg"}]
	}
})
