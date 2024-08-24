<%-- 
    Document   : ViewCustomer
    Created on : Aug 22, 2024, 10:15:44 PM
    Author     : ntung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>View Customer</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="styles.css">
        <style>
            body {
                background-color: #f8f9fa;
            }

            .container {
                background-color: #ffffff;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                padding: 20px;
            }

            h2 {
                color: #333;
            }

            .btn-primary {
                background-color: #ff6f61;
                border: none;
            }

            .btn-primary:hover {
                background-color: #ff4b3a;
            }

            .form-label {
                font-weight: bold;
            }

            .form-control {
                border-radius: 4px;
            }

            .form-select {
                border-radius: 4px;
            }
            .star-yellow {
                color: gold;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="mb-4">View Customer</h2>
            <table class="table table-bordered">
                <tr>
                    <td><strong>User ID</strong></td>
                    <td>${us.user_id}</td>
                </tr>
                <tr>
                    <td><strong>Full Name</strong></td>
                    <td>${us.fullname}</td>
                </tr>
                <tr>
                    <td><strong>Gender</strong></td>
                    <td>
                        <c:choose>
                            <c:when test="${us.gender}">
                                Male
                            </c:when>
                            <c:otherwise>
                                Female
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td><strong>Email</strong></td>
                    <td>${us.email_address}</td>
                </tr>
                <tr>
                    <td><strong>Mobile</strong></td>
                    <td>${us.phone_number}</td>
                </tr>
                <tr>
                    <td><strong>Address</strong></td>
                    <td>${us.address}</td>
                </tr>
            </table>
            <a href="CustomerManagerServlet" class="btn btn-primary w-100">Back to Customer Management</a>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="scripts.js"></script>
    </body>
</html>


