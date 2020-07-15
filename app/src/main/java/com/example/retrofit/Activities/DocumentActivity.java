package com.example.retrofit.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.retrofit.JsonPlaceHolderApi;
import com.example.retrofit.Model.Document;
import com.example.retrofit.Model.Employee;
import com.example.retrofit.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DocumentActivity extends AppCompatActivity {
    private Button send;
    private Spinner spinner;
    private EditText motif;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);
        send=findViewById(R.id.button);
        spinner=findViewById(R.id.planets_spinner);
        motif=findViewById(R.id.type);
        send.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                final Employee current = (Employee) getIntent().getSerializableExtra("sampleObject");
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("http://192.168.1.4:8080/springboot-crud-rest/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
                Document document = new Document(
                        current,motif.getText().toString(),
                        spinner.getSelectedItem().toString(),"demandé");
                Call<Document> call =jsonPlaceHolderApi.createDoc(document);
                call.enqueue(new Callback<Document>() {
                    @Override
                    public void onResponse(Call<Document> call, Response<Document> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(DocumentActivity.this,"failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Document> call, Throwable t) {
                        Toast.makeText(DocumentActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}