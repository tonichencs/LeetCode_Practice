import java.util.ArrayList;

public class Q2962 {
    /*
    You are given an integer array nums and a positive integer k.
    Return the number of subarrays where the maximum element of nums appears at least k times in that subarray.
    A subarray is a contiguous sequence of elements within an array.
     */
    public long countSubarrays(int[] nums, int k) {
        // Extract the maximum element of the nums array
        int max = 0;

        for (int num : nums) {
            max = Math.max(num, max);
        }

        // Store all the indices at which the max element appeared
        ArrayList<Integer> maxIndices = new ArrayList();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == max) {
                maxIndices.add(i);
            }
        }

        // If we have less appearance of max element than the minimum requirement, then return directly.
        if (maxIndices.size() < k) return 0;

        /*
         * prev: the pointer of the leftmost indice the current window can extend to without including the previous appearance of the max element.
         * left: the leftmost max element indices in the current window
         * right: the rightmost max element indices in the current window
         * 
         * ---m-----m-----m--------m-----m-----
         *     |    |              |  
         *   prev  left           right
         */
        long res = 0, prev = 0, left = 0, right = 0;
        for (int i = 0; i <= maxIndices.size() - k; i++) {
            left = maxIndices.get(i);
            right = maxIndices.get(i + k - 1);

            // We multiply the number of starting position for the current window with the number of ending position for the current window under the 
            // premise of the current window containing at least k max elements.
            res += (left - prev + 1) * (nums.length - right);
            prev = left + 1;
        }

        return res;
    }

    public static void main(String[] args) {
        Q2962 instance = new Q2962();

        ArrayList<int[]> numss = new ArrayList<>();
        ArrayList<Integer> ks = new ArrayList<>();

        numss.add(new int[]{1,3,2,3,3});
        numss.add(new int[]{1,4,2,1});

        ks.add(2);
        ks.add(3);

        ArrayList<Integer> anss = new ArrayList<>();

        anss.add(6);
        anss.add(0);
        
        boolean passed = true;
        for (int i = 0; i < anss.size(); i++) {
            long res = instance.countSubarrays(numss.get(i), ks.get(i));
            if (res != anss.get(i)) {
                System.out.printf("The %d test case failed \n", i);
                System.out.println(res);
                System.out.println(anss.get(i));
                passed = false;
            }
        }

        if (passed) System.out.println("The test passed.");
    }
}
