const { createApp } = Vue

createApp({
  data(){
    return{
        query:"",
        list:[{korName : "아메리카노",engName:"Ameri"},
        {korName : "딸기라떼",engName:"Straw"}
      ]

    }
  },
  methods : {
    queryClickHandler(){
        this.list.push({korName : "추가메뉴",engName:"add"});
    }
  },
  beforeCreate(){
    console.log("beforeCreate");
  },

  async created(){ //초기화 할 때 사용
    console.log("created");
    let response = await fetch("/api/menus");
    let list = await response.json();
    this.list = list;

  },

  beforeMount(){
    console.log("beforeMount");
  },
  mounted(){ //화면에 바인딩 끝났다. 부모객체나 문서나 다른 객체의 값을 이용할때도 사용
    console.log("mounted");
  },
  beforeUpdate(){
    console.log("beforeUpdate");
  },
  updated(){
    console.log("updated");
  },
  beforeUnmount(){
    console.log("beforeUnmount");
  },
  unmounted(){
    console.log("unmounted");
  },
  activated(){
    console.log("activated");
  },
  deactivated(){
    console.log("deactivated");
  },

}).mount('main');

