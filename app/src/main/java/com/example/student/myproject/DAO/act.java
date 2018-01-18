package com.example.student.myproject.DAO;

import android.view.View;
import android.widget.TextView;

import com.example.student.myproject.R;

import java.util.Date;

/**
 * Created by Student on 2018/1/17.
 */

public class act {
    String _ID;//ID不能拿來做加減的
    public String Act_Name;
    public int FeedBack_Point_Limited;
    public int FeedBack_Ratio;
    public  Date Act_S_Date;
    public Date Act_E_Date;
    public   Date FeedBack_S_Date;
    public  Date FeedBack_E_Date;
    public  String Memo;

    public act(String Act_Name,int FeedBack_Point_Limited,int FeedBack_Ratio,Date Act_S_Date,Date Act_E_Date,Date FeedBack_S_Date,  Date FeedBack_E_Date,String Memo){

        this.Act_Name=Act_Name;
        this.FeedBack_Point_Limited=FeedBack_Point_Limited;
        this.FeedBack_Ratio=FeedBack_Ratio;
        this.Act_S_Date=Act_S_Date;
        this.Act_E_Date=Act_E_Date;
        this.FeedBack_S_Date=FeedBack_S_Date;
        this.FeedBack_E_Date=FeedBack_E_Date;
        this.Memo=Memo;
    }

}
