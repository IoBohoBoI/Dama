package dama.game;

public class Piece
{
    private boolean isWhite;
    private boolean isKing;

    public Piece(boolean isWhite)
    {
        this.isWhite = isWhite;
        this.isKing = false;
    }

    public boolean isWhite() { return isWhite; }

    public boolean isKing() { return isKing; }

    public void crown() { isKing = true; }

    public Piece clone()
    {
        Piece newPiece = new Piece(this.isWhite);
        newPiece.isKing = this.isKing;

        return newPiece;
    }
}
