package Service;

public class ConversionService {

    public double convert(double amount, double rate) {
        return amount * rate;
    }

    public double convertToBase(double amount, double rate) {
        return amount / rate;
    }
}