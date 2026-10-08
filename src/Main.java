import java.util.*;

public class Main {

    private static final int MAX_ATTEMPTS = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<Character> lettersGuessed = new HashSet<>();
        Set<Character> lettersEntered = new HashSet<>();

        String word = getWord();

        hangman(MAX_ATTEMPTS);

        progress(word, lettersGuessed);

        checks(scanner, word, lettersGuessed, lettersEntered);

        endgame(word, lettersGuessed);
    }

    private static String getWord() {

        List<String> words = List.of(
                "Dog",
                "Bird",
                "Cat",
                "Airplane",
                "Mathematics",
                "Noble",
                "Keyboard",
                "Computer",
                "League",
                "Sports");

        Random random = new Random();

        return words.get(random.nextInt(words.size()));
    }

    private static void progress(String word, Set<Character> lettersGuessed) {

        for (int i = 0; i < word.length(); i++) {
            if (lettersGuessed.contains(Character.toLowerCase(word.charAt(i)))) {
                System.out.print(word.charAt(i) + " ");
            }
            else {
                System.out.print("_ ");
            }
        }
        System.out.println();
    }

    private static void checks(Scanner scanner, String word, Set<Character> lettersGuessed, Set<Character> lettersEntered) {

        int attempts = MAX_ATTEMPTS;

        while (attempts > 0) {

            char guess = Character.toLowerCase(scanner.nextLine().charAt(0));
            boolean letterIsGuessed = false;

            if (lettersEntered.contains(guess)) {

                System.out.println("You already used this letter!");
                continue;
            }

            lettersEntered.add(guess);

            for (int i = 0; i < word.length(); i++) {

                if  (guess == Character.toLowerCase(word.charAt(i))) {

                    letterIsGuessed = true;
                    lettersGuessed.add(guess);
                    break;
                }
            }

            if (!letterIsGuessed) {

                --attempts;

                System.out.println("Try again!");

                if  (attempts > 1 || attempts == 0) {
                    System.out.printf("You have %d more tries.\n", attempts);
                }
                else {
                    System.out.printf("You have %d more try.\n", attempts);
                }

                hangman(attempts);
            }
            else {

                System.out.println("Correct!");

                hangman(attempts);
            }

            progress(word, lettersGuessed);

            if (isWordGuessed(word, lettersGuessed)) {
                break;
            }
        }
    }

    private static boolean isWordGuessed(String word, Set<Character> lettersGuessed) {

        for (int i = 0; i < word.length(); i++) {

            if (!lettersGuessed.contains(Character.toLowerCase(word.charAt(i)))) {
                return false;
            }
        }

        return true;
    }

    private static void endgame(String word, Set<Character> lettersGuessed) {

        if (isWordGuessed(word, lettersGuessed)) {

            System.out.println("===================");
            System.out.println("|    YOU WON!     |");
            System.out.println("===================");
        }
        else {

            System.out.println("===================");
            System.out.println("|    YOU LOST!    |");
            System.out.println("===================");
        }
    }

    private static void hangman(int attempts) {

        switch (attempts) {

            case 6 -> System.out.println(
                            "                     +---+\n" +
                            "                     |   |\n" +
                            "                         |\n" +
                            "                         |\n" +
                            "                         |\n" +
                            "                         |\n" +
                            "                   =========");
            case 5 -> System.out.println(
                            "                     +---+\n" +
                            "                     |   |\n" +
                            "                     O   |\n" +
                            "                         |\n" +
                            "                         |\n" +
                            "                         |\n" +
                            "                   =========");
            case 4 -> System.out.println(
                            "                     +---+\n" +
                            "                     |   |\n" +
                            "                     O   |\n" +
                            "                     |   |\n" +
                            "                         |\n" +
                            "                         |\n" +
                            "                   =========");
            case 3 -> System.out.println(
                            "                     +---+\n" +
                            "                     |   |\n" +
                            "                     O   |\n" +
                            "                    /|   |\n" +
                            "                         |\n" +
                            "                         |\n" +
                            "                   =========");
            case 2 -> System.out.println(
                            "                     +---+\n" +
                            "                     |   |\n" +
                            "                     O   |\n" +
                            "                    /|\\  |\n" +
                            "                         |\n" +
                            "                         |\n" +
                            "                   =========");
            case 1 -> System.out.println(
                            "                     +---+\n" +
                            "                     |   |\n" +
                            "                     O   |\n" +
                            "                    /|\\  |\n" +
                            "                    /    |\n" +
                            "                         |\n" +
                            "                   =========");
            case 0 -> System.out.println(
                            "                     +---+\n" +
                            "                     |   |\n" +
                            "                     O   |\n" +
                            "                    /|\\  |\n" +
                            "                    / \\  |\n" +
                            "                         |\n" +
                            "                   =========");
        }
    }
}