import java.util.*;

public class Solver {

	public static List<Integer> positions = new ArrayList<>();
	public static List<Integer> yakusokus = new ArrayList<>();
	public static List<Integer> yakusokuPositions = new ArrayList<>();

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
		if (yakusokus.contains(currentPosition.size())) {
			return Map.yakusoku[yakusokus.indexOf(currentPosition.size())][0];
		} else {
			return nextPosition;
		}
	}

	public static void set(int range) {
		int yakusokuSize = Map.yakusoku.length;
		for (int j = 0; j < yakusokuSize; j++) {
			yakusokuPositions.add(j, Map.yakusoku[j][0]);
			yakusokus.add(j, Map.yakusoku[j][1]);
		}

		for (int i = 0; i < range; i++) {
			if (!(yakusokuPositions.contains(i))) {
				positions.add(i);
			}
		}
	}

	static int num = Map.p.length;
	static int[] x = new int[num];
	static Integer[] order = new Integer[num-1];

	public static void answer() {
		int num = Map.p.length;
		set(num);
		int[] x = new int[num];
		List<Integer> currentPatern = new ArrayList<>();
		currentPatern.add(0);
		for (int i = 0; i < num -1; i++) {
			currentPatern.add(nearst(currentPatern)); 
		}

		Integer[] order = new Integer[num-1];

		order = (Integer[])currentPatern.toArray(new Integer[currentPatern.size()]);
		for (int i = 0; i < num; i++) {
			x[i] = order[i];
		}

		TSP2D.submit(x);
	}
}


