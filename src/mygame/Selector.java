package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

public class Selector 
{
    public enum Move
    {
        UP, DOWN, 
        LEFT, RIGHT;
    }
    
    
    AssetManager assetManager;
    Node rootNode;
    
    private Geometry cube;
    
    private int xPos = 5;
    private int yPos = 2;
    
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
    
    public int[] select()
    {
        int[] position = new int[2];
        
        System.out.println("xPos: " + xPos);
        System.out.println("yPos: " + yPos);
        
        position[0] = xPos;
        position[1] = yPos;
        
        return position;
    }
    
    public Vector3f move(Selector.Move movement)
    {
        Vector3f v = new Vector3f();
        Vector3f c = cube.getLocalTranslation();
        
        switch(movement)
        {
            case UP:
                yPos++;
                if (checkBoundries())
                {
                    v.set(c.x, c.y, c.z - 20.5f);
                }
                else
                {
                    v.set(c);
                }
                break;
                
            case DOWN:
                yPos--;
                if (checkBoundries())
                {
                    v.set(c.x, c.y, c.z + 20.5f);
                }
                else
                {
                    v.set(c);
                }
                break;
                
            case LEFT:
                xPos--;
                if (checkBoundries())
                {
                    v.set(c.x - 20.5f, c.y, c.z);
                }
                else
                {
                    v.set(c);
                }
                break;
                
            case RIGHT:
                xPos++;
                if (checkBoundries())
                {
                    v.set(c.x + 20.5f, c.y, c.z);
                }
                else
                {
                    v.set(c);
                }
                break;
        }
        return v;
    }
    
    private boolean checkBoundries()
    {
        if (xPos > 7)
        {
            xPos = 7;
            return false;
        }
        if (xPos < 0)
        {
            xPos = 0;
            return false;
        }
        if (yPos > 7)
        {
            yPos = 7;
            return false;
        }
        if (yPos < 0)
        {
            yPos = 0;
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
