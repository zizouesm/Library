package ir.maktab.model;

import java.util.Random;

public class Generate implements IdGenerator{
    public Generate() {
    }
    @Override
    public  int generate() {
        Random random = new Random();
        return random.nextInt();
    }
}
