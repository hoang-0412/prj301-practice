<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, model.Orders" %>
<!DOCTYPE html>
<html>
    <%
        Vector<Orders> vector = (Vector<Orders>) request.getAttribute("vector");
        String tableTitle = (String) request.getAttribute("tableTitle");
        String pageTitle = (String) request.getAttribute("pageTitle");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=pageTitle%></title>
    </head>
    <body>
        <p><a href="orderJSP?service=addOrder">Insert a new order</a></p>
        <form action="orderJSP">
            Search by order ID: <input type="text" name="orderID" id="txtId">
            <input type="submit" name="submit" id="txtSubmit" value="Search">
            <input type="reset" value="Reset">
            <input type="hidden" name="service" value="listAllOrder">
        </form>
        <table border="1">

            <caption><%=tableTitle%></caption>

            <thead>
                <tr>
                    <th>orderID</th>
                    <th>orderDate</th>
                    <th>total</th>
                    <th>userID</th>
                    <th>delete</th>
                    <th>update</th>
                </tr>
            </thead>
            <tbody>
                <% for (Orders o : vector) { %>
                <tr>
                    <td><%=o.getOrderID()%></td>
                    <td><%=o.getOrderDate()%></td>
                    <td><%=o.getTotal()%></td>
                    <td><%=o.getUserID()%></td>
                    <td><a href="orderJSP?service=deleteOrder&orderID=<%=o.getOrderID()%>"
                           onclick="return confirm('Are you sure to delete?')">delete</a></td>
                    <td><a href="orderJSP?service=updateOrder&orderID=<%=o.getOrderID()%>">update</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>

    </body>
</html>
