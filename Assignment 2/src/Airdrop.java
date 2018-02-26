/**
 * Airdrop object for Assignment 2
 * This object helps you to return 3 fields at once.
 * The meaning of airdrop is described in the assignment.
 */
public class Airdrop {
    private int row;
    private int column;
    private int size;

    public Airdrop(int row, int column, int size) {
        this.row = row;
        this.column = column;
        this.size = size;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Airdrop){
            Airdrop x = (Airdrop) obj;

            if(row == x.getRow() &&
                    column == x.getColumn() &&
                    size == x.getSize())
                return true;
        }

        return false;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
