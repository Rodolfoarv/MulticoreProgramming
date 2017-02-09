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
		
		System.out.println(st3.count());
	}
	
}
