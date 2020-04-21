import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

		int x = 1;
		while (x == 1) {
			x = NewGame(board, reader);
		}
	}

	public static int NewGame(Piece[][] board, BufferedReader reader) throws IOException {
		initBoard(board);
		int myPiece_i = 6;
		int myPiece_j = 0;
		boolean force = false;
		String move;
		boolean ok_black=false;
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
				continue;
			}
			if (move.equals("white")) {
				ok_black=false;
				continue;
			}
			if (move.equals("new")) {
				return 1;
			}
			if (move.equals("quit")) {
				break;
			}
			if(move.equals("go")) {
				go = true;
				Pair pair;
				if(ok_black)
					pair = negaMax(board, false, 4, false);
				else pair = negaMax(board, true, 4, true);
				makeTheMove(board, pair.second);
				continue;
			}

			if ((move.length() == 4 || move.length() == 5) && move.charAt(1) - 48 - 1 < 9){
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
					board[i2][j2] = new Queen(ok_black);
				}
				if (force)
					continue;
				if(!go)
					continue;
				Pair pair;


				if(ok_black)
					pair = negaMax(board, false, 4, false);
				else pair = negaMax(board, true, 4, true);
				
				makeTheMove(board, pair.second);
			}
		}
		return 1;
	}

	public static void makeTheMove(Piece[][] board, Move move) {
		apply_move(board, move);
		System.out.print("move ");
		System.out.print((char) (move.start_y + 'a'));
		System.out.print(move.start_x + 1);
		System.out.print((char) (move.final_y + 'a'));
		System.out.print(move.final_x + 1);
		if(board[move.final_x][move.final_y].color){
			if(move.final_x == 7 && board[move.final_x][move.final_y] instanceof Pawn) {
				board[move.final_x][move.final_y] = new Queen(true);
				System.out.print("q");
			}
		}
		else
		{
			if(move.final_x == 0 && board[move.final_x][move.final_y] instanceof Pawn) {
				board[move.final_x][move.final_y] = new Queen(false);
				System.out.print("q");
			}
		}
		System.out.println();
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
		if(move == null)
			return false;
		if (move.start_x == -1 || move.start_y == -1 || move.final_x == -1 || move.final_y == -1)
			return false;
		board[move.final_x][move.final_y] = board[move.start_x][move.start_y];
		board[move.start_x][move.start_y] = null;
		return true;
	}

	public static boolean isCheck(Piece[][] board, boolean color) {

		ArrayList<Move> moves = get_moves(board, !color);
		for (Move move : moves) {
			if (board[move.final_x][move.final_y] instanceof King)
				return true;
		}
		return false;
	}

	public static Pair negaMax(Piece[][] board, boolean color, int depth, boolean player_color) {

		if (depth == 0)
			return new Pair(eval(board, color), new Move());
		ArrayList<Move> moves = get_moves(board, color);

		Move bestMove = null;
		int max = Integer.MIN_VALUE;
		for (Move move : moves) {
			Piece[][] clone = clone(board);
			if (apply_move(clone, move) && !isCheck(clone, player_color)) {
				Pair pair = negaMax(clone, !color, depth - 1, player_color);
				int score = -pair.first;
				
				if (score >= max) {
					max = score;
					bestMove = move;
				}

			}

		}
		
		return new Pair(max, bestMove);
	}
}
