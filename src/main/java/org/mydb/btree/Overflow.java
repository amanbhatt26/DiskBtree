package org.mydb.btree;

public class Overflow {
    public Page oldChild;
    public Page newChild;
    public int separatorKey;

    public Overflow(Page oldChild, Page newChild, int separatorKey){
        this.oldChild = oldChild;
        this.newChild = newChild;
        this.separatorKey = separatorKey;
    }
}
