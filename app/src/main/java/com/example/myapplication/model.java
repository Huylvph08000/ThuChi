package com.example.myapplication;

public class model {
    private String tenKhoan;
    private String soTien;
    private String loai;

    public model(String tenKhoan, String soTien, String loai) {
        this.tenKhoan = tenKhoan;
        this.soTien = soTien;
        this.loai = loai;
    }

    public model() {
    }

    public String getTenKhoan() {
        return tenKhoan;
    }

    public void setTenKhoan(String tenKhoan) {
        this.tenKhoan = tenKhoan;
    }

    public String getSoTien() {
        return soTien;
    }

    public void setSoTien(String soTien) {
        this.soTien = soTien;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }
    @Override
    public String toString() {
        return tenKhoan;
    }
}
