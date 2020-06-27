package com.smilesifat.localcricketcounter;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.smilesifat.localcricketcounter.firebase_models.Details;
import com.smilesifat.localcricketcounter.firebase_models.TeamAPlayers;
import com.smilesifat.localcricketcounter.firebase_models.TeamBPlayers;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TeamDetailsActivity extends AppCompatActivity {

  TextView TeamA,TeamB;
  EditText TeamAPlayer1,TeamAPlayer2,TeamAPlayer3,TeamAPlayer4,TeamAPlayer5,TeamAPlayer6,TeamAPlayer7,TeamAPlayer8,TeamAPlayer9,TeamAPlayer10,TeamAPlayer11;
  EditText TeamBPlayer1,TeamBPlayer2,TeamBPlayer3,TeamBPlayer4,TeamBPlayer5,TeamBPlayer6,TeamBPlayer7,TeamBPlayer8,TeamBPlayer9,TeamBPlayer10,TeamBPlayer11;
  Button PlayersToScoreButton;
  String match;
  TextView toolbarText;
  ImageView toolbarBackButton;
  DatabaseReference teamReferenceDB;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_team_details);

    setUpToolbar();
    PlayersToScoreButton = findViewById(R.id.playersToScoreButton);
    TeamAPlayer1 = findViewById(R.id.team_a_player_1);
    TeamAPlayer2 = findViewById(R.id.team_a_player_2);
    TeamAPlayer3 = findViewById(R.id.team_a_player_3);
    TeamAPlayer4 = findViewById(R.id.team_a_player_4);
    TeamAPlayer5 = findViewById(R.id.team_a_player_5);
    TeamAPlayer6 = findViewById(R.id.team_a_player_6);
    TeamAPlayer7 = findViewById(R.id.team_a_player_7);
    TeamAPlayer8 = findViewById(R.id.team_a_player_8);
    TeamAPlayer9 = findViewById(R.id.team_a_player_9);
    TeamAPlayer10 = findViewById(R.id.team_a_player_10);
    TeamAPlayer11 = findViewById(R.id.team_a_player_11);

    TeamBPlayer1 = findViewById(R.id.team_b_player_1);
    TeamBPlayer2 = findViewById(R.id.team_b_player_2);
    TeamBPlayer3 = findViewById(R.id.team_b_player_3);
    TeamBPlayer4 = findViewById(R.id.team_b_player_4);
    TeamBPlayer5 = findViewById(R.id.team_b_player_5);
    TeamBPlayer6 = findViewById(R.id.team_b_player_6);
    TeamBPlayer7 = findViewById(R.id.team_b_player_7);
    TeamBPlayer8 = findViewById(R.id.team_b_player_8);
    TeamBPlayer9 = findViewById(R.id.team_b_player_9);
    TeamBPlayer10 = findViewById(R.id.team_b_player_10);
    TeamBPlayer11 = findViewById(R.id.team_b_player_11);

    TeamA=findViewById(R.id.team_a_team_details);
    TeamB=findViewById(R.id.team_b_team_details);

    match = getIntent().getStringExtra("match_no");
    Log.d("adil" ,"match: " +match);

    teamReferenceDB = FirebaseDatabase.getInstance().getReference().child("matches").child(match);
    teamReferenceDB.child("details").addListenerForSingleValueEvent(new ValueEventListener() {
      @SuppressLint("SetTextI18n")
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        final Details d = dataSnapshot.getValue(Details.class);
        assert d != null;
        TeamA.setText(d.getTeamOne() + " Squad");
        TeamB.setText(d.getTeamTwo() + " Squad");
        PlayersToScoreButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            setTeamDetailsA();
            setTeamDetailsB();
            Intent intent=new Intent(TeamDetailsActivity.this, MatchScorerActivity.class);
            intent.putExtra("match_no",match);
            startActivity(intent);
          }
        });
      }
      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {
      }
    });
  }
  public  void setTeamDetailsA(){
    String teamAPlayer1=TeamAPlayer1.getText().toString().trim();
    String teamAPlayer2=TeamAPlayer2.getText().toString().trim();
    String teamAPlayer3=TeamAPlayer3.getText().toString().trim();
    String teamAPlayer4=TeamAPlayer4.getText().toString().trim();
    String teamAPlayer5=TeamAPlayer5.getText().toString().trim();
    String teamAPlayer6=TeamAPlayer6.getText().toString().trim();
    String teamAPlayer7=TeamAPlayer7.getText().toString().trim();
    String teamAPlayer8=TeamAPlayer8.getText().toString().trim();
    String teamAPlayer9=TeamAPlayer9.getText().toString().trim();
    String teamAPlayer10=TeamAPlayer10.getText().toString().trim();
    String teamAPlayer11=TeamAPlayer11.getText().toString().trim();

    teamReferenceDB.child("team_a").setValue(new TeamAPlayers(teamAPlayer1,teamAPlayer2,teamAPlayer3,teamAPlayer4,teamAPlayer5,teamAPlayer6,
            teamAPlayer7,teamAPlayer8,teamAPlayer9,teamAPlayer10,teamAPlayer11));
  }
  public  void setTeamDetailsB(){
    String teamBPlayer1=TeamBPlayer1.getText().toString().trim();
    String teamBPlayer2=TeamBPlayer2.getText().toString().trim();
    String teamBPlayer3=TeamBPlayer3.getText().toString().trim();
    String teamBPlayer4=TeamBPlayer4.getText().toString().trim();
    String teamBPlayer5=TeamBPlayer5.getText().toString().trim();
    String teamBPlayer6=TeamBPlayer6.getText().toString().trim();
    String teamBPlayer7=TeamBPlayer7.getText().toString().trim();
    String teamBPlayer8=TeamBPlayer8.getText().toString().trim();
    String teamBPlayer9=TeamBPlayer9.getText().toString().trim();
    String teamBPlayer10=TeamBPlayer10.getText().toString().trim();
    String teamBPlayer11=TeamBPlayer11.getText().toString().trim();

    teamReferenceDB.child("team_b").setValue(new TeamBPlayers(teamBPlayer1,teamBPlayer2,teamBPlayer3,teamBPlayer4,teamBPlayer5,teamBPlayer6,
            teamBPlayer7,teamBPlayer8,teamBPlayer9,teamBPlayer10,teamBPlayer11));
  }
  @SuppressLint("SetTextI18n")
  private void setUpToolbar(){
    toolbarText = findViewById(R.id.toolrbarText);
    toolbarBackButton = findViewById(R.id.toolbarButton);
    toolbarText.setText("Team Details");
    toolbarBackButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });
  }
}