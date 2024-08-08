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
                                <c:forEach items="${sliderList}" var="slide">
                                    <div class="swiper-slide">
                                        <a href="/project_swp391/service-detail?service_id=${slide.service_id}" class="img-bg d-flex align-items-end" style="background-image: url('./resouce/image/service/${slide.image_link}');">
                                            <div class="img-bg-inner">
                                                <h2>${slide.service_title}</h2>
                                                <p>${slide.notes}</p>
                                                <p>${slide.slider_id}</p>

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
            <!-- ======= Features Section ======= -->
            <c:if test="${listSetting.get(0).setting_status==true}">

                <section id="services" class="features">
                    <div class="container" data-aos="fade-up">
                        <div class="section-title">
                            <h2>All Services</h2>
                            <p>There are many servies in Children's Care, Choose Your Category To See</p>
                        </div>
                        <ul class="nav nav-tabs row gy-4 d-flex">

                            <c:forEach items="${requestScope.categoryList}" var="cate">

                                <li class="nav-item col-6 col-md-4 col-lg-2">
                                    <a  class="nav-link" data-bs-toggle="tab" data-bs-target="#tab-${cate.category_id}" href="index?cid=${cate.category_id}">

                                        <i class="${cate.icon}"></i>
                                        <h4>${cate.category_name}</h4>
                                    </a>
                                </li><!-- End Tab 1 Nav -->
                            </c:forEach>


                        </ul>

                        <div class="tab-content">

                            <c:forEach items="${requestScope.list}" var="servicee">
                                <c:forEach items="${servicee}" var="service">
                                    <div class="tab-pane" id="tab-${service.category_id}">
                                        <div class="row gy-4">
                                            <div class="col-lg-8 order-2 order-lg-1" data-aos="fade-up" data-aos-delay="100">
                                                </br>
                                                <center>
                                                    <h3>${service.service_title}</h3>
                                                </center>
                                                </br>

                                                <p>
                                                    ${service.services_bi}
                                                </p>
                                                <p class="fst-italic">

                                                    Discount Price: ${service.service_discount}$
                                                </p>
                                                <p>
                                                <h5>

                                                </h5>
                                                Old Price: ${service.service_price}$
                                                </p>
                                                Rate Star: ${service.service_rateStar} <i class="bi bi-star-fill"></i>  
                                                <div class="text-center mt-auto">
                                                    <a href="service-detail?service_id=${service.service_id}" class="buy-btn"><h5>MORE DETAILS ...</h5></a>
                                                </div>
                                            </div>
                                            <div class="col-lg-4 order-1 order-lg-2 text-center" data-aos="fade-up" data-aos-delay="200">
                                                <img src="./resouce/image/service/${service.image.get(0).getImage_link()}" alt="" class="img-fluid">
                                            </div>
                                        </div>
                                    </div><!-- End Tab Content 1 -->
                                </c:forEach>
                            </c:forEach>

                        </div>

                    </div>
                </section><!-- End Features Section -->
            </c:if>









            <!-- ======= Pricing Section ======= -->
            <c:if test="${listSetting.get(2).setting_status==true}">
                <section id="hotservices" class="pricing">
                    <div class="container" data-aos="fade-up">

                        <div class="section-title">
                            <h2>HOT SERVICES</h2>
                            <p>There are best services people usually buy.</p>
                        </div>

                        <div class="row gy-4">
                            <c:forEach items="${ListHotService}" var="hots">
                                <div class="col-lg-4" data-aos="zoom-in" data-aos-delay="200">
                                    <div class="pricing-item">

                                        <div class="pricing-header">
                                            <h3>${hots.service_title}</h3>
                                            </br>
                                            <h4><sup>$</sup>${hots.service_price}<span>  3̶̶9̶̶9̶$</span></h4>
                                        </div>

                                        <center>
                                            <p>${hots.services_bi}</p>
                                        </center>
                                        </br>
                                        <center>${hots.service_rateStar} <i class="bi bi-star-fill"></i></center>
                                        </br>
                                        </br>
                                        <div class="text-center mt-auto">
                                            <a href="#" class="buy-btn">Buy Now</a>
                                        </div>

                                    </div>
                                </div><!-- End Pricing Item -->
                            </c:forEach>





                        </div>

                    </div>
                </section><!-- End Pricing Section -->
            </c:if>

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
                                    <h4 class="post-title">${blog.tittle}</h4>
                                    <!-- <a class="readmore stretched-link"><span>Read More</span><i class="bi bi-arrow-right"></i></a> -->
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <div class="text-center mt-auto">
                    <a href="Blogs" class="buy-btn">
                        <h5>MORE BLOGS ...</h5>
                    </a>
                </div>
            </section>
            <!-- End Recent Blog Posts Section -->



            <!-- ======= Doctors Section ======= -->

            <c:if test="${listSetting.get(4).setting_status==true}">

                <section id="doctors" class="doctors">
                    <div class="container">

                        <div class="section-title">
                            <h2>Doctors</h2>
                            <p>A team of professional doctors with many achievements and research awards with Master's and Doctoral degrees from all over Southeast Asia such as Eastern Laos, Cambodia, Thailand, Malaysia,... high quality stars with a great team of technicians.</p>
                        </div>

                        <div class="row">

                            <div class="col-lg-6">
                                <div class="member d-flex align-items-start">
                                    <div class="pic"><img src="assests/img/doctors/doctors-1.jpg" class="img-fluid" alt=""></div>
                                    <div class="member-info">
                                        <h4>Bác sĩ Hào</h4>
                                        <span>Chief Medical Officer</span>
                                        <p>TOP 5 Bác sĩ chuyên khoa phổi hàng đầu thế giới.</p>
                                        <div class="social">
                                            <a href=""><i class="ri-twitter-fill"></i></a>
                                            <a href=""><i class="ri-facebook-fill"></i></a>
                                            <a href=""><i class="ri-instagram-fill"></i></a>
                                            <a href=""> <i class="ri-linkedin-box-fill"></i> </a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-6 mt-4 mt-lg-0">
                                <div class="member d-flex align-items-start">
                                    <div class="pic"><img src="assests/img/doctors/doctors-2.jpg" class="img-fluid" alt=""></div>
                                    <div class="member-info">
                                        <h4>Bác sĩ Khoa</h4>
                                        <span>Anesthesiologist</span>
                                        <p>TOP 5 Bác sĩ cứu người giỏi nhất châu Á.</p>
                                        <div class="social">
                                            <a href=""><i class="ri-twitter-fill"></i></a>
                                            <a href=""><i class="ri-facebook-fill"></i></a>
                                            <a href=""><i class="ri-instagram-fill"></i></a>
                                            <a href=""> <i class="ri-linkedin-box-fill"></i> </a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-6 mt-4">
                                <div class="member d-flex align-items-start">
                                    <div class="pic"><img src="assests/img/doctors/doctors-3.jpg" class="img-fluid" alt=""></div>
                                    <div class="member-info">
                                        <h4>Bác sĩ Hoàn</h4>
                                        <span>Cardiology</span>
                                        <p>Quisquam facilis cum velit laborum corrupti fuga rerum quia</p>
                                        <div class="social">
                                            <a href=""><i class="ri-twitter-fill"></i></a>
                                            <a href=""><i class="ri-facebook-fill"></i></a>
                                            <a href=""><i class="ri-instagram-fill"></i></a>
                                            <a href=""> <i class="ri-linkedin-box-fill"></i> </a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-6 mt-4">
                                <div class="member d-flex align-items-start">
                                    <div class="pic"><img src="assests/img/doctors/doctors-4.jpg" class="img-fluid" alt=""></div>
                                    <div class="member-info">
                                        <h4>Bác sĩ Trường</h4>
                                        <span>Neurosurgeon</span>
                                        <p>Dolorum tempora officiis odit laborum officiis et et accusamus</p>
                                        <div class="social">
                                            <a href=""><i class="ri-twitter-fill"></i></a>
                                            <a href=""><i class="ri-facebook-fill"></i></a>
                                            <a href=""><i class="ri-instagram-fill"></i></a>
                                            <a href=""> <i class="ri-linkedin-box-fill"></i> </a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                    </div>
                </section><!-- End Doctors Section -->

            </c:if>




            <!-- ======= Testimonials Section ======= -->
            <c:if test="${listSetting.get(5).setting_status==true}">

                <section id="testimonials" class="testimonials">
                    <div class="container">
                        <div class="container">

                            <div class="section-title">
                                <h2>Feedback About Children's Care</h2>
                                <p>There are some random feedback about services of Children's Care System, Thanks every body so much for love their system.</p>
                            </div>
                        </div>
                        <div class="testimonials-slider swiper" data-aos="fade-up" data-aos-delay="100">
                            <div class="swiper-wrapper">

                                <c:forEach items="${listFeedback}" var="i">
                                    <div class="swiper-slide">
                                        <div class="testimonial-wrap">
                                            <div class="testimonial-item">
                                                <img src="./resouce/image/${i.feedback_image}" class="testimonial-img" alt="">
                                                <h3>${i.name}
                                                    <div class="d-flex flex-row p-4">
                                                        <div class="ratings m-auto">
                                                            <c:forEach begin="1" end="${i.rateStar}">
                                                                <span class="fa fa-star checked"></span>
                                                            </c:forEach>
                                                            <c:forEach begin="1" end="${5-i.rateStar}">
                                                                <span class="fa fa-star"></span>
                                                            </c:forEach>
                                                        </div>
                                                        <br>
                                                    </div></h3>

                                                <p>
                                                    <i class="bx bxs-quote-alt-left quote-icon-left"></i>
                                                    ${i.content}
                                                    <i class="bx bxs-quote-alt-right quote-icon-right"></i>
                                                </p>
                                            </div>
                                        </div>
                                    </div><!-- End testimonial item -->
                                </c:forEach>






                            </div>
                            <div class="swiper-pagination"></div>
                        </div>

                    </div>
                </section><!-- End Testimonials Section -->
            </c:if>

            <!-- ======= Team Section ======= -->
            <section id="team" class="team section-bg">
                <div class="container">

                    <div class="section-title" data-aos="fade-up">
                        <h2>Our Developer Team</h2>
                        <p>There are many developer from FPT University with love.</p>
                    </div>

                    <div class="row">

                        <div class="col-lg-2dot4 col-md-6 d-flex align-items-stretch">
                            <div class="member" data-aos="fade-up" data-aos-delay="100">
                                <div class="member-img">
                                    <img src="data:image/jpeg;base64,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" class="img-fluid" alt="">
                                    <div class="social">
                                        <a href=""><i class="bi bi-twitter"></i></a>
                                        <a href=""><i class="bi bi-facebook"></i></a>
                                        <a href=""><i class="bi bi-instagram"></i></a>
                                        <a href=""><i class="bi bi-linkedin"></i></a>
                                    </div>
                                </div>
                                <div class="member-info">
                                    <h4>xxxxx</h4>
                                    <span>Chief Executive Officer</span>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-2dot4 col-md-6 d-flex align-items-stretch">
                            <div class="member" data-aos="fade-up" data-aos-delay="200">
                                <div class="member-img">
                                    <img src="data:image/jpeg;base64,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"  class="img-fluid" alt="">
                                    <div class="social">
                                        <a href=""><i class="bi bi-twitter"></i></a>
                                        <a href=""><i class="bi bi-facebook"></i></a>
                                        <a href=""><i class="bi bi-instagram"></i></a>
                                        <a href=""><i class="bi bi-linkedin"></i></a>
                                    </div>
                                </div>
                                <div class="member-info">
                                    <h4>xxxxx</h4>
                                    <span>Product Manager</span>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-2dot4 col-md-6 d-flex align-items-stretch">
                            <div class="member" data-aos="fade-up" data-aos-delay="200">
                                <div class="member-img">
                                    <img src="data:image/jpeg;base64,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" class="img-fluid" alt="">
                                    <div class="social">
                                        <a href=""><i class="bi bi-twitter"></i></a>
                                        <a href=""><i class="bi bi-facebook"></i></a>
                                        <a href=""><i class="bi bi-instagram"></i></a>
                                        <a href=""><i class="bi bi-linkedin"></i></a>
                                    </div>
                                </div>
                                <div class="member-info">
                                    <h4>xxxxx</h4>
                                    <span>Product Manager</span>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-2dot4 col-md-6 d-flex align-items-stretch">
                            <div class="member" data-aos="fade-up" data-aos-delay="300">
                                <div class="member-img">
                                    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTqQt0iM8hkAc6xnh_SCuVfe6n5Hd4vae-TCQ&usqp=CAU" class="img-fluid" alt="">
                                    <div class="social">
                                        <a href=""><i class="bi bi-twitter"></i></a>
                                        <a href=""><i class="bi bi-facebook"></i></a>
                                        <a href=""><i class="bi bi-instagram"></i></a>
                                        <a href=""><i class="bi bi-linkedin"></i></a>
                                    </div>
                                </div>
                                <div class="member-info">
                                    <h4>xxxxxx</h4>
                                    <span>CTO</span>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-2dot4 col-md-6 d-flex align-items-stretch">
                            <div class="member" data-aos="fade-up" data-aos-delay="400">
                                <div class="member-img">
                                    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS1xKxDbF8xjs0vOEhqeCQdhZes_B3UGty91g&usqp=CAU"class="img-fluid" alt="">
                                    <div class="social">
                                        <a href=""><i class="bi bi-twitter"></i></a>
                                        <a href=""><i class="bi bi-facebook"></i></a>
                                        <a href=""><i class="bi bi-instagram"></i></a>
                                        <a href=""><i class="bi bi-linkedin"></i></a>
                                    </div>
                                </div>
                                <div class="member-info">
                                    <h4>xxxxx</h4>
                                    <span>Accountant</span>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>
            </section><!-- End Team Section -->



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
