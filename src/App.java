import java.util.Collection;
import java.util.Vector;

public class App {
    public static void main(String[] args) throws Exception {
        long v_startTime = System.currentTimeMillis();
        Integer v_numThreads = 12;

        Integer v_start = 1;
        Integer v_end = 10000;

        Collection<Integer> v_multipliesOfThree = new Vector<>();
        Collection<CountMultipliesOfThree> v_collectionOfThreads = new Vector<>();

        Integer v_job = v_end / v_start;

        for (int i = 1; i <= v_numThreads; i++) {
            Integer trab = Math.round(v_job / v_numThreads);
            int v_endToThread = trab * i;
            int v_startToThread = (v_endToThread - trab) + 1;
            CountMultipliesOfThree v_thread = new CountMultipliesOfThree(v_startToThread, v_endToThread, v_multipliesOfThree);
            v_thread.setName("Thread "+ i);
            v_thread.start();
            v_collectionOfThreads.add(v_thread);
        }

        for(CountMultipliesOfThree v_thread : v_collectionOfThreads)
        {
            try {
                v_thread.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                return;
            }
        }

        //ArrayList<Integer> v_multipliesOfThreeOrdering = new ArrayList<>(v_multipliesOfThree);
        //Collections.sort(v_multipliesOfThreeOrdering);

        //Choose the correct list to exercise
        Long v_sumOfNumbers = (long)0;
        System.out.println("###############");
        for (Integer v_number : v_multipliesOfThree) {
            System.out.println(v_number);
            v_sumOfNumbers += v_number;
        }
        System.out.println("Quantidade de números: " + v_multipliesOfThree.size());
        System.out.println("Somatório dos valores: " + v_sumOfNumbers);
        System.out.println("###############");
        long v_endTime = System.currentTimeMillis();
        System.out.println("O programa demorou:" + (v_endTime - v_startTime) + " milisegundos");
    }
}

class CountMultipliesOfThree extends Thread {

    private Integer v_start;
    private Integer v_end;
    private Collection<Integer> v_numbers;
    
    public CountMultipliesOfThree(Integer p_start, Integer p_end, Collection<Integer> p_numbers) {
        v_start = p_start;
        v_end = p_end;
        v_numbers = p_numbers;
    }

    @Override
    public void run() {
        for(Integer i = v_start; i <=v_end; i++)
        {
            if(i % 3 == 0)
            {
                v_numbers.add(i);
            }
        }
    }
}
