package utils;

import db.DatabaseBroker;
import de.greenrobot.daogenerator.*;
import model.AttrTypes;
import model.Attribute;
import model.ForeignKey;
import model.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neda.vukman on 10/29/2015.
 */
public class ModelGenerator {

    private static final String PROJECT_DIR = System.getProperty("user.dir").replace("\\", "/");
    private static final String OUT_DIR = PROJECT_DIR + "/src";

    DatabaseBroker databaseBroker;
    List<String> tableNamesList;
    String projectPath;

    List<Table> allTables;
    Schema schema;

    public ModelGenerator(String databasePath, String projectPath) {
        databaseBroker = new DatabaseBroker(databasePath);
        tableNamesList = new ArrayList<>();
        allTables = new ArrayList<>();
        this.projectPath = projectPath;

        initializeTableNames();
        allTables = getAllTables();
    }

    private void initializeTableNames(){
        tableNamesList = databaseBroker.getAllTableNames();
    }

    public void generateModel() throws Exception {

         schema = new Schema(1, projectPath); //D:\Neda\IdeaProjects\GenerateDaoGenerator\src   // com.example.nedavukman.testsqliteimport.dao

        for (Table table: allTables) {

            Entity entity = schema.addEntity(table.getTableName());
            entity.setTableName(table.getTableName());

            System.out.println("Table name: " + table.getTableName());
            //set properties
            for (Attribute attr: table.getAttributeList()){
                if (isAttributeForeignKey(table, attr)){
                    continue;
                }
                String name = attr.getName();
                String attrName = Character.toLowerCase(name.charAt(0)) + name.substring(1); //attribute name
                String attrType = attr.getType().toUpperCase(); //attribute type
                if (attr.getPrimaryKey().equals("1")){
                    entity.addIdProperty().autoincrement().columnName(attrName);
                }else {
                    if (attrType.startsWith(AttrTypes.NTEXT)){
                        if (attr.getNotNull().equals("1")){
                            entity.addStringProperty(attrName).columnName(attrName).notNull();
                        }else {
                            entity.addStringProperty(attrName).columnName(attrName);
                        }
                    }else if (attrType.startsWith(AttrTypes.TEXT)){
                        if (attr.getNotNull().equals("1")){
                            entity.addStringProperty(attrName).columnName(attrName).notNull();
                        }else {
                            entity.addStringProperty(attrName).columnName(attrName);
                        }
                    }else if (attrType.startsWith(AttrTypes.NVARCHAR)){
                        if (attr.getNotNull().equals("1")){
                            entity.addStringProperty(attrName).columnName(attrName).notNull();
                        }else {
                            entity.addStringProperty(attrName).columnName(attrName);
                        }
                    }else if (attrType.startsWith(AttrTypes.VARCHAR)){
                        if (attr.getNotNull().equals("1")){
                            entity.addStringProperty(attrName).columnName(attrName).notNull();
                        }else {
                            entity.addStringProperty(attrName).columnName(attrName);
                        }
                    }else if (attrType.startsWith(AttrTypes.INT)){
                        if (attr.getNotNull().equals("1")){
                            entity.addIntProperty(attrName).columnName(attrName).notNull();
                        }else {
                            entity.addIntProperty(attrName).columnName(attrName);
                        }
                    }else if (attrType.startsWith(AttrTypes.INTEGER)){
                        if (attr.getNotNull().equals("1")){
                            entity.addIntProperty(attrName).columnName(attrName).notNull();
                        }else {
                            entity.addIntProperty(attrName).columnName(attrName);
                        }
                    }else if (attrType.startsWith(AttrTypes.DATE)){
                        if (attr.getNotNull().equals("1")){
                            entity.addDateProperty(attrName).columnName(attrName).notNull();
                        }else {
                            entity.addDateProperty(attrName).columnName(attrName);
                        }
                    }else if (attrType.startsWith(AttrTypes.BOOLEAN)){
                        if (attr.getNotNull().equals("1")){
                            entity.addBooleanProperty(attrName).columnName(attrName).notNull();
                        }else {
                            entity.addBooleanProperty(attrName).columnName(attrName);
                        }
                    }else if (attrType.startsWith(AttrTypes.BIGINT)){
                        if (attr.getNotNull().equals("1")){
                            entity.addLongProperty(attrName).columnName(attrName).notNull();
                        }else {
                            entity.addLongProperty(attrName).columnName(attrName);
                        }
                    }else if (attrType.startsWith(AttrTypes.LONG)){
                        if (attr.getNotNull().equals("1")){
                            entity.addLongProperty(attrName).columnName(attrName).notNull();
                        }else {
                            entity.addLongProperty(attrName).columnName(attrName);
                        }
                    }else if (attrType.startsWith(AttrTypes.BIT)){
                        if (attr.getNotNull().equals("1")){
                            entity.addByteProperty(attrName).columnName(attrName).notNull();
                        }else {
                            entity.addByteProperty(attrName).columnName(attrName);
                        }
                    }else if (attrType.startsWith(AttrTypes.BLOB)){
                        if (attr.getNotNull().equals("1")){
                            entity.addByteArrayProperty(attrName).columnName(attrName).notNull();
                        }else {
                            entity.addByteArrayProperty(attrName).columnName(attrName);
                        }
                    }else if (attrType.startsWith(AttrTypes.VARBINARY)){
                        if (attr.getNotNull().equals("1")){
                            entity.addByteArrayProperty(attrName).columnName(attrName).notNull();
                        }else {
                            entity.addByteArrayProperty(attrName).columnName(attrName);
                        }
                    }
                }
            }
        }

        //set relationships
        for (Table table: allTables){
            if (table.getForeignKeys().size() > 0){
                Entity ent = getEntityByTableName(table.getTableName());
                for (ForeignKey fk: table.getForeignKeys()){
                    String fkPropertyName = Character.toLowerCase(fk.getFromPrimaryKey().charAt(0)) + fk.getFromPrimaryKey().substring(1);
                    Property fkProperty = ent.addLongProperty(fkPropertyName).columnName(fkPropertyName).notNull().getProperty();
                    Entity refEntity = getEntityByTableName(fk.getReferencesTable());
                    ent.addToOne(refEntity, fkProperty);

                    String relName ="list"+table.getTableName();
                    ToMany toManyRelationship = refEntity.addToMany(ent, fkProperty);
                    toManyRelationship.setName(relName);
                }
            }
        }
        new DaoGenerator().generateAll(schema, OUT_DIR);
    }

    public void closeConnectionDB(){
        databaseBroker.closeConnectionToDatabase();
    }

    public List<Table> getAllTables(){
        List<Table> tables = new ArrayList<>();
        for (int i = 0; i < tableNamesList.size(); i++) {
            if (tableNamesList.get(i).equals("sqlite_sequence") || tableNamesList.get(i).equals("android_metadata") || tableNamesList.get(i).equals("sqlite_master")){
                continue;
            }
            Table table = databaseBroker.getTableDetails(tableNamesList.get(i));
            tables.add(table);
        }
        return tables;
    }

    public Table getTableByName(String tableName){
        Table table = new Table();
        for (Table t: allTables){
            if (t.getTableName().equals(tableName)){
                table = t;
                break;
            }
        }
        return table;
    }

    public Entity getEntityByTableName(String tableName){
        List<Entity> entities = schema.getEntities();
        Entity entity = null;
        for (Entity en: entities){
            if (en.getTableName().equals(tableName)){
                entity = en;
                break;
            }
        }
        return entity;
    }

    public boolean isAttributeForeignKey(Table table, Attribute attribute){
        boolean result = false;
        for (ForeignKey fk: table.getForeignKeys()){
            if (fk.getFromPrimaryKey().equals(attribute.getName())){
                result = true;
            }
        }
        return result;
    }

}
