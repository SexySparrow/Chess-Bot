import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

class Pair {
	public int first;
	public Move second;

	public Pair(int first, Move second) {
		this.first = first;
		this.second = second;
	}
}

public class BoardCenter {


	public static void main(String[] args) throws Exception {

		Piece[][] board = new Piece[8][8];
		//Enter data using BufferReader
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		//sunt tinute piesele in lista ca sa stim cu e mai putem sa facem miscari;

		int x = 1;
		while (x == 1) {
			x = NewGame(board, reader);
		}
	}

	public static int NewGame(Piece[][] board, BufferedReader reader) throws IOException {
		initBoard(board);
		//whites = new ArrayList();
		//blacks = new ArrayList();
		//umple listele cu piesele corespunzatoare
		//fillList(whites, true, board);
		//fillList(blacks, false, board);
		//TODO new
		int myPiece_i = 6;
		int myPiece_j = 0;
		boolean force = false;
		boolean quit = false;
		boolean black = false;
		boolean white = false;
		String move;
		boolean ok_black=false,ok_white=true;
		boolean go = false;
		while (true) {

			move = reader.readLine();
			if (move.equals("protover 2")) {
				System.out.println("feature sigint=0 sigterm=0 done=1");

			}

			if (move.equals("analyze")) {
				force = true;
			}
			if (move.equals("black")) {
				ok_black=true;
				ok_white=false;
				continue;
				// if (white) {
				// 	force = false;
				// 	myPiece_i = 6;
				// 	myPiece_j = findmyPawn(board, false);
				// 	if (myPiece_j == -1)
				// 		break;
				// } else
				// 	force = true;
				// white = false;
				// black = true;
			}
			if (move.equals("white")) {
				ok_white=true;
				ok_black=false;
				continue;
				// if (black) {
				// 	//flipMatrix(board);
				// 	force = false;
				// 	myPiece_i = 1;
				// 	myPiece_j = findmyPawn(board, true);
				// 	if (myPiece_j == -1)
				// 		break;
				// } else
				// 	force = true;
				// black = false;
				// white = true;
			}
			if (move.equals("new")) {
				return 1;
			}
			if (move.equals("quit")) {
				quit = true;
				break;
			}
			if(move.equals("go")) {
				System.out.println("dashwdas");
				go = true;

				Pair pair;
				ArrayList<Move> moves = new ArrayList<>();
				if(ok_black==true)
					pair = minimax_abeta(board, false, 2);
				else pair = minimax_abeta(board, true, 2);
				
				makeTheMove(board, pair.second);
				continue;


			}
 			boolean player = false;
			if (((move.length() == 4 || move.length() == 5) && ((move.charAt(1) - 48) - 1) < 9) || black || white ){
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
					if(move.length() == 5 && move.charAt(4) == 'q'){
						board[i2][j2] = new Queen(!player);
					}
				}
				if (force)
					continue;
				black = false;
				white = false;
				if(!go)
					continue;
				System.out.println("qqq");
				//go=false;
				//System.out.println("ddd");
				Pair pair;
				ArrayList<Move> moves = new ArrayList<>();
				

					
				if(ok_black==true)
					pair = minimax_abeta(board, false, 4);
				else pair = minimax_abeta(board, true, 4);
				
				makeTheMove(board, pair.second);
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

	public static boolean moveisValid(Piece[][] board, Move move) {
		if (move.start_x < 0 || move.start_x > 7)
			return false;
		if (move.start_y < 0 || move.start_y > 7)
			return false;
		if (move.final_y < 0 || move.final_x > 7)
			return false;
		if (move.final_x < 0 || move.final_y > 7)
			return false;

		if (board[move.start_x][move.start_y] == null)
			return false;
		if (board[move.final_x][move.final_y] != null)
			return board[move.start_x][move.start_y].color != board[move.final_x][move.final_x].color;
		return true;
	}

	public static void makeTheMove(Piece[][] board, Move move) {
		apply_move(board, move);
		System.out.print("move ");
		System.out.print((char) (move.start_y + 'a'));
		System.out.print(move.start_x + 1);
		System.out.print((char) (move.final_y + 'a'));
		System.out.print(move.final_x + 1);
		System.out.println();
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
		board[0][3] = new Queen(true);
		board[0][4] = new King(true);


		board[7][0] = new Rook(false);
		board[7][7] = new Rook(false);
		board[7][1] = new Horse(false);
		board[7][6] = new Horse(false);
		board[7][2] = new Bishop(false);
		board[7][5] = new Bishop(false);
		board[7][3] = new Queen(false);
		board[7][4] = new King(false);
	}


	public static ArrayList<Move> get_moves(Piece[][] board, boolean color) {
		ArrayList<Move> moveList = new ArrayList<>();
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (board[i][j] != null && board[i][j].color == color)
					board[i][j].getMoves(board, i, j, moveList);
		return moveList;
	}

	public static int eval(Piece[][] board, boolean color) {
		int score = 0;
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null && board[i][j].color == color)
					score += board[i][j].value;
				else if (board[i][j] != null && board[i][j].color != color)
					score -= board[i][j].value;
			}
		return score;
	}

	public static Piece[][] clone(Piece[][] board) {

		Piece[][] clone = new Piece[8][8];
		for (int i = 0; i < 8; i++)
			System.arraycopy(board[i], 0, clone[i], 0, 8);
		return clone;


	}

	public static boolean apply_move(Piece[][] board, Move move) {
		if (move.start_x == -1 || move.start_y == -1 || move.final_x == -1 || move.final_y == -1)
			return false;
		board[move.final_x][move.final_y] = board[move.start_x][move.start_y];
		board[move.start_x][move.start_y] = null;
		return true;
	}

	public static boolean isCheck(Piece[][] board, boolean color) {

		ArrayList<Move> moves = get_moves(board, !color);
		for (Move move : moves) {
			if (move.value == 10000)
				return true;
		}
		return false;
	}

	public static Pair minimax_abeta(Piece[][] board, boolean color, int depth) {

		if (depth == 0)
			return new Pair(eval(board, color), new Move());
		boolean ok=false;
		ArrayList<Move> moves = get_moves(board, color);
		if(moves==null)
			System.out.println("quit");
		Move bestMove = null;
		int max = Integer.MIN_VALUE;
		for (Move move : moves) {
			Piece[][] clone = clone(board);
			if (apply_move(clone, move) && !isCheck(clone, color)) {
				ok=true;
				Pair pair = minimax_abeta(clone, color, depth - 1);
				int score = -pair.first;
				
				if (score >= max) {
					max = score;
					bestMove = move;
					//System.out.println(bestMove.value + " " + bestMove.final_x + " " + bestMove.final_y);
				}

				
			}

		}
		
		return new Pair(max, bestMove);
	}
}
