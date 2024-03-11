package school21.spring.service.repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.sql.DataSource;

import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

public class UsersRepositoryJdbcImpl implements UsersRepository{
    
    private final DataSource dataSource;
    private Set<Class<?>> classes;

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
        if(obj == null) return;
        if (!obj.getClass().isAnnotationPresent(OrmEntity.class)) return;

        try {
            for(Class<?> OneClass: classes) {
                StringBuilder uQuery = new StringBuilder("INSERT INTO " + OneClass.getAnnotation(OrmEntity.class).table() + " VALUES(");
                if(obj.getClass() != OneClass) continue;
    
                Field[] fields = OneClass.getDeclaredFields();
                for(int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true);
                    if (fields[i].isAnnotationPresent(OrmColumnId.class)) {
                        for(String column : columns) if(fields[i].getName().equals(column)) uQuery.append(fields[i].get(obj).toString());
                    } else if(fields[i].isAnnotationPresent(OrmColumn.class)){
                        for(String column : columns) 
                            if(fields[i].getName().equals(column)) {
                                if(fields[i].getType().getSimpleName().equals("String")) uQuery.append("'" + fields[i].get(obj).toString()+"'");
                                else uQuery.append(fields[i].get(obj).toString());
                            }
                    }
                    if(i < fields.length-1) uQuery.append(", ");
                    else uQuery.append(");");
                }
                // System.out.println( "\033[31m" + uQuery + "\033[0m");
                executeUpdate(uQuery.toString());
            }
            
        } catch (Exception e) {
            throw new RuntimeException("\033[31m"+e.getMessage()+"\033[0m");
        }

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