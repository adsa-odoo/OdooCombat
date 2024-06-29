<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h2>Register</h2>
<form action="user" method="post">
    <input type="hidden" name="action" value="register">
    Username: <input type="text" name="username"><br>
    Password: <input type="password" name="password"><br>
    Email: <input type="text" name="email"><br>
    <input type="submit" value="Register">
</form>
</body>
</html>
