package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int row = 4;
        int col = 4;
        boolean[][] bool_board = new boolean[row][col]; //true if piece attacking
        boolean[][] pos_board = new boolean[row][col]; //true if piece placed

        for(int i =0; i<row; i++) {
            for (int j = 0; j < col; j++) {
                bool_board[i][j] = false;
                pos_board[i][j] = false;
            }
        }


        if(!CanPlace(bool_board,pos_board,0,0)){
            System.out.println("No solution");
        }
    }
    public static boolean[][] CopyArray(boolean[][] result,boolean[][] copying, int n, int m){
        for(int i =0; i<n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = copying[i][j];
                result[i][j] = copying[i][j];
            }
        }
        return result;
    }

    public static boolean CanPlace(boolean[][] bool_board, boolean[][] pos_board, int row, int col){
        if(row==bool_board.length){
            System.out.println("Solution: "+Arrays.deepToString(pos_board));
            return true;
        }
        else if(bool_board[row][col]==false) {
            System.out.println("Placed at ["+row+"]["+col+"]");
            boolean [][] newBoolBoard = new boolean[bool_board.length][bool_board[0].length];
            newBoolBoard = CopyArray(newBoolBoard,bool_board,bool_board.length,bool_board[0].length);

            UpdateBoard(newBoolBoard,row,col);

            boolean [][] newPosBoard = new boolean[pos_board.length][pos_board[0].length];
            newPosBoard = CopyArray(newPosBoard,pos_board,pos_board.length,pos_board[0].length);

            newPosBoard[row][col] = true;

            boolean canPlaceOnNextRow = CanPlace(newBoolBoard, newPosBoard, row + 1, 0);
            if(canPlaceOnNextRow){
                return true;
            }else if(col+1<bool_board.length){
                System.out.println("Removed at ["+row+"]["+col+"]");
                return CanPlace(bool_board, pos_board, row, col + 1);
            }
        }else if(col+1<bool_board.length){
            System.out.println("Test at ["+row+"]["+col+"]");
            return CanPlace(bool_board, pos_board, row, col + 1);
        }else{
            System.out.println("Test at ["+row+"]["+col+"]");
            return false;
        }
        return false;
    }

    public static boolean[][] UpdateBoard(boolean[][] bool_board, int x, int y){
        int tempx = x;
        int tempy = y;
        bool_board[tempx][tempy] = true;
        //right up
        while(tempx+1<bool_board.length && tempy+1<bool_board[0].length){
            tempx++;
            tempy++;
            bool_board[tempx][tempy] = true;
        }
        tempx = x;
        tempy = y;
        //left up
        while(tempx-1>=0 && tempy+1<bool_board[0].length){
            tempx--;
            tempy++;
            bool_board[tempx][tempy] = true;
        }
        tempx = x;
        tempy = y;
        //left down
        while(tempx-1>=0 && tempy-1>=0){
            tempx--;
            tempy--;
            bool_board[tempx][tempy] = true;
        }
        tempx = x;
        tempy = y;
        //right down
        while(tempx+1<bool_board.length && tempy-1>=0){
            tempx++;
            tempy--;
            bool_board[tempx][tempy] = true;
        }
        //-------------------------
        tempx = x;
        tempy = y;
        //up
        while(tempy+1<bool_board[0].length){
            tempy++;
            bool_board[tempx][tempy] = true;
        }
        tempx = x;
        tempy = y;
        //right
        while(tempx+1<bool_board.length){
            tempx++;
            bool_board[tempx][tempy] = true;
        }
        tempx = x;
        tempy = y;
        //down
        while(tempy-1>=0){
            tempy--;
            bool_board[tempx][tempy] = true;
        }
        tempx = x;
        tempy = y;
        //left
        while(tempx-1>=0){
            tempx--;
            bool_board[tempx][tempy] = true;
        }
        //diagonal
        /*
        up right
        down right
        up left
        down left
         */
        //straight lines
        /*
        up
        right
        down
        left
         */
        return bool_board;
    }
}
