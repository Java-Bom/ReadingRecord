package chap9;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@State(Scope.Thread) //쓰레드별로 인스턴스 생성
public class SumLoopBenchmark {

    @Benchmark
    @BenchmarkMode(Mode.Throughput) // 작업에 걸리는 총 시간
    @OutputTimeUnit(TimeUnit.SECONDS) // 측정에 사용할 시간 단위
    public void sumForLoop(Blackhole blackhole) {
        long sum = 0l;

        for (int i = 0; i < 10000; i++) {
            sum += i;
        }

        blackhole.consume(sum); // 계산 결과값을 따로 반환하거나 소비하지 않으면 무시될 수 있기때문에 해당 메소드를 통해 소비
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void sumIntStream(Blackhole blackhole) {
        long sum = IntStream.range(0, 10000)
                .sum();

        blackhole.consume(sum);
    }
}
