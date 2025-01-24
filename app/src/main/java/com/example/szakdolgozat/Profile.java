package com.example.szakdolgozat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Profile extends AppCompatActivity {

    TextView TeljesNev,Email,Suly,Magassag,Kor,Nem;

    FirebaseAuth mAuth;
    String userID;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);


        Email = findViewById(R.id.Email_input);
        TeljesNev = findViewById(R.id.teljes_nev);
        Suly = findViewById(R.id.suly);
        Magassag = findViewById(R.id.magassag);
        Kor = findViewById(R.id.kor);
        Nem = findViewById(R.id.nem);


        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        userID = mAuth.getCurrentUser().getUid();


        DocumentReference dbUsers = db.collection("users").document(userID);
        dbUsers.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                TeljesNev.setText(value.getString("nev"));
                Email.setText(value.getString("email"));
                Suly.setText(value.getString("suly")+"kg");
                Magassag.setText(value.getString("magassag")+"cm");
                Kor.setText(value.getString("kor")+" éves");
                Nem.setText(value.getString("nem"));

            }
        });


    }


    public void Szerkeszt(View view){
        startActivity(new Intent(Profile.this, ProfileSzerkesztes.class));
        finish();
    }
}