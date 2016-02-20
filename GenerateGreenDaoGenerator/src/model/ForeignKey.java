package model;

/**
 * Created by neda.vukman on 10/29/2015.
 */
public class ForeignKey {

    private String id;
    private String referencesTable;
    private String fromPrimaryKey;
    private String toForeignKey;

    public ForeignKey() {
    }

    public ForeignKey(String id, String referencesTable, String fromPrimaryKey, String toForeignKey) {
        this.id = id;
        this.referencesTable = referencesTable;
        this.fromPrimaryKey = fromPrimaryKey;
        this.toForeignKey = toForeignKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReferencesTable() {
        return referencesTable;
    }

    public void setReferencesTable(String referencesTable) {
        this.referencesTable = referencesTable;
    }

    public String getFromPrimaryKey() {
        return fromPrimaryKey;
    }

    public void setFromPrimaryKey(String fromPrimaryKey) {
        this.fromPrimaryKey = fromPrimaryKey;
    }

    public String getToForeignKey() {
        return toForeignKey;
    }

    public void setToForeignKey(String toForeignKey) {
        this.toForeignKey = toForeignKey;
    }
}
