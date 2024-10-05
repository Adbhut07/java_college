import java.util.*;

class Circle_2201713 {
    private double x;
    private double y;
    private double radius;

    double getX(){
        return x;
    }
    double getY(){
        return y;
    }
    double getRadius(){
        return radius;
    }

    void newCenter(double x, double y){
        this.x = x;
        this.y = y;
    }
    void newRadius(double r){
        this.radius = r;
    }

    double area(){
        double areaOfCircle = (Math.PI)*radius*radius;
        return areaOfCircle;
    }
    double circumference(){
        double circumferenceOfCircle = 2*(Math.PI)*radius;
        return circumferenceOfCircle;
    }

    public boolean overlapsWith(Circle_2201713 c2){
        double x1 = this.x;
        double y1 = this.y;
        double x2 = c2.x;
        double y2 = c2.y;
        double radius1 = this.radius;
        double radius2 = c2.radius;

        // Euclidean distance - distance between the centers of the circles
        double distance = Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));

        return distance < radius1+radius2;
    }

    public boolean completelyOverlapsWith(Circle_2201713 c2){
        double x1 = this.x;
        double y1 = this.y;
        double x2 = c2.x;
        double y2 = c2.y;
        double radius1 = this.radius;
        double radius2 = c2.radius;

        double smallerRadius = Math.min(radius1,radius2);
        double biggerRadius = Math.max(radius1,radius2);

        double distance = Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));

        return distance + smallerRadius < biggerRadius;
    }
}


public class eem514_2201713_MyCircles {
    public static void main(String[] args) {
        Circle_2201713 c1 = new Circle_2201713();
        Circle_2201713 c2 = new Circle_2201713();

        Scanner scanner = new Scanner(System.in);
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        double r1 = scanner.nextDouble();
        c1.newCenter(x1,y1);
        c1.newRadius(r1);
        System.out.println(c1.area());
        System.out.println(c1.circumference());

        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();
        double r2 = scanner.nextDouble();
        c2.newCenter(x2,y2);
        c2.newRadius(r2);
        System.out.println(c2.area());
        System.out.println(c2.circumference());

        System.out.println(c1.overlapsWith(c2));
    }
}