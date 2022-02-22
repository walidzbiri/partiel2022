package zbiri.walid.partiel2022;

import android.app.Application;

import zbiri.walid.partiel2022.api.Driver;

public class ApplicationContext extends Application {
    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    private int team_id;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    private Driver driver;

    @Override
    public void onCreate(){
        super.onCreate();
    }
}
