package cricketworldcup.worldcup.AdminPanel.QuizParticipantDetails;

public class Pdata {
    String matchno;
    String dsmcode;
    String doctorname;
    String dsmname;
    String smname;
    String rsmname;

    public String getMatchno() {
        return matchno;
    }

    public void setMatchno(String matchno) {
        this.matchno = matchno;
    }

    public String getDsmcode() {
        return dsmcode;
    }

    public void setDsmcode(String dsmcode) {
        this.dsmcode = dsmcode;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getDsmname() {
        return dsmname;
    }

    public void setDsmname(String dsmname) {
        this.dsmname = dsmname;
    }

    public String getSmname() {
        return smname;
    }

    public void setSmname(String smname) {
        this.smname = smname;
    }

    public String getRsmname() {
        return rsmname;
    }

    public void setRsmname(String rsmname) {
        this.rsmname = rsmname;
    }

    public Pdata() {
    }

    public Pdata(String matchno, String dsmcode, String doctorname, String dsmname, String smname, String rsmname) {
        this.matchno = matchno;
        this.dsmcode = dsmcode;
        this.doctorname = doctorname;
        this.dsmname = dsmname;
        this.smname = smname;
        this.rsmname = rsmname;
    }
}
