package model;

/**
 * Created by neda.vukman on 10/27/2015.
 */
public class Attribute {

    private String id;
    private String name;
    private String type;
    private String notNull;
    private String primaryKey;

    private boolean isForeignKey;

    public Attribute() {
    }

    public Attribute(String id, String name, String type, String notNull, String primaryKey, Boolean isForeignKey) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.notNull = notNull;
        this.primaryKey = primaryKey;
        this.isForeignKey = isForeignKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotNull() {
        return notNull;
    }

    public void setNotNull(String notNull) {
        this.notNull = notNull;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public boolean isForeignKey() {
        return isForeignKey;
    }

    public void setIsForeignKey(boolean isForeignKey) {
        this.isForeignKey = isForeignKey;
    }
}
