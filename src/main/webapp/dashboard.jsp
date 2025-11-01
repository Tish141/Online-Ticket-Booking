<%-- 
    Document   : dashboard
    Created on : Jul 7, 2025, 9:09:38 PM
    Author     : BACH YEN
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <head>
        <!-- LINK BOOTSTRAP -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
        <!-- LINK FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap"
              rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Playpen+Sans:wght@100..800&display=swap" rel="stylesheet">

        <!-- LINK CSS -->
        <link rel="stylesheet" href="./assets/css/dashboard.css">
        <link rel="stylesheet" href="./assets/css/base.css">

    </head>

    <body>

        <nav class="navbar bg-body-tertiary fixed-top">
            <div class="container-fluid">
                <div class="dashboard-nav-wrapper">
                    <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas"
                            data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                </div>


                <div class="dashboard-nav-wrapper">
                    <img src="./assets/img/logo/fac/LogoA-trans.png" alt="">
                    <a class="navbar-brand dashboard-nav-brand" href="dashboard">
                        Welcome, <%User u = (User) session.getAttribute("user");
                            out.println(u.getName());
                        %>
                    </a>

                    <a href="logout" class="btn primary-btn btn-nav-login active"><i class="bi bi-box-arrow-right"></i></a>
                </div>
                <div class="offcanvas offcanvas-start dashboard-offcanvas" tabindex="-1" id="offcanvasNavbar"
                     aria-labelledby="offcanvasNavbarLabel">
                    <div class="offcanvas-header">
                        <h5 class="offcanvas-title" id="offcanvasNavbarLabel">
                            <a class="navbar-brand" href="#">
                                <img src="./assets/img/logo/fac/Logo-back-text.png" alt="">
                            </a>
                        </h5>
                        <!-- <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button> -->
                    </div>
                    <div class="offcanvas-body">
                        <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                            <!-- Đơn hàng -->
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                   aria-expanded="false">
                                    Đơn hàng
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#">Quản lý đơn hàng</a></li>
                                    <li><a class="dropdown-item" href="#">Chi tiết đơn hàng</a></li>
                                    <li>
                                        <hr class="dropdown-divider">
                                    </li>
                                    <li><a class="dropdown-item" href="#">Thông tin thanh toán</a></li>
                                </ul>
                            </li>
                            <!-- Sản phẩm -->
<!--                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                   aria-expanded="false">
                                    Sản phẩm
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#">Quản lý sản phẩm</a></li>
                                    <li><a class="dropdown-item" href="#">Danh mục sản phẩm</a></li>
                                    <li>
                                         <hr class="dropdown-divider">
                                    </li>
                                    <li><a class="dropdown-item" href="#">Something else here</a></li> 
                                </ul>
                            </li>-->

                            <!-- Sự kiện -->
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                   aria-expanded="false">
                                    Sự kiện
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#">Quản lý vé</a></li>
                                    <li><a class="dropdown-item" href="#">Danh mục vé</a></li>
                                    <li>
                                        <hr class="dropdown-divider">
                                    </li>
                                    <li><a class="dropdown-item" href="#">Thông tin sự kiện</a></li>
                                </ul>
                            </li>

                            <!-- Người dùng -->
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                   aria-expanded="false">
                                    Người dùng
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="users">Quản lý người dùng</a></li>
                                    <li>
                                        <hr class="dropdown-divider">
                                    </li>
                                    <li><a class="dropdown-item" href="messages">Tất cả tin nhắn</a></li>
                                    <li><a class="dropdown-item" href="messages?view=inbox">Tin nhắn chưa đọc</a></li>
                                </ul>
                            </li>
                        </ul>


                    </div>
                </div>
            </div>
        </nav>

        <section class="dashboard-content container">
            <h1 class="section-heading">Chào mừng đến với <span class="section-heading-pink">Active-Net-Admin</span></h1>




        </section>




        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <img src="./assets/img/logo/fac/LogoA-trans.png" alt="F-Active Logo" class="footer-logo-img">
                        <p class="footer-text">
                            Active Net một dự án cộng tác từ Câu lạc bộ sự kiện F-Active cùng HiFive Team
                        </p>
                        <ul class="footer-social-link-list">
                            <a href="" class="footer-social-link-item">
                                <li class="footer-social-list-item">
                                    <i class="bi bi-facebook"></i>
                                </li>
                            </a>
                            <a href="" class="footer-social-link-item">
                                <li class="footer-social-list-item">
                                    <i class="bi bi-youtube"></i>
                                </li>
                            </a>
                            <a href="" class="footer-social-link-item">
                                <li class="footer-social-list-item">
                                    <i class="bi bi-tiktok"></i>
                                </li>
                            </a>
                        </ul>
                    </div>
                    <div class="col-md-4 footer-col">
                        <div class="footer-content">
                            <h4 class="footer-heading">
                                Active Net
                            </h4>
                            <ul class="footer-link-list">
                                <a href="home" class="footer-item-link">
                                    <li class="footer-link-list-item">Trang chủ</li>
                                </a>
                                <a href="about" class="footer-item-link">
                                    <li class="footer-link-list-item">Giới thiệu</li>
                                </a>
                                <a href="product" class="footer-item-link">
                                    <li class="footer-link-list-item">Cửa hàng</li>
                                </a>
                                <a href="contact" class="footer-item-link">
                                    <li class="footer-link-list-item">Liên hệ</li>
                                </a>
                                <a href="login" class="footer-item-link">
                                    <li class="footer-link-list-item">Đăng nhập</li>
                                </a>
                            </ul>
                        </div>

                    </div>
                    <div class="col-md-4 footer-col">
                        <div class="footer-content">
                            <h4 class="footer-heading">
                                Get In Touch
                            </h4>
                            <ul class="footer-link-list">
                                <a href="mailto: clbfactive1420@gmail.com" class="footer-item-link">
                                    <li class="footer-link-list-item">clbfactive1420@gmail.com</li>
                                </a>
                                <a href="tel:0704906670" class="footer-item-link">
                                    <li class="footer-link-list-item">070 490 6670</li>
                                </a>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <p class="footer-copyright">Copyright © 2025 Hifive Team | All Rights Deserved @hifiveteam</p>
                </div>
            </div>
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
        crossorigin="anonymous"></script>
    </body>

</html>