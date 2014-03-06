package mygame;

import com.jme3.scene.Spatial;
import java.util.LinkedList;

public class ChessLogic implements BoardData
{
    private String[][] board;
    private String selectedPiece;
    
    private int[] piecePosition = new int[2];
    
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
    
    public void selectorPosition(int[] position)
    {
        int x = position[0];
        int y = position[1];
        
        if (prevPos != null)
        {
            board[prevCoord[0]][prevCoord[1]] = prevPos;
        }
        
        prevPos = board[y][x];
        
        prevCoord[0] = y;
        prevCoord[1] = x;
        
        piecePosition[0] = x;
        piecePosition[1] = y;
        
        selectedPiece = board[y][x];
        
        StringBuilder sb = new StringBuilder(board[y][x]);
        sb.insert(0, "[");
        sb.append("]");
        
        board[y][x] = sb.toString();
        
        drawBoard();
        
        System.out.println("Selected Piece: " + selectedPiece);
    }
    
    
    /*
     * Work on logical piece moves.
     */
//    public void pawnMove()
//    {
//        // Activates if selectedPiece = "p"
//        
//        int x = piecePosition[0];
//        int y = piecePosition[1];
//        
//        int[] validPosition = new int[2];
//        
//        System.out.println("Valid moves");
//        validPosition[0] = y + 1;
//        
//        System.out.println()
//        
//    }
    
    
    public void setPiecePosition(LinkedList<Spatial> piece, String num)
    {
        for (int i = 0; i < piece.size(); i++)
        {
            if (piece.get(i).getName().equals("pawn") && num.equals("1"))
            {
                for (int p = 0; p < 8; p++)
                {
                    board[1][p] = "p";
                }
            }
            else
            {
                for (int p = 0; p < 8; p++)
                {
                    board[6][p] = "p";
                }
            }
            
            if (piece.get(i).getName().equals("rook") && num.equals("1"))
            {
                board[0][0] = "r";
                board[0][7] = "r";
            }
            else
            {
                board[7][0] = "r";
                board[7][7] = "r";
            }
            
            if (piece.get(i).getName().equals("knight") && num.equals("1"))
            {
                board[0][1] = "k";
                board[0][6] = "k";
            }
            else
            {
                board[7][1] = "k";
                board[7][6] = "k";
            }
            
            if (piece.get(i).getName().equals("bishop") && num.equals("1"))
            {
                board[0][2] = "b";
                board[0][5] = "b";
            }
            else
            {
                board[7][2] = "b";
                board[7][5] = "b";
            }
            
            if (piece.get(i).getName().equals("queen") && num.equals("1"))
            {
                board[0][3] = "q";
            }
            else
            {
                board[7][3] = "q";
            }
            
            if (piece.get(i).getName().equals("king") && num.equals("1"))
            {
                board[0][4] = "K";
            }
            else
            {
                board[7][4] = "K";
            }
        }
    }
}
