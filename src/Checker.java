public class Checker {

    private Game game;
    private Board board;

    public Checker(Board b, Game g){
        game = g;
        board = b;
    }

    public boolean check(int stack, int pos){

         // Impossible to get 4 of a kind in less than 7 moves
        if(game.getTotalNoMoves() < 7) return false;

        if(checkHorizontal(stack, pos)) return true;

        if(checkVertical(stack, pos)) return true;

        if(checkFirstDiagonal(stack, pos)) return true;

        if(checkSecondDiagonal(stack, pos)) return true;

        return false;

    }

    private boolean checkHorizontal(int stack, int pos){
        
        Pawn array [];

        int first, last;

        if(stack <= 3){
            first = 0;
            last = stack + 3;
        } else {
            first = stack - 3;
            last = 6;
        }
        array = new Pawn [last - first + 1];

        int i = first;
        for(int j=0; j<array.length; j++){
            array[j] = board.getPawn(i++, pos);
        }
/*
        for(Pawn p : array)
            System.out.print(p+" ");
        System.out.println();
*/
        return consecutiveFourIn(array);
    }

    private boolean checkVertical(int stack, int pos){

        Pawn array [];

        int first, last;

        if(pos < 3){
            first = 0;
            last = pos + 3;
        } else {
            first = pos - 3;
            last = 5;
        }
        array = new Pawn [last - first + 1];

        int i = first;
        for(int j=0; j<array.length; j++){
            array[j] = board.getPawn(stack, i++);
        }
        return consecutiveFourIn(array);
    }

    private boolean checkFirstDiagonal(int stack, int pos){

        if(
            (
                (stack == 0 && pos >= 3) ||
                (stack == 1 && pos >= 4) ||
                (stack == 2 && pos == 5) 
            )
            ||
            (
                (stack == 4 && pos == 0) ||
                (stack == 5 && pos <= 1) ||
                (stack == 6 && pos <= 2) 
            )
        )
            return false;

        Pawn array [];

        int firstDs, firstDp, lastDs, lastDp;

        int i = stack, j = pos;

        while(i>0 && j>0) { i--; j--;}
        firstDs = i; firstDp = j;
        i = stack; j = pos;
        while(i<6 && j<5) { i++; j++;}
        lastDs = i; lastDp = j;
        array = new Pawn [lastDp - firstDp + 1];
        
        int k = 0;
        for(i = firstDs, j = firstDp; i <= lastDs && j <= lastDp; i++, j++){
            array[k++] = board.getPawn(i, j);
        }

        return consecutiveFourIn(array);
    }

    private boolean checkSecondDiagonal(int stack, int pos){

        if(
            (
                (stack == 0 && pos <= 2) ||
                (stack == 1 && pos <= 1) ||
                (stack == 2 && pos == 0) 
            )
            ||
            (
                (stack == 4 && pos == 5) ||
                (stack == 5 && pos >= 4) ||
                (stack == 6 && pos >= 3) 
            )
        )
            return false;

        Pawn array [];

        int firstDs, firstDp, lastDs, lastDp;

        int i = stack, j = pos;

        while(i<6 && j>0) { i++; j--;}
        firstDs = i; firstDp = j;
        i = stack; j = pos;
        while(i>0 && j<5) { i--; j++;}
        lastDs = i; lastDp = j;
        array = new Pawn [lastDp - firstDp + 1];
        
        int k = 0;
        for(i = firstDs, j = firstDp; i >= lastDs && j <= lastDp; i--, j++){
            array[k++] = board.getPawn(i, j);
        }

        return consecutiveFourIn(array);

    }

    private boolean consecutiveFourIn(Pawn array []){
        int j;
        Color c;
        /*
        for(Pawn p:array)
            System.out.print(p+" ");
        System.out.println();
        */
OUTER:  for(int i=0; i <= array.length-4; i++){
            if(array[i] == null)
                continue OUTER;
            c = array[i].getColor();
            for(j = i+1; j < i+4; j++)
                if(array[j] == null || array[j].getColor() != c)
                    continue OUTER;
            for(j = i; j < i+4; j++)
                array[j].setWin();
            return true; // in case it didn't continue OUTER at some point
        }
        return false; // in case it kept continue-ing all the way
    }
}
