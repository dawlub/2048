package com.game;

import com.game.controller.Controller;
import com.game.model.Model;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	    Model model = new Model();
        Controller controller = new Controller(model);

        JFrame board = new JFrame();
        board.setTitle("2048");
        board.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        board.setSize(450, 500);
        board.setResizable(false);

        board.add(controller.getView());
        board.setVisible(true);

    }
}
