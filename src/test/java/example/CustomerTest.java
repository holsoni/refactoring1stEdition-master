package example;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CustomerTest {
    private static final String MOVIE_NAME_2_DAYS = "Movie 2 days";
    private static final String MOVIE_NAME_3_DAYS = "Movie 3 days";
    private static final String MOVIE_NAME_4_DAYS = "Movie 4 days";

    @Test
    public void testCustomer_getName() {
        String name = "John";
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new Movie("Regular Movie", Movie.MovieType.REGULAR), 2));
        Customer customer = new Customer(name, rentals);
        assertEquals(name, customer.getName());
    }

    @Test
    public void testStatement_noRentals() {
        Customer customer = new Customer("John", new ArrayList<>());
        String statement = customer.getStatement();
        assertTrue(statement.contains("Rental Record for John"));
        assertTrue(statement.contains("Amount owed is 0,0"));
        assertTrue(statement.contains("You earned 0 frequent renter points"));
    }

    @Test
    public void testStatement_oneRentalOfEachMovieType() {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new Movie(MOVIE_NAME_2_DAYS, Movie.MovieType.REGULAR), 2));
        rentals.add(new Rental(new Movie(MOVIE_NAME_2_DAYS, Movie.MovieType.NEW_RELEASE), 2));
        rentals.add(new Rental(new Movie(MOVIE_NAME_2_DAYS, Movie.MovieType.CHILDRENS), 2));
        Customer customer = new Customer("Alice", rentals);
        String statement = customer.getStatement();
        assertNotNull(statement);
        assertTrue(statement.contains("Amount owed is 9,5"));
        assertTrue(statement.contains("You earned 4 frequent renter points"));
    }

    @Test
    public void testStatement_multipleRentalsOfSameMovieType() {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new Movie(MOVIE_NAME_2_DAYS, Movie.MovieType.REGULAR), 2));
        rentals.add(new Rental(new Movie(MOVIE_NAME_3_DAYS, Movie.MovieType.REGULAR), 3));
        rentals.add(new Rental(new Movie(MOVIE_NAME_4_DAYS, Movie.MovieType.REGULAR), 4));
        Customer customer = new Customer("Bob", rentals);
        String statement = customer.getStatement();
        assertNotNull(statement);
        assertTrue(statement.contains("Amount owed is 10,5"));
        assertTrue(statement.contains("You earned 3 frequent renter points"));
    }

    @Test
    public void testStatement_twoDayNewReleaseRental() {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new Movie(MOVIE_NAME_2_DAYS, Movie.MovieType.NEW_RELEASE), 2));
        Customer customer = new Customer("Bob", rentals);
        String statement = customer.getStatement();
        assertNotNull(statement);
        assertTrue(statement.contains("Amount owed is 6,0"));
        assertTrue(statement.contains("You earned 2 frequent renter points"));
    }

    @Test
    public void testStatement_rentalsExceedingFreeRentalPeriod() {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new Movie(MOVIE_NAME_4_DAYS, Movie.MovieType.REGULAR), 4));
        rentals.add(new Rental(new Movie(MOVIE_NAME_3_DAYS, Movie.MovieType.NEW_RELEASE), 3));
        rentals.add(new Rental(new Movie(MOVIE_NAME_4_DAYS, Movie.MovieType.CHILDRENS), 4));
        Customer customer = new Customer("David", rentals);
        String statement = customer.getStatement();
        assertNotNull(statement);
        assertTrue(statement.contains("Amount owed is 17,0"));
        assertTrue(statement.contains("You earned 4 frequent renter points"));
    }

    @Test
    public void testStatement_longName() {
        String longName = "Alice".repeat(1000); // Create a very long name
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new Movie(MOVIE_NAME_2_DAYS, Movie.MovieType.REGULAR), 2));
        Customer customer = new Customer(longName, rentals);
        String statement = customer.getStatement();
        assertTrue(statement.contains("Rental Record for " + longName));
    }

    @Test
    public void testStatement_negativeRentalDays() {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new Movie(MOVIE_NAME_2_DAYS, Movie.MovieType.REGULAR), -2));
        Customer customer = new Customer("Bob", rentals);
        String statement = customer.getStatement();
        assertFalse(statement.contains("Regular Movie"));
    }

    @Test
    public void testStatement_notNull() {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new Movie(MOVIE_NAME_2_DAYS, Movie.MovieType.REGULAR), 2));
        Customer customer = new Customer("Bob", rentals);
        String statement = customer.getStatement();
        assertNotNull(statement);
    }

    @Test
    public void testStatement_thousandsOfRentals() {
        List<Rental> rentals = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            rentals.add(new Rental(new Movie("Movie " + i, Movie.MovieType.REGULAR), 2));
        }
        Customer customer = new Customer("John", rentals);
        String statement = customer.getStatement();
        assertTrue(statement.contains("Amount owed is"));
        assertTrue(statement.contains("You earned"));
    }

}