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


    public int move(Piece[][] board, int poz_i, int poz_j) {
        if(this.color)
            return moveWhite(board,poz_i,poz_j);
        return moveBlack(board,poz_i,poz_j);
    }

    public int moveWhite(Piece[][] board, int poz_i, int poz_j) {
        return 0;
    }
    public int moveBlack(Piece[][] board, int poz_i, int poz_j){
        return 0;
    }

    @Override
    public Move getMove(Piece[][] board, int x, int y) {
        Move move = new Move();
        move.start_x = x;
        move.start_y = y;

        if(board[x][y].color)
            move = makeMoveWhite(board, move);
        else
            move = makeMoveBlack(board, move);

        return move;
    }

    @Override
    public Move makeMoveBlack(Piece[][] board, Move move) {
        move.value = Integer.MIN_VALUE;

        if(isValid(move.start_x + 1, move.start_y - 1) && board[move.start_x + 1][move.start_y - 1] != null)
        {
            if(board[move.start_x + 1][move.start_y - 1].value > move.value
                    && board[move.start_x + 1][move.start_y - 1].color)
            {
                move.final_x = move.start_x - 1;
                move.final_y = move.start_y - 1;
                move.value = board[move.start_x + 1][move.start_y - 1].value;
            }
        }

        if(isValid(move.start_x + 1, move.start_y + 1) && board[move.start_x + 1][move.start_y + 1] != null)
        {
            if(board[move.start_x + 1][move.start_y + 1].value > move.value &&
                    board[move.start_x + 1][move.start_y + 1].color)
            {
                move.final_x = move.start_x - 1;
                move.final_y = move.start_y + 1;
                move.value = board[move.start_x + 1][move.start_y - 1].value;
            }
        }

        if(isValid(move.start_x + 1, move.start_y) && board[move.start_x + 1][move.start_y] == null)
        {
            if( 10  > move.value)
            {
                move.final_x = move.start_x - 1;
                move.final_y = move.start_y - 1;
                move.value = 10;
            }
        }

        return move;
    }

    @Override
    public Move makeMoveWhite(Piece[][] board, Move move) {

        move.value = Integer.MIN_VALUE;

        if(isValid(move.start_x - 1, move.start_y - 1) && board[move.start_x - 1][move.start_y - 1] != null)
        {
            if(board[move.start_x - 1][move.start_y - 1].value > move.value
                    && !board[move.start_x - 1][move.start_y - 1].color)
            {
                move.final_x = move.start_x - 1;
                move.final_y = move.start_y - 1;
                move.value = board[move.start_x - 1][move.start_y - 1].value;
            }
        }

        if(isValid(move.start_x - 1, move.start_y + 1) && board[move.start_x - 1][move.start_y + 1] != null)
        {
            if(board[move.start_x - 1][move.start_y + 1].value > move.value
                    && !board[move.start_x - 1][move.start_y + 1].color)
            {
                move.final_x = move.start_x - 1;
                move.final_y = move.start_y + 1;
                move.value = board[move.start_x - 1][move.start_y - 1].value;
            }
        }

        if(isValid(move.start_x - 1, move.start_y) && board[move.start_x - 1][move.start_y] == null)
        {
            if( 10  > move.value)
            {
                move.final_x = move.start_x - 1;
                move.final_y = move.start_y - 1;
                move.value = 10;
            }
        }

        return move;
    }
}
