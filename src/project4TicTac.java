import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;


/**
 * Ryan Scramlin
 * Project 4--Tic-Tac-Toe-board class
 * The board class is where a majority of board checks are done
 * it creates a board object that is manipulated to check for
 * almost all non GUI methods
 */
public class project4TicTac {
	//board variables
	
	char [][] board = new char [3][3];	//dimensions of 2d array board
	int r= 0;		//row
	int c= 0;		//column
	int lastRow;
	int lastCol;
	
	public char [][] setBoard(){
		// creates board
		for(int i =0; i<board.length;i++){
			for(int j =0; j<board.length; j++){
				board[i][j]='-';
			}
		}
	return this.board;
		}
	
	public void printBoard(){
		//A Simple function to print the state of the board in console
		
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board.length;j++){
				System.out.print("  "+board[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	
	public boolean playerMove(int r, int c,char turn){
		//a method to check if the player is making a legal move.  In gui this method 
		//is more of a safety net that should never return false since the buttons are
		//un pushable once an action has been performed on them
		boolean move=false;
		if(turn=='-'){
			board[r][c]=turn;
			move = true;
		}else if(board[r][c]=='-'){
			board[r][c]=turn;
			move = true;
		}else{
			System.err.println("Illegal move. Try again");
			move=false;
		}
		
	return move;
	}

	public boolean checkWin(char turn){
		//checks for win
		
		boolean win = false;
		boolean tie =false;
		
		if(turn=='-'){
			win=false;
		}else if(board[r][c]==turn && board [r][c+1]==turn && board[r][c+2]==turn){	//top across
			win=true;
		}else if(board[r+1][c]==turn && board [r+1][c+1]==turn && board[r+1][c+2]==turn){	//middle across
			win=true;
		}else if (board[r+2][c]==turn && board [r+2][c+1]==turn && board[r+2][c+2]==turn){	//bottom across
			win = true;
		}else if (board[r][c]==turn && board [r+1][c]==turn && board[r+2][c]==turn){
			win = true;
		}else if (board[r][c+1]==turn && board [r+1][c+1]==turn && board[r+2][c+1]==turn){
			win=true;
		}else if(board[r][c+2]==turn && board [r+1][c+2]==turn && board[r+2][c+2]==turn){
			win=true;
		}else if(board[r][c]==turn && board [r+1][c+1]==turn && board[r+2][c+2]==turn){
			win=true;
		}else if(board[r][c+2]==turn && board [r+1][c+1]==turn && board[r+2][c]==turn){
			win=true;
		}
		return win;

	}
	public boolean getWinMove(project4TicTac b,char turn){
		//This method is used by the computer to check if there is a winning move
		//it can make
		
		int [] moves = new int [2];			//an array to hold row and column of location on board
		boolean win =false;					//returns if a move will result in a win
	
		checkMove:							//meant to break to outer loop
			for(int i=0;i<board.length;i++){		//outer loop
				for(int j=0;j<board.length;j++){	//inner loop
					
					if(board[i][j]=='-'){			//iterated to all un-occupied locations on board
						moves[0]=i;					//checks eache for a win
						moves[1]=j;
						b.playerMove(i, j, turn);
						win = b.checkWin(turn);
						b.resetSpace(i, j);			//resets space to unoccupied after checking for win
						if(win==true){
							b.playerMove(i, j, turn);	//if a win is possible move is made and 
							b.setLastMove(i, j);		//sets last move so GUI can translate to location
							break checkMove;			//break to outter loop
							}
							}
				}
				
			}
		
		return win;									//returned boolean 
	}
	
	public boolean moveBlock(project4TicTac b, char turn){
	//Method checks to see if a block has to be made to prevent player from winning
	//is only executed if a winning move cannot be made
		
		char oppTurn='X';				//the opposite player symbol
		boolean oppWin=false;			//opposite player win
		int [] moves = new int [2];  	//holds board location
		
		
		checkMove:						//loop break
			for(int i=0;i<board.length;i++){
				for(int j=0;j<board.length;j++){
					
					if(board[i][j]=='-'){
						b.playerMove(i, j, oppTurn);
						oppWin = b.checkWin(oppTurn);	//itterates through board to check if there is a location
						b.resetSpace(i, j);				//where opponent can make a winning move
						if(oppWin==true){
							b.playerMove(i, j, turn);
							b.setLastMove(i, j);		
							break checkMove;
							}
							}
				}
				
			}
		
	return oppWin;
	}
	
public boolean checkTie(project4TicTac b){
	boolean tieCheck = true;
	
	checkMove:						//loop break
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board.length;j++){
				
				if(board[i][j]=='-'){
					tieCheck = false;	
					break checkMove;
				}
			}
		}
 return tieCheck;
}
public void makeEasyMove(project4TicTac b, char turn){
//	if a winning move cannot be made and a block does not have to be made
//  computer makes a simple move
	
	makeMove:				//loop break
		for(int i=0;i<board.length;i++){
			for(int j = 0; j<board.length;j++){
				if(board[i][j]=='-'){
					b.playerMove(i, j, turn);	//iterates through board looking for first
					b.setLastMove(i, j);		//unoccupied space
					break makeMove;				//break loop
				}
			}
		}
}
public void resetSpace(int r, int c){
	//simply resets space, used for different checking methods
	board[r][c]='-';
}

public void setLastMove(int lR, int lC){
	//sets the move made row and column
	lastRow = lR;
	lastCol = lC;
}
public int getLastMove(){
	// returns a number corresponding to a row and column
	// number tells GUI which button should be activated
	if(lastRow == 0 && lastCol == 0 ){
		return 1;
	}else if(lastRow == 0 && lastCol == 1 ){
		return 2;
	}else if(lastRow == 0 && lastCol == 2 ){
		return 3;
	}else if(lastRow == 1 && lastCol == 0 ){
		return 4;
	}else if(lastRow == 1 && lastCol == 1 ){
		return 5;
	}else if(lastRow == 1 && lastCol == 2 ){
		return 6;
	}else if(lastRow == 2 && lastCol == 0 ){
		return 7;
	}else if(lastRow == 2 && lastCol == 1 ){
		return 8;
	}else if(lastRow == 2 && lastCol == 2 ){
		return 9;
	}else{
		return 10;
	}
}
}


