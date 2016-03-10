package id.or.codelabs.earthsavior.Model;

/**
 * Created by CodeLabs on 09/03/2016.
 */
public class ModelHome {
    private String idAktifitas;
    private String tempat;
    private String tanggal;
    private int point;

    public ModelHome(String idAktifitas, String tempat, String tanggal, int point) {
        this.idAktifitas = idAktifitas;
        this.tempat = tempat;
        this.tanggal = tanggal;
        this.point = point;
    }

    public String getIdAktifitas() {
        return idAktifitas;
    }

    public void setIdAktifitas(String idAktifitas) {
        this.idAktifitas = idAktifitas;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
