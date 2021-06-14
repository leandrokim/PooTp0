package main.java.models;

public enum TipoIVA {
    DOS_COMA_CINCO(2.5),
    CINCO(5),
    DIEZ_COMA_CINCO(10.5),
    VEINTIUNO(21),
    VEINTISIETE(27);

    private final double value;

//    private
    TipoIVA(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
