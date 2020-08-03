package GroupColum.Table;
import java.awt.Color;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


/**
 * This is the object which manages the header of the JTable and
 * also provides functionality for groupable headers.
 */
public class Group_TableHeader extends JTableHeader {

    /**
     * Identifies the UI class which draws the header.
     */    
    private static final String uiClassID = "GroupableTableHeaderUI";
    
    /**
     * Constructs a GroupableTableHeader which is initialized with cm as the
     * column model. If cm is null this method will initialize the table header
     * with a default TableColumnModel.
     * @param model the column model for the table
     */    
    public Group_TableHeader(Group_TableColumnModel model) {
        super(model);
        setUI(new Group_TableHeaderUI());
        setReorderingAllowed(false);
        setBackground(new Color(210,230,255));//hjadd
    }
    
    
    /**
     * Sets the margins correctly for all groups within
     * the header.
     */    
    public void setColumnMargin() {
        int columnMargin = getColumnModel().getColumnMargin();
        Iterator iter = ((Group_TableColumnModel)columnModel).columnGroupIterator();
        while (iter.hasNext()) {
            Group_Column cGroup = (Group_Column)iter.next();
            cGroup.setColumnMargin(columnMargin);
        }
    }
    
}

