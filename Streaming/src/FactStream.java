import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

public class FactStream {
	
	
	public static void main(String[] args) {
		final int n = 250_000;
		
		Instant start = Instant.now();
		
		
		BigInteger r = IntStream.range(2, n)
				.parallel()
		.mapToObj(BigInteger::valueOf)
		.reduce(BigInteger.ONE, (x,y) -> x.multiply(y));
		
		Instant end = Instant.now();
		Duration delta = Duration.between(start, end);
		
		System.out.println(delta);
		
		System.out.println(r.bitCount());
	}
}
