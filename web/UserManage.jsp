<%-- 
    Document   : Cart
    Created on : Oct 21, 2023, 5:18:49 PM
    Author     : Fpt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Gundam</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- Embed css here-->
        <link rel="stylesheet" href="css/styleCart.css">
        <link rel="shortcut icon" href="images/logo3.jpg">

    </head>

    <body>

        <a href="homeadmin" class="home-button">HOME</a>
        <div class="cart-content">
            <div>
                <table class="cart-table">
                    <tr class="cart-header-table">
                        <th>User ID</th>
                        <th>User Name</th>
                        <th>Full Name</th>
                        <th>Email</th>
                        <th>Phone Number</th>
                        <th>Delete/Ban</th>
  
                    </tr>
                    <c:forEach items="${user}" var="user" >
                        <tr class="cart-item">
                            <td>${user.user_id}</td>
                            <td>${user.username}</td>
                            
                            <td>${user.fullname}</td>
                            <td>${user.email}</td>
                            
                            <td>${user.fullname}</td>
                            <td>
                                <c:if test="${user.user_id == 1}">
                                    Admin
                                </c:if>
                                <c:if test="${user.user_id != 1}">
                                    <form action="deleteuser" method="post">
                                    <input type="hidden" value="${user.user_id}" name="user_id">
                                    <input type="submit" value="Delete">
                                </form>
                                </c:if>
                                
                            </td>
                            
                        </tr>
                    </c:forEach>
                    

                </table>
            </div>
        </div>





    </body>

</html>