package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;
import java.util.LinkedList;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication 
{
    
    Spatial bishop, king, knight, pawn, queen, rook, board;
    
    final float BISHOP_HEIGHT = -8.5f;
    final float KING_HEIGHT = -8.5f;
    final float KNIGHT_HEIGHT = -13f;
    final float PAWN_HEIGHT = -11;
    final float QUEEN_HEIGHT = -11f;
    final float ROOK_HEIGHT = -12f;
    
    final float B_LEFT = -71;
    final float B_RIGHT = 70.75f;
    final float B_TOP = -72.5f;
        
    final float X_SPACE = 20.25f; // 20
    final float Z_SPACE = 22.5f; //22.5

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() 
    {
        flyCam.setMoveSpeed(25f);
        cam.setLocation(new Vector3f(0, 0, 100));
        
        initKeys();
        
        bishop = assetManager.loadModel("Models/bishop/bishop.mesh.j3o");
        king = assetManager.loadModel("Models/king/king.mesh.j3o");
        knight = assetManager.loadModel("Models/knight/knight.mesh.j3o");
        pawn = assetManager.loadModel("Models/pawn/pawn.mesh.j3o");
        queen = assetManager.loadModel("Models/queen/queen.mesh.j3o");
        rook = assetManager.loadModel("Models/rook/rook.mesh.j3o");
        board = assetManager.loadModel("Models/board/board.j3o");

        cleanPiece(bishop);
        cleanPiece(king);
        cleanPiece(knight);
        cleanPiece(pawn);
        cleanPiece(queen);
        cleanPiece(rook);
//        cleanPiece(board);
        board.scale(0.5f);
        Quaternion q = new Quaternion();
        q.fromAngleAxis(FastMath.PI / 2, new Vector3f(1, 0, 0));
        board.rotate(q);
        

        
        // Piece info || X = Left/Right Position on board
        //            || Y = Height off of board. (Positive raises!)
        //            || Z = Front/Back Position on the board
        
        // Square X space from middle to middle is 20
        // Meaning, from 1 middle square to its neighboor, it is X 20 units.
        // Far left of the board is -71
        // Far Right of the board is ??
        
        // Square Z space from middle to middle is 22.5
        // Meaning, from 1 middle square to behind/in front, is Z 22.5 units.
        // Far back of the board is -72.5
        // Far front of the board is ??
        
        // Y Value varies from piece to piece as each has a different height.
        
        board.setLocalTranslation(0, -35, 0);
//        pawn.setLocalTranslation(-25, 0, -100);
//        queen.setLocalTranslation(-71f, QUEEN_HEIGHT, -50f - 100);
//        king.setLocalTranslation(-71f, KING_HEIGHT, -72.5f - 100);
//        rook.setLocalTranslation(-31f, ROOK_HEIGHT, -50f - 100);
//        knight.setLocalTranslation(9, KNIGHT_HEIGHT, -50f - 100);
//        bishop.setLocalTranslation(-11f, BISHOP_HEIGHT, -50f - 100);
        
        
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.5f, -0.5f, -0.5f).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        
        rootNode.addLight(sun);
        rootNode.attachChild(board);
//        rootNode.attachChild(bishop);
//        rootNode.attachChild(king);
//        rootNode.attachChild(pawn);
//        rootNode.attachChild(queen);
//        rootNode.attachChild(knight);
//        rootNode.attachChild(rook);
        createPawn(pawn);
        createRook(rook);
        createKnight(knight);
        createBishop(bishop);
        createQueen(queen);
        createKing(king);
    }
    
    /**
     * Used to rotate and scale the chess pieces.
     * @param piece 
     */
    private void cleanPiece(Spatial piece)
    {
        piece.scale(0.25f);
        
        Quaternion rot = new Quaternion();
        // Rotate to front position.
        rot.fromAngleAxis(FastMath.PI * 3 / 2, new Vector3f(0, 0, 1));
        piece.rotate(rot);
        // Flip upward.
        rot.fromAngleAxis(FastMath.PI / 2, new Vector3f(0, 1, 0));
        piece.rotate(rot);

    }
    
    LinkedList<Spatial> pawns = new LinkedList();
    
    private void createPawn(Spatial p)
    {
        for (int i = 0; i < 8; i++)
        {
            pawns.add(p.clone());
            pawns.get(i).setLocalTranslation(B_LEFT + (X_SPACE * i), PAWN_HEIGHT, B_TOP + Z_SPACE);
            rootNode.attachChild(pawns.get(i));
        }
    }
    
    LinkedList<Spatial> rooks = new LinkedList();
    private void createRook(Spatial r)
    {
        for (int i = 0; i < 2; i++)
        {
            rooks.add(r.clone());
            rooks.get(i).setLocalTranslation(B_LEFT + (X_SPACE * i * 7), ROOK_HEIGHT, B_TOP);
            rootNode.attachChild(rooks.get(i));
        }
    }
    
    LinkedList<Spatial> knights = new LinkedList();
    private void createKnight(Spatial k)
    {
        for (int i = 0; i < 2; i++)
        {
            knights.add(k.clone());
            knights.get(i).setLocalTranslation(B_LEFT + X_SPACE + (i * X_SPACE * 5), KNIGHT_HEIGHT, B_TOP);
            rootNode.attachChild(knights.get(i));
        }
    }
    
    LinkedList<Spatial> bishops = new LinkedList();
    private void createBishop(Spatial b)
    {
        for (int i = 0; i < 2; i++)
        {
            bishops.add(b.clone());
            bishops.get(i).setLocalTranslation(B_LEFT + X_SPACE * 2 + (i * X_SPACE * 3), BISHOP_HEIGHT, B_TOP);
            rootNode.attachChild(bishops.get(i));
        }
    }
    LinkedList<Spatial> queens = new LinkedList();
    private void createQueen(Spatial q)
    {
        for (int i = 0; i < 1; i++)
        {
            queens.add(q.clone());
            queens.get(i).setLocalTranslation(B_LEFT + X_SPACE * 3, QUEEN_HEIGHT, B_TOP);
            rootNode.attachChild(queens.get(i));
        }
    }
    
    LinkedList<Spatial> kings = new LinkedList();
    private void createKing(Spatial k)
    {
        for (int i = 0; i < 1; i++)
        {
            kings.add(k.clone());
            kings.get(i).setLocalTranslation(B_LEFT + X_SPACE * 4, KING_HEIGHT, B_TOP);
            rootNode.attachChild(kings.get(i));
        }
    }
    
    private void initKeys()
    {
        inputManager.addMapping("Click", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
//        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
//        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
//        inputManager.addMapping("Pause", new KeyTrigger(KeyInput.KEY_P));
//        
        inputManager.addListener(actionListener, "Click");
//        inputManager.addListener(analogListener, "Left", "Right");
    }
    
    private ActionListener actionListener = new ActionListener()
    {
        public void onAction(String name, boolean isPressed, float tpf) 
        {
            if (name.equals("Click") && !isPressed)
            {
//                System.out.println("PAWN LOCATION: " + pawn.getLocalTranslation());
            }
        }
    };

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
