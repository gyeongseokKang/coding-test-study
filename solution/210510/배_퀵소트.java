import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {
    public static List<Integer> quickSort(List<Integer> list) {
        if (list.size() <= 1) return list;
        int pivot = list.get(list.size() / 2);

        List<Integer> lesserArr = new LinkedList<>();
        List<Integer> equalArr = new LinkedList<>();
        List<Integer> greaterArr = new LinkedList<>();

        for (int num : list) {
            if (num < pivot) lesserArr.add(num);
            else if (num > pivot) greaterArr.add(num);
            else equalArr.add(num);
        }

        return Stream.of(quickSort(lesserArr), equalArr, quickSort(greaterArr))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

 
	public static void main(String[] args) {
		int[] arrs = { 69, 10, 30, 2,3,16, 16, 8, 31, 22 };
		List<Integer> lists = quickSort(Arrays.stream(arrs).boxed().collect(Collectors.toList()));
		System.out.println("결과");
		
		lists.stream().forEach(s -> System.out.print(s+","));
	}


}

