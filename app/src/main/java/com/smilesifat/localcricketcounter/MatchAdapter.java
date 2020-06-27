package com.smilesifat.localcricketcounter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.smilesifat.localcricketcounter.firebase_models.Details;
import com.smilesifat.localcricketcounter.firebase_models.Score;

import java.util.ArrayList;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Details> matchDetailsList;
    private ArrayList<Score> matchScoreAList;
    private ArrayList<Score> matchScoreBList;

    MatchAdapter(Context context, ArrayList<Details> matchDetailsList,ArrayList<Score>matchScoreAList,ArrayList<Score>matchScoreBList) {
        this.context = context;
        this.matchDetailsList = matchDetailsList;
        this.matchScoreAList = matchScoreAList;
        this.matchScoreBList = matchScoreBList;
    }
    @NonNull
    @Override
    public MatchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match,parent,false);
        return new MatchAdapter.MyViewHolder(v);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MatchAdapter.MyViewHolder holder, int position) {
        final Details match = matchDetailsList.get(position);
        String match_no = "Match : " + (position + 1);
        String team1=match.getTeamOne();
        String team2=match.getTeamTwo();
        String teams = team1 + " vs " + team2;
        holder.matchNoTextView.setText(match_no);
        holder.teamsTextView.setText(teams);
        holder.teamA.setText(team1);
        holder.teamB.setText(team2);

        final Score score1 = matchScoreAList.get(position);

        final int totalScoreA = score1.getRuns();
        final int totalWicketsA = score1.getWkts();
        final int totalOversA = score1.getBalls() / 6;
        final int totalBallsA = score1.getBalls() % 6;
        String scoreA = totalScoreA + "/" + totalWicketsA;
        String oversA = totalOversA + "." + totalBallsA;
        holder.TotalRunsTeamA.setText(scoreA);
        holder.TotalOversTeamA.setText(oversA);

        final Score score2 = matchScoreBList.get(position);
        final int totalScoreB = score2.getRuns();
        final int totalWicketsB = score2.getWkts();
        final int totalOversB = score2.getBalls() / 6;
        final int totalBallsB = score2.getBalls() % 6;
        String scoreB = totalScoreB + "/" + totalWicketsB;
        String oversB = totalOversB + "." + totalBallsB;
        holder.TotalRunsTeamB.setText(scoreB);
        holder.TotalOversTeamB.setText(oversB);

        if(totalScoreA>totalScoreB){
            int totalScore=totalScoreA-totalScoreB;
            holder.showWinningTeam.setText(team1+" Win by "+totalScore+" Runs");
        }
        else if(totalScoreA<totalScoreB){
            int totalWicket=10-totalWicketsB;
            holder.showWinningTeam.setText(team2+" Win by "+totalWicket+" Wickets");
        }

    }
    @Override
    public int getItemCount() {
        return matchDetailsList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView matchNoTextView;
        TextView teamsTextView;
        TextView teamA,teamB;
        TextView TotalRunsTeamA,TotalOversTeamA,TotalRunsTeamB,TotalOversTeamB,showWinningTeam;
        private MyViewHolder(View itemView) {
            super(itemView);
            matchNoTextView = itemView.findViewById(R.id.match_no);
            teamsTextView = itemView.findViewById(R.id.teamNames);
            teamA=itemView.findViewById(R.id.team_a);
            teamB=itemView.findViewById(R.id.team_b);

            TotalRunsTeamA=itemView.findViewById(R.id.total_runs_team_a);
            TotalOversTeamA=itemView.findViewById(R.id.total_overs_team_a);
            TotalRunsTeamB=itemView.findViewById(R.id.total_runs_team_b);
            TotalOversTeamB=itemView.findViewById(R.id.total_overs_team_b);
            showWinningTeam=itemView.findViewById(R.id.show_wining_team);

        }
    }

}
