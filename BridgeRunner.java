/**
 * Runs all threads
 */

public class BridgeRunner {

	public static void main(String[] args) {

		// TODO - check command line inputs
		if (args.length < 3){
			System.out.println("Not enough arguments, Usage: BridgeRunner <bridge limit> <num cars>");
		
			//TODO throw exception and give usage
		}else{
		if (Integer.parseInt(args[2])){

		}

		OneLaneBridge bridge = new OneLaneBridge(0);
		// TODO - instantiate the bridge
		
		// TODO - allocate space for threads

		// TODO - start then join the threads

		System.out.println("All cars have crossed!!");
		}
	}

}