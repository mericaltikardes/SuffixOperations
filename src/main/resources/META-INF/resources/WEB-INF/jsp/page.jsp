<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <link href="webjars\bootstrap\5.1.3\css\bootstrap.min.css" rel="stylesheet">
    <title>Suffix Operations</title>
</head>
<body>
<div class="container my-5">
    <form method="post">
        <div class="mb-3">
            <div id="input-container">

                <input type="text" name="myStrings" required="required">
                <input type="text" name="myStrings" required="required">
            </div>
            <button type="button" class="btn btn-primary mt-5" onclick="addInput()">Ekle</button>
            <button type="submit" class="btn btn-primary mt-5">Gonder</button>
            <a href="save" class="btn btn-primary mt-5">Kaydet</a>
            <p>Response time: ${resultResponseTime} nanoseconds.</p>

        </div>
    </form>
</div>
<div class="container mt-5">
    <h1>Result: ${result}</h1>

</div>

<script>
    function addInput() {
        var container = document.getElementById("input-container");
        var input = document.createElement("input");
        input.type = "text";
        input.name = "myStrings";
        input.required = "required";
        container.appendChild(input);
    }
</script>
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
</body>
</html>
