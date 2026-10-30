<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, model.Cart" %>
<!DOCTYPE html>
<html>
    <%
        Vector<Cart> vector = (Vector<Cart>)request.getAttribute("vector");
        if (vector == null) {
            vector = new Vector<>();
        }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1">         
            <thead>
                <tr>                    
                    <th>productID</th>
                    <th>productName</th>                 
                    <th>price</th>
                    <th>quantity</th>
                    <th>total</th>
                    <th>remove</th>
                     </tr>
            </thead>
            <tbody>                
                <%
                    double total = 0;
                    for (Cart c : vector) {
                    total+= c.getPrice()*c.getQuantity();
                %>                
                <tr>
                    <td><%=c.getProductID()%></td>
                    <td><%=c.getProductName()%></td>                   
                    <td><%=c.getPrice()%></td>
                    <td><%=c.getQuantity()%></td>
                    <td><%=c.getPrice()*c.getQuantity()%></td>
                    <td><a href="cart?service=removeItem&productID=<%=c.getProductID()%>">remove</a></td>
                    </tr>
                <%}%>
            </tbody>
        </table>
            <p>Grand Total: <%=total%></p>
            <p><a href="cart?service=removeAll">Remove All</a></p>
            <p><a href="productjsp">Continue to shopping</a></p>
    </body>
</html>
