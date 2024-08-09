<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Children Care</title>
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet">
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/login.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
    <link href="css/paging.css" rel="stylesheet">
    <style>
        .error-message {
            color: red;
            font-weight: bold;
            margin-top: 10px;
        }

        .btn-smaller {
            font-size: 0.75rem;
            padding: 0.25rem 0.5rem;
        }

        .form-control {
            width: 100%;
            max-width: 350px;
            margin: 0 auto;
        }

        .login-page {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f8f9fa;
        }

        .form {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        }

        .form-check {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .btn-primary {
            width: 100%;
            margin-bottom: 15px;
        }

        .message {
            text-align: center;
        }
    </style>
</head>

<body>

    <div class="login-page">
        <div class="form">
            <c:set var="cookie" value="${pageContext.request.cookies}" />
            <form action="login" class="login-form" method="post" onsubmit="return validateForm(event)">
                <div class="mb-3">
                    <input type="text" name="email" id="email" required class="form-control" placeholder="Email"
                        value="${cookie.cusername.value}" />
                </div>
                <div class="mb-3">
                    <input type="password" name="password" id="password" required class="form-control" placeholder="Password"
                        value="${cookie.cpassword.value}" />
                </div>
                <div class="mb-3 form-check">
                    <label class="form-check-label" for="remember">Remember me</label>
                    <input type="checkbox" id="remember-me" name="remember"
                        value="${(cookie.cRemember!=null?'checked':'')}" />
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
                <p class="message">Not registered? <a href="Register.jsp">Create an account</a></p>
                <p class="message">Forgot Password? <a href="forgotPassword">Click Here</a></p>
                <div id="error-message" class="error-message">
                    <c:if test="${not empty mess}">
                        ${mess}
                    </c:if>
                    <c:if test="${not empty sessionScope.warning}">
                        ${sessionScope.warning}
                    </c:if>
                </div>
                <hr>
                <div class="text-center">
                    <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:9998/G3_SE1846_SWP391_Shop/loginGoogle&response_type=code
                           &client_id=258136205301-i1cc40maqni093imaqstodtjjtn14fnl.apps.googleusercontent.com&approval_prompt=force"
                        class="btn btn-danger"><i class="bi bi-google"></i> Login with Google</a>
                </div>
            </form>
        </div>
    </div>

    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="js/scripts.js"></script>
    <!-- Validation script -->
    <script>
        function validateForm(event) {
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;
            const errorMessage = document.getElementById('error-message');

            const emailPattern = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
            const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]+$/;

            if (!emailPattern.test(email)) {
                errorMessage.textContent = 'Invalid email format.';
                clearField('email');
                return false;
            }
            if (password.length < 10 || password.length > 40) {
                errorMessage.textContent = 'Password must be between 10 and 40 characters.';
                clearField('password');
                return false;
            }

            if (!passwordPattern.test(password)) {
                errorMessage.textContent = 'Password must contain at least one letter and one number.';
                clearField('password');
                return false;
            }

            errorMessage.textContent = '';
            return true;
        }

        function clearField(fieldId) {
            document.getElementById(fieldId).value = '';
        }
    </script>
</body>

</html>
