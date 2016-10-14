package com.github.binarywang.bsh;

import static org.mockito.Mockito.when;

import org.apache.jmeter.threads.JMeterVariables;
import org.apache.log.Hierarchy;
import org.apache.log.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.github.binarywang.BaseTestCase;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

/**
 * @author Binary Wang
 */
public class BshUtilsTest extends BaseTestCase {
    private Logger log;

    @Mock
    private JMeterVariables vars;

    @Before
    public void setUp() {
        this.log = Hierarchy.getDefaultHierarchy().getLoggerFor("BshUtilsTest");
        when(this.vars.getIterator())
            .thenReturn(Maps.newHashMap(ImmutableMap.of("a", 1, "b", "uii"))
                .entrySet().iterator());
        when(this.vars.getThreadName()).thenReturn("thread name bababa");
    }

    /**
     * Test method for {@link com.github.binarywang.bsh.BshUtils#printParameters(org.apache.log.Logger, org.apache.jmeter.threads.JMeterVariables)}.
     */
    @Test
    public void testPrintParameters() {
        BshUtils.printParameters(this.log, this.vars);
    }

}
