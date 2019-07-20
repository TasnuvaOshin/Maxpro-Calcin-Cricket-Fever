package cricketworldcup.worldcup;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import cricketworldcup.worldcup.API.OurClient;
import cricketworldcup.worldcup.API.OurResponse;
import cricketworldcup.worldcup.API.RetrofitSetup;
import cricketworldcup.worldcup.API.dataInfo;
import cricketworldcup.worldcup.API.runInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitTest extends AppCompatActivity {
    TextView live_target, live_inning_teamone, live_team_one_name, live_team_one_run, live_team_one_wicket, live_team_one_over, live_inning_teamtwo, live_team_two_name, live_team_two_run, live_team_two_wicket, live_team_two_over;
private LinearLayout nolive,showlive;
String tone,ttwo;

DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);

          nolive = findViewById(R.id.No_live);
          showlive = findViewById(R.id.show_live);
        live_target = findViewById(R.id.live_target);
        live_inning_teamone = findViewById(R.id.live_inning_teamone);
        live_team_one_name = findViewById(R.id.live_team_one_name);
        live_team_one_run = findViewById(R.id.live_team_one_run);
        live_team_one_wicket = findViewById(R.id.live_team_one_wicket);
        live_team_one_over = findViewById(R.id.live_team_one_over);

        live_inning_teamtwo = findViewById(R.id.live_inning_teamtwo);
        live_team_two_name = findViewById(R.id.live_team_two_name);
        live_team_two_run = findViewById(R.id.live_team_two_run);
        live_team_two_wicket = findViewById(R.id.live_team_two_wicket);
        live_team_two_over = findViewById(R.id.live_team_two_over);


        OurClient client = RetrofitSetup.GetOurRetrofit(OurClient.class);
        Call<OurResponse> list = client.getOurResponse();


        list.enqueue(new Callback<OurResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<OurResponse> call, Response<OurResponse> response) {
                Log.d("Our Response", "We are getting Response");
                Log.d("Our Response", String.valueOf(response.body().toString()));
                List<dataInfo> dataInfoList = response.body().getData();
                int i;
                int j;
                if( dataInfoList.isEmpty()){
                    showlive.setVisibility(View.GONE);
                    nolive.setVisibility(View.VISIBLE);
                }else {
                    nolive.setVisibility(View.GONE);
                    showlive.setVisibility(View.VISIBLE);
                    live_target.setText(dataInfoList.get(0).getNote());

                    List<runInfo> runInfoList = dataInfoList.get(0).getRuns();
                    tone = runInfoList.get(0).getTeam_id();
                    String onename = GetTeamName(tone);
                    live_team_one_name.setText(onename);
                    live_team_one_run.setText(runInfoList.get(0).getScore());
                    live_inning_teamone.setText("Innings: "+runInfoList.get(0).getInning());
                    live_team_one_over.setText("Overs: "+runInfoList.get(0).getOvers());
                    live_team_one_wicket.setText(runInfoList.get(0).getWickets());

                    ttwo = runInfoList.get(1).getTeam_id();
                    String twoname = GetTeamName(ttwo);
                    live_team_two_name.setText(twoname);
                    live_team_two_run.setText(runInfoList.get(1).getScore());
                    live_inning_teamtwo.setText("Innings: "+runInfoList.get(1).getInning());
                    live_team_two_over.setText("Overs: "+runInfoList.get(1).getOvers());
                    live_team_two_wicket.setText(runInfoList.get(1).getWickets());


                }


                /*
                for (i = 0; i < dataInfoList.size(); i++) {

                    Log.d("Our Response", dataInfoList.get(i).getLive());
                    Log.d("Our Response", dataInfoList.get(i).getStatus());
                    Log.d("Our Response", dataInfoList.get(i).getNote());

                    dataInfoList.get(i).getStatus();
                    dataInfoList.get(i).getNote();
                    dataInfoList.get(i).getLocalteam().getCode();
                    dataInfoList.get(i).getLocalteam().getId();
                    dataInfoList.get(i).getVisitorteam().getCode();
                    dataInfoList.get(i).getVisitorteam().getId();

                    Log.d("Our Response", dataInfoList.get(i).getLocalteam().getCode());
                    Log.d("Our Response", dataInfoList.get(i).getLocalteam().getId());
                    Log.d("Our Response", dataInfoList.get(i).getVisitorteam().getId());
                    Log.d("Our Response", dataInfoList.get(i).getVisitorteam().getCode());

                    List<runInfo> runInfoList = dataInfoList.get(i).getRuns();

                    for (j = 0; j < runInfoList.size(); j++) {
                        Log.d("Our Response", runInfoList.get(j).getScore());
                        Log.d("Our Response", runInfoList.get(j).getInning());
                        Log.d("Our Response", runInfoList.get(j).getOvers());
                        Log.d("Our Response", runInfoList.get(j).getWickets());


                    }


                }
                */


            }

            @Override
            public void onFailure(Call<OurResponse> call, Throwable t) {
                Log.d("Our Response", "We are not getting Response");
            }
        });


    }

    private String GetTeamName(String tone) {
        String name = null;
        if(tone.equals("39")){
            name = "SL";
        }
        if(tone.equals("40")){
            name = "SA";
        }
        if(tone.equals("37")){
            name = "BAN";
        }
        if(tone.equals("46")){
            name = "AFG";
        }
        if(tone.equals("10")){
            name = "IND";
        }
        if(tone.equals("1")){
            name = "PAK";
        }
        if(tone.equals("36")){
            name = "AUS";
        }
        if(tone.equals("43")){
            name = "WIN";
        }
        if(tone.equals("42")){
            name = "NZ";
        }
        if(tone.equals("38")){
            name = "ENG";
        }
        return name;
    }
}
