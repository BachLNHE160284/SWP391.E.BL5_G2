<%-- 
    Document   : enterConfirmationCode
    Created on : 26-05-2024, 21:19:55
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Enter Confirmation Code</title>
        <link rel="stylesheet" href="styles.css"> 
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .container {
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 400px;
            }

            h2 {
                text-align: center;
                margin-bottom: 20px;
            }

            form {
                display: flex;
                flex-direction: column;
            }

            label {
                font-weight: bold;
                margin-bottom: 5px;
            }

            input[type="text"],
            input[type="password"] {
                padding: 10px;
                margin-bottom: 10px;
                border-radius: 5px;
                border: 1px solid #ccc;
            }

            input[type="submit"] {
                padding: 10px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            input[type="submit"]:hover {
                background-color: #0056b3;
            }

            p {
                text-align: center;
                margin-bottom: 20px;
            }

            strong {
                color: #007bff;
            }

            .message {
                text-align: center;
                margin-top: 20px;
                color: red;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Enter Confirmation Code</h2>

            <!-- Hiển thị email đã được lưu trong session -->
            <p>Vui lòng nhập mã xác nhận đã gửi tới <strong><%= request.getAttribute("email") %></strong>:</p>

            <!-- Form nhập mã xác nhận và mật khẩu mới -->
            <form action="resetPassword" class="login-form" method="post">
                <label for="confirmationCode">Confirmation Code:</label>
                <input type="text" id="confirmationCode" name="confirmationCode" required>
                <label for="newPassword">New Password:</label>
                <input type="password" id="newPassword" name="newPassword" required>
                <label for="confirmNewPassword">Confirm New Password:</label>
                <input type="password" id="confirmNewPassword" name="confirmNewPassword" required>
                <input type="text"  name="email" value="<%= request.getAttribute("email") %>" hidden>
                <input type="text"  name="code" value="<%= request.getAttribute("code") %>" hidden>
                <input type="submit" value="Reset Password">
            </form>

            <div id="error-message" class="error-message">
                <c:if test="${not empty messR}">
                    ${messR}
                </c:if>
            </div>
        </div>
    </body>

    <script>
        function validateForm(event) {
            const newPassword = document.getElementById('newPassword').value;
            const confirmNewPassword = document.getElementById('confirmNewPassword').value;
            const errorMessage = document.getElementById('error-message');

            const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]+$/;

            if (newPassword.length < 10 || newPassword.length > 40) {
                errorMessage.textContent = 'Password must be between 10 and 40 characters.';
                clearField('newPassword');
                clearField('confirmNewPassword');
                return false;
            }

            if (!passwordPattern.test(newPassword)) {
                errorMessage.textContent = 'Password must contain at least one letter and one number.';
                clearField('newPassword');
                clearField('confirmNewPassword');
                return false;
            }

            if (newPassword !== confirmNewPassword) {
                errorMessage.textContent = 'newPassword do not match.';
                clearField('newPassword');
                clearField('confirmNewPassword');
                return false;
            }

            errorMessage.textContent = '';
            return true;
        }

        function clearField(fieldId) {
            document.getElementById(fieldId).value = '';
        }

        document.querySelector('form.login-form').addEventListener('submit', function (event) {
            if (!validateForm(event)) {
                event.preventDefault();
            }
        });
    </script>

</html>
