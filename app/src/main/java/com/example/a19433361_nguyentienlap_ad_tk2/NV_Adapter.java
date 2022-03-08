package com.example.a19433361_nguyentienlap_ad_tk2;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class NV_Adapter extends BaseAdapter {
    Context context;
    ArrayList<NhanVien> arr;

    public NV_Adapter(Context context, ArrayList<NhanVien> arr) {
        this.context=context;
        this.arr=arr;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int i) {
        return arr.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.nhanvien,null);

        ImageView imgV = view.findViewById(R.id.imgV_ItemNV);
        TextView txtMa = view.findViewById(R.id.txtV_maNV);
        TextView txtTen = view.findViewById(R.id.txtV_tenNV);
        TextView txtGioiTinh = view.findViewById(R.id.txtV_GioiTinhNV);
        TextView txtDonVi = view.findViewById(R.id.txtV_DonViNV);

        NhanVien nhanVien = arr.get(i);


        imgV.setImageBitmap(nhanVien.getImg());
        txtMa.setText(nhanVien.getMaNV());
        txtTen.setText(nhanVien.getTenNV());
        txtGioiTinh.setText(nhanVien.getGioiTinh());
        txtDonVi.setText(nhanVien.getDonVi());

        return view;
    }
}
