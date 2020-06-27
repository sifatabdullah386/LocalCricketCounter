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

public class MatchScorer2Activity extends AppCompatActivity {

    Button zeroButtonB,oneButtonB,twoButtonB,threeButtonB,fourButtonB,sixButtonB,outButtonB,wideButtonB,noBallButtonB
            ,finishButtonB,byeButtonB,legByButtonB;
    TextView scoreTextB,oversTextB,runRateTextB,ballByBallTextB,setTeamB;
    TextView toolbarText;
    ImageView toolbarBackButton;
    String match_no_2;
    DatabaseReference matchReferenceTeamB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_scorer2);

        setUpToolbar();

        match_no_2 = getIntent().getStringExtra("match_no");
        matchReferenceTeamB = FirebaseDatabase.getInstance().getReference().child("matches").child(match_no_2);

        setTeamB = findViewById(R.id.setTeam_b);
        zeroButtonB = findViewById(R.id.ButtonZero_team_b);
        oneButtonB = findViewById(R.id.ButtonOne_team_b);
        twoButtonB = findViewById(R.id.ButtonTwo_team_b);
        threeButtonB = findViewById(R.id.ButtonThree_team_b);
        fourButtonB = findViewById(R.id.ButtonFour_team_b);
        sixButtonB = findViewById(R.id.ButtonSix_team_b);
        outButtonB = findViewById(R.id.ButtonOut_team_b);
        wideButtonB = findViewById(R.id.ButtonWide_team_b);
        noBallButtonB = findViewById(R.id.ButtonNoBall_team_b);
        byeButtonB = findViewById(R.id.ButtonBye_team_b);
        legByButtonB = findViewById(R.id.ButtonLegBy_team_b);

        scoreTextB = findViewById(R.id.score_b);
        oversTextB = findViewById(R.id.overs_b);
        runRateTextB = findViewById(R.id.show_run_rate_team_b);
        ballByBallTextB = findViewById(R.id.textViewBallByBall_team_b);

        finishButtonB = findViewById(R.id.finish_Score_team_b);

        finishButtonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MatchScorer2Activity.this,MatchResultActivity.class);
                intent.putExtra("match_no",match_no_2);
                startActivity(intent);
            }
        });
        matchReferenceTeamB.child("details").addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Details d = dataSnapshot.getValue(Details.class);
                assert d != null;
                setTeamB.setText(d.getTeamTwo()+"(Batting)");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        matchReferenceTeamB.child("score_team_b").setValue(new Score(0,0,0,0,0,0,0,0,0,0,0,0,0));

        addScore(zeroButtonB);
        addScore(oneButtonB);
        addScore(twoButtonB);
        addScore(threeButtonB);
        addScore(fourButtonB);
        addScore(sixButtonB);
        addScore(outButtonB);
        addScore(wideButtonB);
        addScore(noBallButtonB);
        addScore(byeButtonB);
        addScore(legByButtonB);
        updateScore();
    }

    private void addScore(final Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matchReferenceTeamB.child("score_team_b").addListenerForSingleValueEvent(new ValueEventListener() {
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
                            case "00": //zero run
                                balls++;
                                zero_runs++;
                                ballByBallTextB.setText(ballByBallTextB.getText() + "0.");
                                break;
                            case "11": //one run
                                runs++;
                                balls++;
                                one_runs++;
                                ballByBallTextB.setText(ballByBallTextB.getText() + "1.");
                                break;
                            case "22": //two run
                                runs+=2;
                                balls++;
                                two_runs++;
                                ballByBallTextB.setText(ballByBallTextB.getText() + "2.");
                                break;
                            case "33": //three run
                                runs+=3;
                                balls++;
                                three_runs++;
                                ballByBallTextB.setText(ballByBallTextB.getText() + "3.");
                                break;
                            case "44": //four runs
                                runs+=4;
                                balls++;
                                four_runs++;
                                ballByBallTextB.setText(ballByBallTextB.getText() + "4.");
                                break;
                            case "55": //wickets
                                wickets++;
                                balls++;
                                if (s.getWkts() >= 9) {
                                    zeroButtonB.setEnabled(false);
                                    oneButtonB.setEnabled(false);
                                    twoButtonB.setEnabled(false);
                                    threeButtonB.setEnabled(false);
                                    fourButtonB.setEnabled(false);
                                    sixButtonB.setEnabled(false);
                                    wideButtonB.setEnabled(false);
                                    noBallButtonB.setEnabled(false);
                                    legByButtonB.setEnabled(false);
                                    byeButtonB.setEnabled(false);
                                    outButtonB.setEnabled(false);
                                }
                                ballByBallTextB.setText(ballByBallTextB.getText() + "W.");
                                break;
                            case "66": //six runs
                                runs+=6;
                                balls++;
                                six_runs++;
                                ballByBallTextB.setText(ballByBallTextB.getText() + "6.");
                                break;
                            case "77": //wide ball
                                runs++;
                                wides++;
                                ballByBallTextB.setText(ballByBallTextB.getText() + "WD.");
                                break;
                            case "88": //no ball
                                runs++;
                                no_balls++;
                                ballByBallTextB.setText(ballByBallTextB.getText() + "N.");
                                break;
                            case "99": //legBy ball
                                runs++;
                                leg_by_balls++;
                                ballByBallTextB.setText(ballByBallTextB.getText() + "LB.");
                                break;
                            case "100": //bye ball
                                runs++;
                                bye_balls++;
                                ballByBallTextB.setText(ballByBallTextB.getText() + "B.");
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
    private void updateScore(){
        matchReferenceTeamB.child("score_team_b").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final Score s = dataSnapshot.getValue(Score.class);

                assert s != null;
                String score = s.getRuns() + "/" + s.getWkts();
                final int totalBallsB =s.getBalls();
                int over = totalBallsB / 6;
                int balls = totalBallsB % 6;

                String overs = over + "." + balls;

                final double totalScoreB=s.getRuns();
                double runRates= totalScoreB/over;
                runRateTextB.setText(String.valueOf(new DecimalFormat("##.##").format(runRates)));
                scoreTextB.setText(score);
                oversTextB.setText(overs);
                matchReferenceTeamB.child("details").addListenerForSingleValueEvent(new ValueEventListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        final Details d = dataSnapshot.getValue(Details.class);
                        assert d != null;
                        final int totalBallsGiven=d.getTotalOvers()*6;
                        if (totalBallsB == totalBallsGiven) {
                            zeroButtonB.setEnabled(false);
                            oneButtonB.setEnabled(false);
                            twoButtonB.setEnabled(false);
                            threeButtonB.setEnabled(false);
                            fourButtonB.setEnabled(false);
                            sixButtonB.setEnabled(false);
                            wideButtonB.setEnabled(false);
                            noBallButtonB.setEnabled(false);
                            legByButtonB.setEnabled(false);
                            byeButtonB.setEnabled(false);
                            outButtonB.setEnabled(false);
                        }
                        matchReferenceTeamB.child("score_team_a").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                final Score s = dataSnapshot.getValue(Score.class);
                                assert s != null;
                                final double totalScoreA=s.getRuns();
                                if(totalScoreB>totalScoreA){
                                    zeroButtonB.setEnabled(false);
                                    oneButtonB.setEnabled(false);
                                    twoButtonB.setEnabled(false);
                                    threeButtonB.setEnabled(false);
                                    fourButtonB.setEnabled(false);
                                    sixButtonB.setEnabled(false);
                                    wideButtonB.setEnabled(false);
                                    noBallButtonB.setEnabled(false);
                                    legByButtonB.setEnabled(false);
                                    byeButtonB.setEnabled(false);
                                    outButtonB.setEnabled(false);
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
        toolbarText.setText("Score Counter Online");
        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
