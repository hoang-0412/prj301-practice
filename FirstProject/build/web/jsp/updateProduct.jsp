<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,model.Categories,model.Products" %>
<!DOCTYPE html>
<html>
    <%
        //Get data from controller
        Vector<Categories> cVector = (Vector<Categories>)request.getAttribute("cVector");
        Products p = (Products)request.getAttribute("p");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="productjsp?service=updateProduct" method="POST">
            <table>
                <tr>                     
                    <td>productID:</td>
                    <td><input type="text" name="productID" id="txtxId" value="<%=p.getProductID()%>" readonly=""></td>
                </tr>
                <tr>                    
                    <td>productName:</td>
                    <td><input type="text" name="productName" id="txtxName" value="<%=p.getProductName()%>"></td>
                </tr>
                <tr>
                    <td>image:</td>
                    <td><input type="text" name="image" id="txtimage" value="<%=p.getImage()%>"></td>
                </tr>
                <tr>
                    <td>price:</td>
                    <td><input type="text" name="price" id="txtprice" value="<%=p.getPrice()%>"></td>
                </tr>
                <tr>
                    <td>quantity:</td>
                    <td><input type="text" name="quantity" id="txtquantity" value="<%=p.getQuantity()%>"></td>
                </tr>
                <tr>
                    <td>categoryID:</td>
                    <td>                      
                        <select name="categoryID" id="txtcategoryID">
                            <%for (Categories c : cVector) {%>
                            <option value="<%=c.getCategoryID()%>"
                                    <%=c.getCategoryID().equals(p.getCategoryID())?"selected":""%>>         
                                <%=c.getCategoryName()%></option> 
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>importDate:</td>
                    <td><input type="date" name="importDate" id="txtimportDate" value="<%=p.getImportDate()%>"></td>
                </tr>
                <tr>
                    <td>usingDate:</td>
                    <td><input type="date" name="usingDate" id="txtusingDate" value="<%=p.getUsingDate()%>"></td>
                </tr>
                <tr>
                    <td>status:</td>
                    <td><input type="radio" name="status" id="txtstatus1" value="1" <%=p.getStatus()==1?"checked":""%> >Active
                    <input type="radio" name="status" id="txtstatus0" value="0" <%=p.getStatus()==0?"checked":""%>>DeActive</td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" id="txtSubmit" value="Update Product"></td>
                    <td><input type="reset" value="Reset">
                        <input type="hidden" name="service" value="updateProduct"></td>
                
                </tr>
            </table>
        </form>
    </body>
</html>
