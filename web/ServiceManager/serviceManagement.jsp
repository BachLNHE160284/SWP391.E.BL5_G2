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
                border: 1px solid #dcdcdc;
            }
            .table {
                table-layout: fixed;
            }
            .table thead {
                background-color: #343a40;
                color: #ffffff;
            }
            .table thead th {
                border-bottom: 2px solid #dee2e6;
                padding: 15px;
                text-align: center;
            }
            .table tbody td, .table tbody th {
                border: 1px solid #dee2e6;
                padding: 10px;
                vertical-align: middle;
            }
            .table tbody tr:hover {
                background-color: #f1f1f1;
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
                margin: 0 5px;
            }
            img {
                border-radius: 0.25rem;
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

            .pagination {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }
            .pagination a {
                display: inline-block;
                padding: 8px 16px;
                margin: 0 4px;
                border: 1px solid #dee2e6;
                border-radius: 0.25rem;
                text-decoration: none;
                color: #007bff;
                background-color: #ffffff;
                font-size: 14px;
            }
            .pagination a.active {
                color: #ffffff;
                background-color: #007bff;
                border-color: #007bff;
            }
            .pagination a:hover {
                background-color: #e9ecef;
            }
            .pagination a:focus, .pagination a:active {
                outline: none;
                box-shadow: 0 0 0 0.2rem rgba(38, 143, 255, 0.25);
            }

            .search-sort-container {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
                padding: 10px;
                background-color: #ffffff;
                border-radius: 0.5rem;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            .search-form {
                display: flex;
                align-items: center;
            }
            .search-form input[type="text"] {
                padding: 8px 12px;
                border-radius: 0.25rem;
                border: 1px solid #dcdcdc;
                font-size: 14px;
                margin-right: 10px;
                width: 300px;
            }
            .search-form button {
                padding: 8px 16px;
                border: none;
                border-radius: 0.25rem;
                background-color: #007bff;
                color: #ffffff;
                font-size: 14px;
                cursor: pointer;
            }
            .search-form button:hover {
                background-color: #0056b3;
            }
            .sort-controls {
                display: flex;
                align-items: center;
            }
            .sort-controls label {
                margin-right: 10px;
                font-size: 14px;
            }
            .sort-controls select {
                padding: 8px 12px;
                border-radius: 0.25rem;
                border: 1px solid #dcdcdc;
                font-size: 14px;
                margin-right: 10px;
            }
            .sort-controls select:focus {
                border-color: #007bff;
                outline: none;
            }

            .btn-custom {
                display: inline-flex;
                align-items: center;
                padding: 10px 20px;
                border: none;
                border-radius: 0.25rem;
                background-color: #ff6600; /* Màu nền chính */
                color: #ffffff; /* Màu chữ */
                font-size: 16px;
                font-weight: bold;
                text-decoration: none;
                cursor: pointer;
                transition: background-color 0.3s, box-shadow 0.3s;
                margin-bottom: 20px; /* Khoảng cách dưới nút */
            }
            .btn-custom:hover {
                background-color: #e65c00; /* Màu nền khi di chuột qua */
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Hiệu ứng bóng */
            }
            .btn-custom i {
                margin-right: 8px; /* Khoảng cách giữa biểu tượng và chữ */
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2 class="mb-4 text-center">Service Management</h2>
            <a href="AddServiceServlet" class="btn-custom" title="Add Service">
                <i class="fas fa-plus"></i> Add Service
            </a>

            <form id="searchSortForm" action="ServiceManagementServlet" method="get" class="search-sort-container">
                <!-- Search Form -->
                <div class="search-form">
                    <input type="text" name="search" placeholder="Search by service name" value="${param.search}">
                    <button type="submit" class="btn-search">Search</button>
                </div>

                <!-- Sorting Controls -->
                <div class="sort-controls">
                    <label for="sortField">Sort by:</label>
                    <select id="sortField" name="sortField" onchange="submitSortingForm()">
                        <option value="service_id" ${param.sortField == 'service_id' ? 'selected' : ''}>Service ID</option>
                        <option value="name_service" ${param.sortField == 'name_service' ? 'selected' : ''}>Service Name</option>
                        <option value="category_name" ${param.sortField == 'category_name' ? 'selected' : ''}>Category Name</option>
                        <option value="date_add" ${param.sortField == 'date_add' ? 'selected' : ''}>Date Add</option>
                    </select>
                    <select id="sortOrder" name="sortOrder" onchange="submitSortingForm()">
                        <option value="ASC" ${param.sortOrder == 'ASC' ? 'selected' : ''}>Ascending</option>
                        <option value="DESC" ${param.sortOrder == 'DESC' ? 'selected' : ''}>Descending</option>
                    </select>
                </div>
            </form>
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th scope="col" style="width: 120px;">Service ID</th>
                            <th scope="col" style="width: 150px;">Service Name</th>
                            <th scope="col" style="width: 200px;">Category</th>
                            <th scope="col" style="width: 200px;">Service Detail</th>
                            <th scope="col" style="width: 150px;">Date Added</th>
                            <th scope="col" style="width: 100px;">Status</th>
                            <th scope="col" style="width: 150px;">Image Service</th>
                            <th scope="col" style="width: 200px;">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="services" items="${services}">
                            <tr>
                                <th scope="row">${services.service_id}</th>
                                <td>${services.name_service}</td>
                                <td>${services.category_name}</td>
                                <td>${services.service_detail}</td>
                                <td class="dateAdd">${services.date_add}</td>
                                <td class="${services.service_Status == 1 ? 'status-active' : 'status-inactive'}">
                                    ${services.service_Status == 1 ? 'Active' : 'Inactive'}
                                </td>
                                <td>
                                    <c:if test="${not empty services.img_service}">
                                        <img src="${services.img_service}" alt="Image Service" class="service-image">
                                    </c:if>
                                </td>
                                <td>
                                    <a href="ViewServiceServlet?id=${services.service_id}" class="btn btn-info btn-sm">
                                        <i class="fas fa-eye"></i>
                                    </a>
                                    <a href="UpdateServiceServlet?id=${services.service_id}" class="btn btn-warning btn-sm">
                                        <i class="fas fa-edit"></i>
                                    </a>
                                    <a href="javascript:void(0);" class="btn btn-danger btn-sm" onclick="confirmDelete('${services.service_id}');">
                                        <i class="fas fa-trash-alt"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="pagination">
                    <c:forEach begin="1" end="${endPage}" var="i">
                        <c:url value="ServiceManagementServlet" var="pageUrl">
                            <c:param name="index" value="${i}"/>
                            <c:param name="search" value="${param.search}"/>
                            <c:param name="sortField" value="${param.sortField}"/>
                            <c:param name="sortOrder" value="${param.sortOrder}"/>
                        </c:url>
                        <a class="${index == i ? 'active' : ''}" href="${pageUrl}">${i}</a>
                    </c:forEach>
                </div>
            </div>
        </div>
        <a href="ManagerDashboard.jsp" class="btn-custom" title="Back">
             Back
        </a>
        <script>
            function confirmDelete(serviceId) {
                var result = confirm("Are you sure you want to delete this service?");
                if (result) {
                    window.location.href = "DeleteServiceServlet?id=" + serviceId;
                }
            }
            function submitSortingForm() {
                // Submit the form when sortField or sortOrder changes
                document.getElementById('searchSortForm').submit();
            }

            document.getElementById('searchInput').addEventListener('input', function () {
                // Submit the form when user types in the search field
                submitSortingForm();
            });

            document.getElementById('sortField').addEventListener('change', function () {
                // Submit the form when sortField changes
                submitSortingForm();
            });

            document.getElementById('sortOrder').addEventListener('change', function () {
                // Submit the form when sortOrder changes
                submitSortingForm();
            });

            document.querySelectorAll('.dateAdd').forEach(function (element) {
                var dateString = element.innerText;
                var date = new Date(dateString);

                if (!isNaN(date)) { // Kiểm tra nếu giá trị ngày hợp lệ
                    var formattedDate = ("0" + date.getDate()).slice(-2) + "/" +
                            ("0" + (date.getMonth() + 1)).slice(-2) + "/" +
                            date.getFullYear();

                    element.innerText = formattedDate;
                }
            });

        </script>
    </body>
</html>