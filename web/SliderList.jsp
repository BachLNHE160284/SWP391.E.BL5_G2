<%-- 
    Document   : SliderList
    Created on : Aug 16, 2024, 12:59:56 AM
    Author     : ntung
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Slider List</title>
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

            .table img {
                width: 100px;
                height: auto;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="mb-4">Slider List</h2>
            <form action="SliderList" method="GET">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Slider ID</th>
                            <th>Title</th>
                            <th>Image</th>
                            <th>Backlink</th>
                            <th>Status</th>
                            <th>Actions</th> <!-- New Actions column -->
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="slider" items="${sliders}">
                            <tr>
                                <td>${slider.slider_id}</td>
                                <td>${slider.slider_title}</td>
                                <td><img src="${pageContext.request.contextPath}/${slider.slider_img}" alt="Slider Image" style="max-width: 100px; max-height: 50px;"/></td>
                                <td><a href="${slider.backlink}" target="_blank">${slider.backlink}</a></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${slider.status == 1}">
                                            <span style="color: green; font-weight: bold;">Active</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span style="color: red; font-weight: bold;">Inactive</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <!-- Action Buttons -->
                                    <form method="post" action="SliderList" style="display:inline;">
                                        <input type="hidden" name="sliderId" value="${slider.slider_id}">
                                        <c:choose>
                                            <c:when test="${slider.status == 1}">
                                                <input type="hidden" name="action" value="hide">
                                                <button type="submit" class="btn btn-warning btn-sm">Hide</button>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="hidden" name="action" value="show">
                                                <button type="submit" class="btn btn-success btn-sm">Show</button>
                                            </c:otherwise>
                                        </c:choose>
                                    </form>
                                    <a href="EditSlider?sliderId=${slider.slider_id}" class="btn btn-primary btn-sm">Edit</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- Pagination Controls -->
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <c:if test="${currentPage > 1}">
                            <li class="page-item">
                                <a class="page-link" href="?page=${currentPage - 1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                <a class="page-link" href="?page=${i}">${i}</a>
                            </li>
                        </c:forEach>
                        <c:if test="${currentPage < totalPages}">
                            <li class="page-item">
                                <a class="page-link" href="?page=${currentPage + 1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>

                <a href="AddSlider" class="btn btn-primary w-100 mt-3">Back to Slider Management</a>
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="scripts.js"></script>
    </body>

</html>


