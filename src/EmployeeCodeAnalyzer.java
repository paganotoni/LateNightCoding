import java.util.Scanner;

public class EmployeeCodeAnalyzer {

  public final static String[][] WORKING_AREAS = new String[][]{
      new String[]{"01", "Gerencia"},
      new String[]{"02", "Contabilidad"},
      new String[]{"03", "Almacen"},
      new String[]{"04", "Compras"},
      new String[]{"05", "Ventas"},
      new String[]{"06", "Mantenimiento"},
      new String[]{"07", "Personal"},
  };

  public final static String[][] JOB_POSITIONS = new String[][]{
      new String[]{"01", "Gerente"},
      new String[]{"02", "Director"},
      new String[]{"03", "Subdirector"},
      new String[]{"04", "Auxiliar"},
      new String[]{"05", "Representante"},
      new String[]{"06", "Secretario"},
      new String[]{"07", "operador"},
  };

  public final static String[][] SINDICATO = new String[][]{
      new String[]{"S", "Sindicalizado"},
      new String[]{"N", "No Sindicalizado"}
  };

  public final static String[][] PENSION = new String[][]{
      new String[]{"P", "Porvenir"},
      new String[]{"S", "Skandia"},
      new String[]{"C", "City Colfondos"} ,
      new String[]{"R", "Proteccion"},
      new String[]{"I", "ISS"},
      new String[]{"N", "Ing Pensiones y Cesantias"}
  };

  public final static String[][] SALUD = new String[][]{
      new String[]{"F", "Famisanar"},
      new String[]{"C", "Coomeva"},
      new String[]{"S", "Susalud"} ,
      new String[]{"O", "Colsanitas"},
      new String[]{"Q", "Barrios unidos de quibdo"},
      new String[]{"I", "Sisben"}
  };

  public String calculateWorkingArea(String areaCode) {
    return calculateFromArray( WORKING_AREAS, areaCode );
  }

  public String calculateJobPosition(String jobCode) {
    return calculateFromArray( JOB_POSITIONS, jobCode );
  }

  public String calculateSindicato( String sindicatoCode ){
    return calculateFromArray( SINDICATO, sindicatoCode );
  }

  public String calculatePension( String pensionCode ){
    return calculateFromArray( PENSION, pensionCode );
  }

  public String calculateSalud( String saludCode ){
    return calculateFromArray( SALUD, saludCode );
  }

  public String[] analyzeCode( String employerCode ){
    // Validate 10 length String
    String[] result = new String[6];
    result[0] = calculateWorkingArea( employerCode.substring(0, 2) );
    result[1] = calculateJobPosition( employerCode.substring(2, 4) );
    result[2] = calculateSindicato( employerCode.substring(4,5 ) );

    result[3] = calculatePension(employerCode.substring(5, 6));
    result[4] = calculateSalud(employerCode.substring(6, 7));
    result[5] = employerCode.substring(7,employerCode.length() );

    return result;
  }


  public String calculateFromArray( String[][] dataArray, String lookupCode ){
    String result = null;
    for( String[] area : dataArray) {
      if (area[0].equals(lookupCode)){
        result = area[1];
        break;
      }
    }

    return result;
  }

  public static void main( String ... args){
    System.out.println("Digita el numero de empleados:");

    Scanner scanner = new Scanner( System.in );
    Integer employeeNumber = scanner.nextInt();
    EmployeeCodeAnalyzer analyzer = new EmployeeCodeAnalyzer();
    String[][] results = new String[employeeNumber][6];

    Integer[] areas = new Integer[WORKING_AREAS.length];
    Integer[] cargos = new Integer[JOB_POSITIONS.length];
    Integer[] sindicato = new Integer[SINDICATO.length];
    Integer[] pension = new Integer[PENSION.length];
    Integer[] salud = new Integer[SALUD.length];

    Integer oldestYear = Integer.MAX_VALUE;

    for( int i = 0 ; i< employeeNumber ; i++ ){
      System.out.println( "Empleado # "+ (i+1)+" => " );
      String code = scanner.next();
      results[i] = analyzer.analyzeCode( code );



      Integer employerYear = Integer.valueOf( results[i][5] );
      oldestYear =  employerYear < oldestYear ? employerYear : oldestYear;
    }



  }



}
