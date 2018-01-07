package net.mindview.util;//: generics/TupleTest.java

public class TupleTest {
    public static TwoTuple<String, Integer> f() {
        // Autoboxing converts the int to Integer:
        return new TwoTuple<String, Integer>("hi", 47);
    }

    public static ThreeTuple<Amphibian, String, Integer> g() {
        return new ThreeTuple<Amphibian, String, Integer>(
                new Amphibian(), "hi", 47);
    }

    public static FourTuple<Vehicle, Amphibian, String, Integer> h() {
        return
                new FourTuple<Vehicle, Amphibian, String, Integer>(
                        new Vehicle(), new Amphibian(), "hi", 47);
    }

    public static FiveTuple<Vehicle, Amphibian, String, Integer, Double> k() {
        return new
                FiveTuple<Vehicle, Amphibian, String, Integer, Double>(
                new Vehicle(), new Amphibian(), "hi", 47, 11.1);
    }

    public static void main(String[] args) {
        TwoTuple<String, Integer> ttsi = f();
        System.out.println(ttsi);
        // ttsi.first = "there"; // Compile error: final
        System.out.println(g());
        System.out.println(h());
        System.out.println(k());
    }
} /* Output: (80% match)
(hi, 47)
(Amphibian@1f6a7b9, hi, 47)
(Vehicle@35ce36, Amphibian@757aef, hi, 47)
(Vehicle@9cab16, Amphibian@1a46e30, hi, 47, 11.1)
*///:~
