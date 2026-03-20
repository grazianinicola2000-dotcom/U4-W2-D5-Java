package nicolagraziani.entities;

public class VideoGame extends Game {
    private Platform platform;
    private int gameDuration;
    private Type type;

    public VideoGame(String title, int yearOfProduction, double price, Platform platform, int gameDuration, Type type) {
        super(title, yearOfProduction, price);
        this.platform = platform;
        this.gameDuration = gameDuration;
        this.type = type;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public int getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(int gameDuration) {
        this.gameDuration = gameDuration;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "VideoGame{" +
                " gameId=" + getGameId() +
                ", price=" + getPrice() + "$" +
                ", title='" + getTitle() + '\'' +
                ", yearOfProduction=" + getYearOfProduction() +
                ", platform='" + platform + '\'' +
                ", gameDuration=" + gameDuration + "h" +
                ", type=" + type +
                '}';
    }
}
