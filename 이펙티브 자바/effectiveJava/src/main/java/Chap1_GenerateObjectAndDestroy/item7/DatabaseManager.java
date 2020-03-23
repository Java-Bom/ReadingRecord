package Chap1_GenerateObjectAndDestroy.item7;

import Chap1_GenerateObjectAndDestroy.item7.vo.ResultSet;

public interface DatabaseManager {
    ResultSet execute(final String query);
}

