//Deegan Hansen
//for software class
//big project
//solitaire game code

import java.util.*;

public class Solitaire {
    private List<Card> deck;
    private List<List<Card>> tableau;
    private Map<String, List<Card>> foundations;
    private List<Card> stock;
    private List<Card> waste;
    private Scanner scanner;

    static class Card {
        String rank;
        String suit;

        Card(String rank, String suit) {
            this.rank = rank;
            this.suit = suit;
        }

        @Override
        public String toString() {
            return rank + suit;
        }
    }

    public Solitaire() {
        deck = new ArrayList<>();
        tableau = new ArrayList<>();
        foundations = new HashMap<>();
        stock = new ArrayList<>();
        waste = new ArrayList<>();
        scanner = new Scanner(System.in);

        for (int i = 0; i < 7; i++) {
            tableau.add(new ArrayList<>());
        }

        foundations.put("♠", new ArrayList<>());
        foundations.put("♥", new ArrayList<>());
        foundations.put("♦", new ArrayList<>());
        foundations.put("♣", new ArrayList<>());

        initDeck();
        shuffleAndDeal();
    }

    private void initDeck() {
        String[] suits = {"♠", "♥", "♦", "♣"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(new Card(rank, suit));
            }
        }
    }

    private void shuffleAndDeal() {
        Collections.shuffle(deck);

        for (int i = 0; i < 7; i++) {
            for (int j = i; j < 7; j++) {
                tableau.get(j).add(deck.remove(deck.size() - 1));
            }
        }

        stock.addAll(deck);
        deck.clear();
    }

    private int getRankValue(String rank) {
        switch (rank) {
            case "A": return 1;
            case "J": return 11;
            case "Q": return 12;
            case "K": return 13;
            default: return Integer.parseInt(rank);
        }
    }

    private void display() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("Stock: " + stock.size() + " | Waste: " + formatPile(waste) + 
                         " | Foundations: " + formatFoundations());
        System.out.println("=".repeat(80));
        System.out.println("\nTableau:");

        int maxHeight = tableau.stream().mapToInt(List::size).max().orElse(0);
        for (int row = 0; row < maxHeight; row++) {
            for (int col = 0; col < 7; col++) {
                List<Card> pile = tableau.get(col);
                if (row < pile.size()) {
                    System.out.printf("[%3s] ", pile.get(row));
                } else {
                    System.out.print("      ");
                }
            }
            System.out.println();
        }
        System.out.println("\nColumns: 1  2  3  4  5  6  7");
    }

    private String formatPile(List<Card> pile) {
        return pile.isEmpty() ? "   " : String.format("[%3s]", pile.get(pile.size() - 1));
    }

    private String formatFoundations() {
        StringBuilder sb = new StringBuilder();
        for (String suit : new String[]{"♠", "♥", "♦", "♣"}) {
            List<Card> foundation = foundations.get(suit);
            if (foundation.isEmpty()) {
                sb.append("[A?] ");
            } else {
                sb.append(String.format("[%3s] ", foundation.get(foundation.size() - 1)));
            }
        }
        return sb.toString();
    }

    private void drawFromStock() {
        if (!stock.isEmpty()) {
            waste.add(stock.remove(stock.size() - 1));
            System.out.println("Drew: " + waste.get(waste.size() - 1));
        } else if (!waste.isEmpty()) {
            Collections.reverse(waste);
            stock.addAll(waste);
            waste.clear();
            System.out.println("Stock recycled!");
        } else {
            System.out.println("Stock is empty!");
        }
    }

    private boolean moveToFoundation(String sourceType, int sourceIdx) {
        List<Card> source;

        if ("waste".equals(sourceType)) {
            source = waste;
        } else {
            source = tableau.get(sourceIdx);
        }

        if (source.isEmpty()) {
            System.out.println("Source is empty!");
            return false;
        }

        Card card = source.get(source.size() - 1);
        List<Card> foundation = foundations.get(card.suit);
        int expectedRank = foundation.isEmpty() ? 1 : getRankValue(foundation.get(foundation.size() - 1).rank) + 1;

        if (getRankValue(card.rank) == expectedRank) {
            foundation.add(source.remove(source.size() - 1));
            System.out.println("Moved " + card + " to foundation!");
            return true;
        } else {
            System.out.println("Cannot move to foundation!");
            return false;
        }
    }

    private boolean moveTableauCard(int fromCol, int toCol) {
        List<Card> fromPile = tableau.get(fromCol);
        List<Card> toPile = tableau.get(toCol);

        if (fromPile.isEmpty()) {
            System.out.println("Source column is empty!");
            return false;
        }

        Card fromCard = fromPile.get(fromPile.size() - 1);

        if (toPile.isEmpty()) {
            if (getRankValue(fromCard.rank) == 13) {
                toPile.add(fromPile.remove(fromPile.size() - 1));
                System.out.println("Moved King to empty column!");
                return true;
            } else {
                System.out.println("Only Kings can move to empty columns!");
                return false;
            }
        }

        Card toCard = toPile.get(toPile.size() - 1);

        if (canPlaceOn(fromCard, toCard)) {
            toPile.add(fromPile.remove(fromPile.size() - 1));
            System.out.println("Moved " + fromCard + " to column " + (toCol + 1) + "!");
            return true;
        } else {
            System.out.println("Invalid move!");
            return false;
        }
    }

    private boolean canPlaceOn(Card card, Card target) {
        int rankDiff = getRankValue(target.rank) - getRankValue(card.rank);
        boolean fromBlack = card.suit.equals("♠") || card.suit.equals("♣");
        boolean toBlack = target.suit.equals("♠") || target.suit.equals("♣");
        return rankDiff == 1 && fromBlack != toBlack;
    }

    private boolean isWon() {
        return foundations.values().stream().allMatch(f -> f.size() == 13);
    }

    public void play() {
        System.out.println("Welcome to Solitaire!");

        while (true) {
            display();

            if (isWon()) {
                System.out.println("\n🎉 You won! 🎉");
                break;
            }

            System.out.print("\nCommands: (d)raw, (m)ove, (f)oundation, (q)uit: ");
            String cmd = scanner.nextLine().trim().toLowerCase();

            switch (cmd) {
                case "d":
                    drawFromStock();
                    break;
                case "m":
                    try {
                        System.out.print("From column (1-7): ");
                        int fromCol = Integer.parseInt(scanner.nextLine()) - 1;
                        System.out.print("To column (1-7): ");
                        int toCol = Integer.parseInt(scanner.nextLine()) - 1;

                        if (fromCol >= 0 && fromCol < 7 && toCol >= 0 && toCol < 7) {
                            moveTableauCard(fromCol, toCol);
                        } else {
                            System.out.println("Invalid column numbers!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input!");
                    }
                    break;
                case "f":
                    moveToFoundation("waste", 0);
                    break;
                case "q":
                    System.out.println("Thanks for playing!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Unknown command!");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("♠");
        Solitaire game = new Solitaire();
        game.play();
    }
}
