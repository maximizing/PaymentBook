package adapter;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.maximize.pocketmoney.R;

import java.util.ArrayList;

import model.Payment;
import model.Type;

/**
 * Created by maximize on 12/18/2016 AD.
 */

public class ListViewPaymentAdapter extends BaseAdapter {
    private ArrayList<Payment> paymentList;
    private Context mContext;
    private int mLayoutResId;

    public ListViewPaymentAdapter(Context context, int mLayoutResId, ArrayList<Payment> paymentList) {
        this.mContext = context;
        this.mLayoutResId = mLayoutResId;
        this.paymentList = paymentList;
    }

    @Override
    public int getCount() {
        return paymentList.size();
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
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = View.inflate(mContext, mLayoutResId, null);
        }

        TextView item_payment = (TextView) view.findViewById(R.id.item_describtion);
        TextView item_price = (TextView) view.findViewById(R.id.item_price);
        TextView item_type = (TextView) view.findViewById(R.id.item_type);
        TextView item_datetime = (TextView) view.findViewById(R.id.item_date_time);

        item_payment.setMovementMethod(ScrollingMovementMethod.getInstance());
        item_price.setMovementMethod(ScrollingMovementMethod.getInstance());
        item_type.setMovementMethod(ScrollingMovementMethod.getInstance());
        item_datetime.setMovementMethod(ScrollingMovementMethod.getInstance());


        String payment = paymentList.get(position).getDescribtion();
        String price = paymentList.get(position).getPrice();
        String type = paymentList.get(position).getType();
        String datetime = paymentList.get(position).getDateTime();

        item_payment.setText(payment);
        item_price.setText(price+" บาท");
        item_type.setText(type);
        item_datetime.setText(datetime);

        return view;


    }
}
