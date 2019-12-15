package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
private ListView lvThuChi;
private ThuChiDAO thuChiDAO;
private List<model> dsThuChi;
private EditText txtTen, txtSoTien, txtLoai;
private DBManager dbManager;
private Button btnAdd, btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thuChiDAO = new ThuChiDAO(this);
        dbManager = new DBManager(this);
        dsThuChi = thuChiDAO.getAllThuChi();
        txtTen = findViewById(R.id.txtTen);
        txtSoTien = findViewById(R.id.txtSoTien);
        txtLoai = findViewById(R.id.txtType);
        lvThuChi = findViewById(R.id.lvThuChi);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
//        hiển thị dữ liệu lên listview
        final ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, dsThuChi);
        lvThuChi.setAdapter(adapter);
//        thêm dữ liệu vào bảng
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Ten = txtTen.getText().toString();
                String soTien = txtSoTien.getText().toString();
                String Loai = txtLoai.getText().toString();
                model thuchi =  new model(Ten, soTien, Loai);
                boolean isSuccess = thuChiDAO.insertThuChi(thuchi);
                if (isSuccess){
                    Toast.makeText(MainActivity.this, "Insert successfully", Toast.LENGTH_SHORT).show();
                    dsThuChi.clear();
                    dsThuChi.addAll(thuChiDAO.getAllThuChi());
                    adapter.notifyDataSetChanged();

                }
                else {
                    Toast.makeText(MainActivity.this, "Insert failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
//        cập nhật dữ liệu
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           model tc = new model();
           tc.setTenKhoan(txtTen.getText()+"");
           tc.setSoTien(txtSoTien.getText()+"");
           tc.setLoai(txtLoai.getText()+"");
                thuChiDAO.updateThuChi(tc);
                Toast.makeText(MainActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();
                dsThuChi.clear();
                dsThuChi.addAll(thuChiDAO.getAllThuChi());
                adapter.notifyDataSetChanged();
                txtLoai.setText("");
                txtSoTien.setText("");
                txtTen.setText("");
                btnAdd.setEnabled(true);
                btnUpdate.setEnabled(false);

            }
        });
//hiển thị dữ liệu lên form
        lvThuChi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnAdd.setEnabled(false);
                btnUpdate.setEnabled(true);
                model thuchi = dsThuChi.get(position);
                txtTen.setText(thuchi.getTenKhoan());
                txtLoai.setText(thuchi.getLoai());
                txtSoTien.setText(thuchi.getSoTien());

            }
        });
//        Xóa dữ liệu trong bảng
        lvThuChi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                model thuchi = dsThuChi.get(position);
                thuChiDAO.deleteThuChi(thuchi.getTenKhoan());
                dsThuChi.clear();
                dsThuChi.addAll(thuChiDAO.getAllThuChi());
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }



//        dsThuChi.clear();
//        dsThuChi.addAll(thuChiDAO.getAllThuChi());
//        adapter.notifyDataSetChanged();
    }


