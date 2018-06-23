import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.*;

public class Solver {

	public static List<Integer> positions = new ArrayList<>();

	public static int nearst(List<Integer> currentPosition) {
		Integer current = currentPosition.get(currentPosition.size()-1);
		// for (Integer i: currentPosition) {
		// 	positions.add(i);
		// }
		List<Integer> positionList = new ArrayList<>(positions);

		// List<Integer> list = positions.stream().distinct().collect(Collectors.toList());
		positionList.removeAll(currentPosition);
		// System.out.println(list);
		int min = 500;
		int nextPosition = 0;
		for (Integer position: positionList) {
			int distance = TSP2D.distance(current.intValue(), position.intValue());
			if (min >= distance) {
				min = distance;
				nextPosition = position.intValue();
				// System.out.println(nextPosition);
			}
		}

		return nextPosition;
	}

	public static void set(int range) {
		for(int i = 0; i < range; i++) {
			positions.add(i, i);
		}
	}

	public static void answer() {
		int num = Map.p.length;
		set(num);
		int[] x = new int[num];
		List<Integer> currentPatern = new ArrayList<>();
		currentPatern.add(0);

		for (int i = 1; i < num; i++) {
			currentPatern.add(nearst(currentPatern));
		}

		Integer[] order = new Integer[num-1];

		// Mapの数分forを回す
		// 最短の場所を求める

		order = (Integer[])currentPatern.toArray(new Integer[currentPatern.size()]);

		for (int i=0; i<num; i++) {
			x[i] = order[i];
		}

		TSP2D.submit(x);
	}
}


