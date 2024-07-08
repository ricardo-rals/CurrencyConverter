public class ExchangeRates {
    private String base_code;
    private ConversionRates conversion_rates;

    public String getBase_code() {
        return base_code;
    }

    public void setBase_code(String base_code) {
        this.base_code = base_code;
    }

    public double getARS() {
        return conversion_rates.ARS;
    }

    public void setARS(double ARS) {
        this.conversion_rates.ARS = ARS;
    }

    public double getBOB() {
        return conversion_rates.BOB;
    }

    public void setBOB(double BOB) {
        this.conversion_rates.BOB = BOB;
    }

    public double getBRL() {
        return conversion_rates.BRL;
    }

    public void setBRL(double BRL) {
        this.conversion_rates.BRL = BRL;
    }

    public double getCLP() {
        return conversion_rates.CLP;
    }

    public void setCLP(double CLP) {
        this.conversion_rates.CLP = CLP;
    }

    public double getCOP() {
        return conversion_rates.COP;
    }

    public void setCOP(double COP) {
        this.conversion_rates.COP = COP;
    }

    static class ConversionRates {
        private double ARS;
        private double BOB;
        private double BRL;
        private double CLP;
        private double COP;
    }
}
