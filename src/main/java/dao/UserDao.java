package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
public class UserDao {

    private  ObjectMapper objectMapper;
    private File file;

    public void save(User user) {
       try {  List <User> users = objectMapper.readValue(file, new TypeReference<List<User>>() {}
       );
    } catch  (IOException e) {
       throw new RuntimeException(e);}
    }

    public List<User> findAll() {
        try {
            return objectMapper.readValue(file, new TypeReference<List<User>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
