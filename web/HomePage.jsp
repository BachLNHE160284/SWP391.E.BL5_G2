<%-- 
    Document   : HomePage
    Created on : Aug 8, 2024, 12:49:05 PM
    Author     : ntung
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Children's Care</title>
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
        <style>

            /*--------------------------------------------------------------
            # Team
            --------------------------------------------------------------*/
            .team .member {
                margin-bottom: 20px;
                overflow: hidden;
                text-align: center;
                border-radius: 5px;
                background: #fff;
                box-shadow: 0px 2px 15px rgba(0, 0, 0, 0.1);
            }
            .team .member .member-img {
                position: relative;
                overflow: hidden;
            }
            .team .member .social {
                position: absolute;
                left: 0;
                bottom: 0;
                right: 0;
                height: 40px;
                opacity: 0;
                transition: ease-in-out 0.3s;
                background: rgba(255, 255, 255, 0.85);
                display: inline-flex;
                justify-content: center;
                align-items: center;
            }
            .team .member .social a {
                transition: color 0.3s;
                color: #222222;
                margin: 0 10px;
                display: inline-flex;
                justify-content: center;
                align-items: center;
            }
            .team .member .social a i {
                line-height: 0;
            }
            .team .member .social a:hover {
                color: #3498db;
            }
            .team .member .social i {
                font-size: 18px;
                margin: 0 2px;
            }
            .team .member .member-info {
                padding: 25px 15px;
            }
            .team .member .member-info h4 {
                font-weight: 700;
                margin-bottom: 5px;
                font-size: 18px;
                color: #222222;
            }
            .team .member .member-info span {
                display: block;
                font-size: 13px;
                font-weight: 400;
                color: #aaaaaa;
            }
            .team .member .member-info p {
                font-style: italic;
                font-size: 14px;
                line-height: 26px;
                color: #777777;
            }
            .team .member:hover .social {
                opacity: 1;
            }
            .col-2dot4,
            .col-sm-2dot4,
            .col-md-2dot4,
            .col-lg-2dot4,
            .col-xl-2dot4 {
                position: relative;
                width: 100%;
                min-height: 1px;
                padding-right: 15px;
                padding-left: 15px;
            }
            .col-2dot4 {
                -webkit-box-flex: 0;
                -ms-flex: 0 0 20%;
                flex: 0 0 20%;
                max-width: 20%;
            }
            @media (min-width: 540px) {
                .col-sm-2dot4 {
                    -webkit-box-flex: 0;
                    -ms-flex: 0 0 20%;
                    flex: 0 0 20%;
                    max-width: 20%;
                }
            }
            @media (min-width: 720px) {
                .col-md-2dot4 {
                    -webkit-box-flex: 0;
                    -ms-flex: 0 0 20%;
                    flex: 0 0 20%;
                    max-width: 20%;
                }
            }
            @media (min-width: 960px) {
                .col-lg-2dot4 {
                    -webkit-box-flex: 0;
                    -ms-flex: 0 0 20%;
                    flex: 0 0 20%;
                    max-width: 20%;
                }
            }
            @media (min-width: 1140px) {
                .col-xl-2dot4 {
                    -webkit-box-flex: 0;
                    -ms-flex: 0 0 20%;
                    flex: 0 0 20%;
                    max-width: 20%;
                }
            }
            /*--------------------------------------------------------------
            # Featured Services Section
            --------------------------------------------------------------*/
            .featured-services .service-item {
                padding: 30px;
                transition: all ease-in-out 0.4s;
                background: var(--color-white);
                height: 100%;
            }
            .featured-services .service-item .icon {
                margin-bottom: 10px;
            }
            .featured-services .service-item .icon i {
                color: var(--color-primary);
                font-size: 36px;
                transition: 0.3s;
            }
            .featured-services .service-item h4 {
                font-weight: 600;
                margin-bottom: 15px;
                font-size: 24px;
            }
            .featured-services .service-item h4 a {
                color: var(--color-secondary);
                transition: ease-in-out 0.3s;
            }
            .featured-services .service-item p {
                line-height: 24px;
                font-size: 14px;
                margin-bottom: 0;
            }
            .featured-services .service-item:hover {
                transform: translateY(-10px);
                box-shadow: 0px 0 60px 0 rgba(var(--color-secondary-rgb), 0.1);
            }
            .featured-services .service-item:hover h4 a {
                color: var(--color-primary);
            }

            /*--------------------------------------------------------------
            # About Section
            --------------------------------------------------------------*/
            .about .about-img {
                position: relative;
                margin: 60px 0 0 60px;
            }
            .about .about-img:before {
                position: absolute;
                inset: -60px 0 0 -60px;
                z-index: -1;
                content: "";
                background: url("../img/about-bg.png") top left;
                background-repeat: no-repeat;
            }
            @media (max-width: 575px) {
                .about .about-img {
                    margin: 30px 0 0 30px;
                }
                .about .about-img:before {
                    inset: -30px 0 0 -30px;
                }
            }
            .about h3 {
                color: var(--color-secondary);
                font-family: var(--font-secondary);
                font-weight: 300;
                font-size: 32px;
                margin-bottom: 20px;
            }
            @media (max-width: 768px) {
                .about h3 {
                    font-size: 28px;
                }
            }
            .about .nav-pills {
                border-bottom: 1px solid rgba(var(--color-secondary-rgb), 0.2);
            }
            .about .nav-pills li + li {
                margin-left: 40px;
            }
            .about .nav-link {
                background: none;
                font-size: 18px;
                font-weight: 400;
                color: var(--color-secondary);
                padding: 12px 0;
                margin-bottom: -2px;
                border-radius: 0;
                font-family: var(--font-secondary);
            }
            .about .nav-link.active {
                color: var(--color-primary);
                background: none;
                border-bottom: 3px solid var(--color-primary);
            }
            @media (max-width: 575px) {
                .about .nav-link {
                    font-size: 16px;
                }
            }
            .about .tab-content h4 {
                font-size: 18px;
                margin: 0;
                font-weight: 700;
                color: var(--color-secondary);
            }
            .about .tab-content i {
                font-size: 22px;
                line-height: 0;
                margin-right: 8px;
                color: var(--color-primary);
            }

            /*--------------------------------------------------------------
            # Clients Section
            --------------------------------------------------------------*/
            .clients {
                padding: 0 0 60px 0;
            }
            .clients .swiper-slide img {
                opacity: 0.5;
                transition: 0.3s;
                filter: grayscale(100);
            }
            .clients .swiper-slide img:hover {
                filter: none;
                opacity: 1;
            }
            /*--------------------------------------------------------------
            # Features Section
            --------------------------------------------------------------*/
            .features .nav-tabs {
                border: 0;
            }
            .features .nav-link {
                border: 0;
                padding: 25px 20px;
                color: #485664;
                box-shadow: 5px 5px 25px rgba(72, 86, 100, 0.15);
                border-radius: 0;
                display: flex;
                align-items: center;
                justify-content: center;
                flex-direction: column;
                transition: 0s;
                cursor: pointer;
                height: 100%;
            }
            .features .nav-link i {
                font-size: 32px;
                line-height: 0;
            }
            .features .nav-link h4 {
                font-size: 20px;
                font-weight: 600;
                margin: 10px 0 0 0;
                color: var(--color-secondary);
            }
            .features .nav-link:hover {
                color: var(--color-primary);
            }
            .features .nav-link.active {
                transition: 0.3s;
                background: var(--color-secondary) linear-gradient(rgba(var(--color-primary-rgb), 0.95), rgba(var(--color-primary-rgb), 0.6));
                border-color: var(--color-primary);
            }
            .features .nav-link.active h4 {
                color: var(--color-white);
            }

            .features .tab-content {
                margin-top: 30px;
            }
            .features .tab-pane.active {
                -webkit-animation: fadeIn 0.5s ease-out;
                animation: fadeIn 0.5s ease-out;
            }
            .features .tab-pane h3 {
                font-weight: 600;
                font-size: 36px;
                color: var(--color-secondary);
            }
            .features .tab-pane ul {
                list-style: none;
                padding: 0;
            }
            .features .tab-pane ul li {
                padding-bottom: 10px;
            }
            .features .tab-pane ul i {
                font-size: 24px;
                margin-right: 4px;
                color: var(--color-primary);
            }
            .features .tab-pane p:last-child {
                margin-bottom: 0;
            }

            @keyframes fadeIn {
                0% {
                    opacity: 0;
                }
                100% {
                    opacity: 1;
                }
            }
            /*--------------------------------------------------------------
            # Services Section
            --------------------------------------------------------------*/
            .services .img {
                border-radius: 8px;
                overflow: hidden;
            }
            .services .img img {
                transition: 0.6s;
            }
            .services .details {
                padding: 50px 30px;
                margin: -100px 30px 0 30px;
                transition: all ease-in-out 0.3s;
                background: var(--color-white);
                position: relative;
                background: rgba(var(--color-white-rgb), 0.9);
                text-align: center;
                border-radius: 8px;
                box-shadow: 0px 0 25px rgba(var(--color-black-rgb), 0.1);
            }
            .services .details .icon {
                margin: 0;
                width: 72px;
                height: 72px;
                background: var(--color-primary);
                border-radius: 50px;
                display: flex;
                align-items: center;
                justify-content: center;
                margin-bottom: 20px;
                color: var(--color-white);
                font-size: 28px;
                transition: ease-in-out 0.3s;
                position: absolute;
                top: -36px;
                left: calc(50% - 36px);
                border: 6px solid var(--color-white);
            }
            .services .details h3 {
                color: var(--color-default);
                font-weight: 700;
                margin: 10px 0 15px 0;
                font-size: 22px;
                transition: ease-in-out 0.3s;
            }
            .services .details p {
                line-height: 24px;
                font-size: 14px;
                margin-bottom: 0;
            }
            .services .service-item:hover .details h3 {
                color: var(--color-primary);
            }
            .services .service-item:hover .details .icon {
                background: var(--color-white);
                border: 2px solid var(--color-primary);
            }
            .services .service-item:hover .details .icon i {
                color: var(--color-primary);
            }
            .services .service-item:hover .img img {
                transform: scale(1.2);
            }

            /*--------------------------------------------------------------
            # Testimonials Section
            --------------------------------------------------------------*/
            .testimonials {
                padding: 80px 0;
                background: url("../img/testimonials-bg.jpg") no-repeat;
                background-position: center center;
                background-size: cover;
                position: relative;
            }
            .testimonials::before {
                content: "";
                position: absolute;
                inset: 0;
                background: rgba(var(--color-secondary-dark-rgb), 0.8);
            }
            .testimonials .section-header {
                margin-bottom: 40px;
            }
            .testimonials .testimonials-carousel, .testimonials .testimonials-slider {
                overflow: hidden;
            }
            .testimonials .testimonial-item {
                text-align: center;
                color: var(--color-white);
            }
            .testimonials .testimonial-item .testimonial-img {
                width: 100px;
                border-radius: 50%;
                border: 6px solid rgba(var(--color-white-rgb), 0.15);
                margin: 0 auto;
            }
            .testimonials .testimonial-item h3 {
                font-size: 20px;
                font-weight: bold;
                margin: 10px 0 5px 0;
                color: var(--color-white);
            }
            .testimonials .testimonial-item h4 {
                font-size: 14px;
                color: rgba(var(--color-white-rgb), 0.6);
                margin: 0 0 15px 0;
            }
            .testimonials .testimonial-item .stars {
                margin-bottom: 15px;
            }
            .testimonials .testimonial-item .stars i {
                color: var(--color-yellow);
                margin: 0 1px;
            }
            .testimonials .testimonial-item .quote-icon-left, .testimonials .testimonial-item .quote-icon-right {
                color: rgba(var(--color-white-rgb), 0.6);
                font-size: 26px;
                line-height: 0;
            }
            .testimonials .testimonial-item .quote-icon-left {
                display: inline-block;
                left: -5px;
                position: relative;
            }
            .testimonials .testimonial-item .quote-icon-right {
                display: inline-block;
                right: -5px;
                position: relative;
                top: 10px;
                transform: scale(-1, -1);
            }
            .testimonials .testimonial-item p {
                font-style: italic;
                margin: 0 auto 15px auto;
            }
            .testimonials .swiper-pagination {
                margin-top: 20px;
                position: relative;
            }
            .testimonials .swiper-pagination .swiper-pagination-bullet {
                width: 12px;
                height: 12px;
                background-color: rgba(var(--color-white-rgb), 0.4);
                opacity: 0.5;
            }
            .testimonials .swiper-pagination .swiper-pagination-bullet-active {
                background-color: var(--color-white);
                opacity: 1;
            }
            @media (min-width: 992px) {
                .testimonials .testimonial-item p {
                    width: 80%;
                }
            }

            .color-indigo{
                color: #1769ff;
            }
            .color-cyan{
                color: #fd7e14;
            }
            .color-teal{
                color: #20c997;
            }
            .color-red{
                color: red;
            }
            .color-blue{
                color: #0d6efd;
            }
            .color-orange{
                color: #fd7e14;
            }
            /*--------------------------------------------------------------
# Pricing Section
--------------------------------------------------------------*/
            .pricing {
                background: rgba(72, 86, 100, 0.04);
            }
            .pricing .pricing-item {
                padding: 60px 40px;
                box-shadow: 0 3px 20px -2px rgba(108, 117, 125, 0.15);
                background: #ffffff;
                height: 100%;
                display: flex;
                flex-direction: column;
                border: 4px solid ffffff;
                border-radius: 10px;
                overflow: hidden;
            }
            .pricing .pricing-header {
                background: linear-gradient(rgba( 72, 86, 100, 0.9), rgba(var(--color-secondary-rgb), 0.9)), url("../img/pricing-bg.jpg") center center;
                background-size: cover;
                text-align: center;
                padding: 40px;
                margin: -60px -40px 0;
            }
            .pricing h3 {
                font-weight: 600;
                margin-bottom: 5px;
                font-size: 36px;
                color: var(--color-white);
            }
            .pricing h4 {
                font-size: 48px;
                color: #000;
                font-weight: 400;
                font-family: 'Source Sans Pro', sans-serif;
                ;
                margin-bottom: 0;
            }
            .pricing h4 sup {
                font-size: 28px;
            }
            .pricing h4 span {
                color: #000;
                font-size: 24px;
            }
            .pricing ul {
                padding: 30px 0;
                list-style: none;
                color: #6c757d;
                text-align: left;
                line-height: 20px;
            }
            .pricing ul li {
                padding: 10px 0;
                display: flex;
                align-items: center;
            }
            .pricing ul i {
                color: #0ea2bd;
                font-size: 36px;
                padding-right: 3px;
                line-height: 0;
            }
            .pricing ul .na {
                color: rgba(108, 117, 125, 0.5);
            }
            .pricing ul .na i {
                color: rgba(108, 117, 125, 0.5);
                font-size: 24px;
                padding-left: 4px;
            }
            .pricing ul .na span {
                text-decoration: line-through;
            }
            .pricing .buy-btn {
                display: inline-block;
                padding: 12px 40px;
                border-radius: 6px;
                color: #0ea2bd;
                transition: none;
                font-size: 16px;
                font-weight: 700;
                transition: 0.3s;
                border: 1px solid #0ea2bd;
            }
            .pricing .buy-btn:hover {
                background: #0ea2bd;
                color: #fff;
            }
            .pricing .featured {
                border-color: #0ea2bd;
            }
            .pricing .featured .pricing-header {
                background: linear-gradient(rgba(14, 162, 189), 0.9), rgba(14, 162, 189, 0.9), url("../img/pricing-bg.jpg") center center;
            }
            .pricing .featured .buy-btn {
                background: #0ea2bd;
                color: #fff;
            }

            .checked {
                color: orange;
            }

        </style>
        <!-- =======================================================
        * Template Name: Medilab - v4.7.1
        * Template URL: https://bootstrapmade.com/medilab-free-medical-bootstrap-theme/
        * Author: BootstrapMade.com
        * License: https://bootstrapmade.com/license/
        ======================================================== -->
    </head>

    <body style="padding: 80px" >

        <!-- ======= Top Bar ======= -->
        <div id="topbar" class="d-flex align-items-center fixed-top" >
            <div class="container d-flex justify-content-between">
                <div class="contact-info d-flex align-items-center">
                    <i class="bi bi-envelope"></i> <a href="mailto:contact@example.com">Children's Care@fpt.edu.vn</a>
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

        <!-- ======= Hero Slider Section ======= -->
        <section id="hero-slider" class="hero-slider">
            <div class="container-md" data-aos="fade-in">
                <div class="row">
                    <div class="col-12">
                        <div class="swiper sliderFeaturedPosts">
                            <div class="swiper-wrapper">
                                <c:forEach items="${listslider}" var="slide">
                                    <div class="swiper-slide">
                                        <a href="${i.backlink}" class="img-bg d-flex align-items-end" style="background-image: url('./${slide.slider_img}')"></a>
                                        <div class="img-bg-inner">
                                            <h2>${slide.slider_title}</h2>
                                            <p>${slide.slider_detail}</p>

                                        </div>
                                        </a>
                                    </div>

                                </c:forEach>
                            </div>
                            <div class="custom-swiper-button-next">
                                <span class="bi-chevron-right"></span>
                            </div>
                            <div class="custom-swiper-button-prev">
                                <span class="bi-chevron-left"></span>
                            </div>

                            <div class="swiper-pagination"></div>
                        </div>
                    </div>
                </div>
            </div>
        </section><!-- End Hero Slider Section -->

        <main id="main">

            <div class="container">
                <c:if test="${not empty sessionScope.checkoutSuccess}">
                    <div class="alert alert-success" role="alert">
                        ${sessionScope.checkoutSuccess}
                    </div>
                    <c:remove var="checkoutSuccess" scope="session" />
                </c:if>

                <!-- Rest of your home page content -->
            </div>

            <!-- ======= About Section ======= -->
            <section id="about" class="about">
                <div class="container">

                    <div class="row">
                        <div class="col-xl-5 col-lg-6 video-box d-flex justify-content-center align-items-stretch position-relative">
                            <a href="https://www.youtube.com/watch?v=jDDaplaOz7Q" class="glightbox play-btn mb-4"></a>
                        </div>

                        <div class="col-xl-7 col-lg-6 icon-boxes d-flex flex-column align-items-stretch justify-content-center py-5 px-lg-5">
                            <h3>Welcome Children's Care System.</h3>
                            <p>Your child deserves quality education from the start! The quality of child care has a direct impact on a child’s ability to learn, to build healthy relationships, and to become the best they can be.
                            </p>
                            <div class="icon-box">
                                <div class="icon"><i class="bx bx-fingerprint"></i></div>
                                <h4 class="title"><a href="">24/7</a></h4>
                                <p class="description">Always takes care your children anytime, anywhere.</p>
                            </div>

                            <div class="icon-box">
                                <div class="icon"><i class="bx bx-gift"></i></div>
                                <h4 class="title"><a href="">Best Services</a></h4>
                                <p class="description">A team of prestigious and top quality doctors from Eastern Laos, Cambodia, Thailand, Malaysia...</p>
                            </div>

                            <div class="icon-box">
                                <div class="icon"><i class="bx bx-atom"></i></div>
                                <h4 class="title"><a href="">Very Cheap</a></h4>
                                <p class="description">Don't worry about price. Here we have discount to 70% and sometimes free for broken children.</p>
                            </div>

                        </div>
                    </div>

                </div>
            </section><!-- End About Section -->

            <!-- ======= Counts Section ======= -->
            <section id="counts" class="counts">
                <div class="container">

                    <div class="row">

                        <div class="col-lg-3 col-md-6">
                            <div class="count-box">
                                <i class="fas fa-user-md"></i>
                                <span data-purecounter-start="0" data-purecounter-end="85" data-purecounter-duration="1" class="purecounter"></span>
                                <p>Doctors</p>
                            </div>
                        </div>

                        <div class="col-lg-3 col-md-6 mt-5 mt-md-0">
                            <div class="count-box">
                                <i class="far fa-hospital"></i>
                                <span data-purecounter-start="0" data-purecounter-end="18" data-purecounter-duration="1" class="purecounter"></span>
                                <p>Departments</p>
                            </div>
                        </div>

                        <div class="col-lg-3 col-md-6 mt-5 mt-lg-0">
                            <div class="count-box">
                                <i class="fas fa-flask"></i>
                                <span data-purecounter-start="0" data-purecounter-end="12" data-purecounter-duration="1" class="purecounter"></span>
                                <p>Research Labs</p>
                            </div>
                        </div>

                        <div class="col-lg-3 col-md-6 mt-5 mt-lg-0">
                            <div class="count-box">
                                <i class="fas fa-award"></i>
                                <span data-purecounter-start="0" data-purecounter-end="150" data-purecounter-duration="1" class="purecounter"></span>
                                <p>Awards</p>
                            </div>
                        </div>

                    </div>

                </div>
            </section><!-- End Counts Section -->
            <!-- ======= Pricing Section ======= -->
            <section id="hotservices" class="pricing">
                <div class="container" data-aos="fade-up">

                    <div class="section-title">
                        <h2>HOT SERVICES</h2>
                        <p>There are best services people usually buy.</p>
                    </div>

                    <div class="row gy-4">
                        <c:forEach items="${listservicenew}" var="hots">
                            <div class="col-lg-4" data-aos="zoom-in" data-aos-delay="200">
                                <div class="pricing-item">

                                    <div class="pricing-header">
                                        <h3>${hots.name_service}</h3>
                                        </br>
                                        <h4><sup>$</sup>${hots.sale_prices}<del>$ ${hots.original_prices}</del></h4>
                                    </div>

                                    <center>
                                        <p>${hots.brief_infor}</p>
                                    </center>
                                    </br>

                                    </br>
                                    </br>
                                    <style>
                                        del {
                                            text-decoration: line-through; /* Gạch ngang */
                                            color: red; /* Màu đỏ cho gạch ngang */
                                            font-size: 0.5em; /* Kích thước chữ nhỏ hơn */
                                        }

                                    </style>
                                    <div class="text-center mt-auto">
                                        <a href="ServiceDetails?serviceID=${hots.service_id}" class="buy-btn">Book Now</a>
                                    </div>

                                </div>
                            </div><!-- End Pricing Item -->
                        </c:forEach>





                    </div>

                </div>
            </section><!-- End Pricing Section -->

            <!-- ======= Recent Blog Posts Section ======= -->
            <section id="blogs" class="recent-blog-posts">
                <div class="container" data-aos="fade-up">
                    <div class="section-title">
                        <h2>Blogs</h2>
                        <p>There are many blogs in Children's Care</p>
                    </div>

                    <div class="row">
                        <c:forEach var="blog" items="${newtop6}">
                            <div class="col-lg-4" data-aos="fade-up" data-aos-delay="200">
                                <div class="post-box">
                                    <div class="post-img">
                                        <img src="./${blog.thumbnail}" class="img-fluid" alt="">
                                    </div>
                                    <div class="meta">
                                        <span class="post-date">${blog.create_date}</span>
                                    </div>
                                    <h4 class="post-title"><a href="BlogDetails?blogid=${blog.blog_id}">${blog.tittle}</a></h4>
                                    <!-- <a class="readmore stretched-link"><span>Read More</span><i class="bi bi-arrow-right"></i></a> -->
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <div class="text-center mt-auto">
                    <a href="BlogList" class="buy-btn">
                        <h5>MORE BLOGS ...</h5>
                    </a>
                </div>
            </section>
            <!-- End Recent Blog Posts Section -->


        </main><!-- End #main -->

        <!-- ======= Footer ======= -->
        <jsp:include page="assests/module/footer.jsp"  flush="true"/>
        <!-- END FOOTER -->
        <!--<div id="preloader"></div>-->
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
