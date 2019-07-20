package cricketworldcup.worldcup.AdminPanel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cricketworldcup.worldcup.AdminPanel.QuizParticipantDetails.QuizParticipant;
import cricketworldcup.worldcup.AdminPanel.QuizParticipantScore.QuizParticipantScoreActivity;
import cricketworldcup.worldcup.AdminPanel.RegistrationStatus.RegStatusActivity;
import cricketworldcup.worldcup.AdminPanel.ShowWeeklyWinner.ShowWeeklyWinnerFragment;
import cricketworldcup.worldcup.AdminPanel.TeamParticipant.TeamParticipantActivity;
import cricketworldcup.worldcup.R;


public class AdminHomeFragment extends Fragment {
    private Button reg_status,quiz_participant,quiz_detail,myteam_bt,myteam_details,weeklyWinner;
   ShowWeeklyWinnerFragment showWeeklyWinnerFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);
        showWeeklyWinnerFragment = new ShowWeeklyWinnerFragment();
      weeklyWinner = view.findViewById(R.id.adminWeeklyWinnerList);
      weeklyWinner.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
          SetFragment(showWeeklyWinnerFragment);
          }
      });







        myteam_details = view.findViewById(R.id.myteam_details);
        myteam_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),QuizParticipantScoreActivity.class));
                getActivity().overridePendingTransition(0,0);

            }
        });

        myteam_bt = view.findViewById(R.id.myteam_bt);
        myteam_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),TeamParticipantActivity.class));
                getActivity().overridePendingTransition(0,0);

            }
        });
        quiz_detail = view.findViewById(R.id.quiz_Details_bt);
        quiz_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),QuizParticipantScoreActivity.class));
                getActivity().overridePendingTransition(0,0);
            }
        });
        quiz_participant = view.findViewById(R.id.Quiz_participant);
        quiz_participant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),QuizParticipant.class));
                getActivity().overridePendingTransition(0,0);

            }
        });
        reg_status = view.findViewById(R.id.btn_admin_reg_status);
        reg_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),RegStatusActivity.class));
                getActivity().overridePendingTransition(0,0);
            }
        });
        return view;
    }

    private void SetFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack("my_fragment").commit();


    }
}