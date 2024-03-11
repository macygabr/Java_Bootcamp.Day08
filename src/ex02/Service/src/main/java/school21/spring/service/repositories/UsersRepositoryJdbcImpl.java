package school21.spring.service.repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.sql.DataSource;

import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("usersRepositoryJdbc")
public class UsersRepositoryJdbcImpl implements UsersRepository{
    
    @Autowired
    @Qualifier("HikariDataSource")
    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        new CreateDataBase(dataSource);
    }

    @Override
    public <T> T findById(long id) {
        return null;
    }

    @Override
    public <T> List<T> findAll() {
        List res = new ArrayList<>();
        try {
            ResultSet resultSet = dataSource.getConnection().createStatement().executeQuery("SELECT * FROM Users");
            for(;resultSet.next();) res.add(new User(resultSet.getLong(1), resultSet.getString(2)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    @Override
    public <T> void save(T obj){
    }

    @Override
    public <T> void update(T entity){}

    @Override
    public void delete(Long id){}
    
    @Override
    public <T> Optional<T> findByEmail(String email){
        return null;
    }
}