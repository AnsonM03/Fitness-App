import java.util.List;

public class Grafiek {
    private List<String> dataPunten;

    public List<String> getDataPunten() {
        return dataPunten;
    }

    public void setDataPunten(List<String> dataPunten) {
        this.dataPunten = dataPunten;
    }

    public void printGrafiek() {
        System.out.println("Grafiekgegevens:");
        int week = 1;
        for (String data : dataPunten) {
            System.out.println("Week " + week + ": " + data + " kg");
            week++;
        }
    }
}
