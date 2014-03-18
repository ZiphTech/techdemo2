package mygame;

public class PieceLogic 
{
    String piece;
    int location;
    
    
    public boolean isClear()
    {
        boolean clear = true;
        
        if (piece.equals("pawn"))
        {
            
        }
        
        return clear;
    }
    
    private boolean emptyCheck()
    {
        String[][] box = new String[3][3];
        for (int x = 0; x < 3; x++)
        {
            for (int y = 0; y < 3; y++)
            {
                box[x][y] = "*";
            }
        }
        return false;
    }
}
