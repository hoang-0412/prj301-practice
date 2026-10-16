<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Categories" %>
<!DOCTYPE html>
<html>
    <%
        //Get data from controller
        Categories c = (Categories) request.getAttribute("c");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Category</title>
    </head>
    <body>
        <form action="categoryJSP?service=updateCategory" method="POST">
            <table>
                <tr>                    
                    <td>categoryID:</td>
                    <td><input type="text" name="categoryID" id="txtcategoryID" value="<%=c.getCategoryID()%>" readonly=""></td>
                </tr>
                <tr>
                    <td>categoryName:</td>
                    <td><input type="text" name="categoryName" id="txtcategoryName" value="<%=c.getCategoryName()%>"></td>
                </tr>
                <tr>
                    <td>describe:</td>
                    <td><input type="text" name="describe" id="txtdescribe" value="<%=c.getDescribe()%>"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" id="txtSubmit" value="Update Category"></td>
                    <td><input type="reset" value="Reset">
                        <input type="hidden" name="service" value="updateCategory"></td>
                </tr>   
            </table>
        </form>
    </body>
</html>
