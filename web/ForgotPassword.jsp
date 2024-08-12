<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forgot Password</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha512-Fo3rlrZj/k7ujTnHqT3Nbl5NRKp7d2R2WOhCwfhFhqNkMlZRA5xGqB5k1dyOtGJQhoIbS+GRvrvcv4BF5mz5g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #e9ecef;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            width: 350px;
            text-align: center;
        }

        h2 {
            font-size: 24px;
            margin-bottom: 20px;
            color: #333;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        label {
            font-weight: bold;
            margin-bottom: 10px;
            display: block;
            width: 100%;
            text-align: left;
            color: #555;
        }

        input[type="email"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border-radius: 5px;
            border: 1px solid #ddd;
            font-size: 16px;
            box-sizing: border-box;
            transition: border-color 0.3s ease;
        }

        input[type="email"]:focus {
            border-color: #007bff;
            outline: none;
        }

        input[type="submit"] {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .message {
            margin-top: 20px;
            font-size: 14px;
            color: #d9534f;
            display: block;
            width: 100%;
            text-align: center;
        }

        .message.success {
            color: #28a745;
        }

        .message.error {
            color: #d9534f;
        }

        .icon {
            font-size: 50px;
            color: #007bff;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <i class="fas fa-lock icon"></i>
        <h2>Forgot Password</h2>
        <form action="forgotPassword" method="post">
            <label for="email">Enter your email address:</label>
            <input type="email" id="email" name="email" placeholder="you@example.com" required>
            <input type="submit" value="Send Confirmation Code">
        </form>
        <p class="message <%= request.getAttribute("message") != null ? "error" : "" %>">
            <%= request.getAttribute("message") %>
        </p>
    </div>
</body>
</html>
