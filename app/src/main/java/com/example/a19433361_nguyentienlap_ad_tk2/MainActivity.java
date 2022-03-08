package com.example.a19433361_nguyentienlap_ad_tk2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static int RESULT_LOAD_IMG = 1;

    ListView listView;
    NV_Adapter nv_adapter;

    ArrayList<NhanVien> arrNV;

    String[] dv_list;
    String donvi;

    EditText ed_maNV ;
    EditText ed_tenNV ;
    RadioGroup rb_gioitinh ;

    RadioButton Rbtn_nam ;
    RadioButton Rbtn_nu ;

    NhanVien nhanVien;

    int viTri ;

    ImageView imgV ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();

        imgV = findViewById(R.id.imgV_img);
        Button btn_chonAnh = findViewById(R.id.btn_ChonHinh);
        Button btn_Them = findViewById(R.id.btn_Them);
        Button btn_Sua = findViewById(R.id.btn_Sua);
        Button btn_TruyVan = findViewById(R.id.btn_TrV);
        Button btn_Thoat = findViewById(R.id.btn_Thoat);
        ed_maNV = findViewById(R.id.editT_Ma);
        ed_tenNV = findViewById(R.id.EditT_Ten);
        rb_gioitinh = findViewById(R.id.rb_gioitinh);
        Rbtn_nam = findViewById(R.id.Rbtn_nam);
        Rbtn_nu = findViewById(R.id.rbnu);

        Spinner  spinner_DonVi = findViewById(R.id.spin_DV);
        dv_list = getResources().getStringArray(R.array.donvi_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,dv_list);
        spinner_DonVi.setAdapter(adapter);
        spinner_DonVi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                donvi = dv_list[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                donvi=dv_list[0];
            }
        });

        btn_chonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent,RESULT_LOAD_IMG);
            }
        });

        btn_Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((imgV.getDrawable())==null){
                    Toast toast  =  Toast.makeText(MainActivity.this,"Chon Hinh",Toast.LENGTH_SHORT);
                    toast.show();
                }else if (ed_maNV.getText().toString().equals("")){
                    Toast toast  =  Toast.makeText(MainActivity.this,"Nhap ma",Toast.LENGTH_SHORT);
                    toast.show();
                }else if (ed_tenNV.getText().toString().equals("")){
                    Toast toast  =  Toast.makeText(MainActivity.this,"Nhap ten",Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    Bitmap img = ((BitmapDrawable) imgV.getDrawable()).getBitmap();
                    String ma = ed_maNV.getText().toString();
                    String ten = ed_tenNV.getText().toString();
                    String gioitinh = ((RadioButton)findViewById(rb_gioitinh.getCheckedRadioButtonId())).getText().toString();
                    arrNV.add(new NhanVien(img,ma,ten,gioitinh,donvi));
                    nv_adapter.notifyDataSetChanged();
                    //clear();
                }
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                viTri=i;
                nhanVien = arrNV.get(i);

                imgV.setImageBitmap(nhanVien.getImg());
                ed_maNV.setText(nhanVien.getMaNV());
                ed_tenNV.setText(nhanVien.getTenNV());
                if (nhanVien.getGioiTinh().equals("Nam"))
                    Rbtn_nam.setChecked(true);
                else
                    Rbtn_nu.setChecked(true);
                for (int j = 0;j<dv_list.length;j++){
                    if (dv_list[j].equals(nhanVien.getDonVi())){
                        spinner_DonVi.setSelection(j);}}
            }
        });



        btn_Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap img = ((BitmapDrawable) imgV.getDrawable()).getBitmap();
                String ma = ed_maNV.getText().toString();
                String ten = ed_tenNV.getText().toString();
                String gioitinh = ((RadioButton)findViewById(rb_gioitinh.getCheckedRadioButtonId())).getText().toString();
                String donVi = spinner_DonVi.getSelectedItem().toString();

                nhanVien.setImg(img);
                nhanVien.setMaNV(ma);
                nhanVien.setTenNV(ten);
                nhanVien.setGioiTinh(gioitinh);
                nhanVien.setDonVi(donVi);

                nv_adapter.notifyDataSetChanged();
                listView.setAdapter(nv_adapter);

//                if(!arrNV.isEmpty()){
//                    arrNV.remove(nhanVien);
//                    nv_adapter.notifyDataSetChanged();
//                    Toast.makeText(MainActivity.this,"Đã xóa ",Toast.LENGTH_LONG).show();
//                }else{
//                    Toast.makeText(MainActivity.this,"Không có nhân Vien ",Toast.LENGTH_LONG).show();
//                }
            }
        });

        btn_TruyVan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ma = ed_maNV.getText().toString();
                if (ma.equals("")){
                    Toast toast  =  Toast.makeText(MainActivity.this,"Nhập mã",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    for (int j = 0;j<arrNV.size();j++){
                        if (ma.equals(arrNV.get(j).getMaNV())){
                            NhanVien nhanVien = arrNV.get(j);

                            imgV.setImageBitmap(nhanVien.getImg());
                            ed_maNV.setText(nhanVien.getMaNV());
                            ed_tenNV.setText(nhanVien.getTenNV());
                            if (nhanVien.getGioiTinh().equals("Nam"))
                                Rbtn_nam.setChecked(true);
                            else
                                Rbtn_nu.setChecked(true);
                            for (int p= 0;p<dv_list.length;p++){
                                if (dv_list[p].equals(nhanVien.getDonVi())){
                                    spinner_DonVi.setSelection(p);}}
                                Toast.makeText(MainActivity.this,"Truy Vấn Thnahf Công",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        btn_Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void Anhxa() {
        listView = (ListView) findViewById(R.id.listV_NV);
        arrNV = new ArrayList<NhanVien>();
        nv_adapter = new NV_Adapter(this, arrNV);
        listView.setAdapter(nv_adapter);
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imgV.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }
    public void clear(){
        imgV.setImageResource(R.mipmap.ic_launcher);
        ed_maNV.setText("");
        ed_tenNV.setText("");
    }
}