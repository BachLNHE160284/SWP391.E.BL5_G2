
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header id="header" class="fixed-top">
    <div class="container d-flex align-items-center">

        <h1 class="logo me-auto"><a href="HomePage">Children's Care</a></h1>
        <!-- Uncomment below if you prefer to use an image logo -->
        <!-- <a href="index.html" class="logo me-auto"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->

        <nav id="navbar" class="navbar order-last order-lg-0">
            <ul>

                <li><a class="nav-link scrollto " href="HomePage">Home</a></li>
                <li><a class="nav-link scrollto" href="BlogList">Blogs</a></li>
                <li><a class="nav-link scrollto" href="ServiceList">Services</a></li>

                <c:if test="${sessionScope.acc.role_id == 4}">
                    <li><a class="nav-link scrollto" href="ManagerDashboard.jsp">Manager Dashboard</a></li>
                    </c:if>                   
                    <c:if test="${sessionScope.acc != null || sessionScope.acc != null}">
                    <li class="dropdown"><a href="#"><span>User Manager</span><i class="bi bi-chevron-right"></i></a>
                        <ul>
                            <li><a href="updateProfile">Edit profile</a></li>
                            <li><a href="ReservationDetails">My Reservation</a></li>
                            <li><a href="Logout">Logout</a></li>
                        </ul>
                    </li>
                </c:if>

            </ul>
            <i class="bi bi-list mobile-nav-toggle"></i>
        </nav><!-- .navbar -->

        <!--      <a href="login" class="appointment-btn scrollto"><span class="d-none d-md-inline"></span>Login</a>-->
        <a class="me-3 m-auto" href="ReservationDetails" >
            <i style="font-size: 30px;color:grey;" class="fas fa-shopping-cart"></i>
            <span id="cart-number" class="position-absolute bottom-50 start-99 translate-middle badge rounded-pill bg-danger">
                <c:choose>
                    <c:when test="${sessionScope.size() != null}">${sessionScope.size()}</c:when>
                    <c:otherwise>.</c:otherwise>
                </c:choose>
                <span  class="visually-hidden">Cart item</span>
            </span>
        </a>
        <c:choose>
            <c:when test="${sessionScope.acc != null || sessionScope.admin != null}">
                <c:choose>
                    <c:when test="${sessionScope.acc != null}">
                        <a href="#" class="btn btn-#fff ms-lg-2">
                            <p>
                                <img style="margin-top: 25px;height: 50px; width: 50px; border-radius: 50%;" 
                                     src="./${sessionScope.acc.avartar}" alt="User Image">
                            </p> 
                            <p>${sessionScope.acc.fullname}</p>
                        </a>     
                    </c:when>
                    <c:otherwise>
                        <a href="#" class="btn btn-#fff ms-lg-2">
                            <p>
                                <img style="margin-top: 25px;height: 50px; width: 50px; border-radius: 50%; background-color: #fff; border-color: #fff; border: 2px solid #fff;" 
                                     src="./${sessionScope.admin.avartar}" alt="Admin Image">
                            </p> 
                            <p>${sessionScope.admin.username}</p>
                        </a>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <a href="Login.jsp" style="padding: 25px; margin-top: 20px;">
                    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16" style="color: gray;">
                    <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                    <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                    </svg>
                    <p>Login</p>
                </a>
            </c:otherwise>
        </c:choose>



    </div>
</header>

