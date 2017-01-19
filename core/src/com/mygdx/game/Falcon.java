/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author lamon
 */
public class Falcon {
    private Vector3 position;
    private Vector3 velocity;
    private Texture falconPic;
    private Rectangle bounds;
    
    private final float GRAVITY = -15;
    private final float MOVEMENT = 100;
    
    public Falcon(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(MOVEMENT,0,0);
        falconPic = new Texture("Falcon.jpg");
        bounds = new Rectangle(position.x, position.y, falconPic.getWidth(), falconPic.getHeight());
    }
    
    
    
    public void update(float deltaTime){
        // add gravity
        velocity.y += GRAVITY;
        // scaling velocity by time
        velocity.scl(deltaTime);
        // adding velocity to position
        position.add(velocity);
        // unscale velocity
        velocity.scl(1/deltaTime);
        
        // set the new bounds
        bounds.setPosition(position.x, position.y);
    }
    
    public void render(SpriteBatch batch){
        batch.draw(falconPic, position.x, position.y);
    }
    
    public float getX(){
        return position.x;
    }
    
    public float getY(){
        return position.y;
    }
    
    public float moveRight(){
        return position.x-MOVEMENT;
    }
    
    public float moveLeft(){
        return position.x+MOVEMENT;
    }
    public float stop(){
        return position.x;
    }
    public float collide(){
        return position.y=0;
    }
    public Rectangle getBounds(){
        return bounds;
    }
    
    
    public void dispose(){
        falconPic.dispose();
    }
}
