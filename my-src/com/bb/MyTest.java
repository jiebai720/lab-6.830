package com.bb;

public class MyTest {


    public static void main(String args[]){



        for (int i = 0; i < 18; i++) {
            int index = i/8 ;
            System.out.println( i+"---"+ index );

            int index2=  i%8 ;
            System.out.println( i+">>>>>>>"+ index2 );
        }

        byte b = 1 ;

        for (int i = 0; i < 8; i++) {
            System.out.println( i + "=== lll ===" + getByte( b , i ) );
        }

        int[] lengths = new int[] { 1, 2, 1000 };

        for (int len: lengths) {
//            System.out.println( "len :::" + len );
        }

    }

    static int getByte( byte b,int bit) {
        if( bit < 0 || bit > 7)
            return 0;
        return ( b & (0b1 << bit)) > 0 ? 1: 0;
    }



}
