import org.json.JSONException;
import org.json.JSONObject;

public class TestJson {
    public static void main (String args[]) throws JSONException {
        JSONObject jsonObject;
        jsonObject = new JSONObject(args[0]);
// To string method prints it with specified indentation
        System.out.println(jsonObject.toString(4));
    }
}
