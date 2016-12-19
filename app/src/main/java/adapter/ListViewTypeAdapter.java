package adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.maximize.pocketmoney.R;

import java.util.ArrayList;

import db.DatabaseHelper;
import model.Type;

/**
 * Created by maximize on 12/17/2016 AD.
 */

public class ListViewTypeAdapter extends BaseAdapter {
    private ArrayList<Type> typeList;
    private Context mContext;
    private int mLayoutResId;


    public ListViewTypeAdapter(Context context, int mLayoutResId , ArrayList<Type> typeList){
        this.mContext = context;
        this.mLayoutResId = mLayoutResId;
        this.typeList = typeList;
    }


    @Override
    public int getCount() {
        return typeList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = View.inflate(mContext, mLayoutResId, null);
        }

        TextView item_text  = (TextView) view.findViewById(R.id.item_type);
        CheckBox item_check = (CheckBox) view.findViewById(R.id.item_check);

        item_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                typeList.get(position).setTypeCheck(isChecked);
            }
        });

        Type type = typeList.get(position);
        Type checkbox = typeList.get(position);
        item_text.setText(type.getType());
        item_check.setChecked(checkbox.getTypeCheck());

        return view;
    }
}
