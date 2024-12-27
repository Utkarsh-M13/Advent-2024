import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class MainTwo {
    public static void main(String[] args) {
        // Create the directions in which the word can be oriented
        int[][] directions = {{1, 1}, {-1, 1},};

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

        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (cw[i][j] == 'A') {
                    int found = 0;
                    for (int k = 0; k < 2; k++) {
                        HashSet<Character> hs = new HashSet<>();
                        hs.add('M');
                        hs.add('S');

                        int[] cor = {j, i};
                        int[] dir = directions[k];
                        cor[0] = cor[0] + dir[0];
                        cor[1] = cor[1] + dir[1];
                        if (cor[0] < 0 || cor[0] >= cols || cor[1] < 0 || cor[1] >= rows) {
                            break;
                        }
                        if (!hs.contains(cw[cor[1]][cor[0]])) {
                            break;
                        } else {
                            hs.remove(cw[cor[1]][cor[0]]);
                        }

                        cor[0] = j - dir[0];
                        cor[1] = i - dir[1];
                        if (cor[0] < 0 || cor[0] >= cols || cor[1] < 0 || cor[1] >= rows) {
                            break;
                        }
                        if (!hs.contains(cw[cor[1]][cor[0]])) {
                            break;
                        } else {
                            hs.remove(cw[cor[1]][cor[0]]);
                            found++;
                        }
                    }
                    if (found == 2) {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
