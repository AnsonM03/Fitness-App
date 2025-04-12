public class Maaltijd {
    private String naam;
    private int calorieën;

    public Maaltijd(String naam, int calorieën) {
        this.naam = naam;
        this.calorieën = calorieën;
    }

    public String getNaam() {
        return naam;
    }

    public int getCalorieën() {
        return calorieën;
    }
}
