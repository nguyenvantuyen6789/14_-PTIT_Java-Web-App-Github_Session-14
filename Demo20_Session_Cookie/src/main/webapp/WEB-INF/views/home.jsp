<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h4 style="color: blue;">Facebook</h4>
    <div>
        Hi, ${sessionScope.fullName}
    </div>
    <a href="account-detail">View Detail</a>

    <br>
    <a href="logout">Logout</a>
</body>
</html>
