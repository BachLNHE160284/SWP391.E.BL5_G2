<%-- 
    Document   : ChangePassword
    Created on : May 21, 2023, 1:59:30 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Boutique</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css2?family=Libre+Franklin:wght@300;400;700&amp;display=swap">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Martel+Sans:wght@300;400;800&amp;display=swap">
        <!-- theme stylesheet-->
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="../stylesheet/profile.css">
        <link
            href="https://fonts.googleapis.com/css2?family=Alegreya&family=MuseoModerno:wght@200;300;400;500;600;800;900&family=Open+Sans:ital,wght@0,300;1,300&display=swap"
            rel="stylesheet">
        <link rel="shortcut icon" href="img/favicon.png">
        <style>
            .custom-btn {
                border: none;
                background-color: #000;
                color: #f2f2f2;
                font-weight: bolder;
                padding: 10px;
                border-radius: 5px;
                margin: 10px;
            }

            .custom-btn:hover {
                background-color: #323232;
            }

            .form-container {
                max-width: 500px;
                margin: auto;
                padding: 20px;
                border: 1px solid #ddd;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .form-title {
                text-align: center;
                margin-bottom: 20px;
            }

            .btn-submit {
                width: 100%;
                padding: 10px;
                border: none;
                border-radius: 4px;
                background-color: #000;
                color: white;
                font-size: 17px;
                cursor: pointer;
            }

            .btn-submit:hover {
                background-color: #323232;
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
            .message {
                margin-bottom: -20px; /* Điều chỉnh giá trị này tùy ý */
            }

        </style>
    </head>

    <body>

        <jsp:include page="Header.jsp" />

        <div class="container my-5">
            <div class="d-flex justify-content-center mb-3">
                <form action="customerOrder" method="get" class="me-2">
                    <input type="submit" value="History" class="btn custom-btn">
                </form>

                <form action="updateProfile" method="get" class="ms-2">
                    <button type="submit" class="btn custom-btn">Update Profile</button>
                </form>
            </div>

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
                        <label for="repass" class="form-label">Re-enter New Password</label>
                        <input type="password" class="form-control" id="repass" name="repass" placeholder="Re-enter New Password" required>
                    </div>
                    <div class="message <%= request.getAttribute("messType") != null ? request.getAttribute("messType") : "" %>">
                        <p><strong><%= request.getAttribute("mess") != null ? request.getAttribute("mess") : "" %></strong></p>
                    </div>
                    <input type="submit" value="Change Password" class="btn btn-submit mt-3">

                </form>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-w76A2CnDpCk5VL0VLn2VkaKzS1LRpMNnvZ91F8JJQV2z5l0mD1e73MEsm1iHpG5G"
        crossorigin="anonymous"></script>

    </body>

</html>
