package com.example.retrofit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.retrofit.Model.Employee;
import com.example.retrofit.R;
import com.squareup.picasso.Picasso;

import static android.content.pm.PackageInstaller.EXTRA_SESSION_ID;

public class MainActivity2 extends AppCompatActivity {
    private static final String CHANNEL_ID = "";
    private TextView firstname;
    private TextView grade;
    private TextView rate;
    private TextView ville;
    private TextView email;
    private TextView phone;
    private ImageView img;
    private Button vacation;
    private Button doc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        firstname= findViewById(R.id.lastname);
        grade=findViewById(R.id.grade);
        doc=findViewById(R.id.document);
        rate=findViewById(R.id.rate);
        img=findViewById(R.id.profile_image);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        ville=findViewById(R.id.ville);
        vacation=findViewById(R.id.vacation);
        Intent intent=getIntent();
        String hello=intent.getStringExtra(EXTRA_SESSION_ID);
        String message=intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String vil=intent.getStringExtra("ville");
        String em=intent.getStringExtra("email");
        final byte[] imag=intent.getByteArrayExtra("image");
        Bitmap decodedByte = BitmapFactory.decodeByteArray(imag, 0, imag.length);
        float rat=getIntent().getFloatExtra("rate",0);
        String ech=getIntent().getStringExtra("tel");
        final Employee emp = (Employee )getIntent().getSerializableExtra("sampleObject");
        firstname.setText("Bonjour "+hello);
        ville.setText(vil);
        grade.setText(message);
        email.setText(em);
        phone.setText(ech);
        rate.setText(String.valueOf(rat));
        img.setImageBitmap(decodedByte);
        vacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity2.this, Vacation.class);
                intent.putExtra("sampleObject", emp);
                startActivity(intent);
            }
        });
        doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity2.this, DocumentActivity.class);
                intent.putExtra("sampleObject", emp);
                startActivity(intent);
            }
        });
    }

}