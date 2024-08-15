<%-- 
    Document   : updatePost
    Created on : Aug 15, 2024, 1:42:29 PM
    Author     : lebac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Update Post</title>
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
            <h2 class="mb-4">Update Post</h2>
            <form id="addProductForm" action="UpdatePostServlet" method="POST" enctype="multipart/form-data">
                <input type="hidden" class="form-control" id="blog_id" name="blog_id" value="${blog.blog_id}" required>
                <div class="mb-3">
                    <label for="tittle" class="form-label">Title</label>
                    <input type="text" class="form-control" id="tittle" name="tittle" value="${blog.tittle}" required>
                </div>
                <div class="mb-3">
                    <label for="author_id" class="form-label">Author</label>
                    <select id="author_id" name="author_id" class="form-select" required>
                        <c:forEach var="u" items="${u}">
                            <option value="${u.user_id}">${u.fullname}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="category_id" class="form-label">Category</label>
                    <select id="category_id" name="category_id" class="form-select" required>
                        <c:forEach var="cate" items="${cate}">
                            <option value="${cate.category_id}">${cate.category_name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="content" class="form-label">Content</label>
                    <textarea class="form-control" id="content" name="content" rows="2">${blog.content}</textarea>
                </div>
                <div class="mb-3">
                    <label for="brief_infor" class="form-label">Brief Information</label>
                    <textarea class="form-control" id="brief_infor" name="brief_infor" rows="3">${blog.brief_infor}</textarea>
                </div>
                <div class="mb-3">
                    <label for="status" class="form-label">Status</label>
                    <select class="form-select" id="status" name="status" required>
                        <option value="1" ${blog.status == 1 ? 'selected' : ''}>Active</option>
                        <option value="2" ${blog.status == 2 ? 'selected' : ''}>Inactive</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="img_service" class="form-label">Image Service</label>
                    <img src="${pageContext.request.contextPath}/${blog.thumbnail}" id="currentImage" style="max-width: 200px; max-height: 200px;">
                    <input type="file" class="form-control" id="img_service" name="img_service" accept="image/*">
                    <input type="hidden" name="currentImagePath" value="${blog.thumbnail}">

                </div>
                <button type="submit" class="btn btn-primary w-100 mb-3">Update Post</button>
                <a href="PostManagementServlet" class="btn btn-primary w-100">Back to Post Management</a>
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="scripts.js"></script>
    </body>
</html>
