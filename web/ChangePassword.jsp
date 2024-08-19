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

        .btn-container {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }

        .btn-submit, .btn-cancel {
            width: 48%;
            padding: 10px;
            border: none;
            border-radius: 25px;
            background-color: #007bff;
            color: white;
            font-size: 17px;
            cursor: pointer;
            text-align: center;
        }

        .btn-submit:hover, .btn-cancel:hover {
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
                <label for="repass" class="form-label">Confirm New Password</label>
                <input type="password" class="form-control" id="repass" name="repass" placeholder="Confirm New Password" required>
            </div>

            <div class="btn-container">
                <button type="submit" class="btn-submit">Change Password</button>
                <a href="UserProfile.jsp" class="btn-cancel">Cancel</a>
            </div>

            <div class="message">
                <% 
                    String messType = (String) request.getAttribute("messType");
                    String mess = (String) request.getAttribute("mess");
                    if (mess != null) {
                        String color = "black";
                        if ("success".equals(messType)) {
                            color = "blue";
                        } else if ("error".equals(messType)) {
                            color = "red";
                        }
                %>
                <p class="<%= messType %>" style="color: <%= color %>;"><%= mess %></p>
                <% } %>
            </div>
        </form>
    </div>
</body>
</html>
