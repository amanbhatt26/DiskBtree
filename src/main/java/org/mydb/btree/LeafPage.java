package org.mydb.btree;

import org.mydb.Utilities.BinarySearch;
import org.mydb.Utilities.Constants;
import org.mydb.Utilities.IPageSearch;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class LeafPage implements Page {


    private int pageNumber;
    private List<Integer> keys;

    private Constants.PageType pageType = Constants.PageType.LEAF;
    private final IPageSearch pageSearch = new BinarySearch();

    public LeafPage(int pageNumber){
        this.pageNumber = pageNumber;
        this.keys = new ArrayList();

    }

    public LeafPage(ByteBuffer byteLayout){
        byteLayout.position(0);
        int pageNumber = byteLayout.getInt();
        int pageTypeOrdinal = byteLayout.getInt();
        List<Integer> keys = new ArrayList<>();
        int keysSize = byteLayout.getInt();

        while(keys.size() < keysSize){
            keys.add(byteLayout.getInt());
        }

        this.pageNumber = pageNumber;
        this.keys = keys;

    }

    @Override
    public Overflow add(int val){
        if(pageSearch.search(val, this.keys) != -1){
            return null;
        }

        if(this.sizeLeft() < 4){
            /* TODO: implement new page and distribute keys accordingly then return */
            Page newPage = new LeafPage(0);
            int mid = this.keys.size()/2;
            int pos = pageSearch.possiblePos(val, this.keys);
            int it = mid;
            while(it< this.keys.size()){
                newPage.add(this.keys.get(it++));
            }
            List<Integer> old = this.keys;
            this.keys = new ArrayList<>();
            for(it = 0;it < mid;it++){
                this.keys.add(old.get(it));
            }

            if(pos < mid){
                this.keys.add(val);
            }else{
                newPage.add(val);
            }

            return new Overflow(this, newPage, this.keys.get(this.keys.size()-1));

        }

        int pos = pageSearch.possiblePos(val, this.keys);
        this.keys.add(pos, val);
        return null;
    }

    @Override
    public Constants.PageType getPageType() {
        return pageType;
    }

    @Override
    public int sizeLeft() {
        return Constants.PAGE_SIZE - calculateSize();
    }

    public ByteBuffer byteLayout(){
        ByteBuffer buffer = ByteBuffer.allocate(Constants.PAGE_SIZE);
        buffer.putInt(this.pageNumber);
        buffer.putInt(this.pageType.ordinal());
        buffer.putInt(this.keys.size());
        for(var key:this.keys){
            buffer.putInt(key);
        }

        return buffer;
    }

    public int getPageNumber(){
        return pageNumber;
    }
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    private int calculateSize(){
       int size = 12 + keys.size()*4;
       return size;
    }
}
