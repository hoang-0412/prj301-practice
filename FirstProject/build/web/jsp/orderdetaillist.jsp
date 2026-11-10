<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, model.OrderDetails" %>
<!DOCTYPE html>
<html>

    <%
        Vector<OrderDetails> vector = (Vector<OrderDetails>) request.getAttribute("vector");
        Vector<OrderDetails> sumVector = (Vector<OrderDetails>) request.getAttribute("sumVector");
        String tableTitle = (String) request.getAttribute("tableTitle");
        String pageTitle = (String) request.getAttribute("pageTitle");
    %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=pageTitle%></title>
    </head>
    <body>
        <p><a href="orderdetailJSP?service=addOrderDetail">Insert a new order detail</a></p>

        <form action="orderdetailJSP">
            Search by order detail ID: <input type="text" name="detailID" id="txtId">
            <input type="submit" name="submit" id="txtSubmit" value="Search">
            <input type="reset" value="Reset">
            <input type="hidden" name="service" value="listAllOrderDetail">
        </form>

        <table border="1">
            <caption><%=tableTitle%></caption>
            <thead>
                <tr>
                    <th>detailID</th>
                    <th>price</th>
                    <th>quantity</th>
                    <th>orderID</th>
                    <th>productID</th>
                    <th>delete</th>
                    <th>update</th>
                </tr>
            </thead>
            <tbody>
                <% for (OrderDetails od : vector) { %>
                <tr>
                    <td><%=od.getDetailID()%></td>
                    <td><%=od.getPrice()%></td>
                    <td><%=od.getQuantity()%></td>
                    <td><%=od.getOrderID()%></td>
                    <td><%=od.getProductID()%></td>
                    <td><a href="orderdetailJSP?service=deleteOrderDetail&detailID=<%=od.getDetailID()%>"
                           onclick="return confirm('Are you sure to delete?')">delete</a></td>
                    <td><a href="orderdetailJSP?service=updateOrderDetail&detailID=<%=od.getDetailID()%>">update</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>

        <br>
        <form action="orderdetailJSP">
            Enter orderID: <input type="text" name="orderID">
            <br>
            <input type="hidden" name="service" value="listAllOrderDetail">
            <input type="submit" name="sum" value="SUM">
        </form>

        <% if (sumVector != null) {
                double total = 0;
                for (OrderDetails s : sumVector) {
                    total += s.getPrice() * s.getQuantity();
                }
        %>
            <p>OrderID: <%= request.getParameter("orderID") %> - Total: <%= total %></p>
        <% } %>

    </body>
</html>
