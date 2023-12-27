public class Point3D extends Point2D {

    private double zCoord;

    public Point3D(double x, double y, double z) {
        super(x, y);
        zCoord = z;
    }

    public Point3D() {
        this (0,0,0);
    }

    public double getZ() {
        return zCoord;
    }

    public void setZ (double val) { 
        zCoord = val;
    }
}