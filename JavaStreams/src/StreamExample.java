import java.util.Optional;
import java.util.stream.Stream;

public class StreamExample {
	
	
	
	public static void main(String[] args) {
		Stream<String> st = Stream.of("Januar", "Februar", "Marz", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober",
				"November", "Dezember");
		
		Stream<String> st2 = Stream.of("Apfel", "Guave", "Mango", "Banane", "Melone", "Wassermelonen");
		
		Stream<String> st3 = Stream.concat(st, st2);
		
//		st3.forEach(System.out::println);
//		
//		st3.forEach((str) -> {
//			System.out.println(str);
//		});
		
//		System.out.println(st3.count());
//		st3.map(String::toUpperCase).forEach(System.out::println);
		
		
//		Optional<String> r = st3.max((a,b) -> a.length() - b.length());
//		if(r.isPresent()){
//			System.out.println(r.get());
//		}else{
//			System.out.println("There is nothing");
//		}
		
//		int r = st3.mapToInt(String::length).sum();
		int r = st3.reduce(0, (accum, x) -> accum + x.length(),(x,y) -> x+y);
		System.out.println(r);
		
		
	}
	
}
