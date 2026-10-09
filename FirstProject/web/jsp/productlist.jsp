<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, model.Products" %>
<!DOCTYPE html>
<html>
    <%
        //java code here
        //Get data from controller
        Vector<Products> vector = (Vector<Products>)request.getAttribute("vector");
        String tableTitle = (String)request.getAttribute("tableTitle");
        String pageTtile = (String)request.getAttribute("pageTtile");
    %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=pageTtile%></title>
    </head>
    <body>
        <p><a href="addProduct.html">Insert a new Product</a></p>
        <form action="productjsp">
            Search By Product Name: <input type="text" name="productName" id="txtName">
            <input type="submit" name="submit" id="txtSubmit" value="Search">
            <input type="reset" value="Reset">
            <input type="hidden" name="service" value="listAllProduct">
        </form>
        <table border="1">
            <caption><%=tableTitle%></caption>
            <thead>
                <tr>                    
                    <th>productID</th>
                    <th>productName</th>
                    <th>image</th>
                    <th>price</th>
                    <th>quantity</th>
                    <th>categoryID</th>
                    <th>importDate</th> 
                    <th>usingDate</th>
                    <th>status</th>
                    <th>delete</th>
                    <th>update</th>
                </tr>
            </thead>
            <tbody>                
                <%for (Products p : vector) {%>
                <tr>
                    <td><%=p.getProductID()%></td>
                    <td><%=p.getProductName()%></td>
                    <td><%=p.getImage()%></td>
                    <td><%=p.getPrice()%></td>
                    <td><%=p.getQuantity()%></td>
                    <td><%=p.getCategoryID()%></td>
                    <td><%=p.getImportDate()%></td>
                    <td><%=p.getUsingDate()%></td>
                    <td><%=p.getStatus()%></td>
                    <td></td>
                    <td></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
