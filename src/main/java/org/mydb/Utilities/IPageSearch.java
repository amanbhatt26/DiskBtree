package org.mydb.Utilities;

import java.util.List;

public interface IPageSearch {

    int search(int key, List<Integer> keys);
    int possiblePos(int key, List<Integer> keys);
}
