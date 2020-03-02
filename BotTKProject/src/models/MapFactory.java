package models;


public class MapFactory {
    private static Icons[][] grid = new Icons[25][20];

    public static Map makeMap(int varient){
       switch (varient){
           case 1:
               map1();
               break;
           case 2:
               map2();
               break;
           case 3:
               map3();
               break;
       }
    }

    private static Icons[][] map1(){
        throw new UnsupportedOperationException("This has not yet been implemented.");
    }

    private static Icons[][] map2(){
        throw new UnsupportedOperationException("This has not yet been implemented.");
    }

    private static Icons[][] map3(){
        throw new UnsupportedOperationException("This has not yet been implemented.");
    }

}
