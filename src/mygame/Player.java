package mygame;

import com.jme3.math.ColorRGBA;
import com.jme3.scene.Spatial;
import java.util.LinkedList;

public class Player 
{
    private PieceGraphicData pieceData;
    private Board board;
    
    private String name;
    private ColorRGBA color;
    
    public static int playerNum = 0;
    
    private LinkedList<Spatial> pieces = new LinkedList();
    
    public Player(PieceGraphicData pd, Board b)
    {
        this.pieceData = pd;
        this.board = b;
        playerNum++;
        pd.setPlayerNum(playerNum);
    }
    
    public Player(String n, ColorRGBA c, PieceGraphicData pd, Board b)
    {
        this(pd, b);
        this.name = n;
        this.color = c;
    }
    
    public void genPieces()
    {
        pieceData.setPieces(getPieces(), getColor());
        System.out.println(getName() + " has " + getPieces().size() + " pieces available.");
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
     * @return the pieces
     */
    public LinkedList<Spatial> getPieces() {
        return pieces;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the color
     */
    public ColorRGBA getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(ColorRGBA color) {
        this.color = color;
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