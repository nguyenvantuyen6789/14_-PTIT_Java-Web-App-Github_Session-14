<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h4>Login Account</h4>
    <form action="login" method="post">
        <table>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="username" value="${usernameCookie}" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" value="${passwordCookie}" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="checkbox" name="rememberMe" />
                </td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Login" /></td>
            </tr>
        </table>
    </form>
</body>
</html>
