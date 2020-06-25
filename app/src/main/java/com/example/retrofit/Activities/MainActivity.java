package com.example.retrofit.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofit.JsonPlaceHolderApi;
import com.example.retrofit.Model.Echelon;
import com.example.retrofit.R;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static android.content.pm.PackageInstaller.EXTRA_SESSION_ID;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;
    public final static String EXTRA_MESSAGE = "com.example.retrofit.MESSAGE";
    private static final String CHANNEL_ID = "my_channel_01";
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.textenter);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.1.4:8080/springboot-crud-rest/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi= retrofit.create(JsonPlaceHolderApi.class);
        final Call<List<Echelon>> call1= jsonPlaceHolderApi.getEch();
        call1.enqueue(new Callback<List<Echelon>>() {
            @Override
            public void onResponse(Call<List<Echelon>> call, Response<List<Echelon>> response) {
                List<Echelon> posts= response.body();
                for(Echelon post:posts){
                    int x=Integer.parseInt(editText.getText().toString());
                            int hello = Integer.parseInt(post.getEmployee().getCin());
                            if(hello==x){
                                String hel = post.getEmployee().getLastName();
                                String echelon=post.getId_echelon().getLib_echelon();
                                float note=post.getNote_moyenne();
                                String ville=post.getEmployee().getService().getName();
                                String echelle=post.getId_echelon().getId_echelle().getLib_echelle();
                                String image=post.getEmployee().getImage();
                                String conten = String.valueOf(post.getEmployee().getGrade());
                                Intent intent =new Intent(MainActivity.this, MainActivity2.class);
                                intent.putExtra(EXTRA_SESSION_ID, hel);
                                intent.putExtra(EXTRA_MESSAGE, conten);
                                intent.putExtra("ville", ville);
                                intent.putExtra("email", echelon);
                                intent.putExtra("tel", echelle);
                                intent.putExtra("image", image);
                                intent.putExtra("rate", note);
                                intent.putExtra("sampleObject", post.getEmployee());
                                startActivity(intent);
                            }
                        }
            }
            @Override
            public void onFailure(Call<List<Echelon>> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


            }});
        }

}