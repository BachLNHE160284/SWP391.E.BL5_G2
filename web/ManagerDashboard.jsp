<%-- 
    Document   : ManagerDashboard
    Created on : Aug 21, 2024, 1:25:57 PM
    Author     : lebac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .navbar {
            background-color: #343a40;
        }
        .navbar-brand, .navbar-nav .nav-link {
            color: white !important;
        }
        .container-fluid {
            padding-top: 20px;
        }
        .card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        .card-header {
            background-color: #ff7f50;
            color: white;
            border-radius: 15px 15px 0 0;
        }
        .card-body {
            background-color: white;
            color: #343a40;
        }
        .card-footer {
            background-color: #f8f9fa;
            border-radius: 0 0 15px 15px;
        }
        .stat-card {
            text-align: center;
            padding: 20px;
            background-color: white;
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        .stat-card h3 {
            margin-bottom: 10px;
        }
        .stat-card i {
            font-size: 3em;
            color: #ff7f50;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark">
        <a class="navbar-brand" href="#">Manager Dashboard</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="HomePage">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Logout">Logout</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-6">
                <div class="stat-card">
                    <img src="https://cdn-icons-png.flaticon.com/512/4646/4646577.png" alt="Major Image" style="width: 50px; height: 50px;">
                    <h3>Services</h3>
                    <a href="ServiceManagementServlet" class="btn btn-warning">Manage Services</a>
                </div>
            </div>
            <div class="col-md-6">
                <div class="stat-card">
                    <img src="https://cdn-icons-png.flaticon.com/512/4658/4658825.png" alt="Major Image" style="width: 50px; height: 50px;">
                    <h3>Feedbacks</h3>
                    <a href="FeedbackManagementServlet" class="btn btn-warning">Manage Feedbacks</a>
                </div>
            </div>
            <div class="col-md-6">
                <div class="stat-card">
                    <img src="https://static.vecteezy.com/system/resources/previews/002/553/976/non_2x/delivery-cargo-service-logistic-cardboard-box-and-mail-post-line-style-icon-free-vector.jpg" alt="Major Image" style="width: 50px; height: 50px;">
                    <h3>Posts</h3>
                    <a href="PostManagementServlet" class="btn btn-warning">Manage Posts</a>
                </div>
            </div>
<!--            <div class="col-md-4">
                <div class="stat-card">
                    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPiFxdPfQeUYEz_SDGpwzJcLgNUtH9sBl1Jg&s" alt="Major Image" style="width: 50px; height: 50px;">
                    <h3>Customers</h3>
                    <a href="majorManagement.html" class="btn btn-warning">Manage Customers</a>
                </div>
            </div>-->
            <div class="col-md-6">
                <div class="stat-card">
                    <img src="https://cdn-icons-png.flaticon.com/512/7545/7545476.png" alt="Major Image" style="width: 50px; height: 50px;">
                    <h3>Sliders</h3>
                    <a href="SliderList" class="btn btn-warning">Manage Sliders</a>
                </div>
            </div>
        </div>
        
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>