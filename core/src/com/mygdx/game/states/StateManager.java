/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Stack;

/**
 *
 * @author lamon
 */
public class StateManager {
    private Stack<State> states;
    
    public StateManager(){
        states = new Stack<State>();
    }
    
    public void push(State s){
        states.push(s);
    }
    
    public void pop(){
        State s = states.pop();
        s.dispose();
    }
    
    public void set(State s){
        pop();
        push(s);
    }
    
    public void update(float deltaTime){
        states.peek().update(deltaTime);
    }
    
    public void render(SpriteBatch batch){
        states.peek().render(batch);
    }

    public void handleInput() {
        states.peek().handleInput();
    }
    
}
