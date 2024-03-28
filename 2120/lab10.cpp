#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <map>
#include <unordered_map>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

typedef string Node;
vector<Node> allNodes;
map<Node, vector<Node>> neighbors;
map<Node, int> dist;
map<Node, Node> pred;

string start, target;


int longestDist = 0;
int pathDist = 0;
Node longestStart, longestEnd;

//build graph (adjacency list of nodes)
void build_graph(void){
  //for all words
  for (Node &n : allNodes){
    Node origWord = n;
    //for all characters in word
    for(int i = 0; i < 5; i++){
      char origChar = n[i];
      //replace character with every other character
      for(char c = 'a'; c <= 'z'; c++){
        if(c == origChar) continue;
        n[i] = c;
        //if new version of words is found, add to map of neighbors for origWord
        std::vector<Node>::iterator it = find(allNodes.begin(), allNodes.end(), n);
        if(it != allNodes.end()) {
          Node nbr = n;
          neighbors[origWord].push_back(nbr);
        }
        n[i] = origChar;
      }
    }
  }
}

//breadth first search
void bfs(Node start){
  for(Node &a : allNodes) dist[a] = allNodes.size();
  dist[start] = 0;
  queue<Node> to_visit;
  to_visit.push(start);
  while(!to_visit.empty()){
    Node x = to_visit.front();
    to_visit.pop();

    //store longest distance and start and end words
    if(dist[x] > longestDist){
      longestStart = start;
      longestEnd = x;
      longestDist = dist[x];
    }

    for(Node n : neighbors[x]){
      if(dist[n] == allNodes.size()){
        dist[n] = 1 + dist[x];
        pred[n] = x;
        to_visit.push(n);
      }
    }
  }
}



//find the length of the word ladder
void path_length(Node start, Node end){
    if(end == "") return;
    if(start.compare(end) != 0) path_length(start, pred[end]);
    pathDist++;
}


//print out the word ladder
void print_path(Node start, Node end){
  if(end == "") return;
  if(start.compare(end) != 0) print_path(start, pred[end]);
  cout << end << "\n";
}


int main(void){
  //read in data
  string word;
  Node newWord;
  ifstream wordfile;
  wordfile.open("words5.txt");
  while (wordfile >> word){
    newWord = word;
    allNodes.push_back(newWord);
  }
  wordfile.close();



  build_graph();

  /*
    //to test word ladder
    cout << "Enter start word: ";
    cin >> start;
    cout << "Enter end word: ";
    cin >> target;

    Node s = start;
    Node e = target;

  //print results of search
  bfs(s);
  print_path(s, e);
  path_length(s, e);
  cout << "Diameter is: " << pathDist << "\n";
*/


  //for all words
  for(Node n : allNodes){
    cout << n << "\n";
    bfs(n);
    dist.clear();
    pred.clear();
  }

  dist.clear();
  pred.clear();
  //print results of search
  bfs(longestStart);
  print_path(longestStart, longestEnd);
  cout << "Longest Diameter is: " << longestDist << "\n";


/*
  //for all words
  for(Node a : allNodes){
    dist.clear();
    pred.clear();
    bfs(a);
    //compare to all other words
    for(Node b : allNodes){
      //bfs(a);
      path_length(a, b);
      //if new length is better than previous best, replace
      if(pathDist > longestDist){
        longestDist = pathDist;
        longestStart = a;
        longestEnd = b;
      }
      //clear out for next search
      //dist.clear();
      //pred.clear();
      pathDist = 0;
    }
  }

  //print results of search
  bfs(longestStart);
  print_path(longestStart, longestEnd);
  cout << "Longest Diameter is: " << longestDist << "\n";

*/



  return 0;

}
