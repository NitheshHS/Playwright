package org.saucedemo.util;

public class MainClass {

    public static void main(String[] args) throws NoSuchMethodException {
        int[] nums={0,0,1,1,1,2,2,3,3,4,4};//[0,1,2,3,4]
        nums = removeDuplicates(nums);

        for(int num:nums){
            System.out.println(num);
        }
    }

    private static int[] replaceArray(int[] nums, int num) {
        for(int i = 0; i< nums.length; i++){
            int temp= nums[i];
            for (int j = i; j< nums.length; j++){
                if(temp==num){
                   nums[i] = -1;
                }
            }
        }
        return nums;
    }

    public static int[] removeDuplicates(int[] nums){
        int count = 1;
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] == nums[j]) {
                        nums[j] = -1;
                    }
                }
            }
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {//[1,2]
                    if(nums.length<=2 && nums[j] != nums[i]){
                        ++count;
                    }
                    if (nums[i] == -1 && nums[j] != nums[i]) {
                        ++count;
                        int temp = nums[i];//-1
                        nums[i] = nums[j];//-1
                        nums[j] = temp;
                        break;
                    }
                }

            }
            System.out.println("count " + count);

            return nums;
        }

}
