package simpledb;
import java.util.*;

/**
 * SeqScan is an implementation of a sequential scan access method that reads
 * each tuple of a table in no particular order (e.g., as they are laid out on
 * disk).
 */
public class SeqScan implements DbIterator {


    TransactionId tid ;
    int tableid ;
    String tableAlias ;

    int position = -1 ;
    DbFileIterator dbFileIterator ;
    DbFile dbFile ;
    /**
     * Creates a sequential scan over the specified table as a part of the
     * specified transaction.
     *
     * @param tid The transaction this scan is running as a part of.
     * @param tableid the table to scan.
     * @param tableAlias the alias of this table (needed by the parser);
     *         the returned tupleDesc should have fields with name tableAlias.fieldName
     *         (note: this class is not responsible for handling a case where tableAlias
     *         or fieldName are null.  It shouldn't crash if they are, but the resulting
     *         name can be null.fieldName, tableAlias.null, or null.null).
     */
    public SeqScan( TransactionId tid, int tableid, String tableAlias) {
        // some code goes here
        this.tid = tid ;
        this.tableAlias = tableAlias ;
        this.tableid = tableid ;

    }

    public void open()
        throws DbException, TransactionAbortedException {
        // some code goes here

        dbFile = Database.getCatalog().getDbFile( tableid );
        dbFileIterator = dbFile.iterator( tid );
        dbFileIterator.open();
    }

    /**
     * Returns the TupleDesc with field names from the underlying HeapFile,
     * prefixed with the tableAlias string from the constructor.
     * @return the TupleDesc with field names from the underlying HeapFile,
     * prefixed with the tableAlias string from the constructor.
     */
    public TupleDesc getTupleDesc() {
        // some code goes here
        return dbFile.getTupleDesc();
    }

    public boolean hasNext() throws TransactionAbortedException, DbException {
        // some code goes here

        if( dbFileIterator.hasNext() )
            return true ;

        return false;
    }

    public Tuple next()
        throws NoSuchElementException, TransactionAbortedException, DbException {
        // some code goes here

        if( dbFileIterator.hasNext() ){
            return  dbFileIterator.next();
        }

        return null;
    }

    public void close() {
        // some code goes here
        dbFileIterator.close();
    }

    public void rewind()
        throws DbException, NoSuchElementException, TransactionAbortedException {
        // some code goes here

        dbFileIterator.rewind();
    }

}
