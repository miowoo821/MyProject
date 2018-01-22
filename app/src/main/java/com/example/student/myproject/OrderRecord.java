package com.example.student.myproject;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

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

        final TextView tv1=neworder.findViewById(R.id.New_order_date);
        EditText ed1=neworder.findViewById(R.id.New_order_amount);
        EditText ed2=neworder.findViewById(R.id.New_order_memo);
        TextView tv2=neworder.findViewById(R.id.New_order_N_point);

        tv1.setOnClickListener(new View.OnClickListener() {
            int mYear, mMonth, mDay;
            @Override
            public void onClick(View view) {
                Calendar c=Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(OrderRecord.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                        String str = (String.valueOf(i+"/"+i1+"/"+i2));
                        tv1.setText(str);
                    }
                },mYear, mMonth, mDay).show();
            }
        });
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
        builder.setView(neworder);
        builder.show();
        return super.onOptionsItemSelected(item);
    }

}
