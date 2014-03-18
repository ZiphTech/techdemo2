package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.LinkedList;

public class PieceGraphicData implements IBoardData
{
    private Spatial bishop, king, knight, pawn, queen, rook;
    
    private final float BISHOP_HEIGHT = -8.5f;
    private final float KING_HEIGHT = -8.5f;
    private final float KNIGHT_HEIGHT = -13f;
    private final float PAWN_HEIGHT = -11;
    private final float QUEEN_HEIGHT = -11f;
    private final float ROOK_HEIGHT = -12f;
    

    // Piece info || X = Left/Right Position on board
    //            || Y = Height off of board. (Positive raises!)
    //            || Z = Front/Back Position on the board

    // Square X space from middle to middle is 20
    // Meaning, from 1 middle square to its neighboor, it is X 20 units.
    // Far left of the board is -71
    // Far Right of the board is 70.75f

    // Square Z space from middle to middle is 22.5
    // Meaning, from 1 middle square to behind/in front, is Z 22.5 units.
    // Far back of the board is -72.5f
    // Far front of the board is ??

    // Y Value varies from piece to piece as each has a different height.
    
//    // Positional spots on the board.
//    private final float B_LEFT = -71;
////    private final float B_RIGHT = 70.75f; // Might not need
//    private final float B_TOP = -72.5f;
//
//    // Space between squares on the board.
//    private final float X_SPACE = 20.25f; // 20
//    private final float Z_SPACE = 22.5f; //22.5
    
    private Node rootNode;
    private AssetManager assetManager;
    
    private int playerNum;
    
    public PieceGraphicData(AssetManager am, Node r)
    {
        this.rootNode = r;
        this.assetManager = am;
    }
    
    public void loadModels()
    {
        bishop = assetManager.loadModel("Models/bishop/bishop.mesh.j3o");
        king = assetManager.loadModel("Models/king/king.mesh.j3o");
        knight = assetManager.loadModel("Models/knight/knight.mesh.j3o");
        pawn = assetManager.loadModel("Models/pawn/pawn.mesh.j3o");
        queen = assetManager.loadModel("Models/queen/queen.mesh.j3o");
        rook = assetManager.loadModel("Models/rook/rook.mesh.j3o");       
        setNames();
        cleanPieces();
    }
    
    private void setNames()
    {
        bishop.setName("bishop");
        king.setName("king");
        knight.setName("knight");
        pawn.setName("pawn");
        queen.setName("queen");
        rook.setName("rook");
    }
    
    private void cleanPieces()
    {
        cleanPiece(bishop);
        cleanPiece(king);
        cleanPiece(knight);
        cleanPiece(pawn);
        cleanPiece(queen);
        cleanPiece(rook);
    }
    
    private void cleanPiece(Spatial p)
    {
        p.scale(0.25f);
        
        Quaternion rot = new Quaternion();
        // Rotate to front position.
        rot.fromAngleAxis(FastMath.PI * 3 / 2, new Vector3f(0, 0, 1));
        p.rotate(rot);
        // Flip upward.
        rot.fromAngleAxis(FastMath.PI / 2, new Vector3f(0, 1, 0));
        p.rotate(rot);
    }
    
    public void setPieces(LinkedList<Spatial> pieces, ColorRGBA pColor)
    {
        setPiece(pawn, pieces, 8, pColor);
        setPiece(rook, pieces, 2, pColor);
        setPiece(knight, pieces, 2, pColor);
        setPiece(bishop, pieces, 2, pColor);
        setPiece(queen, pieces, 1, pColor);
        setPiece(king, pieces, 1, pColor);
    }
    
    private void setPiece(Spatial p, LinkedList<Spatial> list, int amount, ColorRGBA pColor)
    {
        // Needed to keep our list size in check.
        // Fixes issues with pieces not being placed correctly
        int listSize = list.size();
        
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setColor("Diffuse", pColor);
        mat.setBoolean("UseMaterialColors", true);
        
        if (p.getName().equals("knight") && playerNum == 2)
        {
            Quaternion rot = new Quaternion();
            rot.fromAngleAxis(FastMath.PI, new Vector3f(0, 0, 1));
            p.rotate(rot);
        }

        
        for (int i = 0; i < amount; i++)
        {
            p.setMaterial(mat);
            list.add(p.clone());
            list.get(i + listSize).setLocalTranslation(pieceLocation(p, i));
            rootNode.attachChild(list.get(i + listSize));
        }
    }
    
    
    // DO NOT EDIT UNLESS ABSOLUTLY NECESSARY!!!!!
    private Vector3f pieceLocation(Spatial p, int count)
    {
        Vector3f loc = new Vector3f();
        
        if (p.getName().equals("pawn"))
        {
            if (getPlayerNum() == 1)
            {
                return loc.set(B_LEFT + (X_SPACE * count), PAWN_HEIGHT, B_TOP + Z_SPACE);
            }
            else
            {
                return loc.set(B_LEFT + (X_SPACE * count), PAWN_HEIGHT, -B_TOP - Z_SPACE);
            }
        }
        if (p.getName().equals("rook"))
        {
            if (getPlayerNum() == 1)
            {
                return loc.set(B_LEFT + (X_SPACE * count * 7), ROOK_HEIGHT, B_TOP);
            }
            else
            {
                return loc.set(B_LEFT + (X_SPACE * count * 7), ROOK_HEIGHT, -B_TOP);
            }
        }
        if (p.getName().equals("knight"))
        {
            if (getPlayerNum() == 1)
            {
                return loc.set(B_LEFT + X_SPACE + (count * X_SPACE * 5), KNIGHT_HEIGHT, B_TOP);
            }
            else
            {
                return loc.set(B_LEFT + X_SPACE + (count * X_SPACE * 5), KNIGHT_HEIGHT, -B_TOP);
            }
        }
        if (p.getName().equals("bishop"))
        {
            if (getPlayerNum() == 1)
            {
                return loc.set(B_LEFT + X_SPACE * 2 + (count * X_SPACE * 3), BISHOP_HEIGHT, B_TOP);
            }
            else
            {
                return loc.set(B_LEFT + X_SPACE * 2 + (count * X_SPACE * 3), BISHOP_HEIGHT, -B_TOP);
            }
        }
        if (p.getName().equals("queen"))
        {
            if (getPlayerNum() == 1)
            {
                return loc.set(B_LEFT + X_SPACE * 3, QUEEN_HEIGHT, B_TOP);
            }
            else
            {
                return loc.set(B_LEFT + X_SPACE * 3, QUEEN_HEIGHT, -B_TOP);
            }
        }
        if (p.getName().equals("king"))
        {
            if (getPlayerNum() == 1)
            {
                return loc.set(B_LEFT + X_SPACE * 4, KING_HEIGHT, B_TOP);
            }
            else
            {
                return loc.set(B_LEFT + X_SPACE * 4, KING_HEIGHT, -B_TOP);
            }
        }
        return null;
    }

    /**
     * @return the playerNum
     */
    public int getPlayerNum() {
        return playerNum;
    }

    /**
     * @param playerNum the playerNum to set
     */
    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }
}