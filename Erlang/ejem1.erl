-module(ejem1).
-compile(export_all). % This command exports all the functions

pow(_B,0) -> 1; % entre clausulas entonces es ;, pero cuando acaban todas las clausulas las termino con punto
pow(B,E) when E > 0 -> B * pow(B,E-1). %The underscore is to ignore the variable
