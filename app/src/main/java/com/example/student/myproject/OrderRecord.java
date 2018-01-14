package com.example.student.myproject;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class OrderRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_record);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.newconsumption,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //點選menu(+)後要觸發下面程式碼
        AlertDialog.Builder builder=new AlertDialog.Builder(OrderRecord.this);//新增一個會在MainActivity裡面彈出的Dialog物件
        builder.setTitle("新增紀錄");

        LayoutInflater inflater=LayoutInflater.from(OrderRecord.this);//layout解壓縮用的，可以把res裡面的layout挖出來
        View neworder=inflater.inflate(R.layout.neworder,null);//第一個參數是要放入的Layout,第二個放null(為什麼)

        builder.setView(neworder);


        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
        return super.onOptionsItemSelected(item);
    }

}
