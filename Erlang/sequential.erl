-module(sequential).
-compile(export_all).

fill(B) when byte_size(B) >= 4 -> binary_to_list(B);
fill(B) -> fill(list_to_binary([$0, B])).

bcd(0) -> ["0000"];
bcd(N) when N < 10 -> [fill(integer_to_binary(N, 2))];
bcd(N) -> bcd(N div 10) ++ [fill(integer_to_binary(N rem 10, 2))].

% We will calculate the prime_factors with the sieve of Erastothenes

range(X, X) -> [X];
range(X, Y) -> [X | range(X + 1, Y)].

sieve_E([X]) -> [X];
sieve_E([H | T]) -> [H | sieve_E(remove([H * X || X <-[H | T]], T))].

remove(_, []) -> [];
remove([H | X], [H | Y]) -> remove(X, Y);
remove(X, [H | Y]) -> [H | remove(X, Y)].

prime_factors(1) -> [];
prime_factors(N) -> factors(N, primes(N)).

factors(1, _) -> [];
factors(N, [P | T]) when N rem P /= 0 -> factors(N, T);
factors(N, [P | T]) -> [P | factors(N div P, [P | T])].

primes(X) -> sieve_E(range(2, X)).
primes(X, Y) -> remove(primes(X), primes(Y)).
