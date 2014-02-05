package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication 
{
    
    Spatial bishop, king, knight, pawn, queen, rook;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() 
    {
        flyCam.setMoveSpeed(25f);
        cam.setLocation(new Vector3f(0, 0, 100));
        
        bishop = assetManager.loadModel("Models/bishop/bishop.mesh.j3o");
        king = assetManager.loadModel("Models/king/king.mesh.j3o");
        knight = assetManager.loadModel("Models/knight/knight.mesh.j3o");
        pawn = assetManager.loadModel("Models/pawn/pawn.mesh.j3o");
        queen = assetManager.loadModel("Models/queen/queen.mesh.j3o");
        rook = assetManager.loadModel("Models/rook/rook.mesh.j3o");

        cleanPiece(bishop);
        cleanPiece(king);
        cleanPiece(knight);
        cleanPiece(pawn);
        cleanPiece(queen);
        cleanPiece(rook);
        
        pawn.setLocalTranslation(-25, 0, 0);
        queen.setLocalTranslation(-50, 0, 0);
        king.setLocalTranslation(25, 0, 0);
        knight.setLocalTranslation(50, 0, 0);
        rook.setLocalTranslation(75, 0, 0);
        
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(1,0,-2).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        
        rootNode.addLight(sun);
        rootNode.attachChild(bishop);
        rootNode.attachChild(king);
        rootNode.attachChild(pawn);
        rootNode.attachChild(queen);
        rootNode.attachChild(knight);
        rootNode.attachChild(rook);
    }
    
    
    /**
     * Used to rotate and scale the chess pieces.
     * @param piece 
     */
    private void cleanPiece(Spatial piece)
    {
        piece.scale(0.25f);
        
        Quaternion rot = new Quaternion();
        rot.fromAngleAxis(FastMath.PI / 2, new Vector3f(1, 0, 0));
        piece.setLocalRotation(rot);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
        Vector3f v = new Vector3f(0, 0, 0);
        bishop.rotate(0, 0, v.z + 2 * tpf);
        king.rotate(0, 0, v.z + 2 * tpf);
        knight.rotate(0, 0, v.z + 2 * tpf);
        pawn.rotate(0, 0, v.z + 2 * tpf);
        queen.rotate(0, 0, v.z + 2 * tpf);
        rook.rotate(0, 0, v.z + 2 * tpf);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
