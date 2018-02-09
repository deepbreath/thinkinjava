package test01;

import java.awt.*;

interface HashColor{Color getColor();}

class Colored<T extends HashColor>{

    T item;
    Colored (T item){
        this.item=item;
    }
    Color color(){return item.getColor();}

}

class Dimension{
    public int x,y,z;
}

class ColoredDimension<T extends Dimension & HashColor>{
    T item;
    ColoredDimension (T item){
        this.item=item;
    }

    T getItem(){
        return item;
    }

    Color color(){return item.getColor();}
    int getX(){return item.x;}
    int getY(){return item.y;}
    int getZ(){return item.z;}

}

interface Weight{int weight();}

class Solid<T extends Dimension & HashColor & Weight>{

    T item;
    Solid (T item){
        this.item=item;
    }

    T getItem(){return item;}

    Color color(){return item.getColor();}

    int getX(){return item.x;}
    int getY(){return item.y;}
    int getZ(){return item.z;}

    int weighet(){return item.weight();}

}

class Bounded extends Dimension implements HashColor, Weight {

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public int weight() {
        return 0;
    }
}


public class BasicBounds {

    public static void main(String[] args){
        Solid<Bounded> solid=new Solid<>(new Bounded());
        solid.color();
        solid.getX();
        solid.getY();
    }
}
