/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author lamon
 */
public class Obstacle {
    private final float PIPE_GAP = 100;
    private final float MIN_HEIGHT = 50;
    private final float MAX_HEIGHT = 350;
    public static final float WIDTH = 52;
    
    private boolean passed;
    private Vector2 position;
    private Texture obstacleTop;
    private Texture obstacleBottom;
    
    private Rectangle topBounds;
    private Rectangle bottomBounds;
    
    public Obstacle(float x){
        float y = (int)(Math.random()*(325-75+1) + 75); 
        position = new Vector2(x,y);
        obstacleTop = new Texture("toptube.png");
        obstacleBottom = new Texture("bottomtube.png");
        
        topBounds = new Rectangle(position.x, position.y + PIPE_GAP/2, obstacleTop.getWidth(), obstacleTop.getHeight());
        bottomBounds = new Rectangle(position.x, position.y - PIPE_GAP/2 - obstacleBottom.getHeight(), obstacleBottom.getWidth(), obstacleBottom.getHeight());
        passed=false;
    }
    
    public void render(SpriteBatch batch){
        batch.draw(obstacleTop, position.x, position.y + PIPE_GAP/2);
        batch.draw(obstacleBottom, position.x, position.y - PIPE_GAP/2 - obstacleBottom.getHeight());
    }
    
    public float getX(){
        return position.x;
    }
    
    public void setX(float x){
        passed=false;
        position.x = x;
        float y = (int)(Math.random()*(325-75+1) + 75); 
        position.y = y;
        topBounds.setPosition(position.x, position.y + PIPE_GAP/2);
        bottomBounds.setPosition(position.x, position.y - PIPE_GAP/2 - obstacleBottom.getHeight());
    }
    
    public boolean collides(Falcon f){
        if(topBounds.overlaps(f.getBounds())){
            return true;
        }
        if(bottomBounds.overlaps(f.getBounds())){
            return true;
        }
        return false;
    }
    
    public void dispose(){
        obstacleTop.dispose();
        obstacleBottom.dispose();
    }
    public boolean hasPassed(){
        return passed;
    }
    public void pass(){
        passed = true;
    }
    
}
