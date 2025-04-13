import java.util.ArrayList;
import java.util.Arrays;

public class CalorieTrackerTest {

    public static void main(String[] args) {
        testVoegToeAanDagboek();
        testZoekVoedingsmiddel();
        testToonOverzicht();
    }

    public static void testVoegToeAanDagboek() {
        Persoon persoon = new Persoon(1, "Anson", 70, 1.75);
        CalorieTracker tracker = new CalorieTracker(persoon);
        Maaltijd maaltijd = new Maaltijd("Pasta", 600);

        tracker.voegToeAanDagboek(maaltijd);
        boolean result = tracker.getDagboek().contains(maaltijd);

        System.out.println("Test voegToeAanDagboek: " + (result ? "GESLAAGD" : "GEFAALD"));
    }

    public static void testZoekVoedingsmiddel() {
        Persoon persoon = new Persoon(1, "Anson", 70, 1.75);
        CalorieTracker tracker = new CalorieTracker(persoon);

        boolean result = tracker.zoekVoedingsmiddel("Pizza");
        System.out.println("Test zoekVoedingsmiddel ('Pizza'): " + (result ? "GESLAAGD" : "GEFAALD"));
    }

    public static void testToonOverzicht() {
        Persoon persoon = new Persoon(1, "Anson", 70, 1.75);
        CalorieTracker tracker = new CalorieTracker(persoon);
        tracker.voegToeAanDagboek(new Maaltijd("Pasta", 600));
        tracker.voegToeAanDagboek(new Maaltijd("Pizza", 800));

        System.out.println("Test toonOverzicht:");
        tracker.toonOverzicht();
    }
}

