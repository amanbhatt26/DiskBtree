package org.mydb;

import org.mydb.btree.LeafPage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LeafPage page = new LeafPage(12);

        page.insertKey(10);
        page.insertKey(20);
        page.insertKey(30);

        ByteBuffer pageLayout = page.byteLayout();



        try {
            var file = new RandomAccessFile("mypage.page", "rws");
            file.seek(10);
            file.write(pageLayout.array());

            file.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        try {
            var file = new RandomAccessFile("mypage.page", "rws");
            file.seek(10);
            file.read(pageLayout.array());

            file.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LeafPage newPage = LeafPage.createLeafPage(pageLayout);
    }
}