package cricketworldcup.worldcup.AdminPanel.AdminProfile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import cricketworldcup.worldcup.Authentication.LoginActivity;
import cricketworldcup.worldcup.R;


public class AdminProfileFragment extends Fragment {
    Button button;
    FirebaseAuth firebaseAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_admin_profile, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
          button = view.findViewById(R.id.button_logout);

          button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  startActivity(new Intent(getActivity(),LoginActivity.class));
                  getActivity().overridePendingTransition(0,0);
              }
          });
        return view;
    }

}
