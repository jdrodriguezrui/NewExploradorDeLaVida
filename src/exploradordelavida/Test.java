package exploradordelavida;

import java.util.TreeMap;

public class Test
{

    public static void main ( String [] args )
    {
        TreeMap < Position , Integer > myMap = new TreeMap < Position , Integer > ();

        Position p1 = new Position ( 1 , 2 );
        Position p2 = new Position ( 3 , 4 );
        Position p3 = new Position ( 3 , 5 );

        myMap.put ( p1 , 1 );
        myMap.put ( p2 , 2 );

        System.out.println ( myMap.get(p3) == null );
    }

}
