import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.student.myproject.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by mio on 2018/1/15.
 */

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<Map<String,String>> act_list=new ArrayList<>();

    public MyAdapter(Context context,ArrayList<Map<String,String>> act_list){
        this.context=context;
        this.act_list=act_list;
    }

    @Override
    //設定要回傳資料的筆數(總共會有幾格)
    public int getCount() {
        return act_list.size();
    }

    @Override
    // //取得某一列的內容
    public Object getItem(int i) {
        return null;
    }

    @Override
    //取得某一列的 id
    public long getItemId(int i) {
        return 0;
    }

    @Override
    //修改某一列 View 的內容
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view==null){
            LayoutInflater inflater=LayoutInflater.from(context);
            view=inflater.inflate(R.layout.act_list,null);
            viewHolder=new ViewHolder();
            viewHolder.id=view.findViewById(R.id.act_list_id);
            viewHolder.name=view.findViewById(R.id.act_list_name);
            viewHolder.ratio=view.findViewById(R.id.act_list_ratio);
            viewHolder.actsd=view.findViewById(R.id.act_list_actsd);
            viewHolder.acted=view.findViewById(R.id.act_list_acted);

            view.setTag(viewHolder);

        }
        else {
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.id.setText(act_list.get(i).toString());
        viewHolder.name.setText(act_list.get(i).toString());
        viewHolder.ratio.setText(act_list.get(i).toString());
        viewHolder.actsd.setText(act_list.get(i).toString());
        viewHolder.acted.setText(act_list.get(i).toString());

        return view;
    }


    static class ViewHolder{
        TextView id;
        TextView name;
        TextView ratio;
        TextView actsd;
        TextView acted;
    }
}
