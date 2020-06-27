package com.smilesifat.localcricketcounter;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smilesifat.localcricketcounter.firebase_models.Details;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MatchDetailsActivity extends AppCompatActivity {

    EditText totalOversEditText, totalPlayerEditText, teamOneEditText, teamTwoEditText;
    Button MatchDetailsButton;
    String match;
    int total_matches=0;
    TextView toolbarText;
    ImageView toolbarBackButton;
    DatabaseReference dbReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);

        setUpToolbar();
        dbReference = FirebaseDatabase.getInstance().getReference();

        totalOversEditText = findViewById(R.id.enter_total_overs);
        totalPlayerEditText = findViewById(R.id.enter_total_players);
        teamOneEditText = findViewById(R.id.enter_team_name_one);
        teamTwoEditText = findViewById(R.id.enter_team_name_two);
        MatchDetailsButton = findViewById(R.id.next_button);

        dbReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                total_matches = dataSnapshot.child("total_matches").getValue(Integer.class) + 1;
                match = "match" + total_matches;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        MatchDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMatchDetails();
                Intent intent = new Intent(MatchDetailsActivity.this, TeamDetailsActivity.class);
                intent.putExtra("match_no",match);
                startActivity(intent);
            }
        });
    }
    public void setMatchDetails(){
        int Overs =Integer.parseInt(totalOversEditText.getText().toString().trim());
        int Players = Integer.parseInt(totalPlayerEditText.getText().toString().trim());
        String TeamOne = teamOneEditText.getText().toString().trim();
        String TeamTwo = teamTwoEditText.getText().toString().trim();

        dbReference.child("total_matches").setValue(total_matches);
        dbReference.child("matches").child(match).child("details").setValue(new Details(Overs,Players,TeamOne,TeamTwo,true));

        Toast.makeText(MatchDetailsActivity.this,"Details Add Successfully",Toast.LENGTH_LONG).show();
    }
    @SuppressLint("SetTextI18n")
    private void setUpToolbar(){
        toolbarText = findViewById(R.id.toolrbarText);
        toolbarBackButton = findViewById(R.id.toolbarButton);
        toolbarText.setText("Match Details");
        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}