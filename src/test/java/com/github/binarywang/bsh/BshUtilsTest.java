package com.github.binarywang.bsh;

import com.github.binarywang.BaseTestCase;
import com.google.common.collect.ImmutableMap;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.log.Hierarchy;
import org.apache.log.Logger;
import org.mockito.Mock;
import org.testng.annotations.*;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

import static org.mockito.Mockito.when;

/**
 * @author Binary Wang
 */
public class BshUtilsTest extends BaseTestCase {
  private Logger log;

  @Mock
  private JMeterVariables vars;

  @BeforeMethod
  public void setUp() {
    this.log = Hierarchy.getDefaultHierarchy().getLoggerFor("BshUtilsTest");
    Iterator<Map.Entry<String, Object>> iterator = ImmutableMap
        .of("a", new Object(), "b", new Object())
        .entrySet().iterator();
    when(this.vars.getIterator())
        .thenReturn(iterator);
    when(this.vars.getThreadName()).thenReturn("thread name bababa");
  }

  /**
   * Test method for {@link com.github.binarywang.bsh.BshUtils#printParameters(org.apache.log.Logger, org.apache.jmeter.threads.JMeterVariables)}.
   */
  @Test
  public void testPrintParameters() {
    BshUtils.printParameters(this.log, this.vars);
  }

  /**
   * Test method for
   * {@link com.github.binarywang.bsh.BshUtils#checkState(Logger, JMeterVariables, String, String, int)}
   *
   * @throws UnsupportedEncodingException
   * @throws InterruptedException
   */
  @Test
  public void testCheckState() {
    BshUtils.checkState(this.log, this.vars,
        "http://10.255.33.103/v1/flow/getstate", "1995", 5);
  }
}
