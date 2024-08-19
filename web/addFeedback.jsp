<%-- 
    Document   : addFeedback
    Created on : Aug 19, 2024, 9:34:11 PM
    Author     : lebac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Submit Feedback</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f0f0;
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
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 400px;
            }
            h1 {
                text-align: center;
                margin-bottom: 20px;
            }
            label {
                display: block;
                margin-bottom: 8px;
                font-weight: bold;
            }
            input, textarea, button {
                width: 90%;
                padding: 10px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            button {
                background-color: #28a745;
                color: #fff;
                border: none;
                cursor: pointer;
            }
            button:hover {
                background-color: #218838;
            }

            .rating {
                margin-bottom: 20px;
                display: flex;
                flex-direction: row-reverse; /* this is the magic */
                justify-content: flex-end;
            }

            .rating input {
                display: none;
            }

            .rating label {
                font-size: 24px;
                cursor: pointer;
            }

            .rating label:hover,
            .rating label:hover ~ label { /* reason why the stars are in reverse order in the html */
                color: orange;
            }

            .rating input:checked ~ label {
                color: orange;
            }

            .container {
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            .button-container {
                display: flex;
                justify-content: center;
                width: 100%;
                margin-top: 20px; /* Khoảng cách giữa nút và form */
            }

            .btn-home {
                display: inline-block;
                padding: 10px 20px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                text-decoration: none;
                transition: background-color 0.3s ease;
            }

            .btn-home:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Submit Your Feedback</h1>
            <form action="SubmitFeedback" method="post" enctype="multipart/form-data">
                <label for="service_id">Service ID:</label>
                <input type="text" id="service_id" name="service_id" required><br>
                <label for="feedback">Feedback:</label>
                <textarea id="feedback" name="feedback" rows="4" required style="resize:none"></textarea><br>

                <label for="star">Rating (1-5):</label>
                <div class="rating">
                    <!-- Notice that the stars are in reverse order -->

                    <input type="radio" id="star5" name="rate_star" value="5">
                    <label for="star5">&#9733;</label>
                    <input type="radio" id="star4" name="rate_star" value="4">
                    <label for="star4">&#9733;</label>
                    <input type="radio" id="star3" name="rate_star" value="3">
                    <label for="star3">&#9733;</label>
                    <input type="radio" id="star2" name="rate_star" value="2">
                    <label for="star2">&#9733;</label>
                    <input type="radio" id="star1" name="rate_star" value="1">
                    <label for="star1">&#9733;</label>
                </div>

                <label for="feedback_img">Feedback Image:</label>
                <input type="file" id="feedback_img" name="feedback_img" accept="image/*"><br>
                <button type="submit">Submit Feedback</button>
            </form>
        </div>
    </body>
</html>
