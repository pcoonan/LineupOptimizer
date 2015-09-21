#ifndef NFL_Analyzer_H_
#define NFL_Analyzer_H_

#include <vector>
#include "Player.h"

class NFL_Analyzer
{
public:
	NFL_Analyzer(std::vector<Player>&);
	NFL_Analyzer();
	
	~NFL_Analyzer();
};

#endif