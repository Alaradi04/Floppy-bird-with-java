
public class Player {
    int x;
    int y;
    int width;
    int height;

    public Player(){
        x = (int)(Main.width * 0.1);
        y = Main.height/2 - 50;
        width = 50;
        height = 45;
    }


    boolean collosionTop(Pipe pipe){
        
        if((this.x + this.width) >= pipe.x && (this.x + this.width) <= pipe.x + pipe.width  && this.y <=  pipe.height){
            return true;
        }

        return false;
    }

      boolean collosionBott(Pipe pipe){
        
        if((this.x + this.width) >= pipe.x && (this.x + this.width) <= pipe.x + pipe.width  && (this.y + this.height) >= pipe.y){
            return true;
        }

        return false;
    }
}
