public class Menu {
    public static void main(String args[]){
        System.out.println("\n\t .:: Welcome to Connect 4 ::.");
        System.out.println("\n\t==============================");
        ///*
        System.out.print("\n\n\t  Press <Enter> to continue.");
        try{
            System.in.read();
        } catch(Exception e) {}
        //*/

        Game game = new Game();

        while(true){
            //game.clear();
            game.run();
            ///*
            System.out.print("\n\n\t  Press <Enter> to continue.");
            try{
                System.in.read();
            } catch(Exception e) {}
            game.reset();
            //*/
        }
    }
}
