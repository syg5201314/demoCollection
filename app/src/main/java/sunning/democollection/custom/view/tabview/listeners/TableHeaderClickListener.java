package sunning.democollection.custom.view.tabview.listeners;

/**
 * Listener interface to listen for clicks on table headers of a {@link de.codecrafters.tableview.SortableTableView}.
 *
 * @author ISchwarz
 */
public interface TableHeaderClickListener {

    /**
     * This method is called of a table header was clicked.
     *
     * @param columnIndex The index of the column that was clicked.
     */
    void onHeaderClicked(final int columnIndex);

}
