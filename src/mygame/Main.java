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
//        Box b = new Box(10.25f, 1, 10.25f);
//        cube = new Geometry("Selector", b);
//        cube.move(31, -25, 31);
//        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        mat.setColor("Color", new ColorRGBA(0, 1, 0, 0.5f));
//        mat.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
//        cube.setQueueBucket(Bucket.Translucent);
//        cube.setMaterial(mat);
//        rootNode.attachChild(cube);
        /*
         * END TESTING ZONE
         */
    }
    
    private void initCam()
    {
        flyCam.setMoveSpeed(25f);
        flyCam.setEnabled(false);
        
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
//        inputManager.addMapping("Pause", new KeyTrigger(KeyInput.KEY_P));
//        
        inputManager.addListener(actionListener, "Click", "Up", "Left", "Right", "Down");
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
                Vector3f v = cube.getCube().getLocalTranslation();
                cube.getCube().setLocalTranslation(v.x, v.y, v.z - 20.5f);
                cube.incX();
            }
            if (name.equals("Down") && !isPressed)
            {
                Vector3f v = cube.getCube().getLocalTranslation();
                cube.getCube().setLocalTranslation(v.x, v.y, v.z + 20.5f);
            }
            if (name.equals("Left") && !isPressed)
            {
                Vector3f v = cube.getCube().getLocalTranslation();
                cube.getCube().setLocalTranslation(v.x - 20.5f, v.y, v.z);
            }
            if (name.equals("Right") && !isPressed)
            {
                Vector3f v = cube.getCube().getLocalTranslation();
                cube.getCube().setLocalTranslation(v.x + 20.5f, v.y, v.z);
            }
        }
    };

//    float alpha = 0.5f;
//    int delta = 1;
//    float change = 0.25f;

    @Override
    public void simpleUpdate(float tpf) 
    {
        cube.alphaFade(tpf);
        //TODO: add update code
//        Vector3f v = cam.getLocation();

//        cube.setLocalTranslation(v.x, v.y - 25, v.z);
        
        
//        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        if (alpha < 0.40f || alpha > 1)
//        {
//            delta *= -1;
//        }
//        
//        alpha += change * delta * tpf * 2;
//        
//        mat.setColor("Color", new ColorRGBA(0, 1, 0, alpha));
//        mat.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
//        cube.getCube().setQueueBucket(Bucket.Translucent);
//        cube.getCube().setMaterial(mat);
    }

    @Override
    public void simpleRender(RenderManager rm) 
    {
        //TODO: add render code
    }
}
