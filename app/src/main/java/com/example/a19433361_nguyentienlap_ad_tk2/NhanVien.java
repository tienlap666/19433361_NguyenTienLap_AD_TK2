package com.example.a19433361_nguyentienlap_ad_tk2;

import android.graphics.Bitmap;

public class NhanVien {
    private Bitmap img;
    private String maNV;
    private String tenNV;
    private String gioiTinh;
    private String donVi;

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public NhanVien(Bitmap img, String maNV, String tenNV, String gioiTinh, String donVi) {
        this.img = img;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gioiTinh = gioiTinh;
        this.donVi = donVi;
    }

}
