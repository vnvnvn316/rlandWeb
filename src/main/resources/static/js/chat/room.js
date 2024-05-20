window.addEventListener("load", function(){
    const chatWindow = this.document.querySelector("#chat-window");

    const connButton = chatWindow.querySelector(".btn-conn");
    const textInput = chatWindow.querySelector("input[type='text']");
    const ul = chatWindow.querySelector("ul");



    connButton.onclick = function(){
        const sock = new WebSocket("ws://localhost:8080/chat");
        sock.onopen = () => {
            let li = `<li>서버에 연결되었습니다.</li>`;
            ul.insertAdjacentHTML("beforeend",li);
            textInput.disabled=false;
        };
        sock.onclose = ()=> {

        };
        sock.onmessage = (e)=> {
            
        };
    }
});