import java.util.HashMap;
import java.util.Map;
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
    Scanner scanner = new Scanner( System.in );
    System.out.println("Digita el numero de empleados:");
    Integer employeeNumber = scanner.nextInt();

    EmployeeCodeAnalyzer analyzer = new EmployeeCodeAnalyzer();
    String[][] results = new String[employeeNumber][6];

    //HashMap<String, Integer> areasCount =  new HashMap<String, Integer>();
    //HashMap<String, Integer> cargosCount =  new HashMap<String, Integer>();
    //HashMap<String, Integer> sindicatosCount =  new HashMap<String, Integer>();
    String[] areaCodes = new String[]{ "Gerencia", "Contabilidad", "Almacen", "Compras", "Ventas", "Mantenimiento", "Personal" };
    Integer[] areaCounts = new Integer[]{ 0, 0, 0, 0, 0, 0, 0};

    String[] positionCodes = new String[]{ "Gerente", "Director", "Subdirector", "Auxiliar", "Representante", "Secretario", "Operador" };
    Integer[] positionCounts = new Integer[]{ 0, 0, 0, 0, 0, 0, 0};

    String[] sindicatoCodes = new String[]{ "Sindicalizado", "No Sindicalizado" };
    Integer[] sindicatoCounts = new Integer[]{ 0, 0 };

    String[] pensionCodes = new String[]{ "Porvenir", "Skandia", "City Colfondos", "Proteccion", "ISS", "Ing Pensiones y Cesantias" };
    Integer[] pensionCounts = new Integer[]{ 0, 0, 0, 0, 0, 0};

    String[] saludCodes = new String[]{ "Famisanar", "Coomeva", "Susalud", "Colsanitas", "Barrios unidos de quibdo", "Sisben" };
    Integer[] saludCounts = new Integer[]{ 0, 0, 0, 0, 0, 0};

    Object[][] cargos = new Object[JOB_POSITIONS.length][2];
    //String[][] sindicato = new String[SINDICATO.length][2];
    //String[][] pension = new String[PENSION.length][2];
    //String[][] salud = new String[SALUD.length][2];

    Integer oldestYear = Integer.MAX_VALUE;


    for( int i = 0 ; i< employeeNumber ; i++ ){
      System.out.println( "Empleado # "+ (i+1)+" => " );
      String code = scanner.next();
      results[i] = analyzer.analyzeCode( code );

      Integer codeIndex = java.util.Arrays.asList(areaCodes).indexOf(results[i][0]);
      areaCounts[codeIndex] = areaCounts[codeIndex] + 1;

      Integer positionIndex = java.util.Arrays.asList(positionCodes).indexOf(results[i][1]);
      positionCounts[positionIndex] = positionCounts[positionIndex] + 1;

      Integer sindicatoIndex = java.util.Arrays.asList(sindicatoCodes).indexOf(results[i][2]);
      sindicatoCounts[sindicatoIndex] = sindicatoCounts[sindicatoIndex] + 1;

      Integer pensionIndex = java.util.Arrays.asList(pensionCodes).indexOf(results[i][3]);
      pensionCounts[pensionIndex] = pensionCounts[pensionIndex] + 1;

      Integer saludIndex = java.util.Arrays.asList(saludCodes).indexOf(results[i][4]);
      saludCounts[saludIndex] = saludCounts[saludIndex] + 1;

      Integer employerYear = Integer.valueOf( results[i][5] );
      oldestYear =  employerYear < oldestYear ? employerYear : oldestYear;

    }

    System.out.println("\nWorking Areas Stats:");
    for( int i = 0 ; i < areaCodes.length ; i++ ){
      System.out.println( WORKING_AREAS[i][1] + ": " + areaCounts[i] + " Employers " );
    }

    System.out.println("\n");
    System.out.println("Position Stats:");
    for( int i = 0 ; i < positionCounts.length ; i++ ){
      System.out.println( JOB_POSITIONS[i][1] + ": " + positionCounts[i]  +" Employers");
    }
    System.out.println("\n");
    System.out.println("Sindicato Stats:");

    for( int i = 0 ; i < sindicatoCodes.length ; i++ ){
      System.out.println( SINDICATO[i][1] + " : " + sindicatoCounts[i]  +" Employers");
    }

    System.out.println("\n");
    System.out.println("Pension Stats:");

    for( int i = 0 ; i < pensionCodes.length ; i++ ){
      System.out.println( PENSION[i][1] + " : " + pensionCounts[i]  +" Employers");
    }

    System.out.println("\n");
    System.out.println("Salud Stats:");

    for( int i = 0 ; i < saludCodes.length ; i++ ){
      System.out.println( SALUD[i][1] + " : " + saludCounts[i]  +" Employers");
    }

    System.out.println("\n");
    System.out.println( "Oldest contract year: " +oldestYear);

  }



}
