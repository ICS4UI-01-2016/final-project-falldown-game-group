/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Falcon;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Obstacle;


/**
 *
 * @author lamon
 */
public class PlayState extends State {

    private Falcon falcon;
    private Obstacle[] obstacles;
    
    private Texture bg;
    private int score;
    private BitmapFont font;

    private final float CAM_Y_OFFSET = 30;
    private final float OBSTACLE_GAP_AMOUNT = 4;

    public PlayState(StateManager sm) {
        super(sm);
        setCameraView(MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);
        //setCameraPosition(FlappyBird.WIDTH/2, FlappyBird.HEIGHT/2);
        falcon = new Falcon(50, 200);
        bg = new Texture("bg.png");
        // move the camera to match the bird
        moveCameraY(falcon.getY() + CAM_Y_OFFSET);

        // creating the pipes
        obstacles = new Obstacle[3];
        for (int i = 0; i < obstacles.length; i++) {
            obstacles[i] = new Obstacle(200 + OBSTACLE_GAP_AMOUNT * Obstacle.WIDTH * i);
            
        }
        score=0;
        font =new BitmapFont();
    }

    @Override
    public void render(SpriteBatch batch) {
        // draw the screen
        // link spritebatch to the camera
        batch.setProjectionMatrix(getCombinedCamera());
        // beginning of stuff to draw
        batch.begin();
        // draw the background
        batch.draw(bg, getCameraX() - getViewWidth() / 2, getCameraY() - getViewHeight() / 2);
        font.draw(batch, ""+score, getCameraX(), getCameraY()+150);
        // draw the bird
        falcon.render(batch);
        // draw pipes
        for (int i = 0; i < obstacles.length; i++) {
            obstacles[i].render(batch);
        }
        // end the stuff to draw
        batch.end();
    }

    @Override
    public void update(float deltaTime) {
        // update any game models
        falcon.update(deltaTime);
        // move the camera to match the bird
        moveCameraY(falcon.getY() + CAM_Y_OFFSET);

        // did bird hit the bottom of the screen
        if (falcon.getY() <= 0) {
            // end the game
            StateManager gsm = getStateManager();
            // pop off the game screen to go to menu
            gsm.pop();
            Preferences pref=Gdx.app.getPreferences("highScore");
            int highScore=pref.getInteger("highScore",0);
                if(score>highScore){
                    pref.putInteger("highScore", score);
                    pref.flush();
                }
        }

        // did the bird hit a pipe
        for (int i = 0; i < obstacles.length; i++) {
            if (obstacles[i].collides(falcon)) {
                // end the game
                StateManager gsm = getStateManager();
                // pop off the game screen to go to menu
                gsm.pop();
                Preferences pref=Gdx.app.getPreferences("highScore");
                int highScore=pref.getInteger("highScore",0);
                if(score>highScore){
                    pref.putInteger("highScore", score);
                    pref.flush();
                }
            }else if(!obstacles[i].hasPassed()&&falcon.getX()>obstacles[i].getX()+Obstacle.WIDTH){
                score++;
                obstacles[i].pass();
            }
        }

        // adjust the pipes
        for (int i = 0; i < obstacles.length; i++) {
            // has the bird passed the pipe
            if (getCameraX() - MyGdxGame.WIDTH / 4 > obstacles[i].getX() + Obstacle.WIDTH) {
                float x = obstacles[i].getX() + OBSTACLE_GAP_AMOUNT * Obstacle.WIDTH * obstacles.length;
                obstacles[i].setX(x);
            }
        }
    }

    @Override
    public void handleInput() {
        // handle any player input changes

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            falcon.moveLeft();
        }else if (Gdx.input.isKeyPressed(Input.Keys.D)){
            falcon.moveRight();
        }
    }

    @Override
    public void dispose() {
        bg.dispose();
        falcon.dispose();
        for(int i = 0; i < obstacles.length; i++){
            obstacles[i].dispose();
        }
    }

}
