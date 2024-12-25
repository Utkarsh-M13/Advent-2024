import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {2, 6, 3, 7, 5, 5, 4};
        arr = sort(arr);
        ArrayList<Integer> l1 = new ArrayList<>();
        ArrayList<Integer> l2 = new ArrayList<>();

        try (BufferedReader bf = new BufferedReader(new FileReader("input.txt"))){
            String line = bf.readLine();
            while (line != null) {
                String[] segments = line.split("   ");
                l1.add(Integer.parseInt(segments[0]));
                l2.add(Integer.parseInt(segments[1]));
                line = bf.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Integer[] list1 = sort( l1.toArray(new Integer[0]));
        Integer[] list2 = sort( l2.toArray(new Integer[0]));
        int sum = 0;
        for (int i = 0; i < list1.length; i++) {
            sum += Math.abs(list1[i] - list2[i]);
        }
        System.out.println(sum);
        int max = -1;
        for (int i = 0; i < list1.length; i++) {
            max = list1[i] > max ? list1[i] : max;
        }
        int[] freq = new int[max];
        for (int i = 0; i < list2.length; i++) {
            if (list2[i] <= max) {
                freq[list2[i]-1]++;
            }
        }
        int sim = 0;
        for (int i = 0; i < list1.length; i++) {
            sim += list1[i] * freq[list1[i] - 1];
        }
        System.out.println(sim);
    }

    private static Integer[] sort(Integer[] arr) {
        if (arr.length == 1 || arr.length == 0) {
            return arr;
        }
        Integer[] left = sort(Arrays.copyOfRange(arr, 0, arr.length / 2));
        Integer[] right = sort(Arrays.copyOfRange(arr, arr.length / 2, arr.length));
        return merge(left, right);
    }

    private static Integer[] merge(Integer[] left, Integer[] right) {
        int len = left.length + right.length;
        Integer[] arr = new Integer[len];
        int li = 0;
        int ri = 0;
        for (int i = 0; i < len; i++) {
            if (li < left.length && ri < right.length) {
                if (left[li] < right[ri]) {
                    arr[i] = left[li];
                    li++;
                } else {
                    arr[i] = right[ri];
                    ri++;
                }
            } else if (li < left.length) {
                arr[i] = left[li];
                li++;
            } else {
                arr[i] = right[ri];
                ri++;
            }
        }
        return arr;
    }
}
