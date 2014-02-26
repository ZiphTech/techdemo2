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


public class Main extends SimpleApplication 
{
    Spatial board;
    
    PieceData pd = new PieceData(rootNode);

    public static void main(String[] args) 
    {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() 
    {
        Quaternion q = new Quaternion();
        
        flyCam.setMoveSpeed(25f);
        cam.setLocation(new Vector3f(0, 159, 206));
        cam.lookAt(new Vector3f(0, -20, 10), new Vector3f(0, 0, 0));
        
        initKeys();
        
        pd.loadModels(assetManager);
        pd.cleanPieces();
        
        board = assetManager.loadModel("Models/board/board.j3o");
        
        board.scale(0.5f);
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
        
        // Maybe look into alternative lighting solutions
        // Having 2 DirectionalLights appear to have a hefty performance toll
        DirectionalLight sun = new DirectionalLight();
        DirectionalLight sun2 = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.5f, -0.8f).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        sun2.setDirection(new Vector3f(-0.1f, -0.5f, 0.8f).normalizeLocal());
        sun2.setColor(ColorRGBA.White);
        
        rootNode.addLight(sun);
        rootNode.addLight(sun2);
        rootNode.attachChild(board);
        
        Player whitePlayer = new Player("Cujo", "white", pd);
        Player blackPlayer = new Player("Dan", "black", pd);
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
//                System.out.println("CAM ROTATION: " + cam.getDirection());
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
