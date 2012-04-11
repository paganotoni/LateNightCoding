import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EmployeeCodeAnalyzerTest {

  EmployeeCodeAnalyzer analyzer;

  @Before
  public void initialize(){
    analyzer = new EmployeeCodeAnalyzer();
  }

  @Test
  public void calculateWorkingAreaTest(){
     assertEquals( analyzer.calculateWorkingArea("01") , "Gerencia" );
     assertEquals( analyzer.calculateWorkingArea("11") , null );
     assertEquals( analyzer.calculateWorkingArea("06") , "Mantenimiento" );
  }

  @Test
  public void calculateJobPostionTest(){
    assertEquals( analyzer.calculateJobPosition("01") , "Gerente" );
    assertEquals( analyzer.calculateJobPosition("11") , null );
    assertEquals( analyzer.calculateJobPosition("06") , "Secretario" );
  }

  @Test
   public void calculateSindicatoTest(){
    assertEquals( analyzer.calculateSindicato("S") , "Gerente" );
    assertEquals( analyzer.calculateSindicato("W") , null );
    assertEquals( analyzer.calculateSindicato("N") , "Secretario" );
  }

  @Test
  public void calculatePensionTest(){
    assertEquals( analyzer.calculatePension("I") , "ISS" );
    assertEquals( analyzer.calculatePension("W") , null );
    assertEquals( analyzer.calculatePension("P") , "Porvenir" );
  }

  @Test
  public void calculateSaludTest(){
    assertEquals( analyzer.calculateSalud("F") , "Famisanar" );
    assertEquals( analyzer.calculateSalud("W") , null );
    assertEquals( analyzer.calculateSalud("I") , "Sisben" );
  }

  @Test
  public void analyzeCodeTest(){
    String sampleCode = "0101NPF1986";
    String result[] = analyzer.analyzeCode(sampleCode);
    assertEquals(result[0], "Gerencia");
    assertEquals(result[1], "Gerente");
    assertEquals(result[2], "No Sindicalizado");
    assertEquals(result[3], "Porvenir");
    assertEquals(result[4], "Famisanar");
    assertEquals(result[5], "1986");
  }

  @Test
  public void calculateFromArrayTest(){
    assertEquals( analyzer.calculateFromArray( EmployeeCodeAnalyzer.WORKING_AREAS, "01" ), "Gerencia" );
  }
}
