package com.game.view;

import com.game.controller.Controller;
import com.game.model.Field;
import com.game.model.Model;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel{
    private boolean isGameOver;
    private boolean isGameWon;
    private Controller controller;
    private static final Color BACKGROUND_COLOR = new Color(0xBEAE7F);
    private static final int FIELD_SIZE = 92;
    private static final int FIELD_MARGIN = 10;
    private static final String FONT_NAME = "Calibri";

    public View(Controller controller) {
        setFocusable(true);
        this.controller = controller;
        addKeyListener(controller);
    }
    //Method for printing all fields
    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        graphics.setColor(BACKGROUND_COLOR);
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                drawField(graphics, controller.getGameField()[j][i], i, j);

        graphics.drawString("Score: " + controller.getScore(), 135, 450);

        if(isGameWon)
            JOptionPane.showMessageDialog(this, "You Won!!!");
        else if(isGameOver)
            JOptionPane.showMessageDialog(this, "You Lost");
    }
    //Method for drawing  single field and value inside it
    private void drawField(Graphics graphics, Field field, int x, int y) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Calculating coordinates for the single field
        int xCoord = x * (FIELD_MARGIN + FIELD_SIZE) + FIELD_MARGIN;
        int yCoord = y * (FIELD_MARGIN + FIELD_SIZE) + FIELD_MARGIN;
        graphics2D.setColor(field.getFieldColor());

        //In method below I'm setting coordinate of the square to fill, size, and round of the corners;
        graphics2D.fillRoundRect(xCoord, yCoord, FIELD_SIZE, FIELD_SIZE, 20, 20);

        graphics2D.setColor(field.fontColor());
        Font font = new Font(FONT_NAME, Font.BOLD, 40);
        graphics.setFont(font);

        String value = String.valueOf(field.getValue());
        FontMetrics fontMetrics = getFontMetrics(font);

        int width = fontMetrics.stringWidth(value);
        int height = -(int) fontMetrics.getLineMetrics(value, graphics2D).getBaselineOffsets()[2];

        //Setting value in field
        if( field.getValue() != 0)
            graphics.drawString(value, xCoord +(FIELD_SIZE - width) / 2, yCoord + FIELD_SIZE -(FIELD_SIZE - height) / 2-2);


    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public boolean isGameWon() {
        return isGameWon;
    }

    public void setGameWon(boolean gameWon) {
        isGameWon = gameWon;
    }
}
