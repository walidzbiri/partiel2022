package zbiri.walid.partiel2022.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    public static final String ENDPOINT="https://busin.fr/";

    @GET("drivers.json")
    Call<ArrayList<Driver>> getDrivers();

    @GET("teams.json")
    Call<TeamsResponse> getTeams();

}
