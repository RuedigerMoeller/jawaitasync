package samples;

import jawaitasync.Promise;

import static jawaitasync.Promise.await;
import static jawaitasync.Promise.complete;
import static jawaitasync.PromiseTools.sleepAsync;

public class CompositionExample {
	public Promise testAsync() {
		System.out.print("{1}");
		await(sleepAsync(1000));
		System.out.print("{2}");
		await(test2Async());
		return complete(null);
	}

	public Promise test2Async() {
		System.out.print("{3}");
		await(sleepAsync(1000));
		return complete(null);
	}
}
