import javax.swing.*;
import java.util.Random;

/**
 *
 * @author Richie!
 */
public class Notas {

    final static String[]   NOMBRES_MATERIAS = new String[]{ "MATEMATICAS", "FISICA", "INFORMATICA", "ESPANIOL", "FILOSOFIA", "ESTADISTICA"};
    final static Integer[]  CREDITOS = new Integer[]{ 4, 4, 3, 2, 1, 2};
    final static Float      NOTA_MINIMA = 0F;
    final static Float      NOTA_MAXIMA = 5F;

    public static void main(String... args) {
        //TODO: add exception handling for wrong number input
         Integer numeroDeEstudiante = Integer.parseInt(JOptionPane.showInputDialog(null, "Por Favor digita el numero de estudiates"));
         Integer numeroDeMaterias = CREDITOS.length;

         Notas calculadorDeNotas = new Notas();
         Notas otra = new Notas();

         Float[][] primerParcial = calculadorDeNotas.generarParcial( numeroDeEstudiante, numeroDeMaterias);
         Float[][] segundoParcial = calculadorDeNotas.generarParcial( numeroDeEstudiante, numeroDeMaterias);
         Float[][] tercerParcial = calculadorDeNotas.generarParcial( numeroDeEstudiante, numeroDeMaterias);

         Float[][] definitivas =  calculadorDeNotas.calcularDefinitiva(numeroDeEstudiante, numeroDeMaterias, primerParcial, segundoParcial, tercerParcial);
         Integer[] perdidasPorEstudiante = calculadorDeNotas.materiasPerdidasPorEstudiante( numeroDeEstudiante, numeroDeMaterias, definitivas );
         Float[][] habilitaciones =  calculadorDeNotas.habilitar( numeroDeEstudiante, numeroDeMaterias, definitivas, perdidasPorEstudiante );
         Float[] promedios = calculadorDeNotas.promedioSemestral( numeroDeEstudiante, numeroDeMaterias, definitivas );

         /*
          * Nombre  $
          * Materia 1  p1 = $ p2 = $ p3 = $ DEF=$ [HAB=$]
          * Materia 1  p1 = $ p2 = $ p3 = $ DEF=$ [HAB=$]
          * Materia 1  p1 = $ p2 = $ p3 = $ DEF=$ [HAB=$]
          * Materia 1  p1 = $ p2 = $ p3 = $ DEF=$ [HAB=$]
          * Materia 1  p1 = $ p2 = $ p3 = $ DEF=$ [HAB=$]
          * Materia 1  p1 = $ p2 = $ p3 = $ DEF=$ [HAB=$]
          *
          * Promedio Semestral $
          * Estado $
          */

         for( int indiceEstudiantes = 0; indiceEstudiantes < numeroDeEstudiante ; indiceEstudiantes++ ){
             System.out.println( "\n* Nombre: ESTUDIANTE_"+ (indiceEstudiantes+1) );
             for( int indiceMateria = 0; indiceMateria < numeroDeMaterias; indiceMateria++ ){
                 String materia = NOMBRES_MATERIAS[indiceMateria];
                 Float notaPrimerParcial =  primerParcial[indiceEstudiantes][indiceMateria];
                 Float notaSegundoParcial =  segundoParcial[indiceEstudiantes][indiceMateria];
                 Float notaTercerParcial =  tercerParcial[indiceEstudiantes][indiceMateria];
                 Float notaDefinitiva =  definitivas[indiceEstudiantes][indiceMateria];
                 Float habilitacion =  habilitaciones[indiceEstudiantes][indiceMateria];

                 System.out.print("* " + materia + " \tP1:" + notaPrimerParcial + " \tP2:" + notaSegundoParcial + " \tP3: " + notaTercerParcial + " \tDEF: " + notaDefinitiva);
                 if( habilitacion != null ) System.out.print( "\t ***HAB: "+habilitacion );
                 System.out.println("");
             }


             System.out.println( "\n* Promedio Semestral:"+ promedios[indiceEstudiantes] );
             String estado = perdidasPorEstudiante[indiceEstudiantes] >= 3 || promedios[indiceEstudiantes] < 3.0F ? "SEMESTRE PERDIDO" : "SEMESTRE GANADO! AL MENOS UNO!!!";
             System.out.println( "* ESTADO:"+ estado );
         }
    }
    
    public Float[][] generarParcial(Integer numeroDeEstudiante, Integer numeroDeMaterias ){
        Float[][] parcial = new Float[numeroDeEstudiante][numeroDeMaterias];
        
        for( int indiceEstudiantes = 0; indiceEstudiantes < numeroDeEstudiante ; indiceEstudiantes++ ){
            for( int indiceMateria = 0; indiceMateria < numeroDeMaterias; indiceMateria++ ){
                parcial[indiceEstudiantes][ indiceMateria ] = new Random().nextFloat( ) * 5.0F;

                if( parcial[indiceEstudiantes][indiceMateria] < NOTA_MINIMA ) parcial[indiceEstudiantes][indiceMateria] = NOTA_MINIMA;
                if( parcial[indiceEstudiantes][indiceMateria] > NOTA_MAXIMA ) parcial[indiceEstudiantes][indiceMateria] = NOTA_MAXIMA;
            }
        }

        return parcial;
    }
    
    public Float[][] calcularDefinitiva( Integer numeroDeEstudiante, Integer numeroDeMaterias , Float[][] primerParcial, Float[][] segundoParcial,Float[][] tercerParcial ) {
        Float[][] definitivas = new Float[numeroDeEstudiante][numeroDeMaterias];
        
        for( int indiceEstudiantes = 0; indiceEstudiantes < numeroDeEstudiante ; indiceEstudiantes++ ){
            for( int indiceMateria = 0; indiceMateria < numeroDeMaterias; indiceMateria++ ){
                Float partePrimera = primerParcial[indiceEstudiantes][indiceMateria] * 0.3F;
                Float parteSegunda = segundoParcial[indiceEstudiantes][indiceMateria] * 0.3F;
                Float parteTercero = segundoParcial[indiceEstudiantes][indiceMateria] * 0.4F;

                definitivas[indiceEstudiantes][ indiceMateria ] = partePrimera + parteSegunda + parteTercero ;
            }
        }

        return definitivas;
    }

    public Float[][] habilitar( Integer numeroDeEstudiante, Integer numeroDeMaterias, Float[][] definitivas, Integer[] perdidas ){
        Float[][] habilitaciones = new Float[numeroDeEstudiante][numeroDeMaterias];
        for( int indiceEstudiantes = 0; indiceEstudiantes < numeroDeEstudiante ; indiceEstudiantes++ ){
            if( perdidas[indiceEstudiantes] < 3 ){
                for( int indiceMateria = 0; indiceMateria < numeroDeMaterias; indiceMateria++ ){
                    if( definitivas[indiceEstudiantes][indiceMateria] < 3F ) habilitaciones[indiceEstudiantes][indiceMateria] = new Random().nextFloat( ) * 5F;
                }
            }
        }

        return habilitaciones;
    }

    public Float[] promedioSemestral(Integer numeroDeEstudiante, Integer numeroDeMaterias , Float[][] definitivas){
        Float[] promedio = new Float[numeroDeEstudiante];

        for( int indiceEstudiantes = 0; indiceEstudiantes < numeroDeEstudiante ; indiceEstudiantes++ ){
            Float sumaNotas = 0F;
            Integer totalCreditos =  0;
            for( int indiceMateria = 0; indiceMateria < numeroDeMaterias; indiceMateria++ ){
                 totalCreditos += CREDITOS[indiceMateria];
                 sumaNotas += definitivas[indiceEstudiantes][indiceMateria] * CREDITOS[indiceMateria];
            }

            promedio[indiceEstudiantes] = sumaNotas / totalCreditos;
        }

        return promedio;
    }

    public Integer[] materiasPerdidasPorEstudiante(  Integer numeroDeEstudiante, Integer numeroDeMaterias, Float[][] definitivas ){
        Integer[] perdidas = new Integer[numeroDeEstudiante];

        for( int indiceEstudiantes = 0; indiceEstudiantes < numeroDeEstudiante ; indiceEstudiantes++ ){
            int perdidasEstudiante = 0;
            for( int indiceMateria = 0; indiceMateria < numeroDeMaterias; indiceMateria++ ){
                if( definitivas[indiceEstudiantes][indiceMateria] < 3.0F ) perdidasEstudiante++;
            }
            perdidas[indiceEstudiantes] = perdidasEstudiante;
        }
        
        return perdidas;
    }
}
