<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Register Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <link href="css/login.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>

        <!-- Register Form -->
        <div class="login-page">
            <div class="form">
                <h2>Register</h2>
                <form action="register" class="login-form" method="post">
                    <input type="text" required name="email" id="email" placeholder="Email" value="${email}" />
                    <input type="text" required name="fullname" placeholder="Full Name" value="${fullname}" />
                    <input type="password" required name="password" id="password" placeholder="Password" value="${password}" />
                    <input type="password" required name="repassword" id="repassword" placeholder="Re_Password" value="${repassword}" />
                    <div class="wrapper" style="margin: auto; margin-top: -10px; margin-bottom: 10px">
                        <div class="option">
                            <input class="check" type="radio" id="nam" name="gender" value="1" required ${gender eq '1' ? 'checked' : ''}>
                            <div class="ok">
                                <span class="span">Male</span>
                            </div>
                        </div>
                        <div class="option">
                            <input class="check" type="radio" id="nu" name="gender" value="0" required ${gender eq '0' ? 'checked' : ''}>
                            <div class="ok">
                                <span class="span">Female</span>
                            </div>
                        </div>
                    </div>
                    <div id="error-message" class="error-message">
                        <c:if test="${not empty messR}">
                            ${messR}
                        </c:if>
                    </div>
                    <button type="submit">Signup</button>
                    <p class="message">Already registered? <a href="Login.jsp">Login</a></p>
                </form>
            </div>
        </div>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.16.0/font/bootstrap-icons.css" rel="stylesheet">
    </body>
    <script>
        function validateForm(event) {
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;
            const repassword = document.getElementById('repassword').value;
            const errorMessage = document.getElementById('error-message');

            const emailPattern = /^[\w\.-]+@([\w-]+\.)+[\w-]{2,4}$/;
            const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]+$/;

            if (!emailPattern.test(email)) {
                errorMessage.textContent = 'Invalid email format.';
                clearField('email');
                return false;
            }
            if (password.length < 10 || password.length > 40) {
                errorMessage.textContent = 'Password must be between 10 and 40 characters.';
                clearField('password');
                clearField('repassword');
                return false;
            }

            if (!passwordPattern.test(password)) {
                errorMessage.textContent = 'Password must contain at least one letter and one number.';
                clearField('password');
                clearField('repassword');
                return false;
            }

            if (password !== repassword) {
                errorMessage.textContent = 'Passwords do not match.';
                clearField('password');
                clearField('repassword');
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