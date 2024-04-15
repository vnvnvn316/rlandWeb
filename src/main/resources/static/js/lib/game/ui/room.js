import Boy from "../item/boy.js";


export default class Room {
    #img
    #ctx
    #boy
    constructor(){
        const gameSection =document.querySelector("#game-section");
        this.canvas = gameSection.querySelector(".room");
    
        /** @type {CanvasRenderingContext2D} */
        this.#ctx = this.canvas.getContext("2d");

        this.#img = new Image();
        this.#img.src="./res/map.png";
        
        this.#boy = new Boy();

    }

    draw(){
        this.#ctx.drawImage(this.#img,0,0);
        this.#boy.draw(this.#ctx);

    }

    update(){

    }

    run(){

    }

}