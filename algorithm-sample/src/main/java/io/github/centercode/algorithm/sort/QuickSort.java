package io.github.centercode.algorithm.sort;

public class QuickSort {

    void sort(int[] arr) {
        sortSolution2(arr, 0, arr.length - 1);
    }

    void sortSolution1(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            sortSolution1(arr, low, pivot - 1);
            sortSolution1(arr, pivot + 1, high);
        }
    }

    /**
     * 尾递归优化版
     */
    void sortSolution2(int[] arr, int low, int high) {
        while (low < high) {
            int pivot = partition(arr, low, high);
            // 对两个子数组中较短的那个执行快排
            if (pivot - low < high - pivot) {
                // 递归排序左子数组
                sortSolution2(arr, low, pivot - 1);
                // 剩余未排序区间为 [pivot + 1, high]
                low = pivot + 1;
            } else {
                // 递归排序右子数组
                sortSolution2(arr, pivot + 1, high);
                // 剩余未排序区间为 [low, pivot - 1]
                high = pivot - 1;
            }
        }
    }

    int partition(int[] arr, int low, int high) {
        return partitionSolution1(arr, low, high);
    }

    int partitionSolution1(int[] arr, int low, int high) {
        int pivot = arr[low];
        while (low < high) {
            while (low < high && pivot <= arr[high]) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }

    int partitionSolution2(int[] nums, int left, int right) {
        int i = left, j = right;
        while (i < j) {
            while (i < j && nums[j] >= nums[left]) {
                j--;
            }
            while (i < j && nums[i] <= nums[left]) {
                i++;
            }
            swap(nums, i, j);
        }
        swap(nums, left, i);
        return i;
    }

    /**
     * 三数取中值优化版
     */
    int partitionSolution3(int[] nums, int left, int right) {
        // 选取三个候选元素的中位数
        int mid = medianThree(nums, left, right);
        // 将中位数交换至数组最左端
        swap(nums, left, mid);
        int i = left, j = right;
        while (i < j) {
            while (i < j && nums[j] >= nums[left]) {
                j--;
            }
            while (i < j && nums[i] <= nums[left]) {
                i++;
            }
            swap(nums, i, j);
        }
        swap(nums, left, i);
        return i;
    }

    /**
     * 三数取中值
     */
    int medianThree(int[] nums, int left, int right) {
        int mid = (left + right) / 2;
        if ((nums[left] < nums[mid]) ^ (nums[left] < nums[right])) {
            return left;
        } else if ((nums[mid] < nums[left]) ^ (nums[mid] < nums[right])) {
            return mid;
        } else {
            return right;
        }
    }

    void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
