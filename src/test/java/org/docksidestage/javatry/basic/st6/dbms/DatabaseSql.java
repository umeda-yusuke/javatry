package org.docksidestage.javatry.basic.st6.dbms;

/**
 * The database SQL
 * @author umeaa-yusuke
 */
public abstract class DatabaseSql {

    public abstract String buildPagingQuery(int pageSize, int pageNumber);
}
