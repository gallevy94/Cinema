package com.example.cinema;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Calendar;


public class CompleteBookingActivity extends Activity {

    int day, month, year, hour;
    String hour_str;
    String movie_name;
    String name;
    String date;

    TextView nameTV;
    TextView movieTV;
    TextView dateTv;
    TextView timeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_booking_layout);

        day = getIntent().getExtras().getInt("day");
        month = getIntent().getExtras().getInt("month");
        year = getIntent().getExtras().getInt("year");
        hour_str = getIntent().getExtras().getString("hour");
        movie_name = getIntent().getExtras().getString("movie_name");
        name = getIntent().getExtras().getString("name");
        date = getIntent().getStringExtra("date");

        hour = Integer.parseInt(hour_str.substring(0, 2));

        Button eventBtn = findViewById(R.id.calendar_event_id);
        Button done_btn=findViewById(R.id.done_btn_id);
        TextView almost_done = findViewById(R.id.almost_done_id);
        nameTV = findViewById(R.id.details_name);
        movieTV = findViewById(R.id.details_movie);
        dateTv = findViewById(R.id.details_date);
        timeTv = findViewById(R.id.details_time);

        almost_done.setText(getResources().getString(R.string.almost_done) + " " + name +"..");
        nameTV.setText(getResources().getString(R.string.name_title) + " " + name);
        movieTV.setText(getResources().getString(R.string.movie_name) + " " + movie_name);
        dateTv.setText(getResources().getString(R.string.screening_date) + " " + date);
        timeTv.setText(getResources().getString(R.string.screening_time) + " " + hour_str);


        eventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEvent();
            }
        });

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CompleteBookingActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addEvent() {
        Calendar beginCal = Calendar.getInstance();
        beginCal.set(year, month, day, hour, 0);
        long startTime = beginCal.getTimeInMillis();
        Calendar endCal = Calendar.getInstance();
        endCal.set(year, month, day, hour+1,0);
        long endTime = endCal.getTimeInMillis();

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra(CalendarContract.Events.TITLE, getResources().getString(R.string.event_title)+" "+ movie_name +"!");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION,  getResources().getString(R.string.cinema_city));
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginCal.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endCal.getTimeInMillis());
        intent.putExtra(CalendarContract.Events.STATUS, 1);
        intent.putExtra(CalendarContract.Events.VISIBLE, 0);
        intent.putExtra(CalendarContract.Events.HAS_ALARM, 1);
        startActivity(intent);
    }

}
