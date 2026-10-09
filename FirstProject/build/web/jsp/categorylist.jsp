<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, model.Categories" %>
<!DOCTYPE html>
<html>
    <%
        Vector<Categories> vector = (Vector<Categories>) request.getAttribute("vector");
        String tableTitle = (String) request.getAttribute("tableTitle");
        String pageTitle = (String) request.getAttribute("pageTitle");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=pageTitle%></title>
    </head>
    <body>
        <p><a href="addCategory.html">Insert a new category</a></p>
        <form action="categoryJSP">
            Search by category name: <input type="text" name="categoryName" id="txtName">
            <input type="submit" name="submit" id="txtSubmit" value="Search">
            <input type="reset" value="Reset">
            <input type="hidden" name="service" value="listAllCategory">
        </form>

        <table border="1">
            <caption><%=tableTitle%></caption>
            <thead>
                <tr>
                    <th>categoryID</th>
                    <th>categoryName</th>
                    <th>describe</th>
                    <th>delete</th>
                    <th>update</th>
                </tr>
            </thead>
            <tbody>
                <% for (Categories c : vector) { %>

                <tr>
                    <td><%= c.getCategoryID() %></td>
                    <td><%= c.getCategoryName() %></td>
                    <td><%= c.getDescribe() %></td>
                    <td></td>
                    <td></td>
                </tr>

                <% } %>

            </tbody>
        </table>
    </body>
</html>
