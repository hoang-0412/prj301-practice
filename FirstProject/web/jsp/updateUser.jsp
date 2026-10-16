<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Users" %>
<!DOCTYPE html>
<html>

    <%
        Users u = (Users) request.getAttribute("u");
    %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="userJSP?service=updateUser" method="POST">
            <table border="0">
                <tbody>
                    <tr>
                        <td>userID:</td>
                        <td><input type="text" name="userID" id="txtId" value="<%=u.getUserID()%>" readonly=""></td>
                    </tr>
                    <tr>
                        <td>fullName:</td>
                        <td><input type="text" name="fullName" id="txtName" value="<%=u.getFullName()%>"></td>
                    </tr>
                    <tr>
                        <td>password:</td>
                        <td><input type="text" name="password" id="txtPassword" value="<%=u.getPassword()%>"></td>
                    </tr>
                    <tr>
                        <td>roleID:</td>
                        <td><input type="text" name="roleID" id="txtRoleID" value="<%=u.getRoleID()%>"></td>
                    </tr>
                    <tr>
                        <td>address:</td>
                        <td><input type="text" name="address" id="txtAddress" value="<%=u.getAddress()%>"></td>
                    </tr>
                    <tr>
                        <td>phone:</td>
                        <td><input type="text" name="phone" id="txtPhone" value="<%=u.getPhone()%>"></td>
                    </tr>
                    <tr>
                        <td>email:</td>
                        <td><input type="text" name="email" id="txtEmail" value="<%=u.getEmail()%>"></td>
                    </tr>
                    <tr>
                        <td>activate:</td>
                        <td><input type="text" name="activate" id="txtActivate" value="<%=u.getActivate()%>"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="submit" id="txtSubmit" value="Update User"></td>
                        <td><input type="reset" value="Reset">
                            <input type="hidden" name="service" value="updateUser"></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
