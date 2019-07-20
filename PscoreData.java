package cricketworldcup.worldcup.AdminPanel.QuizParticipantScore;

public class PscoreData {
    String score;
    String dsm;
    String name;
    String phone;
    String dsmname;
    String rsmname;
    String  smname;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDsm() {
        return dsm;
    }

    public void setDsm(String dsm) {
        this.dsm = dsm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDsmname() {
        return dsmname;
    }

    public void setDsmname(String dsmname) {
        this.dsmname = dsmname;
    }

    public String getRsmname() {
        return rsmname;
    }

    public void setRsmname(String rsmname) {
        this.rsmname = rsmname;
    }

    public String getSmname() {
        return smname;
    }

    public void setSmname(String smname) {
        this.smname = smname;
    }

    public PscoreData() {
    }

    public PscoreData(String score, String dsm, String name, String phone, String dsmname, String rsmname, String smname) {
        this.score = score;
        this.dsm = dsm;
        this.name = name;
        this.phone = phone;
        this.dsmname = dsmname;
        this.rsmname = rsmname;
        this.smname = smname;
    }
}
