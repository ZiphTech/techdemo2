package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() 
    {
        Spatial liz = assetManager.loadModel("Models/lizard.j3o");
        
        liz.scale(0.5f);
        
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(0, 0, -2f));
        
        DirectionalLight dl2 = new DirectionalLight();
        dl.setDirection(new Vector3f(5f, 0, -2f));
        
        liz.addLight(dl);
        liz.addLight(dl2);
        
        rootNode.attachChild(liz);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
