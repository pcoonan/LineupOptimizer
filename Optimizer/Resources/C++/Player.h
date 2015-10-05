// ============================================================================ 
// player.h
// ~~~~~~~~
// Patrick Coonan
// ============================================================================ 

#ifndef Player_H_
#define Player_H_

#include <string>

class Player
{
public:
	Player(std::string, std::string, int, int);
	Player();
	std::string name;
	std::string pos;
	int salary;
	int ppg;
	~Player();
	std::string position();
	std::string print();
	std::string pname();
};

#endif