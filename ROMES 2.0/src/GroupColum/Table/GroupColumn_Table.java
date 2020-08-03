package GroupColum.Table;
/* Java Version 1.4.2_03-b02
 *
 * |-------------------------------------------------------------------|
 * |        |             Name              |         Language         |
 * |        |-------------------------------|--------------------------|
 * |  SNo.  |       |        Second         |        |      Others     |
 * |        | First |-----------------------| Native |-----------------|
 * |        |       |   1   |    2   |      |   2    |   3    |        |
 * |-------------------------------------------------------------------|
 * |        |       |       |        |      |        |        |        |
 *
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


// Steve Webb 16/09/04 swebb99_uk@hotmail.com

public class GroupColumn_Table extends JFrame {
    
    GroupColumn_Table() {
        super( "Multi-Width Header Example" );
        
        String col [] = {"고객사","모델명","PWB 부번","PCB SIZE(mm)","작업면","사용솔더","A","B","C","D","E","F"
				,"품종교체시간(분)","A","B","C","D","E","F","점수"};
		DefaultTableModel dm= new DefaultTableModel(col,0);
            // Setup table
            JTable table = new JTable( /*dm, new GroupableTableColumnModel()*/);
            table.setColumnModel(new Group_TableColumnModel());
            table.setTableHeader(new Group_TableHeader((Group_TableColumnModel)table.getColumnModel()));
            table.setModel(dm);
            
            
            // Setup Column Groups
            Group_TableColumnModel cm = (Group_TableColumnModel)table.getColumnModel();
            Group_Column g_name = new Group_Column(new GroupableTableCellRenderer(),"SMT 라인 배정");
            g_name.add(cm.getColumn(6));
            g_name.add(cm.getColumn(7));
            g_name.add(cm.getColumn(8));
            g_name.add(cm.getColumn(9));
            g_name.add(cm.getColumn(10));
            g_name.add(cm.getColumn(11));
            Group_Column g_lang = new Group_Column("Cycle Time(개)");
            g_lang.add(cm.getColumn(13));
            g_lang.add(cm.getColumn(14));
            g_lang.add(cm.getColumn(15));
            g_lang.add(cm.getColumn(16));
            g_lang.add(cm.getColumn(17));
            g_lang.add(cm.getColumn(18));
            cm.addGroupColumn(g_name);
            cm.addGroupColumn(g_lang);
            
            // Finish off gui
            JScrollPane scroll = new JScrollPane( table );
            getContentPane().add( scroll );
            setSize( 800, 200 );
    }
    
    public static void main(String[] args) {
        GroupColumn_Table frame = new GroupColumn_Table();
        frame.addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent e ) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }
}

/**
 * Demo renderer just to prove they can be used.
 */
class GroupableTableCellRenderer extends DefaultTableCellRenderer {
    /**
     *
     * @param table
     * @param value
     * @param selected
     * @param focused
     * @param row
     * @param column
     * @return
     */
    public Component getTableCellRendererComponent(JTable table, Object value,
    boolean selected, boolean focused, int row, int column) {
        JTableHeader header = table.getTableHeader();
        if (header != null) {
            //setForeground(Color.WHITE);
            setBackground(new Color(150,255,180));
        }
        setHorizontalAlignment(SwingConstants.CENTER);
        setText(value != null ? value.toString() : " ");
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        return this;
    }
}

