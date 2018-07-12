package gui.apps.stack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;

class Stack {
	private int[] stack;
	private int top;
	private int stackSize;
	public Stack() {
		stack = new int[10];
		top = -1;
		stackSize = 0;
	}
	
	public boolean isEmpty() {
		return (top == -1);
	}
	
	public boolean isFull() {
		return (top == 9);
	}
	
	public void push(int value) {
		if (!isFull()) {
			stack[++top] = value;
			stackSize++;
		}
	}
	
	public int pop() {
		if (!isEmpty()) {
			stackSize--;
			return stack[top--];
		}
		return -1;
	}
	
	public int peek() {
		if (!isEmpty()) {
			return stack[top];
		}
		return -1;
	}
	public int size() {
		return stackSize;
	}
	
	public String toString() {
		String str = "";
		if (!isEmpty()) {
			for (int i = size() - 1 ; i >= 0; i--) {
				str += stack[i];
				if (i != 0)
					str += ", ";
			}
		}
		return str;
	}
}


public class StackMainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel stackSize;
	private JLabel stackLbl;
	private JLabel status;
	private JLabel peekLbl;
	private JButton pushButton,popButton;
	private Stack stack;
	
	
	private void refreshInfo() {
		stackLbl.setText(stack.toString());
		if (!stack.isEmpty()) {
			peekLbl.setText(stack.peek() + "");
			stackSize.setText(stack.size() + "");
		}
		else {
			peekLbl.setText("");
			stackSize.setText("0");
		}
	}
	
	private void handlePushEvent() {
		String str = textField.getText();
		if (str.length() == 0)
			status.setText("Please enter Some value before push.");
		else {
			int value = 0;
			try {
				value = Integer.parseInt(str);
					if (!stack.isFull()) {
						stack.push(value);
						refreshInfo();
						status.setText("Value " + value + " is pushed to stack.");
						textField.setText("");
						popButton.setEnabled(true);
					}
					else {
						status.setText("Stack is Full. Can't able to push " + value);
						textField.setText("");
						pushButton.setEnabled(false);
					}
			}
			catch(NumberFormatException exception) {
				textField.setText("");
				status.setText("Only numrical values are allowed. You Enterd " + str + ".");
			}
		}
		
	}	
	
	private void handlePopEvent() {
		int item = stack.pop();
		status.setText("Poped value is " + item);
		if (stack.isEmpty())
			popButton.setEnabled(false);
		if (!stack.isFull())
			pushButton.setEnabled(true);
		refreshInfo();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StackMainWindow frame = new StackMainWindow();
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
	public StackMainWindow() {
		setName("MainStackWindow");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\admin\\Windows Icons\\pink.ico"));
		setTitle("Stack");
		setBackground(Color.WHITE);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(new Dimension(600, 400));
		getContentPane().setLayout(null);
		
		JLabel lblStackGuiApplication = new JLabel("Stack GUI Application");
		lblStackGuiApplication.setForeground(new Color(0, 128, 128));
		lblStackGuiApplication.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		lblStackGuiApplication.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblStackGuiApplication.setBounds(188, 0, 210, 38);
		getContentPane().add(lblStackGuiApplication);
		
		textField = new JTextField();
		textField.setBackground(new Color(255, 222, 173));
		textField.setFont(new Font("Consolas", Font.PLAIN, 16));
		textField.setBounds(10, 117, 111, 32);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblInputValue = new JLabel("Input Value");
		lblInputValue.setBounds(10, 92, 111, 24);
		getContentPane().add(lblInputValue);
		
		pushButton = new JButton("Push");
		pushButton.setFont(new Font("Consolas", Font.PLAIN, 16));
		pushButton.setToolTipText("Push Value Into The Stack");
		pushButton.setBounds(149, 117, 111, 32);
		getContentPane().add(pushButton);
		
		popButton = new JButton("Pop");
		popButton.setFont(new Font("Consolas", Font.PLAIN, 16));
		popButton.setToolTipText("Pop Value from Stack\r\n");
		popButton.setBounds(453, 117, 111, 32);
		popButton.setEnabled(false);
		getContentPane().add(popButton);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 178, 574, 2);
		getContentPane().add(separator_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 49, 574, 2);
		getContentPane().add(separator);
		
		JLabel lblStatus = new JLabel("Status : ");
		lblStatus.setFont(new Font("Consolas", Font.BOLD, 14));
		lblStatus.setBounds(10, 335, 82, 24);
		getContentPane().add(lblStatus);
		
		status = new JLabel("Stack is Empty.");
		status.setFont(new Font("Fira Code", Font.PLAIN, 12));
		status.setBounds(84, 337, 500, 24);
		status.setForeground(Color.RED);
		getContentPane().add(status);
		
		JLabel lblStack = new JLabel("Stack : ");
		lblStack.setFont(new Font("Consolas", Font.BOLD, 14));
		lblStack.setBounds(10, 191, 82, 24);
		getContentPane().add(lblStack);
		
		stackLbl = new JLabel("");
		stackLbl.setFont(new Font("Fira Code", Font.PLAIN, 12));
		stackLbl.setBounds(102, 191, 482, 24);
		getContentPane().add(stackLbl);
		
		JLabel lblSize = new JLabel("Size : ");
		lblSize.setFont(new Font("Consolas", Font.BOLD, 14));
		lblSize.setBounds(10, 226, 56, 24);
		getContentPane().add(lblSize);
		
		stackSize = new JLabel("0");
		stackSize.setFont(new Font("Consolas", Font.BOLD, 14));
		stackSize.setBounds(64, 226, 82, 24);
		getContentPane().add(stackSize);
		
		JLabel lblPeek = new JLabel("Peek/Top : ");
		lblPeek.setFont(new Font("Consolas", Font.BOLD, 14));
		lblPeek.setBounds(10, 269, 111, 24);
		getContentPane().add(lblPeek);
		
		peekLbl = new JLabel("");
		peekLbl.setFont(new Font("Consolas",Font.PLAIN,14));
		peekLbl.setBounds(102, 269, 111, 24);
		getContentPane().add(peekLbl);
		
		stack = new Stack();
		refreshInfo();
		
		pushButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handlePushEvent();
			}

			
		});
		
		popButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handlePopEvent();
				
			}
			
		});
	}
}
