package mygame;

import com.jme3.scene.Spatial;
import java.util.LinkedList;

public class Player 
{
    private PieceData pieceData;
    
    private String pName;
    private String pColor;
    
    private LinkedList<Spatial> pieces = new LinkedList();
    
    public Player(String n, String c, PieceData pd)
    {
        this.pName = n;
        this.pColor = c;
        this.pieceData = pd;
        
        pd.setColor(c);
        genPieces();
    }
    
    private void genPieces()
    {
        pieceData.setPieces(pieces);
        System.out.println(pName + " has " + pieces.size() + " pieces available.");
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