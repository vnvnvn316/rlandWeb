function InputFileList(input){
    this.input = input;
}

InputFileList.prototype = {
    add:function(file){
        var dt = new DataTransfer();
        var files = this.input.files;
        
        for(var f of files)
            dt.items.add(f);

        //추가로 담는 파일
        dt.items.add(file);

        this.input.files = dt.files;

        //console.log(this.input.files);

    }
};


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
        imgLabel.classList.remove("valid");
        imgLabel.classList.remove("invalid");
    };

    imgLabel.ondragover = function(e){
        e.stopPropagation();
        e.preventDefault();

        console.log("over");

        var valid = e.dataTransfer &&
                    e.dataTransfer.types &&
                    e.dataTransfer.types.indexOf("files")>=0;
        
        console.log("e.dataTransfer = " , e.dataTransfer);
        console.log("e.dataTransfer.types = " , e.dataTransfer.types);

        if(valid)
            imgLabel.classList.add("valid");
        else
            imgLabel.classList.add("invalid");

    };

    imgLabel.ondrop = function(e){
        e.stopPropagation(); //파일을 drop 했을때 페이지가 새로열리지 않도록
        e.preventDefault();

        var files = e.dataTransfer.files;
        var file = files[0];

        new InputFileList(imgInput).add(file);
       
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

    };

});