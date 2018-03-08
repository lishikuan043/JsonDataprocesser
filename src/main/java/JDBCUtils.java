import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

/**
 * Created by hust on 2018/2/19.
 */
public class JDBCUtils {
    public static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/jd_service";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "043043";

    private static BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
