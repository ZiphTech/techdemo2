package mygame;

import com.jme3.scene.Spatial;
import java.util.LinkedList;

public class Board
{
    private int[] piecePosition = new int[2];
    
    private String[][] board;
    
    private final int X_SIZE = 8;
    private final int Y_SIZE = 8;

    public void initBoard()
    {
        board = new String[X_SIZE][Y_SIZE];
        for(int i = 0; i < X_SIZE; i++)
        {
            for (int j = 0; j < Y_SIZE; j++)
            {
                board[i][j] = "*";
            }
        }
    }
    
    public void drawBoard()
    {
        for (int i = 0; i < X_SIZE; i++)
        {
            for (int j = 0; j < Y_SIZE; j++)
            {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
    
    
    String prevPos;
    int[] prevCoord = new int[2];
    
    public String selectedPosition(int[] position)
    {
        String selectedPiece;
        
        int x = position[0];
        int y = position[1];
        
        if (prevPos != null)
        {
            board[prevCoord[0]][prevCoord[1]] = prevPos;
        }
        
        prevPos = board[y][x];
//        
//        prevCoord[0] = y;
//        prevCoord[1] = x;
//        
//        piecePosition[0] = x;
//        piecePosition[1] = y;
        
        selectedPiece = board[y][x];
//        
//        StringBuilder sb = new StringBuilder(board[y][x]);
//        sb.insert(0, "[");
//        sb.append("]");
//        
//        System.out.println("SB TO STRING: " + sb.toString());
//        
//        System.out.println("CURRENT BOARD DATA AT " + y + " " + x + ": " + board[prevCoord[0]][prevCoord[1]].toString());
//        
//        if (sb.toString().equals("[" + selectedPiece + "]"))
//        {
//            board[y][x] = selectedPiece;
//        }
//        else
//        {
////            sb.insert(0, "[");
////            sb.append("]");
//            board[y][x] = sb.toString();
//        }
//        
////        board[y][x] = sb.toString();
//        
//        drawBoard();
//        
        System.out.println("Selected Piece: " + selectedPiece);
        return selectedPiece;
    }
    
    public void setPlayerPieces(LinkedList<Spatial> piece)
    {
        for (int i = 0; i < piece.size(); i++)
        {
            for (int p = 0; p < 8; p++)
            {
                board[1][p] = "p1";
            }
            for (int p = 0; p < 8; p++)
            {
                board[6][p] = "p2";
            }
            board[0][0] = "r1";
            board[0][7] = "r1";
            board[7][0] = "r2";
            board[7][7] = "r2";
            board[0][1] = "k1";
            board[0][6] = "k1";
            board[7][1] = "k2";
            board[7][6] = "k2";
            board[0][2] = "b1";
            board[0][5] = "b1";
            board[7][2] = "b2";
            board[7][5] = "b2";
            board[0][3] = "q1";
            board[7][3] = "q2";
            board[0][4] = "K1";
            board[7][4] = "K2";
        }
    }
}
