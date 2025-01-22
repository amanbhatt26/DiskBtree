package org.mydb.btree;

public abstract class Page {

    private int pageNumber;
    private int pageSize;



    abstract public byte[] byteLayout();
    abstract public int getPageNumber();
    abstract public int getPageSize();

    public static Page createPage(byte[] byteLayout){
        return null;
    }

}
