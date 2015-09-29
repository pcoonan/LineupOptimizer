#include "NFL_Analyzer.h"
#include <vector>
#include "Player.h"

using namespace std;

vector<Player> qb;		// Quarterback list - one per team
vector<Player> rb;		// Running back list - two per team
vector<Player> wr;		// Wide reciever list - three per team
vector<Player> te;		// Tight end list - one per team
vector<Player> dst;		// Defense/Special Teams list - one per team

						// In addition, there is one flex position, either a rb, wr, or te

NFL_Analyzer::NFL_Analyzer()
{
}

NFL_Analyzer::NFL_Analyzer(vector<Player>& pVec){

}

NFL_Analyzer::~NFL_Analyzer()
{
}
