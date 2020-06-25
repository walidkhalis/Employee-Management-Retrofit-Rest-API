package com.example.retrofit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    private static final String TAG = Vacation.class.getName();

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
                        .baseUrl("http://192.168.1.6:8080/springboot-crud-rest/api/v1/")
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