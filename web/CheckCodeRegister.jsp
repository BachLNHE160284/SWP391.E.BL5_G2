<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Check Email Code</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 50%;
            margin: 100px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            margin-top: 0;
        }
        form {
            margin-top: 20px;
            text-align: center;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"] {
            width: calc(100% - 22px); /* trừ border */
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box; /* kích thước tính cả border */
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .message {
            color: red;
            text-align: center;
            margin-top: 10px;
        }
        input[type="text"][hidden] {
            display: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Check Code Email</h2>
        <p>Vui lòng nhập mã xác nhận đã gửi tới <strong><%= request.getAttribute("email") %></strong>:</p>
        <form action="checkCodeRegister" method="post">
            <input type="text" name="confirmationCode" placeholder="Confirmation Code" required>
            <input type="submit" value="Submit">
            <input type="text" name="email" value="<%= request.getAttribute("email") %>" hidden>
            <input type="text" name="code" value="<%= request.getAttribute("code") %>" hidden>
            <input type="text" name="fullname" value="<%= request.getAttribute("fullname") %>" hidden>
            <input type="text" name="password" value="<%= request.getAttribute("password") %>" hidden>
            <input type="text" name="gender" value="<%= request.getAttribute("gender") %>" hidden>
        </form>
        <p class="message"><%= request.getAttribute("message") %></p>
    </div>
</body>
</html>
