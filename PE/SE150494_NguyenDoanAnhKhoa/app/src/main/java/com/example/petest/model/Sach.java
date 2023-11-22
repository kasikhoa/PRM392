package com.example.petest.model;

import java.util.Date;

public class Sach {
    private int MaSach;
    private String Tensach;
    private String Theloai;
    private int idTacgia;
    private String NgayXB;

    public Sach(int masach, String tensach, String theloai, int idTacgia, String ngayXB) {
        MaSach = masach;
        Tensach = tensach;
        Theloai = theloai;
        this.idTacgia = idTacgia;
        NgayXB = ngayXB;
    }

    public Sach(String tensach, String theloai, int idTacgia, String ngayXB) {
        Tensach = tensach;
        Theloai = theloai;
        this.idTacgia = idTacgia;
        NgayXB = ngayXB;
    }

    public int getMasach() {
        return MaSach;
    }

    public void setMasach(int masach) {
        MaSach = masach;
    }

    public String getTensach() {
        return Tensach;
    }

    public void setTensach(String tensach) {
        Tensach = tensach;
    }

    public String getTheloai() {
        return Theloai;
    }

    public void setTheloai(String theloai) {
        Theloai = theloai;
    }

    public int getIdTacgia() {
        return idTacgia;
    }

    public void setIdTacgia(int idTacgia) {
        this.idTacgia = idTacgia;
    }

    public String getNgayXB() {
        return NgayXB;
    }

    public void setNgayXB(String ngayXB) {
        NgayXB = ngayXB;
    }
}
