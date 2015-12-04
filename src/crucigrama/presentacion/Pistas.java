/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */

package crucigrama.presentacion;



import crucigrama.negocios.IJuegoPalabras;
import crucigrama.negocios.Direccion;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class Pistas
extends JComponent {
    private IJuegoPalabras puzzle;
    private static final String EOL = System.getProperty("line.separator");
    

    public Pistas(IJuegoPalabras p) {
        
        JTextPane testPane = new JTextPane();
        this.puzzle = p;
      
        Iterator directions = Direccion.iterator();
        Document doc = testPane.getDocument();
        ArrayList<String[]> lines = new ArrayList<>();
        while (directions.hasNext()) {
            Direccion d = (Direccion)directions.next();
            lines.add(this.appendString(doc, testPane, d.getNombre() + EOL, "bold"));
            Iterator clues = this.puzzle.getPistas(d).entrySet().iterator();
            while (clues.hasNext()) {
                Map.Entry clue = (Map.Entry) clues.next();
                lines.add(this.appendString(doc, testPane, clue.getKey() + "  " + clue.getValue() + EOL, "regular"));
            }
            if (!directions.hasNext()) continue;
            lines.add(this.appendString(doc, testPane, EOL, "regular"));
        }
        Dimension referenceSize = testPane.getPreferredSize();
        int columns = (int)Math.sqrt(1.618033988749895 * (double)referenceSize.height / (double)referenceSize.width);
        double linesPerColumn = (double)lines.size() / (double)columns;
        this.setLayout(new GridLayout(1, columns));
        int i = 0;
        while (i < columns) {
            JTextPane tp = new JTextPane();
     
            Document displayDoc = tp.getDocument();
            int startLine = (int)((double)i * linesPerColumn);
            int endLine = (int)((double)(i + 1) * linesPerColumn);
            int j = startLine;
            while (j < endLine) {
                this.appendString(displayDoc, tp, (String[])lines.get(j));
                ++j;
            }
            tp.setEditable(false);
            this.add(tp);
            ++i;
        }
    }

    private String[] appendString(Document d, JTextPane pane, String[] stringAndStyle) {
        return this.appendString(d, pane, stringAndStyle[0], stringAndStyle[1]);
    }

    private String[] appendString(Document d, JTextPane pane, String s, String style) {
        try {
            d.insertString(d.getLength(), s, pane.getStyle(style));
        }
        catch (BadLocationException e) {
            throw new RuntimeException("Modified document.");
        }
        return new String[]{s, style};
    }

    public IJuegoPalabras getPuzzle() {
        return this.puzzle;
    }

    

   
}

