package com.example.student.myproject;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ActivityPage extends AppCompatActivity {
    SQLiteDatabase dbW;//在此頁面新增一個名為db的SQLiteDatabase物件，千萬要記得要用的時候還要給它等於dbHelper.getReadableDatabase();
    SQLiteDatabase dbR;
    ListView lv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        MyDBHelper dbHelper=new MyDBHelper(this);
        dbR=dbHelper.getReadableDatabase();//把名為dbR的SQLiteDatabase物件賦予dbHelper.getReadableDatabase()功能

        lv2=(ListView)findViewById(R.id.listView2);

        Cursor cursor;
        cursor=dbR.query("act_list",new String[]{"_id","act_name","limted","act_S_D","act_E_D","F_S_D","F_E_D","ratio","memo"},
                null,null,null,null,null);



        CursorAdapter listAdapter=new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,cursor,
                new String[]{"_id","act_name","limted","act_S_D","act_E_D","F_S_D","F_E_D","ratio","memo"},
                new int[]{android.R.id.text1},
                0);

        lv2.setAdapter(listAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.newconsumption,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)  {
        AlertDialog.Builder builder=new AlertDialog.Builder(ActivityPage.this);
        builder.setTitle("登錄活動");

        LayoutInflater inflater=LayoutInflater.from(ActivityPage.this);
        final View newactivity=inflater.inflate(R.layout.newactivity,null);

        final TextView tv1=newactivity.findViewById(R.id.act_start_date);
        final TextView tv2=newactivity.findViewById(R.id.act_end_date);
        final TextView tv3=newactivity.findViewById(R.id.feedback_start_date);
        final TextView tv4=newactivity.findViewById(R.id.feedback_end_date);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mYear, mMonth, mDay;
                Calendar c=Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(ActivityPage.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                        String str = (String.valueOf(i+"/"+i1+"/"+i2));

                        tv1.setText(str);
                    }
                },mYear, mMonth, mDay).show();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mYear, mMonth, mDay;
                Calendar c=Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(ActivityPage.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                        String str = (String.valueOf(i+"/"+i1+"/"+i2));

                        tv2.setText(str);
                    }
                },mYear, mMonth, mDay).show();
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mYear, mMonth, mDay;
                Calendar c=Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(ActivityPage.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                        String str = (String.valueOf(i+"/"+i1+"/"+i2));

                        tv3.setText(str);
                    }
                },mYear, mMonth, mDay).show();
            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mYear, mMonth, mDay;
                Calendar c=Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(ActivityPage.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                        String str = (String.valueOf(i+"/"+i1+"/"+i2));

                        tv4.setText(str);
                    }
                },mYear, mMonth, mDay).show();
            }
        });
//-------------------很重要--------------------------------------------------------------------
        final MyDBHelper dbHelper=new MyDBHelper(this);//新增一個名為db的SQLiteDatabase物件，不能在按下後才宣告
        dbW=dbHelper.getWritableDatabase();//取得具有寫入功能的資料庫實體，//把名為dbR的SQLiteDatabase物件賦予dbHelper.getWritableDatabase()功能
        dbR=dbHelper.getReadableDatabase();//取得具有讀取功能的資料庫實體，//把名為dbR的SQLiteDatabase物件賦予dbHelper.getReadableDatabase()功能
//------------------很重要--------------------------------------------------------------------------------
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {//按下確定後執行新增資料的行為
                EditText act_name=newactivity.findViewById(R.id.act_name);
                EditText limted=newactivity.findViewById(R.id.limted);
                TextView act_S_D=newactivity.findViewById(R.id.act_start_date);
                TextView act_E_D=newactivity.findViewById(R.id.act_end_date);
                TextView F_S_D=newactivity.findViewById(R.id.feedback_start_date);
                TextView F_E_D=newactivity.findViewById(R.id.feedback_end_date);
                EditText ratio=newactivity.findViewById(R.id.ratio);
                EditText memo=newactivity.findViewById(R.id.memo);
                ContentValues cv=new ContentValues();
                cv.put("act_name",act_name.toString());
                cv.put("limted",limted.toString());
                cv.put("act_S_D",act_S_D.toString());
                cv.put("act_E_D",act_E_D.toString());
                cv.put("F_S_D",F_S_D.toString());
                cv.put("F_E_D",F_E_D.toString());
                cv.put("ratio",ratio.toString());
                cv.put("memo",memo.toString());
                dbW.insert("act_list",null,cv);

                String _id="序號,";
                String name="活動名稱,";
                String lim="回饋上限,";
                String S_D="活動開始日期,";
                String[] colum={"_id","act_name","limted","act_S_D","act_E_D","F_S_D","F_E_D","ratio","memo"};
                Cursor c;
                c=dbR.query("act_list",colum,null,null,null,null,null);



                if (c.getCount()>0){
                    c.moveToFirst();
                    for(int X=0 ; X<c.getCount();X++){
//                        _id +=(X+1);
//                      name+=c.getString(0) ;
//                        lim+= c.getString(1)+"\n";
                        String A=c.getString(0);
                        String  B=c.getString(1);
                        String C=c.getString(2);
                        String  D=c.getString(3);
                        c.moveToNext();
                    }
                }
                Toast.makeText(ActivityPage.this,"新增成功",Toast.LENGTH_SHORT).show();
            }

        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setView(newactivity);


        builder.show();
        return super.onOptionsItemSelected(item);
    }


}
