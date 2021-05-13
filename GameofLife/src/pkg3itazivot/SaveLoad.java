package pkg3itazivot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveLoad
{
    private BufferedReader br;
    private BufferedWriter bw;
    private final String fileName;

    
    public SaveLoad( String fileName )
    {
        this.fileName = fileName;
    }

   
    public void Save( int[][] zeme )
    {
        try
        {
            bw = new BufferedWriter( new FileWriter( ".\\" + fileName ) );

            for ( int i = 0; i < zeme[ 0 ].length; i++ )
            {
                for ( int j = 0; j < zeme.length; j++ )
                {
                    bw.write( String.valueOf( zeme[ i ][ j ] ) );
                }
                bw.newLine();
            }

            bw.flush();
        }
        catch ( IOException exception )
        {
            // Do nothing.
        }
    }

   
    public int[][] Load()
    {
        ArrayList< String> radky = new ArrayList<>();
        String radek;

        try
        {
            br = new BufferedReader( new FileReader( ".\\" + fileName ) );

            while ( ( radek = br.readLine() ) != null )
            {
                radky.add( radek );
            }
        }
        catch ( IOException exception )
        {
            
            return new int[ 28 ][ 28 ];
        }

        int[][] zeme = new int[ radky.get( 0 ).length() ][ radky.size() ];

        try
        {
            for ( int i = 0; i < zeme[ 0 ].length; i++ )
            {
                for ( int j = 0; j < zeme.length; j++ )
                {
                    zeme[ i ][ j ] = radky.get( i ).charAt( j ) == '1' ? 1 : 0;
                }
            }
        }
        catch ( Exception exception )
        {
            
            return new int[ 28 ][ 28 ];
        }

        return zeme;
    }

    public boolean DoesFileExist()
    {
        try
        {
            br = new BufferedReader( new FileReader( ".\\" + fileName ) );
        }
        catch ( IOException exception )
        {
            return false;
        }
        return true;
    }
}
