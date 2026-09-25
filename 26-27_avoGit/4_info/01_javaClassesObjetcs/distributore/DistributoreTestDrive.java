public class DistributoreTestDrive {
    public static void main(String[] args) {
        Distributore d = new Distributore();
        d.eroga();
        d.setAcceso(false);
        System.out.println("acceso: " + d.isAcceso());
        d.eroga();
        System.out.println("lattine rimaste: " + d.getLattine());
    }
}
