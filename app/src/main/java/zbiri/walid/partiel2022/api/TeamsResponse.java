package zbiri.walid.partiel2022.api;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamsResponse {
    @SerializedName("teams")
    @Expose
    private List<Team> teams;

    public List<Team> getTeams() {
        return teams;
    }

    @NonNull
    @Override
    public String toString() {
        return teams.toString();
    }
}
