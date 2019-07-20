package cricketworldcup.worldcup.AdminPanel.QuizParticipantDetails;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cricketworldcup.worldcup.R;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class QuizParticipant extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private DatabaseReference user;
    private DatabaseReference maindata;
    private String dsm, name, phoneno;
    private String dsmname, rsmname, smname;
    private List<Pdata> list;
     private EditText editText;
     private String matchno;
    File directory, sd, file;
    WritableWorkbook workbook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_participant);

        user = FirebaseDatabase.getInstance().getReference().child("Users");
        maindata = FirebaseDatabase.getInstance().getReference().child("data");
        editText = findViewById(R.id.et_matchno);
        list = new ArrayList<>();

    }

    private void FindOutUser(String key) {
Log.d("key",key);
        user.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    dsm = String.valueOf(dataSnapshot.child("userdsmcode").getValue());
                    name = String.valueOf(dataSnapshot.child("username").getValue());
                    phoneno = String.valueOf(dataSnapshot.child("userphoneno").getValue());
                     Log.d("WeDSM",dsm);
                    PrepareData(dsm, name, phoneno);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void PrepareData(final String dsm, final String name, String phoneno) {

        maindata.orderByChild("G").equalTo(dsm).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    dsmname = String.valueOf(ds.child("A").getValue());
                    smname = String.valueOf(ds.child("B").getValue());
                    rsmname = String.valueOf(ds.child("D").getValue());

                }
            list.add(new Pdata(matchno,dsm,name,dsmname,smname,rsmname));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //CheckForWriting();

    }




    public void DownloadQuizDetails(View view) {
         matchno = editText.getText().toString();
         if(TextUtils.isEmpty(matchno)){

             Toast.makeText(this, "Please Enter Match No", Toast.LENGTH_SHORT).show();
         }else{
             StartFunctionalWork();

             Toast.makeText(this, "Processing", Toast.LENGTH_SHORT).show();
         }

    }

    private void StartFunctionalWork() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("User_Quiz_Answer").child(matchno);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String key = ds.getKey();

                    FindOutUser(key);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });




    }

    public void createExcelSheet() {
        if(isStoragePermissionGranted()) {
            String folder_main = "MaxPro";

            String csvFile = "QuizDetails.xls";
            File f  = new File(Environment.getExternalStorageDirectory(),folder_main);
            // directory = new File(sd.getAbsolutePath());
            if (!f.exists()) {
                f.mkdirs();
            }
            file = new File(f, csvFile);
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            try {
                workbook = Workbook.createWorkbook(file, wbSettings);
                createFirstSheet();

                //closing cursor
                workbook.write();
                workbook.close();

                Toast.makeText(this, "File Saved !", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{

            Toast.makeText(this, "Permission Denied !", Toast.LENGTH_SHORT).show();
        }
    }

    public void createFirstSheet() {
        try {

            //Excel sheet name. 0 (number)represents first sheet
            WritableSheet sheet = workbook.createSheet("sheet1", 0);
            // column and row title
            sheet.addCell(new Label(0, 0, "Match No"));
            sheet.addCell(new Label(1, 0, "Dsm Code"));
            sheet.addCell(new Label(2, 0, "Doctor Name"));
            sheet.addCell(new Label(3, 0, "DsmName"));
            sheet.addCell(new Label(4, 0, "SmName"));
            sheet.addCell(new Label(5, 0, "RsmName"));


            for (int i = 0; i< list.size(); i++) {
                sheet.addCell(new Label(0, i + 1, list.get(i).getMatchno()));
                sheet.addCell(new Label(1, i + 1, list.get(i).getDsmcode()));
                sheet.addCell(new Label(2, i + 1, list.get(i).getDoctorname()));
                sheet.addCell(new Label(3, i + 1, list.get(i).getDsmname()));
                sheet.addCell(new Label(4, i + 1, list.get(i).getSmname()));
                sheet.addCell(new Label(5, i + 1, list.get(i).getRsmname()));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }





    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v("TAG","Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }

    public void DownloadFile(View view) {
        createExcelSheet();
    }
}

