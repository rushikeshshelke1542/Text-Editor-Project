import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;

    JMenuBar menuBar;

    JTextArea textArea;
    JMenu file, edit;

    JMenuItem newFile, openFile, saveFile;


    JMenuItem cut, copy, paste, selectAll, close;




    public TextEditor() {

        //initialize frame
        frame = new JFrame();

        //initialize menu bar
        menuBar = new JMenuBar();

        //initialize text area

        textArea = new JTextArea();

        //initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //initialize menu items for file
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //add action listener for file items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //add file menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //initialize menu items for edit
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //add action listener for edit items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //add edit menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //add menu into menu bar
        menuBar.add(file);
        menuBar.add(edit);

        //set menu Bar into frame
        frame.setJMenuBar(menuBar);

        //create a panel
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        //add text area to panel
        panel.add(textArea, BorderLayout.CENTER);

        //add scroller to panel
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);

        //add panel to frame
        frame.add(panel);
        //get size and set as per window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Set the size of the frame to match the screen size
        frame.setSize(screenWidth, screenHeight);
        //set frame
       // frame.setBounds(0,0,400,400);
        frame.setVisible(true);
        frame.setLayout(null);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            textArea.cut();
        }

        if(actionEvent.getSource()==copy){
            textArea.copy();
        }

        if(actionEvent.getSource()==paste){
            textArea.paste();
        }

        if(actionEvent.getSource()==selectAll){
            textArea.selectAll();
        }

        if(actionEvent.getSource()==close){
            System.exit(0);
        }

        if(actionEvent.getSource()==openFile){

            //open file chooser
            JFileChooser fileChooser = new JFileChooser("/Users");

            //check open selected or cancel
            int chooseOption = fileChooser.showOpenDialog(null);

            if(chooseOption==JFileChooser.APPROVE_OPTION){

                //get the selected file
                File file = fileChooser.getSelectedFile();
                //get file path
                String filePath = file.getPath();

                try{
                    //read the file
                    FileReader fileReader = new FileReader(filePath);

                    //it is just like a scanner class to scan the file text
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    //create 2 string to copy content of selected file and paste it to new file
                    String intermediate = "";
                    StringBuilder output = new StringBuilder();

                    while((intermediate = bufferedReader.readLine())!=null){
                        output.append(intermediate).append("\n");
                    }

                    textArea.setText(output.toString());

                } catch (IOException e) {
                   e.printStackTrace();
               }

            }
        }

        if(actionEvent.getSource()==saveFile){

            //get the file chooser
            JFileChooser fileChooser = new JFileChooser("/Users");

            //open the save dialog
            int chooseOption = fileChooser.showSaveDialog(null);

            //check save is selected or cancel
            if(chooseOption==JFileChooser.APPROVE_OPTION){

                //create a new file in current directory with given name

                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");

                try{
                    //initialize file writer
                    FileWriter fileWriter = new FileWriter(file);

                    //now user buffer write to write
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    textArea.write(bufferedWriter);

                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        if(actionEvent.getSource()==newFile){
            TextEditor textEditor = new TextEditor();
        }

    }

    public static void main(String[] args) {

        TextEditor texteditor = new TextEditor();

    }
}