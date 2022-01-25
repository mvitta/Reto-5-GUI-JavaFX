public class DensidadPoblacional extends ObjetoGeografico {

    private int numeroDeHabitantes;

    public DensidadPoblacional(int numeroDeHabitantes) {
        this.numeroDeHabitantes = numeroDeHabitantes;
    }

    public int afeccion() {
        if (this.numeroDeHabitantes < 10000) {
            return 0;
        } else if (this.numeroDeHabitantes > 10000 && this.numeroDeHabitantes < 50000) {
            return 1;
        } else {
            return 2;
        }
    }

    public int getNumeroDeHabitantes() {
        return this.numeroDeHabitantes;
    }

    public void setNumeroDeHabitantes(int numeroDeHabitantes) {
        this.numeroDeHabitantes = numeroDeHabitantes;
    }

    @Override
    public String toString() {
        return "Numero De Habitantes: " + getNumeroDeHabitantes() + "\n";
    }

}
