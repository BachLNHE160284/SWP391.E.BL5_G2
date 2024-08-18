<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
        }

        .form-container {
            width: 100%;
            max-width: 500px;
            margin: 5% auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: #fff;
        }

        .form-title {
            text-align: center;
            margin-bottom: 30px;
            font-size: 24px;
            font-weight: bold;
        }

        .form-label {
            font-weight: bold;
        }

        .form-control {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        .btn-submit {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 4px;
            background-color: #007bff;
            color: white;
            font-size: 17px;
            cursor: pointer;
        }

        .btn-submit:hover {
            background-color: #0056b3;
        }

        .message {
            text-align: center;
            margin-bottom: 20px;
        }

        .message.success {
            color: blue;
        }

        .message.error {
            color: red;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h1 class="form-title">Change Password</h1>
        <p>Change Password email: <strong><%= request.getAttribute("userEmail") %></strong></p>
        <form action="changePassword" method="post">
            <div class="mb-3">
                <label for="oldpass" class="form-label">Old Password</label>
                <input type="password" class="form-control" id="oldpass" name="oldpass" placeholder="Old Password" required>
            </div>

            <div class="mb-3">
                <label for="newpass" class="form-label">New Password</label>
                <input type="password" class="form-control" id="newpass" name="newpass" placeholder="New Password" required>
            </div>

            <div class="mb-3">
                <label for="repass" class="form-label">Re-enter New Password</label>
                <input type="password" class="form-control" id="repass" name="repass" placeholder="Re-enter New Password" required>
            </div>

            <div class="message <%= request.getAttribute("messType") != null ? request.getAttribute("messType") : "" %>">
                <p><strong><%= request.getAttribute("mess") != null ? request.getAttribute("mess") : "" %></strong></p>
            </div>

            <input type="submit" value="Change Password" class="btn btn-submit">
        </form>
    </div>
</body>
</html>
