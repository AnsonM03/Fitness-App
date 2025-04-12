import java.util.List;

public class WorkoutPlan {
    private List<String> oefeningen;

    public void setOefeningen(List<String> oefeningen) {
        this.oefeningen = oefeningen;
    }

    public void printWorkoutPlan() {
        System.out.println("Workout Plan:");
        for (String oefeningen : oefeningen) {
            System.out.println("- " + oefeningen);
        }
    }
}
