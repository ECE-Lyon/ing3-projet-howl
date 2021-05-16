public class Members extends Customers {
    private int id;
    private String username;
    private String password;
    private enum type{
        REGULAR,
        SENIOR,
        CHILDREN
    };

    public Members(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login() {

    }
    public void logOut(){

    }
    public void bookTickets(type discounts){

    }

    @Override
    public void browseMovies(){};
    public void bookTickets(){};
    public void makePayement(){};

}
