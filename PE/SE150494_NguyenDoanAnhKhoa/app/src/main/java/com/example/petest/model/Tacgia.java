package com.example.petest.model;

public class Tacgia {
    private int idTacgia;
    private String TenTacgia;
    private String dienthoai;
    private String Diachi;

    public Tacgia() {
    }

    public Tacgia(int idTacgia, String TenTacgia, String dienthoai, String Diachi) {
        this.idTacgia = idTacgia;
        this.TenTacgia = TenTacgia;
        this.dienthoai = dienthoai;
        this.Diachi = Diachi;
    }

    public Tacgia(String TenTacgia, String dienthoai, String Diachi) {
        this.TenTacgia = TenTacgia;
        this.dienthoai = dienthoai;
        this.Diachi = Diachi;
    }

    public int getIdTacgia() {
        return idTacgia;
    }

    public void setIdTacgia(int idTacgia) {
        this.idTacgia = idTacgia;
    }

    public String getTenTacgia() {
        return TenTacgia;
    }

    public void setTenTacgia(String TenTacgia) {
        this.TenTacgia = TenTacgia;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
    }
}
