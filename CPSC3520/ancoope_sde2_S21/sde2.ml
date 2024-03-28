(* CODE FOR FIRST_DUPLICATE *)

let rec first_duplicate = function(list) ->
  if list = [] then (* if list is empty *)
    -10000
  else
    (*is the element in the list *)
    if(List.mem (List.hd list)(List.tl list)) then
      List.hd list
    else
      first_duplicate (List.tl list);;


(* CODE FOR FIRST_NONREPEATING *)

(* helper function for first_nonrepeating *)
let rec nonrepeat_helper = function(prelist,element,postlist) ->
  if postlist = [] then
    -10000

  else
    (* look if element is in the front of the list *)
    (* if it is recurse *)
    if (List.mem (element) (prelist)) then
      nonrepeat_helper (element::prelist, (List.hd postlist), (List.tl postlist))

      (* look if element is in the back of the list *)
      (* if it is move on to next element/recurse *)
    else
      if (List.mem (element) (postlist)) then
        nonrepeat_helper (element::prelist, (List.hd postlist), (List.tl postlist))

      (* if element is not in front or back of list *)
      (* not a duplicate, return element *)
      else
        element;;

(* first_nonrepeating main function *)
let rec first_nonrepeating = function(mainlist) ->

  if mainlist = [] then
    -10000
  else
    (* does list contain element twice *)
    (* if the answer is no/false *)
    (* return that element *)
    nonrepeat_helper([], (List.hd mainlist), (List.tl mainlist));;


(* CODE FOR SUMOFTWO PROBLEM *)


(* sumOfTwo helper function *)
let rec sumHelper = function(x,list2,z) ->
  if list2 = [] then
    false
  else
    (* does x plus the first number in list2 equal z, the target value *)
    if (x + (List.hd list2) = z) then
      true
    else
      (* recurse to next number in list2 *)
      if (sumHelper (x, (List.tl list2),z)) then
        true
      else
        (* no numbers in list2 add with x to equal z *)
        false;;

(* sumOfTwo main function *)
let rec sumOfTwo = function(list1, list2, x) ->
  if list1 = [] then
    false

  else
    if list2 = [] then
      false
    else
      (* does the head of list1 work with any elements in list2 *)
      if (sumHelper ((List.hd list1),list2,x)) then
        true
      else
        (* recurse to next element in list1 *)
        if (sumOfTwo ((List.tl list1),list2,x)) then
          true
        (* no numbers together in list1 or list2 equal x *)
        else
          false;;


(* CODE FOR CYK INSPIRED PROBLEM *)

(* helper function that makes an ascending list from i to j *)
let rec listMaker = function (i, j) ->
  if (i > j) then
    []
  else
    (i) :: listMaker(i+1,j);;

(* helper function that makes a descending list from i to j *)
let rec revListMaker = function (i, j) ->
  if (i < j) then
    []
  else
    (i)::revListMaker(i-1,j);;

(* helper function that puts elements from two lists into tuples in one list *)
let rec helperCYKSub = function(list1, list2) ->
  if list1 = [] then
    []
  else
    ((List.hd list1), (List.hd list2)):: helperCYKSub((List.tl list1), (List.tl list2));;


(* cyk_sublists main function *)
let cyk_sublists = function(x) ->

  helperCYKSub(listMaker(1, x-1), revListMaker(x-1, 1));;
