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
                            <p class="info-field"><strong>User ID:</strong> <span>${acc.user_id}</span></p>
                            <p class="info-field"><strong>Full Name:</strong> <span>${acc.fullname}</span></p>
                            <p class="info-field"><strong>Gender:</strong> <span>${acc.gender ? "Male" : "Female"}</span></p>
                            <p class="info-field"><strong>Phone Number:</strong> <span>${acc.phone_number}</span></p>
                            <p class="info-field"><strong>Email Address:</strong> <span>${acc.email_address}</span></p>
                            <p class="info-field"><strong>Address:</strong> <span>${acc.address}</span></p>
                            <p class="info-field"><strong>Username:</strong> <span>${acc.username}</span></p>
                            <p class="info-field"><strong>Avatar:</strong> <img src="${acc.avartar}" alt="User Avatar" style="width:100px;height:auto;border-radius:50%;"></p>
                            <p class="info-field"><strong>Role ID:</strong> <span>${acc.role_id}</span></p>
                            <p class="info-field"><strong>Status:</strong> <span>${acc.status == 1 ? "Active" : "Inactive"}</span></p>
                            <p class="info-field"><strong>Create Date:</strong> <span>${acc.create_date}</span></p>
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
