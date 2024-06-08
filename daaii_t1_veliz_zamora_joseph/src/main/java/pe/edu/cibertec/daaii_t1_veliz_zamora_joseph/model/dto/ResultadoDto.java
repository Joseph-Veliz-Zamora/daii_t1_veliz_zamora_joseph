package pe.edu.cibertec.daaii_t1_veliz_zamora_joseph.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResultadoDto {
    private Boolean respuesta;
    private String mensaje;

}
