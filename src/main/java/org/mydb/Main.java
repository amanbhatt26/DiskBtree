package org.mydb;

import org.mydb.btree.LeafPage;
import org.mydb.btree.Overflow;
import org.mydb.btree.Page;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Page page = new LeafPage(12);

        Overflow of;
        of = page.add(20);
        of = page.add(10);
        of = page.add(30);
        of = page.add(31);
        of = page.add(55);
        of = page.add(99);
        of = page.add(101);
        of = page.add(102);

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

        LeafPage newPage = new LeafPage(pageLayout);
    }
}