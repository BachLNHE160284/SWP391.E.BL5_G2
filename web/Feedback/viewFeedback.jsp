<%-- 
    Document   : viewFeedback
    Created on : Aug 21, 2024, 8:43:17 PM
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
        <title>View Feedback</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
            .star-yellow {
                color: gold;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="mb-4">View Feedback</h2>
            <form id="addProductForm" action="AddServiceServlet" method="POST" enctype="multipart/form-data">
                <table class="table table-bordered">
                    <tr>
                        <td><strong>Feedback ID</strong></td>
                        <td>${fb.feedback_id}</td>
                    </tr>
                    <tr>
                        <td><strong>Feedback Details</strong></td>
                        <td>${fb.feedback}</td>
                    </tr>
                    <tr>
                        <td><strong>Feedback Star</strong></td>
                        <td>
                            <c:forEach var="i" begin="1" end="5">
                                <c:choose>
                                    <c:when test="${fb.rate_star >= i}">
                                        <i class="fas fa-star star-yellow"></i>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="far fa-star star-yellow"></i>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <td><strong>Feedback Date</strong></td>
                        <td>${fb.create_date}</td>
                    </tr>
                    <tr>
                        <td><strong>Service ID</strong></td>
                        <td>${fb.service.service_id}</td>
                    </tr>
                    <tr>
                        <td><strong>Service Name</strong></td>
                        <td>${fb.service.name_service}</td>
                    </tr>
                    <tr>
                        <td><strong>Username</strong></td>
                        <td>${fb.user.fullname}</td>
                    </tr>
                    <tr>
                        <td><strong>User Email</strong></td>
                        <td>${fb.user.email_address}</td>
                    </tr>
                    <c:if test="${not empty fb.feedback_img}">
                        <tr>
                            <td><strong>Feedback Image</strong></td>
                            <td>
                                <img id="img_service" name="img_service" src="${pageContext.request.contextPath}/${fb.feedback_img}" width="200" />
                            </td>
                        </tr>
                    </c:if>
                </table>
                <a href="FeedbackManagementServlet" class="btn btn-primary w-100">Back to Feedback Management</a>
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="scripts.js"></script>
    </body>
</html>

