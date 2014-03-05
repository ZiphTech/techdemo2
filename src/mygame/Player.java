package mygame;

import com.jme3.math.ColorRGBA;
import com.jme3.scene.Spatial;
import java.util.LinkedList;

public class Player 
{
    private PieceData pieceData;
    
    private String name;
    private ColorRGBA color;
    
    private LinkedList<Spatial> pieces = new LinkedList();
    
    public Player(String n, ColorRGBA c, PieceData pd, String num)
    {
        this.name = n;
        this.color = c;
        this.pieceData = pd;
        
        pd.setPlayerNum(num);
        genPieces();
    }
    
    private void genPieces()
    {
        pieceData.setPieces(pieces, color);
        System.out.println(name + " has " + pieces.size() + " pieces available.");
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