package com.game.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Model {
    private static final int FIELD_SIZE = 4;
    private Field[][] gameField;
    private int score;
    private int maxField;

    //Constructor which prepares playing board
    public Model() {
        resetGameField();
    }

    //Merging fields in line if it's possible
    private boolean mergeFields(Field[] fields) {
        LinkedList<Field> list = new LinkedList<>();
        boolean result = false;
        for (int i = 0; i < FIELD_SIZE; i++) {
            if (fields[i].isEmpty())
                continue;
            if (i < FIELD_SIZE - 1 && fields[i].getValue() == fields[i + 1].getValue()) {
                int newValue = fields[i].getValue() * 2;
                if (newValue > maxField) {
                    maxField = newValue;
                }
                score += newValue;
                list.addLast(new Field(newValue));
                fields[i + 1].setValue(0);
                result = true;
            } else {
                list.addLast(new Field(fields[i].getValue()));
            }
            fields[i].setValue(0);
        }
        for (int i = 0; i < list.size(); i++) {
            fields[i] = list.get(i);
        }
        return result;
    }

    //Move all empty on one side
    private boolean moveFields(Field[] fields){
        int position = 0;
        boolean result = false;
        for(int i = 0; i < FIELD_SIZE; i++){
            if(!fields[i].isEmpty()){
                if(i != position){
                    fields[position] = fields[i];
                    fields[i] = new Field();
                }
                position++;
            }
        }
        return result;
    }

    //It will call moveFields() and mergeFields() methods for each row
    public void left(){
        boolean flag = false;
        for (int i = 0; i < FIELD_SIZE; i++) {
            if(moveFields(gameField[i]) | mergeFields(gameField[i]))
                flag = true;
        }
        if(flag)
            addRandomField();
    }

    //Rotate clockwise one time
    private Field[][] rotate(Field[][] fields){
        Field[][] result = new Field[FIELD_SIZE][FIELD_SIZE];
        for(int row = 0; row < FIELD_SIZE; row++){
            for(int column = 0; column < FIELD_SIZE; column++)
                result[column][FIELD_SIZE - 1 - row] = fields[row][column];
        }
        return result;
    }

    //Rotate two times, merg and rotate to starting position
    public void right(){
        gameField = rotate(gameField);
        gameField = rotate(gameField);
        left();
        gameField = rotate(gameField);
        gameField = rotate(gameField);
    }

    // Rotate three times, merg and rotate again to go back to starting position
    public void up(){
        gameField = rotate(gameField);
        gameField = rotate(gameField);
        gameField = rotate(gameField);
        left();
        gameField = rotate(gameField);
    }

    //Rotate one time, merge and rotate three times to go back to starting position
    public void down(){
        gameField = rotate(gameField);
        left();
        gameField = rotate(gameField);
        gameField = rotate(gameField);
        gameField = rotate(gameField);
    }

    //Creating new game board with two random fields
    public void resetGameField() {
        gameField = new Field[FIELD_SIZE][FIELD_SIZE];
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                gameField[i][j] = new Field();
            }
        }
        addRandomField();
        addRandomField();
    }

    //Creting new field with random chosen value 2 or 4
    private void addRandomField() {
        List<Field> emptyFields = getEmptyFields();
        if(!emptyFields.isEmpty()){
            int index = (int) Math.random() * emptyFields.size() % emptyFields.size();
            Field emptyField = emptyFields.get(index);
            emptyField.setValue(Math.random() < 0.9 ? 2 : 4);
        }
    }

    private int getNumberOfEmptyFields(){
        return getEmptyFields().size();
    }

    private boolean isFull(){
        return getNumberOfEmptyFields() == 0;
    }

    //Checking possibility of move
    public boolean canMove(){
        if(!isFull())
            return true;
        for(int column = 0; column < FIELD_SIZE; column++)
            for (int row = 0; row < FIELD_SIZE; row++){
                Field field = gameField[column][row];
                if((column < FIELD_SIZE - 1 && field.getValue() == gameField[column + 1][row].getValue())
                        || ((row < FIELD_SIZE - 1 ) && (field.getValue() == gameField[column][row + 1].getValue())))
                    return true;
            }
        return false;
    }

    private List<Field> getEmptyFields() {
        List<Field> list = new ArrayList<>();
        for (Field[] field : gameField) {
            for (Field f : field){
                if(f.isEmpty())
                    list.add(f);
            }
        }
        return list;
    }
}