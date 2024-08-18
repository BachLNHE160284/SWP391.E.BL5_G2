<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Profile</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <style>
            body {
                background-color: #f8f9fa;
            }

            .profile-card {
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            .profile-card h3 {
                font-weight: bold;
            }

            .info-field {
                font-size: 1.1em;
                margin-bottom: 10px;
            }

            .btn-dark {
                border-radius: 20px;
            }

            .table-striped tbody tr:nth-of-type(odd) {
                background-color: #e9ecef;
            }

            .table-striped tbody tr:nth-of-type(even) {
                background-color: #ffffff;
            }
        </style>
    </head>

    <body>

        <div class="container my-5">
            <div class="text-center mb-4">
                <form action="updateProfile" method="get" class="d-inline">
                    <button type="submit" class="btn btn-dark btn-sm">Update Profile</button>
                </form>
                <form action="changePassword" method="get" class="d-inline ms-2">
                    <button type="submit" class="btn btn-dark btn-sm">Change Password</button>
                </form>
            </div>

            <h1 class="text-center mb-5">Your Profile</h1>

            <div class="row">
                <div class="col-md-6 mb-4">
                    <div class="card profile-card bg-light">
                        <div class="card-body">
                            <h3>About You</h3>
                            <p><strong>User ID:</strong> ${acc.user_id}</p>
                            <p><strong>Full Name:</strong> ${acc.fullname}</p>
                            <p><strong>Gender:</strong> ${acc.gender ? "Male" : "Female"}</p>
                            <p><strong>Phone Number:</strong> ${acc.phone_number}</p>
                            <p><strong>Email Address:</strong> ${acc.email_address}</p>
                            <p><strong>Address:</strong> ${acc.address}</p>
                            <p><strong>Username:</strong> ${acc.username}</p>
                            <p><strong>Avatar:</strong> <img src="${acc.avartar}" alt="User Avatar" style="width:100px;height:auto;border-radius:50%;"></p>
                            <p><strong>Role ID:</strong> ${acc.role_id}</p>
                            <p><strong>Status:</strong> ${acc.status == 1 ? "Active" : "Inactive"}</p>
                            <p><strong>Create Date:</strong> ${acc.create_date}</p>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Bootstrap JS and dependencies -->
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>

</html>
