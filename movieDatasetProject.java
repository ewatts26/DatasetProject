import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class movieDatasetProject {
    public static void main(String[] args) throws FileNotFoundException {
        actionVSnonaction("/Users/ewatts/CS_Seminar/imdb_top_1000.csv");
    }
    public static void actionVSnonaction(String pathname) throws FileNotFoundException {
        File f = new File(pathname);
        Scanner sc = new Scanner(f);

        String[] header = sc.nextLine().split(",");
        int genre_idx = 8;
        int runtime_idx = 7;

        ArrayList<Double> actionRuntime = new ArrayList<>();
        ArrayList<Double> nonActionRuntime = new ArrayList<>();

        while (sc.hasNextLine()) {
            ArrayList<String> line = new ArrayList<>(Arrays.asList(sc.nextLine().split(",")));

            String runtime = line.get(runtime_idx);
            int space = runtime.indexOf(" ");
            // if space -1, dont get substring
            if (space != -1) {
                runtime = runtime.substring(0, space);
            }

            // if runtime can be parsed do vvvv {
            if (isParseable(runtime)) {
                if (line.get(genre_idx).equals("\"Action")) {
                    
                    actionRuntime.add(Double.parseDouble(runtime));
                } else {
                    nonActionRuntime.add((Double)Double.parseDouble(runtime));
                }
            }
        }

        double action_avg = 0;
        for (Double d: actionRuntime) {
            action_avg += d;
        }

        double nonAction_avg = 0;
        for (Double d: nonActionRuntime) {
            nonAction_avg += d;
        }

        action_avg /= actionRuntime.size();
        nonAction_avg /= nonActionRuntime.size();

        System.out.println(String.format("Action average: %.2f", action_avg));
        System.out.println(String.format("Action average: %.2f", nonAction_avg));
        System.out.println("Action movies have a higher average runtime than non-action movies.");
    }

    public static boolean isParseable(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
