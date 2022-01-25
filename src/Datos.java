public class Datos {
    private String nombre;
    private int Id;
    private String municipio;
    private double irca;
    private String tipoDeAgua;
    private String tipoDeCuerpo;
    private int numeroDeHabitantes;
    private int afeccion;

    public Datos(String nombre, int Id, String municipio, double irca, String tipoDeAgua, String tipoDeCuerpo,
            int numeroDeHabitantes, int afeccion) {
        this.nombre = nombre;
        this.Id = Id;
        this.municipio = municipio;
        this.irca = irca;
        this.tipoDeAgua = tipoDeAgua;
        this.tipoDeCuerpo = tipoDeCuerpo;
        this.numeroDeHabitantes = numeroDeHabitantes;
        this.afeccion = afeccion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return this.Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public double getIrca() {
        return this.irca;
    }

    public void setIrca(double irca) {
        this.irca = irca;
    }

    public String getTipoDeAgua() {
        return this.tipoDeAgua;
    }

    public void setTipoDeAgua(String tipoDeAgua) {
        this.tipoDeAgua = tipoDeAgua;
    }

    public String getTipoDeCuerpo() {
        return this.tipoDeCuerpo;
    }

    public void setTipoDeCuerpo(String tipoDeCuerpo) {
        this.tipoDeCuerpo = tipoDeCuerpo;
    }

    public int getNumeroDeHabitantes() {
        return this.numeroDeHabitantes;
    }

    public void setNumeroDeHabitantes(int numeroDeHabitantes) {
        this.numeroDeHabitantes = numeroDeHabitantes;
    }

    public int getAfeccion() {
        return this.afeccion;
    }

    public void setAfeccion(int afeccion) {
        this.afeccion = afeccion;
    }

}