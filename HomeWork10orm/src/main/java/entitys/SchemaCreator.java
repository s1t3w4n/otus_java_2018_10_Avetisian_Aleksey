package entitys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;

public class SchemaCreator {
    private static Logger logger = LoggerFactory.getLogger(SchemaCreator.class);

    public static void createUserTable(DataSource dataSource) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pst = connection.prepareStatement(
                     "create table user(id bigint(20) NOT NULL auto_increment, name varchar(255), age int(3))")) {
            pst.executeUpdate();
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet rs = metaData.getColumns(null, null, "USER", null);

            StringBuilder columns = new StringBuilder("Columns:");
            while (rs.next()) {
                columns.append(" ").append(rs.getString(4)).append(";");
            }
            logger.info(columns.toString());
        }
        logger.info("SchemaCreator created");
    }
}
