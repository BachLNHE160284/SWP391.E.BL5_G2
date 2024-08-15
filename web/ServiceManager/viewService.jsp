<%-- 
    Document   : viewService
    Created on : Aug 8, 2024, 7:34:47 PM
    Author     : lebac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="mb-4">View Service</h2>
            <form id="addProductForm" action="AddServiceServlet" method="POST" enctype="multipart/form-data">
                <table class="table table-bordered">
                    <tr>
                        <td><strong>Service Name</strong></td>
                        <td>${service.name_service}</td>
                    </tr>
                    <tr>
                        <td><strong>Original Prices</strong></td>
                        <td><fmt:formatNumber value="${service.original_prices}" type="number" groupingUsed="true" minFractionDigits="0" />đ</td>
                    </tr>
                    <tr>
                        <td><strong>Sale Prices</strong></td>
                        <td><fmt:formatNumber value="${service.sale_prices}" type="number" groupingUsed="true" minFractionDigits="0" />đ</td>
                    </tr>
                    <tr>
                        <td><strong>Quantity</strong></td>
                        <td>${service.quantity}</td>
                    </tr>
                    <tr>
                        <td><strong>Category</strong></td>
                        <td>${service.category.category_name}</td>
                    </tr>
                    <tr>
                        <td><strong>Thumbnail</strong></td>
                        <td>${service.thumbnail}</td>
                    </tr>
                    <tr>
                        <td><strong>Brief Information</strong></td>
                        <td>${service.brief_infor}</td>
                    </tr>
                    <tr>
                        <td><strong>Service Detail</strong></td>
                        <td>${service.service_detail}</td>
                    </tr>
                    <tr>
                        <td><strong>Update Date</strong></td>
                        <td id="dateAdd">${service.date_add}</td>
                    </tr>
                    <tr>
                        <td><strong>Create Date</strong></td>
                        <td id="dateCreate">${service.create_date}</td>
                    </tr>
                    <c:if test="${not empty service.img_service}">

                        <tr>
                            <td><strong>Service Image</strong></td>
                            <td colspan="2">
                                <img id="img_service" name="img_service" src="${pageContext.request.contextPath}/${service.img_service}" width="200" />
                            </td>
                        </tr>
                    </c:if>
                </table>
                <a href="ServiceManagementServlet" class="btn btn-primary w-100">Back to Service Management</a>
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="scripts.js"></script>
        <script>
            var updateDateString = document.getElementById("dateAdd").innerText;
            var updateDate = new Date(updateDateString);

            var formattedUpdateDate = ("0" + updateDate.getDate()).slice(-2) + "/" +
                    ("0" + (updateDate.getMonth() + 1)).slice(-2) + "/" +
                    updateDate.getFullYear();

            document.getElementById("dateAdd").innerText = formattedUpdateDate;

// Định dạng ngày tháng cho Create Date
            var createDateString = document.getElementById("dateCreate").innerText;
            var createDate = new Date(createDateString);

            var formattedCreateDate = ("0" + createDate.getDate()).slice(-2) + "/" +
                    ("0" + (createDate.getMonth() + 1)).slice(-2) + "/" +
                    createDate.getFullYear();

            document.getElementById("dateCreate").innerText = formattedCreateDate;
        </script>
    </body>
</html>
