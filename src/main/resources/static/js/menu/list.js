// WARNING: For GET requests, body is set to null by browsers.

function Cookie(){
    this.map = {};

    var cookieDecoded = decodeURIComponent(document.cookie);
    var cookieTokens = cookieDecoded.split(";"); //쿠키 문자열은 ;로 구분된 여러 개의 쿠키 쌍으로 구성
    
    for(c of cookieTokens){
        var temp = c.split("=");
        var key = temp[0];
        var value = JSON.parse(temp[1]);

        console.log("key",key);
        console.log("value",value);

        this.map[key] = value;
    }

    console.log(document.cookie);

}

Cookie.prototype = {
    get : function(name){
        return this.map[name];
    },
    save : function() {
        
        var list = this.map["menus"];
        var size = list.length;
        var lastIndex = size-1;

        str ="[";

        for(var m of this.map["menus"]){
            str+=JSON.stringify(m);
            if(m!==list[lastIndex])
                str+=",";
        }
        
        str +="]";

        var encoded = encodeURIComponent(str);
        document.cookie = `menus=${encoded}; path=/;`;

    },
    remove : function(name) {

    },
    add : function(name, value) {

    },
    addItem : function(name, item) {
        var list = this.map[name];
        list.push(item);
    },
    set : function(name, value){
    }
}

window.addEventListener("load", function(){

    // var val=decodeURIComponent(document.cookie.split("=")[1]);
    // console.log(JSON.parse(val));

    var cookie = new Cookie();
    console.log(cookie.get("menus"));

    var categoryFilter = this.document.querySelector(".category-filter");
    //var li1 = categoryFilter.querySelector("ul>li:nth-child(2)");

    //console.log(categoryFilter,",", li1);

    var queryForm = this.document.getElementById("query-form");
    var queryButton = queryForm.getElementsByClassName("icon-find")[0];
    var queryInput = queryForm.getElementsByClassName("query-input")[0];
    
    var menuCardList = this.document.getElementById("menu-card-list");
    var menuContent = menuCardList.getElementsByClassName("content")[0];
    
    menuContent.onclick = function(e) {
        
        if(!e.target.classList.contains("btn-cart"))
            return;

        e.preventDefault();
        
        var item={};

        item.id=e.target.dataset.id;
        item.korName=e.target.dataset.korName;
        item.engName=e.target.dataset.engName;
        item.price=e.target.dataset.price;
        item.regDate=e.target.dataset.regDate;
        item.img=e.target.dataset.img;
        item.categoryId=e.target.dataset.categoryId;

        console.log(item);
        // cookie.remove("menus");
        // cookie.add("",)
        cookie.addItem("menus", item);
        //cookie.set();
        cookie.save();
        e.preventDefault();

        // alert("dd");

    };

    categoryFilter.onclick = function(e) {
        
        if(e.target.tagName != 'A')
            return;

        e.preventDefault();

        var categoryId = e.target.dataset.id;
        
        if(categoryId==undefined)
            categoryId='';

        var url = `http://localhost:8080/api/menus?c=${categoryId}`;

        request(url, function(list){
            bind(list);
            console.log("카테고리 목록 재로드");
        });
    };


    queryButton.onclick = function(e){ //이벤트객체 e를 전달 
        
        e.preventDefault(); 

        var q = queryInput.value;

        var url =`http://localhost:8080/api/menus?q=${q}&p=1`;

        request(url, function(list){
            bind(list);
            console.log("검색어 목록 재로드");
        });

    };

    function request(url, callback, method) {

        method = method || "GET";
        
        var xhr = new XMLHttpRequest();
        xhr.withCredentials = true;
        
        xhr.onload = function(){
            var list = JSON.parse(this.responseText);
            callback(list);
        };

        xhr.open(method,url);
    
        xhr.send();

    }

    function bind(list){

        menuContent.classList.add("fade-out");

        //setTimeout(function(){
        menuContent.ontransitionend = function(){
            
            menuContent.ontransitionend = null;

            menuContent.classList.remove("fade-out");

            menuContent.innerHTML ="";

            for(var menu of list){

                var sectionHTML = `
                <section class="menu-card">
                    <h1>
                        <a class="heading-3" href="detail.html">${menu.korName}</a>
                    </h1>
                        
                    <h2 class="heading-2 font-weight:normal color:base-5">${menu.engName}</h2>
                    <div class="price-block d:flex align-items:flex-end"><span class="font-weight:bold" >${menu.price}원</span><span class="soldout-msg ml:auto mr:auto md:d:none">SOLD OUT</span></div>
                    <div class="img-block">
                        <a href="detail.html?id=1"><img src="/image/menu/8.jpg"></a>
                    </div>
                    <div class="like-block d:flex justify-content:flex-end">
                        <a class="icon icon-heart-fill icon-color:base-4" href="">좋아요</a>
                        <span class="font-weight:bold ml:1" ></span>
                    </div>
                    <div class="pay-block d:flex">
                        <form action="/cart/add-menu" method="post">
                            <input type="hidden" name="id" >
                            <button class="icon md:icon:none icon-cart icon-color:base-0 color:base-0 btn-type:icon btn-cart md:btn-type:text">담기</button>
                        </form>
                        <a class="icon md:icon:none icon-card icon-color:base-0 color:base-0 btn-type:icon btn-card md:btn-type:text" href="">주문하기</a>
                    </div>
            </section>`;

            menuContent.insertAdjacentHTML("beforeend",sectionHTML);
        
            }
        };

        //},1000);
    
            

    }

});
