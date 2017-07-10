package sg.edu.rp.c346.mybmicalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView tvInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tvInfo = (TextView)findViewById(R.id.textInfo);
        Intent intentReceived = getIntent();
        String comment = intentReceived.getStringExtra("value");
        tvInfo.setText(comment);
    }
}
