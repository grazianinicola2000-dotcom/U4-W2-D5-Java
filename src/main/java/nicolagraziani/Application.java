package nicolagraziani;

import com.github.javafaker.Faker;
import nicolagraziani.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.function.Supplier;

public class Application {

    public static void main(String[] args) {

        Supplier<ArrayList<Game>> gamesListGen = () -> {
            ArrayList<Game> gameList = new ArrayList<>();
            Random random = new Random();
            Faker faker = new Faker(Locale.ENGLISH);
            for (int i = 0; i < 10; i++) {
                gameList.add(new VideoGame(faker.esports().game(), random.nextInt(1990, 2026), Math.round(random.nextDouble(12.99, 100) * 100.0) / 100.0, "PS5", random.nextInt(20, 100), Type.FPS));
                gameList.add(new VideoGame(faker.esports().game(), random.nextInt(1990, 2026), Math.round(random.nextDouble(12.99, 100) * 100.0) / 100.0, "PC", random.nextInt(20, 100), Type.RPG));
                gameList.add(new VideoGame(faker.esports().game(), random.nextInt(1990, 2026), Math.round(random.nextDouble(12.99, 100) * 100.0) / 100.0, "XBOX", random.nextInt(20, 100), Type.OPENWORLD));
                gameList.add(new VideoGame(faker.esports().game(), random.nextInt(1990, 2026), Math.round(random.nextDouble(12.99, 100) * 100.0) / 100.0, "SWITCH", random.nextInt(20, 100), Type.STRATEGY));
                gameList.add(new BoardGame(faker.esports().game(), random.nextInt(1990, 2026), Math.round(random.nextDouble(12.99, 100) * 100.0) / 100.0, random.nextInt(2, 11), random.nextInt(15, 250)));
            }
            return gameList;
        };

        List<Game> gameList = gamesListGen.get();
        gameList.forEach(System.out::println);

        GameCollection collection = new GameCollection(gameList);

        VideoGame newVG = new VideoGame("Call of Duty", 2000, 23.54, "PS5", 60, Type.FPS);
        BoardGame newBG = new BoardGame("Il gioco dell'oca", 1990, 20.99, 4, 60);

        // ADD GAME

        collection.addGame(newVG);
        collection.addGame(newBG);
        collection.getGames();

        // FIND BY ID
        System.out.println("GAME SELECTED BY ID");
        Game gameById = collection.searchById(51); // NullPointerException
        System.out.println(gameById.toString());

        // FIND BY PRICE
        System.out.println("GAMES SELECTED BY PRICE");
        List<Game> gamesByPrice = collection.searchByPrice(45); // NullPointerException
        gamesByPrice.forEach(System.out::println);

        // FIND BY NUMBER OF PLAYERS
        System.out.println("GAMES SELECTED BY NUMBER OF PLAYERS");
        List<Game> gameByNumOfPlayers = collection.searchedByPlayersNum(4);
        gameByNumOfPlayers.forEach(System.out::println);

        // REMOVE BY ID
        System.out.println("UPDATED GAMES LIST");
        collection.removeById(52);
        collection.getGames();

        // UPDATE DETAILS BY ID
        collection.updateDetailsById(49, newVG);
        System.out.println(collection.searchById(49));
        collection.updateDetailsById(48, newBG);
        System.out.println(collection.searchById(48));
        System.out.println("-------------List---------------");
        collection.getGames();

    }
}
