import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BoardCenter{

    /* Chess pieces encoding:
        K - is King
        Q - is Queen
        H - is Knight(Horse)
        R - is Rook(Tura)
        P - is Pawn
        B - is Bishop */
    public static void main(String[] args) throws Exception {

        Piece[][] board = new Piece[8][8];
        //Enter data using BufferReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        initBoard(board);
        //printBoard(board);
        int myPiece_i = 6;
        int myPiece_j = 0;
        int force=0;
	String move = reader.readLine();
        while (true)
        {
	    
            move = reader.readLine();
	    if(move.equals("protover 2"))
		{
			System.out.println("feature sigint=0 sigterm=0 done=1");
			//move = reader.readLine();
			
		}
	    if(move.equals("force"))
		force=1;
	 if(move.length() ==4 && ((move.charAt(1) - 48) - 1) <9)
	{	
            int j1 = (move.charAt(0) - 'a');
            int i1 = (move.charAt(1) - 48) - 1;
            int j2 = (move.charAt(2) - 'a');
            int i2 = (move.charAt(3) - 48) - 1;
            board[i2][j2] = board[i1][j1];
            board[i1][j1] = null;

            if(myPiece_i != 0 && board[myPiece_i -1][myPiece_j] == null && force==0)
            {
                System.out.print("move ");
                System.out.print((char)(myPiece_j + 'a'));
                System.out.print(myPiece_i + 1);
                board[myPiece_i - 1][myPiece_j] = board[myPiece_i][myPiece_j];
                board[myPiece_i][myPiece_j] = null;
                myPiece_i--;

                System.out.print((char)(myPiece_j + 'a'));
                System.out.print(myPiece_i + 1);
            }
            else
                if(myPiece_i != 0 && myPiece_j != 7  && board[myPiece_i - 1][myPiece_j + 1] != null)
                {
                    System.out.print("move ");
                    System.out.print((char)(myPiece_j + 'a'));
                    System.out.print(myPiece_i + 1);
                    board[myPiece_i - 1][myPiece_j + 1] = board[myPiece_i][myPiece_j];
                    board[myPiece_i][myPiece_j] = null;
                    myPiece_i--;
                    myPiece_j++;
                    System.out.print((char)(myPiece_j + 'a'));
                    System.out.print(myPiece_i + 1);
                }
                else
		{
		
                    break;
		}

                System.out.println();
	}
            //printBoard(board);
        }
    }

    public static void printBoard(Piece[][] board)
    {
        for (int i = 7; i >= 0; i--)
        {
            for (int j = 0; j < 8; j++)
                System.out.print(board[i][j]);
            System.out.println();
        }
    }
    public static void initBoard(Piece[][] board)
    {
        for (int i = 0; i < 8; i++)
        {
            board[1][i] = new Pawn(true);
            board[6][i] = new Pawn(false);
        }
        board[0][0] = new Rook(true);
        board[0][7] = new Rook(true);
        board[0][1] = new Horse(true);
        board[0][6] = new Horse(true);
        board[0][2] = new Bishop(true);
        board[0][5] = new Bishop(true);
        board[0][3] = new King(true);
        board[0][4] = new Queen(true);


        board[7][0] = new Rook(false);
        board[7][7] = new Rook(false);
        board[7][1] = new Horse(false);
        board[7][6] = new Horse(false);
        board[7][2] = new Bishop(false);
        board[7][5] = new Bishop(false);
        board[7][3] = new King(false);
        board[7][4] = new Queen(false);
    }
}
