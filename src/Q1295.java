import java.util.ArrayList;

public class Q1295 {
    /*
     * Given an array nums of integers, return how many of them contain an even number of digits.
     */
    public int findNumbers(int[] nums) {
        int res = 0;

        for (int num : nums) {
            res += countDigit(num) % 2 == 0 ? 1 : 0;
        }

        return res;
    }

    public int countDigit(int num) {
        int count = 0;
        while (num > 0) {
            num /= 10;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Q1295 instance = new Q1295();

        ArrayList<int[]> numss = new ArrayList<>();
        ArrayList<Integer> anss = new ArrayList<>();

        numss.add(new int[]{12, 345, 2, 6, 7896});
        numss.add(new int[]{555, 901, 482, 1771});

        anss.add(2);
        anss.add(1);

        boolean flag = true;
        for (int i = 0; i < numss.size(); i++) {
            if (instance.findNumbers(numss.get(i)) != anss.get(i)) {
                flag = false;
                break;
            }
        }

        if (flag) {
            System.out.println("All test cases passed!");
        } else {
            System.out.println("Some test cases failed.");
        }
    }
}