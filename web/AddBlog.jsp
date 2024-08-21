<%-- 
    Document   : AddBlog
    Created on : Aug 7, 2024, 8:31:47 PM
    Author     : ntung
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New Blog</title>
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
            max-width: 600px;
            margin-top: 50px;
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
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

        .form-control,
        .form-select {
            border-radius: 4px;
        }

        .success-message,
        .error-message {
            font-weight: bold;
            margin-top: 15px;
        }

        .success-message {
            color: green;
        }

        .error-message {
            color: red;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Add New Blog</h1>
        
        <form action="AddBlog" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="title" class="form-label">Title</label>
                <input type="text" class="form-control" id="title" name="title" required>
            </div>
            <div class="mb-3">
                <label for="content" class="form-label">Content</label>
                <textarea class="form-control" id="content" name="content" rows="5" required></textarea>
            </div>
            <div class="mb-3">
                <label for="authorId" class="form-label">Author ID</label>
                <input type="number" class="form-control" id="authorId" name="authorId" required>
            </div>
            <div class="mb-3">
                <label for="updatedBy" class="form-label">Updated By (User ID)</label>
                <input type="number" class="form-control" id="updatedBy" name="updatedBy">
            </div>
            <div class="mb-3">
                <label for="thumbnail" class="form-label">Thumbnail URL</label>
                <input type="file" class="form-control" id="thumbnail" name="thumbnail">
            </div>
            <div class="mb-3">
                <label for="briefInfo" class="form-label">Brief Information</label>
                <textarea class="form-control" id="briefInfo" name="briefInfo" rows="2"></textarea>
            </div>
            <div class="mb-3">
                <label for="category_id" class="form-label">Category</label>
                <select id="category_id" name="category_id" class="form-select" required>
                    <c:forEach var="category" items="${cate}">
                        <option value="${category.category_id}">${category.category_name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="status" class="form-label">Status</label>
                <select id="status" name="status" class="form-select">
                    <option value="1">Active</option>
                    <option value="0">Inactive</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary w-100 mb-3">Add Blog</button>
            <a href="PostManagementServlet" class="btn btn-primary w-100">Back to Blog Management</a>
        </form>
        
        <%
            String successMessage = request.getAttribute("successMessage") != null ? (String) request.getAttribute("successMessage") : "";
            String errorMessage = request.getAttribute("errorMessage") != null ? (String) request.getAttribute("errorMessage") : "";

            if (!successMessage.isEmpty()) {
        %>
        <p class="success-message"><%= successMessage %></p>
        <% 
            } 
            if (!errorMessage.isEmpty()) { 
        %>
        <p class="error-message"><%= errorMessage %></p>
        <% 
            } 
        %>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="scripts.js"></script>
</body>
</html>
