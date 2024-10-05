public class Main {
//    public static void main(String[] args) {
//
//        System.out.println("Hello world! \n");
//        String s = new String("Hello");
//        String t = new String("Heleo");
//
////        String s = "Hello";
////        String t = "Heleo";
//
//        System.out.println(s==t);
//        System.out.println(s.equals(t));
//
//        s=t;
//        System.out.println(s);
//    }
    public static void main(String args[]){
        StringBuilder s1 = new StringBuilder("Hello");
        StringBuilder s2 = new StringBuilder("World");
        StringBuilder s = s1.append(s2);
        System.out.println(s.toString());
    }
}