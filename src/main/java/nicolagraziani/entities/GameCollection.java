package nicolagraziani.entities;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;

public class GameCollection {
    private List<Game> games = new ArrayList<>();

    public GameCollection(List<Game> gameList) {
        this.games = gameList;
    }

    public void getGames() {
        games.forEach(game -> System.out.println(game.toString()));
    }

    public void addGame(Game game) {
        if (games.stream().anyMatch(game1 -> game1.getGameId() == game.getGameId())) {
            System.out.println("!------ID already present in the collection------!");
        } else games.add(game);
    }

    public Game searchById(int id) {
        Optional<Game> result = games.stream().filter(game -> game.getGameId() == id).findFirst();
        if (result.isPresent()) {
            return result.get();
        } else {
            System.out.println("!------ID not found------!");
            return null;
        }
    }

    public List<Game> searchByPrice(double price) {
        List<Game> result = games.stream().filter(game -> game.getPrice() < price).toList();
        if (result.isEmpty()) {
            System.out.println("!------No games found under this price------!");
            return null;
        } else {
            return result;
        }
    }

    public List<Game> searchedByPlayersNum(int player) {
        if (player < 2 || player > 10) System.out.println("!------Players must be between 2 and 10------!");
        return games.stream().filter(game -> game instanceof BoardGame && ((BoardGame) game).getNumberOfPlayers() == player).toList();
    }

    public void removeById(int id) {
        Optional<Game> selected = games.stream().filter(game -> game.getGameId() == id).findFirst();
        if (selected.isPresent()) {
            games.remove(selected.get());
            System.out.println("The game:");
            System.out.println(selected.get());
            System.out.println("Has been removed");
        } else {
            System.out.println("!------ID not found------!");
        }
    }

    public void updateDetailsById(int id, Game game) {
        Optional<Game> selected = games.stream().filter(game1 -> game1.getGameId() == id).findFirst();
        if (selected.isPresent()) {
            selected.get().setTitle(game.getTitle());
            selected.get().setPrice(game.getPrice());
            selected.get().setYearOfProduction(game.getYearOfProduction());
            if (selected.get() instanceof VideoGame && game instanceof VideoGame) {
                ((VideoGame) selected.get()).setPlatform(((VideoGame) game).getPlatform());
                ((VideoGame) selected.get()).setGameDuration(((VideoGame) game).getGameDuration());
            } else if (selected.get() instanceof BoardGame && game instanceof BoardGame) {
                ((BoardGame) selected.get()).setNumberOfPlayers(((BoardGame) game).getNumberOfPlayers());
                ((BoardGame) selected.get()).setAverageDuration(((BoardGame) game).getAverageDuration());
            }
            System.out.println("GAME SUCCESSFULLY UPDATED");
        } else {
            System.out.println("!------ID not found------!");
        }
    }

    public void stats() {
        System.out.println("STATS COLLECTION:");
        List<Game> numOfVideoGames = games.stream().filter(game -> game instanceof VideoGame).toList();
        System.out.println("The number of video games in the list is: " + numOfVideoGames.size());
        List<Game> numOfBoardGames = games.stream().filter(game -> game instanceof BoardGame).toList();
        System.out.println("The number of board games in the list is: " + numOfBoardGames.size());
        DoubleSummaryStatistics stats = games.stream().mapToDouble(Game::getPrice).summaryStatistics();
        System.out.println("The game with the highest price is: " + stats.getMax());
        System.out.println("The average game prices is: " + stats.getAverage());

    }

}
