package Chap10_Exceptions.item71;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LolClient {

    private Map<String, UserInfo> map = new HashMap<>();

    public UserInfo readUserWithException(String fileName) throws IOException {
        if (map.containsKey(fileName)) {
            return map.get(fileName);
        }
        throw new IOException();
    }

    public Optional<UserInfo> readUserInfoWithOptional(String fileName) throws IOException {
        if (map.containsKey(fileName)) {
            return Optional.of(map.get(fileName));
        }
        return Optional.empty();
    }

}
