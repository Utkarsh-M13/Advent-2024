import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Create the directions in which the word can be oriented
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};

        // Read the input
        ArrayList<String> data = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader("sample.txt"))) {
            String line = br.readLine();
            while (line != null) {
                data.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Convert arraylist to 2D array
        int rows = data.size();
        int cols = data.get(0).length();

        Character[][] cw = new Character[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cw[i][j] = data.get(i).charAt(j);
            }
        }

        String rgx = "XMAS";
        int idx = 0;

        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < 8; k++) {
                    int[] dir = directions[k];
                    boolean notFound = false;
                    int[] cor = {j, i};
                    while (!notFound) {
                        if (cw[cor[1]][cor[0]] == rgx.charAt(idx)) {
                            // Increments in directions
                            cor[0] = cor[0] + dir[0];
                            cor[1] = cor[1] + dir[1];
                            idx++;
                            if (idx == 4) {
                                count++;
                                break;
                            }
                            if (cor[0] < 0 || cor[0] >= cols || cor[1] < 0 || cor[1] >= rows) {
                                break;
                            }
                        } else {
                            notFound = true;
                        }
                    }
                    idx = 0;
                }
            }
        }
        System.out.println(count);
    }
}