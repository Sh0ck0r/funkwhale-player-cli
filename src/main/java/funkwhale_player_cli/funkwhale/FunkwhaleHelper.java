package funkwhale_player_cli.funkwhale;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

public class FunkwhaleHelper {

    private FunkwhaleHelper() {}

    @NotNull
    @Contract("_ -> new")
    public static JSONObject formatStringifyResponseToJson(String response) {
        return new JSONObject(response);
    }
}
