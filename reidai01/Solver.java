import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.*;

public class Solver {

	public static List<Integer> positions = new ArrayList<>();
	public static List<Integer> nearList1 = new ArrayList<>();
	public static List<Integer> nearList2 = new ArrayList<>();
	public static List<Integer> nearList3 = new ArrayList<>();

	public static int threePickedUp(int dis, int first, List<Integer> currentPositions) {
		currentPositions.add(first);
		List<Integer> positionList = new ArrayList<>(positions);
		positionList.removeAll(currentPositions);
		int min = 500;
		int nextPosition = 0;
		for (Integer position: positionList) {
			int distance = TSP2D.distance(first, position.intValue());
			if (distance != 0) {
				if (min >= distance) {
					min = distance;
					nextPosition = position.intValue();
				}
			} 
		}
		currentPositions.add(nextPosition);
		positionList.removeAll(currentPositions);

		for (Integer position: positionList) {
			int distance = TSP2D.distance(nextPosition, position.intValue());
			if (distance != 0) {
				if (min >= distance) {
					min += distance;
					nextPosition = position.intValue();
				}
			} 
		}
		return min + dis;
	}

	public static int nearst(List<Integer> currentPosition) {
		// 近いやつ3つをピックアップする。
		Integer current = currentPosition.get(currentPosition.size()-1);
		List<Integer> positionList = new ArrayList<>(positions);
		HashMap<Integer, Integer> map = new HashMap<>();

		positionList.removeAll(currentPosition);
		int min = 500;
		int nextPosition = 0;
		for (Integer position: positionList) {
			int distance = TSP2D.distance(current.intValue(), position.intValue());
			if (distance != 0) {
				Integer distan = Integer.valueOf(distance);
				map.put(distan, position);
			}
		}

		Object[] mapkey = map.keySet().toArray();
		Arrays.sort(mapkey);
		int count = 0;
		int threeDistance = 50000;
		for (Integer key: map.keySet()) {
			if (count == 3) {
				break;
			}
			// System.out.println(key);
			int posi = map.get(key).intValue();
			int threeDis = threePickedUp(key, posi, positionList);

			if (threeDistance > threeDis) {
				System.out.println(key);
				nextPosition = posi;
				threeDistance = threeDis;
			}
			count++;
		}

		return nextPosition;
	}

	public static void set(int range) {
		// for initilize 
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
			int posi = nearst(currentPatern);
			System.out.println(posi);
			currentPatern.add(posi);
		}

		Integer[] order = new Integer[num-1];

		order = (Integer[])currentPatern.toArray(new Integer[currentPatern.size()]);

		for (int i=0; i<num; i++) {
			x[i] = order[i];
		}

		TSP2D.submit(x);
	}
}


