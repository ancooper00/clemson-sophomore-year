(*
Prototype:
first_duplicate of a list returns -10000 if there are no duplicates in the integer list argument.
Otherwise the first item that occurs more than once (duplicate) in the integer list is returned.

Signature:
val first_duplicate : int list -> int = <fun>

Sample Use:
# first_duplicate [1;2;3;4;5;6;7;4;5;8;9];; : int = 4
# first_duplicate [1;2;3;4;5;6;7;4;5;2;9];; : int = 2
# first_duplicate [1;2;3;4;5;6;7;8;9;10];; : int = -10000
*)

let rec first_duplicate = function(list) ->
  if list = [] then (* if list is empty *)
    -10000
  else
    if(List.mem (List.hd list)(List.tl list)) then
      List.hd list
    else
      first_duplicate (List.tl list);;
