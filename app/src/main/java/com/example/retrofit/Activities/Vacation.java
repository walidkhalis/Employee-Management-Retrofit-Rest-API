package com.example.retrofit.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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

import com.example.retrofit.JsonPlaceHolderApi;
import com.example.retrofit.Model.Employee;
import com.example.retrofit.Model.Permission;
import com.example.retrofit.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Vacation extends AppCompatActivity {
    private Button send;
    private Spinner spinner;
    private EditText dated;
    private EditText datef;
    private EditText dater;
    private EditText motif;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation);
        send=findViewById(R.id.button);
        spinner=findViewById(R.id.planets_spinner);
        dated=findViewById(R.id.debu);
        datef=findViewById(R.id.fin);
        dater=findViewById(R.id.reprise);
        motif=findViewById(R.id.type);
        datef.setInputType(InputType.TYPE_NULL);
        dater.setInputType(InputType.TYPE_NULL);
        dated.setInputType(InputType.TYPE_NULL);
        dated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTime(dated);

            }
        });
        datef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTime(datef);

            }
        });
        dater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTime(dater);

            }
        });
        send.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Employee current = (Employee) getIntent().getSerializableExtra("sampleObject");
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("http://192.168.1.4:8080/springboot-crud-rest/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd HH:mm");
                Date datede=null,datefin=null,daterep=null;
                try {
                    datede = fmt.parse(dated.getText().toString());
                    datefin = fmt.parse(datef.getText().toString());
                    daterep = fmt.parse(dater.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                String datedString = fmt.format(datede);
                String datefString = fmt.format(datefin);
                String daterString = fmt.format(daterep);
                jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
                Permission permission = new Permission(
                        current,datedString,datefString,
                        daterString,motif.getText().toString(),
                        spinner.getSelectedItem().toString(),"demandé");
                Call<Permission> call =jsonPlaceHolderApi.createPermission(permission);
                call.enqueue(new Callback<Permission>() {
                    @Override
                    public void onResponse(Call<Permission> call, Response<Permission> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(Vacation.this,"failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Permission> call, Throwable t) {
                        Toast.makeText(Vacation.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void showDateTime(final EditText var){
        final Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                TimePickerDialog.OnTimeSetListener timeSetListener= new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy-MM-dd HH:mm");
                        Date u=calendar.getTime();
                        var.setText(simpleDateFormat.format(u));
                    }
                };
                new TimePickerDialog(Vacation.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };
        new DatePickerDialog(Vacation.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}