import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 3, 2, 7, 6, 8};
        System.out.println(Arrays.toString(arr));
//        bubble(arr);
//        bubble2(arr);
//        bubble3(arr);
//        bubble4(arr);
//        bubble5(arr);
//        bubble6(arr);
//        bubble7(arr);
//        bubble8(arr);
//        bubble9(arr);

//        insert1(arr);
//        insert2(arr);

//        bubble10(arr);
//        insert3(arr);
//        bubble11(arr);
//        insert4(arr);

//        bubble12(arr);
//        insert5(arr);
//        bubble13(arr);
//        insert6(arr);
//        bubble14(arr);
//        bubble15(arr);
//        insert7(arr);
//        bubble16(arr);

//        merge(arr, 0, arr.length - 1);


        merge2(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void merge2(int[] arr, int left, int right) {
        int mid;

        if (left < right) {
            mid = (left + right) / 2;

            merge2(arr, left, mid);
            merge2(arr, mid + 1, right);

            combine2(arr, left, mid, right);
        }
    }

    private static void combine2(int[] arr, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        int[] copyArr = new int[arr.length];

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                copyArr[k++] = arr[i++];
            } else {
                copyArr[k++] = arr[j++];
            }
        }

        if (i > mid) {
            for (int l = j; l <= right; l++) {
                copyArr[k++] = arr[l];
            }
        } else {
            for (int l = i; l <= mid; l++) {
                copyArr[k++] = arr[l];
            }
        }

        for (int l = left; l <= right; l++) {
            arr[l] = copyArr[l];
        }
    }

    public static void merge(int[] arr, int left, int right) {
        int mid;

        if (left < right) {
            mid = (left + right) / 2;
            merge(arr, left, mid);
            merge(arr, mid + 1, right);

            combine(arr, left, mid, right);
        }
    }

    private static void combine(int[] arr, int left, int mid, int right) {
        int[] copyArr = new int[arr.length];

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                copyArr[k++] = arr[i++];
            } else {
                copyArr[k++] = arr[j++];
            }
        }

        if (i > mid) {
            for (int l = j; l <= right; l++) {
                copyArr[k++] = arr[l];
            }
        } else {
            for (int l = i; l <= mid; l++) {
                copyArr[k++] = arr[l];
            }
        }

        for (int l = left; l <= right; l++) {
            arr[l] = copyArr[l];
        }
    }

    public static void bubble16(int[] arr) {
        int size = arr.length;
        int temp = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void insert6(int[] arr) {
        int size = arr.length;
        for (int i = 1; i < size; i++) {
            int temp = arr[i];
            int prevIndex = i - 1;

            while (prevIndex >= 0
                && arr[prevIndex] > temp) {
                arr[prevIndex + 1] = arr[prevIndex];
                prevIndex--;
            }

            arr[prevIndex + 1] = temp;
        }
    }

    public static void insert7(int[] arr) {
        int size = arr.length;
        for (int i = 1; i < size; i++) {
            int temp = arr[i];
            int prevIndex = i - 1;

            while (prevIndex >= 0
            && arr[prevIndex] > temp) {
                arr[prevIndex + 1] = arr[prevIndex];
                prevIndex--;
            }

            arr[prevIndex + 1] = temp;
        }
    }

    public static void bubble15(int[] arr) {
        int size = arr.length;
        int temp = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void bubble14(int[] arr) {
        int size = arr.length;
        int temp = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    


    public static void bubble13(int[] arr) {
        int size = arr.length;
        int temp = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void insert5(int[] arr) {
        int size = arr.length;

        for (int i = 0; i < size; i++) {
            int temp = arr[i];
            int prevIndex = i - 1;
            while (prevIndex >= 0
            && arr[prevIndex] > temp) {
                arr[prevIndex + 1] = arr[prevIndex];
                prevIndex--;
            }
            arr[prevIndex + 1] = temp;
        }
    }

    public static void bubble12(int[] arr) {
        int size = arr.length;
        int temp = 0;

        for (int i = 0; i < size; i++) {
            for (int j  = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void insert4(int[] arr) {

        int size = arr.length;

        for (int i = 1; i < size; i++) {
            int temp = arr[i];
            int prevIndex = i - 1;

            while (prevIndex >= 0
            && arr[prevIndex] > temp) {
                arr[prevIndex + 1] = arr[prevIndex];
                prevIndex--;
            }

            arr[prevIndex + 1] = temp;
        }
    }

    public static void bubble11(int[] arr) {
        int size = arr.length;
        int temp = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void insert3(int[] arr) {
        int size = arr.length;
        for (int i = 1; i < size; i++) {
            int temp = arr[i];
            int prevIndex = i - 1;

            while (prevIndex >= 0
            && arr[prevIndex] > temp) {
                arr[prevIndex + 1] = arr[prevIndex];
                prevIndex--;
            }
            arr[prevIndex + 1] = temp;
        }
    }

    public static void bubble10(int[] arr) {
        int size = arr.length;
        int temp = 0;

        for (int i = 0; i < size; i++) {
            for (int  j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void insert2(int[] arr) {
        int size = arr.length;

        for (int i = 1; i < size; i++) {
            int temp = arr[i];
            int prevIndex = i - 1;

            while (prevIndex >= 0
            && arr[prevIndex] > temp) {
                arr[prevIndex + 1] = arr[prevIndex];
                prevIndex--;
            }

            arr[prevIndex + 1] = temp;
        }

    }

    public static void insert1(int[] arr) {
        int size = arr.length;
        for (int i = 1; i < size; i++) {
            int temp = arr[i];
            int prevIndex = i - 1;
            while (prevIndex >= 0
            && arr[prevIndex] > temp) {
                arr[prevIndex + 1] = arr[prevIndex];
                prevIndex--;
            }

            arr[prevIndex + 1] = temp;
        }
    }

    public static void bubble9(int[] arr) {
        int temp = 0;
        int size = arr.length;

        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void bubble8(int[] arr) {
        int temp = 0;
        int size = arr.length;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void bubble7(int[] arr) {
        int temp = 0;
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

    }

    public static void bubble6(int[] arr) {
        int temp = 0;
        int size = arr.length;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void bubble5(int[] arr) {
        int temp = 0;
        int size = arr.length;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


    public static void bubble4(int[] arr) {
        int temp = 0;
        int size = arr.length;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i -1 ; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void bubble3(int[] arr) {
        int temp = 0;
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size -i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void bubble2(int[] arr) {
        int temp = 0;
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            System.out.println(arr[i]);
        }

    }

    public static void bubble(int[] arr) {
        int temp = 0;
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            System.out.println(arr[i]);
        }
    }
}
