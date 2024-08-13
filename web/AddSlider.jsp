<%-- 
    Document   : AddSlider
    Created on : Aug 7, 2024, 8:31:47 PM
    Author     : ntung
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Slider</title>
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
        <h1>Add New Slider</h1>
        
        <form action="AddSlider" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="sliderImg">Slider Image:</label>
                <input type="file" id="sliderImg" name="sliderImg" required/>
            </div>
            <div class="form-group">
                <label for="backlink">Backlink:</label>
                <input type="text" id="backlink" name="backlink" required/>
            </div>
            <div class="form-group">
                <label for="sliderTitle">Slider Title:</label>
                <input type="text" id="sliderTitle" name="sliderTitle" required/>
            </div>
            <div class="form-group">
                <label for="sliderDetail">Slider Detail:</label>
                <textarea id="sliderDetail" name="sliderDetail" rows="5" required></textarea>
            </div>
            <div class="form-group">
                <label for="updateBy">Updated By (User ID):</label>
                <input type="number" id="updateBy" name="updateBy" required/>
            </div>
            <div class="form-group">
                <label for="status">Status:</label>
                <select id="status" name="status">
                    <option value="1">Active</option>
                    <option value="0">Inactive</option>
                </select>
            </div>
            <div class="form-group">
                <button type="submit">Add Slider</button>
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
