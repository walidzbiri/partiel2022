package zbiri.walid.partiel2022;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zbiri.walid.partiel2022.api.ApiService;
import zbiri.walid.partiel2022.api.Driver;
import zbiri.walid.partiel2022.api.DriversResponse;

public class MainActivity extends AppCompatActivity implements DriversAdapter.OnDriverListener{

    private List<Driver> mDrivers;
    private RecyclerView mRecyclerView;
    private DriversAdapter mDriversAdapter;
    private Context context;
    private AlertDialog.Builder builder;
    private Toolbar mTopToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);

        builder = new AlertDialog.Builder(this);
        builder.setTitle("Partiel Février 2022");
        builder.setMessage("ZBIRI WALID");
        builder.setPositiveButton("Fermer",null);

        //ApplicationContext app= (ApplicationContext) getApplicationContext();
        setTitle("");

        ApiService apiService=new Retrofit.Builder()
                .baseUrl(ApiService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class);
        Call<ArrayList<Driver>> call=apiService.getDrivers();
        mRecyclerView=findViewById(R.id.rvDrivers);
        mRecyclerView.setLayoutManager(new GridLayoutManager(context,1));

        SearchView searchView=(SearchView) findViewById(R.id.simpleSearchView) ;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mDriversAdapter.getFilter().filter(s);
                return false;
            }
        });

        call.enqueue(new Callback<ArrayList<Driver>>() {
            @Override
            public void onResponse(Call<ArrayList<Driver>> call, Response<ArrayList<Driver>> response) {
                ArrayList<Driver> repo_response = (ArrayList<Driver>) response.body();
                mDrivers=repo_response;
                mDriversAdapter=new DriversAdapter(context,mDrivers,MainActivity.this);
                mRecyclerView.setAdapter(mDriversAdapter);
            }
            @Override
            public void onFailure(Call<ArrayList<Driver>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage()
                        ,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void OnDriverClick(int position) {
        Intent intent=new Intent(this,DriverDetails.class);
        ApplicationContext app=(ApplicationContext) getApplicationContext();
        app.setTeam_id(mDrivers.get(position).getTeam().getId());
        app.setDriver(mDrivers.get(position));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.dev:
                builder.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}