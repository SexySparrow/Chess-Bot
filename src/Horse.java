import java.util.ArrayList;

public class Horse extends Piece {
    public Horse(boolean color)
    {
        this.color = color;
        this.value = 150;
    }

    @Override
    public String toString() {
        return " H  ";
    }

    //TODO implementeaza motode din Piece
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
        Move[] moves2 = new Move[8];
        for (int i = 0; i < 8; i++)
        {
            moves2[i].value = Integer.MIN_VALUE;
            moves2[i].start_x = move.start_x;
            moves2[i].start_y = move.start_y;
        }
        int[] index_x = {1,1,2,2,-1,-1,-2,-2};
        int[] index_y = {-2,2,-1,1,-2,2,-1,1};

        for (int i = 0; i < 8; i++){
            moves2[i].final_x = moves2[i].start_x + index_x[i];
            moves2[i].final_y = moves2[i].start_y + index_y[i];
            moves2[i] = checkPosition(board, moves2[i]);

            if(moves2[i] != null)
                moves.add(new Move(moves2[i].start_x,moves2[i].start_y,moves2[i].final_x,
                        moves2[i].final_y,moves2[i].value));
        }
    }

    @Override
    public void makeMoveWhite(Piece[][] board, Move move, ArrayList<Move> moves) {
        makeMoveBlack(board, move, moves);
    }

    public Move checkPosition(Piece[][] board, Move move){

        move.value = Integer.MIN_VALUE;
        if (!isValid(move.final_x, move.final_y))
            return null;

        if(board[move.final_x][move.final_y] == null){
            move.value = 0;
            return move;
        }

        if(board[move.start_x][move.start_y].color != board[move.final_x][move.final_y].color){
            move.value = board[move.final_x][move.final_y].value;
            return move;
        }
        return null;
    }

}
