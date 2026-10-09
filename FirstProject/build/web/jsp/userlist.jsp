<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, model.Users" %>
<!DOCTYPE html>
<html>
    
    <%
        Vector<Users> vector = (Vector<Users>) request.getAttribute("vector");
        String tableTitle = (String) request.getAttribute("tableTitle");
        String pageTitle = (String) request.getAttribute("pageTitle");
    %>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=pageTitle%></title>
    </head>
    <body>
        <p><a href="addUser.html">Insert a new user</a></p>
        <form action="userJSP">
            Search by user name:<input type="text" name="userName" id="txtName">
            <input type="submit" name="submit" id="txtSubmit" value="Search">
            <input type="reset" value="Reset">
            <input type="hidden" name="service" value="listAllUser">
        </form>
        
        <table border="1">
            
            <caption><%=tableTitle%></caption>
            <thead>
                <tr>
                    <th>userID</th>
                    <th>fullName</th>
                    <th>password</th>
                    <th>roleID</th>
                    <th>address</th>
                    <th>phone</th>
                    <th>email</th>
                    <th>activate</th>
                    <th>delete</th>
                    <th>update</th>
                </tr>
            </thead>
            <tbody>
                <% for (Users u : vector) { %>
                <tr>
                    <td><%=u.getUserID()%></td>
                    <td><%=u.getFullName()%></td>
                    <td><%=u.getPassword()%></td>
                    <td><%=u.getRoleID()%></td>
                    <td><%=u.getAddress()%></td>
                    <td><%=u.getPhone()%></td>
                    <td><%=u.getEmail()%></td>
                    <td><%=u.getActivate()%></td>
                    <td></td>
                    <td></td>
                </tr>
                <%}%>
            </tbody>
        </table>

    </body>
</html>
