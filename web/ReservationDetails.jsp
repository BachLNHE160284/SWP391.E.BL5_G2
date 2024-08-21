<%-- 
    Document   : ReservationDetails
    Created on : Aug 19, 2024, 11:01:02 PM
    Author     : ntung
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Reservation Details</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
        <link href="assests/img/favicon.png" rel="icon">
        <link href="assests/img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

        <!-- Vendor CSS Files -->
        <link href="assests/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
        <link href="assests/vendor/animate.css/animate.min.css" rel="stylesheet">
        <link href="assests/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assests/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assests/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assests/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
        <link href="assests/vendor/remixicon/remixicon.css" rel="stylesheet">
        <link href="assests/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

        <!-- Template Main CSS File -->
        <link href="assests/css/style.css" rel="stylesheet">
        <style>.cart-quantity-input {
                height: 34px;
                width: 50px;
                border-radius: 5px;
                border: 1px solid #56CCF2;
                background-color: #eee;
                color: #333;
                padding: 0;
                text-align: center;
                font-size: 1.2em;
                margin-right: 25px;
            }
            @media (min-width: 768px){
                .seven-cols .col-md-1,
                .seven-cols .col-sm-1,
                .seven-cols .col-lg-1  {
                    width: 100%;
                    *width: 100%;
                }
            }

            @media (min-width: 992px) {
                .seven-cols .col-md-1,
                .seven-cols .col-sm-1,
                .seven-cols .col-lg-1 {
                    width: 14.285714285714285714285714285714%;
                    *width: 14.285714285714285714285714285714%;
                }
            }

            /**
             *  The following is not really needed in this case
             *  Only to demonstrate the usage of @media for large screens
             */
            @media (min-width: 1200px) {
                .seven-cols .col-md-1,
                .seven-cols .col-sm-1,
                .seven-cols .col-lg-1 {
                    width: 14.285714285714285714285714285714%;
                    *width: 14.285714285714285714285714285714%;
                }
            }
            .lead {
                font-size: 0.9rem;
                font-weight: 300;
            }
        </style>
        <!-- =======================================================
        * Template Name: Medilab - v4.7.1
        * Template URL: https://bootstrapmade.com/medilab-free-medical-bootstrap-theme/
        * Author: BootstrapMade.com
        * License: https://bootstrapmade.com/license/
        ======================================================== -->
    </head>

    <body>

        <!-- ======= Top Bar ======= -->

        <div id="topbar" class="d-flex align-items-center fixed-top">
            <div class="container d-flex justify-content-between">
                <div class="contact-info d-flex align-items-center">
                    <i class="bi bi-envelope"></i> <a href="mailto:contact@example.com">contact@example.com</a>
                    <i class="bi bi-phone"></i> +1 5589 55488 55
                </div>
                <div class="d-none d-lg-flex social-links align-items-center">
                    <a href="#" class="twitter"><i class="bi bi-twitter"></i></a>
                    <a href="#" class="facebook"><i class="bi bi-facebook"></i></a>
                    <a href="#" class="instagram"><i class="bi bi-instagram"></i></a>
                    <a href="#" class="linkedin"><i class="bi bi-linkedin"></i></i></a>
                </div>
            </div>
        </div>

        <!-- ======= Header ======= -->
        <jsp:include page="assests/module/header.jsp" flush="true"/>
        <!-- End Header -->

        <main id="main" style="padding: 150px 0 0;">
            <div class="row">
                <div class="col-md-9">
                    <div class="row ">
                        <c:forEach items="${listCate}" var="c">
                            <div class="col-md-2">
                                <div class="frame">
                                    <a href="ServiceList?cateId=${c.getCategory_id()}"><button name="cate" value="${c.getCategory_id()}" class="custom-btn btn-5"><span>${c.getCategory_name()}</span></button></a>
                                </div>
                            </div>

                        </c:forEach>
                    </div>
                </div>
                <div class="col-md-3">
                    <div >
                        <nav class="navbar navbar-light bg-light w-100 "  style="display: block">
                            <form action="serchservice" class="form-inline">
                                <div class="input-group input-group-sm">
                                    <input name="txt" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                                    <div class="input-group-append">
                                        <button type="submit" style="border-radius: 0px" class="btn btn-secondary btn-number">
                                            <i class="fa fa-search"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </nav>
                    </div>
                </div>
            </div>
            <c:if test="${sessionScope.carts.size() > 0 && sessionScope.carts != null}">


                <section class="" style="background-color: #fff;">
                    <div class="container h-100">
                        <div class="row d-flex justify-content-center align-items-center h-100">
                            <div class="col">
                                <p><span class="h2">Your Reservation Detail</span><span class="h4">(${sessionScope.carts.size()} item in your cart)</span></p>

                                <div class="card mb-4">

                                    <div class="card-body p-4">
                                        <c:forEach items="${sessionScope.carts}" var="cart">



                                            <form action="reservation-detail" method="post">

                                                <div class="row align-items-center seven-cols">
                                                    <input type="hidden" value="${cart.value.service.service_id}" name="id">
                                                    <div class="col-md-2 d-flex justify-content-center">
                                                        <div>
                                                            <p class="small text-muted mb-4 pb-4">ID</p>
                                                            <p class="lead fw-normal mb-4">${cart.value.service.service_id}</p>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-2 d-flex justify-content-center">
                                                        <div>
                                                            <p class="small text-muted mb-4 pb-4">Services Name</p>
                                                            <p class="lead fw-normal mb-4">${cart.value.service.service_title}</p>
                                                        </div>
                                                    </div>


                                                    <div class="col-md-2 d-flex justify-content-center">
                                                        <div>
                                                            <p class="small text-muted mb-4 pb-4">Quantity</p>
                                                            <input class="lead fw-normal mb-4 cart-quantity-input" min="1" onchange="this.form.submit()" type="number" name="quantity" value="${cart.value.quantity}">
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2 d-flex justify-content-center">
                                                        <div>
                                                            <p class="small text-muted mb-4 pb-4">Price</p>
                                                            <p class="lead fw-normal mb-4">$${cart.value.service.service_price}</p>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2 d-flex justify-content-center">
                                                        <div>
                                                            <p class="small text-muted mb-4 pb-4">Total</p>
                                                            <p class="lead fw-normal mb-4">$${cart.value.service.service_price*cart.value.quantity*cart.value.num_of_person}</p>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2 d-flex justify-content-center">
                                                        <div>
                                                            <p class="small text-muted mb-4 pb-4"></p>
                                                            <a href="deletecart?id=${cart.value.service.service_id}"><button onclick="document.getElementById('f').submit()" type="button" class="btn btn-danger">Delete</button>
                                                            </a>


                                                        </div>
                                                    </div>       
                                                </div>
                                            </form>
                                        </c:forEach>

                                    </div>

                                </div>

                                <div class="card mb-5">
                                    <div class="card-body p-4">

                                        <div class="float-end">
                                            <p class="mb-0 me-5 d-flex align-items-center">
                                                <span class="small text-muted me-2">Order total:</span> <span
                                                    class="lead fw-normal">$${total_order}</span>
                                            </p>
                                        </div>

                                    </div>
                                </div>

                                <div class="d-flex justify-content-end">
                                    <a href="servicelist">
                                        <button type="button" class="btn btn-light btn-lg me-2">Continue shopping</button>

                                    </a>
                                    <a href="reservation-contact">
                                        <button type="button" class="btn btn-primary btn-lg">Check Out</button>

                                    </a>
                                </div>

                            </div>
                        </div>
                    </div>
                </section>
            </c:if>
            <c:if test="${sessionScope.carts.size() <= 0 || sessionScope.carts == null}">
                <section class="inner-pag">
                    <div class="container" style="padding: 50px 0 0;">
                        <center>
                            <h1>
                                Your cart is empty, Shopping now to see your details
                            </h1>
                            <a href="ServiceList"><button type="button" class="btn btn-primary">Shopping Now</button></a>
                            </br>
                        </center>

                    </div>
                </section>
            </c:if>



        </main><!-- End #main -->

        <!-- ======= Footer ======= -->
        <jsp:include page="assests/module/footer.jsp" flush="true"/>
        <!-- End Footer -->

        <div id="preloader"></div>
        <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

        <!-- Vendor JS Files -->
        <script src="assests/vendor/purecounter/purecounter.js"></script>
        <script src="assests/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assests/vendor/glightbox/js/glightbox.min.js"></script>
        <script src="assests/vendor/swiper/swiper-bundle.min.js"></script>
        <script src="assests/vendor/php-email-form/validate.js"></script>

        <!-- Template Main JS File -->
        <script src="assests/js/main.js"></script>


    </body>

</html>
