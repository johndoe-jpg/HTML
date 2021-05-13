package pkg3itazivot;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Zivot
{
    private int[][] zeme;
    private final int[][] sousedi;
    private int delkaZivota;

    public Zivot( int size )
    {
        zeme = new int[ size ][ size ];
        sousedi = new int[ size ][ size ];
    }

    public Zivot( int size, int count )
    {
        this( size );

        NaplnZivotem( count );
    }

    private void NaplnZivotem( int count )
    {
        Random random = new Random();
        ArrayList<Point> souradnice = new ArrayList<>();
        int index;

        if ( count > zeme.length * zeme[ 0 ].length )
        {
            count = zeme.length * zeme[ 0 ].length;
        }

        if ( count > 0 )
        {
            for ( int i = 0; i < zeme[ 0 ].length; i++ )
            {
                for ( int j = 0; j < zeme.length; j++ )
                {
                    souradnice.add( new Point( i, j ) );
                }
            }
        }

        for ( int i = 0; i < count; i++ )
        {
            index = random.nextInt( souradnice.size() );
            zeme[ (int) souradnice.get( index ).getX() ][ (int) souradnice.get( index ).getY() ] = 1;
            souradnice.remove( index );
        }
    }

    private void VypoctiSousedy()
    {
        for ( int i = 0; i < zeme[ 0 ].length; i++ )
        {
            for ( int j = 0; j < zeme.length; j++ )
            {
                sousedi[ i ][ j ] = VypoctiSousedy( i, j );
            }
        }
    }

    private int VypoctiSousedy( int x, int y )
    {
        int soused = 0;

        for ( int i = -1; i <= 1; i++ )
        {
            for ( int j = -1; j <= 1; j++ )
            {
                if ( i == 0 && j == 0 )
                {
                    continue;
                }

                try
                {
                    soused += zeme[ x + i ][ y + j ];
                }
                catch ( Exception exception )
                {
                    // Do nothing
                }
            }
        }

        return soused;
    }

    private void DalsiDen()
    {
        for ( int i = 0; i < zeme[ 0 ].length; i++ )
        {
            for ( int j = 0; j < zeme.length; j++ )
            {
                if ( zeme[ i ][ j ] == 1 && ( sousedi[ i ][ j ] < 2 || sousedi[ i ][ j ] > 3 ) )
                {
                    zeme[ i ][ j ] = 0;
                }
                else if ( sousedi[ i ][ j ] == 3 )
                {
                    zeme[ i ][ j ] = 1;
                }
            }
        }
    }

    public void Zij()
    {
        VypoctiSousedy();
        DalsiDen();
        delkaZivota++;
    }

    public boolean JeNekdoNaZivu()
    {
        for ( int i = 0; i < zeme[ 0 ].length; i++ )
        {
            for ( int j = 0; j < zeme.length; j++ )
            {
                if ( zeme[ i ][ j ] == 1 )
                {
                    return true;
                }
            }
        }

        return false;
    }

    private void VypisPole( int[][] pole )
    {
        for ( int i = 0; i < pole.length; i++ )
        {
            for ( int j = 0; j < pole[ i ].length; j++ )
            {
                System.out.print( pole[ i ][ j ] );
            }
            System.out.println( "" );
        }
        System.out.println( "" );
    }

    public int[][] getZeme()
    {
        return zeme;
    }

    public void setZeme( int[][] zeme )
    {
        this.zeme = zeme;
        VypoctiSousedy();
        delkaZivota = 0;
    }

    public int getDelkaZivota()
    {
        return delkaZivota;
    }
}
