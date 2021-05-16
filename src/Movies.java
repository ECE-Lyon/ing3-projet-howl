public class Movies {
    private String name;
    private String genre;
    private int duration;
    private String releaseDate;
    private int numberOfSeats;
    private int price;

    public Movies(String name, String genre, int duration, String releaseDate, int numberOfSeats, int price) {
        this.name = name;
        this.genre = genre;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.numberOfSeats = numberOfSeats;
        this.price = price;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    public void updateDetails(){

    }
    public void updateSeats(){}


}
