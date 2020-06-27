package com.smilesifat.localcricketcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class MatchDetailsOfflineActivity extends AppCompatActivity {

    EditText totalOversEditTextOffline, totalPlayerEditTextOffline, teamOneEditTextOffline, teamTwoEditTextOffline;
    Button MatchDetailsButtonOffline;
    TextView toolbarText;
    ImageView toolbarBackButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details_offline);
        setUpToolbar();
        totalOversEditTextOffline = findViewById(R.id.enter_total_overs_offline);
        totalPlayerEditTextOffline= findViewById(R.id.enter_total_players_offline);
        teamOneEditTextOffline = findViewById(R.id.enter_team_name_one_offline);
        teamTwoEditTextOffline = findViewById(R.id.enter_team_name_two_offline);
        MatchDetailsButtonOffline= findViewById(R.id.next_button_offline);

        MatchDetailsButtonOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MatchDetailsOfflineActivity.this, ScoreCounter.class);
                int Overs =Integer.parseInt(totalOversEditTextOffline.getText().toString().trim());
                int Players = Integer.parseInt(totalPlayerEditTextOffline.getText().toString().trim());
                String TeamOne = teamOneEditTextOffline.getText().toString().trim();
                String TeamTwo = MatchDetailsButtonOffline.getText().toString().trim();

                intent.putExtra("total_Overs", Overs);
                intent.putExtra("total_Players",Players);
                intent.putExtra("team_One",TeamOne);
                intent.putExtra("team_Two",TeamTwo);

                totalOversEditTextOffline.setText("");
                totalPlayerEditTextOffline.setText("");
                teamOneEditTextOffline.setText("");
                teamTwoEditTextOffline.setText("");
                startActivity(intent);
            }
        });
    }
    @SuppressLint("SetTextI18n")
    private void setUpToolbar(){
        toolbarText = findViewById(R.id.toolrbarText);
        toolbarBackButton = findViewById(R.id.toolbarButton);
        toolbarText.setText("Match Details Offline");
        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
