import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class Solver {

	public static void answer() {

		int num = Map.p.length;
		int[] x = new int[num];
		Integer[] order = new Integer[num-1];

		for (int i=1; i<num; i++) {
			order[i-1] = i;
		}

        List<Integer> list = Arrays.asList(order);
        Collections.shuffle(list);
		order = (Integer[])list.toArray(new Integer[list.size()]);

		x[0] = 0;
		for (int i=1; i<num; i++) {
			x[i] = order[i-1];
		}

		TSP2D.submit(x);
	}
}


