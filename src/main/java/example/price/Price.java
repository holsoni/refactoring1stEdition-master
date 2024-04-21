package example.price;

import example.Movie;

public abstract class Price {
    private Movie.MovieType priceCode;

    public abstract double getCharge(int daysRented);
    public int getBonusPointsPerMovieGenre(int daysRented) {
        return 1;
    }
}
