package org.docksidestage.javatry.basic.st6.dbms;

/**
 * The database SQL
 * @author umeaa-yusuke
 */
public abstract class Database {

    public abstract String buildPagingQuery(int pageSize, int pageNumber);

    protected int calculateOffset(int pageSize, int pageNumber) {
        return pageSize * (pageNumber - 1);
    }
}
