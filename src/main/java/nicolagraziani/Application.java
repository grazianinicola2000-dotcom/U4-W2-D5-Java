package nicolagraziani;

import com.github.javafaker.Faker;
import nicolagraziani.entities.*;

import java.util.*;
import java.util.function.Supplier;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Supplier<ArrayList<Game>> gamesListGen = () -> {
            ArrayList<Game> gameList = new ArrayList<>();
            Random random = new Random();
            Faker faker = new Faker(Locale.ENGLISH);
            for (int i = 0; i < 10; i++) {
                gameList.add(new VideoGame(faker.esports().game(), random.nextInt(1990, 2026), Math.round(random.nextDouble(12.99, 100) * 100.0) / 100.0, Platform.PS5, random.nextInt(20, 100), Type.FPS));
                gameList.add(new VideoGame(faker.esports().game(), random.nextInt(1990, 2026), Math.round(random.nextDouble(12.99, 100) * 100.0) / 100.0, Platform.PC, random.nextInt(20, 100), Type.RPG));
                gameList.add(new VideoGame(faker.esports().game(), random.nextInt(1990, 2026), Math.round(random.nextDouble(12.99, 100) * 100.0) / 100., Platform.XBOX, random.nextInt(20, 100), Type.OPENWORLD));
                gameList.add(new VideoGame(faker.esports().game(), random.nextInt(1990, 2026), Math.round(random.nextDouble(12.99, 100) * 100.0) / 100.0, Platform.SWITCH, random.nextInt(20, 100), Type.STRATEGY));
                gameList.add(new BoardGame(faker.esports().game(), random.nextInt(1990, 2026), Math.round(random.nextDouble(12.99, 100) * 100.0) / 100.0, random.nextInt(2, 11), random.nextInt(15, 250)));
            }
            return gameList;
        };

        List<Game> gameList = gamesListGen.get();
        gameList.forEach(System.out::println);

        GameCollection collection = new GameCollection(gameList);

        VideoGame newVG = new VideoGame("Call of Duty", 2000, 23.54, Platform.PS5, 60, Type.FPS);
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

        //STATS OF THE COLLECTION
        collection.stats();

        System.out.println("EPIGAMES");
        while (true) {
            System.out.println("Type 'Show' to view the catalog");
            System.out.println("Type 'Exit' to exit");
            String command = scanner.nextLine().toLowerCase();
            if (command.equals("show")) {
                collection.getGames();
                while (true) {
                    System.out.println("Type: ");
                    System.out.println("'+' to add a Game");
                    System.out.println("'s_id' to find a game by Id");
                    System.out.println("'s_p' to find games under a certain price");
                    System.out.println("'n_p' to find a game based on the number of players required");
                    System.out.println("'r_id' to remove a game from the collection");
                    System.out.println("'u_id' to update game details");
                    System.out.println("'stats' to view the stats of the collection");
                    System.out.println("'back' to go back");
                    String command1 = scanner.nextLine().toLowerCase();
                    switch (command1) {
                        case "+" -> {
                            System.out.println("Type 'BG' to add a board game or 'VG' to add a videogame");
                            while (true) {
                                String command2 = scanner.nextLine().toLowerCase();
                                switch (command2) {
                                    case "bg" -> {
                                        collection.addGame(newBGByScanner());
                                        System.out.println("game successfully added");
                                    }
                                    case "vg" -> {
                                        collection.addGame(newVGByScanner());
                                        System.out.println("game successfully added");
                                    }
                                    case "back" -> {
                                        break;
                                    }
                                    default -> System.out.println("!-----Invalid Input-----!");
                                }
                                break;
                            }
                        }
                        case "s_id" -> {
                            System.out.println("Enter the ID of the game you are looking for (1, 2, 3,...)");
                            System.out.println("Type '0' to go back");
                            while (true) {
                                try {
                                    int id1 = Integer.parseInt(scanner.nextLine());

                                    if (id1 > 0) {
                                        Game g = collection.searchById(id1);
                                        if (g != null) System.out.println(g);
                                        break;
                                    } else if (id1 == 0) {
                                        break;
                                    } else {
                                        System.out.println("!-----Invalid number-----!");
                                    }

                                } catch (NumberFormatException e) {
                                    System.out.println("!-----The field needs a number-----!");
                                }

                            }
                        }
                        case "s_p" -> {
                            System.out.println("Enter the price");
                            System.out.println("Type '0' to go back");
                            while (true) {
                                try {
                                    int price = Integer.parseInt(scanner.nextLine());

                                    if (price > 0) {
                                        List<Game> g = collection.searchByPrice(price);
                                        if (g != null) {
                                            g.forEach(System.out::println);
                                            break;
                                        } else {
                                            System.out.println("There are no games under this price");
                                        }
                                    } else if (price == 0) {
                                        break;
                                    } else {
                                        System.out.println("!-----Invalid number-----!");
                                    }

                                } catch (NumberFormatException e) {
                                    System.out.println("!-----The field needs a number-----!");
                                }

                            }
                        }
                        case "n_p" -> {
                            System.out.println("Enter the number of players");
                            System.out.println("Type '0' to go back");
                            while (true) {
                                try {
                                    int players = Integer.parseInt(scanner.nextLine());

                                    if (players >= 2 && players <= 10) {
                                        List<Game> g = collection.searchedByPlayersNum(players);
                                        if (g != null) {
                                            g.forEach(System.out::println);
                                            break;
                                        } else {
                                            System.out.println("There are no games you can play =C");
                                            break;
                                        }
                                    } else if (players == 0) {
                                        break;
                                    } else {
                                        System.out.println("!-----Invalid number-----!");
                                    }

                                } catch (NumberFormatException e) {
                                    System.out.println("!-----The field needs a number-----!");
                                }

                            }
                        }
                        case "r_id" -> {
                            System.out.println("Enter the id of the game you want to remove");
                            System.out.println("Type '0' to go back");
                            while (true) {
                                try {
                                    int id = Integer.parseInt(scanner.nextLine());

                                    if (id > 0) {
                                        collection.removeById(id);
                                        break;
                                    } else if (id == 0) {
                                        break;
                                    } else {
                                        System.out.println("!-----Invalid number-----!");
                                    }

                                } catch (NumberFormatException e) {
                                    System.out.println("!-----The field needs a number-----!");
                                }

                            }
                        }
                        case "u_id" -> {
                            System.out.println("Enter the ID of the game you are looking for (1, 2, 3,...)");
                            System.out.println("Type '0' to go back");
                            while (true) {
                                try {
                                    int id = Integer.parseInt(scanner.nextLine());

                                    if (id > 0) {
                                        Game g = collection.searchById(id);
                                        if (g != null) {
                                            System.out.println(g);
                                            if (g instanceof VideoGame) {
                                                collection.updateDetailsById(id, newVGByScanner());
                                                break;
                                            } else if (g instanceof BoardGame) {
                                                collection.updateDetailsById(id, newBGByScanner());
                                                break;
                                            }
                                        }
                                    } else if (id == 0) {
                                        break;
                                    } else {
                                        System.out.println("!-----Invalid number-----!");
                                    }

                                } catch (NumberFormatException e) {
                                    System.out.println("!-----The field needs a number-----!");
                                }

                            }
                        }
                        case "stats" -> {
                            collection.stats();
                        }
                        case "back" -> {
                            break;
                        }
                        default -> System.out.println("!-----Invalid Input-----!");
                    }
                    break;
                }

            } else if (command.equals("exit")) {
                break;
            } else {
                System.out.println("!-----Invalid Command, Try again-----!");
            }
        }
    }

    public static VideoGame newVGByScanner() {
        Scanner scanner = new Scanner(System.in);
        double price = 0;
        int year = 0;
        int duration = 0;
        Type type = null;
        Platform platform = null;

        System.out.println("Enter the title");
        String title = scanner.nextLine();
        System.out.println("Enter the price");
        while (true) {
            try {
                double price1 = Double.parseDouble(scanner.nextLine());
                if (price1 > 0) {
                    price = price1;
                    break;
                } else {
                    System.out.println("!-----Invalid price-----!");
                }
            } catch (NumberFormatException e) {
                System.out.println("!-----The field needs a number-----!");
            }
        }
        System.out.println("Enter the Year of release");
        while (true) {
            try {
                int year1 = Integer.parseInt(scanner.nextLine());
                if (year1 >= 1960 && year1 <= 2026) {
                    year = year1;
                    break;
                } else if (year1 > 2026) {
                    System.out.println("!-----it is not yet possible to develop a game in the future-----!");
                } else if (year1 < 1960) {
                    System.out.println("!-----I don't think it was possible to develop a video game before 1960.-----!");
                }
            } catch (NumberFormatException e) {
                System.out.println("!-----The field needs a number-----!");
            }
        }
        System.out.println("Enter the platform (PS5, XBOX, PC, SWITCH)");
        while (true) {
            try {
                platform = Platform.valueOf(scanner.nextLine().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("!-----Unfortunately we only accept games that run on PS5, XBOX, PC, SWITCH-----!");
            }
        } //Aggiungere un Enum
        System.out.println("Enter the game duration(hour)");
        while (true) {
            try {
                int duration1 = Integer.parseInt(scanner.nextLine());
                if (duration1 <= 0) {
                    System.out.println("!-----Your game lasts 0? well... nice game... try again-----!");
                } else {
                    duration = duration1;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("!-----The field needs a number-----!");
            }
        }
        System.out.println("Enter the game genre (FPS, STRATEGY, RPG, OPENWORLD)");
        while (true) {
            try {
                type = Type.valueOf(scanner.nextLine().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("!-----Unfortunately here we only have FPS, STRATEGY, RPG or OPENWORLD-----!");
            }
        }

        return new VideoGame(title, year, price, platform, duration, type);
    }

    public static BoardGame newBGByScanner() {
        Scanner scanner = new Scanner(System.in);
        double price = 0;
        int year = 0;
        int duration = 0;
        int players = 0;

        System.out.println("Enter the title");
        String title = scanner.nextLine();
        System.out.println("Enter the price");
        while (true) {
            try {
                double price1 = Double.parseDouble(scanner.nextLine());
                if (price1 > 0) {
                    price = price1;
                    break;
                } else {
                    System.out.println("!-----Invalid price-----!");
                }
            } catch (NumberFormatException e) {
                System.out.println("!-----The field needs a number-----!");
            }
        }
        System.out.println("Enter the Year of release");
        while (true) {
            try {
                int year1 = Integer.parseInt(scanner.nextLine());
                if (year1 >= 1960 && year1 <= 2026) {
                    year = year1;
                    break;
                } else if (year1 > 2026) {
                    System.out.println("!-----it is not yet possible to develop a game in the future-----!");
                } else if (year1 < 1960) {
                    System.out.println("!-----I don't think it was possible to develop a video game before 1960.-----!");
                }
            } catch (NumberFormatException e) {
                System.out.println("!-----The field needs a number-----!");
            }
        }
        System.out.println("Enter the number of Players");
        while (true) {
            try {
                int players1 = Integer.parseInt(scanner.nextLine());
                if (players1 >= 2 && players1 <= 10) {
                    players = players1;
                    break;
                } else
                    System.out.println("!-----the number must be between 2 and 10, how many people do you want to play with?!-----");
            } catch (NumberFormatException e) {
                System.out.println("!-----The field needs a number-----!");
            }
        }
        System.out.println("Enter the game duration(min)");
        while (true) {
            try {
                int duration1 = Integer.parseInt(scanner.nextLine());
                if (duration1 <= 0) {
                    System.out.println("!-----Your game lasts 0? well... nice game... try again-----!");
                } else {
                    duration = duration1;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("!-----The field needs a number-----!");
            }
        }

        return new BoardGame(title, year, price, duration, players);
    }
}
