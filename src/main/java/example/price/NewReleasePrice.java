package example.price;

import static example.Movie.MovieType.NEW_RELEASE;

public class NewReleasePrice extends Price {
    @Override
    public double getCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int getBonusPointsPerMovieGenre(int daysRented) {
        if (daysRented > 1) {
            return 2;
        }
        return 1;
    }
}
