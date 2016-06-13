package id.or.codelabs.earthsavior.Model;

/**
 * Created by codelabsunikom on 6/13/16.
 */
public class ModelChart {
    private String namaUser;
    private String urlAvatar;
    private int point;

    public ModelChart() {
    }

    public ModelChart(String namaUser, String urlAvatar, int point) {
        this.namaUser = namaUser;
        this.urlAvatar = urlAvatar;
        this.point = point;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
