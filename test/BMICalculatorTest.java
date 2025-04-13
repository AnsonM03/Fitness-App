public class BMICalculatorTest {

    public static void main(String[] args) {
        testBerekenBMI();
        testClassificeerBMI();
    }

    public static void testBerekenBMI() {
        BMICalculator calculator = new BMICalculator(70, 1.75);
        double bmi = calculator.berekenBMI();
        boolean result = Math.abs(bmi - 22.86) < 0.1;

        System.out.println("Test berekenBMI: " + (result ? "GESLAAGD" : "GEFAALD"));
    }

    public static void testClassificeerBMI() {
        BMICalculator calculator = new BMICalculator(70, 1.75);
        String classificatie = calculator.classificeerBMI(22.86);

        boolean result = classificatie.equals("Gezond gewicht");
        System.out.println("Test classificeerBMI: " + (result ? "GESLAAGD" : "GEFAALD"));
    }
}
