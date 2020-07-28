package com.example.cinema;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button reportBtn=findViewById(R.id.report_id);
        Button orderBtn=findViewById(R.id.booking_id);
        Button busyBtn = findViewById(R.id.busy_id);
        ImageButton callBtn=findViewById(R.id.call_id);
        ImageButton emailBtn=findViewById(R.id.email_id);
        ImageButton addBtn = findViewById(R.id.add_id);


        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ReportActivity.class);
                startActivity(intent);
            }
        });

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,BookActivity.class);
                startActivity(intent);
            }
        });

        busyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,BusyActivity.class);
                startActivity(intent);
            }
        });

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhone();
            }
        });

        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mailUs();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
                contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                contactIntent.putExtra(ContactsContract.Intents.Insert.NAME, getResources().getString(R.string.cinema_city));
                contactIntent.putExtra(ContactsContract.Intents.Insert.PHONE, "039437606");

                startActivityForResult(contactIntent, 1);
            }
        });
    }

    private void mailUs()
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:cinema_city@gmail.com?subject=");
        intent.setData(data);
        startActivity(intent);
    }

    private void dialPhone()
    {
        Intent intent=new Intent(Intent.ACTION_DIAL);
        String number="03-943-7606";
        intent.setData(Uri.parse("tel:"+number));
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }
}
