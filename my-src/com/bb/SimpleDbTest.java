package com.bb;


import simpledb.*;

import java.io.File;

public class SimpleDbTest {


    public static void main (String args[])   throws  Exception {

        Type types[] = new Type[]{ Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE };
        String names[] = new String[]{ "field0", "field1", "field2" };
        TupleDesc descriptor = new TupleDesc( types , names );

        HeapFile table1 = new HeapFile(new File("some_data_file.dat"), descriptor);
        Database.getCatalog().addTable(table1, "test");


        TransactionId tid = new TransactionId();
        SeqScan f = new SeqScan(tid, table1.getId(), "test");

        try {
// and run it
            f.open();
            while (f.hasNext()) {
                Tuple tup = f.next();
                System.out.println( tup );
            }
            f.close();
            Database.getBufferPool().transactionComplete(tid);
        } catch (Exception e) {
            System.out.println ("Exception : " + e);
        }

    }


}
