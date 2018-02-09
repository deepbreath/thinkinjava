package test01;

public class Holder<T> {
    private T value;

    public Holder(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
    public boolean equals(Object object){
        return value.equals(object);
    }

    public static void main(String[] args){

        Holder<Apple> Apple=new Holder<Apple>(new Apple());

        Apple d=Apple.getValue();

        Apple.setValue(d);

        //Holder<Fruit> Fruit=Apple;//Cannot upcast

        Holder<? extends Fruit> fruit=Apple;

        Fruit p=fruit.getValue();

        //Renturns 'Object'
        d=(Apple)fruit.getValue();

        try{

            Orange c=(Orange)fruit.getValue();

        }catch (Exception e){

            System.out.println(e);
        }

        System.out.println(fruit.equals(d));


    }

}
