package org.mydb.Utilities;

import org.mydb.btree.Page;

import java.util.List;
import java.util.Map;

public enum PageBuffer {
    INSTANCE;
    private Map<Integer, Page> pages;

    public Page getPage(int pageNumber){
        return pages.getOrDefault(pageNumber, null);
    }
    public void addPage(int pageNumber, Page page){
        pages.put(pageNumber, page);
    }

}
