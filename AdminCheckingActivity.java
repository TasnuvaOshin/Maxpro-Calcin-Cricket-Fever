package cricketworldcup.worldcup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cricketworldcup.worldcup.AdminLogin.AdminLoginActivity;

public class AdminCheckingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_checking);
    }

    public void GoToAdmin(View view) {

        Intent i = new Intent(AdminCheckingActivity.this,AdminLoginActivity.class);
        i.putExtra("type","admin");
        startActivity(i);
        AdminCheckingActivity.this.overridePendingTransition(0,0);
    }

    public void GoToDsm(View view) {
        Intent i = new Intent(AdminCheckingActivity.this,AdminLoginActivity.class);
        i.putExtra("type","dsm");
        startActivity(i);
        AdminCheckingActivity.this.overridePendingTransition(0,0);


    }

    public void GoToRsm(View view) {
        Intent i = new Intent(AdminCheckingActivity.this,AdminLoginActivity.class);
        i.putExtra("type","rsm");
        startActivity(i);
        AdminCheckingActivity.this.overridePendingTransition(0,0);

    }

    public void GoToSm(View view) {
        Intent i = new Intent(AdminCheckingActivity.this,AdminLoginActivity.class);
        i.putExtra("type","sm");
        startActivity(i);
        AdminCheckingActivity.this.overridePendingTransition(0,0);

    }
}
