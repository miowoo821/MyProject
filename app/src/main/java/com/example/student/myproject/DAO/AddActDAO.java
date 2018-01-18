package com.example.student.myproject.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.student.myproject.ActivityPage;
import com.example.student.myproject.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Student on 2018/1/17.
 */
//僅在RAM裡面的新增查詢修改刪除
public class AddActDAO {
    public ArrayList<act> act_list;
    Context context;//存在SQLite還需要取得可儲存檔案路徑嗎????
    public AddActDAO(Context context){
        this.context=context;
        act_list = new ArrayList<>();
    }
//    public AddActDAO(){
//        this.act_list=new ArrayList<>();
//    }
    public void saveFile(){
        File f=new File(context.getFilesDir(),"act_list");
        FileWriter fw=null;
        try {
            fw=new FileWriter(f);
            Gson gson = new Gson();
            String act_data=gson.toJson(act_list);
            fw.write(act_data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  void loadFile(){
        File f=new File(context.getFilesDir(),"act_list");
        FileReader fr=null;
        try {
            fr=new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            Gson gson = new Gson();
            act_list=gson.fromJson(str,new TypeToken<ArrayList<act>>(){}.getType());
            br.close();//寫完關閉
            fr.close();//寫完關閉
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean act_add(act Act){//因為act.java的名稱打小寫了，為了區分，變數名稱就打大寫了(跟一般慣例相反)

        act_list.add(Act);
        saveFile();
     return true;
    }
    public ArrayList<act> getList(){
        loadFile();
        return act_list;
    }

    public act get_Act(String id){
        for(act A:act_list){
            loadFile();
            if (A._ID==id){
                return A;
            }
        }
        return  null;
    }
    public boolean update(act Act){
        for(act G:act_list){
            loadFile();
            if (G._ID==Act._ID){
                G.Act_Name=Act.Act_Name;
                G.FeedBack_Point_Limited=Act.FeedBack_Point_Limited;
                G.FeedBack_Ratio=Act.FeedBack_Ratio;
                G.Act_S_Date=Act.Act_S_Date;
                G.Act_E_Date=Act.Act_E_Date;
                G.FeedBack_S_Date=Act.FeedBack_S_Date;
                G.FeedBack_E_Date=Act.FeedBack_E_Date;
                G.Memo=Act.Memo;
                saveFile();
                return true;
            }
        }
        return  false;
    }
    public boolean delete(String ID){
        for(int i=0;i<act_list.size();i++){
            if (act_list.get(i)._ID==ID){//搜尋資料庫裡面與剛輸入的ID相符合的資料

                act_list.remove(i);
                saveFile();
                return true;
            }
        }
        return  false;
    }

}
