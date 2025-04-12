import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class CalorieTracker {
    private Persoon persoon;
    private List<String> voedingsmiddelenDatabase;
    private List<Maaltijd> dagboek;
    private String bestandpad;

    public CalorieTracker(Persoon persoon) {
        this.persoon = persoon;
        this.voedingsmiddelenDatabase = new ArrayList<>();
        this.dagboek = new ArrayList<>();
        this.bestandpad = "/Users/ansonmok/Documents/";

        voedingsmiddelenDatabase.add("Kip met rijst");
        voedingsmiddelenDatabase.add("Pasta");
        voedingsmiddelenDatabase.add("Pizza");
        voedingsmiddelenDatabase.add("Stampot met rookworst");
        voedingsmiddelenDatabase.add("Hamburger");

        laadDagboek();
    }

    public boolean zoekVoedingsmiddel(String naam) {
        return voedingsmiddelenDatabase.contains(naam);
    }

    public List<Maaltijd> getDagboek() {
        return dagboek;
    }

    public void voegToeAanDagboek(Maaltijd maaltijd) {
        dagboek.add(maaltijd);
        slaDagboekOp();
        System.out.println("Maaltijd toegevoegd aan dagboek: " + maaltijd.getNaam());
    }

    private void slaDagboekOp() {
        try {
            File directory = new File("/Users/ansonmok/Documents/");
            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    System.out.println("Directory aangemaakt: " + directory.getAbsolutePath());
                } else {
                    System.err.println("Kon de directory niet aanmaken: " + directory.getAbsolutePath());
                    return;
                }
            }

            File bestand = new File(directory, "dagboek.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(bestand))) {
                for (Maaltijd maaltijd : dagboek) {
                    writer.write(maaltijd.getNaam() + "," + maaltijd.getCalorieën());
                    writer.newLine();
                }
                System.out.println("Dagboek opgeslagen in: " + bestand.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Fout bij het opslaan van het dagboek: " + e.getMessage());
        }
    }


    private void laadDagboek() {
        File bestand = new File("/Users/ansonmok/Documents/dagboek.txt");
        if (!bestand.exists()) {
            System.out.println("Geen bestaand dagboek gevonden. Een nieuw bestand wordt aangemaakt.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(bestand))) {
            String regel;
            while ((regel = reader.readLine()) != null) {
                String[] delen = regel.split(",");
                String naam = delen[0];
                int calorieën = Integer.parseInt(delen[1]);
                dagboek.add(new Maaltijd(naam, calorieën));
            }
            System.out.println("Dagboek geladen uit: " + bestand.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Fout bij het laden van het dagboek: " + e.getMessage());
        }
    }
}
