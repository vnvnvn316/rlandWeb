const { createApp } = Vue

createApp({
  data(){
    return{
        query:""
    }
  },
  methods : {
    queryClickHandler(){
        console.log(this.query);
    }
  }
}).mount('main');