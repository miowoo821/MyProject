package com.example.student.myproject.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.student.myproject.ActivityPage;
import com.example.student.myproject.R;

/**
 * Created by Student on 2018/1/17.
 */

public class AddActDAO {
    LayoutInflater inflater=LayoutInflater.from(ActivityPage.this);
    final View newactivity=inflater.inflate(R.layout.newactivity,null);


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
}
