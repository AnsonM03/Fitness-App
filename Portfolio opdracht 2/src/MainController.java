import java.util.List;
import java.util.Scanner;

public class MainController {
    private Scanner scanner = new Scanner(System.in);
    private ProgressTracker tracker = new ProgressTracker();
    private WorkoutPlanner planner = new WorkoutPlanner();
    private CalorieTracker calorieTracker;
    private boolean running = true;
    private Persoon gebruiker;

    private static final String[] MENU_OPTIES = {
            "Voeg gewicht en lengte toe",
            "Bekijk gebruikersgegevens",
            "Toon voortgangsgrafiek",
            "Selecteer trainingsdoel en genereer workoutplan",
            "Bereken BMI",
            "Beheer dagboek",
            "Afsluiten"
    };

    public MainController() {
        gebruiker = new Persoon(1,"Anson", 60, 1.62);
        calorieTracker = new CalorieTracker(gebruiker);
    }

    public void start() {
        while (running) {
            toonMenu();
            System.out.print("Maak een keuze (1-7): ");
            String input = scanner.nextLine();
            try {
                int keuze = Integer.parseInt(input);
                verwerkKeuze(keuze);
            } catch (NumberFormatException e) {
                System.out.println("Ongeldige invoer. Probeer opnieuw.");
            }
        }
    }

    private void toonMenu() {
        System.out.println("\n--- FitFlow Menu ---");
        for (int i = 0; i < MENU_OPTIES.length; i++) {
            System.out.println((i + 1) + ". " + MENU_OPTIES[i]);
        }
    }

    private void verwerkKeuze(int keuze) {
        switch (keuze) {
            case 1:
                voegGewichtToe();
                break;
            case 2:
                toonGebruikersGegevens();
                break;
            case 3:
                toonGrafiek();
                break;
            case 4:
                selecteerDoelEnGenereerPlan();
                break;
            case 5:
                berekenBMI();
                break;
            case 6:
                beheerDagboekMetBestand();
            case 7:
                System.out.println("Applicatie wordt afgesloten...");
                running = false;
                break;
            default:
                System.out.println("Ongeldige keuze.");
        }
    }

    private void voegGewichtToe() {
        try {
            System.out.print("Voer je gewicht in (kg): ");

            double gewicht = Double.parseDouble(scanner.nextLine());
            System.out.print("Voer je lengte in (m): ");

            double lengte = Double.parseDouble(scanner.nextLine());
            tracker.voegGegevensToe(gewicht, lengte);
            System.out.println("Gewicht en lengte toegevoegd.");

        } catch (NumberFormatException e) {
            System.out.println("Ongeldige invoer. Gewicht en lengte moeten een getal zijn.");
        }
    }

    private void toonGrafiek() {
        System.out.print("Voor welke periode wil je de grafiek? (bijv. '3 weken'): ");
        String periode = scanner.nextLine();
        Grafiek grafiek = tracker.genereerGrafiek(periode);
        grafiek.printGrafiek();
    }

    private void selecteerDoelEnGenereerPlan() {
        System.out.println("Beschikbare doelen: Afvallen, Spieropbouw, Conditie verbeteren");
        System.out.print("Kies een doel: ");
        String doel = scanner.nextLine();
        try {
            planner.selecteerDoel(doel);
            System.out.println("Geselecteerd doel: " + planner.getGeselecteerDoel());

            WorkoutPlan plan = planner.genereerWorkoutPlan(doel);
            System.out.println("Gegenereerd workoutplan: ");
        } catch (IllegalArgumentException e) {
            System.out.println("Fout: " + e.getMessage());
        }
    }

    private void berekenBMI() {
        try {
            Double gewicht = tracker.getLaatsteGewicht();
            Double lengte = tracker.getLaatsteLengte();

            if (gewicht == null || lengte == null) {
                System.out.println("Geen gegevens beschikbaar.");
                return;
            }

            BMICalculator bmiCalculator = new BMICalculator(gewicht, lengte);
            double bmi = bmiCalculator.berekenBMI();
            System.out.printf("BMI van %s is: %.2f (%s)\n",
                    gebruiker.getNaam(), bmi, bmiCalculator.classificeerBMI(bmi));
        } catch (IllegalArgumentException e) {
            System.out.println("Fout bij BMI-berekening: " + e.getMessage());
        }
    }


    private void beheerDagboekMetBestand() {
        System.out.println("\n--- Beheer dagboek ---");
        System.out.println("1. Zoek naar een voedingsmiddel");
        System.out.println("2. Voeg een maaltijd toe aan het dagboek");
        System.out.println("3. Bekijk je dagboek");
        System.out.print("Maak een keuze (1-3): ");

        try {
            int keuze = Integer.parseInt(scanner.nextLine());
            switch (keuze) {
                case 1:
                    zoekVoedingsmiddel();
                    break;
                case 2:
                    voegMaaltijdToe();
                    break;
                case 3:
                    toonDagboek();
                    break;
                default:
                    System.out.println("Ongeldige keuze.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ongeldige invoer. Probeer opnieuw.");
        }
    }

    private void zoekVoedingsmiddel() {
        System.out.println("Voer de naam van het voedingsmiddel in");
        String naam = scanner.nextLine();
        if (calorieTracker.zoekVoedingsmiddel(naam)) {
            System.out.println(naam + " is beschikbaar.");
        } else {
            System.out.println(naam + " is niet beschikbaar.");
        }
    }

    private void voegMaaltijdToe() {
        System.out.println("Voer de naam van de maaltijd in:");
        String naam = scanner.nextLine();
        System.out.print("Voer het aantal calorieën in: ");
        try {
            int calorieën = Integer.parseInt(scanner.nextLine());
            Maaltijd maaltijd = new Maaltijd(naam, calorieën);
            calorieTracker.voegToeAanDagboek(maaltijd);
        } catch (NumberFormatException e) {
            System.out.println("Ongeldige invoer. Calorieën moeten een getal zijn.");
        }
    }

    private void toonDagboek() {
        List<Maaltijd> dagboek = calorieTracker.getDagboek();
        if (dagboek.isEmpty()) {
            System.out.println("Je dagboek is leeg");
        } else {
            System.out.println("\n--- Dagboek ---");
            for (Maaltijd maaltijd : dagboek) {
                System.out.println(maaltijd.getNaam() + " - " + maaltijd.getCalorieën() + " calorieën");
            }
        }
    }

    private void toonGebruikersGegevens() {
        System.out.println("\n--- Gebruikersgegevens ---");
        System.out.println("ID: " + gebruiker.getId());
        System.out.println("Naam: " + gebruiker.getNaam());
        System.out.println("Gewicht: " + gebruiker.getGewicht() + " kg");
        System.out.println("Lengte: " + gebruiker.getLengte() + " m");

        try {
            BMICalculator bmiCalculator = new BMICalculator(gebruiker.getGewicht(), gebruiker.getLengte());
            double bmi = bmiCalculator.berekenBMI();
            String classificatie = bmiCalculator.classificeerBMI(bmi);
            System.out.printf("BMI: %.2f (%s)\n", bmi, classificatie);
        } catch (IllegalArgumentException e) {
            System.out.println("BMI kon niet berekend worden: " + e.getMessage());
        }
    }
}

