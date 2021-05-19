public class Movies {
    private String name;
    private String genre;
    private String duration;
    private String releaseDate;
    private int numberOfSeats;
    private int price;
    private String image;

    public Movies(String name, String genre, String duration, String releaseDate, int numberOfSeats, int price, String image) {
        this.name = name;
        this.genre = genre;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.numberOfSeats = numberOfSeats;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void updateDetails(){

    }
    public void updateSeats(){}


}
