#include <iostream>
#include <algorithm>
#include <vector>
#include <cstdlib>

using namespace std;

static int maxWeight = 2000; //max weight in grams
int numCandies = 16;

//structure describing a piece of candy
struct candy {
  int weight;
  int tastiness;
  double tToWRatio; //tastiness to weight ratio
  int bagLoc = 3; //location of the candy (3 is not in a bag)
};

//structure describing a bag
struct Bag {
  int currentWeight = 0;
  int totalTastiness = 0;
};

//Bag bags[3];
vector<Bag> bags;
vector<candy> allCandies;

//int totalWeight = 0;
//int totalTastiness = 0;

void resetBags(){
  for(int z = 0; z < 3; z++){
    bags[z].currentWeight = 0;
    bags[z].totalTastiness = 0;
  }
}

void resetCandies(){
  //reset bagLoc for all allCandies
  for(int r = 0; r < numCandies; r++){
    allCandies[r].bagLoc = 3;
  }
}

//helper function that makes sort function sort by tToWRatio
bool sortByRatio(const candy &lhs, const candy &rhs){
  return lhs.tToWRatio > rhs.tToWRatio;
}

//helper function determines if candy can be added to a bag
bool canAddCandy(candy c, int bagNum){
  //bag is already at max weight
  if (bags[bagNum].currentWeight == maxWeight){
    return false;
  }
  //bag will over fill when candy is added
  else if (bags[bagNum].currentWeight + c.weight > maxWeight){
    return false;
  }
  //candy has already been allocated
  else if(c.bagLoc != 3){
    return false;
  }
  else return true;
}

//helper function to get total tastiness of all bags
int totalTaste(){
  int totalTastinessAllBags = 0;
  for(int i = 0; i < 3; i++){
    totalTastinessAllBags += bags[i].totalTastiness;
  }
  return totalTastinessAllBags;
}

//function to add candy to a bag
void addToBag(candy c, int bagNum){
  bags[bagNum].currentWeight += c.weight;
  bags[bagNum].totalTastiness += c.tastiness;
  //c.bagLoc = bagNum;
}

//function to remove a candy from a bag
void removeCandy(candy c, int bagNum){
  bags[bagNum].currentWeight -= c.weight;
  bags[bagNum].totalTastiness -= c.tastiness;
  c.bagLoc = 3;
}

//function fills a bag with candy
void fill(int bagNum){
    //for each piece of candy, assign to proper bag
    for(int j = 0; j < numCandies; j++){
      if(!canAddCandy(allCandies[j], bagNum)){
        continue;
      }
      //candy can fit so add
      else{
        addToBag(allCandies[j], bagNum);
        allCandies[j].bagLoc = bagNum;
      }
    }

}

//function removes candy from given bag until bag is not over weight
void unfill(int bagNum){
  for(int c = numCandies - 1; c >= 0; c--){
    if(bags[bagNum].currentWeight > maxWeight && allCandies[c].bagLoc == bagNum){
      removeCandy(allCandies[c], bagNum);
    }
  }
}

//greedy algorithm approach to solution
void greedy(){
  //sort allCandies for greedy fill
  sort(allCandies.begin(), allCandies.end(), sortByRatio);
  for(int i = 0; i < 3; i++){
    fill(i);
  }
  //print results
  cout << "Greedy: " << totalTaste() << "\n";
}

//interative refinement approach
void iterative(){
  int best = 0;

  //for 1000 iterations
  for(int i = 0; i < 1000; i++){
    resetBags();
    resetCandies();

    //randomly add all candies to bags
    for(int a = 0; a < numCandies; a++){
      int randBag = rand() % 4;
      addToBag(allCandies[a], randBag);
      allCandies[a].bagLoc = randBag;
    }

    sort(allCandies.begin(), allCandies.end(), sortByRatio);

    bool improving = true;
    while(improving){
      improving = false;

      for(int j = 0; j < numCandies; j++){
        for(int b = 0; b < 3; b++){
          int oldBag = allCandies[j].bagLoc; //get previous location of candy

          int oldTaste = totalTaste(); //keep track of old value

          vector<candy> oldSolve = allCandies; //keep copy of original solution
          vector<Bag> oldBags = bags; //keep copy of orginal bag weights and taste

          if(oldBag != 3) removeCandy(allCandies[j], oldBag);
          addToBag(allCandies[j], b); //move candy to new bag
          if(oldBag != 3) fill(oldBag); //fix old bag
          unfill(b); //fix new bag

          //if better save number
          if(totalTaste() > oldTaste) {
            improving = true; //got better so keep going
            if(totalTaste() > best) best = totalTaste(); //if best taste, save
            break;
          }
          else{ //revert back to old solution
            allCandies = oldSolve;
            bags = oldBags;
          }
        }
      }
    }
  }
  //print results
  cout << "Refinement: " << best << "\n";
}




int main(void){

  //read in data
  for(int i = 0; i < numCandies; i++){
    candy nextCandy;
    cin >> nextCandy.weight >> nextCandy.tastiness;
    nextCandy.tToWRatio = (double)nextCandy.tastiness / nextCandy.weight;
    allCandies.push_back(nextCandy);
  }

/*  //get all tastiness and weight
  for(int i = 0; i < numCandies; i++){
    totalTastiness += allCandies[i].tastiness;
    totalWeight += allCandies[i].weight;
  }
*/

  //set bags
  for(int i = 0; i < 3; i++){
    Bag newBag;
    bags.push_back(newBag);
  }

  //solve for greedy
  greedy();

  resetCandies();
  resetBags();
  iterative();

  for(int i = 0; i < numCandies; i++){
    cout << "Bag Location: " << allCandies[i].bagLoc << "\n";
  }


  return 0;
}
