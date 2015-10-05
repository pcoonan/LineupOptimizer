#ifndef NBA_Analyzer_H_
#define NBA_Analyzer_H_

#include <vector>
#include "Player.h"

class NBA_Analyzer
{
public:
	NBA_Analyzer(std::vector<Player>&);
	NBA_Analyzer();
	~NBA_Analyzer();
	std::vector<Player> fillPos(std::vector<Player> players, std::string pos);
	void print_pos(std::string pos);
	std::vector<Player*> optimize();
	bool findPG(std::vector<Player*>& curList, std::vector<Player*>& bestList, int salary, int ppg);
	bool findSG(std::vector<Player*>& curList, std::vector<Player*>& bestList, int salary, int ppg);
	bool findSF(std::vector<Player*>& curList, std::vector<Player*>& bestList, int salary, int ppg);
	bool findPF(std::vector<Player*>& curList, std::vector<Player*>& bestList, int salary, int ppg);
	bool findC(std::vector<Player*>& curList, std::vector<Player*>& bestList, int salary, int ppg);
	bool findG(std::vector<Player*>& curList, std::vector<Player*>& bestList, int salary, int ppg);
	bool findF(std::vector<Player*>& curList, std::vector<Player*>& bestList, int salary, int ppg);
	bool findUtil(std::vector<Player*>& curList, std::vector<Player*>& bestList, int salary, int ppg);
	bool contains(Player*& player, std::vector<Player*>& list);
	void makeBest(std::vector<Player*>& curList, std::vector<Player*>& bestList);
	int scoreList(std::vector<Player*>& list);
	int totalCombination();
};

#endif