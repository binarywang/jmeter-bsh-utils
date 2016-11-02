package com.github.binarywang.bsh;

import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.log.Logger;

import com.google.gson.JsonObject;
import com.jayway.jsonpath.JsonPath;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.util.StringPool;

/**
 * @author Binary Wang
 */
public class BshUtils {
    public static void printParameters(Logger log, JMeterVariables vars) {
        log.info("vars = " + ToStringBuilder.reflectionToString(vars,
            ToStringStyle.MULTI_LINE_STYLE));

        Iterator<Entry<String, Object>> it = vars.getIterator();
        while (it.hasNext()) {
            String msg = " [params]-> " + it.next().toString();
            log(log, vars, msg);
        }
    }

    public static void log(Logger log, JMeterVariables vars, String msg) {
        msg = vars.getThreadName() + ": " + msg;
        log.info(msg);
        System.out.println(msg);
    }

    public static void checkState(Logger log, JMeterVariables vars, String path,
            String flowId, int sleepInterval) {
        try {
            while (true) {
                log(log, vars, "Will be sleeping for the next " + sleepInterval
                    + " seconds..............");
                Thread.sleep(sleepInterval * 1000);
                log(log, vars, "Wakes up now and continues to work");
                log(log, vars,
                    String.format("path=[%s],flowId=[%s]", path, flowId));

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id", flowId);
                HttpResponse response = HttpRequest.post(path)
                    .header("User-Agent", BshUtils.class.getName())
                    .body(jsonObject.toString().getBytes(StringPool.UTF_8),
                        "application/json; charset=utf-8")
                    .send();
                log(log, vars, "================>\n   " + response.bodyText());
                Boolean success = JsonPath.read(response.bodyText(),
                    "$.success");
                if (!success
                    || JsonPath.read(response.bodyText(), "$.result") == null) {
                    log(log, vars, "查询流程状态时发生错误");
                    break;
                }

                String jsonPath = "$.result.flowNodes[0].runtimeState";
                Integer state = JsonPath.read(response.bodyText(), jsonPath);
                if (state != 2) {
                    log(log, vars, "G-SA-WX is still ongoing");
                    continue;
                }

                jsonPath = "$.result.flowNodes[1].graph.grapgNodes[2].runtimeState";
                state = JsonPath.read(response.bodyText(), jsonPath);
                if (state == 2) {
                    break;
                }

                log(log, vars, "J-CREATE-INSTALLMENT-ORDER is still ongoing");
                continue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
