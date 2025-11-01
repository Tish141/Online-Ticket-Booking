<%@page import="model.Ticket"%>
<%@page import="dao.TicketsDAO"%>
<%@page import="model.User"%>
<%@page import="model.IO"%>
<%@ page import="java.util.List" %>
<%@ page import="model.CartItem" %>
<%@ page import="dao.CartItemDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // Gọi DAO trực tiếp để lấy dữ liệu test
//    CartItemDAO dao = new CartItemDAO();
//    List<CartItem> cartItems = dao.getAll();
//    request.setAttribute("cartItems", cartItems); // để giữ nguyên code phía dưới
%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Giỏ Hàng</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Playpen+Sans&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="./assets/css/cart.css">
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
                            <a href="product" class="nav-item-link">
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

        <main class="container">
            <section class="paying">
                <h1 class="section-heading">
                    Thông tin <span class="section-heading-pink">giỏ hàng</span>
                </h1>

                <div class="paying order-info-table w-75 mx-auto">
                    <form class="paying-order-table-info">
                        <div class="row fw-bold pb-2">
                            <div class="col-6">Tên sản phẩm</div>
                            <div class="col-2">Số lượng</div>
                            <div class="col-2">Đơn giá</div>
                            <div class="col-2">Thành tiền</div>
                        </div>

                        <%
                            List<CartItem> cartItems = (List<CartItem>) request.getAttribute("cartItems");
                            int totalAmount = 0;
                            TicketsDAO ticketDao = new TicketsDAO();
                            
                            if (cartItems != null && !cartItems.isEmpty()) {
                                for (CartItem item : cartItems) {
                                    int itemTotal = item.getUnitPrice() * item.getQuantity();
                                    totalAmount += itemTotal;
                                    Ticket t = ticketDao.getTicketById(item.getItemId());

                        %>
                        <div class="row py-3">
                            <div class="col-6 d-flex">
                                <a href="cart?action=delete&itemId=<%=item.getId()%>" class="paying-order-table-btn-remove me-2">&times;</a>
                                <span><%=t.getName()%></span>
                            </div>
                            <div class="col-2">
                                <input type="text" readonly class="paying-order-table-quantity-input w-100" value="<%= item.getQuantity()%>" min="1">
                            </div>
                            <div class="col-2"><%= IO.formatCurrency(item.getUnitPrice() + "")%></div>
                            <div class="col-2"><%= IO.formatCurrency(itemTotal + "")%></div>
                        </div>
                        <%
                            }
                            request.setAttribute("totalAmount", totalAmount);
                        } else {
                        %>
                        <div class="row py-3">
                            <div class="col-12 text-center">Giỏ hàng trống</div>
                        </div>
                        <% }%>

                        <div class="row">
                            <div class="col-12">
                                <div class="order-info-line"></div>
                            </div>
                        </div>

                        <div class="row pt-3 fw-bold text-end">
                            <div class="col-10 text-end">Tổng thành tiền</div>
                            <div class="col-2 text-center" style="color: var(--pink-primary);">
                                <%= IO.formatCurrency(totalAmount + "")%>
                            </div>
                        </div>

                        <div class="row pt-4">
                            <div class="col text-end">
                                <button type="button" class="btn primary-btn" data-bs-toggle="modal" data-bs-target="#modalChuyenKhoan">
                                    Thanh toán
                                </button>
                            </div>

                        </div>
                    </form>
                </div>
            </section>

            <div class="modal fade" id="modalChuyenKhoan" tabindex="-1">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header flex-column">
                            <img src="./assets/img/logo/fac/Logo-back-text.png" alt="Logo-FAC" class="logo-fac" />
                        </div>
                        <section class="modal-body">
                            <h4 class="modal-body-head" id="modalChuyenKhoanmodal-body-label">
                                <span>Nội dung chuyển khoản</span>
                            </h4>
                            <p><strong>${bankTransferNote}</strong></p> 
                            <p>
                                <span class="modal-body-label">Lưu ý:</span>
                                <span class="modal-body-content">Điền chính xác nội dung chuyển khoản</span>
                            </p>
                            <img src="./assets/img/QR.jpg" alt="QR" class="QR" />
                        </section>
                        <div class="modal-footer">
                            <button type="button" class="btn primary-btn modal-btn-button-cancel" data-bs-dismiss="modal">Hủy</button>
                            <form action="cart" method="post">
                                <input type="hidden" name="action" value="checkout">

                                <input type="hidden" name="totalAmount" value="<%= totalAmount%>">
                                <input type="hidden" name="bankTransferNote" value="Giao dịch từ website">

                                <button type="submit" class="btn primary-btn modal-btn-button-finish">Xong !</button>
                            </form>


                        </div>
                    </div>
                </div>
            </div>
        </main>

        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <img src="./assets/img/logo/fac/LogoA-trans.png" alt="F-Active Logo" class="footer-logo-img">
                        <p class="footer-text">Active Net một dự án cộng tác từ Câu lạc bộ sự kiện F-Active cùng HiFive Team</p>
                        <ul class="footer-social-link-list">
                            <a href="#" class="footer-social-link-item"><li class="footer-social-list-item"><i class="bi bi-facebook"></i></li></a>
                            <a href="#" class="footer-social-link-item"><li class="footer-social-list-item"><i class="bi bi-youtube"></i></li></a>
                            <a href="#" class="footer-social-link-item"><li class="footer-social-list-item"><i class="bi bi-tiktok"></i></li></a>
                        </ul>
                    </div>
                    <div class="col-md-4 footer-col">
                        <div class="footer-content">
                            <h4 class="footer-heading">Active Net</h4>
                            <ul class="footer-link-list">
                                <a href="#" class="footer-item-link"><li class="footer-link-list-item">Trang chủ</li></a>
                                <a href="#" class="footer-item-link"><li class="footer-link-list-item">Giới thiệu</li></a>
                                <a href="#" class="footer-item-link"><li class="footer-link-list-item">Cửa hàng</li></a>
                                <a href="#" class="footer-item-link"><li class="footer-link-list-item">Liên hệ</li></a>
                                <a href="#" class="footer-item-link"><li class="footer-link-list-item">Đăng nhập</li></a>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-4 footer-col">
                        <div class="footer-content">
                            <h4 class="footer-heading">Get In Touch</h4>
                            <ul class="footer-link-list">
                                <a href="mailto:clbfactive1420@gmail.com" class="footer-item-link"><li class="footer-link-list-item">clbfactive1420@gmail.com</li></a>
                                <a href="tel:0704906670" class="footer-item-link"><li class="footer-link-list-item">070 490 6670</li></a>
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
