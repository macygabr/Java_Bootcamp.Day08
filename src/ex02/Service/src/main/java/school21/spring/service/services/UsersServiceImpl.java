package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

@Component("usersService")
public class UsersServiceImpl implements UsersService {
    @Autowired
    @Qualifier("usersRepositoryJdbc")
    private UsersRepository usersRepository;

    public UsersServiceImpl(){}

    @Override
    public String signUp(String email) {
        if (!usersRepository.findByEmail(email).isPresent()) return null;
        String passwd = generatePassword();
        usersRepository.save(new User(((User)usersRepository.findByEmail(email).get()).getId(), email, passwd));
        return passwd;
    }

    private String generatePassword(){
        char[] pass = new char[10];
        for (int i = 0; i < pass.length; i++){
            char newChar;
            if ((int)(Math.random() * 100) % 10 < 3) newChar = (char)('A' + (int)(Math.random() * 26));
            else if ((int)(Math.random() * 100) % 10 >= 3 && (int)(Math.random() * 100) % 10 <= 6) newChar = (char)('0' + (int)(Math.random() * 10));
            else newChar = (char)('a' + (int)(Math.random() * 26));
            pass[i] = newChar;
        }
        return new String(pass);
    }
}