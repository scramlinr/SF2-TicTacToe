import java.awt.EventQueue;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class project4TicTacGui implements ActionListener {

	private JFrame frame;

	/**
	 * Ryan Scramlin
	 * Project 4--Tic-Tac-Toe-W/GUI
	 * @throws UnsupportedAudioFileException 
	 */
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException {
		music();					//Method plays background music
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					project4TicTacGui window = new project4TicTacGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws MalformedURLException 
	 */
	public project4TicTacGui() throws MalformedURLException {
		initialize();
	}
	static JButton btnOne;			//button 1, 2, etc...
	static JButton btnTwo;
	static JButton btnThree;
	static JButton btnFour;
	static JButton btnFive;
	static JButton btnSix;
	static JButton btnSeven;
	static JButton btnEight;
	static JButton btnNine;
	char turn;			//Tells whether it is 'X','O'
	static project4TicTac gameBoard = new project4TicTac();
	private JLabel lblNewLabel;			//background image
	
public void oppTurn(){
	//Checks made for computers turn
	
	int winButton;		//returned row col value to corresponding button to perform action
	turn = 'O';
	if (oppTurnWin(gameBoard) == true){		//checks for winning move
		winButton = gameBoard.getLastMove();	//get move that resulted in win for button
		switchMove(winButton);					//pass to switch statement
		if (gameBoard.checkWin(turn)==true){		//check for win
			JOptionPane.showMessageDialog(null, turn+" is the winner!!");	//winner dialog
			anotherMatch();				//check for another match
		}
	}else if(blockCheck(gameBoard)==true){		//check for a block move
		winButton= gameBoard.getLastMove();
		switchMove(winButton);
		if (gameBoard.checkWin(turn)==true){
			JOptionPane.showMessageDialog(null, turn+" is the winner!!");
			anotherMatch();
		}
	}else if(gameBoard.checkTie(gameBoard)==true){				//check for a tie
		JOptionPane.showMessageDialog(null, "Game is a Tie!");
		anotherMatch();
	}else{
		gameBoard.makeEasyMove(gameBoard,turn);		//make a move
		winButton = gameBoard.getLastMove();
		switchMove(winButton);
		if (gameBoard.checkWin(turn)==true){
			JOptionPane.showMessageDialog(null, turn+" is the winner!!");
			anotherMatch();
		}
	}	
}
public void switchMove(int lastMove){
	//switch statement receives a number corresponding to a button to perform an action on
	//a specific location of GUI board
	
	switch (lastMove) {
	case 1:
		btnOne.setText("O");		//cheange text to 'O'
		btnOne.setEnabled(false);	//so button cannot be pushed again
		gameBoard.checkWin(turn);	//check for a win
		break;
	case 2:
		btnTwo.setText("O");
		btnTwo.setEnabled(false);
		gameBoard.checkWin(turn);
		break;
	case 3:
		btnThree.setText("O");
		btnThree.setEnabled(false);
		gameBoard.checkWin(turn);
		break;
	case 4:
		btnFour.setText("O");
		btnFour.setEnabled(false);
		gameBoard.checkWin(turn);
		break;
	case 5:
		btnFive.setText("O");
		btnFive.setEnabled(false);
		gameBoard.checkWin(turn);
		break;
	case 6:
		btnSix.setText("O");
		btnSix.setEnabled(false);
		gameBoard.checkWin(turn);
		break;
	case 7:
		btnSeven.setText("O");
		btnSeven.setEnabled(false);
		gameBoard.checkWin(turn);
		break;
	case 8:
		btnEight.setText("O");
		btnEight.setEnabled(false);
		gameBoard.checkWin(turn);
		break;
	case 9:
		btnNine.setText("O");
		btnNine.setEnabled(false);
		gameBoard.checkWin(turn);
		break;
	}
}
public void checkWin(String turn){
	boolean win = false;
	if(turn=="-"){
		win=false;
	}else if(btnOne.getText()==turn && btnTwo.getText()==turn && btnThree.getText()==turn){	//top across
		win=true;
	}else if(btnFour.getText()==turn && btnFive.getText()==turn  && btnSix.getText()==turn ){	//middle across
		win=true;
	}else if (btnSeven.getText()==turn  && btnEight.getText()==turn  && btnNine.getText()==turn ){	//bottom across
		win = true;
	}else if (btnOne.getText()==turn  && btnFour.getText()==turn  &&btnSeven.getText()==turn ){
		win = true;
	}else if (btnTwo.getText()==turn  && btnFive.getText()==turn  && btnEight.getText()==turn ){
		win=true;
	}else if(btnThree.getText()==turn  && btnSix.getText()==turn  && btnNine.getText()==turn ){
		win=true;
	}else if(btnOne.getText()==turn  && btnFive.getText()==turn  && btnNine.getText()==turn ){
		win=true;
	}else if(btnThree.getText()==turn  && btnFive.getText()==turn && btnSeven.getText()==turn ){
		win=true;
	}
if (win == true){
	JOptionPane.showMessageDialog(null, turn+" is the winner!!");
}

}
public static void resetBoard(){
	//resets board to start a new game
	
	btnOne.setText("");		//set text to blank
	btnOne.setEnabled(true);	//re enable button
	btnTwo.setText("");
	btnTwo.setEnabled(true);
	btnThree.setText("");
	btnThree.setEnabled(true);
	btnFour.setText("");
	btnFour.setEnabled(true);
	btnFive.setText("");
	btnFive.setEnabled(true);
	btnSix.setText("");
	btnSix.setEnabled(true);
	btnSeven.setText("");
	btnSeven.setEnabled(true);
	btnEight.setText("");
	btnEight.setEnabled(true);
	btnNine.setText("");
	btnNine.setEnabled(true);
	
	gameBoard.setBoard();	//reset the 2d array for checks
}
	
	
	
	public static boolean anotherMatch(){
	//JOption dialog for a new game
		
		int contOption = JOptionPane.showConfirmDialog(null, 
                "Do you want to play again? ", 
                "", 
                JOptionPane.YES_NO_OPTION);
		
		boolean cont = false;
		
		if(contOption == JOptionPane.YES_OPTION){
			cont = true;
			resetBoard();
		}else if (contOption == JOptionPane.NO_OPTION){
			cont = false;
			System.exit(1);
		}else{
			System.out.println("i am error");
		}
	return cont;
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 * @throws MalformedURLException 
	 */
	private void initialize() throws MalformedURLException {
	//Initializes the 2d gameboard and gui
	//Also, contains all human player made actions
		
		gameBoard.setBoard();		//set 2d array board
		
		frame = new JFrame();
		frame.setBounds(100, 100, 622, 422);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnOne = new JButton("");			//JButton 1
		btnOne.addActionListener(new ActionListener () {

			public void actionPerformed(ActionEvent e) {	
				// If btnOne pushed performs some actions
				btnOne.setText(""+'X');		//change text to 'X'
				btnOne.setEnabled(false);	//disable button so it cannot be pushed again
				turn = 'X';
				gameBoard.playerMove(0, 0, turn);	//checks location 0,0
				gameBoard.printBoard();				//prints board for following 2d array..mainly for testing
				if (gameBoard.checkWin(turn)==true){
					JOptionPane.showMessageDialog(null, turn+" is the winner!!");
					anotherMatch();
				}
				//opponent takes their turn
				oppTurn();
				}
		});
		btnOne.setFont(new Font("Times New Roman", Font.BOLD, 38));
		btnOne.setBounds(170, 74, 69, 63);
		frame.getContentPane().add(btnOne);
		
		btnTwo = new JButton("");
		btnTwo.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				// btnOne action Event
				btnTwo.setText("X");
				btnTwo.setEnabled(false);
				turn = 'X';
				gameBoard.playerMove(0, 1, turn);
				gameBoard.printBoard();
				if (gameBoard.checkWin(turn)==true){
					JOptionPane.showMessageDialog(null, turn+" is the winner!!");
					anotherMatch();
				}else if(gameBoard.checkTie(gameBoard)==true){
					JOptionPane.showMessageDialog(null, "Game is a Tie!");
				anotherMatch();
				}
					oppTurn();
			}
			
		});
		
		btnTwo.setFont(new Font("Times New Roman", Font.BOLD, 38));
		btnTwo.setBounds(245, 74, 69, 63);
		frame.getContentPane().add(btnTwo);
		
		btnThree = new JButton("");
		btnThree.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// button 2
				btnThree.setText("X");
				btnThree.setEnabled(false);
				turn = 'X';
				gameBoard.playerMove(0, 2, turn);
				gameBoard.printBoard();
				if (gameBoard.checkWin(turn)==true){
					JOptionPane.showMessageDialog(null, turn+" is the winner!!");
					anotherMatch();
				}else if(gameBoard.checkTie(gameBoard)==true){
					JOptionPane.showMessageDialog(null, "Game is a Tie!");
				anotherMatch();
				}
				oppTurn();
			}
			
		});
		btnThree.setFont(new Font("Times New Roman", Font.BOLD, 38));
		btnThree.setBounds(324, 74, 69, 63);
		frame.getContentPane().add(btnThree);
		
		btnFour = new JButton("");
		btnFour.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				// button 3
				btnFour.setText(""+'X');
				btnFour.setEnabled(false);
				turn = 'X';
				gameBoard.playerMove(1, 0, turn);
				gameBoard.printBoard();
				if (gameBoard.checkWin(turn)==true){
					JOptionPane.showMessageDialog(null, turn+" is the winner!!");
					anotherMatch();
				}else if(gameBoard.checkTie(gameBoard)==true){
					JOptionPane.showMessageDialog(null, "Game is a Tie!");
				anotherMatch();
				}
				//begin oppTurn
				oppTurn();
				}
		});;
		btnFour.setFont(new Font("Times New Roman", Font.BOLD, 38));
		btnFour.setBounds(170, 148, 69, 63);
		frame.getContentPane().add(btnFour);
		
		btnFive = new JButton("");
		btnFive.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				// button 4
				btnFive.setText(""+'X');
				btnFive.setEnabled(false);
				turn = 'X';
				gameBoard.playerMove(1, 1, turn);
				gameBoard.printBoard();
				if (gameBoard.checkWin(turn)==true){
					JOptionPane.showMessageDialog(null, turn+" is the winner!!");
					anotherMatch();
				}else if(gameBoard.checkTie(gameBoard)==true){
					JOptionPane.showMessageDialog(null, "Game is a Tie!");
				anotherMatch();
				}
				//begin oppTurn
				oppTurn();
				}
		});
		btnFive.setFont(new Font("Times New Roman", Font.BOLD, 38));
		btnFive.setBounds(245, 148, 69, 63);
		frame.getContentPane().add(btnFive);
		
		btnSix = new JButton("");
		btnSix.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				// button 6
				btnSix.setText(""+'X');
				btnSix.setEnabled(false);
				turn = 'X';
				gameBoard.playerMove(1, 2, turn);
				gameBoard.printBoard();
				if (gameBoard.checkWin(turn)==true){
					JOptionPane.showMessageDialog(null, turn+" is the winner!!");
					anotherMatch();
				}else if(gameBoard.checkTie(gameBoard)==true){
					JOptionPane.showMessageDialog(null, "Game is a Tie!");
				anotherMatch();
				}
				//begin oppTurn
				oppTurn();
				}
		});
		btnSix.setFont(new Font("Times New Roman", Font.BOLD, 38));
		btnSix.setBounds(324, 148, 69, 63);
		frame.getContentPane().add(btnSix);
		
		btnSeven = new JButton("");
		btnSeven.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				// button 7
				btnSeven.setText(""+'X');
				btnSeven.setEnabled(false);
				turn = 'X';
				gameBoard.playerMove(2, 0, turn);
				gameBoard.printBoard();
				if (gameBoard.checkWin(turn)==true){
					JOptionPane.showMessageDialog(null, turn+" is the winner!!");
					anotherMatch();
				}else if(gameBoard.checkTie(gameBoard)==true){
					JOptionPane.showMessageDialog(null, "Game is a Tie!");
				anotherMatch();
				}
				//begin oppTurn
				oppTurn();
				}
		});;
		btnSeven.setFont(new Font("Times New Roman", Font.BOLD, 38));
		btnSeven.setBounds(170, 222, 69, 63);
		frame.getContentPane().add(btnSeven);
		
		btnEight = new JButton("");
		btnEight.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				// button 8
				btnEight.setText(""+'X');
				btnEight.setEnabled(false);
				turn = 'X';
				gameBoard.playerMove(2, 1, turn);
				gameBoard.printBoard();
				if (gameBoard.checkWin(turn)==true){
					JOptionPane.showMessageDialog(null, turn+" is the winner!!");
					anotherMatch();
				}else if(gameBoard.checkTie(gameBoard)==true){
					JOptionPane.showMessageDialog(null, "Game is a Tie!");
				anotherMatch();
				}
				//begin oppTurn
				oppTurn();
				}
		});;
		btnEight.setFont(new Font("Times New Roman", Font.BOLD, 38));
		btnEight.setBounds(245, 222, 69, 63);
		frame.getContentPane().add(btnEight);
		
		btnNine = new JButton("");
		btnNine.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				// button 9
				btnNine.setText(""+'X');
				btnNine.setEnabled(false);
				turn = 'X';
				gameBoard.playerMove(2, 2, turn);
				gameBoard.printBoard();
				if (gameBoard.checkWin(turn)==true){
					JOptionPane.showMessageDialog(null, turn+" is the winner!!");
					anotherMatch();
				}else if(gameBoard.checkTie(gameBoard)==true){
					JOptionPane.showMessageDialog(null, "Game is a Tie!");
				anotherMatch();
				}
				//begin oppTurn
				oppTurn();
				}
		});
		btnNine.setFont(new Font("Times New Roman", Font.BOLD, 38));
		btnNine.setBounds(324, 222, 69, 63);
		frame.getContentPane().add(btnNine);
		
		JLabel bG = new JLabel("");
		bG.setIcon(new ImageIcon(project4TicTacGui.class.getResource("guileBG.png")));
		bG.setBounds(0, 0, 606, 383);
		frame.getContentPane().add(bG);
		
	
	}
	
	public static boolean oppTurnWin(project4TicTac gameBoard) {
	//check for win for 
		char turn = 'O';
		boolean win = false;
		win = gameBoard.getWinMove(gameBoard, turn);
		return win;
	}

	public static boolean blockCheck(project4TicTac gameBoard) {
		//check for block
		boolean block = false;
		char turn = 'O';
		block = gameBoard.moveBlock(gameBoard, turn);
		return block;
	}	
	
	
	public static  void music() throws UnsupportedAudioFileException, IOException{
	//background music method
		{
        new Thread(new Runnable() 
        {
            public void run() 
            {
                try
                {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResource("guile.wav"));	
                    clip = AudioSystem.getClip();
                    clip.open(inputStream);
                    clip.start(); 
                } 
                catch (Exception e) 
                {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	}


