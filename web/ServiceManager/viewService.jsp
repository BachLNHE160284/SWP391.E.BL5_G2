<%-- 
    Document   : viewService
    Created on : Aug 8, 2024, 7:34:47 PM
    Author     : lebac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>View Service</title>
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
        <div class="container mt-5">
            <h2 class="mb-4">View Service</h2>
            <form id="addProductForm" action="AddServiceServlet" method="POST" enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="name_service" class="form-label">Service Name</label>
                    <input type="text" class="form-control" id="name_service" name="name_service" value="${service.name_service}" readonly required>
                </div>
                <div class="mb-3">
                    <label for="original_prices" class="form-label">Original Prices</label>
                    <input type="number" step="0.01" class="form-control" id="original_prices" name="original_prices" value="${service.original_prices}" readonly required>
                </div>
                <div class="mb-3">
                    <label for="sale_prices" class="form-label">Sale Prices</label>
                    <input type="number" step="0.01" class="form-control" id="sale_prices" name="sale_prices" value="${service.sale_prices}" readonly required>
                </div>
                <div class="mb-3">
                    <label for="quantity" class="form-label">Quantity</label>
                    <input type="number" class="form-control" id="quantity" name="quantity" value="${service.quantity}" readonly required>
                </div>
                <div class="mb-3">
                    <label for="category_name" class="form-label">Category</label>
                    <input type="text" class="form-control" id="category_name" name="category_name" value="${service.category.category_name}" readonly required>
                </div>
                
                <div class="mb-3">
                    <label for="thumbnail" class="form-label">Thumbnail</label>
                    <input type="text" class="form-control" id="thumbnail" name="thumbnail" value="${service.thumbnail}" readonly>
                </div>
                <div class="mb-3">
                    <label for="brief_infor" class="form-label">Brief Information</label>
                    <textarea class="form-control" id="brief_infor" name="brief_infor" rows="2" readonly>${service.thumbnail}</textarea>
                </div>
                <div class="mb-3">
                    <label for="service_detail" class="form-label">Service Detail</label>
                    <textarea class="form-control" id="service_detail" name="service_detail" rows="3" readonly>${service.service_detail}</textarea>
                </div>
                <div class="mb-3">
                    <label for="date_add" class="form-label">Update Date</label>
                    <input type="text" class="form-control" id="date_add" name="date_add" value="${service.date_add}" readonly>
                </div>
                <div class="mb-3">
                    <label for="create_date" class="form-label">Create Date</label>
                    <input type="text" class="form-control" id="create_date" name="create_date" value="${service.create_date}" readonly>
                </div>
                <c:if test="${not empty service.img_service}">
                        <div class="mb-3">
                            <label for="img_service" class="form-label"></label>
                            <img id="img_service" name="img_service" src="${pageContext.request.contextPath}/${service.img_service}" width="200" />
                        </div>
                    </c:if>
                <a href="ServiceManagementServlet" class="btn btn-primary w-100">Back to Service Management</a>
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="scripts.js"></script>
    </body>
</html>
