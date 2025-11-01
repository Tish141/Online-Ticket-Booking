package model;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Tran Hieu Nghia - CE191115
 */
public class IO {

    public static String hashMD5(String pass) {
        try {
            MessageDigest mes = MessageDigest.getInstance("MD5");
            byte[] mesMD5 = mes.digest(pass.getBytes());
            //[0x0a, 0x7a, 0x12, 0x09,...]
            StringBuilder str = new StringBuilder();
            for (byte b : mesMD5) {
                //0x0a
                String ch = String.format("%02x", b);
                //0a
                str.append(ch);
            }
            //str = 0a7a1209
            return str.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public static String formatCurrency(String number) {
        StringBuilder result = new StringBuilder();
        int count = 0;

        // Duyệt chuỗi từ phải sang trái
        for (int i = number.length() - 1; i >= 0; i--) {
            char c = number.charAt(i);
            result.insert(0, c); // Thêm ký tự vào đầu kết quả
            count++;

            // Sau mỗi 3 ký tự và không phải đầu chuỗi thì thêm dấu chấm
            if (count % 3 == 0 && i != 0) {
                result.insert(0, '.');
            }
        }

        return result.toString();
    }

//    USER LOGIN/REGISTER VALIDATE INPUT
    public static List<String> userLoginValidator(String email, String password) {
        List<String> errors = new ArrayList<>();

        if (email == null || email.trim().isEmpty()) {
            errors.add("Email không được để trống.");
        } else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            errors.add("Email không hợp lệ.");
        }

        if (password == null || password.length() < 6) {
            errors.add("Mật khẩu phải từ 6 ký tự trở lên.");
        }

        return errors;
    }

    public static List<String> userRegisterValidator(String username, String email, String password, String phone) {
        List<String> errors = new ArrayList<>();

        // Username: không rỗng, ít nhất 3 ký tự
        if (username == null || username.trim().isEmpty()) {
            errors.add("Tên đăng nhập không được để trống.");
        } else if (username.length() < 3) {
            errors.add("Tên đăng nhập phải có ít nhất 3 ký tự.");
        }

        // Email: phải đúng định dạng
        if (email == null || email.trim().isEmpty()) {
            errors.add("Email không được để trống.");
        } else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            errors.add("Email không hợp lệ.");
        }

        // Password: ít nhất 6 ký tự, có chữ và số
        if (password == null || password.length() < 6) {
            errors.add("Mật khẩu phải có ít nhất 6 ký tự.");
        } else if (!password.matches(".*[a-zA-Z].*") || !password.matches(".*\\d.*")) {
            errors.add("Mật khẩu phải chứa cả chữ và số.");
        }

        // Phone (số Việt Nam): bắt đầu bằng 0, đủ 10 số, đầu 03, 05, 07, 08, 09
        if (phone == null || phone.trim().isEmpty()) {
            errors.add("Số điện thoại không được để trống.");
        } else if (!phone.matches("^(03|05|07|08|09)\\d{8}$")) {
            errors.add("Số điện thoại không hợp lệ (phải là số Việt Nam hợp lệ).");
        }

        return errors;
    }
}
