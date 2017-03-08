fill(B) when byte_size(B) >= 4 -> binary_to_list(B);
fill(B) -> fill(list_to_binary([$0, B])).

bcd(0) -> ["0000"];
bcd(N) when N < 10 -> [fill(integer_to_binary(N, 2))];
bcd(N) -> bcd(N div 10) ++ [fill(integer_to_binary(N rem 10, 2))].

prime_factors(1) -> [];
prime_factors(N) -> factors(N, primes(N)).

factors(1, _) -> [];
factors(N, [P | T]) when N rem P /= 0 -> factors(N, T);
factors(N, [P | T]) -> [P | factors(N div P, [P | T])].

primes(X) -> sieve(range(2, X)).
primes(X, Y) -> remove(primes(X), primes(Y)).
