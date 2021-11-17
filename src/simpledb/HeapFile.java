package simpledb;

import java.io.*;
import java.util.*;

/**
 * HeapFile is an implementation of a DbFile that stores a collection
 * of tuples in no particular order.  Tuples are stored on pages, each of
 * which is a fixed size, and the file is simply a collection of those
 * pages. HeapFile works closely with HeapPage.  The format of HeapPages
 * is described in the HeapPage constructor.
 *
 * @see simpledb.HeapPage#HeapPage
 * @author Sam Madden
 */
public class HeapFile implements DbFile {


    File file ;
    TupleDesc tupleDesc ;

    /**
     * Constructs a heap file backed by the specified file.
     *
     * @param f the file that stores the on-disk backing store for this heap file.
     */
    public HeapFile(File f, TupleDesc td) {
        // some code goes here
        this.file = f ;
        this.tupleDesc = td ;
    }

    /**
     * Returns the File backing this HeapFile on disk.
     *
     * @return the File backing this HeapFile on disk.
     */
    public File getFile() {
        // some code goes here
        return this.file ;
    }

    /**
    * Returns an ID uniquely identifying this HeapFile. Implementation note:
    * you will need to generate this tableid somewhere ensure that each
    * HeapFile has a "unique id," and that you always return the same value
    * for a particular HeapFile. We suggest hashing the absolute file name of
    * the file underlying the heapfile, i.e. f.getAbsoluteFile().hashCode().
    *
    * @return an ID uniquely identifying this HeapFile.
    */
    public int getId() {

        return  this.file.getAbsolutePath().hashCode() ;
        // some code goes here
//        throw new UnsupportedOperationException("implement this");
    }
    
    /**
     * Returns the TupleDesc of the table stored in this DbFile.
     * @return TupleDesc of this DbFile.
     */
    public TupleDesc getTupleDesc() {

        return this.tupleDesc ;
    	// some code goes here
//    	throw new UnsupportedOperationException("implement this");
    }

    // see DbFile.java for javadocs
    public Page readPage(PageId pid) {
        // some code goes here

        HeapPage page = null ;

        for (int i = 0; i < numPages() ; i++) {

            if( i  ==  pid.pageno() ){
                try {
                    byte[]  data = getContent( this.file , Database.getBufferPool().PAGE_SIZE , i*Database.getBufferPool().PAGE_SIZE ) ;
                    page = new HeapPage( (HeapPageId) pid , data ) ;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        return page ;
    }

    /**
     *
     * <p>Title: getContent</p>
     * <p>Description:根据文件路径读取文件转出byte[] </p>
     * @return 字节数组
     * @throws IOException
     */
    public static byte[] getContent( File file , int pageSize  ,int skipPosition ) throws IOException {

        FileInputStream fi = new FileInputStream(file);
        fi.skip( skipPosition );

        byte[] buffer = new byte[pageSize];
        int numRead = 0 ;
        int  offset = 0 ;
        while ( offset < buffer.length
                && (numRead = fi.read(buffer, offset , buffer.length - offset)) >= 0) {
            offset += numRead;
        }

        // 确保所有数据均被读取
        if (offset != buffer.length) {
            throw new IOException("Could not completely read file "
                    + file.getName());
        }
        fi.close();
        return buffer ;
    }


    // see DbFile.java for javadocs
    public void writePage(Page page) throws IOException {
        // some code goes here
        // not necessary for lab1
    }

    /**
     * Returns the number of pages in this HeapFile.
     */
    public int numPages() {
        // some code goes here

        long fileSizeBytes = this.file.length()  ;
        long pageSize = Integer.valueOf(Database.getBufferPool().PAGE_SIZE).longValue();
        long a = fileSizeBytes / pageSize ;
        if( fileSizeBytes % pageSize >0 ) {
            a++;
        }
        return   Double.valueOf(a).intValue()  ;
    }

    // see DbFile.java for javadocs
    public ArrayList<Page> addTuple(TransactionId tid, Tuple t)
        throws DbException, IOException, TransactionAbortedException {
        // some code goes here
        return null;
        // not necessary for lab1
    }

    // see DbFile.java for javadocs
    public Page deleteTuple(TransactionId tid, Tuple t)
        throws DbException, TransactionAbortedException {
        // some code goes here
        return null;
        // not necessary for lab1
    }

    // see DbFile.java for javadocs
    public DbFileIterator iterator(TransactionId tid) {
        // some code goes here

        List<Tuple> tupleList = new ArrayList() ;

        for (int i = 0; i < numPages() ; i++) {

            HeapPageId heapPageId = new HeapPageId( getId() , i );

            try {
                Page page = Database.getBufferPool().getPage( tid , heapPageId  , Permissions.READ_ONLY ) ;
                HeapPage heapPage = (HeapPage) page ;
                if( page instanceof  HeapPage) {

                    Iterator<Tuple>  tupleIterator = heapPage.iterator();
                    while ( tupleIterator.hasNext()) {
                        Tuple tuple = (Tuple) tupleIterator.next();
                        tupleList.add( tuple ) ;
                    }
                }
            } catch (TransactionAbortedException e) {
                e.printStackTrace();
            } catch (DbException e) {
                e.printStackTrace();
            }
        }

        DbFileIterator tupleIterator = new HeapFileIterator( tupleList ) ;

        return tupleIterator  ;
    }
    
}

