package db;

import model.Attribute;
import model.ForeignKey;
import model.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neda.vukman on 10/29/2015.
 */
public class DatabaseBroker {

    Connection connection = null;;

    public DatabaseBroker(String dbPath) {
        openConnectionToDatabase(dbPath);
    }

    private void openConnectionToDatabase(String dbPath){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            System.out.println("Opened database successfully");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void closeConnectionToDatabase(){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> getAllTableNames(){
        List<String> tableNames = new ArrayList<>();
        Statement stmt = null;

        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT tbl_name FROM sqlite_master WHERE type = 'table'" );

            while ( rs.next() ) {
                String name = rs.getString("tbl_name");
                tableNames.add(name);
            }
            rs.close();
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return tableNames;
    }

    public Table getTableDetails(String tableName){
        Table table = new Table();
        List<Attribute> attributeList = new ArrayList<>();
        List<ForeignKey> foreignKeyList = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( "PRAGMA table_info('" + tableName + "')" );

            while ( rs.next() ) {
                Attribute attribute = new Attribute();
                attribute.setId(rs.getString("cid"));
                attribute.setName(rs.getString("name"));
                attribute.setType(rs.getString("type"));
                attribute.setNotNull(rs.getString("notnull"));
                attribute.setPrimaryKey(rs.getString("pk"));

                attributeList.add(attribute);
            }
            table.setTableName(tableName);
            table.setAttributeList(attributeList);

            rs = stmt.executeQuery( "PRAGMA foreign_key_list('" + tableName + "')" );

            while (rs.next()){
                ForeignKey foreignKey = new ForeignKey();
                foreignKey.setId(rs.getString("id"));
                foreignKey.setReferencesTable(rs.getString("table"));
                foreignKey.setFromPrimaryKey(rs.getString("from"));
                foreignKey.setToForeignKey(rs.getString("to"));

                foreignKeyList.add(foreignKey);
            }
            table.setForeignKeys(foreignKeyList);
            rs.close();
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return table;
    }





}
