/*
 * Creado por Pedro Abarca
 * Noviembre,  2015
 * Universidad Técnica Nacional
 */
package crucigrama.modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.table.AbstractTableModel;

public class ModeloTablaMapa
extends AbstractTableModel {
    private final Map map;
    private List mapEntries;
    private long tempKeyCount = 0;
    private static final Comparator KEY_COMPARATOR = (Comparator) (Object o1, Object o2) -> {
        Object k1 = ((Map.Entry)o1).getKey();
        Object k2 = ((Map.Entry)o2).getKey();
        return ((Comparable)k1).compareTo(k2);
    };

    public ModeloTablaMapa(Map m) {
        this.map = m;
        this.refresh();
    }

    public void refresh() {
        int oldSize = 0;
        if (this.mapEntries != null) {
            oldSize = this.mapEntries.size() + 1;
        }
        Set s = this.map.entrySet();
        TreeSet ts = new TreeSet(KEY_COMPARATOR);
        ts.addAll(s);
        this.mapEntries = new ArrayList();
        this.mapEntries.addAll(ts);
        int newSize = this.getRowCount();
        this.fireTableDataChanged();
        if (oldSize > newSize) {
            this.fireTableRowsDeleted(newSize, oldSize - 1);
        } else if (oldSize < newSize) {
            this.fireTableRowsInserted(oldSize, newSize - 1);
        }
    }

    @Override
    public int getRowCount() {
        return this.map.size() + 1;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    private Map.Entry getEntry(int row) {
        return (Map.Entry)this.mapEntries.get(row);
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (row < this.map.size()) {
            Map.Entry entry = this.getEntry(row);
            return col == 0 ? entry.getKey() : entry.getValue();
        }
        return "";
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 0 || row < this.map.size();
    }

    private Map.Entry getEntry(final Object key) {
        return new Map.Entry(){

            @Override
            public Object getKey() {
                return key;
            }

            @Override
            public Object getValue() {
                return ModeloTablaMapa.this.map.get(key);
            }

            @Override
            public Object setValue(Object o) {
                return ModeloTablaMapa.this.map.put(key, o);
            }
        };
    }

    @Override
    public void setValueAt(Object o, int row, int col) {
        if (col == 1) {
            if (row < this.map.size()) {
                Map.Entry entry = this.getEntry(row);
                entry.setValue(o);
            } else {
                String key = "@tempKey" + Long.toString(this.tempKeyCount++, 36);
                this.map.put(key, o);
                this.mapEntries.add(this.getEntry(key));
            }
        } else if (row < this.map.size()) {
            Map.Entry entry = this.getEntry(row);
            Object oldKey = entry.getKey();
            Object oldValue = entry.getValue();
            this.map.remove(oldKey);
            this.map.put(o, oldValue);
            this.mapEntries.set(row, this.getEntry(o));
        } else {
            this.map.put(o, "");
            this.mapEntries.add(this.getEntry(o));
        }
    }

}

