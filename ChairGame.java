class ChairGame {
	/*
	 * You are in a room with a circle of 100 chairs. The chairs are numbered
	 * sequentially from 1 to 100.
	 * At some point in time, the person in chair #1 will be asked to leave.
	 * The person in chair #2 will be skipped, and the person in chair #3 will
	 * be asked to leave. This pattern of skipping one person and asking the
	 * next to leave will keep going around the circle until there is one
	 * person left… the survivor.
	 * Write a program to determine which chair the survivor is sitting in.
	 */

	// the pattern is (n - 2^(ceiling(log[2](n)-1)) * 2
	public static int findLastChair(int numberOfChairs) {
		// bitmath provides closest power of two less than the number
		// in this case we want exact matches to return one less 
		// as in f(4)->1, f(8)->2, f(16)->3
		int closestPowerLessThan = Integer.highestOneBit(numberOfChairs - 1);

		return (numberOfChairs - closestPowerLessThan)*2;
	}

	public static void main(String[] args) {
		// check for args
		if (args.length == 0) {
			System.out.println("Usage: java CalculateLastChair [(positive int > 1)]\nRunning Test ...\n");
			ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
			TestApp.runTest();
			return;
		}

		String input = args[0];
		int num;

		// make sure the arg is a number > 1
		try {
			num = Integer.valueOf(input);
			if (num < 2) throw new NumberFormatException();
		}
		catch (NumberFormatException e) {
			System.out.printf("Argument must be an int > 1. You passed %s\n",input);
			return;
		}

		System.out.printf("The last person is in chair: %d\n", findLastChair(num));
		return;
	}

}

class TestApp {
	public static void runTest() {
		int[] testValues = {2,4,8,16,32,64,128};
		for(int i : testValues) {
			int result = ChairGame.findLastChair(i);
			assert result == i : "Numbers don't match";
			System.out.printf("with %d chairs the result is %d\n",i,result);
		}
	}
}
