<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.OrderDetails" %>
<!DOCTYPE html>
<html>
    
    <%
        OrderDetails od = (OrderDetails) request.getAttribute("od");
    %>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="orderdetailJSP?service=updateOrderDetail" method="POST">
            <table border="0">
                <tbody>
                    <tr>
                        <td>detailID:</td>
                        <td><input type="text" name="detailID" id="txtId" value="<%=od.getDetailID()%>" readonly=""></td>
                    </tr>
                    <tr>
                        <td>price:</td>
                        <td><input type="text" name="price" id="txtPrice" value="<%=od.getPrice()%>"></td>
                    </tr>
                    <tr>
                        <td>quantity:</td>
                        <td><input type="text" name="quantity" id="txtquantity" value="<%=od.getQuantity()%>"></td>
                    </tr>
                    <tr>
                        <td>orderID:</td>
                        <td><input type="text" name="orderID" id="txtOrderID" value="<%=od.getOrderID()%>"></td>
                    </tr>
                    <tr>
                        <td>productID:</td>
                        <td><input type="text" name="productID" id="txtProductID" value="<%=od.getProductID()%>"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="submit" id="txtSubmit" value="Update Order Detail"></td>
                        <td><input type="reset" value="Reset">
                            <input type="hidden" name="service" value="updateOrderDetail"></td>
                    </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>
