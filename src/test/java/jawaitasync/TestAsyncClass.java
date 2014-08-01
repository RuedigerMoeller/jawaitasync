package jawaitasync;

import jawaitasync.loop.EventLoopHolder;
import jawaitasync.loop.MockedEventLoop;
import jawaitasync.processor.AsmProcessorLoader;
import org.junit.Assert;

public class TestAsyncClass {
	static public void assertCallAsyncMethod(String expectedOutput, String className, String methodName) throws Exception {
		EventLoopHolder.instance = new MockedEventLoop();

		ClassLoader loader = new AsmProcessorLoader(ClassLoader.getSystemClassLoader());
		Class clazz = loader.loadClass(className);

		Assert.assertEquals(expectedOutput, OutUtils.captureOutput(() -> {
			ClassLoader loader2 = loader;
			try {
				Promise promise = (Promise) clazz.getMethod(methodName).invoke(clazz.newInstance());
				EventLoopHolder.instance.loop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));
	}
}