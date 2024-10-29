package servidor.Repositorios;

import servidor.DTO.NodoTurnoDTO;

public interface GeneradorTurnoRepositoryInt {

    public NodoTurnoDTO generarTurno(String identificacion);

    public boolean estadoActivo();
}
