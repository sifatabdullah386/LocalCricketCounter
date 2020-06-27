package com.smilesifat.localcricketcounter;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.DecimalFormat;

public class ScoreCounter extends AppCompatActivity implements View.OnClickListener {

    TextView setTeamName,showRun,showWickets,showOvers,showBalls,showRunRate,showBaByBall;
    Button zeroRun,oneRun,twoRuns,threeRuns,fourRuns,sixRuns,wideRuns,legByRuns,byeRuns,noBalls,wickets;
    Button NextTeamButton;
    int balls=0;
    int overs=0;
    double totalScore=0;
    int totalWickets=0;
    double runRates=0;
    int totalOversOffline;
    int totalPlayersOffline;
    String teamOneOffline;
    String teamTwoOffline;
    TextView toolbarText;
    ImageView toolbarBackButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_counter);

        setUpToolbar();

        setTeamName=findViewById(R.id.setTeam_A);
        showRun = findViewById(R.id.show_team_1_score);
        showWickets = findViewById(R.id.show_team_1_wicket);
        showOvers = findViewById(R.id.show_team_1_overs);
        showBalls = findViewById(R.id.show_team_1_balls);
        showRunRate = findViewById(R.id.show_run_rate);
        showBaByBall = findViewById(R.id.textViewBallByBall);

        totalOversOffline=getIntent().getIntExtra("total_Overs",0);
        totalPlayersOffline=getIntent().getIntExtra("total_Players",0);
        teamOneOffline=getIntent().getStringExtra("team_One");
        setTeamName.setText(teamOneOffline);

        zeroRun = findViewById(R.id.ButtonZero);
        oneRun = findViewById(R.id.ButtonOne);
        twoRuns = findViewById(R.id.ButtonTwo);
        threeRuns = findViewById(R.id.ButtonThree);
        fourRuns = findViewById(R.id.ButtonFour);
        sixRuns = findViewById(R.id.ButtonSix);
        wideRuns = findViewById(R.id.ButtonWide);
        legByRuns = findViewById(R.id.ButtonLegBy);
        byeRuns = findViewById(R.id.ButtonBye);
        noBalls = findViewById(R.id.ButtonNoBall);
        wickets = findViewById(R.id.ButtonOut);
        NextTeamButton=findViewById(R.id.Next_Team_Score);

        zeroRun.setOnClickListener(this);
        oneRun.setOnClickListener(this);
        twoRuns.setOnClickListener(this);
        threeRuns.setOnClickListener(this);
        fourRuns.setOnClickListener(this);
        sixRuns.setOnClickListener(this);
        wideRuns.setOnClickListener(this);
        legByRuns.setOnClickListener(this);
        byeRuns.setOnClickListener(this);
        noBalls.setOnClickListener(this);
        wickets.setOnClickListener(this);
        NextTeamButton.setOnClickListener(this);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ButtonZero:
                balls=balls+1;
                displayRuns (totalScore);
                displayBalls(balls);
                displayRunRate(runRates);
                displayOvers(overs);
                showBaByBall.setText(showBaByBall.getText() + "0.");
                break;
            case R.id.ButtonOne:
                totalScore = totalScore + 1;
                displayRuns (totalScore);
                balls=balls+1;
                displayBalls(balls);
                displayRunRate(runRates);
                displayOvers(overs);
                showBaByBall.setText(showBaByBall.getText() + "1.");

                break;
            case R.id.ButtonTwo:
                totalScore=totalScore+2;
                displayRuns(totalScore);
                balls=balls+1;
                displayBalls(balls);
                displayRunRate(runRates);
                displayOvers(overs);
                showBaByBall.setText(showBaByBall.getText() + "2.");

                break;
            case R.id.ButtonThree:
                totalScore=totalScore+3;
                displayRuns(totalScore);
                balls=balls+1;
                displayBalls(balls);
                displayRunRate(runRates);
                displayOvers(overs);
                showBaByBall.setText(showBaByBall.getText() + "3.");

                break;
            case R.id.ButtonFour:
                totalScore=totalScore+4;
                displayRuns(totalScore);
                balls=balls+1;
                displayBalls(balls);
                displayRunRate(runRates);
                displayOvers(overs);
                showBaByBall.setText(showBaByBall.getText() + "4.");

                break;
            case R.id.ButtonSix:
                totalScore=totalScore+6;
                displayRuns(totalScore);
                balls=balls+1;
                displayBalls(balls);
                displayRunRate(runRates);
                displayOvers(overs);
                showBaByBall.setText(showBaByBall.getText() + "6.");
                break;
            case R.id.ButtonWide:
                totalScore=totalScore+1;
                displayRuns(totalScore);
                displayRunRate(runRates);
                showBaByBall.setText(showBaByBall.getText() + "W.");

                break;
            case R.id.ButtonLegBy:
                totalScore=totalScore+5;
                displayRuns(totalScore);
                balls=balls+1;
                displayBalls(balls);
                displayRunRate(runRates);
                displayOvers(overs);
                showBaByBall.setText(showBaByBall.getText() + "LB.");

                break;
            case R.id.ButtonBye:
                totalScore=totalScore+1;
                displayRuns(totalScore);
                balls=balls+1;
                displayBalls(balls);
                displayRunRate(runRates);
                displayOvers(overs);
                showBaByBall.setText(showBaByBall.getText() + "B.");

                break;
            case R.id.ButtonNoBall:
                totalScore=totalScore+1;
                displayRuns (totalScore);
                displayRunRate(runRates);
                showBaByBall.setText(showBaByBall.getText() + "NB.");

                break;
            case R.id.ButtonOut:
                totalWickets=totalWickets+1;
                displayWickets(totalWickets);
                displayRunRate(runRates);
                displayOvers(overs);
                showBaByBall.setText(showBaByBall.getText() + "W.");

                if (totalWickets >= 10) {
                    zeroRun.setEnabled(false);
                    oneRun.setEnabled(false);
                    twoRuns.setEnabled(false);
                    threeRuns.setEnabled(false);
                    fourRuns.setEnabled(false);
                    sixRuns.setEnabled(false);
                    wideRuns.setEnabled(false);
                    noBalls.setEnabled(false);
                    legByRuns.setEnabled(false);
                    byeRuns.setEnabled(false);
                    wickets.setEnabled(false);
                }
                break;
            case R.id.Next_Team_Score:
                goToTeamB();
                break;
        }

    }
    public void displayRuns(double score){
        showRun.setText(String.valueOf(new DecimalFormat("###").format(score)));
    }
    public void displayWickets(int wicket){
        showWickets.setText(String.valueOf(wicket));
    }
    public void displayRunRate(double run_rate){
        runRates= (totalScore/overs);
        showRunRate.setText(String.valueOf(new DecimalFormat("##.##").format(run_rate)));
    }
    public void displayBalls(int ball){
        if(balls == 6){
            balls = 0;
            overs++;
        }
        showBalls.setText(String.valueOf(ball));
    }
    public void displayOvers(int over){
        if(balls == 6){
            balls = 0;
            overs++;
        }
        showOvers.setText(String.valueOf(over));
    }
    public void goToTeamB(){
        balls=0;
        overs=0;
        totalScore=0;
        totalWickets=0;
        runRates=0;
        displayRuns(totalScore);
        displayBalls(balls);
        displayRunRate(runRates);
        displayOvers(overs);
        showRun.setText(String.valueOf(new DecimalFormat("###").format(totalScore)));
        showWickets.setText(String.valueOf(totalWickets));
        showRunRate.setText(String.valueOf(runRates));
        showOvers.setText(String.valueOf(overs));
        showBalls.setText(String.valueOf(balls));
        showBaByBall.setText("");
        zeroRun.setEnabled(true);
        oneRun.setEnabled(true);
        twoRuns.setEnabled(true);
        threeRuns.setEnabled(true);
        fourRuns.setEnabled(true);
        sixRuns.setEnabled(true);
        wideRuns.setEnabled(true);
        noBalls.setEnabled(true);
        legByRuns.setEnabled(true);
        byeRuns.setEnabled(true);
        wickets.setEnabled(true);
        teamTwoOffline=getIntent().getStringExtra("team_Two");
        setTeamName.setText(teamTwoOffline);
    }
    @SuppressLint("SetTextI18n")
    private void setUpToolbar(){
        toolbarText = findViewById(R.id.toolrbarText);
        toolbarBackButton = findViewById(R.id.toolbarButton);
        toolbarText.setText("Score Counter Offline");
        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
