public class BMICalculator extends Persoon {

    public BMICalculator(double gewicht, double lengte) {
        super(gewicht, lengte);
    }

    public double berekenBMI() {
        if (gewicht > 0 && lengte > 0) {
            return gewicht / (lengte * lengte);
        } else {
            throw new IllegalArgumentException("Lengte of gewicht is ongeldig.");
        }
    }

    public void toonBMI(double bmi) {
        System.out.println("De berekende BMI is: " + bmi);
    }

    public String classificeerBMI(double bmi) {
        if (bmi < 18.5) {
            return "Ondergewicht";
        } else if (bmi < 25) {
            return "Gezond gewicht";
        } else if (bmi < 30) {
            return "Overgewicht";
        } else {
            return "Obesitas";
        }
    }
}
