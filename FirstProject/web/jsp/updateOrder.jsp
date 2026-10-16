<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Orders" %>
<!DOCTYPE html>
<html>
    
    <%
        Orders o = (Orders) request.getAttribute("o");
    %>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="orderJSP?service=addOrder" method="POST">
            <table border="0">
                <tbody>
                    <tr>
                        <td>orderID:</td>
                        <td><input type="text" name="orderID" id="txtId" value="<%=o.getOrderID()%>" readonly=""></td>
                    </tr>
                    <tr>
                        <td>orderDate:</td>
                        <td><input type="text" name="orderDate" id="txtDate" value="<%=o.getOrderDate()%>"></td>
                    </tr>
                    <tr>
                        <td>total:</td>
                        <td><input type="text" name="total" id="txtTotal" value="<%=o.getTotal()%>"></td>
                    </tr>
                    <tr>
                        <td>userID:</td>
                        <td><input type="text" name="userID" id="txtUserId" value="<%=o.getUserID()%>"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="submit" id="txtSubmit" value="update Order"></td>
                        <td><input type="reset" value="Reset">
                            <input type="hidden" name="service" value="updateOrder"></td>
                    </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>
