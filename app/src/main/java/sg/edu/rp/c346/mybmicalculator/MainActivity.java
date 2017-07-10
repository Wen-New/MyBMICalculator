package sg.edu.rp.c346.mybmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etWeight;
    EditText etHeight;
    Button btnCalculate;
    Button btnResetData;
    Button btnDetails;
    TextView tvDate;
    TextView tvBMI;
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etWeight = (EditText) findViewById(R.id.editWeight);
        etHeight = (EditText) findViewById(R.id.editHeight);
        btnCalculate = (Button) findViewById(R.id.buttonCalculate);
        btnResetData = (Button) findViewById(R.id.buttonReset);
        btnDetails = (Button) findViewById(R.id.buttonDetail);
        tvDate = (TextView) findViewById(R.id.textDate);
        tvBMI = (TextView) findViewById(R.id.textBMI);
        tvInfo = (TextView) findViewById(R.id.textInfo);

        btnResetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etHeight.setText("");
                etWeight.setText("");
                tvDate.setText("Last Calculated Date:");
                tvBMI.setText("Last Calculated BMI:0.0");
                tvInfo.setText("");
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float bmi = 0;
                float weight = Float.parseFloat(etWeight.getText().toString());
                float height = Float.parseFloat(etHeight.getText().toString());
                bmi = weight / (height * height);
                tvBMI.setText("Last Calculated BMI:" + bmi);

                Calendar now = Calendar.getInstance(); //Create a Calendar object with current date/time
                String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                        (now.get(Calendar.MONTH) + 1) + "/" +
                        now.get(Calendar.YEAR) + " " +
                        now.get(Calendar.HOUR_OF_DAY) + ":" +
                        now.get(Calendar.MINUTE);
                tvDate.setText("Last Calculated Date:" + datetime);
                etHeight.setText("");
                etWeight.setText("");


            }
        });
        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float bmi = 0;
                float weight = Float.parseFloat(etWeight.getText().toString());
                float height = Float.parseFloat(etHeight.getText().toString());
                bmi = weight / (height * height);
                Intent intent = new Intent(getBaseContext(), SecondActivity.class);
                String msg = "";
                if (bmi < 18.5 && bmi > 0) {
                    msg = "You are Underweight!";
                    intent.putExtra("value", msg);
                } else if (bmi <= 24.9 && bmi >= 18.5) {
                    msg = "You are Normal!";
                    intent.putExtra("value", msg);
                } else if (bmi <= 29.9 && bmi >= 25) {
                    msg = "You are Overweight!";
                    intent.putExtra("value", msg);
                } else if (bmi >= 30) {
                    msg = "You are Obese!";
                    intent.putExtra("value", msg);
                }
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        String date = tvDate.getText().toString();
        String bmi = tvBMI.getText().toString();
        String info = tvInfo.getText().toString();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putString("date", date);
        prefEdit.putString("bmi", bmi);
        prefEdit.putString("info", info);
        prefEdit.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        String date = tvDate.getText().toString();
        String bmi = tvBMI.getText().toString();
        String info = tvInfo.getText().toString();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        TextView tvDate = (TextView) findViewById(R.id.textDate);
        tvDate.setText(prefs.getString("date", date));
        TextView tvBMI = (TextView) findViewById(R.id.textBMI);
        tvBMI.setText(prefs.getString("bmi", bmi));
        TextView tvInfo = (TextView) findViewById(R.id.textInfo);
        tvInfo.setText(prefs.getString("info", info));

    }
}
