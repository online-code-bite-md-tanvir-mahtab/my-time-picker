package com.tanvircodder.mytimepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText mTimePicker,mTimePickerSecond;
    private Button mCalButton;
    private TextView mTotalHour;
    private int mHour,mMinute;
    private int mHour2,mMinute2;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTimePicker = (EditText) findViewById(R.id.item_picker);
        mTimePickerSecond  = (EditText) findViewById(R.id.item_picker_second);
        mTimePicker.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Toast.makeText(MainActivity.this,"you just tuched te text",Toast.LENGTH_LONG)
                        .show();
                //                now i am going to get the current time instance
                final Calendar calendar= Calendar.getInstance();
//                for the hour
                mHour = calendar.get(Calendar.HOUR_OF_DAY);
                mMinute = calendar.get(Calendar.MINUTE);

//                now i am going to show case the diagram
                TimePickerDialog timePickerDialog  = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        mTimePicker.setText(i + ":" + i1);
                    }
                },mHour,mMinute,false);
                timePickerDialog.show();
                return false;
            }
        });

        mTimePickerSecond.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Calendar calendar = Calendar.getInstance();

                mHour2 = calendar.get(Calendar.HOUR_OF_DAY);
                mMinute2 = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        mTimePickerSecond.setText(i + ":" + i1);
                    }
                },mHour2,mMinute2,false);
                timePickerDialog.show();
                return false;
            }
        });

    }
    public void timeCalculate(View view){
        mTimePicker = (EditText)findViewById(R.id.item_picker);
        mTimePickerSecond = (EditText)findViewById(R.id.item_picker_second);
        mTotalHour = (TextView) findViewById(R.id.total_hour);
        String[] firstHour =mTimePicker.getText().toString().split(":");
        String[] secondHour = mTimePickerSecond.getText().toString().split(":");
        double f_hour = convertToFirstHour(firstHour);
        double s_hour = convertToSecondHour(secondHour);
        String totalHour = Integer.toString((int) ((f_hour-s_hour)/3660));
        mTotalHour.setText(totalHour);

    }

    private double convertToFirstHour(String[] firstHour) {
        double  hour =0;
        double  minute =0 ;
        if (firstHour.length > 0){
            hour = Integer.parseInt(firstHour[0]);
            minute = Integer.parseInt(firstHour[1]);
            return (hour*3600)+(minute*60);
        }else{

        }
        return (hour*3600)+(minute*60);

    }
    private double convertToSecondHour(String[] firstHour) {
        double hour = 0;
        double minute = 0;
        if (firstHour.length > 0){
            hour = Integer.parseInt(firstHour[0]);
            minute = Integer.parseInt(firstHour[1]);
            System.out.println(hour + ":" + minute);
        }else{

        }
        return (hour*3600)+(minute*60);
    }
}