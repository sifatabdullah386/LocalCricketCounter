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
import java.text.DecimalFormat;

public class MatchScorerActivity extends AppCompatActivity {

    Button zeroButton,oneButton,twoButton,threeButton,fourButton,sixButton,outButton,wideButton,noBallButton,byeButton,legByButton,nextButton;
    TextView scoreText,oversText,runRateText,ballByBallText,setTeam;
    TextView toolbarText;
    ImageView toolbarBackButton;
    String match_no_1;
    DatabaseReference matchReferenceTeamA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_scorer);

        setUpToolbar();

        match_no_1 = getIntent().getStringExtra("match_no");
        matchReferenceTeamA = FirebaseDatabase.getInstance().getReference().child("matches").child(match_no_1);

        setTeam = findViewById(R.id.setTeam_a);
        zeroButton = findViewById(R.id.ButtonZero_team_a);
        oneButton = findViewById(R.id.ButtonOne_team_a);
        twoButton = findViewById(R.id.ButtonTwo_team_a);
        threeButton = findViewById(R.id.ButtonThree_team_a);
        fourButton = findViewById(R.id.ButtonFour_team_a);
        sixButton = findViewById(R.id.ButtonSix_team_a);
        outButton = findViewById(R.id.ButtonOut_team_a);
        wideButton = findViewById(R.id.ButtonWide_team_a);
        noBallButton = findViewById(R.id.ButtonNoBall_team_a);
        byeButton = findViewById(R.id.ButtonBye_team_a);
        legByButton = findViewById(R.id.ButtonLegBy_team_a);
        nextButton = findViewById(R.id.next_team_b);

        scoreText = findViewById(R.id.score);
        oversText = findViewById(R.id.overs);
        runRateText = findViewById(R.id.show_run_rate_team_a);
        ballByBallText = findViewById(R.id.textViewBallByBall_team_a);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MatchScorerActivity.this,MatchScorer2Activity.class);
                intent.putExtra("match_no",match_no_1);
                startActivity(intent);
            }
        });

        matchReferenceTeamA.child("details").addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Details d = dataSnapshot.getValue(Details.class);
                assert d != null;
                setTeam.setText(d.getTeamOne()+"(Batting)");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        matchReferenceTeamA.child("score_team_a").setValue(new Score(0,0,0,0,0,0,0,0,0,0,0,0,0));

        addScore(zeroButton);
        addScore(oneButton);
        addScore(twoButton);
        addScore(threeButton);
        addScore(fourButton);
        addScore(sixButton);
        addScore(outButton);
        addScore(wideButton);
        addScore(noBallButton);
        addScore(byeButton);
        addScore(legByButton);
        updateScore();

    }

    private void addScore(final Button button){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                matchReferenceTeamA.child("score_team_a").addListenerForSingleValueEvent(new ValueEventListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Score s = dataSnapshot.getValue(Score.class);

                        assert s != null;
                        int zero_runs = s.getZeroRuns();
                        int runs = s.getRuns();
                        int one_runs = s.getOneRuns();
                        int two_runs = s.getTwoRuns();
                        int three_runs = s.getThreeRuns();
                        int four_runs = s.getFourRuns();
                        int six_runs = s.getSixRuns();
                        int wickets = s.getWkts();
                        int balls = s.getBalls();
                        int wides = s.getWides();
                        int no_balls = s.getNoballs();
                        int leg_by_balls = s.getLegByRuns();
                        int bye_balls = s.getByeRuns();

                        switch (button.getTag().toString()){
                            case "0": //zero run
                                balls++;
                                zero_runs++;
                                ballByBallText.setText(ballByBallText.getText() + "0.");
                                break;
                            case "1": //one run
                                runs++;
                                balls++;
                                one_runs++;
                                ballByBallText.setText(ballByBallText.getText() + "1.");
                                break;
                            case "2": //two run
                                runs+=2;
                                balls++;
                                two_runs++;
                                ballByBallText.setText(ballByBallText.getText() + "2.");
                                break;
                            case "3": //three run
                                runs+=3;
                                balls++;
                                three_runs++;
                                ballByBallText.setText(ballByBallText.getText() + "3.");
                                break;
                            case "4": //four runs
                                runs+=4;
                                balls++;
                                four_runs++;
                                ballByBallText.setText(ballByBallText.getText() + "4.");
                                break;
                            case "5": //wickets
                                wickets++;
                                balls++;
                                if (s.getWkts() >= 9) {
                                    zeroButton.setEnabled(false);
                                    oneButton.setEnabled(false);
                                    twoButton.setEnabled(false);
                                    threeButton.setEnabled(false);
                                    fourButton.setEnabled(false);
                                    sixButton.setEnabled(false);
                                    wideButton.setEnabled(false);
                                    noBallButton.setEnabled(false);
                                    legByButton.setEnabled(false);
                                    byeButton.setEnabled(false);
                                    outButton.setEnabled(false);
                                }
                                ballByBallText.setText(ballByBallText.getText() + "W.");
                                break;
                            case "6": //six runs
                                runs+=6;
                                balls++;
                                six_runs++;
                                ballByBallText.setText(ballByBallText.getText() + "6.");
                                break;
                            case "7": //wide ball
                                runs++;
                                wides++;
                                ballByBallText.setText(ballByBallText.getText() + "WD.");
                                break;
                            case "8": //no ball
                                runs++;
                                no_balls++;
                                ballByBallText.setText(ballByBallText.getText() + "N.");
                                break;
                            case "9": //legBy ball
                                runs++;
                                leg_by_balls++;
                                ballByBallText.setText(ballByBallText.getText() + "LB.");
                                break;
                            case "10": //bye ball
                                runs++;
                                bye_balls++;
                                ballByBallText.setText(ballByBallText.getText() + "B.");
                                break;
                            case "11": //undo button

                                break;
                        }
                        dataSnapshot.getRef().setValue(new Score(balls,runs,wickets,wides,no_balls,zero_runs,one_runs,two_runs,three_runs,four_runs,six_runs,leg_by_balls,bye_balls));
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    public void updateScore(){

        matchReferenceTeamA.child("score_team_a").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final Score s = dataSnapshot.getValue(Score.class);

                assert s != null;
                String score = s.getRuns() + "/" + s.getWkts();
                final int totalBalls=s.getBalls();
                int over = totalBalls / 6;
                int balls = totalBalls % 6;

                String overs = over + "." + balls;

                final double totalScore=s.getRuns();
                double runRates= totalScore/over;
                runRateText.setText(String.valueOf(new DecimalFormat("##.##").format(runRates)));
                scoreText.setText(score);
                oversText.setText(overs);

                matchReferenceTeamA.child("details").addListenerForSingleValueEvent(new ValueEventListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        final Details d = dataSnapshot.getValue(Details.class);
                        assert d != null;
                        final int totalBallsGiven=d.getTotalOvers()*6;
                        if (totalBalls == totalBallsGiven) {
                            zeroButton.setEnabled(false);
                            oneButton.setEnabled(false);
                            twoButton.setEnabled(false);
                            threeButton.setEnabled(false);
                            fourButton.setEnabled(false);
                            sixButton.setEnabled(false);
                            wideButton.setEnabled(false);
                            noBallButton.setEnabled(false);
                            legByButton.setEnabled(false);
                            byeButton.setEnabled(false);
                            outButton.setEnabled(false);
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
    @SuppressLint("SetTextI18n")
    private void setUpToolbar(){
        toolbarText = findViewById(R.id.toolrbarText);
        toolbarBackButton = findViewById(R.id.toolbarButton);
        toolbarText.setText("Score Counter Online");
        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}