package com.bb;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class MainTest {


    public static void main (String args[])
            throws  Exception {

        System.out.println( 1/4 + "--" + 5/4 );

        ByteArrayOutputStream pageBAOS = new ByteArrayOutputStream( 4096 );
        DataOutputStream pageStream = new DataOutputStream(pageBAOS);
        int c = 865535 ;

        char buf[] = new char[1024];
        buf[0] =  (char)c ;

        short a =  32767 ;
        System.out.println( Short.MAX_VALUE + "---" + a );
        String abc = String.valueOf(a);

        try {
            String s = new String(buf, 0, 1);
//            pageStream.writeShort( Integer.parseInt(s.trim()) );
//            pageStream.writeInt( Integer.parseInt(s.trim()) );
            pageStream.writeInt( Integer.parseInt(abc)  );
        } catch (NumberFormatException e) {
              e.printStackTrace()  ;
        }

        FileOutputStream os = new FileOutputStream("D:\\AppServ\\2.dat");
        pageStream.flush();
        pageBAOS.writeTo( os );

    }



}
