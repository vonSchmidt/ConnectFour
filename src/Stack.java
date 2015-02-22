public class Stack {
    private Pawn s [];
    private int top;

    public Stack(){
        s = new Pawn[6];
        top = 0;
    }

    public boolean isEmpty(){
        return top == 0;
    }

    public void empty(){
        while(!isEmpty())
            pop();
        top = 0;
    }

    public int push(Pawn p){
        if(isFull())
            return -1;
        s[top++] = p;
        return top-1;
    }

    public Pawn pop(){
        Pawn tmp = s[top-1];
        s[--top] = null;
        return tmp;
    }

    public Pawn peek(){
        return s[top];
    }

    public boolean isFull(){
        return top == 6;
    }

    public Pawn pawnAt(int i){
        return s[i];
    }
}
