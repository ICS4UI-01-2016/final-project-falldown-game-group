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
    private final float OBSTACLE_GAP = 100;
    private final float MIN_HEIGHT = 50;
    private final float MAX_HEIGHT = 350;
    public static final float HEIGHT = 52;
    
    private boolean passed;
    private Vector2 position;
    private Texture obstacleRight;
    private Texture obstacleLeft;
    private Texture obstacleMiddle;
    
    private Rectangle rightBounds;
    private Rectangle leftBounds;
    private Rectangle middleBounds;
    
    
    public Obstacle(float x){
        float y = (int)(Math.random()*(325-25+1) + 25); 
        position = new Vector2(x,y);
        obstacleRight = new Texture("back.png");
        obstacleLeft = new Texture("back.png");
        obstacleMiddle = new Texture("back.png");
        
        rightBounds = new Rectangle(position.x + OBSTACLE_GAP/3, position.y, obstacleRight.getWidth(), obstacleRight.getHeight());
        leftBounds = new Rectangle(position.x - OBSTACLE_GAP/3, position.y - obstacleLeft.getHeight(), obstacleLeft.getWidth(), obstacleLeft.getHeight());
        passed=false;
    }
    
    public void render(SpriteBatch batch){
        batch.draw(obstacleRight, position.x + OBSTACLE_GAP/3, position.y);
        batch.draw(obstacleLeft, position.x - OBSTACLE_GAP/3 - obstacleLeft.getWidth(), position.y);
        
    }
    
    public float getX(){
        return position.x;
    }
    public float getY(){
        return position.y;
    }
    
    public void setY(float y){
        passed=false;
        position.y = y;
        float x = (int)(Math.random()*(325-25+1) + 25); 
        position.x = x;
        rightBounds.setPosition(position.x + OBSTACLE_GAP/2, position.y);
        leftBounds.setPosition(position.x, position.y - OBSTACLE_GAP/2 - obstacleLeft.getHeight());
    }
    
    public boolean collides(Falcon f){
        if(rightBounds.overlaps(f.getBounds())){
            return true;
        }
        if(leftBounds.overlaps(f.getBounds())){
            return true;
        }
        return false;
    }
    
    public void dispose(){
        obstacleRight.dispose();
        obstacleLeft.dispose();
    }
    public boolean hasPassed(){
        return passed;
    }
    public void pass(){
        passed = true;
    }
    
}
