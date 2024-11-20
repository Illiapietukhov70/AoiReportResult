

public class Geometry {
    final static char[] xyz = {'X', 'Y', 'Z'};;
    private int[] position = { -1, -1,- 1};
    private int[] positionLocal = { -1, -1,- 1};;
    private int[] rotation= { -1, -1,- 1}; ;
    private int[] rotationLocal = { -1, -1,- 1};;
    private int[] size = { -1, -1,- 1};;
    private int[] sizeLocal= { -1, -1,- 1}; ;

    public Geometry() {
        this.position = position;
        this.positionLocal = positionLocal;
        this.rotation = rotation;
        this.rotationLocal = rotationLocal;
        this.size= size;
        this.sizeLocal = sizeLocal;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public int[] getPositionLocal() {
        return positionLocal;
    }

    public void setPositionLocal(int[] positionLocal) {
        this.positionLocal = positionLocal;
    }

    public int[] getRotation() {
        return rotation;
    }

    public void setRotation(int[] rotation) {
        this.rotation = rotation;
    }

    public int[] getRotationLocal() {
        return rotationLocal;
    }

    public void setRotationLocal(int[] rotationLocal) {
        this.rotationLocal = rotationLocal;
    }

    public int[] getSize() {
        return size;
    }

    public void setSize(int[] size) {
        this.size = size;
    }

    public int[] getSizeLocal() {
        return sizeLocal;
    }

    public void setSizeLocal(int[] sizeLocal) {
        this.sizeLocal = sizeLocal;
    }
}


