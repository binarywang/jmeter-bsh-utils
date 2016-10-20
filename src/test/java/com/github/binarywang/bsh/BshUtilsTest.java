package com.github.binarywang.bsh;

import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;

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

    /**
     * Test method for
     * {@link com.github.binarywang.bsh.BshUtils#checkState(org.apache.log.Logger, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
     * .
     * 
     * @throws UnsupportedEncodingException
     * @throws InterruptedException
     */
    @Test
    public void testCheckState()
            throws UnsupportedEncodingException, InterruptedException {
        BshUtils.checkState(this.log, this.vars,
            "http://10.255.33.103/v1/flow/getstate", "UU_W_SPEED_000003285",
            "UU_SPEED_WECHAT", "1995", 5);
    }
}
