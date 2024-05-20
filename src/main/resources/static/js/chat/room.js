window.addEventListener("load", function(){
    const chatWindow = this.document.querySelector("#chat-window");

    const connButton = chatWindow.querySelector(".btn-conn");
    const sendButton = chatWindow.querySelector(".btn-send");

    const textInput = chatWindow.querySelector("input[type='text']");
    const ul = chatWindow.querySelector("ul");

    let sock=null;

    connButton.onclick = function(){
        sock = new WebSocket("ws://192.168.0.54:8080/chat");
        sock.onopen = () => {
            let li = `<li>서버에 연결되었습니다.</li>`;
            ul.insertAdjacentHTML("beforeend",li);
            textInput.disabled=false;
        };
        sock.onclose = ()=> {

        };
        sock.onmessage = (e)=> {
            let li = `<li>${e.data}</li>`;
            ul.insertAdjacentHTML("beforeend",li);
        };
    }

    sendButton.onclick = function(){
        if(sock)
            sock.send(textInput.value);
    }


});