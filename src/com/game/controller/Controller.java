package com.game.controller;

import com.game.model.Field;
import com.game.model.Model;
import com.game.view.View;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
    private Model model;
    private View view;

    public Field[][] getGameField(){
        return model.getGameField();
    }


    public Controller(Model model) {
        this.model = model;
        view = new View(this);
    }

    @Override
    public void keyPressed(KeyEvent event){
        if(event.getKeyCode() == KeyEvent.VK_ESCAPE)
            model.resetGameField();
        if(!model.canMove())
            view.setGameOver(true);

        if(!view.isGameOver() && !view.isGameWon()){
            switch (event.getKeyCode()){
                case KeyEvent.VK_LEFT -> model.left();
                case KeyEvent.VK_RIGHT -> model.right();
                case KeyEvent.VK_UP -> model.up();
                case KeyEvent.VK_DOWN -> model.down();
            }
        }
        if(model.getMaxField() == 2048)
            view.setGameWon(true);

        view.repaint();
    }

    public String getScore(){
        return String.valueOf(model.getScore());
    }

    public View getView() {
        return view;
    }
}
