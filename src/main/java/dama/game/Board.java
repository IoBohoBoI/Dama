package dama.game;

public class Board
{
    public static final int SIZE = 8;
    private Piece[][] board;

    public Board()
    {
        board = new Piece[SIZE][SIZE];
        initializeBoard();
    }

    public void initializeBoard()
    {
        //Posiziona le pedine sulla scacchiera
        for (int row = 0; row < SIZE; row++)
            for (int col = 0; col < SIZE; col++)
                if ((row + col) % 2 == 1)
                {
                    if (row < 3)
                        board[row][col] = new Piece(false); //Nero
                    else if (row > 4)
                        board[row][col] = new Piece(true);  //Bianco
                }
    }

    // Metodo di clonazione della scacchiera (utilizzato per favorire il funzionamento dell'IA)
    public Board clone()
    {
        Board newBoard = new Board();
        newBoard.board = new Piece[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (board[i][j] != null)
                    newBoard.board[i][j] = board[i][j].clone();

        return newBoard;
    }

    public Piece getPiece(int row, int col)
    {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE)
            return null;

        return board[row][col];
    }

    public void setPiece(int row, int col, Piece piece)
    {
        if (row >= 0 && row < SIZE && col >= 0 && col < SIZE)
            board[row][col] = piece;
    }

    public Piece[][] getBoard() { return board; }
}
