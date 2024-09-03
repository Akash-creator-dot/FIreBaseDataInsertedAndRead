package com.techgiants.firebase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {
private Button btnsend,btnreceive;
EditText edt;
TextView txt;
private FirebaseDatabase mdatabase;
private DatabaseReference mrefrence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnsend=findViewById(R.id.btn_send);
        btnreceive=findViewById(R.id.btn_receive);
        edt=findViewById(R.id.edt);
        txt=findViewById(R.id.txt);
        mdatabase =FirebaseDatabase.getInstance();
        mrefrence=mdatabase.getReference();
    btnsend.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            senddata();
        }
    });
    btnreceive.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            receivedata();
        }
    });
    }

    private void receivedata() {
        mrefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data=snapshot.getValue(String.class);
                txt.setText(data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void senddata(){
        String data=edt.getText().toString();
        mrefrence.setValue(data);
        Toast.makeText(MainActivity.this, "Data inserted successivefully", Toast.LENGTH_SHORT).show();
    }

}