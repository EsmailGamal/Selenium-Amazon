package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Map;

public class UserDataReader {
    private Map<String, Map<String, String>> userData;

    public UserDataReader(String filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        userData = mapper.readValue(new File(filePath), Map.class); // Adjusted to match the JSON structure
    }

    public Map<String, String> getUser(String userKey) {
        return userData.get(userKey); // Directly fetch the user object
    }
}
