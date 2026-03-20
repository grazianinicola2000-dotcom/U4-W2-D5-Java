package nicolagraziani.entities;

public abstract class Game {
    private static int idSelector = 1;
    private int gameId;
    private double price;
    private String title;
    private int yearOfProduction; //Avevo messo LocalDate, ma mi complicava la vita per generare i giochi.

    public Game(String title, int yearOfProduction, double price) {
        this.gameId = idSelector++; //Ho optato per creare un "counter" static cosi incrementandolo ogni volta che uso il costruttore
        // mi restituisce sempre un numero diverso (eliminando la possibilità di avere Id uguali)
        this.title = title;
        this.yearOfProduction = yearOfProduction;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public int getGameId() {
        return gameId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
