package org.mydb.Utilities;

import java.util.List;

public class BinarySearch implements IPageSearch{

    private int[] binarySearch(int key, List<Integer> keys){

        int left, right;
        left = 0;
        right = keys.size()-1;
        int[] result = new int[2];
        while(left <= right){

            int mid = left + (right - left)/2;
            int midValue = keys.get(mid);
            if(midValue== key){
                result[1] = mid;
                return result;
            }else if (key > midValue){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }

        result[0] = 1;
        result[1] = left;
        return result;

    }
    public int search(int key, List<Integer> keys){
        int[] result = binarySearch(key, keys);
        if(result[0] == 0) return result[1];

        return -1;
    }

    @Override
    public int possiblePos(int key, List<Integer> keys) {
        int[] result = binarySearch(key, keys);
        return result[1];
    }
}
