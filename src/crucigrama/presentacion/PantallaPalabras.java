/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */

package crucigrama.presentacion;


import crucigrama.negocios.JuegoPalabras;
import crucigrama.modelo.ModeloTablaMapa;
import crucigrama.negocios.IJuegoPalabras;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class PantallaPalabras
extends JComponent {
    private String[] COLUMN_NAMES = new String[]{"Palabra", "Pista"};
    private Component tableView;
    private JMenuBar menuBar;
    private Properties wordList;
    private ModeloTablaMapa tableModel;
    private final Random rand = new Random();
    private static JFrame f;
    private PantallaJuego ps;
    private Controlador mgr;
    static /* synthetic */ Class class$java$awt$event$ActionEvent;

    public PantallaPalabras(Controlador m) throws NoSuchMethodException {
        this.mgr = m;
        this.setLayout(new BorderLayout());
        this.add(this.getMenuBar(), "North");
        this.add(this.getTable(), "Center");
    }

    void initialize(Map m) {
  
        if (m.containsKey("wordList")) {
      
            this.setWordList((Properties)m.get("wordList"));
        } else {
            this.setWordList(new Properties());
        }
    }


    private ModeloTablaMapa getTableModel() {
        if (this.tableModel == null) {
            this.tableModel = new ModeloTablaMapa(this.getWordList());
        }
        return this.tableModel;
    }

    private Properties getWordList() {
        if (this.wordList == null) {
            this.wordList = new Properties();
        }
        return this.wordList;
    }

    private void setWordList(Properties wl) {
        if (!this.getWordList().equals(wl)) {
            this.getWordList().clear();
            this.getWordList().putAll(wl);
            this.getTableModel().refresh();
        }
    }

    public void setTable(Properties w, ModeloTablaMapa t) {
        this.wordList = w;
        this.tableModel = t;
        f.repaint();
    }

    private TableColumnModel getTableColumnModel() {
        DefaultTableColumnModel tcm = new DefaultTableColumnModel();
        tcm.addColumn(this.genColumn(0, 100, "Palabra"));
        tcm.addColumn(this.genColumn(1, 400, "Pista"));
        return tcm;
    }

    private TableColumn genColumn(int modelIndex, int width, String label) {
        TableColumn tc = new TableColumn(modelIndex, width);
        tc.setHeaderValue(label);
        return tc;
    }

    private Component getTable() {
        if (this.tableView == null) {
            JTable table = new JTable(this.getTableModel(), this.getTableColumnModel());
            JScrollPane scrollPane = new JScrollPane(table);
            table.setPreferredScrollableViewportSize(new Dimension(500, 70));
            this.tableView = scrollPane;
        }
        return this.tableView;
    }

    public static void showFrame(boolean show) {
        f.setVisible(show);
        f.repaint();
    }

    private Component getMenuBar() throws NoSuchMethodException {
        if (this.menuBar == null) {
            this.menuBar = new JMenuBar();
            JMenu menu = this.addMenu(this.menuBar, "Archivo", 70, "Load and save word lists");
   
            this.addMenu(menu, "Salir", 88, null, "exit");
            menu = this.addMenu(this.menuBar, "Jugar", 67, "Generate crossword puzzles.");
   
            this.addMenu(menu, "Generar Random", 82, null, "genRandom");

        }
        return this.menuBar;
    }

    private JMenu addMenu(JMenuBar menuBar, String label, int mnemonic, String accessibleDescription) {
        JMenu menu = new JMenu(label);
        menu.setMnemonic(mnemonic);
        menu.getAccessibleContext().setAccessibleDescription(accessibleDescription);
        menuBar.add(menu);
        return menu;
    }

    public void setPuzzleScreen(PantallaJuego p) {
        this.ps = p;
    }

    public void genRandom(ActionEvent e) {
        this.genRandom();
    }

  
    public void genRandom() {
        this.genSeed(this.rand.nextLong());
    }

    public void genSeed(long seed) {
       
       // this.showPuzzle(new JuegoPalabras(this.wordList, seed));
    }

    private void showPuzzle(IJuegoPalabras p) {
        HashMap<String, IJuegoPalabras> m = new HashMap<>();
        m.put("puzzle", p);
        this.showPuzzle(m);
    }

   

    private void showPuzzle(Map m) {
        this.mgr.cambiarPantallas(m);
    }

    public void exit(ActionEvent e) {
        System.exit(0);
    }

    private void handleException(Throwable ex) {
        JOptionPane.showMessageDialog(this, ex.toString(), ex.getClass().getName(), 0);
    }

    private void addMenu(JMenu menu, String label, int mnemonic,
            String accessibleDescription, String actionCallbackName)
            throws NoSuchMethodException {
        JMenuItem menuItem = new JMenuItem(label, mnemonic);
        menuItem.getAccessibleContext().setAccessibleDescription(
                accessibleDescription != null ? accessibleDescription : label);
        Class[] arrclass = new Class[1];
        Class class_ = 
                class$java$awt$event$ActionEvent == null 
                ? (PantallaPalabras.class$java$awt$event$ActionEvent 
                = PantallaPalabras.class$("java.awt.event.ActionEvent")) 
                : class$java$awt$event$ActionEvent;
        arrclass[0] = class_;
        final Method callback = this.getClass().getMethod(actionCallbackName, arrclass);
        menuItem.addActionListener((ActionEvent e) -> {
            try {
                callback.invoke(PantallaPalabras.this, e);
            } catch (InvocationTargetException ex) {
                PantallaPalabras.this.handleException(ex.getTargetException());
            } catch (IllegalAccessException ex) {
                PantallaPalabras.this.handleException(ex);
            }
        });
        menu.add(menuItem);
    }

   

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

}

