<%-- 
    Document   : serviceManagement
    Created on : Aug 7, 2024, 7:38:04 PM
    Author     : lebac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Service Management</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.3/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.3/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <style>
            body {
                background-color: #f4f7f6;
                font-family: Arial, sans-serif;
            }
            .container {
                margin-top: 30px;
            }
            .table-responsive {
                background-color: #ffffff;
                padding: 20px;
                border-radius: 0.5rem;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                border: 1px solid #dcdcdc; /* Light grey border */
            }
            .table {
                table-layout: fixed; /* Fix the table layout to ensure consistent column widths */
            }
            .table thead {
                background-color: #343a40;
                color: #ffffff;
            }
            .table thead th {
                border-bottom: 2px solid #dee2e6; /* Slightly darker border for header */
                padding: 15px;
                text-align: center;
            }
            .table tbody td, .table tbody th {
                border: 1px solid #dee2e6; /* Border for cells */
                padding: 10px;
                vertical-align: middle;
            }
            .table tbody tr:hover {
                background-color: #f1f1f1; /* Highlight row on hover */
            }
            .btn-primary {
                background-color: #ff6600;
                border-color: #ff6600;
            }
            .btn-primary:hover {
                background-color: #e65c00;
                border-color: #e65c00;
            }
            .btn {
                margin: 0 5px; /* Add margin to buttons */
            }
            img {
                border-radius: 0.25rem; /* Rounded corners for images */
            }
            .service-image {
                max-width: 100px;
                max-height: 100px;
            }
            .status-active {
                color: #28a745;
                font-weight: bold;
            }
            .status-inactive {
                color: #dc3545;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2 class="mb-4 text-center">Service Management</h2>
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th scope="col" style="width: 120px;">Service ID</th>
                            <th scope="col" style="width: 150px;">Service Name</th>
                            <!--
                            <th scope="col" style="width: 150px;">Original Prices</th>
                            <th scope="col" style="width: 150px;">Sale Prices</th>
                            <th scope="col" style="width: 100px;">Quantity</th>
                            <th scope="col" style="width: 120px;">Thumbnail</th>
                            <th scope="col" style="width: 200px;">Brief Info</th>
                            -->
                            <th scope="col" style="width: 200px;">Category</th>

                            <th scope="col" style="width: 200px;">Service Detail</th>
                            <!--                        <th scope="col" style="width: 150px;">Date Added</th>-->

                            <th scope="col" style="width: 150px;">Date Added</th>
                            <th scope="col" style="width: 100px;">Status</th>
                            <th scope="col" style="width: 150px;">Image Service</th>
                            <th scope="col" style="width: 200px;">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="service" items="${service}">
                            <tr>
                                <th scope="row">${service.service_id}</th>
                                <td>${service.name_service}</td>
                                <!--
                                <td>${service.original_prices}</td>
                                <td>${service.sale_prices}</td>
                                <td>${service.quantity}</td>
                                <td>${service.thumbnail}</td>
                                -->
                                <td>${service.category_name}</td>
                                <!--
                                <td>${service.brief_infor}</td>
                                -->
                                <td>${service.service_detail}</td>
                                <td>${service.date_add}</td>
                                <td class="${service.service_Status == 1 ? 'status-active' : 'status-inactive'}">
                                    ${service.service_Status == 1 ? 'Active' : 'Inactive'}
                                </td>
                                <td>
                                    <c:if test="${not empty service.img_service}">
                                        <img src="${service.img_service}" alt="Image Service" class="service-image">
                                    </c:if>
                                </td>
                                <td>
                                    <!-- Nút View -->
                                    <a href="ViewServiceServlet?id=${service.service_id}" class="btn btn-info btn-sm">
                                        <i class="fas fa-eye"></i>
                                    </a>

                                    <!-- Nút Update -->
                                    <a href="UpdateServiceServlet?id=${service.service_id}" class="btn btn-warning btn-sm">
                                        <i class="fas fa-edit"></i>
                                    </a>

                                    <!-- Nút Delete -->
                                    <a href="javascript:void(0);" class="btn btn-danger btn-sm" onclick="confirmDelete('${service.service_id}');">
                                        <i class="fas fa-trash-alt"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <script>
            function confirmDelete(serviceId) {
                var result = confirm("Are you sure you want to delete this service?");
                if (result) {
                    window.location.href = "DeleteServiceServlet?id=" + serviceId;
                }
            }
        </script>
    </body>
</html>