import java.util.HashMap;

/**
 * Class pour stocker les utilisateurs lors des tests de connexion
 */
public class Users {

    HashMap<String,String> usersList = new HashMap<>();

    Users(){

    }

    public void addUser(String username, String password){
        usersList.put(username,password);
    }
}
