package studentManagementSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class StudentGUI {
	public static void main(String[] args) {
		
		//adding ------------------------------------->
		JFrame frame = new JFrame();
		frame.setSize(450,390);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel title = new JLabel("Student Management System");
		title.setBounds(110,5,300,40);
		title.setFont(new Font("Console",Font.BOLD,15));
		title.setForeground(Color.black);
		frame.add(title);
		
		JLabel namelabel = new JLabel("Name");
		namelabel.setBounds(50, 50, 250, 30);
		frame.add(namelabel);
		
		JTextField name = new JTextField();
		name.setBounds(100, 50, 250, 30);
		frame.add(name);
		
		
		JLabel rolllabel = new JLabel("Roll No");
		rolllabel.setBounds(50, 100, 250, 30);
		frame.add(rolllabel);
		
		JTextField roll = new JTextField();
		roll.setBounds(100, 100, 250, 30);
		frame.add(roll);
		
		
		JLabel markslabel = new JLabel("Marks");
		markslabel.setBounds(50, 150, 250, 30);
		frame.add(markslabel);
		
		JTextField marks = new JTextField();
		marks.setBounds(100, 150, 250, 30);
		frame.add(marks);
		
		
		JButton button = new JButton("Add");
		button.setBounds(90, 200, 120, 40);
		frame.add(button);
		
		StudentDAO obj = new StudentDAO();
		
		button.addActionListener(e -> {
		    //System.out.println(name.getText() + " " + roll.getText() + " " + marks.getText());
			
			if(name.getText().isEmpty() || roll.getText().isEmpty() || marks.getText().isEmpty()) {
		        JOptionPane.showMessageDialog(frame, "Please fill all fields!");
		        return;
		    }
			
			try {
				String n = name.getText();
				int r = Integer.parseInt(roll.getText());
				int m = Integer.parseInt(marks.getText());
				
				Student s = new Student();
				s.name = n;
				s.roll_no = r;
				s.marks = m;
				
				obj.addStudent(s);
				
				JOptionPane.showMessageDialog(frame, "Student Added!");
	
			    name.setText("");
			    roll.setText("");
			    marks.setText("");
			}
			catch(Exception e1) {
				JOptionPane.showMessageDialog(frame, "Roll No. and Marks should be in Number format");
			}
			
		});
		
		
		
		// searching -------------------> 
		JButton search = new JButton("Search");
		search.setBounds(220, 200, 120, 40);
		frame.add(search);
		
		search.addActionListener(e -> {

		    if(roll.getText().isEmpty()) {
		        JOptionPane.showMessageDialog(frame, "Enter Roll Number!");
		        return;
		    }

		    try {
		        int r = Integer.parseInt(roll.getText());

		        Student s = obj.getStudent(r);

		        if(s != null) {
		            name.setText(s.name);
		            marks.setText(String.valueOf(s.marks));
		        } else {
		            JOptionPane.showMessageDialog(frame, "Student not found");
		        }

		    } catch (NumberFormatException ex) {
		        JOptionPane.showMessageDialog(frame, "Roll must be a number!");
		    }
		});
		
		
		// delete  ------------------------------->
		JButton delete = new JButton("Delete");
		delete.setBounds(90, 250, 120, 40);
		frame.add(delete);
		
		delete.addActionListener(e -> {
			
			if(roll.getText().isEmpty()) {
				JOptionPane.showMessageDialog(frame,"Enter Roll Number!");
				return;
			}
			else {
				try {
					int r = Integer.parseInt(roll.getText());
					StudentDAO dao = new StudentDAO();
					Student s = dao.getStudent(r);
					if(s != null) {
						dao.deleteStudent(r);
						JOptionPane.showMessageDialog(frame,"Student Infromation Deleted");
						
						name.setText("");
						marks.setText("");
						roll.setText("");
						
					}
					else {
						JOptionPane.showMessageDialog(frame, "Roll Number isn't in database");
					}
				}
				catch(Exception e2) {
					JOptionPane.showMessageDialog(frame,"Roll Number should be in number");
				}
			}
		});
		
		
		
		// display all  ------------------------------------------->
		JButton showBtn = new JButton("Show All");
        showBtn.setBounds(220, 250, 120, 40);
        frame.add(showBtn);

        showBtn.addActionListener(e -> {
        		StudentDAO dao = new StudentDAO();
            List<Student> list = dao.getStudents();

            String[] cols = {"Roll", "Name", "Marks"};
            DefaultTableModel model = new DefaultTableModel(cols, 0);

            for(Student s : list) {
                model.addRow(new Object[]{s.roll_no, s.name, s.marks});
            }

            JTable table = new JTable(model);
            JScrollPane sp = new JScrollPane(table);

            JFrame tableFrame = new JFrame("All Students");
            tableFrame.setSize(400, 400);
            tableFrame.add(sp);
            tableFrame.setVisible(true);
            
            name.setText("");
            roll.setText("");
            marks.setText("");
        });

		
		
		frame.setVisible(true);
	}
}
