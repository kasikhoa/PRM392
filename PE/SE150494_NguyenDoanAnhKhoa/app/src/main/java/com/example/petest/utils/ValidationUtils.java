package com.example.petest.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ValidationUtils {
    public static boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public static boolean isValidPhone(String phone) {

        String regex = "^0\\d{9}$";

        return phone.matches(regex);

    }
    public static boolean isValidDate(String inputDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Định dạng ngày tháng
        sdf.setLenient(false); // Tắt tính linh hoạt trong việc parse ngày tháng

        try {
            Date date = sdf.parse(inputDate);
            Date currentDate = new Date(); // Lấy ngày hiện tại

            // So sánh ngày nhập vào với ngày hiện tại
            if (date.before(currentDate)) {
                return true; // Ngày hợp lệ và trước ngày hiện tại
            } else {
                return false; // Ngày không hợp lệ hoặc sau ngày hiện tại
            }
        } catch (ParseException e) {
            return false; // Lỗi trong việc parse ngày tháng
        }
    }
}
