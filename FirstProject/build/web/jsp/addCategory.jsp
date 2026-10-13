<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Category</title>
    </head>
    <body>
        <form action="categoryJSP?service=addCategory" method="POST">
            <table>
                <tr>                    
                    <td>categoryID:</td>
                    <td><input type="text" name="categoryID" id="txtcategoryID"></td>
                </tr>
                <tr>
                    <td>categoryName:</td>
                    <td><input type="text" name="categoryName" id="txtcategoryName"></td>
                </tr>
                <tr>
                    <td>describe:</td>
                    <td><input type="text" name="describe" id="txtdescribe"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" id="txtSubmit" value="Add Category"></td>
                    <td><input type="reset" value="Reset">
                        <input type="hidden" name="service" value="addCategory"></td>
                </tr>   
            </table>
        </form>
    </body>
</html>
