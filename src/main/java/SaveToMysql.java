import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class SaveToMysql {

    private static QueryRunner queryRunner= new QueryRunner(JDBCUtils.getDataSource());

    public static int SaveData(ItemUnit itemUnit) {
        String sql = "INSERT INTO jd_item(address,services,num) VALUES (?,?,?)";
        Object[] param = {itemUnit.getPosition(),itemUnit.getServicesInString(),itemUnit.getServiceNum()};
        int line = 0;
        try {
            line = queryRunner.update(sql,param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return line;
    }


}
