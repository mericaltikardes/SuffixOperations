<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link href="webjars\bootstrap\5.1.3\css\bootstrap.min.css" rel="stylesheet">
    <title>Suffix Operations</title>
</head>
<body>
<h2>Suffix Operations</h2>
<div class="container my-5">
    <form method="post">
        <div class="mb-3">
            <div class="container mt-5">
                <h1>Result: ${result}</h1>
                <p>Response time: ${resultResponseTime} ${resultResponseTime==null ?" ":"miliseconds"}</p>
            </div>

            <button type="button" class="btn btn-primary  mt-1" style="margin-left: 10px" onclick="addInput()">Ekle</button>
            <button type="button" class="btn btn-primary  mt-1" onclick="delInput()">delete</button>
            <button type="submit" class="btn btn-primary mt-1">Birlestir</button>
            <a href="save" class="btn btn-primary mt-1">Kaydet</a>

            <div id="input-container">
                <textarea type="text" style="margin:10px;width:50%" name="myStrings" required="required"></textarea>
                <br>
                <textarea type="text" style="margin:10px;width:50%" name="myStrings" required="required"></textarea>
            </div>

        </div>
    </form>
</div>

<script>
    function delInput(){
        var container = document.getElementById("input-container");
        if(container.childElementCount>3){
            container.removeChild(container.children.item(container.childElementCount-1))
            container.removeChild(container.children.item(container.childElementCount-1))
        }
    }
    function addInput() {
        var container = document.getElementById("input-container");
        var input = document.createElement("textarea");
        var br = document.createElement("br");
        input.type = "text";
        input.name = "myStrings";
        input.required = "required";
        input.style.margin="10px";
        input.style.width="50%";
        container.appendChild(br);
        container.appendChild(input);

    }

</script>
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
</body>
</html>