package com.smilesifat.localcricketcounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.smilesifat.localcricketcounter.firebase_models.Details;
import com.smilesifat.localcricketcounter.firebase_models.Score;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MatchesListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MatchAdapter matchAdapter;
    ArrayList<Details> matchDetailsList;
    ArrayList<Score> matchScoreAList;
    ArrayList<Score> matchScoreBList;
    DatabaseReference matchesReference = FirebaseDatabase.getInstance().getReference().child("matches");
    TextView toolbarText;
    ImageView toolbarBackButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches_list);

        setUpToolbar();

        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        matchDetailsList = new ArrayList<>();
        matchScoreAList = new ArrayList<>();
        matchScoreBList = new ArrayList<>();

        getMatchesList();

    }

    private void getMatchesList() {

        matchesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot m : dataSnapshot.getChildren()){
                    Details d = m.child("details").getValue(Details.class);
                    matchDetailsList.add(d);
                    Score s1 = m.child("score_team_a").getValue(Score.class);
                    matchScoreAList.add(s1);
                    Score s2 = m.child("score_team_b").getValue(Score.class);
                    matchScoreBList.add(s2);
                }
                matchAdapter = new MatchAdapter(MatchesListActivity.this, matchDetailsList,matchScoreAList,matchScoreBList);
                recyclerView.setAdapter(matchAdapter);
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

        toolbarText.setText("Matches Lists");

        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
