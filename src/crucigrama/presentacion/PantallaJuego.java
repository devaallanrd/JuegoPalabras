/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */
package crucigrama.presentacion;

import crucigrama.negocios.IJuegoPalabras;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class PantallaJuego
extends JComponent
 {
    private JMenuBar menuBar;
    private DesplegarJuego puzzleDisplay;
    private IJuegoPalabras puzzle;


    private Controlador mgr;
    private Map statefulMenuItemList = new HashMap();
    static /* synthetic */ Class class$java$awt$event$ItemEvent;
    static /* synthetic */ Class class$java$awt$event$ActionEvent;

    public PantallaJuego(Controlador mgr) throws NoSuchMethodException {
        this.mgr = mgr;
        this.setLayout(new BorderLayout());
        this.add(this.getMenuBar(), "North");
    }

    private PantallaJuego(IJuegoPalabras p) throws NoSuchMethodException {
        this((Controlador)null);
        this.setPuzzle(p);
    }

    public void initialize(Map m) {
        if (m.containsKey("puzzle")) {
            IJuegoPalabras p = (IJuegoPalabras)m.get("puzzle");
           
            this.setPuzzle(p);
            this.getStatefulMenuItem("Mostrar Todo").setSelected(false);
            this.setKeyShown(false);
            this.getStatefulMenuItem("Mostrar Letras").setSelected(true);
            this.setCluesShown(true);
        } else {
           // this.load((File)m.get("file"));
        }
    }

    private JCheckBoxMenuItem getStatefulMenuItem(String name) {
        return (JCheckBoxMenuItem)this.statefulMenuItemList.get(name);
    }

    public DesplegarJuego getPuzzleDisplay() {
        if (this.puzzleDisplay == null) {
            this.puzzleDisplay = new DesplegarJuego(this.puzzle);
        }
        return this.puzzleDisplay;
    }

    public void setPuzzle(IJuegoPalabras p) {
        this.puzzle = p;
        DesplegarJuego pd = this.getPuzzleDisplay();
        if (pd != null) {
            this.remove(pd);
            this.puzzleDisplay = null;
        }
        this.add((Component)this.getPuzzleDisplay(), "Center");
    }

    public void setCluesShown(boolean s) {
        this.getPuzzleDisplay().setMostrarPistas(s);
    }

    public void setKeyShown(boolean k) {
        this.getPuzzleDisplay().setMuestraLetras(k);
    }

    private Component getMenuBar() throws NoSuchMethodException {
        if (this.menuBar == null) {
            this.menuBar = new JMenuBar();
            JMenu menu = this.addMenu(this.menuBar, "Archivo", 70, "Load and save word lists");
            this.addMenuItem(menu, "Nuevo", 78, null, "nuevoJuego");
            this.addMenuItem(menu, "Salir", 88, null, "salir");
            menu = this.addMenu(this.menuBar, "Edit", 69, "Editar Palabras");
            this.addMenuItem(menu, "Editar Este Juego", 69, null, "edit");
            menu = this.addMenu(this.menuBar, "Ver", 86, "Change display options..");
            this.addCheckBoxMenuItem(menu, "Mostrar Resuelto", 75, null, "mostrarResuelto", false);
            this.addCheckBoxMenuItem(menu, "Mostrar Pistas", 67, null, "mostrarPistas", true);
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

    public void nuevoJuego(ActionEvent e) {
        this.mgr.cambiarPantallas(new HashMap());
    }

    public void edit(ActionEvent e) {
        HashMap<String, Map> m = new HashMap<String, Map>();
     //   m.put("wordList", this.puzzle.getListaPalabras());

        this.mgr.cambiarPantallas(m);
    }

    public void salir(ActionEvent e) {
        System.exit(0);
    }

    public void mostrarResuelto(ItemEvent e) {
        int state = e.getStateChange();
        this.setKeyShown(state == 1);
    }

    public void mostrarPistas(ItemEvent e) {
        int state = e.getStateChange();
        this.setCluesShown(state == 1);
    }

    private void handleException(Throwable ex) {
        JOptionPane.showMessageDialog(this, ex.toString(), ex.getClass().getName(), 0);
    }

  
    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    private void addCheckBoxMenuItem(JMenu menu, String label, int mnemonic, String accessibleDescription, String itemCallbackName, boolean initialState) throws NoSuchMethodException {
        JCheckBoxMenuItem checkBoxMenuItem = new JCheckBoxMenuItem(label, initialState);
        this.statefulMenuItemList.put(label, checkBoxMenuItem);
        checkBoxMenuItem.setMnemonic(mnemonic);
        checkBoxMenuItem.getAccessibleContext().setAccessibleDescription(accessibleDescription != null ? accessibleDescription : label);
        Class[] arrclass = new Class[1];
        Class class_ = class$java$awt$event$ItemEvent == null ? (PantallaJuego.class$java$awt$event$ItemEvent = PantallaJuego.class$("java.awt.event.ItemEvent")) : class$java$awt$event$ItemEvent;
        arrclass[0] = class_;
        final Method callback = this.getClass().getMethod(itemCallbackName, arrclass);
        checkBoxMenuItem.addItemListener((ItemEvent e) -> {
            try {
                callback.invoke(PantallaJuego.this, e);
            }
            catch (InvocationTargetException ex) {
                PantallaJuego.this.handleException(ex.getTargetException());
            }
            catch (IllegalAccessException ex) {
                PantallaJuego.this.handleException(ex);
            }
        });
        menu.add(checkBoxMenuItem);
    }
    
    
     private void addMenuItem(JMenu menu, String label, int mnemonic, String accessibleDescription, String actionCallbackName) throws NoSuchMethodException {
        JMenuItem menuItem = new JMenuItem(label, mnemonic);
        menuItem.getAccessibleContext().setAccessibleDescription(accessibleDescription != null ? accessibleDescription : label);
        Class[] arrclass = new Class[1];
        Class class_ = class$java$awt$event$ActionEvent == null ? (PantallaJuego.class$java$awt$event$ActionEvent = PantallaJuego.class$("java.awt.event.ActionEvent")) : class$java$awt$event$ActionEvent;
        arrclass[0] = class_;
        final Method callback = this.getClass().getMethod(actionCallbackName, arrclass);
        menuItem.addActionListener((ActionEvent e) -> {
            try {
                callback.invoke(PantallaJuego.this, e);
            }
            catch (InvocationTargetException ex) {
                ex.getTargetException().printStackTrace();
            }
            catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        });
        menu.add(menuItem);
    }
   

   
    
    
    
}

