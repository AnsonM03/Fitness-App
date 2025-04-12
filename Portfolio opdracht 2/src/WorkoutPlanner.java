import java.util.ArrayList;
import java.util.List;

public class WorkoutPlanner {
    private List<String> trainingsdoelen;
    private String geselecteerDoel;

    public WorkoutPlanner() {
        this.trainingsdoelen = new ArrayList<>();
        this.trainingsdoelen.add("Afvallen");
        this.trainingsdoelen.add("Spieropbouw");
        this.trainingsdoelen.add("Conditie verbeteren");
    }

    public String getGeselecteerDoel() {
        return geselecteerDoel;
    }

    public void selecteerDoel(String doel) {
        if (trainingsdoelen.contains(doel)) {
            this.geselecteerDoel = doel;
            System.out.println("Geselecteerd doel: " + doel);
        } else {
            throw new IllegalArgumentException("Ongeldig trainingsdoel: " + doel);
        }
    }

    public WorkoutPlan genereerWorkoutPlan(String doel) {
        if (!trainingsdoelen.contains(doel)) {
            throw new IllegalArgumentException("Ongeldig trainingsdoel: " + doel);
        }

        WorkoutPlan plan = new WorkoutPlan();
        List<String> oefeningen = new ArrayList<>();

        switch (doel) {
            case "Afvallen":
                oefeningen.add("Hardlopen - 30 minuten");
                oefeningen.add("Fietsen - 45 minuten");
                oefeningen.add("Lunges - 20 minuten");
                oefeningen.add("Jumping Jacks - 25 minuten");
                break;
            case "Spieropbouw":
                oefeningen.add("Bankdrukken - 3 sets van 10 herhalingen");
                oefeningen.add("Squats - 3 sets van 12 herhalingen");
                oefeningen.add("Deadlifts - 3 sets van 8 herhalingen");
                break;
            case "Conditie verbeteren":
                oefeningen.add("Hardlopen - 5 km");
                oefeningen.add("Touwtjespringen - 15 minuten");
                oefeningen.add("Roeien - 20 minuten");
                break;
            default:
                throw new IllegalArgumentException("Onbekend trainingsdoel.");

        }

        plan.setOefeningen(oefeningen);
        return plan;
    }
}
