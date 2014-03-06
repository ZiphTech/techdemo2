package mygame;

import com.jme3.math.ColorRGBA;
import com.jme3.scene.Spatial;
import java.util.LinkedList;

public class Player 
{
    private PieceData pieceData;
    
    private String name;
    private String num;
    private ColorRGBA color;
    
    private LinkedList<Spatial> pieces = new LinkedList();
    
    public Player(String n, ColorRGBA c, PieceData pd, String num)
    {
        this.name = n;
        this.color = c;
        this.pieceData = pd;
        this.num = num;
        
        pd.setPlayerNum(num);
        genPieces();
    }
    
    private void genPieces()
    {
        pieceData.setPieces(getPieces(), color);
        System.out.println(name + " has " + getPieces().size() + " pieces available.");
    }
    
    // Debug info
    public void pieceData()
    {
        for (int i = 0; i < getPieces().size(); i++)
        {
            System.out.println(getPieces().get(i).getName());
        }
    }
    
    public void pieceCount()
    {
        int pawnCount = 0;
        int rookCount = 0;
        int knightCount = 0;
        int bishopCount = 0;
        int queenCount = 0;
        int kingCount = 0;
        for (int i = 0; i < getPieces().size(); i++)
        {
            if (getPieces().get(i).getName().equals("pawn"))
            {
                pawnCount++;
            }
            if(getPieces().get(i).getName().equals("rook"))
            {
                rookCount++;
            }
            if(getPieces().get(i).getName().equals("knight"))
            {
                knightCount++;
            }
            if(getPieces().get(i).getName().equals("bishop"))
            {
                bishopCount++;
            }
            if(getPieces().get(i).getName().equals("queen"))
            {
                queenCount++;
            }
            if(getPieces().get(i).getName().equals("king"))
            {
                kingCount++;
            }
        }
        System.out.println("Pawn Count: " + pawnCount);
        System.out.println("Rook Count: " + rookCount);
        System.out.println("Knight Count: " + knightCount);
        System.out.println("bishop Count: " + bishopCount);
        System.out.println("Queen Count: " + queenCount);
        System.out.println("King Count: " + kingCount);
    }

    /**
     * @return the num
     */
    public String getNum() {
        return num;
    }

    /**
     * @return the pieces
     */
    public LinkedList<Spatial> getPieces() {
        return pieces;
    }
}
//public boolean hasPieces()
//{
//	if (pawns != empty)
//	{
//	}
//	if (... != empty)
//	
//	return false;
//}