<%-- 
    Document   : EditSlider
    Created on : Aug 16, 2024, 2:04:36 PM
    Author     : ntung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Update Slider</title>
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
            <h2 class="mb-4">Update Slider</h2>
            <form id="updateSliderForm" action="UpdateSliderServlet" method="POST" enctype="multipart/form-data">
                <input type="hidden" class="form-control" id="slider_id" name="slider_id" value="${slider.slider_id}" required>
                
                <div class="mb-3">
                    <label for="slider_title" class="form-label">Slider Title</label>
                    <input type="text" class="form-control" id="slider_title" name="slider_title" value="${slider.slider_title}" required>
                </div>
                
                <div class="mb-3">
                    <label for="backlink" class="form-label">Backlink</label>
                    <input type="text" class="form-control" id="backlink" name="backlink" value="${slider.backlink}" required>
                </div>
                
                <div class="mb-3">
                    <label for="slider_img" class="form-label">Current Image</label>
                    <img src="${pageContext.request.contextPath}/${slider.slider_img}" id="currentImage" style="max-width: 200px; max-height: 200px;">
                    <input type="file" class="form-control" id="slider_img" name="slider_img" accept="image/*">
                    <input type="hidden" name="currentImagePath" value="${slider.slider_img}">
                </div>

                <div class="mb-3">
                    <label for="status" class="form-label">Status</label>
                    <select id="status" name="status" class="form-select" required>
                        <option value="1" ${slider.status == 1 ? 'selected' : ''}>Active</option>
                        <option value="0" ${slider.status == 0 ? 'selected' : ''}>Inactive</option>
                    </select>
                </div>
                
                <button type="submit" class="btn btn-primary w-100 mb-3">Update Slider</button>
                <a href="SliderList" class="btn btn-primary w-100">Back to Slider List</a>
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="scripts.js"></script>
    </body>
</html>
