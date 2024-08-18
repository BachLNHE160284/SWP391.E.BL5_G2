<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Profile</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        body {
            background-color: #f8f9fa;
        }

        .update-form {
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            background-color: #ffffff;
        }

        .form-group label {
            font-weight: bold;
        }

        .btn-primary {
            border-radius: 20px;
        }
    </style>
</head>

<body>

    <div class="container my-5">
        <h1 class="text-center mb-4">Update Profile</h1>

        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="update-form">
                    <form action="updateProfile" method="post">
                        <input type="hidden" name="user_id" value="${user.user_id}">
                        <input type="hidden" name="email_address" value="${user.email_address}">

                        <div class="form-group">
                            <label for="fullname">Full Name:</label>
                            <input type="text" class="form-control" id="fullname" name="fullname" value="${user.fullname}" required>
                        </div>

                        <div class="form-group">
                            <label>Gender:</label><br>
                            <input type="radio" id="male" name="gender" value="true" ${user.gender ? "checked" : ""}> Male
                            <input type="radio" id="female" name="gender" value="false" ${!user.gender ? "checked" : ""}> Female
                        </div>

                        <div class="form-group">
                            <label for="phone_number">Phone Number:</label>
                            <input type="text" class="form-control" id="phone_number" name="phone_number" value="${user.phone_number}" required>
                        </div>

                        <div class="form-group">
                            <label for="address">Address:</label>
                            <input type="text" class="form-control" id="address" name="address" value="${user.address}" required>
                        </div>

                        <div class="form-group">
                            <label for="username">Username:</label>
                            <input type="text" class="form-control" id="username" name="username" value="${user.username}" required>
                        </div>

                        <div class="form-group">
                            <label for="avartar">Avatar URL:</label>
                            <input type="text" class="form-control" id="avartar" name="avartar" value="${user.avartar}"><!-- Optional -->
                        </div>

                        <div class="form-group">
                            <label for="role_id">Role ID:</label>
                            <input type="number" class="form-control" id="role_id" name="role_id" value="${user.role_id}" required>
                        </div>

                        <div class="form-group">
                            <label for="status">Status:</label>
                            <input type="number" class="form-control" id="status" name="status" value="${user.status}" required>
                        </div>


                        <button type="submit" class="btn btn-primary">Update Profile</button>
                    </form>

                    <!-- Display error message if any -->
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger mt-3">
                            <c:out value="${error}" />
                        </div>
                    </c:if>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </div>

</body>

</html>
