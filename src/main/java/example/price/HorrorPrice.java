package example.price;

public class HorrorPrice extends Price{
    @Override
    public double getCharge(int daysRented) {
        int charge = 3;
        if (daysRented > 4)
            charge += (daysRented - 4) * 2;
        return charge;
    }

    @Override
    public int getBonusPointsPerMovieGenre(int daysRented) {
        if (daysRented > 3) {
            return 2;
        }
        return 1;
    }
}
