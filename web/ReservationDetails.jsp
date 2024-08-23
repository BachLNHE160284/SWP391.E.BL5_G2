<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            .container {
                max-width: 800px;
                margin: auto;
                padding: 20px;
            }
            .table .total-row th {
                text-align: right;
            }
            .checkout-btn {
                text-align: right;
                margin-top: 20px;
            }
            .back-btn {
                margin-bottom: 20px;
            }
            .pagination {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <header>
            <!-- Header content -->
        </header>

        <main id="main">
            <div class="container-fluid">
                <h1 class="my-4">Your Cart</h1>

                <div class="back-btn">
                    <a href="ServiceList" class="btn btn-secondary">Back</a>
                </div>

                <c:if test="${not empty services}">
                    <table class="table table-hover">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col">Service</th>
                                <th scope="col">Thumbnail</th>
                                <th scope="col">Price</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Original Price</th>
                                <th scope="col">Total</th>
                                <th scope="col">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${services}">
                                <tr>
                                    <td>${item.name_service}</td>
                                    <td><img src="./${item.thumbnail}" alt="${item.name_service}" class="img-thumbnail" width="100" /></td>
                                    <td>${item.sale_prices}</td>
                                    <td>
                                        <form action="UpdateCartServlet" method="post" class="form-inline">
                                            <input type="hidden" name="serviceID" value="${item.service_id}" />
                                            <input type="number" name="quantity" class="form-control" value="${item.quantity}" min="1" max="99" />
                                            <button type="submit" class="btn btn-primary ml-2">Update</button>
                                        </form>
                                    </td>
                                    <td>${item.original_prices}</td>
                                    <td>${item.sale_prices * item.quantity}</td>
                                    <td>
                                        <form action="DeleteCartServlet" method="post">
                                            <input type="hidden" name="serviceID" value="${item.service_id}" />
                                            <input type="hidden" name="action" value="delete" />
                                            <button onclick="return confirm('Are you sure to delete this service?')" type="submit" class="btn btn-danger">Delete</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr class="total-row">
                                <th colspan="5">Grand Total:</th>
                                <th colspan="2">${grandTotal}</th>
                            </tr>
                        </tbody>
                    </table>

                    <div class="checkout-btn">
                        <form action="CheckoutServlet" method="post">
                            <button type="submit" class="btn btn-success btn-lg">Proceed to Checkout</button>
                        </form>
                    </div>

                    <!-- Pagination Controls -->
                    <div class="pagination">
                        <ul class="pagination">
                            <c:forEach begin="1" end="${totalPages}" var="i">
                                <li class="page-item ${i == currentPage ? 'active' : ''}">
                                    <a class="page-link" href="ReservationDetails?page=${i}">${i}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>
                <c:if test="${empty services}">
                    <p>Your cart is empty.</p>
                </c:if>
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
