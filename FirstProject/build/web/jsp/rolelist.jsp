<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, model.Roles" %>
<!DOCTYPE html>
<html>
    
    <%
        Vector<Roles> vector = (Vector<Roles>) request.getAttribute("vector");
        String tableTitle = (String) request.getAttribute("tableTitle");
        String pageTitle = (String) request.getAttribute("pageTitle");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=pageTitle%></title>
    </head>
    <body>
        <p><a href="addRole.html">Insert a new role</a></p>
        
        <form action="roleJSP">
            Search a role name:<input type="text" name="roleName" id="txtName">
            <input type="submit" name="submit" id="txtSubmit" value="Search">
            <input type="reset" value="Reset">
            <input type="hidden" name="service" value="listAllRole">
        </form>
        
        <table border="1">
            <caption><%=tableTitle%></caption>
            <thead>
                <tr>
                    <th>roleID</th>
                    <th>roleName</th>
                    <th>delete</th>
                    <th>update</th>
                </tr>
            </thead>
            <tbody>
                <% for (Roles r : vector) { %>
                <tr>
                    <td><%=r.getRoleID()%></td>
                    <td><%=r.getRoleName()%></td>
                    <td></td>
                    <td></td>
                </tr>
                <%}%>
            </tbody>
        </table>

    </body>
</html>
