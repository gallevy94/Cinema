package com.example.cinema;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import java.util.Calendar;


public class BookActivity extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button btnDatePicker, btnTimePicker, next_btn;
    EditText txtDate, fullName;
    Spinner hourSpn;
    int mYear, mMonth, mDay;
    String hour_str;
    ImageView movie_pic;
    String movieName;
    RadioGroup radioGroup;
    String date;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_layout);

        final LinearLayout linearLayout = findViewById(R.id.calender_layout);

        btnDatePicker = findViewById(R.id.date_btn);
        btnTimePicker = findViewById(R.id.time_btn);
        next_btn = findViewById(R.id.next_btn);
        txtDate = findViewById(R.id.in_date);
        fullName = findViewById(R.id.full_name);
        movie_pic = findViewById(R.id.movie_pic);

        radioGroup = findViewById(R.id.radio_group);

        hourSpn = findViewById(R.id.in_time);
        ArrayAdapter<CharSequence> hour_adap = ArrayAdapter.createFromResource(this, R.array.time_array, android.R.layout.simple_dropdown_item_1line);
        hour_adap.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        hourSpn.setAdapter(hour_adap);
        hourSpn.setOnItemSelectedListener(this);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        next_btn.setOnClickListener(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.star_wars:
                        movie_pic.setImageResource(R.drawable.star_wars);
                        movieName = getString(R.string.star_war);
                        break;

                    case R.id.joker:
                        movie_pic.setImageResource(R.drawable.joker);
                        movieName = getString(R.string.joker);
                        break;

                    case R.id.jumanji:
                        movie_pic.setImageResource(R.drawable.jumanji);
                        movieName = getString(R.string.jumanji);
                        break;
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();

            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            mDay=dayOfMonth;
                            mMonth=(monthOfYear + 1);
                            mYear=year;
                        }
                    }, mYear, mMonth, mDay);
            Calendar mcurrentDate = Calendar.getInstance();
            mcurrentDate.add(Calendar.DATE, 1);
            datePickerDialog.getDatePicker().setMinDate(mcurrentDate.getTimeInMillis());
            datePickerDialog.show();
        }

        if (v == btnTimePicker) {
            hourSpn.performClick();
        }

        if(v==next_btn){
            Intent intent=new Intent(BookActivity.this,CompleteBookingActivity.class);
            //hour = Integer.parseInt(hourSpn.getSelectedItem().toString());
            if(fullName.getText().toString().isEmpty()|| mDay==0||mMonth==0||mYear==0 || radioGroup.getCheckedRadioButtonId() == -1){
                Toast.makeText(this, getResources().getString(R.string.error_str), Toast.LENGTH_SHORT).show();
            }
            else {

                date = txtDate.getText().toString();
                hour_str = hourSpn.getSelectedItem().toString();
                intent.putExtra("day",mDay);
                intent.putExtra("month",mMonth-1);
                intent.putExtra("year",mYear);
                intent.putExtra("hour", hour_str);
                intent.putExtra("name",fullName.getText().toString());
                intent.putExtra("movie_name",movieName);
                intent.putExtra("date", date);

                startActivity(intent);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}