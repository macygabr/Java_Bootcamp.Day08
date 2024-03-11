package school21.spring.service.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class CreateDataBase {
    private DataSource dataSource;
    public CreateDataBase(DataSource dataSource) {
        this.dataSource = dataSource;
        try {
            dataSource.getConnection().createStatement().executeUpdate("DROP TABLE IF EXISTS Users");
            dataSource.getConnection().createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Users ( id INTEGER, email TEXT);");
            Insert();
            dataSource.getConnection().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void Insert() throws SQLException {
        Connection connect = dataSource.getConnection();
        PreparedStatement statement = connect.prepareStatement("INSERT INTO Users (id, email) VALUES (?, ?);");
        
        for(int i = 1; i<=10; i++){
            statement.setInt(1, i);
            statement.setString(2, "test");
            statement.executeUpdate();
        }
    }
}