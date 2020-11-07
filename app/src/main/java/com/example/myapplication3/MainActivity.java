package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et1, et2, et3;
    Spinner Gender;
    CheckBox chk;
    private TextView tv_result;
    private Button btn, btn2;



    public static final String NAME = "NAME";
    public static final String WEIGHT = "WEIGHT";
    public static final String HEIGHT = "HEIGHT";
    public static final String FLAG = "FLAG";
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et1 = (EditText) findViewById(R.id.et_weight);
        et2 = (EditText) findViewById(R.id.et_height);
        et3 = (EditText) findViewById(R.id.et_name);

        chk = (CheckBox) findViewById(R.id.check);
        tv_result = (TextView) findViewById(R.id.tv_result);
        btn2 = (Button) findViewById(R.id.button2);


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();

            }

        });

        btn = (Button) findViewById(R.id.button);
     //  CheckPrefs();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bmi();
            }
        });
        setupSharedPrefs();
        setupViews();
        CheckPrefs();
        CheckPrefs();

    }
public void openActivity2()
{
    Intent intent=new Intent(this,MainActivity2.class);
    startActivity(intent);

}



      private void bmi() {
        float a, b, c;
        a = Float.parseFloat(et2.getText().toString()) / 100;
        b = Float.parseFloat(et1.getText().toString());
        c = b / (a * a);
        tv_result.setText(" " + et3.getText() + " " + "Your BMI is:" + " " + c);


        if (c <= 10.0) {
            Toast.makeText(getApplicationContext(), "You are underweight", Toast.LENGTH_SHORT).show();
        }
        if ((c >= 10.0) && (c <= 25)) {
            Toast.makeText(getApplicationContext(), "You are Normal", Toast.LENGTH_SHORT).show();
        }
        if (c >= 25.0) {
            Toast.makeText(getApplicationContext(),"You are Overweight", Toast.LENGTH_SHORT).show();
        }
    }






    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }



    public void setupViews() {
        et1 = (EditText) findViewById(R.id.et_weight);
        et2 = (EditText) findViewById(R.id.et_height);
        et3 = (EditText) findViewById(R.id.et_name);
        chk = (CheckBox) findViewById(R.id.check);


    }
    private void CheckPrefs() {
        boolean flag = prefs.getBoolean(FLAG, false);
        if (flag) {
            String name = prefs.getString(NAME, "");
            String weight =prefs.getString(WEIGHT, "");
            String height = prefs.getString(HEIGHT, "");
            et3.setText(name);
            et1.setText(weight);
            et2.setText(height);
            chk.setChecked(true);

        }
    }

    public void btnOnClick(View view) {
        String name = et3.getText().toString();
        String weight = et1.getText().toString();
        String height = et2.getText().toString();
        String gender = prefs.getString(String.valueOf(Gender), "");
        if (chk.isChecked()) {
            editor.putString(NAME, name);
            editor.putString(HEIGHT, height);
            editor.putString(WEIGHT, weight);
            editor.putString(String.valueOf(Gender), gender);
            editor.putBoolean(FLAG, true);
            editor.commit();


        }
    }
}