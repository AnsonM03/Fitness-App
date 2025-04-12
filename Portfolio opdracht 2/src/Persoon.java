import java.util.ArrayList;
import java.util.List;

public class Persoon {
    private int id;
    private String naam;
    private double gewicht;
    private double lengte;
    private double bmi;

    public Persoon(int id, String naam, double gewicht, double lengte) {
        this.id = id;
        this.naam = naam;
        this.gewicht = gewicht;
        this.lengte = lengte;
    }
        public int getId() {
            return id;
        }
        public String getNaam() {
            return naam;
        }

        public double getGewicht() {
            return gewicht;
        }

        public void setGewicht(double gewicht) {
            this.gewicht = gewicht;
        }

        public double getLengte() {
            return lengte;
        }

        public void setLengte(double lengte) {
            this.lengte = lengte;
        }

        public double getBmi() {
            return bmi;
        }
    }
