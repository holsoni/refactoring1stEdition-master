package example;

import java.util.List;

@SuppressWarnings("StringConcatenationInLoop")
class Customer {
    private static final String RENTAL_HEADING = "Rental Record for %s \n";
    private static final String AMOUNT_OWED_STRING = "Amount owed is %f\n";
    private static final String AMOUNT_FREQUENT_POINTS_STRING = "You earned %d frequent renter points";
    private static final String TABULATION = "\t";

    private final String name;
    private final List<Rental> rentals;

    public Customer(String name, List<Rental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }


    public String getName() {
        return name;
    }

    public String getStatement() {
        String statement = String.format(RENTAL_HEADING, getName());
        statement = getString(statement);
        statement += String.format(AMOUNT_OWED_STRING, getTotalCharge());
        statement += String.format(AMOUNT_FREQUENT_POINTS_STRING, getTotalBonusPoints());
        return statement;
    }

    private String getString(String result) {
        for (Rental rental : rentals) {
            result += TABULATION + rental.getMovie().getTitle()+ TABULATION + rental.getCharge() + TABULATION;
        }
        return result;
    }

    private double getTotalCharge() {
        return rentals.stream().mapToDouble(Rental::getCharge).sum();
    }

    private int getTotalBonusPoints() {
        return rentals.stream().mapToInt(Rental::getBonusPoint).sum();
    }


}
