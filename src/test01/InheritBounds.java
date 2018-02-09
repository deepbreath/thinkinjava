package test01;

class HoldItem<T>{
    T item;
    HoldItem(T item){
        this.item=item;
    }

    T getItem(){return item;}
}

class Color2<T extends HashColor> extends HoldItem<T> {

    public Color2(T item) {
        super(item);
    }

    java.awt.Color color(){return item.getColor();}

}

class ColoredDimension2<T extends Dimension & HashColor> extends Color2<T> {

    public ColoredDimension2(T item) {
        super(item);
    }

    int getX(){return item.x;}

    int getY(){return item.y;}

    int getZ(){return item.z;}
}

class Solid2<T extends Dimension & HashColor & Weight> extends ColoredDimension2<T> {
    public Solid2(T item) {
        super(item);
    }

    int weight(){return item.weight();}

}

public class InheritBounds {

    public static void main(String[] args){

    Solid2<Bounded> solid2=new Solid2<>(new Bounded());

    solid2.color();
    solid2.getX();
    solid2.getY();
    solid2.getZ();
    solid2.weight();
    }
}
