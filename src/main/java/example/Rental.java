package example;

import static example.Movie.MovieType.NEW_RELEASE;

class Rental {
    private final Movie movie;
    private final int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public double getCharge() {
        double charge = 0;
        switch (getMovie().getPriceCode()) {
            case REGULAR -> {
                charge += 2;
                if (getDaysRented() > 2)
                    charge += (getDaysRented() - 2) * 1.5;
            }
            case NEW_RELEASE -> charge += getDaysRented() * 3;
            case CHILDRENS -> {
                charge += 1.5;
                if (getDaysRented() > 3)
                    charge += (getDaysRented() - 3) * 1.5;
            }
            default -> throw new IllegalStateException("Unexpected value: " + getMovie().getPriceCode());
        }
        return charge;
    }

    public int getBonusPointsForMovie() {
        int bonusPoint = 0;
        bonusPoint++;
        if ((getMovie().getPriceCode() == NEW_RELEASE) && getDaysRented() > 1)
            bonusPoint++;
        return bonusPoint;
    }
}
