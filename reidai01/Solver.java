import java.util.*;

public class Solver {

	public static List<Integer> positions = new ArrayList<>();

	public static int nearst(List<Integer> currentPosition) {
		Integer current = currentPosition.get(currentPosition.size()-1);
		List<Integer> positionList = new ArrayList<>(positions);
		positionList.removeAll(currentPosition);
		int min = 500;
		int nextPosition = 0;
		for (Integer position: positionList) {
			int distance = TSP2D.distance(current.intValue(), position.intValue());
			if (min >= distance) {
				min = distance;
				nextPosition = position.intValue();
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

		order = (Integer[])currentPatern.toArray(new Integer[currentPatern.size()]);

		for (int i=0; i<num; i++) {
			x[i] = order[i];
		}

		TSP2D.submit(x);
	}
}


