<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="roleJSP?service=addRole" method="POST">
            <table border="0">
                <thead>
                    <tr>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>roleID:</td>
                        <td><input type="text" name="roleID" id="txtId"></td>
                    </tr>
                    <tr>
                        <td>roleName:</td>
                        <td><input type="text" name="roleName" id="txtName"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="submit" id="txtSubmit" value="Add Role"></td>
                        <td><input type="reset" value="Reset">
                            <input type="hidden" name="service" value="addRole"></td>
                    </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>
