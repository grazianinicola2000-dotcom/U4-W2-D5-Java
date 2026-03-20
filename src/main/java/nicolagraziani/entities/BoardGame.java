package nicolagraziani.entities;

public class BoardGame extends Game {
    private int numberOfPlayers;
    private int averageDuration;


    public BoardGame(String title, int yearOfProduction, double price, int numberOfPlayers, int averageDuration) {
        super(title, yearOfProduction, price);
        this.numberOfPlayers = numberOfPlayers;
        this.averageDuration = averageDuration;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getAverageDuration() {
        return averageDuration;
    }

    public void setAverageDuration(int averageDuration) {
        this.averageDuration = averageDuration;
    }

    @Override
    public String toString() {
        return "BoardGame{" +
                " gameId=" + getGameId() +
                ", price=" + getPrice() + "$" +
                ", title='" + getTitle() + '\'' +
                ", yearOfProduction=" + getYearOfProduction() +
                ", numberOfPlayers=" + numberOfPlayers +
                ", averageDuration=" + averageDuration + "min" +
                '}';
    }
}
