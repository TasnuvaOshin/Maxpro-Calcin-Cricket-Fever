package cricketworldcup.worldcup.AdminPanel.QuizParticipantScore;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
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

public class QuizParticipantScoreActivity extends AppCompatActivity {
    private static final String TAG = "MYATG";
    private DatabaseReference databaseReference, databaseReferencePoints;
    private String dsm, name, phoneno, userId;
    private int i;
    private String score;
    private DatabaseReference maindata;
    String dsmname, rsmname, smname;
    File directory, sd, file;
    WritableWorkbook workbook;
    private EditText editText;
    String matchno;
    List<PscoreData> list;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_participant_score);
        list = new ArrayList<>();
        editText = findViewById(R.id.et_matchno);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Processing....");

    }

    public void StartDownloading(View view) {
        if (!TextUtils.isEmpty(editText.getText())) {
            matchno = editText.getText().toString();
            new jsonTask().execute();
            Toast.makeText(this, "Processing .....", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(this, "Please Enter the Number", Toast.LENGTH_SHORT).show();
        }


    }

    private void CreateData() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("MainQuizScore").child(matchno);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    dsmname = String.valueOf(ds.child("Dsmname").getValue());
                    rsmname = String.valueOf(ds.child("rsmname").getValue());
                    smname = String.valueOf(ds.child("smname").getValue());
                    dsm = String.valueOf(ds.child("userdsmcode").getValue());
                    name = String.valueOf(ds.child("username").getValue());
                    phoneno = String.valueOf(ds.child("userphone").getValue());
                    score = String.valueOf(ds.child("score").getValue());
                    list.add(new PscoreData(score, dsm, name, phoneno, dsmname, rsmname, smname));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void createExcelSheet() {
        if (isStoragePermissionGranted()) {
            String folder_main = "MaxPro";

            String csvFile = "ScoreDetails.xls";
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

                Toast.makeText(this, "File Downloaded !", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            Toast.makeText(this, "Permission Denied !", Toast.LENGTH_SHORT).show();
        }
    }

    public void createFirstSheet() {
        try {

            //Excel sheet name. 0 (number)represents first sheet
            WritableSheet sheet = workbook.createSheet("sheet1", 0);
            // column and row title
            sheet.addCell(new Label(0, 0, "Score"));
            sheet.addCell(new Label(1, 0, "DsmCode"));
            sheet.addCell(new Label(2, 0, "Doctor Name"));
            sheet.addCell(new Label(3, 0, "Doctor Phone"));
            sheet.addCell(new Label(4, 0, "Dsm Name"));
            sheet.addCell(new Label(5, 0, "Rsm Name"));
            sheet.addCell(new Label(6, 0, "Sm Name"));


            for (int i = 0; i < list.size(); i++) {
                sheet.addCell(new Label(0, i + 1, list.get(i).getScore()));
                sheet.addCell(new Label(1, i + 1, list.get(i).getDsm()));
                sheet.addCell(new Label(2, i + 1, list.get(i).getName()));
                sheet.addCell(new Label(3, i + 1, list.get(i).getPhone()));
                sheet.addCell(new Label(4, i + 1, list.get(i).getDsmname()));
                sheet.addCell(new Label(5, i + 1, list.get(i).getRsmname()));
                sheet.addCell(new Label(6, i + 1, list.get(i).getSmname()));


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
        }
    }


    public void StartDown(View view) {
        createExcelSheet();


    }

    public class jsonTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(QuizParticipantScoreActivity.this, "After 2 minutes You can Download the file", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            CreateData();
            return null;
        }
    }
}
