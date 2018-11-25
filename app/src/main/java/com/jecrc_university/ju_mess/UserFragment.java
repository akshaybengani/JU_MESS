package com.jecrc_university.ju_mess;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;


/**
 * A simple {@link Fragment} subclass.
 */

public class UserFragment extends Fragment {

    private ScrollView scrollViewUser;
    private TextView textViewFullName,textViewEmail,textViewContactNo,textViewHostelStatus,textViewGender,textViewRegistrationNo;
    private ImageView imageViewUserImage;
    private Button buttonSignOut,buttonEditInfo;
    private List<MyUserData> myUserDataList;

    private String userId,fullname,email,contactNo,registrationNo,hostelStatus,genderInfo;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;


    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user, container, false);

        scrollViewUser = (ScrollView)view.findViewById(R.id.scrollviewUser);
        scrollViewUser.setVisibility(View.GONE);

        textViewFullName = (TextView)view.findViewById(R.id.textviewFullName);
        textViewEmail = (TextView)view.findViewById(R.id.textviewEmail);
        textViewContactNo = (TextView)view.findViewById(R.id.textviewContactNo);
        textViewHostelStatus = (TextView)view.findViewById(R.id.textviewHostelStatus);
        textViewGender = (TextView)view.findViewById(R.id.textviewGender);
        textViewRegistrationNo = (TextView)view.findViewById(R.id.textviewRegistrationNo);
        imageViewUserImage = (ImageView)view.findViewById(R.id.imageViewUserImage);
        buttonSignOut = (Button)view.findViewById(R.id.buttonSignOut);
        buttonEditInfo = (Button)view.findViewById(R.id.buttonEditInfo);

        myUserDataList = new ArrayList<>();

        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("/Users/");

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Loading ...");
        progressDialog.setMessage("Please Wait while we bring your information");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    if (Objects.equals(dataSnapshot1.getKey(), userId))
                    {
                        MyUserData myUserData = dataSnapshot1.getValue(MyUserData.class);
                        myUserDataList.add(myUserData);
                    }
                }
                onResponseRecieved();
             }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                onResponseRecieved();
            }
        });



        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                if (FirebaseAuth.getInstance().getCurrentUser()==null)
                {
                    Intent intent = new Intent(getContext(),LogInActivity.class);
                    startActivity(intent);
                }
            }
        });


        return view;
    }

    private void onResponseRecieved() {

        if (myUserDataList.isEmpty())
        {
            Toast.makeText(getContext(),"Something went wrong Please try again",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(),MainActivity.class);
            startActivity(intent);
        }
        else {

            fullname = myUserDataList.get(0).getFullName();
            contactNo = myUserDataList.get(0).getContactNo();
            registrationNo = myUserDataList.get(0).getRegistrationNo();
            hostelStatus = myUserDataList.get(0).getHostlerStatus();
            genderInfo = myUserDataList.get(0).getGender();

            email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

            textViewFullName.setText(fullname);
            textViewEmail.setText(email);
            textViewRegistrationNo.setText(registrationNo);
            textViewHostelStatus.setText(hostelStatus);
            textViewGender.setText(genderInfo);
            textViewContactNo.setText(contactNo);

            progressDialog.dismiss();
            scrollViewUser.setVisibility(View.VISIBLE);

        }

    }

}
