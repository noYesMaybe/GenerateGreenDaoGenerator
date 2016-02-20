package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neda.vukman on 10/27/2015.
 */
public class Table {

    private String tableName;
    private List<Attribute> attributeList;
    private List<ForeignKey> foreignKeys;

    //sa kojom je tabelom vezan


    public Table() {
        foreignKeys = new ArrayList<>();
    }

    public Table(String tableName, List<Attribute> attributeList) {
        this.tableName = tableName;
        this.attributeList = attributeList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }

    public List<ForeignKey> getForeignKeys() {
        return foreignKeys;
    }

    public void setForeignKeys(List<ForeignKey> foreignKeys) {
        this.foreignKeys = foreignKeys;
    }
}
