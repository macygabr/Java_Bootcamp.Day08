package school21.spring.service.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("usersRepositoryJdbcTemplate")
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository{
    
    @Autowired
    @Qualifier("SpringDataSource")
    private final DataSource dataSource;
    
    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        new CreateDataBase(dataSource);
    }

    @Override
    public <T> T findById(long id) {
        return null;
    }

    @Override
    public <T> List<T> findAll() {
        return null;
    }

    @Override
    public <T> void save(T entity){}

    @Override
    public <T> void update(T entity){}

    @Override
    public void delete(Long id){}
    
    @Override
    public <T> Optional<T> findByEmail(String email){
        return null;
    }
}