package simpledb;
import java.util.*;

/**
 * TupleDesc describes the schema of a tuple.
 */
public class TupleDesc {

    private  Type[] types ;
    private  String[] fieldStrings ;

    /**
     * Merge two TupleDescs into one, with td1.numFields + td2.numFields
     * fields, with the first td1.numFields coming from td1 and the remaining
     * from td2.
     * @param td1 The TupleDesc with the first fields of the new TupleDesc
     * @param td2 The TupleDesc with the last fields of the TupleDesc
     * @return the new TupleDesc
     */
    public static TupleDesc combine(TupleDesc td1, TupleDesc td2) {

        int length  = td1.numFields() + td2.numFields() ;
        Type[] td3 =  new Type[length] ;
        String[] fieldStrings = new String[length] ;

        for (int i=0 ; i< length; i++){
            if( i <= td1.numFields()-1 ){
                td3[i] = td1.getType(i) ;
                fieldStrings[i] = td1.fieldStrings[i] ;
            }else{
                td3[i] = td2.getType(i-td1.numFields() ) ;
                fieldStrings[i] = td2.fieldStrings[ i-td1.numFields()] ;
            }
        }

        return new TupleDesc( td3 ,  fieldStrings ) ;
    }

    /**
     * Create a new TupleDesc with typeAr.length fields with fields of the
     * specified types, with associated named fields.
     *
     * @param typeAr array specifying the number of and types of fields in
     *        this TupleDesc. It must contain at least one entry.
     * @param fieldAr array specifying the names of the fields. Note that names may be null.
     */
    public TupleDesc(Type[] typeAr, String[] fieldAr) {
        // some code goes here
        this.types = typeAr ;
        this.fieldStrings = fieldAr ;
    }

    /**
     * Constructor.
     * Create a new tuple desc with typeAr.length fields with fields of the
     * specified types, with anonymous (unnamed) fields.
     *
     * @param typeAr array specifying the number of and types of fields in
     *        this TupleDesc. It must contain at least one entry.
     */
    public TupleDesc(Type[] typeAr) {
        // some code goes here
        types = typeAr ;
    }

    /**
     * @return the number of fields in this TupleDesc
     */
    public int numFields() {
        // some code goes here
        return types.length ;
    }

    /**
     * Gets the (possibly null) field name of the ith field of this TupleDesc.
     *
     * @param i index of the field name to return. It must be a valid index.
     * @return the name of the ith field
     * @throws NoSuchElementException if i is not a valid field reference.
     */
    public String getFieldName(int i) throws NoSuchElementException {
        // some code goes here

        if( fieldStrings != null && fieldStrings.length > 0)
            return fieldStrings[i] ;

        return null;
    }

    /**
     * Find the index of the field with a given name.
     *
     * @param name name of the field.
     * @return the index of the field that is first to have the given name.
     * @throws NoSuchElementException if no field with a matching name is found.
     */
    public int nameToId(String name) throws NoSuchElementException {
        // some code goes here
        if( name == null || name.equals("") ){
            throw new  NoSuchElementException("No Such Element Exception");
        }

        if( fieldStrings != null && fieldStrings.length >0){
            for (int i = 0; i < fieldStrings.length ; i++) {
                if(fieldStrings[i].equals(name )){
                    return  i ;
                }
            }
        }
        throw new  NoSuchElementException("No Such Element Exception");
    }

    /**
     * Gets the type of the ith field of this TupleDesc.
     *
     * @param i The index of the field to get the type of. It must be a valid index.
     * @return the type of the ith field
     * @throws NoSuchElementException if i is not a valid field reference.
     */
    public Type getType(int i) throws NoSuchElementException {
        // some code goes here
        return types[i] ;
    }

    /**
     * @return The size (in bytes) of tuples corresponding to this TupleDesc.
     * Note that tuples from a given TupleDesc are of a fixed size.
     */
    public int getSize() {
        // some code goes here

        if( types.length > 0 ){
            return  types.length * getType(0).getLen() ;
        }
        return 0 ;
    }

    /**
     * Compares the specified object with this TupleDesc for equality.
     * Two TupleDescs are considered equal if they are the same size and if the
     * n-th type in this TupleDesc is equal to the n-th type in td.
     *
     * @param o the Object to be compared for equality with this TupleDesc.
     * @return true if the object is equal to this TupleDesc.
     */
    public boolean equals(Object o) {
        // some code goes here
        if( o == null ){
            return false;
        }
        if( !(o instanceof  TupleDesc ) ){
            return false ;
        }

        TupleDesc tupleDescTemp = (TupleDesc) o ;

        if( this.getSize() != tupleDescTemp.getSize() ){
            return false ;
        }

        for (int i = 0; i < this.types.length ; i++) {
            if( !this.getType(i).equals( tupleDescTemp.getType(i) ) ){
                return false  ;
            }
        }

        return true ;
    }

    public int hashCode() {
        // If you want to use TupleDesc as keys for HashMap, implement this so
        // that equal objects have equals hashCode() results
        throw new UnsupportedOperationException("unimplemented");
    }

    /**
     * Returns a String describing this descriptor. It should be of the form
     * "fieldType[0](fieldName[0]), ..., fieldType[M](fieldName[M])", although
     * the exact format does not matter.
     * @return String describing this descriptor.
     */
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder() ;

        for (int i = 0; i < types.length ; i++) {

            stringBuilder.append( getType(i) + "[" + getFieldName(i) +"],") ;
        }

        return stringBuilder.toString() ;
    }

}
