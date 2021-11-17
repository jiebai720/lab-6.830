package simpledb;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *  个人自定义
 */
public class HeapFileIterator implements  DbFileIterator {



    List<Tuple> list ;
    int position = -1 ;


    public HeapFileIterator( List<Tuple> list) {
        this.list = list ;
    }

    @Override
    public void open() throws DbException, TransactionAbortedException {
        position = 0;
        System.out.println( "open.....");
    }

    @Override
    public boolean hasNext() throws DbException, TransactionAbortedException {

        if( position >= 0 && position < this.list.size() ) {
            if( this.list != null && this.list.size() > 0 ){
                if( this.list.get(position) != null )
                    return true ;
            }
        }

        return false;
    }

    @Override
    public Tuple next() throws DbException, TransactionAbortedException, NoSuchElementException {

        if( position < 0) {
                throw  new NoSuchElementException("not open");
        }
        position++ ;
        return this.list.get(position-1) ;
    }

    @Override
    public void rewind() throws DbException, TransactionAbortedException {

        position = 0 ;
    }

    @Override
    public void close() {
        position = -1 ;
    }


}
