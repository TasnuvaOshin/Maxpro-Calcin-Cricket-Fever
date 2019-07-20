package cricketworldcup.worldcup.AdminLogin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cricketworldcup.worldcup.AdminPanel.AdminHomeActivity;
import cricketworldcup.worldcup.DSM.DsmActivity;
import cricketworldcup.worldcup.R;
import cricketworldcup.worldcup.RSM.RsmActivity;
import cricketworldcup.worldcup.SM.SmActivity;

public class AdminLoginActivity extends AppCompatActivity {
    String type;
    EditText id,pass;
    String adminId,adminPass;
    DatabaseReference dsm,rsm,sm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        type = getIntent().getStringExtra("type");
        id = findViewById(R.id.id);
        pass =  findViewById(R.id.pass);
        dsm = FirebaseDatabase.getInstance().getReference().child("dsmdata");
        rsm = FirebaseDatabase.getInstance().getReference().child("rsmdata");
        sm = FirebaseDatabase.getInstance().getReference().child("smdata");
    }

    public void SubmitAdmin(View view) {
        adminId = id.getText().toString();
        adminPass = pass.getText().toString();

        if(type.equals("admin")){
            if(adminId.equals("admin") && adminPass.equals("maxpro@2019")){
                startActivity(new Intent(AdminLoginActivity.this,AdminHomeActivity.class));
                AdminLoginActivity.this.overridePendingTransition(0,0);

            }

        }
        if(type.equals("dsm")){

            CheckDsm(adminId,adminPass);
        }

        if(type.equals("rsm")){

            CheckRsm(adminId,adminPass);
        }
        if(type.equals("sm")){
            CheckSm(adminId,adminPass);

        }

    }

    private void CheckSm(String adminId, final String adminPass) {
        sm.orderByChild("user_id").equalTo(adminId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        String pass = String.valueOf(ds.child("password").getValue());
                        String code = String.valueOf(ds.child("ffc").getValue());

                        if(pass.equals(adminPass)){

                            Intent i = new Intent(AdminLoginActivity.this,SmActivity.class);
                            i.putExtra("code",code);
                            Toast.makeText(AdminLoginActivity.this, "Successfully Logged In" , Toast.LENGTH_SHORT).show();
                            startActivity(i);
                            AdminLoginActivity.this.overridePendingTransition(0,0);

                        }else{

                            Toast.makeText(AdminLoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                        }

                    }
                }else {

                    Toast.makeText(AdminLoginActivity.this, "Wrong Dsm Profile", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void CheckRsm(String adminId, final String adminPass) {

        rsm.orderByChild("user_id").equalTo(adminId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        String pass = String.valueOf(ds.child("password").getValue());
                        String code = String.valueOf(ds.child("ffc").getValue());

                        if(pass.equals(adminPass)){

                            Intent i = new Intent(AdminLoginActivity.this,RsmActivity.class);
                            i.putExtra("code",code);
                            Toast.makeText(AdminLoginActivity.this, "Successfully Logged In" , Toast.LENGTH_SHORT).show();
                            startActivity(i);
                            AdminLoginActivity.this.overridePendingTransition(0,0);

                        }else{

                            Toast.makeText(AdminLoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                        }

                    }
                }else {

                    Toast.makeText(AdminLoginActivity.this, "Wrong Dsm Profile", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void CheckDsm(String adminId, final String adminPass) {


dsm.orderByChild("user_id").equalTo(adminId).addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(dataSnapshot.exists()){

            for(DataSnapshot ds : dataSnapshot.getChildren()) {
                String pass = String.valueOf(ds.child("password").getValue());
                String code = String.valueOf(ds.child("ffc").getValue());

                if(pass.equals(adminPass)){

                    Intent i = new Intent(AdminLoginActivity.this,DsmActivity.class);
                    i.putExtra("code",code);
                    Toast.makeText(AdminLoginActivity.this, "Successfully Logged In" , Toast.LENGTH_SHORT).show();
                    startActivity(i);
                    AdminLoginActivity.this.overridePendingTransition(0,0);

                }else{

                    Toast.makeText(AdminLoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                }

            }
            }else {

            Toast.makeText(AdminLoginActivity.this, "Wrong Dsm Profile", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});


    }
}
