package Chap2_GenerateObjectAndDestroy.item7;

import Chap2_GenerateObjectAndDestroy.item7.vo.ResultSet;

public interface DatabaseManager {
    ResultSet execute(final String query);
}

