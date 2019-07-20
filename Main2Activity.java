package cricketworldcup.worldcup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("data");

        databaseReference.orderByChild("G").equalTo("36").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds2 : dataSnapshot.getChildren()) {

                    String region = String.valueOf(ds2.child("H").getValue());
Log.d("V",String.valueOf(ds2.child("A").getValue())+"  ----   "+String.valueOf(ds2.child("E").getValue())+" -----  "+String.valueOf(ds2.child("D").getValue())+" ----  "+String.valueOf(ds2.child("C").getValue())+" ---- "+String.valueOf(ds2.child("B").getValue()));

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
   }





