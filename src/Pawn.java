public class Pawn {
    private Color color;

    public Pawn(Color c){
        color = c;
    }

    public Color getColor() { return color; }
    public void setColor(Color col) { color = col; }
    public void setWin() { 
        switch(color){
            case RED:
                setColor(Color.WINNING_RED);
                break;
            case YELLOW:
                setColor(Color.WINNING_YELLOW);
                break;
            default:
                break;
        }
    }

    public String toString(){
        switch(color){
            case RED:
                return "\033[0;31m⚈\033[0m";
            case YELLOW:
                return "\033[0;33m⚈\033[0m";
            case WINNING_RED:
                return "\033[5;31m⚈\033[0m";
            case WINNING_YELLOW:
                return "\033[5;33m⚈\033[0m";
            default:
                return "";
        }
    }
}
