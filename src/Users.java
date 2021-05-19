import java.util.HashMap;

public class Users {
    HashMap<String,String> usersList = new HashMap<>();

    Users(){

    }

    public void addUser(String username, String password){
        usersList.put(username,password);
    }
}
