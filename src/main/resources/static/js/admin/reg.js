// imgInput.oninput = (e) => {
//     alert("dd");
// }

window.addEventListener("load",function(){
    var regForm = this.document.querySelector("#reg-form");
    var imgInput = regForm.querySelector(".img-input");
    var previewPanel = regForm.querySelector(".preview-pannel");
    var imgLabel = regForm.querySelector(".img-label");

    imgLabel.ondragenter = function(e){
        console.log("enter");
    };

    imgLabel.ondragleave = function(e){
        console.log("leave");
    };

    imgLabel.ondragover = function(e){
        e.preventDefault();
        console.log("dragover");
    };

    imgLabel.ondrop = function(e){
        e.preventDefault();
        console.log("drop");
    };

    imgInput.oninput = function(e){

        var file = imgInput.files[0];
        
        if(file.type.indexOf("image/")!=0){ //타입 제약
            alert("이미지만 업로드 할 수 있습니다.");
            return;
        } 

        // 1MB = 1000KB
        if(file.size > 100*1024){ //크기 제약
            alert("100k 이하만 업로드 할 수 있습니다.");
            return;
        }
        
        var reader = new FileReader();
        
        reader.onload = function(e){
            
            var img = document.createElement("img");
            img.src = e.target.result;
            
            previewPanel.append(img);
            
            //렌더링 시스템에 시간간격 둠
            setTimeout(()=>{
                img.classList.add("p:relative");
                img.classList.add("slide-in");
            },5);
        };
        
        reader.readAsDataURL(file);

        console.log("hello");
    };

});