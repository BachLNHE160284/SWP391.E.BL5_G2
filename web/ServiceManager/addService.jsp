<%-- 
    Document   : addService
    Created on : Aug 7, 2024, 6:28:20 PM
    Author     : lebac
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.User"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Service</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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
        </style>
    </head>
    <body>
        <%
    Object obj = session.getAttribute("acc");
    User user = null;
    if (obj != null) {
        user = (User) obj;
    }
    if (user == null || user.getRole_id() != 4) {
        response.sendRedirect("Login.jsp"); 
    } else {
        %>
        <div class="container mt-5">
            <h2 class="mb-4">Add New Service</h2>
            <form id="addProductForm" action="AddServiceServlet" method="POST" enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="name_service" class="form-label">Service Name</label>
                    <input type="text" class="form-control" id="name_service" name="name_service" required>
                </div>
                <div class="mb-3">
                    <label for="original_prices" class="form-label">Original Prices</label>
                    <input type="number" step="0.01" class="form-control" id="original_prices" name="original_prices" required>
                </div>
                <div class="mb-3">
                    <label for="sale_prices" class="form-label">Sale Prices</label>
                    <input type="number" step="0.01" class="form-control" id="sale_prices" name="sale_prices" required>
                </div>
                <div class="mb-3">
                    <label for="quantity" class="form-label">Quantity</label>
                    <input type="number" class="form-control" id="quantity" name="quantity" required>
                </div>
                <div class="mb-3">
                    <label for="category_id" class="form-label">Category</label>
                    <select id="category_id" name="category_id" class="form-select" required>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.category_id}">${category.category_name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="thumbnail" class="form-label">Thumbnail</label>
                    <input type="text" class="form-control" id="thumbnail" name="thumbnail">
                </div>
                <div class="mb-3">
                    <label for="brief_infor" class="form-label">Brief Information</label>
                    <textarea class="form-control" id="brief_infor" name="brief_infor" rows="2"></textarea>
                </div>
                <div class="mb-3">
                    <label for="service_detail" class="form-label">Service Detail</label>
                    <textarea class="form-control" id="service_detail" name="service_detail" rows="3"></textarea>
                </div>
                <div class="mb-3">
                    <label for="img_service" class="form-label">Image Service</label>
                    <input type="file" class="form-control" id="img_service" name="img_service" accept="image/*">
                </div>
                <button type="submit" class="btn btn-primary w-100 mb-3">Add Service</button>
                <a href="ServiceManagementServlet" class="btn btn-primary w-100">Back to Service Management</a>
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="scripts.js"></script>
        <%
        }
        %>
    </body>
</html>