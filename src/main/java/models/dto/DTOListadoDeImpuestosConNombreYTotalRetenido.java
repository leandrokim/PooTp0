package main.java.models.dto;

import java.time.LocalDate;
import java.util.List;

public class DTOListadoDeImpuestosConNombreYTotalRetenido {

    public double totalRetenido;
    public String nombreDelImpuesto;


    public DTOListadoDeImpuestosConNombreYTotalRetenido(double totalRetenido,
                                                        String nombreDelImpuesto) {
        this.totalRetenido = totalRetenido;
        this.nombreDelImpuesto = nombreDelImpuesto;

    }
    /* public List<DTOListadoDeImpuestosConNombreYTotalRetenido unificacionDeImpuestosYRetenciones(List<DTOListadoDeImpuestosConNombreYTotalRetenido> retencionesEImpuestos,List<DTOListadoDeImpuestosConNombreYTotalRetenido> dto)
     {

         for (DTOListadoDeImpuestosConNombreYTotalRetenido lista : retencionesEImpuestos) {

             DTOListadoDeImpuestosConNombreYTotalRetenido busq=verificarImpuesto(dto, lista.nombreDelImpuesto);

             if (busq!=null)
             {
                 busq.totalRetenido=busq.totalRetenido + lista.totalRetenido;
                 dto.replaceAll(p ->p.nombreDelImpuesto==busq.nombreDelImpuesto?busq:p);
             }
             else{
                 dto.add(new DTOListadoDeImpuestosConNombreYTotalRetenido(lista.totalRetenido,lista.nombreDelImpuesto));
             }

         }
         return dto;
     }
    public DTOListadoDeImpuestosConNombreYTotalRetenido verificarImpuesto(List<DTOListadoDeImpuestosConNombreYTotalRetenido> dto,String nombreImpuesto)
    {//si se quiere agregar sobre esta clase asi no se repite en los 3 pasos de total retenciones por impuestos
        return (dto.stream()
                .filter(v -> v.nombreDelImpuesto.equals(nombreImpuesto))
                .findFirst().orElse(null));
    }*/
}