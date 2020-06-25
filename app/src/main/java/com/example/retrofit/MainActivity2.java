package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        String imag=intent.getStringExtra("image");
        String statut=intent.getStringExtra("statut");
        float rat=getIntent().getFloatExtra("rate",0);
        String ech=getIntent().getStringExtra("tel");
        final Employee emp = (Employee )getIntent().getSerializableExtra("sampleObject");
        final Employee emp1 = (Employee )getIntent().getSerializableExtra("Object");
        firstname.setText("Bonjour M. "+hello);
        ville.setText(vil);
        grade.setText(message);
        email.setText(em);
        phone.setText(ech);
        rate.setText(String.valueOf(rat));
        Picasso.get().load(imag).noFade().into(img);
        vacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity2.this,Vacation.class);
                intent.putExtra("sampleObject", emp);
                startActivity(intent);
            }
        });
        doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity2.this,DocumentActivity.class);
                intent.putExtra("sampleObject", emp);
                startActivity(intent);
            }
        });
    }

}