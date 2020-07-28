package com.example.cinema;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

public class ReportActivity extends Activity {

    final int PHOTO_REQUEST = 1;
    final int VIDEO_REQUEST = 2;
    ImageButton pictureBtn;
    ImageButton videoBtn;
    ImageButton playBtn;
    ImageView imageView;
    VideoView videoView;
    EditText email_add;
    Button continueBtn;
    Uri videoFileUri;
    String name;
    String phone;
    String email;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_layout);

        continueBtn = findViewById(R.id.done_report_btn);
        pictureBtn = findViewById(R.id.pic_button);
        videoBtn = findViewById(R.id.video_button);
        imageView = findViewById(R.id.imageView1);
        videoView = findViewById(R.id.imageView2);
        playBtn = findViewById(R.id.play_button);
        email_add = findViewById(R.id.email_address_id);
        playBtn.setEnabled(false);

        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        email = getIntent().getStringExtra("email");

        continueBtn.setOnClickListener(new ReportActivity.ContinueButtonListener());
        pictureBtn.setOnClickListener(new ReportActivity.PictureButtonListener());
        videoBtn.setOnClickListener(new ReportActivity.VideoButtonListener());
        playBtn.setOnClickListener(new ReportActivity.PlayButtonListener());
    }


    private class ContinueButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            if(email_add.getText().toString().isEmpty()){
                Toast.makeText(ReportActivity.this, getResources().getString(R.string.email_error), Toast.LENGTH_LONG).show();
            }

            else
            {
                Intent intent = new Intent(ReportActivity.this, MainActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("phone", phone);
                intent.putExtra("email", email);

                Toast.makeText(ReportActivity.this, getResources().getString(R.string.thanks_str), Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        }
    }

    private class PictureButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent photoIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(photoIntent, PHOTO_REQUEST);
        }
    }


    private class VideoButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            Intent videoIntent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(videoIntent, VIDEO_REQUEST);
        }
    }

    private class PlayButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            videoView.setVideoURI(videoFileUri);
            videoView.start();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PHOTO_REQUEST && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            Toast.makeText(ReportActivity.this, getResources().getString(R.string.picturesaved_str), Toast.LENGTH_SHORT).show();
        }

        if (requestCode == VIDEO_REQUEST && resultCode == Activity.RESULT_OK)
        {
            videoFileUri = data.getData();
            videoView.setVideoURI(videoFileUri);
            playBtn.setEnabled(true);
            Toast.makeText(ReportActivity.this, getResources().getString(R.string.videosaved_str), Toast.LENGTH_SHORT).show();
        }

    }


}
