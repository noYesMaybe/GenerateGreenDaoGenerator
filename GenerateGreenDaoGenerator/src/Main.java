import db.DatabaseBroker;
import gui.MainForm;
import model.Table;
import utils.ModelGenerator;

import java.sql.*;
import java.util.List;

/**
 * Created by neda.vukman on 10/28/2015.
 */
public class Main {

    static ModelGenerator generator;

    public static void main(String args[]) {
        MainForm mainForm = new MainForm();

    }
}

//        generator = new ModelGenerator("C:\\tempdb\\electiondata.db3", "com.example.nedavukman.testsqliteimport"+".dao"); //putanja projekta u kom zelis da ti se nalazi model
//        try {
//            generator.generateModel();
//            System.out.println( "Model generated");
//            generator.closeConnectionDB();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Connection c = null;
//        Statement stmt = null;
//        try {
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:C:\\tempdb\\electiondata.db3");
//            System.out.println("Opened database successfully");
//
//            stmt = c.createStatement();
//            ResultSet rs = stmt.executeQuery( "SELECT * FROM VI_Tabulator");
//
//            while ( rs.next() ) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
////                int age  = rs.getInt("age");
////                String address = rs.getString("address");
////                float salary = rs.getFloat("salary");
//                System.out.println( "ID = " + id );
//                System.out.println( "NAME = " + name );
//            }
//            rs.close();
//            stmt.close();
//            c.close();
//        } catch ( Exception e ) {
//            System.err.println(e.getClass().getName() + ": " + e.getMessage() );
//            System.exit(0);
//        }