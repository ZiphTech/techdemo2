package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
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
    
    PieceData pd;
    Selector cube;
    ChessLogic cl;
    
//    Geometry cube;

    public static void main(String[] args) 
    {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() 
    {
        pd = new PieceData(assetManager, rootNode);
        cube = new Selector(assetManager, rootNode);
        
        initCam();
        initKeys();
        initBoard();
        initLights();
        
        pd.loadModels();
        pd.cleanPieces();
        
        Player whitePlayer = new Player("Abe", ColorRGBA.White, pd, "1");
        Player blackPlayer = new Player("Cujo", ColorRGBA.DarkGray, pd, "2");
        
        /* 
         * TESTING ZONE
         */
        cl = new ChessLogic();
        cl.initBoard();
        
//        whitePlayer.pieceData();
        whitePlayer.pieceCount();
        
        cl.setPiecePosition(whitePlayer.getPieces(), whitePlayer.getNum());
        cl.drawBoard();
        /*
         * END TESTING ZONE
         */
    }
    
    private void initCam()
    {
        flyCam.setMoveSpeed(25f);
//        flyCam.setEnabled(false);
        
        cam.setLocation(new Vector3f(0, 159, 206));
        cam.lookAt(new Vector3f(0, -20, 10), new Vector3f(0, 0, 0));
    }
    
    private void initBoard()
    {
        Quaternion q = new Quaternion();
        board = assetManager.loadModel("Models/board/board.j3o");
        
        board.scale(0.5f);
        q.fromAngleAxis(FastMath.PI / 2, new Vector3f(1, 0, 0));
        board.rotate(q);
        
        board.setLocalTranslation(0, -35, 0);
        rootNode.attachChild(board);
    }
    
    private void initLights()
    {
        // Maybe look into alternative lighting solutions
        // Having 2 DirectionalLights appear to have a hefty performance toll
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.5f, -0.8f).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        
        rootNode.addLight(sun);
    }
    
    private void initKeys()
    {
        inputManager.addMapping("Click", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addMapping("Up", new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping("Down", new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addMapping("Select", new KeyTrigger(KeyInput.KEY_SPACE));
//        inputManager.addMapping("Pause", new KeyTrigger(KeyInput.KEY_P));
//        
        inputManager.addListener(actionListener, "Click", "Up", "Left", "Right", "Down", "Select");
    }
    
    private ActionListener actionListener = new ActionListener()
    {
        public void onAction(String name, boolean isPressed, float tpf) 
        {
            if (name.equals("Click") && !isPressed)
            {
//                System.out.println("CAM ROTATION: " + cam.getDirection());
//                System.out.println("PICKER LOC: " + cube.getLocalTranslation().toString());
            }
            if (name.equals("Up") && !isPressed)
            {
                cube.getCube().setLocalTranslation(cube.move(Selector.Move.UP));
            }
            if (name.equals("Down") && !isPressed)
            {
                cube.getCube().setLocalTranslation(cube.move(Selector.Move.DOWN));
            }
            if (name.equals("Left") && !isPressed)
            {
                cube.getCube().setLocalTranslation(cube.move(Selector.Move.LEFT));
            }
            if (name.equals("Right") && !isPressed)
            {
                cube.getCube().setLocalTranslation(cube.move(Selector.Move.RIGHT));
            }
            if (name.equals("Select") && !isPressed)
            {
                cl.selectorPosition(cube.select());
            }
        }
    };


    @Override
    public void simpleUpdate(float tpf) 
    {
        //TODO: add update code
        cube.alphaFade(tpf);
    }

    @Override
    public void simpleRender(RenderManager rm) 
    {
        //TODO: add render code
    }
}
