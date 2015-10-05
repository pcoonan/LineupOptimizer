#include "Player.h"
#include <string>
#include <sstream>

using namespace std;
Player::Player()
{

}

Player::Player(string ipos, string iname, int isalary, int ippg)
{
	name = iname;
	pos = ipos;
	salary = isalary;
	ppg = ippg;
}


Player::~Player()
{
}


string Player::position()
{
	return pos;
}


string Player::print()
{
	ostringstream oss;
	oss << name + " " + pos + " ";
	oss << salary;
	oss << " ";
	oss << ppg;
	return oss.str();
}


string Player::pname()
{
	return name;
}
