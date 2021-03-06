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
import android.util.Log;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class ActivityPage extends AppCompatActivity {
    SQLiteDatabase dbW;//在此頁面新增一個名為db的SQLiteDatabase物件，千萬要記得要用的時候還要給它等於dbHelper.getReadableDatabase();
    SQLiteDatabase dbR;
    ListView lv2;
    String  actd;
//    MyAdapter adapter;
//    ArrayList<Map<String,String>> act_list=new ArrayList<>();

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

        cursor.move(1);//這邊是移動到幾筆
        Log.d("GHFDSGHSG",cursor.getString(0));//這邊的引數是輸入欄位，所以上面步驟不可少
        SimpleCursorAdapter listAdapter=new SimpleCursorAdapter(ActivityPage.this,
                R.layout.test,//切記！！不能擺constraintlayout
                cursor,
                new String[]{"_id","act_name","limted","act_S_D","act_E_D","F_S_D","F_E_D","ratio","memo"},
                //new int[]{R.id.act_list_id,R.id.act_list_name,R.id.act_list_ratio,R.id.act_list_actsd,R.id.act_list_acted},
                new int[]{R.id.textView14,R.id.textView15,R.id.textView16,R.id.textView17,R.id.textView18,R.id.textView19,R.id.textView20,R.id.textView21,R.id.textView22},
                1);

     //   adapter=new MyAdapter(ActivityPage.this,cursor);
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
            int mYear, mMonth, mDay;
            Calendar c;
            @Override
            public void onClick(View view) {
                c=Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                //DatePickerDialog連續執行兩次，就有兩層DatePickerDialog，最上面那層就是比較後面執行的，所以先填的是後面的日期
                //修正方法，在第一個DatePickerDialog關閉才跳出第二個
                new DatePickerDialog(ActivityPage.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                        actd = (String.valueOf(i+"/"+i1+"/"+i2));
                        Log.d("GGGGGGGG1111111",actd);
                        tv1.setText(actd);

                        Log.d("GGGGGGGG1111111",tv1.getText().toString());

                        new DatePickerDialog(ActivityPage.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                                actd = (String.valueOf(i+"/"+i1+"/"+i2));
                                Log.d("GGGGGGGG2",actd);
                                tv2.setText(actd);
                                Log.d("GGGGGGGG222222222",tv2.getText().toString());
                            }
                        },mYear, mMonth, mDay).show();
                    }
                },mYear, mMonth, mDay).show();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            int mYear, mMonth, mDay;
            @Override
            public void onClick(View view) {

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
                        new DatePickerDialog(ActivityPage.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                                String str = (String.valueOf(i+"/"+i1+"/"+i2));

                                tv2.setText(str);
                            }
                        },mYear, mMonth, mDay).show();
                    }
                },mYear, mMonth, mDay).show();
////                int mYear, mMonth, mDay;
////                Calendar c=Calendar.getInstance();
//                mYear = c.get(Calendar.YEAR);
//                mMonth = c.get(Calendar.MONTH);
//                mDay = c.get(Calendar.DAY_OF_MONTH);
//                new DatePickerDialog(ActivityPage.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                        i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
//                        String str = (String.valueOf(i+"/"+i1+"/"+i2));
//
//                        tv2.setText(str);
//                    }
//                },mYear, mMonth, mDay).show();
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            int mYear, mMonth, mDay;
            @Override
            public void onClick(View view) {

                Calendar c=Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(ActivityPage.this,android.R.style.Theme_Holo_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                        String str = (String.valueOf(i+"/"+i1+"/"+i2));

                        tv3.setText(str);
                        new DatePickerDialog(ActivityPage.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                                String str = (String.valueOf(i+"/"+i1+"/"+i2));

                                tv4.setText(str);
                            }
                        },i, i1-1, i2+13).show();
                    }
                },mYear, mMonth, mDay).show();
            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            int mYear, mMonth, mDay;
            @Override
            public void onClick(View view) {

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

                        new DatePickerDialog(ActivityPage.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                                String str = (String.valueOf(i+"/"+i1+"/"+i2));

                                tv4.setText(str);
                            }
                        },i, i1-1, i2+13).show();
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


                cv.put("act_name",act_name.getText().toString());
                cv.put("limted",limted.getText().toString());
                cv.put("act_S_D",act_S_D.getText().toString());
                cv.put("act_E_D",act_E_D.getText().toString());
                cv.put("F_S_D",F_S_D.getText().toString());
                cv.put("F_E_D",F_E_D.getText().toString());
                cv.put("ratio",ratio.getText().toString());
                cv.put("memo",memo.getText().toString());
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
