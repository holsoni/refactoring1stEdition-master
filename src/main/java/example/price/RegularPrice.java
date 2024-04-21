package example.price;

public class RegularPrice extends Price{
    @Override
    public double getCharge(int daysRented) {
        int charge = 2;
        if (daysRented > 2)
            charge += (daysRented - 2) * 1.5;
        return charge;
    }
}
