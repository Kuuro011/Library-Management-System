import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField.AbstractFormatter;
import color.AlternateColorRender;

public class TransactionFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtBookNum;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTable tblTransac;
	private JTextField txtBorrID;
	private JComboBox cbAccession;
	private JComboBox cbStatus;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransactionFrame frame = new TransactionFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TransactionFrame() {
		setTitle("Transaction Module");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1130, 750);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(127, 255, 212));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBookNumber = new JLabel("Book Number");
		lblBookNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBookNumber.setBounds(47, 54, 113, 28);
		contentPane.add(lblBookNumber);
		
		txtBookNum = new JTextField();
		txtBookNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBookNum.setColumns(10);
		txtBookNum.setBounds(150, 55, 621, 27);
		contentPane.add(txtBookNum);
		
		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTitle.setColumns(10);
		txtTitle.setBounds(150, 94, 621, 27);
		contentPane.add(txtTitle);
		
		JLabel lblNewLabel = new JLabel("Book Title");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(47, 93, 90, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblAuthors = new JLabel("Author(s)");
		lblAuthors.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAuthors.setBounds(47, 141, 77, 27);
		contentPane.add(lblAuthors);
		
		txtAuthor = new JTextField();
		txtAuthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(150, 141, 621, 27);
		contentPane.add(txtAuthor);
		
		cbAccession = new JComboBox();
		cbAccession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setStatusBasedOnAccession();
			}
		});
		cbAccession.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbAccession.setBackground(Color.WHITE);
		cbAccession.setBounds(150, 183, 89, 28);
		contentPane.add(cbAccession);
		
		JLabel lblAccession = new JLabel("Accession");
		lblAccession.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAccession.setBounds(47, 187, 80, 24);
		contentPane.add(lblAccession);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStatus.setBounds(47, 230, 80, 20);
		contentPane.add(lblStatus);
		
		cbStatus = new JComboBox();
		cbStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbStatus.setBackground(Color.WHITE);
		cbStatus.setBounds(150, 228, 89, 22);
		cbStatus.addItem("Available");
		cbStatus.addItem("Not Available");
		cbStatus.addItem("Borrowed");
		contentPane.add(cbStatus);
		
		tblTransac = new JTable();
		tblTransac.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblTransac.setColumnSelectionAllowed(true);
		tblTransac.setCellSelectionEnabled(true);
		
		tblTransac.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Transaction ID", "Book Num", "Title", "Accession", "Status", "Transaction Date", "Return Date", "Borrower", "ID"
			}
		));
		tblTransac.setBounds(126, 598, 632, -215);
		
		AlternateColorRender alternate = new AlternateColorRender();
		tblTransac.setDefaultRenderer(Object.class, alternate);
		contentPane.add(tblTransac);
		
		JScrollPane js = new JScrollPane(tblTransac);
		js.setVisible(true);
		js.setBounds(24, 355, 1059, 276); // Adjust the bounds to match the table
		contentPane.add(js);
		
		JPanel panel = new JPanel();
		panel.setBounds(24, 279, 1059, 65);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtBorrID = new JTextField();
		txtBorrID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBorrID.setColumns(10);
		txtBorrID.setBounds(130, 23, 621, 30);
		panel.add(txtBorrID);
		
		JLabel lblBorrowersName = new JLabel("Borrower's ID");
		lblBorrowersName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBorrowersName.setBounds(10, 22, 113, 30);
		panel.add(lblBorrowersName);
		
		
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		btnUpdate.setBounds(779, 13, 128, 40);
		panel.add(btnUpdate);
		JButton btnSearchBook = new JButton("Search Book");
		btnSearchBook.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		btnSearchBook.setBounds(917, 11, 128, 40);
		panel.add(btnSearchBook);
		btnSearchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(65, 105, 225));
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBack.setBorderPainted(false);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuFrame menu = new MainMenuFrame();
				menu.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(34, 642, 128, 40);
		contentPane.add(btnBack);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(24, 33, 1059, 235);
		contentPane.add(panel_1);
		
		fetchAndDisplayData();
	
	}
	
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	
	// This method is to set the status based on the selected accession number
	private void setStatusBasedOnAccession() {
		try {
			String bookNumber = txtBookNum.getText();
			int accessionNumber = Integer.parseInt(cbAccession.getSelectedItem().toString());
			
			String selectStatusSql = "SELECT book_status FROM Books WHERE Book_Num = ? AND Accession_Num = ?";
			try(PreparedStatement selectStatusStmt = conn.prepareStatement(selectStatusSql)){
				selectStatusStmt.setString(1, bookNumber);
				selectStatusStmt.setInt(2, accessionNumber);
				ResultSet statusResultSet = selectStatusStmt.executeQuery();
				
				if(statusResultSet.next()) {
					String status = statusResultSet.getString("book_status");
					cbStatus.setSelectedItem(status);
				}
				
				else {
					cbStatus.setSelectedIndex(-1);
				}
			}
			
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	// New method to fetch and display data in the JTable
    private void fetchAndDisplayData() {
        try {
            // Load the JDBC driver (version 4.0 or later)
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");
            System.out.println("Connected");

            // Fetch data from Transactions table
            String selectTransactionsSql = "SELECT * FROM Transactions";
            try (PreparedStatement selectTransactionsStmt = conn.prepareStatement(selectTransactionsSql)) {
                ResultSet rs = selectTransactionsStmt.executeQuery();

                DefaultTableModel tblModel = (DefaultTableModel) tblTransac.getModel();
                tblModel.setRowCount(0); // Clear existing rows

                while (rs.next()) {
                    String transacId = rs.getString("transaction_id");
                    String bookNum = rs.getString("BooNum");
                    String title = rs.getString("Title");
                    String accNum = rs.getString("AccessionNum");
                    String bookStatus = rs.getString("BookStatus");
                    String transactionDate = rs.getString("transaction_date");
                    String returnDate = rs.getString("return_date");
                    String userName = rs.getString("Borrower");
                    String userId = rs.getString("user_id");
                    // array to store data into jtable
                    String tbData[] = {transacId, bookNum, title, accNum, bookStatus,
                            transactionDate, returnDate, userName, userId};

                    // add string array data to jtable
                    tblModel.addRow(tbData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String userId;
    public void update() {
        try {
            // Load the JDBC driver (version 4.0 or later)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB?useSSL=false", "root", "ranielle25");
            System.out.println("Connected");

            // Get values from UI components
            String bn = txtBookNum.getText();
            String tl = txtTitle.getText();
            int acc = Integer.parseInt(cbAccession.getSelectedItem().toString());
            String status = (String) cbStatus.getSelectedItem();
            userId = txtBorrID.getText();
            String borrId = txtBorrID.getText();
            
            String userType = getUserType(borrId);
            
        	// Check if the user is a student or faculty/school staff based on ID number
            if ("Student".equals(userType)) {
            	String borrowerNameSql = "SELECT LastName, FirstName, MiddleName FROM Students WHERE StudentNo = ?";
            	try(PreparedStatement borrowerNameStmt = conn.prepareStatement(borrowerNameSql)){
            		borrowerNameStmt.setString(1, borrId);
            		ResultSet borrowerNameResult = borrowerNameStmt.executeQuery();
            		
            		if(borrowerNameResult.next()) {
            			String borrowerName = borrowerNameResult.getString("LastName") + 
            					", " + borrowerNameResult.getString("FirstName") +
            					" " + borrowerNameResult.getString("MiddleName");
            			
            			insertTransaction(bn, tl, acc, status, borrowerName);          			
            		}
            		else {
            			JOptionPane.showMessageDialog(rootPane, "Name not found!");
            		}
            	}
                
                
            } else if ("Faculty".equals(userType) ||  "Staff".equals(userType)) {
            	String borrowerNameSql = "SELECT LastName, FirstName, MiddleName FROM Employees WHERE employeeID = ?";
            	try(PreparedStatement borrowerNameStmt = conn.prepareStatement(borrowerNameSql)){
            		borrowerNameStmt.setString(1, borrId);
            		ResultSet borrowerNameResult = borrowerNameStmt.executeQuery();
            		
            		if(borrowerNameResult.next()) {
            			String borrowerName = borrowerNameResult.getString("LastName") + 
            					", " + borrowerNameResult.getString("FirstName") +
            					" " + borrowerNameResult.getString("MiddleName");
            			
            			insertTransaction(bn, tl, acc, status, borrowerName);
            			
            		}
            		else {
            			JOptionPane.showMessageDialog(rootPane, "Name not found!");
            		}
            	}
            	
            	
            } else {
                JOptionPane.showMessageDialog(rootPane, "Invalid user ID format!");
            }
            
            
            
            fetchAndDisplayData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void insertTransaction(String bn, String tl, int acc, String status, String borrowerName) {
        String checkId = userId;

        try {
            // Load the JDBC driver (version 4.0 or later)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB?useSSL=false", "root", "ranielle25");
            System.out.println("Connected");

            // Update book status in Books table
            String updateBookStatusSql = "UPDATE Books SET book_status = ? WHERE Book_Num = ? AND Accession_Num = ?";
            
            try(PreparedStatement updateBookStatusStmt = conn.prepareStatement(updateBookStatusSql)){
            	updateBookStatusStmt.setString(1, status);
            	updateBookStatusStmt.setString(2, bn);
            	updateBookStatusStmt.setInt(3, acc);
            	
            	//Execute query
            	int rowAffected = updateBookStatusStmt.executeUpdate();
            	String borrId = txtBorrID.getText();
            	
            	String userType = getUserType(borrId);
            	
            	if ("Faculty".equals(userType) || "Staff".equals(userType)) {                  
                    String insertSql = "INSERT INTO Transactions (BooNum, Title, AccessionNum, Borrower, BookStatus, transaction_date, return_date, user_id) " +
                            "VALUES (?, ?, ?, ?, ?, CURRENT_DATE(), 'IND', ?)";

                    try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                        pstmt.setString(1, bn);
                        pstmt.setString(2, tl);
                        pstmt.setInt(3, acc);
                        pstmt.setString(4, borrowerName);
                        pstmt.setString(5, status);
                        pstmt.setString(6, userId);

                        // Execute the update
                        int rowsAffected = pstmt.executeUpdate();
                        
                        JOptionPane.showMessageDialog(rootPane, "Transaction Recorded!");
                        
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    // If user ID does not start with "1", set return date to three days from the transaction date
                    String insertSql = "INSERT INTO Transactions (BooNum, Title, AccessionNum, Borrower, BookStatus, transaction_date, return_date, user_id) " +
                            "VALUES (?, ?, ?, ?, ?, CURRENT_DATE(), DATE_ADD(CURDATE(), INTERVAL 3 DAY), ?)";

                    try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                        pstmt.setString(1, bn);
                        pstmt.setString(2, tl);
                        pstmt.setInt(3, acc);
                        pstmt.setString(4, borrowerName);
                        pstmt.setString(5, status);
                        pstmt.setString(6, userId);

                        // Execute the update
                        int rowsAffected = pstmt.executeUpdate();
                        
                        JOptionPane.showMessageDialog(rootPane, "Transaction Recorded!");
                        
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
         
                }
            	
            }
            catch(SQLException e) {
            	e.printStackTrace();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Get user type from both Employees and Students table
    private String getUserType(String userId) {
    	String userType = "";
    	
		String userTypeSql = "SELECT UserType FROM Students WHERE StudentNo = ? UNION SELECT UserType FROM Employees WHERE employeeID = ?";
		try(PreparedStatement userTypeStmt = conn.prepareStatement(userTypeSql)) {
			userTypeStmt.setString(1, userId);
			userTypeStmt.setString(2, userId);
			
			ResultSet userTypeResult = userTypeStmt.executeQuery();
			
			if(userTypeResult.next()) {
				userType = userTypeResult.getString("UserType");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return userType;
    	
    }
    
	
	public void search() {
		try {		
			 // Load the JDBC driver (version 4.0 or later)
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}   		
			
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");
				Statement stmt = conn.createStatement();
				System.out.println("Connected");
								
				String sql = "SELECT * FROM Books WHERE Book_Num = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				String bookNumber = txtBookNum.getText();
				//int bookNumber = txtBookNumber
				pstmt.setString(1, bookNumber);
				
				ResultSet rs = pstmt.executeQuery();

		        DefaultTableModel tblModel = (DefaultTableModel) tblTransac.getModel();
		        
		        if(!rs.isBeforeFirst()) {
					JOptionPane.showMessageDialog(this, "Sorry, book not found!");				
					
					txtTitle.setText("");
					txtAuthor.setText("");
					cbAccession.removeAllItems();
				}
		        
		        else {
		        	DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		        	while(rs.next()) {
		        				   		        		
						//add data until there is none
						String bookNum = rs.getString("Book_Num");
						String title = rs.getString("Title");
						String author = rs.getString("Author");
						String accession = String.valueOf(rs.getInt("Accession_Num"));
						
						comboBoxModel.addElement(accession);
						
						txtTitle.setText(title);
						txtAuthor.setText(author);				
						cbAccession.setModel(comboBoxModel);		
												
					}
		        }			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}          				
								
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
