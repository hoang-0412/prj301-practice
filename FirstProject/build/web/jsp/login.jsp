<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String mess = (String) request.getAttribute("mess");
            if (mess != null) {
        %>
        <p style="color: red;"><%= mess %></p>
        <% } %>
        <form action="productjsp" method="POST">
            UserName: <input type="text" name="email" id="txtemail" required=""></br>
            PassWord: <input type="password" name="pass" id="txtpass" required=""></br>
            <input type="submit" name="submit" id="txtsubmit" value="Login">
            <input type="reset" value="Reset">
            <input type="hidden" name="service" value="login">
        </form>
    </body>
</html>
