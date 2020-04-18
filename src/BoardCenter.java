import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BoardCenter {

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
		//sunt tinute piesele in lista ca sa stim cu e mai putem sa facem miscari;
		ArrayList<Piece> whites = new ArrayList<>();
		ArrayList<Piece> blacks = new ArrayList<>();

		int x = 1;
		while (x == 1) {
			x = NewGame(board, reader , whites, blacks);
		}
	}

	public static int NewGame(Piece[][] board, BufferedReader reader, ArrayList whites, ArrayList blacks) throws IOException {
		initBoard(board);
		whites = new ArrayList();
		blacks = new ArrayList();
		//umple listele cu piesele corespunzatoare
		fillList(whites,true,board);
		fillList(blacks,false,board);
		//TODO new
		int myPiece_i = 6;
		int myPiece_j = 0;
		boolean force = false;
		boolean quit = false;
		boolean black = false;
		boolean white = false;
		String move;
		while (true) {

			move = reader.readLine();
			if (move.equals("protover 2")) {
				System.out.println("feature sigint=0 sigterm=0 done=1");

			}

			if (move.equals("analyze")) {
				force = true;
			}
			if (move.equals("black")) {
				if (white) {
					force = false;
					myPiece_i = 6;
					myPiece_j = findmyPawn(board, false);
					if (myPiece_j == -1)
						break;
				} else
					force = true;
				white = false;
				black = true;
			}
			if (move.equals("white")) {
				if (black) {
					//flipMatrix(board);
					force = false;
					myPiece_i = 1;
					myPiece_j = findmyPawn(board, true);
					if (myPiece_j == -1)
						break;
				} else
					force = true;
				black = false;
				white = true;
			}
			if (move.equals("new")) {
				return 1;
			}
			if (move.equals("quit")) {
				quit = true;
				break;
			}

			if ((move.length() == 4 && ((move.charAt(1) - 48) - 1) < 9) || black || white) {
				if (!black && !white) {
					int j1 = (move.charAt(0) - 'a');
					int i1 = (move.charAt(1) - 48) - 1;
					int j2 = (move.charAt(2) - 'a');
					int i2 = (move.charAt(3) - 48) - 1;
					board[i2][j2] = board[i1][j1];
					board[i1][j1] = null;

					if (i1 == myPiece_i && j1 == myPiece_j && force) {
						myPiece_i = i2;
						myPiece_j = j2;
					}
				}
				if (force)
					continue;
				black = false;
				white = false;
				if (!board[myPiece_i][myPiece_j].color) {
					if (myPiece_i != 0 && board[myPiece_i - 1][myPiece_j] == null) {
						System.out.print("move ");
						System.out.print((char) (myPiece_j + 'a'));
						System.out.print(myPiece_i + 1);
						board[myPiece_i - 1][myPiece_j] = board[myPiece_i][myPiece_j];
						board[myPiece_i][myPiece_j] = null;
						myPiece_i--;

						System.out.print((char) (myPiece_j + 'a'));
						System.out.print(myPiece_i + 1);
					} else if (myPiece_i != 0 && myPiece_j != 7 && board[myPiece_i - 1][myPiece_j + 1] != null) {
						System.out.print("move ");
						System.out.print((char) (myPiece_j + 'a'));
						System.out.print(myPiece_i + 1);
						board[myPiece_i - 1][myPiece_j + 1] = board[myPiece_i][myPiece_j];
						board[myPiece_i][myPiece_j] = null;
						myPiece_i--;
						myPiece_j++;
						System.out.print((char) (myPiece_j + 'a'));
						System.out.print(myPiece_i + 1);
					} else {

						break;
					}
				} else {
					if (myPiece_i != 7 && board[myPiece_i + 1][myPiece_j] == null) {
						System.out.print("move ");
						System.out.print((char) (myPiece_j + 'a'));
						System.out.print(myPiece_i + 1);
						board[myPiece_i + 1][myPiece_j] = board[myPiece_i][myPiece_j];
						board[myPiece_i][myPiece_j] = null;
						myPiece_i++;

						System.out.print((char) (myPiece_j + 'a'));
						System.out.print(myPiece_i + 1);
					} else if (myPiece_i != 7 && myPiece_j != 7 && board[myPiece_i + 1][myPiece_j + 1] != null) {
						System.out.print("move ");
						System.out.print((char) (myPiece_j + 'a'));
						System.out.print(myPiece_i + 1);
						board[myPiece_i + 1][myPiece_j + 1] = board[myPiece_i][myPiece_j];
						board[myPiece_i][myPiece_j] = null;
						myPiece_i++;
						myPiece_j++;
						System.out.print((char) (myPiece_j + 'a'));
						System.out.print(myPiece_i + 1);
					} else {
						break;
					}
				}


				System.out.println();
			}
			//printBoard(board);
		}
		if (!quit) {
			System.out.println("resign");
			while (!move.equals("new"))
				move = reader.readLine();
		}
		return 1;
	}

	public static int findmyPawn(Piece[][] board, boolean color) {
		int line;
		if (color)
			line = 1;
		else
			line = 6;


		for (int i = 0; i < 8; i++) {
			if (board[line][i] instanceof Pawn)
				return i;
		}
		return -1;
	}


	public static void printBoard(Piece[][] board) {
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++)
				System.out.print(board[i][j]);
			System.out.println();
		}
	}

	public static void initBoard(Piece[][] board) {
		for (int i = 0; i < 8; i++) {
			board[1][i] = new Pawn(true);
			board[6][i] = new Pawn(false);
			board[2][i] = null;
			board[3][i] = null;
			board[4][i] = null;
			board[5][i] = null;
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

	public static void flipMatrix(Piece[][] board) {

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				Piece aux = board[i][j];
				board[i][j] = board[7 - i][j];
				board[7 - i][j] = aux;
			}

		}
	}

	public static void fillList(ArrayList pieces, boolean color, Piece[][] board) {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (board[i][j].color == color)
					pieces.add(board[i][j]);
	}
}
