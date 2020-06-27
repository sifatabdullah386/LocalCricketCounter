package com.smilesifat.localcricketcounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.smilesifat.localcricketcounter.firebase_models.Details;
import com.smilesifat.localcricketcounter.firebase_models.Score;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MatchResultActivity extends AppCompatActivity {

  TextView showTeamOne,showTeamOneRunsWickets,showTeamOneOversBalls,showTeamTwo,showTeamTwoRunsWickets,showTeamTwoOversBalls,showWinningTeam;
  Button exitButton;
  TextView toolbarText;
  ImageView toolbarBackButton;
  String match_no_3;
  DatabaseReference matchReferenceResult;
  String team1,team2;
  @SuppressLint("SetTextI18n")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_match_result);

    setUpToolbar();

    match_no_3 = getIntent().getStringExtra("match_no");
    matchReferenceResult = FirebaseDatabase.getInstance().getReference().child("matches").child(match_no_3);

    showTeamOne=findViewById(R.id.show_team_1_name);
    showTeamOneRunsWickets=findViewById(R.id.show_team_1_runs_wickets);
    showTeamOneOversBalls=findViewById(R.id.show_team_1_overs_balls);
    showTeamTwo=findViewById(R.id.show_team_2_name);
    showTeamTwoRunsWickets=findViewById(R.id.show_team_2_runs_wickets);
    showTeamTwoOversBalls=findViewById(R.id.show_team_2_overs_balls);
    showWinningTeam=findViewById(R.id.show_wining_team);

    exitButton=findViewById(R.id.go_to_main);

    exitButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MatchResultActivity.this,MainActivity.class);
        startActivity(intent);
      }
    });
    matchReferenceResult.child("details").addListenerForSingleValueEvent(new ValueEventListener() {
      @SuppressLint("SetTextI18n")
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        final Details d = dataSnapshot.getValue(Details.class);
        assert d != null;
        final String teamOne= d.getTeamOne();
        team1=teamOne;
        final String teamTwo= d.getTeamTwo();
        team2=teamTwo;
        showTeamOne.setText(teamOne);
        showTeamTwo.setText(teamTwo);
        matchReferenceResult.child("score_team_a").addListenerForSingleValueEvent(new ValueEventListener() {
          @SuppressLint("SetTextI18n")
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            final Score s = dataSnapshot.getValue(Score.class);
            assert s != null;
            final int totalScoreA = s.getRuns();
            final int totalWicketsA = s.getWkts();
            final int totalBallsA=s.getBalls();
            final int totalOversA = totalBallsA / 6;
            final int totalBalls1 = totalBallsA % 6;

            String scoreA = totalScoreA + "/" + totalWicketsA;
            String oversA = totalOversA + "." + totalBalls1;

            showTeamOneRunsWickets.setText(scoreA);
            showTeamOneOversBalls.setText(oversA);

            matchReferenceResult.child("score_team_b").addListenerForSingleValueEvent(new ValueEventListener() {
              @SuppressLint("SetTextI18n")
              @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Score s = dataSnapshot.getValue(Score.class);
                assert s != null;
                final int totalScoreB = s.getRuns();
                final int totalWicketsB = s.getWkts();
                final int totalBallsB=s.getBalls();
                final int totalOversB = totalBallsB/ 6;
                final int totalBalls2 = totalBallsB% 6;

                String scoreB = totalScoreB + "/" + totalWicketsB;
                String oversB = totalOversB + "." + totalBalls2;

                showTeamTwoRunsWickets.setText(scoreB);
                showTeamTwoOversBalls.setText(oversB);

                if(totalScoreA>totalScoreB){
                  final int totalScore=totalScoreA-totalScoreB;
                  //final int totalBalls=getBalls-totalBallsB;
                  showWinningTeam.setText(teamOne+" Win by "+totalScore+" Runs");
                }
                else if (totalScoreA < totalScoreB){
                  final int totalWickets=10-totalWicketsB;
                  //final int totalBalls=getBalls-totalBallsA;
                  showWinningTeam.setText(teamTwo+" Win by "+totalWickets+" Wickets");
                }
                else{
                    showWinningTeam.setText("Match Drawn");
                }
              }
              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {
              }
            });
          }
          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {
          }
        });
      }
      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {
      }
    });

  }
  @SuppressLint("SetTextI18n")
  private void setUpToolbar(){
    toolbarText = findViewById(R.id.toolrbarText);
    toolbarBackButton = findViewById(R.id.toolbarButton);
    toolbarText.setText("Score Counter Result");
    toolbarBackButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });
  }
}
