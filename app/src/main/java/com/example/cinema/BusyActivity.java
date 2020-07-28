package com.example.cinema;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BusyActivity extends Activity implements TimePickerDialog.OnTimeSetListener {

    int hour, min;
    TextView timeTv;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busy_layout);

        Button pickTimeBtn = findViewById(R.id.time_btn);
        Button setBtn = findViewById(R.id.set_alarm_id);
        Button done_btn = findViewById(R.id.done_btn_id);
        timeTv = findViewById(R.id.time_tv);
        final String remainder = getResources().getString(R.string.Booking);


        pickTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                min = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(BusyActivity.this,
                        BusyActivity.this, hour, min, true);
                timePickerDialog.show();

            }
        });

        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (timeTv.getText().toString().isEmpty())
                    Toast.makeText(BusyActivity.this, getResources().getString(R.string.time_error_str), Toast.LENGTH_LONG).show();

                else {
                    createAlarm(remainder, hour, min);
                    Toast.makeText(BusyActivity.this, getResources().getString(R.string.set_alarm_toast), Toast.LENGTH_LONG).show();
                }
            }


        });

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hour = hourOfDay;
        min = minute;

        if((min/10)!= 0) {
            timeTv.setText(hour + ":" + min);
        }
        else {
            timeTv.setText(hour + ":" + "0" + min);
        }
    }

    private void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, message);
        intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
        intent.putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}


