public class BMICalculator {

    // Methode om BMI te berekenen op basis van ProgressTracker
    public double berekenBMI(ProgressTracker tracker) {
        Double laatsteGewicht = tracker.getLaatsteGewicht();
        Double laatsteLengte = tracker.getLaatsteLengte();

        if (laatsteGewicht != null && laatsteLengte != null && laatsteLengte > 0) {
            return laatsteGewicht / (laatsteLengte * laatsteLengte); // Bereken BMI
        } else {
            throw new IllegalArgumentException("Ongeldige gegevens: Gewicht of lengte ontbreekt.");
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
