public class Reservations {
    private Integer movieId;
    private int amount;

    public Reservations(Integer movieId, int amount) {
        this.movieId = movieId;
        this.amount = amount;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
