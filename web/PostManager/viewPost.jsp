<%-- 
    Document   : viewPost
    Created on : Aug 15, 2024, 12:50:40 PM
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
        <title>View Blog</title>
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
            <h2 class="mb-4">View Blog</h2>
            <form id="addProductForm" action="AddServiceServlet" method="POST" enctype="multipart/form-data">
                <table class="table table-bordered">
                    <tr>
                        <td><strong>Post ID</strong></td>
                        <td>${blog.blog_id}</td>
                    </tr>
                    <tr>
                        <td><strong>Title</strong></td>
                        <td>${blog.tittle}</td>
                    </tr>
                    <tr>
                        <td><strong>Content</strong></td>
                        <td>${blog.content}</td>
                    </tr>
                    <tr>
                        <td><strong>Update Date</strong></td>
                        <td>${blog.update_date}</td>
                    </tr>
                    <tr>
                        <td><strong>Create Date</strong></td>
                        <td>${blog.create_date}</td>
                    </tr>
                    <tr>
                        <td><strong>Brief Information</strong></td>
                        <td>${blog.brief_infor}</td>
                    </tr>
                    <tr>
                        <td><strong>Category ID</strong></td>
                        <td>${blog.category_id}</td>
                    </tr>
                    <tr>
                        <td><strong>Category Name</strong></td>
                        <td>${blog.category_name}</td>
                    </tr>
                    <tr>
                        <td><strong>Status</strong></td>
                        <td>
                            <c:choose>
                                <c:when test="${blog.status == 1}">
                                    <span style="color: green; font-weight: bold;">Active</span>
                                </c:when>
                                <c:otherwise>
                                    <span style="color: red; font-weight: bold;">Inactive</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <c:if test="${not empty blog.thumbnail}">
                        <tr>
                            <td><strong>Image</strong></td>
                            <td>
                                <img id="img_service" name="img_service" src="${pageContext.request.contextPath}/${blog.thumbnail}" width="200" />
                            </td>
                        </tr>
                    </c:if>
                </table>
                <a href="PostManagementServlet" class="btn btn-primary w-100">Back to Post Management</a>
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="scripts.js"></script>
    </body>
</html>
