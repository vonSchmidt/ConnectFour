public class Player {
    private static int ID_ref = 0;
    private Color color;

    public Player(){
        if(ID_ref == 0)
            color = Color.RED;
        else
            color = Color.YELLOW;
        ID_ref++;
    }

    public static void resetREF(){ ID_ref = 0; }
        
    public void makeTurn(Board b){
        b.makeTurn(color);
    }

}
