<%@page import="model.User"%>
<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Ticket" %>

<%
    String currentCategory = request.getParameter("category") != null ? request.getParameter("category") : "all";
    String currentSort = request.getParameter("sort") != null ? request.getParameter("sort") : "";
%>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Cửa Hàng</title>

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
        <link rel="stylesheet" href="./assets/css/shop.css">
        <link rel="stylesheet" href="./assets/css/base.css">

    </head>

    <body>
        <nav class="navbar nav-bg">
            <div class="container">
                <div class="row nav-wrapper">
                    <div class="col-md-3">
                        <a class="nav-logo-link" href="">
                            <img src="./assets/img/logo/fac/Logo-Nav.png" alt="F-Active Logo" class="nav-brand-img">
                        </a>
                    </div>
                    <div class="col-md-6">
                        <ul class="nav-list">
                            <a href="home" class="nav-item-link">
                                <li class="nav-list-item">Trang chủ</li>
                            </a>
                            <a href="about" class="nav-item-link">
                                <li class="nav-list-item">Giới thiệu</li>
                            </a>
                            <a href="product" class="nav-item-link active">
                                <li class="nav-list-item">Cửa hàng</li>
                            </a>
                            <a href="contact" class="nav-item-link">
                                <li class="nav-list-item">Liên hệ</li>
                            </a>
                        </ul>
                    </div>
                    <div class="col-md-3">
                        <%
                            User d = (User) session.getAttribute("user");
                            if (d == null) {

                        %>
                        <a href="login" class="btn primary-btn btn-nav-login active">Đăng nhập</a>
                        <%                        } else {
                        %>
                        <a href="logout" class="btn primary-btn btn-nav-login active"><i class="bi bi-box-arrow-right"></i></a>
                        <a href="profile" class="btn primary-btn btn-nav-login active"><i class="bi bi-person-circle"></i></a>
                        <a href="cart" class="btn primary-btn btn-nav-login active"><i class="bi bi-bag"></i></a>
                        
                            <%                        }
                            %>
                    </div>
                </div>
        </nav>



        <section class="banner-section">

            <div id="carouselBanner" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#carouselBanner" data-bs-slide-to="0" class="active"
                            aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#carouselBanner" data-bs-slide-to="1"
                            aria-label="Slide 2"></button>
                </div>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <div class="row">
                            <div class="col-2">
                                <img src="./assets/img/events/cam-care.jpg" class="d-block w-100" alt="...">
                            </div>
                            <div class="col-2">
                                <img src="./assets/img/events/0107-2.jpg" class="d-block w-100" alt="...">
                            </div>
                            <div class="col-2">
                                <img src="./assets/img/events/luu-dau-chan-huong.jpg" class="d-block w-100" alt="...">
                            </div>
                            <div class="col-2">
                                <img src="./assets/img/events/photobuc.jpg" class="d-block w-100" alt="...">
                            </div>
                            <div class="col-2">
                                <img src="./assets/img/events/nhat-huyen.jpg" class="d-block w-100" alt="...">
                            </div>
                            <div class="col-2">
                                <img src="./assets/img/events/tram-den-cham.jpg" class="d-block w-100" alt="...">
                            </div>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="row">
                            <div class="col-2">
                                <img src="./assets/img/events/pink-shirt-day.jpg" class="d-block w-100" alt="...">
                            </div>
                            <div class="col-2">
                                <img src="./assets/img/events/nhat-huyen-ws.jpg" class="d-block w-100" alt="...">
                            </div>
                            <div class="col-2">
                                <img src="./assets/img/events/sac-viet.jpg" class="d-block w-100" alt="...">
                            </div>
                            <div class="col-2">
                                <img src="./assets/img/events/sac-xuan.jpg" class="d-block w-100" alt="...">
                            </div>
                            <div class="col-2">
                                <img src="./assets/img/events/ttdd-3.jpg" class="d-block w-100" alt="...">
                            </div>
                            <div class="col-2">
                                <img src="./assets/img/events/photobuc.jpg" class="d-block w-100" alt="...">
                            </div>
                        </div>
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselBanner" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselBanner" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </section>


        <section class="product-section">
            <div class="container">
                <div class="club-header">
                    <img src="./assets/img/logo/fac/Logo-back-text.png" alt="F-Active Logo" class="club-logo">
                </div>

                <div class="row">
                    <aside class="col-md-3">
                        <div class="filter-box">
                            <!-- Tìm kiếm -->
                            <div class="mb-4">
                                <form action="shop" method="get" class="custom-search-bar">
                                    <input type="text" name="keyword" placeholder="Tìm sản phẩm..." class="form-control"
                                           value="<%= request.getParameter("keyword") != null ? request.getParameter("keyword") : ""%>"/>
                                </form>
                            </div>

                            <!-- Bộ lọc danh mục -->
                            <p class="filter-heading"><strong>Danh mục</strong></p>
                            <ul class="list-unstyled">
                                <li><a href="shop?category=all<%= !currentSort.isEmpty() ? "&sort=" + currentSort : ""%>">Tất cả</a></li>
                                <li><a href="shop?category=1<%= !currentSort.isEmpty() ? "&sort=" + currentSort : ""%>">Vé sự kiện</a></li>
                                <li><a href="shop?category=21<%= !currentSort.isEmpty() ? "&sort=" + currentSort : ""%>">Sự kiện mới</a></li>
                            </ul>

                            <!-- Sắp xếp -->
                            <p class="filter-heading mt-4"><strong>Sắp xếp:</strong></p>
                            <ul class="list-unstyled">
                                <li><a href="shop?category=<%= currentCategory%>&sort=az">A-Z</a></li>
                                <li><a href="shop?category=<%= currentCategory%>&sort=za">Z-A</a></li>
                                <li><a href="shop?category=<%= currentCategory%>&sort=lowtohigh">Giá thấp đến cao</a></li>
                                <li><a href="shop?category=<%= currentCategory%>&sort=hightolow">Giá cao đến thấp</a></li>
                            </ul>
                        </div>
                    </aside>
                    <!-- Sản Phẩm -->
                    <div class="col-md-9">
                        <div class="product-container row">
                            <%
                                List<Ticket> productList = (List<Ticket>) request.getAttribute("productList");
                                if (productList != null && !productList.isEmpty()) {
                                    for (Ticket p : productList) {
                            %>
                            <div class="col-md-4 mb-4 d-flex">
                                <div class="ticket-wrapper">
                                    <a href="single?id=<%=p.getId()%>">
                                        <img src="<%= request.getContextPath() + "/" + p.getImageUrl()%>" class="li-img" alt="...">
                                    </a>
                                    <a href="single?id=<%=p.getId()%>">
                                        <p class="ticket-name"><%= p.getName()%></p>
                                    </a>
                                    <p class="ticket-time"></p>
                                    <p class="price"><%= String.format("%,d", p.getPrice())%> VND</p>
                                    <a href="cart?action=add&tid=<%=p.getId()%>" class="btn primary-btn buy">
                                        <i class="bi bi-bag"></i>
                                    </a>
                                </div>
                            </div>
                            <%
                                }
                            } else {
                            %>
                            <p>Không có sản phẩm nào.</p>
                            <%
                                }
                            %>
                        </div>
                    </div>
                    <div class="dots-pagination-horizontal">
                        <button class="btn btn-outline-danger rounded-circle dot-arrow prev">
                            <i class="bi bi-chevron-left"></i>
                        </button>

                        <div class="dots"></div>

                        <button class="btn btn-outline-danger rounded-circle dot-arrow next">
                            <i class="bi bi-chevron-right"></i>
                        </button>
                    </div>
                </div>
        </section>

        <section class="shop-booking">
            <div class="container">
                <h1 class="section-heading">Gửi <span class="section-heading-pink">lời nhắn</span> đến chúng tôi</h1>

                <form action="" method="post" class="booking-form">
                    <img src="./assets/img/Logo-back-text.png" alt="">
                    <label for="messageInput">Để lại lời nhắn cho chúng tớ nhé:</label>
                    <textarea name="messageInput" id="messageInput" cols="80" rows="10"
                              placeholder="Nhập lời nhắn của bạn"></textarea>

                    <button type="submit" class="btn primary-btn">Gửi lời nhắn</button>
                </form>
            </div>
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