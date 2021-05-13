package pkg3itazivot;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Panel extends JPanel
{
    private final Zivot zivot;

    public Panel( Zivot zivot )
    {
        this.setSize( 560, 560 );
        this.setVisible( true );
        this.setBackground( Color.BLACK );
        this.zivot = zivot;
    }

    @Override
    public void paint( Graphics g )
    {
        super.paint( g );

        int[][] zeme = zivot.getZeme();

        for ( int i = 0; i < zeme[ 0 ].length; i++ )
        {
            for ( int j = 0; j < zeme.length; j++ )
            {
                if ( zeme[ i ][ j ] == 1 )
                {
                    g.setColor( Color.WHITE );
                }
                else
                {
                    g.setColor( Color.BLACK );
                }
                g.fillRect( j * 20, i * 20, 20, 20 );
            }
        }
    }
}
