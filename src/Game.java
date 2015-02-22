public class Game {

    private Board board;
    private Checker checker;
    private Player player0;
    private Player player1;
    private boolean running;
    private String status = " * Status *";
    private int TotalNoMoves;

    public Game(){
        running = false;
        player0 = new Player();
        player1 = new Player();
        board = new Board(this);
        checker = new Checker(board, this);
        TotalNoMoves = 0;
    }

    public int getTotalNoMoves(){
        return TotalNoMoves;
    }

    public void incrementNoMoves(){
        TotalNoMoves++;
    }

    public void reset(){
        status = " * Status *";
        TotalNoMoves = 0;
        running = false;
        board.empty();
        Player.resetREF();
        player0 = new Player();
        player1 = new Player();
        checker = new Checker(board, this);
    }

    public void stopRunning(){ running = false; }
    public void startRunning() { running = true; }
    public void checkFullBoard(){
        if(board.isFull())
            status = ".:: It's a DRAW ::.";
    }

    public void run(){
        running = true;
        while(running && !board.isFull()){
            clear();
            status = "\033[0;31m.:: Player One's Turn (RED) ::.\033[0m";
            checkFullBoard();
            System.out.println("\n\n\t"+status);
            System.out.println(board);
            if(board.isFull()) break;
            player0.makeTurn(board);
            clear();
            checkFullBoard();
            System.out.println("\n\n\t"+status);
            System.out.println(board);
            if(board.isFull()) break;
            if(!running) break;
            clear();
            status = "\033[0;33m.:: Player Two's Turn (YELLOW) ::.\033[0m";
            checkFullBoard();
            System.out.println("\n\n\t"+status);
            System.out.println(board);
            if(board.isFull()) break;
            player1.makeTurn(board);
            clear();
            checkFullBoard();
            System.out.println("\n\n\t"+status);
            System.out.println(board);
            if(board.isFull()) break;
            if(!running) break;
        }
        running = false;
    } 

    private boolean checkState(int stack, int pos){
        return checker.check(stack, pos);
    }


    public void clear(){
        System.out.print("\033[H\033[2J");
    }


    public void checkAndDeclareWinner(int x, int pos, Color c){
        if(checkState(x-1, pos)){
            switch(c){
                case RED:
                    status = ".:: \033[0;31mPlayer 1 wins ::.\033[0m ";
                    break;
                case YELLOW:
                    status = ".:: \033[0;33mPlayer 2 wins ::.\033[0m ";
                    break;
            }

            stopRunning();
        }
    }
}
