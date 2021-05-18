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
    
    public static int[] mergeSort(int[] arr) {
        if (arr.length < 2) return arr;

        int mid = arr.length / 2;
        int[] low_arr = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        int[] high_arr = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));

        int[] mergedArr = new int[arr.length];
        int m = 0, l = 0, h = 0;
        while (l < low_arr.length && h < high_arr.length) {
            if (low_arr[l] < high_arr[h])
                mergedArr[m++] = low_arr[l++];
            else
                mergedArr[m++] = high_arr[h++];
        }
        while (l < low_arr.length) {
            mergedArr[m++] = low_arr[l++];
        }
        while (h < high_arr.length) {
            mergedArr[m++] = high_arr[h++];
        }
        return mergedArr;
    }
 
	public static void main(String[] args) {
		int[] arrs = { 69, 10, 30, 2,3,16, 16, 8, 31, 22 };
		List<Integer> lists = quickSort(Arrays.stream(arrs).boxed().collect(Collectors.toList()));
		System.out.println("퀵 소트 결과");
		
		lists.stream().forEach(s -> System.out.print(s+","));
		int[] MergeArr = mergeSort(arrs);
		System.out.println();
		System.out.println("머지 소트 결과");
		Arrays.stream(MergeArr).forEach(s -> System.out.print(s+","));
		
	}


}

