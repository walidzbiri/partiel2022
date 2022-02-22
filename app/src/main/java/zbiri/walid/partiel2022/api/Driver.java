package zbiri.walid.partiel2022.api;

public class Driver {
    private int position;
    private String points;
    private DriverDetails driver;
    private TeamDetails team;


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public DriverDetails getDriver() {
        return driver;
    }

    public void setDriver(DriverDetails driver) {
        this.driver = driver;
    }

    public TeamDetails getTeam() {
        return team;
    }

    public void setTeam(DriverDetails driver) {
        this.team = team;
    }
}
