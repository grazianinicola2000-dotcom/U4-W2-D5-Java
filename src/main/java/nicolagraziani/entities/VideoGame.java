package nicolagraziani.entities;

public class VideoGame extends Game {
    private String platform;
    private int gameDuration;
    private Type type;

    public VideoGame(String title, int yearOfProduction, double price, String platform, int gameDuration, Type type) {
        super(title, yearOfProduction, price);
        this.platform = platform;
        this.gameDuration = gameDuration;
        this.type = type;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setGameDuration(int gameDuration) {
        this.gameDuration = gameDuration;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
