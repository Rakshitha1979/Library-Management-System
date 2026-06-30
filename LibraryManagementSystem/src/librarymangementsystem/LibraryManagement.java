package librarymangementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class LibraryManagement extends JFrame implements ActionListener {

    private JLabel lblTitle, lblBookId, lblBookName, lblAuthor, lblPublisher;

    private JTextField txtBookId, txtBookName, txtAuthor, txtPublisher;

    private JButton btnAdd, btnSearch, btnIssue, btnReturn, btnDisplay, btnClear, btnExit;
    private JTable table;
    private JScrollPane scrollPane;
    


    public LibraryManagement() {

        setTitle("Library Management System");
        setSize(850, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Heading
        lblTitle = new JLabel("LIBRARY MANAGEMENT SYSTEM");
        lblTitle.setBounds(250, 20, 300, 30);
        add(lblTitle);

        // Book ID
        lblBookId = new JLabel("Book ID:");
        lblBookId.setBounds(50, 80, 100, 30);
        add(lblBookId);

        txtBookId = new JTextField();
        txtBookId.setBounds(160, 80, 200, 30);
        add(txtBookId);

        // Book Name
        lblBookName = new JLabel("Book Name:");
        lblBookName.setBounds(50, 130, 100, 30);
        add(lblBookName);

        txtBookName = new JTextField();
        txtBookName.setBounds(160, 130, 200, 30);
        add(txtBookName);

        // Author
        lblAuthor = new JLabel("Author:");
        lblAuthor.setBounds(50, 180, 100, 30);
        add(lblAuthor);

        txtAuthor = new JTextField();
        txtAuthor.setBounds(160, 180, 200, 30);
        add(txtAuthor);

        // Publisher
        lblPublisher = new JLabel("Publisher:");
        lblPublisher.setBounds(50, 230, 100, 30);
        add(lblPublisher);

        txtPublisher = new JTextField();
        txtPublisher.setBounds(160, 230, 200, 30);
        add(txtPublisher);

        // Buttons
        btnAdd = new JButton("Add Book");
        btnAdd.setBounds(420, 80, 150, 30);
        add(btnAdd);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(420, 120, 150, 30);
        add(btnSearch);

        btnIssue = new JButton("Issue Book");
        btnIssue.setBounds(420, 160, 150, 30);
        add(btnIssue);

        btnReturn = new JButton("Return Book");
        btnReturn.setBounds(420, 200, 150, 30);
        add(btnReturn);

        btnDisplay = new JButton("Display Books");
        btnDisplay.setBounds(420, 240, 150, 30);
        add(btnDisplay);

        btnClear = new JButton("Clear");
        btnClear.setBounds(420, 280, 150, 30);
        add(btnClear);

        btnExit = new JButton("Exit");
        btnExit.setBounds(420, 320, 150, 30);
        add(btnExit);

        // Action Listeners
        btnAdd.addActionListener(this);
        btnSearch.addActionListener(this);
        btnIssue.addActionListener(this);
        btnReturn.addActionListener(this);
        btnDisplay.addActionListener(this);
        btnClear.addActionListener(this);
        btnExit.addActionListener(this);

        // Create Table
String[] columns = {"Book ID", "Book Name", "Author", "Publisher", "Available"};

DefaultTableModel model = new DefaultTableModel(columns, 0);

table = new JTable(model);

scrollPane = new JScrollPane(table);
scrollPane.setBounds(30, 380, 730, 150);

add(scrollPane);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAdd) {

   try {

    int id = Integer.parseInt(txtBookId.getText());

    String title = txtBookName.getText().trim();
    String author = txtAuthor.getText().trim();
    String publisher = txtPublisher.getText().trim();

    if (title.isEmpty() || author.isEmpty() || publisher.isEmpty()) {

        JOptionPane.showMessageDialog(this,
                "Please fill all fields!");

        return;
    }

    Book book = new Book(id, title, author, publisher, true);

    LibraryDB.addBook(book);

    JOptionPane.showMessageDialog(this,
            "Book Added Successfully!");

    // Clear the fields
    txtBookId.setText("");
    txtBookName.setText("");
    txtAuthor.setText("");
    txtPublisher.setText("");

} catch (NumberFormatException ex) {

    JOptionPane.showMessageDialog(this,
            "Book ID must be a number!");
}



        } else if (e.getSource() == btnIssue) {

            JOptionPane.showMessageDialog(this, "Issue Book Button Clicked!");

        } else if (e.getSource() == btnReturn) {

            JOptionPane.showMessageDialog(this, "Return Book Button Clicked!");

        } else if (e.getSource() == btnDisplay) {

    try {

        DefaultTableModel model = (DefaultTableModel) table.getModel();

        model.setRowCount(0);

        ResultSet rs = LibraryDB.getAllBooks();

        while (rs.next()) {

            model.addRow(new Object[]{
                rs.getInt("book_id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("publisher"),
                rs.getBoolean("available")
            });

        }

    } catch (Exception ex) {

        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading books!");

    }

}else if (e.getSource() == btnClear) {

            txtBookId.setText("");
            txtBookName.setText("");
            txtAuthor.setText("");
            txtPublisher.setText("");

        } else if (e.getSource() == btnExit) {

            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new LibraryManagement();
    }
}