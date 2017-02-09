import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

public class PiStream {
	
	private static long num_rects = 1000000000;
	private static double width = 1.0 / (double) num_rects;
	
	private static double doit(long i){
		double mid = (i+0.5) * width;
		double height = 4.0 / (1.0 + mid * mid);
		return height;
	}
	
	public static void main(String[] args) {
		
		Instant start = Instant.now();
		
		double area = 
				LongStream.range(0, num_rects)
					.mapToDouble(PiStream::doit)
						.sum() * width;
		
		Instant end = Instant.now();
		Duration delta = Duration.between(start, end);
		System.out.println(delta);
		
		System.out.println(area);
		
		
		
	}
}
