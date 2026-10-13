<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Categories, java.util.Vector" %>
<!DOCTYPE html>
<html>

    <%
        //Get data from controller
        Vector<Categories> cVector = (Vector<Categories>) request.getAttribute("cVector");
    %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="productjsp?service=addProduct" method="POST">
            <table>
                <tr>                    
                    <td>productName:</td>
                    <td><input type="text" name="productName" id="txtxName"></td>
                </tr>
                <tr>
                    <td>image:</td>
                    <td><input type="text" name="image" id="txtimage"></td>
                </tr>
                <tr>
                    <td>price:</td>
                    <td><input type="text" name="price" id="txtprice"></td>
                </tr>
                <tr>
                    <td>quantity:</td>
                    <td><input type="text" name="quantity" id="txtquantity"></td>
                </tr>
                <tr>
                    <td>categoryID:</td>
                    <td>
                        <select name="categoryID" id="txtcategoryID">
                            <% for (Categories c : cVector) { %>
                            <option value="<%=c.getCategoryID()%>"><%=c.getCategoryName()%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>importDate:</td>
                    <td><input type="date" name="importDate" id="txtimportDate"></td>
                </tr>
                <tr>
                    <td>usingDate:</td>
                    <td><input type="date" name="usingDate" id="txtusingDate"></td>
                </tr>
                <tr>
                    <td>status:</td>
                    <td><input type="radio" name="status" id="txtstatus1" value="1" checked="">Active
                        <input type="radio" name="status" id="txtstatus0" value="0" >DeActive</td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" id="txtSubmit" value="Add Product"></td>
                    <td><input type="reset" value="Reset">
                        <input type="hidden" name="service" value="addProduct"></td>
                </tr>   
            </table>
        </form>
    </body>
</html>
