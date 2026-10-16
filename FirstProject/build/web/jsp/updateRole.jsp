<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Roles" %>
<!DOCTYPE html>
<html>
    
    <%
        Roles r = (Roles) request.getAttribute("r");
    %>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="roleJSP?service=updateRole" method="POST">
            <table border="0">
                <tbody>
                    <tr>
                        <td>roleID:</td>
                        <td><input type="text" name="roleID" id="txtId" value="<%=r.getRoleID()%>" readonly=""></td>
                    </tr>
                    <tr>
                        <td>roleName:</td>
                        <td><input type="text" name="roleName" id="txtName" value="<%=r.getRoleName()%>"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="submit" id="txtSubmit" value="Update Role"></td>
                        <td><input type="reset" value="Reset">
                            <input type="hidden" name="service" value="updateRole"></td>
                    </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>
