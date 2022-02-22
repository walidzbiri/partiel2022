package zbiri.walid.partiel2022.api;

public class Team {
    private int position;
    private int points;
    private String season;
    private String website;

    private TeamDetails team;


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public TeamDetails getTeam() {
        return team;
    }

    public void setTeam(TeamDetails team) {
        this.team = team;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
