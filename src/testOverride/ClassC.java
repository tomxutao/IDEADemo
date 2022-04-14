package testOverride;

public class ClassC extends ClassB {

    @Override
    void getBig() {
    }

    public static void main(String[] args) {
        new ClassC().check();
    }
}
