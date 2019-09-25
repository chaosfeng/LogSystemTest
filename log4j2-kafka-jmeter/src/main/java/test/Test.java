package test;

import lombok.extern.slf4j.Slf4j;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import service.InventoryService;
import service.OrderService;

import java.io.Serializable;

@Slf4j(topic = "Test_Log4j2_To_Kafka")
public class Test extends AbstractJavaSamplerClient implements Serializable {

    private static InventoryService inventoryService = new InventoryService();

    private static OrderService orderService = new OrderService();

    @Override
    public Arguments getDefaultParameters(){
        System.out.println("get Default Parameters");
        Arguments params = new Arguments();
        params.addArgument("logLoop", "10000");
        return params;
    }

    @Override
    public void setupTest(JavaSamplerContext context) {
        super.setupTest(context);
    }

    @Override
    public void teardownTest(JavaSamplerContext context) {
        super.teardownTest(context);
    }

    @Override
    public SampleResult runTest(JavaSamplerContext context) {
        SampleResult sampleResult = new SampleResult();
        sampleResult.sampleStart();
        try {
            int logLoop = context.getIntParameter("logLoop");
            // 每一轮打印日志数约为10条
            for(int i=0; i < logLoop; i++) {
                orderService.testOrderLog(i);
                inventoryService.testInventoryLog(i);
            }
            sampleResult.setSuccessful(true);
        } catch (Exception e) {
            e.printStackTrace();
            sampleResult.setSuccessful(false);
        }
        sampleResult.sampleEnd();
        return sampleResult;
    }

}
