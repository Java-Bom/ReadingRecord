package item7;

import item7.vo.ResultSet;

public interface DatabaseManager {
    ResultSet execute(final String query);
}

