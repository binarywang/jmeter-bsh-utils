package com.github.binarywang.bsh;

import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.log.Logger;

/**
 * @author Binary Wang
 */
public class BshUtils {
    public static void printParameters(Logger log,
            JMeterVariables vars) {
        log.info("vars = " + ToStringBuilder.reflectionToString(vars,
            ToStringStyle.MULTI_LINE_STYLE));

        Iterator<Entry<String, Object>> it = vars.getIterator();
        while (it.hasNext()) {
            String msg = vars.getThreadName() + " [params]: "
                + it.next().toString();
            log.info(msg);
            System.out.println(msg);
        }
    }
}
