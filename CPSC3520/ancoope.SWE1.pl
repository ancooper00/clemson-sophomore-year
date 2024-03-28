%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% ECE3520/CpSc3520 SDE1: Prolog Declarative and Logic Programming

% Use the following Prolog relations as a database of familial 
% relationships for 4 generations of people.  If you find obvious
% minor errors (typos) you may correct them.  You may add additional
% data if you like but you do not have to.

% Then write Prolog rules to encode the relations listed at the bottom.
% You may create additional predicates as needed to accomplish this,
% including relations for debugging or extra relations as you desire.
% All should be included when you turn this in.  Your rules must be able
% to work on any data and across as many generations as the data specifies.
% They may not be specific to this data.

% Using SWI-Prolog, run your code, demonstrating that it works in all modes.
% Log this session and turn it in with your code in this (modified) file.
% You examples should demonstrate working across 4 generations where
% applicable.

% Fact recording Predicates:

% list of two parents, father first, then list of all children
%parent_list(?Parent_list, ?Child_list).

% Data:

parent_list([fred_smith, mary_jones],
            [tom_smith, lisa_smith, jane_smith, john_smith]).

parent_list([tom_smith, evelyn_harris],
            [mark_smith, freddy_smith, joe_smith, francis_smith]).

parent_list([mark_smith, pam_wilson],
            [martha_smith, frederick_smith]).

parent_list([freddy_smith, connie_warrick],
            [jill_smith, marcus_smith, tim_smith]).

parent_list([john_smith, layla_morris],
            [julie_smith, leslie_smith, heather_smith, zach_smith]).

parent_list([edward_thompson, susan_holt],
            [leonard_thompson, mary_thompson]).

parent_list([leonard_thompson, lisa_smith],
            [joe_thompson, catherine_thompson, john_thompson, carrie_thompson]).

parent_list([joe_thompson, lisa_houser],
            [lilly_thompson, richard_thompson, marcus_thompson]).

parent_list([john_thompson, mary_snyder],
            []).

parent_list([jeremiah_leech, sally_swithers],
            [arthur_leech]).

parent_list([arthur_leech, jane_smith],
            [timothy_leech, jack_leech, heather_leech]).

parent_list([robert_harris, julia_swift],
            [evelyn_harris, albert_harris]).

parent_list([albert_harris, margaret_little],
            [june_harris, jackie_harris, leonard_harris]).

parent_list([leonard_harris, constance_may],
            [jennifer_harris, karen_harris, kenneth_harris]).

parent_list([beau_morris, jennifer_willis],
            [layla_morris]).

parent_list([willard_louis, missy_deas],
            [jonathan_louis]).

parent_list([jonathan_louis, marsha_lang],
            [tom_louis]).

parent_list([tom_louis, catherine_thompson],
            [mary_louis, jane_louis, katie_louis]).

male_list([fred_smith, tom_smith, mark_smith, frederick_smith, freddy_smith, marcus_smith, tim_smith, joe_smith, leonard_thompson, arthur_leech, john_smith, zach_smith, edward_thompson, joe_thompson, richard_thompson, marcus_thompson, john_thompson, jeremiah_leech, timothy_leech, jack_leech, robert_harris, albert_harris, leonard_harris, kenneth_harris, beau_morris, willard_louis, jonathan_louis, tom_louis]).

female_list([mary_jones, evelyn_harris, pam_wilson, martha_smith, connie_warrick, jill_smith, francis_smith, lisa_smith, jane_smith, layla_morris, julie_smith, leslie_smith, heather_smith, susan_holt, lisa_houser, lilly_thompson, catherine_thompson, mary_snyder, carrie_thompson, mary_thompson, sally_swithers, heather_leech, margaret_little, june_harris, jackie_harris, constance_may, jennifer_harris, karen_harris, jennifer_willis, missy_deas, marsha_lang, mary_louis, jane_louis, katie_louis]).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% SWE1 Assignment - Create rules for:

% parent(?Parent, ?Child).

parent(P, C) :- parent_list(PL, CL), member(P, PL), member(C, CL).


% married(?Husband, ?Wife).

married(H, W) :- parent_list([H|[W|_]],_).


% ancestor with generational distance rule
% ancestor_d(?Ancestor, ?Person, ?Distance).

ancestor_d(A, P, D) :- parent(A, P), D is 1.
ancestor_d(A, P, D) :- parent(A, X), ancestor_d(X, P, D2), D is D2+1 .


% ancestor(?Ancestor, ?Person).

ancestor(A, P) :- ancestor_d(A, P, _).


% descendent(?Descendent, ?Person).

descendent(D, P) :- ancestor(P, D).


% common_ancestor(?Person1, ?Person2, ?Ancestor).

common_ancestor(P1, P2, A) :- ancestor(A, P1), ancestor(A, P2).


% blood(?Person1, ?Person2). %% blood relative

blood(P1, P2) :- descendent(P1, P2); descendent(P2, P1); (ancestor(X, P1), ancestor(X, P2)).


% sibling(?Person1, Person2).

sibling(P1, P2) :- parent(A, P1), !, parent(A, P2), not(P1=P2).


% father(?Father, ?Child).

father(F, C) :- parent(F, C), parent_list([F|_],_).


% mother(?Mother, ?Child).

mother(M, C) :- parent(M, C), parent_list([_|[M|_]],_).


% uncle(?Uncle, ?Person). %% 

uncle(U, P) :- male_list(ML),
               member(U, ML),
               ( (parent(X,P), sibling(U, X)) ; (parent(X,P), sibling(X,Y), married(U, Y)) ).

% aunt(?Aunt, ?Person). %% 

aunt(A, P) :- female_list(FL),
              member(A, FL),
              ( (parent(X,P), sibling(A, X)) ; (parent(X,P), sibling(X,Y), married(Y, A)) ).



% Least Common Ancestor lca(?Person1, Person2, ?Ancestor).

lca(P1,P2,A) :- father(A,P1), father(A,P2), P1 \== P2.

lca(P1,P2,A) :- father(A,P1), father(A,P4), P1 \== P3, ancestor(P4, P2).

lca(P1,P2,A) :- father(A,P3), ancestor(P3,P1), father(A,P2), P3\== P2.

lca(P1,P2,A) :- father(A,P3), ancestor(P3, P1),

                father(A,P4), P3 \== P4, ancestor(P4, P2).


% cousin(?Cousin, ?Person).

cousin(C, P) :- lca(C, P, A),
                ancestor_d(A,C,C1),
                ancestor_d(A,P,C2),
                C1 > 1, C2 > 1.



%% 1st cousin, 2nd cousin, 3rd once removed, etc.
%% cousin_type(+Person1, +Person2, -CousinType, -Removed).

cousin_type(P1, P2, T, R) :- lca(P1, P2, A),
                             ancestor_d(A, P1, X),
                             ancestor_d(A, P2, Y),
                             (X>1),
                             (Y>1),
                             (T is (min(X,Y)-1)),
                             R is abs(X-Y).


