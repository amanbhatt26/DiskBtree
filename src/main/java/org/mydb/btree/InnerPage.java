package org.mydb.btree;

import org.mydb.Utilities.Constants;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class InnerPage{
//    private int pageNumber;
//    private List<Integer> keys;
//    private List<Integer> children;
//    private int arraySize;
//    private int childSize;
//    private Constants.PageType pageType = Constants.PageType.NON_LEAF;
//
//    public InnerPage(int pageNumber){
//        this.pageNumber = pageNumber;
//        this.keys = new ArrayList();
//        this.arraySize = 0;
//        this.childSize = 0;
//    }
//
//    public InnerPage(ByteBuffer byteLayout){
//        int pageNumber = byteLayout.getInt(0);
//        List<Integer> keys = new ArrayList<>();
//        int keysSize = byteLayout.getInt(4*1);
//
//        for(int byteOffset = 4*2; byteOffset < 4*2 + 4*keysSize; byteOffset += 4){
//            keys.add(byteLayout.getInt(byteOffset));
//        }
//
//        this.pageNumber = pageNumber;
//        this.keys = keys;
//        this.arraySize = 0;
//        this.childSize = 0;
//    }
//
//    @Override
//    public Overflow add(int val){
//        return null;
//    }
//
//    @Override
//    public Constants.PageType getPageType() {
//        return pageType;
//    }
//
//    public ByteBuffer byteLayout(){
//        ByteBuffer buffer = ByteBuffer.allocate(Constants.PAGE_SIZE);
//        buffer.putInt(this.pageNumber);
//        buffer.putInt(this.pageType.ordinal());
//        buffer.putInt(arraySize);
//        for(var key:this.keys){
//            buffer.putInt(key);
//        }
//
//        return buffer;
//    }


}
