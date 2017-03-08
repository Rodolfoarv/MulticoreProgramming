-module(ejem1).
-compile(export_all). % This command exports all the functions

pow(_B,0) -> 1; % entre clausulas entonces es ;, pero cuando acaban todas las clausulas las termino con punto
pow(B,E) when E > 0 -> B * pow(B,E-1). %The underscore is to ignore the variable

%Returns a list with duplicate elements
dup([]) -> [];
dup([H|T]) -> [H, H | dup(T)].

% add1([])    ->   [];
% add1(H|T)   ->   [H + 1 | add1(T)].

countdown(0) -> [];
countdown(N) when N>0 -> [N | countdown(N-1)].

log2(1) -> 0;
log2(N) when N > 1 -> 1 + log2(N div 2).
