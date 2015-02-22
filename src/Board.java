import java.util.Scanner;

public class Board {
    private Stack board [];
    private Game game;

    public Board(Game game){
        board = new Stack [7];
        for(int i = 0; i < 7; i++)
            board[i] = new Stack();
        this.game = game;
    }

    public Pawn getPawn(int stack, int pos){
        return board[stack].pawnAt(pos);
    }

    public void empty(){
        for(Stack s:board)
            s.empty();
    }

    public boolean isFull(){
        boolean isFull = true;
        for(Stack s:board)
            if(!s.isFull())
                isFull = false;
        return isFull;
    }

    public void makeTurn(Color c){
        Scanner q = new Scanner(System.in);
        String a;
        int x = 0;
        do{
            System.out.print(" > ");
            a = q.next();
            try{
                x = Integer.parseInt(a);
            } catch(Exception e){
                System.out.println("  [Error] Non-Numeric Characters will not be allowed! [Error]");
            }
        } while( x < 1 || x > 7 || board[x-1].isFull() );

        int pos = board[x-1].push(new Pawn(c));
        game.incrementNoMoves();

        game.checkAndDeclareWinner(x, pos, c);
    }

    @Override
    public String toString(){
        String S = "\n";
        S += "\t 1  2  3  4  5  6  7 \n\t";
        for(int i = 5; i >= 0; i--){
            for(Stack s : board){
                S += "[";
                if(s.pawnAt(i) != null)
                    S += s.pawnAt(i);
                else
                    S += " ";
                S += "]";
            }
            S += "\n\t";
        }
        S += "=====================\n";

        return S;
    }
}
