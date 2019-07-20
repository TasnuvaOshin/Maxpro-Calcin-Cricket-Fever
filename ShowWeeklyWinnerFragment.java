package cricketworldcup.worldcup.AdminPanel.ShowWeeklyWinner;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cricketworldcup.worldcup.R;


public class ShowWeeklyWinnerFragment extends Fragment {
    private Button downloadQuiz, downloadTeam;
    private EditText editText;
    private String week;
    private DatabaseReference databaseReference;
    private DownloadManager downloadManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_weekly_winner, container, false);

        downloadQuiz = view.findViewById(R.id.downloadQuizList);
        downloadTeam = view.findViewById(R.id.downloadTeamList);
        editText = view.findViewById(R.id.Week);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Admin_Live").child("downloadList");

        downloadQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                week = editText.getText().toString();
                if (week.isEmpty()) {
                    Toast.makeText(getActivity(), "Please Enter Week Number ", Toast.LENGTH_SHORT).show();
                } else {

                    databaseReference.child(week).child("quiz").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String url = String.valueOf(dataSnapshot.getValue());

                            downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                            Uri uri = Uri.parse(url);
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            Long ref = downloadManager.enqueue(request);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }
        });


        downloadTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                week = editText.getText().toString();
                if (week.isEmpty()) {
                    Toast.makeText(getActivity(), "Please Enter Week Number ", Toast.LENGTH_SHORT).show();
                } else {

                    databaseReference.child(week).child("team").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String url = String.valueOf(dataSnapshot.getValue());

                            downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                            Uri uri = Uri.parse(url);
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            Long ref = downloadManager.enqueue(request);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }


            }
        });


        return view;
    }


}
