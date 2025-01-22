package org.mydb.btree;

import org.mydb.Utilities.BinarySearch;
import org.mydb.Utilities.IPageSearch;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class LeafPage {
    private int pageNumber;

    private final List<Integer> keys;
    private IPageSearch pageSearch;


    private int calculatePageSize(){
        /* array size + pageNumber + pageSize + array elements */
        return 32+32+32 + 32*keys.size();
    }

    public ByteBuffer byteLayout(){
        ByteBuffer buffer = ByteBuffer.allocate(32);
        buffer.putInt(pageNumber);
        buffer.putInt(calculatePageSize());
        buffer.putInt(keys.size());

        for(Integer key: keys){
            buffer.putInt(key);
        }
        return buffer;
    }
    public int getPageNumber(){
        return pageNumber;
    }

    public void setPageNumber(int n){
        pageNumber = n;
    }

    public int getPageSize(){
        return calculatePageSize();
    }

    public List<Integer> getKeys(){
        return keys;
    }
    public boolean isKeyPresent(int key){
        return pageSearch.search(key, keys) != -1;
    }

    public void insertKey(int key){
        int pos = pageSearch.possiblePos(key, keys);
        keys.add(pos, key);
//        if(pos < keys.size()) {
//
//        }else{
//            keys.add(key);
//        }


    }

    public LeafPage(int pageNumber){
        this.pageNumber = pageNumber;
        this.pageSearch  = new BinarySearch();
        this.keys = new ArrayList<>();

    }

    public LeafPage(int pageNumber, List<Integer> keys){
        this.pageNumber = pageNumber;
        this.keys = keys;
        this.pageSearch = new BinarySearch();
    }
    public static LeafPage createLeafPage(ByteBuffer byteLayout){
        int pageNumber = byteLayout.getInt(0);
        List<Integer> keys = new ArrayList<>();

        int keysSize = byteLayout.getInt(4*2);

        for(int byteOffset = 4*3; byteOffset < 4*3 + 4*keysSize; byteOffset += 4){
            keys.add(byteLayout.getInt(byteOffset));
        }

        return new LeafPage(pageNumber, keys);
    }
}
