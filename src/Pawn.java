import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(boolean color)
    {
        this.color = color;
        this.value = 50;
    }

    @Override
    public String toString() {
        return " P  ";
    }

    @Override
    public void getMoves(Piece[][] board, int x, int y, ArrayList<Move> moves) {
        Move move = new Move();
        move.start_x = x;
        move.start_y = y;

        if(board[x][y].color)
            makeMoveWhite(board, move, moves);
        else
            makeMoveBlack(board, move, moves);
    }

    @Override
    public void makeMoveBlack(Piece[][] board, Move move, ArrayList<Move> moves) {
        if(isValid(move.start_x + 1, move.start_y - 1) && board[move.start_x + 1][move.start_y - 1] != null)
        {
            if(board[move.start_x + 1][move.start_y - 1].color)
            {
                move.final_x = move.start_x + 1;
                move.final_y = move.start_y - 1;
                moves.add(new Move(move.start_x,move.start_y,move.final_x,move.final_y,
                        board[move.start_x + 1][move.start_y - 1].value));
            }
        }

        if(isValid(move.start_x + 1, move.start_y + 1) && board[move.start_x + 1][move.start_y + 1] != null)
        {
            if(board[move.start_x + 1][move.start_y + 1].color)
            {
                move.final_x = move.start_x + 1;
                move.final_y = move.start_y + 1;
                move.value = board[move.start_x + 1][move.start_y + 1].value;
                moves.add(new Move(move.start_x,move.start_y,move.final_x,move.final_y,
                        board[move.start_x + 1][move.start_y + 1].value));
            }
        }

        if(isValid(move.start_x + 1, move.start_y) && board[move.start_x + 1][move.start_y] == null)
        {
                move.final_x = move.start_x + 1;
                move.final_y = move.start_y;
                moves.add(new Move(move.start_x,move.start_y,move.final_x,move.final_y,0));
        }
    }

    @Override
    public void makeMoveWhite(Piece[][] board, Move move, ArrayList<Move> moves) {

        if(isValid(move.start_x - 1, move.start_y - 1) && board[move.start_x - 1][move.start_y - 1] != null)
        {
            if(board[move.start_x - 1][move.start_y - 1].value > move.value
                    && !board[move.start_x - 1][move.start_y - 1].color)
            {
                move.final_x = move.start_x - 1;
                move.final_y = move.start_y - 1;
                moves.add(new Move(move.start_x,move.start_y,move.final_x,move.final_y,
                        board[move.start_x - 1][move.start_y - 1].value));
            }
        }

        if(isValid(move.start_x - 1, move.start_y + 1) && board[move.start_x - 1][move.start_y + 1] != null)
        {
            if(board[move.start_x - 1][move.start_y + 1].value > move.value
                    && !board[move.start_x - 1][move.start_y + 1].color)
            {
                move.final_x = move.start_x - 1;
                move.final_y = move.start_y + 1;
                moves.add(new Move(move.start_x,move.start_y,move.final_x,move.final_y,
                        board[move.start_x - 1][move.start_y + 1].value));
            }
        }

        if(isValid(move.start_x - 1, move.start_y) && board[move.start_x - 1][move.start_y] == null)
        {
                move.final_x = move.start_x - 1;
                move.final_y = move.start_y;
                moves.add(new Move(move.start_x,move.start_y,move.final_x,move.final_y,0));
        }
    }

//    public static void pawnWhite (Piece[][] board, int i , int j, ArrayList moveList) {
//
//        if (isValid(i+1, j) && board[i+1][j] == null)
//            moveList.add(new Move (i,j,i+1,j));
//        if(isValid (i+1, j-1) && (board[i+1][j-1] == null || board[i+1][j-1].color == false))
//            moveList.add(new Move (i,j,i+1,j-1));
//        if(isValid (i+1, j+1) && (board[i+1][j+1] == null || board[i+1][j+1].color == false))
//            moveList.add(new Move (i,j,i+1,j+1));
//        if(i==1 && board[i+2][j] == null)
//            moveList.add(new Move (i,j,i+2,j));
//    }
//
//    public static void pawnBlack (Piece[][] board, int i , int j, ArrayList moveList) {
//
//        if (isValid(i-1, j) && (board[i-1][j] == null || board[i-1][j].color == true))
//            moveList.add(new Move (i,j,i-1,j));
//        if(isValid (i-1, j-1) && (board[i-1][j-1] == null || board[i-1][j-1].color == true))
//            moveList.add(new Move (i,j,i-1,j-1));
//        if(isValid (i-1, j+1) && (board[i-1][j+1] == null || board[i-1][j+1].color == true))
//            moveList.add(new Move (i,j,i-1,j+1));
//        if(i==6 && board[i-2][j] == null)
//            moveList.add(new Move (i,j,i-2,j));
//    }
}
