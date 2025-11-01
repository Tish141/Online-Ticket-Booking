<%-- 
    Document   : user
    Created on : Jul 13, 2025, 2:35:24 PM
    Author     : BACH YEN
--%>

<%@page import="model.IO"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Thông tin tài khoản</title>

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
        <link rel="stylesheet" href="./assets/css/user.css">
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



        <!--    LOAD USER SESSION-->
        <%
            request.getSession();
            User u = (User) session.getAttribute("user");
        %>




        <section class="user-profile container">
            <h1 class="section-heading">Chi tiết <span class="section-heading-pink">tài khoản</span></h1>

            <div class="user-profile-content">
                <div class="input-group mb-3">
                    <span class="input-group-text" id="inputGroup-sizing-default">Tài khoản</span>
                    <input type="text" class="form-control" aria-label="Sizing example input"
                           aria-describedby="inputGroup-sizing-default" value="<%=u.getName()%>" readonly>
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="inputGroup-sizing-default">Email</span>
                    <input type="text" class="form-control" aria-label="Sizing example input"
                           aria-describedby="inputGroup-sizing-default" value="<%=u.getEmail()%>" readonly>
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="inputGroup-sizing-default">Số điện thoại</span>
                    <input type="text" class="form-control" aria-label="Sizing example input"
                           aria-describedby="inputGroup-sizing-default" value="<%=u.getPhone()%>" readonly>
                </div>

                <!--            <div class="input-group mb-3">
                                <span class="input-group-text" id="inputGroup-sizing-default">Mật khẩu</span>
                                <input type="password" class="form-control" id="displayPassword" aria-label="Sizing example input"
                                    aria-describedby="inputGroup-sizing-default" value="<%=u.getPasswordHash()%>" readonly>
                                <button class="btn btn-primary" onclick="togglePassword()"><i class="bi bi-eye-slash"></i></button>
                            </div>-->
                <div class="input-group mb-3">
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editProfileModal">
                        Chỉnh sửa thông tin
                    </button>
                </div>
            </div>
        </section>

        <!-- Modal -->
        <div class="modal fade mt-5 user-edit-modal" id="editProfileModal" data-bs-backdrop="static"
             data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="profile?view=edit" method="post">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="staticBackdropLabel">Chỉnh sửa tài khoản</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3 row">
                                <label for="staticEmail" class="col-sm-3 col-form-label">UID</label>
                                <div class="col-sm-9">
                                    <input type="text" readonly class="form-control-plaintext" id="staticEmail"
                                           value="<%=u.getId()%>" name="inputUID">
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="inputUserName" class="col-sm-3 col-form-label">Tên</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" value="<%=u.getName()%>" id="inputUserName" name="inputUserName" required>
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="inputEmail" class="col-sm-3 col-form-label">Email</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" value="<%=u.getEmail()%>" id="inputEmail" name="inputEmail"  aria-describedby="emailHelp" required>
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="inputPassword" class="col-sm-3 col-form-label">Mật khẩu</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="inputPassword" name="inputPassword" required>
                                    <div class="form-text">Hãy nhập lại mật khẩu hiện tại nếu bạn không có bất kỳ thay đổi nào.</div>
                                </div>
                            </div>
                            <div class="mb-5 row">
                                <label for="inputPhone" class="col-sm-3 col-form-label">Số điện thoại</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" value="<%=u.getPhone()%>" id="inputPhone" name="inputPhone" required>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                            <button type="submit" class="btn btn-primary">Lưu</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <section class="order-history container">
            <h1 class="section-heading">Lịch sử <span class="section-heading-pink">đơn hàng</span></h1>

            <div class="order-history-show">
                <div class="orders-card">
                    <div class="orders-card-inner">
                        <div class="orders-card-header">
                            <div class="orders-card-heading-text">
                                <h5>Đơn hàng: [orderID]</h5>
                            </div>
                            <div class="orders-card-heading-status">
                                <h5>Trạng thái: [orderStatus]</h5>
                            </div>
                        </div>

                        <div class="orders-card-body">
                            <div class="orders-card-infor">

                                <!-- Thông tin đơn hàng button -->
                                <p class="d-inline-flex gap-1">
                                    <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseOrderItems"
                                       role="button" aria-expanded="false" aria-controls="collapseOrderItems">
                                        Thông tin sản phẩm
                                    </a>
                                </p>

                                <!-- Thông tin đơn hàng content -->
                                <div class="collapse" id="collapseOrderItems">
                                    <div class="card card-body">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col">#</th>
                                                    <th scope="col">First</th>
                                                    <th scope="col">Last</th>
                                                    <th scope="col">Handle</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th scope="row">1</th>
                                                    <td>Mark</td>
                                                    <td>Otto</td>
                                                    <td>@mdo</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">2</th>
                                                    <td>Jacob</td>
                                                    <td>Thornton</td>
                                                    <td>@fat</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">3</th>
                                                    <td>John</td>
                                                    <td>Doe</td>
                                                    <td>@social</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="orders-card-footer">
                            <p>Ngày mua: 11/07/2025</p>
                            <p>Tổng tiền: -----</p>
                        </div>
                    </div>
                </div>

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
                                <a href="" class="footer-item-link">
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

        <script>
            function togglePassword() {
                const passwordInput = document.getElementById("displayPassword");
                if (passwordInput.type === "password") {
                    passwordInput.type = "text";
                } else {
                    passwordInput.type = "password";
                }
            }
        </script>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
        crossorigin="anonymous"></script>
    </body>

</html>
