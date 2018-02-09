package test01;

import java.util.List;

interface SuperPower{}

interface XrayVision extends SuperPower {
    void seeThroughtWalls();
}

interface SuperHearing extends SuperPower {
    void hearSUbtleNoises();
}

interface  SuperSmell extends SuperPower {
    void trackBySmell();
}

class SuperHero<POWER extends SuperPower>{
    POWER power;
    SuperHero(POWER power){this.power=power;}

    public POWER getPower() {return power; }
}

class SuperSleuth<POWER extends XrayVision> extends SuperHero<POWER> {

    public SuperSleuth(POWER power) {
        super(power);
    }

    void see(){power.seeThroughtWalls();}
}

class CaninerHero<POWER extends SuperHearing & SuperSmell> extends SuperHero<POWER> {

    public CaninerHero(POWER power) {
        super(power);
    }
    void hear(){power.hearSUbtleNoises();}
    void smell(){power.trackBySmell();}

}

class SuperHearSmell implements SuperHearing, SuperSmell {

    @Override
    public void hearSUbtleNoises() {}

    @Override
    public void trackBySmell() {}
}

class DogBoy extends CaninerHero<SuperHearSmell> {
    public DogBoy(SuperHearSmell superHearSmell) {
        super(superHearSmell);
    }
}

public class EpicBattle {

    static <POWER extends SuperHearing> void useSuperHearing(SuperHero<POWER> hero){
        hero.getPower().hearSUbtleNoises();
    }

    static <POWER extends SuperHearing & SuperSmell> void superFind(SuperHero<POWER> hero){
        hero.getPower().hearSUbtleNoises();
        hero.getPower().trackBySmell();
    }

    public static void main(String[] args){

        DogBoy dogBoy=new DogBoy(new SuperHearSmell());
        useSuperHearing(dogBoy);
        superFind(dogBoy);
        List<? extends SuperHearing> audioBoys;


    }

}
