package com.example.maximize.pocketmoney;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

 private ImageView mImageView_write, mImageView_type, mImageView_activity, mImageView_dashboard;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mImageView_write = (ImageView) findViewById(R.id.IMV_write);
        mImageView_type = (ImageView) findViewById(R.id.IMV_type);
        mImageView_activity = (ImageView) findViewById(R.id.IMV_activity);
        mImageView_dashboard = (ImageView) findViewById(R.id.IMV_dashboard);

        mImageView_write.setOnClickListener(this);
        mImageView_type.setOnClickListener(this);
        mImageView_activity.setOnClickListener(this);
        mImageView_dashboard.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.IMV_write:
                Intent i = new Intent(this,PaymentActivity.class);
                startActivity(i);
                break;
            case R.id.IMV_type:
                Intent j = new Intent(this,CreateTypeActivity.class);
                startActivity(j);
                break;
            case R.id.IMV_activity:
                Intent k = new Intent(this,ListActivity.class);
                startActivity(k);
                break;
            case R.id.IMV_dashboard:
                Intent l = new Intent(this,ChartActivity.class);
                startActivity(l);
                break;
            default:
                break;

        }

    }
}
