
import java.util.ArrayList;
import java.util.Arrays;

public class App {

    public static void main(String[] args) throws Exception {
        ArrayList<int[]> arr = new ArrayList<int[]>();
        boolean doesContain = false;

        int[] a = {1,1};
        arr.add(a);
        int[] b = {2,3};
        arr.add(b);
        int[] c = {2,3};

        for(int[] x : arr) {
            if(Arrays.equals(x, c)) {
                doesContain = true;
            }
        }
        System.out.println(doesContain);
    }
}
