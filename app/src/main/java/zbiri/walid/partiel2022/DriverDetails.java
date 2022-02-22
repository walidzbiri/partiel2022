package zbiri.walid.partiel2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zbiri.walid.partiel2022.api.ApiService;
import zbiri.walid.partiel2022.api.Team;
import zbiri.walid.partiel2022.api.TeamsResponse;

public class DriverDetails extends AppCompatActivity {
    private Toolbar mTopToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_details);

        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("");
        ApplicationContext app=(ApplicationContext) getApplicationContext();

        ImageView driverImage=(ImageView) findViewById(R.id.imageView_driver);
        ImageView teamImage=(ImageView) findViewById(R.id.imageView_team);
        TextView firstname=(TextView) findViewById(R.id.textView_driverName);
        TextView lastname=(TextView) findViewById(R.id.textView_driverLastName);
        TextView position=(TextView) findViewById(R.id.textView_position);
        TextView nbpoints=(TextView) findViewById(R.id.textView_nbpoints);
        TextView desc=(TextView) findViewById(R.id.textView_desc);

         ApiService apiService=new Retrofit.Builder()
                .baseUrl(ApiService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class);
        Call<TeamsResponse> call=null;
        call=apiService.getTeams();
        call.enqueue(new Callback<TeamsResponse>() {
            @Override
            public void onResponse(Call<TeamsResponse> call, Response<TeamsResponse> response) {
                TeamsResponse repo_response = (TeamsResponse) response.body();
                Team myTeam=repo_response.getTeams().stream()
                        .filter(d -> d.getTeam().getId() == app.getTeam_id()) // keep only projects of a
                        .collect(Collectors.toList()).get(0);
                Picasso.get().load(myTeam.getTeam().getLogo()).into(teamImage);
                firstname.setText(app.getDriver().getDriver().getName().split(" ")[0]);
                lastname.setText(app.getDriver().getDriver().getName().split(" ")[1]);
                position.setText("Position au classement: "+myTeam.getPosition());
                nbpoints.setText("Nb Points: "+myTeam.getPoints());
                desc.setText(myTeam.getPosition()+(myTeam.getPosition()==1?"ère":"ème")+" position au classement par équipe avec "+myTeam.getPoints()+"pts");
                Picasso.get().load(app.getDriver().getDriver().getImage()).into(driverImage);

                //overview.setText(repo_response.getOverview());
                //genres.setText(textForGenre);
                //setTitle(repo_response.getTitle());
            }

            @Override
            public void onFailure(Call<TeamsResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage()
                        ,
                        Toast.LENGTH_SHORT).show();
            }
        });



    }
    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return  true;
    }

}