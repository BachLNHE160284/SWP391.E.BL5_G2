<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Reservation Details</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="assests/css/custom-style.css" rel="stylesheet">
    <style>
        .service-details {
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .service-details h2 {
            font-size: 24px;
            margin-bottom: 15px;
        }

        .service-details img {
            max-width: 100%;
            border-radius: 8px;
            margin-bottom: 15px;
        }

        .service-details p {
            font-size: 16px;
            margin-bottom: 10px;
        }

        .service-details .price {
            font-size: 18px;
            font-weight: bold;
            color: #4CAF50;
        }

        .service-details .original-price {
            text-decoration: line-through;
            color: #888;
        }

        .container {
            max-width: 800px;
            margin: auto;
            padding: 20px;
        }
    </style>
</head>
<body>
    <header>
        <!-- Header content -->
    </header>

    <main id="main">
        <div class="container">
            <h1 class="my-4">Service Reservation</h1>
            <div class="service-details">
                <c:forEach var="service" items="${services}">
                    <h2>${service.name_service}</h2>
                    <img src="./${service.thumbnail}" alt="${service.name_service}" />
                    <p>${service.brief_infor}</p>
                    <p class="price">Price: ${service.sale_prices}</p>
                    <p class="original-price">Original Price: ${service.original_prices}</p>
                </c:forEach>
            </div>
        </div>
    </main>

    <footer>
        <!-- Footer content -->
    </footer>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
