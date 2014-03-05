package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

public class Selector 
{
    AssetManager assetManager;
    Node rootNode;
    
    private Geometry cube;
    
    private int bXPos = 0;
    private int bYPos = 0;
    
    private float alpha = 0.5f;
    private int delta = 1;
    private float change = 0.25f;
    
    public Selector(AssetManager am, Node n)
    {
        this.assetManager = am;
        this.rootNode = n;
        createSelector();
    }
    
    private void createSelector()
    {
        Box b = new Box(10.25f, 1, 10.25f);
        cube = new Geometry("Selector", b);
        getCube().move(31, -25, 31);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", new ColorRGBA(0, 1, 0, 0.5f));
        mat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        getCube().setQueueBucket(RenderQueue.Bucket.Translucent);
        getCube().setMaterial(mat);
        rootNode.attachChild(getCube());
    }
    
    public void alphaFade(float tpf)
    {
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        if (alpha < 0.40f || alpha > 1)
        {
            delta *= -1;
        }
        
        alpha += change * delta * tpf * 2;
        
        mat.setColor("Color", new ColorRGBA(0, 1, 0, alpha));
        mat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        cube.setQueueBucket(RenderQueue.Bucket.Translucent);
        cube.setMaterial(mat);
    }
    
    public void incX()
    {
        if(checkBoundries())
        {
            bXPos++;
        }
        else
        {
            bXPos--;
        }
    }
    
    public void decX()
    {
        if (checkBoundries())
        {
            bXPos--;
        }
        else
        {
            bXPos++;
        }
    }
    
    public void incY()
    {
        if (checkBoundries())
        {
            bYPos++;
        }
        else
        {
            bYPos--;
        }
    }
    
    public void decY()
    {
        if (checkBoundries())
        {
            bYPos--;
        }
        else
        {
            bYPos++;
        }
    }
    
    private boolean checkBoundries()
    {
        if (bXPos > 7)
        {
//            bXPos = 7;
            return false;
        }
        if (bXPos < 0)
        {
//            bXPos = 0;
            return false;
        }
        if (bYPos > 7)
        {
//            bYPos = 7;
            return false;
        }
        if (bYPos < 0)
        {
//            bYPos = 0;
            return false;
        }
        return true;
    }

    /**
     * @return the cube
     */
    public Geometry getCube() 
    {
        return cube;
    }
}
