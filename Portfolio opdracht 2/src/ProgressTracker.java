import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProgressTracker implements Tracker {
    private List<Double> gewichtHistorie;
    private List<Double> lengteHistorie;
    private String bestandspad = "gegevens.txt";

    public ProgressTracker() {
        this.gewichtHistorie = new ArrayList<>();
        this.lengteHistorie = new ArrayList<>();
        laadGegevens();
    }

    @Override
    public void toonOverzicht() {
        System.out.println("Gewichtsgeschiedenis:");
        for (Double gewicht : gewichtHistorie) {
            System.out.println("- " + gewicht + " kg");
        }
    }

    public void voegGegevensToe(Double gewicht, double lengte) {
        if (gewicht > 0 && lengte > 0) {
            gewichtHistorie.add(gewicht);
            lengteHistorie.add(lengte);
            slaGegevensOp(gewicht, lengte);
            System.out.println("Gewicht en lengte toegevoegd: " + gewicht + " kg" + lengte + " m");
        } else {
            throw new IllegalArgumentException("Gewicht en lengte moeten groter zijn dan 0.");
        }
    }

    public void slaGegevensOp(Double gewicht, double lengte) {
        try (FileWriter writer = new FileWriter("/Users/ansonmok/Documents/gegevens", true)) {
            writer.write(gewicht + "," + lengte + "\n");
        } catch (IOException e) {
            System.err.println("Fout bij het laden van gewichtsgegevens: " + e.getMessage());
        }
    }


public void laadGegevens() {
    try (BufferedReader reader = new BufferedReader(new FileReader("/Users/ansonmok/Documents/gegevens"))) {
        String regel;
        while ((regel = reader.readLine()) != null) {
            String[] delen = regel.split(",");

            gewichtHistorie.add(Double.parseDouble(delen[0]));
            lengteHistorie.add(Double.parseDouble(delen[1]));
        }
    } catch (FileNotFoundException e) {
        System.out.println("Geen bestaand gegevensbestand gevonden. Een nieuw bestand wordt aangemaakt.");
    } catch (IOException e) {
        System.err.println("Fout bij het laden van gegevens: " + e.getMessage());
    }
}

    public Double getLaatsteGewicht() {
        if (!gewichtHistorie.isEmpty()) {
            return gewichtHistorie.get(gewichtHistorie.size() - 1);
        }
        return null;
    }

    public Double getLaatsteLengte() {
        if (!lengteHistorie.isEmpty()) {
            return lengteHistorie.get(lengteHistorie.size() - 1);
        }
        return null;
    }


    public Grafiek genereerGrafiek(String tijdsperiode) {
        System.out.println("Grafiek gegenereerd voor tijdsperiode: " + tijdsperiode);
        Grafiek grafiek = new Grafiek();
        List<String> datapunten = new ArrayList<>();

        int aantal = 0;
        try {
            String[] delen = tijdsperiode.split(" ");
            aantal = Integer.parseInt(delen[0]);
        } catch (Exception e) {
            System.out.println("Ongeldige invoer voor tijdsperiode.");
            return grafiek;
        }

        int max = Math.min(aantal, gewichtHistorie.size());

        for (int i = 0; i < max; i++) {
            datapunten.add("Gewicht: " + gewichtHistorie.get(i) + " kg, Lengte: " + lengteHistorie.get(i) + " m");
        }

        if (max == 0) {
            datapunten.add("Niet genoeg data beschikbaar voor " + aantal + " metingen.");
        }

        grafiek.setDataPunten(datapunten);
        return grafiek;
    }
}
