package id.or.codelabs.earthsavior.Model;

/**
 * Created by CodeLabs on 10/03/2016.
 */
public class ModelBadges {
    private int imgBadges;
    private String nameReward;

    public ModelBadges(int imgBadges, String nameReward) {
        this.imgBadges = imgBadges;
        this.nameReward = nameReward;
    }

    public int getImgBadges() {
        return imgBadges;
    }

    public void setImgBadges(int imgBadges) {
        this.imgBadges = imgBadges;
    }

    public String getNameReward() {
        return nameReward;
    }

    public void setNameReward(String nameReward) {
        this.nameReward = nameReward;
    }


}
