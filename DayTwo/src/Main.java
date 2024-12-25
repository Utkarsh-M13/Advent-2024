import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        int safe = 0;
        int levels = -1;
        ArrayList<Integer[]> reports = new ArrayList<Integer[]>();
        try (BufferedReader bf = new BufferedReader(new FileReader("input.txt"))) {
            String line = bf.readLine();
            while (line != null) {
                String[] segments = line.split(" ");
                Integer[] report = new Integer[segments.length];
                for (int i = 0; i < segments.length; i++) {
                    report[i] = Integer.parseInt(segments[i]);
                }
                reports.add(report);
                line = bf.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int numReports = reports.size();
        for (int i = 0; i < numReports; i++) {
            Integer[] report = reports.get(i);
            int invalid = -1;
            boolean valid = true;
            boolean des = report[0] >= report[1];
            levels = report.length;
            for (int j = 0; j < levels - 1; j++) {
                if (Objects.equals(report[j], report[j + 1])) {
                    valid = false;
                    break;
                } else if (report[j] < report[j + 1] && des) {
                    valid = false;
                    break;
                } else if (report[j] > report[j + 1] && !des) {
                    valid = false;
                    break;
                }
                int diff = Math.abs(report[j + 1] - report[j]);
                if (diff < 1 || diff > 3) {
                    valid = false;
                    break;
                }
            }
            if (!valid) {
                for (int k = 0; k < levels; k++) {
                    Integer[] modifiedReport = new Integer[report.length - 1];
                    valid = true;
                    for (int j = 0; j < k; j++) {
                        modifiedReport[j] = report[j];
                    }
                    for (int j = k; j < levels - 1; j++) {
                        modifiedReport[j] = report[j + 1];
                    }
                    des = modifiedReport[0] >= modifiedReport[1];
                    for (int j = 0; j < levels - 2; j++) {
                        if (Objects.equals(modifiedReport[j], modifiedReport[j + 1])) {
                            valid = false;
                            break;
                        } else if (modifiedReport[j] < modifiedReport[j + 1] && des) {
                            valid = false;
                            break;
                        } else if (modifiedReport[j] > modifiedReport[j + 1] && !des) {
                            valid = false;
                            break;
                        }
                        int diff = Math.abs(modifiedReport[j + 1] - modifiedReport[j]);
                        if (diff < 1 || diff > 3) {
                            valid = false;
                            break;
                        }
                    }
                    if (valid) {
                        break;
                    }
                }
            }
            if (valid) {
                safe++;
            }
        }
            System.out.println(safe);
        }
    }