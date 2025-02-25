package dama.game;

import java.util.ArrayList;
import java.util.List;

public class Move
{
    private int startRow, startCol;
    private int endRow, endCol;
    private boolean isCapture;
    private int capturedRow, capturedCol;

    //Costruttore mossa
    public Move(int startRow, int startCol, int endRow, int endCol)
    {
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
        this.isCapture = false;
    }

    //Costruttore cattura
    public Move(int startRow, int startCol, int endRow, int endCol, int capturedRow, int capturedCol)
    {
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
        this.isCapture = true;
        this.capturedRow = capturedRow;
        this.capturedCol = capturedCol;
    }

    public int getStartRow() { return startRow; }
    public int getStartCol() { return startCol; }
    public int getEndRow() { return endRow; }
    public int getEndCol() { return endCol; }
    public boolean isCapture() { return isCapture; }
    public int getCapturedRow() { return capturedRow; }
    public int getCapturedCol() { return capturedCol; }

    //Restituisce le mosse possibili
    public static List<Move> getPossibleMoves(Board board, int row, int col)
    {
        List<Move> moves = new ArrayList<>();
        Piece piece = board.getPiece(row, col);

        if (piece == null)
            return moves;

        int direction = piece.isWhite() ? -1 : 1;
        int[] dirCols = {-1, 1};
        int[] dirRows = piece.isKing() ? new int[]{-1, 1} : new int[]{direction};

        //Mosse
        for (int dr : dirRows)
            for (int dc : dirCols)
            {
                int newRow = row + dr;
                int newCol = col + dc;

                if (isValidMove(board, row, col, newRow, newCol))
                    moves.add(new Move(row, col, newRow, newCol));
            }

        //Cattura
        for (int dr : dirRows)
            for (int dc : dirCols)
            {
                int midRow = row + dr;
                int midCol = col + dc;
                int newRow = row + 2 * dr;
                int newCol = col + 2 * dc;

                if (isValidCapture(board, row, col, midRow, midCol, newRow, newCol))
                    moves.add(new Move(row, col, newRow, newCol, midRow, midCol));
            }

        return moves;
    }

    private static boolean isValidMove(Board board, int row, int col, int newRow, int newCol)
    {
        if (newRow < 0 || newRow >= Board.SIZE || newCol < 0 || newCol >= Board.SIZE)
            return false;

        if (board.getPiece(newRow, newCol) != null)
            return false;

        return true;
    }

    private static boolean isValidCapture(Board board, int row, int col, int midRow, int midCol, int newRow, int newCol)
    {
        if (newRow < 0 || newRow >= Board.SIZE || newCol < 0 || newCol >= Board.SIZE)
            return false;

        if (board.getPiece(newRow, newCol) != null)
            return false;

        Piece piece = board.getPiece(row, col);
        Piece middlePiece = board.getPiece(midRow, midCol);

        if (middlePiece == null)
            return false;

        if (middlePiece.isWhite() == piece.isWhite())
            return false;

        return true;
    }
}
