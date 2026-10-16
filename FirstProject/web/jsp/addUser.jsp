<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="userJSP?service=addUser" method="POST">
            <table border="1">
                <tbody>
                    <tr>
                        <td>userID:</td>
                        <td><input type="text" name="userID" id="txtId"></td>
                    </tr>
                    <tr>
                        <td>fullName:</td>
                        <td><input type="text" name="fullName" id="txtName"></td>
                    </tr>
                    <tr>
                        <td>password:</td>
                        <td><input type="text" name="password" id="txtPassword"></td>
                    </tr>
                    <tr>
                        <td>roleID:</td>
                        <td><input type="text" name="roleID" id="txtRoleID"></td>
                    </tr>
                    <tr>
                        <td>address:</td>
                        <td><input type="text" name="address" id="txtAddress"></td>
                    </tr>
                    <tr>
                        <td>phone:</td>
                        <td><input type="text" name="phone" id="txtPhone"></td>
                    </tr>
                    <tr>
                        <td>email:</td>
                        <td><input type="text" name="email" id="txtEmail"></td>
                    </tr>
                    <tr>
                        <td>activate:</td>
                        <td><input type="text" name="activate" id="txtActivate"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="submit" id="txtSubmit" value="Add User"></td>
                        <td><input type="reset" value="Reset">
                            <input type="hidden" name="service" value="addUser"></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
