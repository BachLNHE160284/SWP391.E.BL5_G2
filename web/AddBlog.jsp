<%-- 
    Document   : AddBlog
    Created on : Aug 7, 2024, 8:31:47 PM
    Author     : ntung
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Blog</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .container {
            max-width: 600px;
            margin: auto;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .form-group input,
        .form-group textarea,
        .form-group select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .form-group textarea {
            resize: vertical;
        }
        .form-group button {
            padding: 10px 15px;
            background-color: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
        }
        .form-group button:hover {
            background-color: #0056b3;
        }
        .success-message {
            color: green;
            font-weight: bold;
        }
        .error-message {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Add New Blog</h1>
        
        <form action="AddBlog" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" id="title" name="title" required/>
            </div>
            <div class="form-group">
                <label for="content">Content:</label>
                <textarea id="content" name="content" rows="5" required></textarea>
            </div>
            <div class="form-group">
                <label for="authorId">Author ID:</label>
                <input type="number" id="authorId" name="authorId" required/>
            </div>
            <div class="form-group">
                <label for="updatedBy">Updated By (User ID):</label>
                <input type="number" id="updatedBy" name="updatedBy"/>
            </div>
            <div class="form-group">
                <label for="thumbnail">Thumbnail URL:</label>
                <input type="file" class="form-control-file" id="thumbnail" name="thumbnail"/>
                
            </div>
            <div class="form-group">
                <label for="briefInfo">Brief Information:</label>
                <input type="text" id="briefInfo" name="briefInfo"/>
            </div>
            <div class="form-group">
                <label for="category_id" class="form-label">Category</label>
                <select id="category_id" name="category_id" class="form-select" required>
                    <c:forEach var="category" items="${cate}">
                                <option value="${category.category_id}">${category.category_name}</option>
                            </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="status">Status:</label>
                <select id="status" name="status">
                    <option value="1">Active</option>
                    <option value="0">Inactive</option>
                </select>
            </div>
            <div class="form-group">
                <button type="submit">Add Blog</button>
            </div>
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
</body>
</html>

