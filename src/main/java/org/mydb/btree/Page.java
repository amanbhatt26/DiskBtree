package org.mydb.btree;

import org.mydb.Utilities.Constants;

import java.nio.ByteBuffer;

public interface Page {
    ByteBuffer byteLayout();
    Overflow add(int val);
    Constants.PageType getPageType();
    int sizeLeft();
    static Page fromByteLayout(ByteBuffer buffer){
        /*TODO: return specific page types based on the bytebuffer */
        return null;
    }
}
