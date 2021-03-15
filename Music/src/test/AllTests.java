package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite music-ohjelmalle
 * @author laura
 * @version 11.3.2021
 *
 */

  @RunWith(Suite.class)
  @SuiteClasses({
     // kanta.test.HetuTarkistusTest.class,
      music2.test.KappaleTest.class,
      music2.test.KappaleetTest.class,
      //music2.test.SettiTest.class,
      music2.test.SetitTest.class,
      //music2.test.MusicTest.class
      music2.test.RelaatiotTest.class,
      music2.test.RelaatioTest.class
      })
  public class AllTests {
   //
  }
