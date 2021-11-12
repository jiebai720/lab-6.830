package com.bb;

public class My {


    public static void main(String args[]){



        for (int i = 0; i < 17; i++) {

            int index = i/8 ;
            System.out.println( i+"---"+ index );
        }

        int[] lengths = new int[] { 1, 2, 1000 };

        for (int len: lengths) {
            System.out.println( "len :::" + len );
        }

    }


}
