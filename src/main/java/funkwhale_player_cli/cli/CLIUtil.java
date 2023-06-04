package funkwhale_player_cli.cli;

public class CLIUtil {

    public static final String YELLOW = "\033[0;33m";
    public static final String RESET = "\033[0m";

    private CLIUtil() {}

    public static String[] parseInput(String input) {
        if (input.contains(" ")) {
            String prefix = input.substring(0, input.indexOf(" "));
            input = input.substring(input.indexOf(" ") + 1); // remove prefix
            String[] args = input.split(" ");
            String[] result = new String[args.length + 1]; // +1 for prefix
            result[0] = prefix;
            for (int i = 0; i < args.length; i++) {
                // Process the arguments to remove the spaces
                args[i] = args[i].trim();
                // Add the arguments to the result array
                result[i + 1] = args[i];
            }
            return result;
        }
        return new String[]{input};
    }
}
