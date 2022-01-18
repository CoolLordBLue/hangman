package game;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangPanel extends JPanel implements ActionListener
{

	private int WIDTH = 700;
	private int HEIGHT = 540;
	private Dimension size = new Dimension(WIDTH,HEIGHT); 
	private JTextField textField;
	private JLabel titleLBL, hangLBL, wordLBL, asterisksLBL, pickLBL, lgLBL, guessedLBL, updatesLBL;
	private String guessedLetters = "";
	private String solved;
	private String asterisks = "";
	private String alphabet = "abcdefghijklmnopqrstuvwxyz";
	private int numWrongGuesses = 0;
	
	private ImageIcon win = new ImageIcon("src/files/trophy.png");
	private ImageIcon rip = new ImageIcon("src/files/RIP.jpg");
	
	public HangPanel() 
	 {
		 setPreferredSize(size);
		 this.setBackground(Color.CYAN);
		 setLayout(null);
		 
		 solved = GetWord.guessWord();
		 System.out.println(solved);
		 for (int x = 0; x < solved.length(); x++)
		 {
			 asterisks += "*";
		 }
		 
		 titleLBL = new JLabel("HangMan");
		 titleLBL.setFont(new Font("Script MT Bold", Font.BOLD, 24));
		 titleLBL.setHorizontalAlignment(SwingConstants.CENTER);
		 titleLBL.setBounds(0, 20, 350, 54);
		 add(titleLBL);
		 
		 hangLBL = new JLabel("");
		 hangLBL.setIcon(new ImageIcon("src/files/0.jpg")); 
		 hangLBL.setBounds(350, 0, 350, 541);
		 add(hangLBL);
		 
		 wordLBL = new JLabel("Word To Guess");
		 wordLBL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		 wordLBL.setHorizontalAlignment(SwingConstants.CENTER);
		 wordLBL.setBounds(0, 85, 350, 31);
		 add(wordLBL);
		 
		 asterisksLBL = new JLabel();
		 asterisksLBL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		 asterisksLBL.setHorizontalAlignment(SwingConstants.CENTER);
		 asterisksLBL.setBounds(0, 118, 350, 31);
		 asterisksLBL.setText(asterisks);
		 add(asterisksLBL);
		 
		 pickLBL = new JLabel("Please Pick a Letter");
		 pickLBL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		 pickLBL.setHorizontalAlignment(SwingConstants.CENTER);
		 pickLBL.setBounds(0, 179, 350, 31);
		 add(pickLBL);
		 
		 textField = new JTextField();
		 textField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		 textField.setHorizontalAlignment(SwingConstants.CENTER);
		 textField.setBounds(148, 221, 15, 20);
		 add(textField);
		 textField.setColumns(10);
		 textField.addActionListener(this);
		 
		 lgLBL = new JLabel("Letters Guessed");
		 lgLBL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		 lgLBL.setHorizontalAlignment(SwingConstants.CENTER);
		 lgLBL.setBounds(0, 267, 350, 31);
		 add(lgLBL);
		 
		 guessedLBL = new JLabel();
		 guessedLBL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		 guessedLBL.setHorizontalAlignment(SwingConstants.CENTER);
		 guessedLBL.setBounds(0, 297, 350, 31);
		 add(guessedLBL);
		 
		 updatesLBL = new JLabel();
		 updatesLBL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		 updatesLBL.setHorizontalAlignment(SwingConstants.CENTER);
		 updatesLBL.setBounds(0, 351, 350, 190);
		 add(updatesLBL);
	 }
	
	private void checkGuess(String temp)
	{
		if (alphabet.indexOf(temp) >= 0)
		{
			if (guessedLetters.indexOf(temp) == -1)
			{
				guessedLetters += temp;
			}
			
			if (solved.indexOf(temp) >= 0)
			{
				for (int x = 0; x < solved.length(); x++)
				{
					if (solved.charAt(x) == temp.charAt(0))
					{
						asterisks = asterisks.substring(0,x) + temp + asterisks.substring(x+1);
					}
				}
			}
			else 
			{
				numWrongGuesses++;
			}
		}
	}
	private boolean checkGameOver()
	{
		if (solved.equals(asterisks))
		{
			updatesLBL.setIcon(win);
			return true;
		}
		else if (numWrongGuesses == 7)
		{
			updatesLBL.setIcon(rip);
			return true;
		}
		
		return false;
		
	}
	private void gameRestart()
	{
		solved = GetWord.guessWord();
		numWrongGuesses = 0;
		asterisks = "";
		for (int x = 0; x < solved.length(); x++)
		{
			asterisks += "*";
		}
		System.out.println(solved);
		hangLBL.setIcon(new ImageIcon("src/files/" + numWrongGuesses + ".jpg"));
		asterisksLBL.setText(asterisks);
		guessedLetters = "";
		guessedLBL.setText(guessedLetters);
		updatesLBL.setIcon(null);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String guess = textField.getText().toLowerCase();
		
		if (guess.length() > 0)
		{
			checkGuess(guess.substring(0,1)); 
		}
		
		
		hangLBL.setIcon(new ImageIcon("src/files/" + numWrongGuesses + ".jpg"));
		asterisksLBL.setText(asterisks);
		guessedLBL.setText(guessedLetters);
		textField.setText("");
		
		if (checkGameOver())
		{
			int restart = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
			if (restart == 0)
			{
				gameRestart();
			}
			else
			{
				System.exit(0);
			}
		}
	}
}
