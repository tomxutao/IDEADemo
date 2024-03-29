import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

public class Test3 {

    public static void main(String[] args) {

        AA a1 = new AA();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("1", "1");
        hashMap.put("2", 2);
        hashMap.put("3", a1);

        HashMap<String, Object> hashMap2 = new HashMap<>();
        hashMap2.put("4", "4");
        hashMap2.put("5", 5);
        hashMap2.put("6", hashMap);

        HashMap<String, Object> hashMap_clone = (HashMap<String, Object>) hashMap2.clone();

        System.out.println("1");

        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();

            MethodType mt = MethodType.methodType(String.class, char.class, char.class);
            MethodHandle replaceMH = publicLookup.findVirtual(String.class, "replace", mt);
            String output = (String) replaceMH.invoke("jovo", Character.valueOf('o'), 'a');

            MethodType mt1 = MethodType.methodType(List.class, Object[].class);
            MethodHandle asList = publicLookup.findStatic(Arrays.class, "asList", mt1);
            List<Integer> list = (List<Integer>) asList.invokeWithArguments(1,2,"3");

            MethodType mt2 = MethodType.methodType(int.class, int.class, int.class);
            MethodHandle sumMH = lookup.findStatic(Integer.class, "sum", mt2);
            int sum = (int) sumMH.invokeExact(1, 11);

            System.out.println(sum);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private static class AA {

    }

//    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        List<Callable<Integer>> tasks = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            int finalI = i;
//            tasks.add(() -> {
//                int second = 10 - finalI;
//                Thread.sleep(second * 1000);
//                System.out.println("" + Thread.currentThread().getName() + ", second: " + second);
//                return second;
//            });
//        }
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//
//        CompletionService<Integer> completionService = new ExecutorCompletionService(executorService);
//        tasks.forEach(task -> completionService.submit(task));
//
//        for (int i = 0; i < tasks.size(); i++) {
//            System.out.println("submit start " + i);
//            System.out.println(completionService.take().get());
//            System.out.println("submit end " + i);
//        }
//        System.out.println("submit over");
//
//        executorService.shutdown();
//    }
}
