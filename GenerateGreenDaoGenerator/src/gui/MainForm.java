package gui;

import utils.ModelGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by neda.vukman on 10/29/2015.
 */
public class MainForm extends JFrame{
    private JPanel rootPanel;
    private JTextField textFieldDatabasePath;
    private JButton buttonGenerateModel;
    private JTextField textFieldProjectPath;
    private JButton buttonSelectDBFile;

    String databasePath = "";
    String projectPath = "";
    ModelGenerator generator;

    public MainForm() {
        super("Generate dao model");

        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textFieldDatabasePath.setEditable(false);

        buttonGenerateModel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                databasePath = textFieldDatabasePath.getText().toString();
                projectPath = textFieldProjectPath.getText().toString();
                if (!databasePath.equals("") && !projectPath.equals("")){
                    System.out.println( "Database path: " + databasePath);
                    System.out.println( "Project path: " + projectPath);

                    generator = new ModelGenerator(databasePath, projectPath + ".dao"); //path of android project where you want your model to be in eg: projectPath -> com.example.nedavukman.testsqliteimport
                    try {
                        generator.generateModel();
                        System.out.println("Model generated");
                        generator.closeConnectionDB();

                        JOptionPane.showMessageDialog(rootPanel, "Model successfully generated!");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });


        buttonSelectDBFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();
                if (f!=null){
                    String fileName = f.getAbsolutePath();
                    textFieldDatabasePath.setText(fileName);
                }
            }
        });

        setVisible(true);
    }
}
